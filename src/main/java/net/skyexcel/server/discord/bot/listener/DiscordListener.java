package net.skyexcel.server.discord.bot.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import net.skyexcel.server.discord.SkyExcelNetworkDiscordMain;
import net.skyexcel.server.discord.utils.VerifyUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;

public class DiscordListener implements EventListener {
    private Color embedColor;

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof ReadyEvent) {
            embedColor = new Color(
                    Integer.parseInt(SkyExcelNetworkDiscordMain.botConfig.getString("bot_messages.embedColor").substring(1, 3), 16),
                    Integer.parseInt(SkyExcelNetworkDiscordMain.botConfig.getString("bot_messages.embedColor").substring(3, 5), 16),
                    Integer.parseInt(SkyExcelNetworkDiscordMain.botConfig.getString("bot_messages.embedColor").substring(5, 7), 16)
            );
        } else if (event instanceof SlashCommandInteractionEvent e) {
            if (e.getName().equalsIgnoreCase("인증생성")) {
                if (!e.isFromGuild()) {
                    e.replyEmbeds(getEmbed("onlyGuild")).setEphemeral(true).queue();

                    return;
                }

                if (!isModerator(e.getGuild(), e.getUser())) return;

                MessageEmbed embed = new EmbedBuilder()
                        .setColor(embedColor)
                        .setTitle("` \uD83D\uDD17 디스코드 계정 연동하기  \uD83D\uDD17 `")
                        .setDescription("`ㆍ\uD83E\uDD97 | 메뚜기팜 디스코드를 이용 하시려면 마인크래프트 계정을 연동해주세요! ` \n" +
                                "`ㆍ✅ | 아래 '인증번호 입력하기' 버튼을 클릭하여 연동을 하세요! `\n")
                        .build();

                Button openVerifyModal = Button.primary("openVerifyModal", "인증번호 입력하기");
                Button howToVerify = Button.secondary("howToVerify", "어떻게 연동하나요?");
                Button askForVerify = Button.secondary("askForVerify", "문의하기");

                MessageCreateData message = new MessageCreateBuilder()
                        .addEmbeds(embed)
                        .addComponents(ActionRow.of(openVerifyModal, howToVerify, askForVerify))
                        .build();

                e.reply("인증 임베드를 생성하였습니다!").setEphemeral(true).queue();
                e.getChannel().sendMessage(message).queue();
            }
        } else if (event instanceof ButtonInteractionEvent e) {
            if (e.getButton().getId().equals("openVerifyModal")) {
                TextInput verifyCode = TextInput.create("verifyCode", "인증코드", TextInputStyle.SHORT)
                        .setPlaceholder("서버에서 발급받은 코드를 입력해주세요.")
                        .setMinLength(6)
                        .setMaxLength(6)
                        .build();

                Modal modal = Modal.create("verifyCode", "디스코드 연동하기")
                        .addActionRows(ActionRow.of(verifyCode))
                        .build();

                e.replyModal(modal).queue();
            } else if (e.getButton().getId().equals("howToVerify")) {
                e.replyEmbeds(getEmbed("howToVerify")).setEphemeral(true).queue();
            } else if (e.getButton().getId().equals("askForVerify")) {
                e.replyEmbeds(getEmbed("askForVerify")).setEphemeral(true).queue();
            }
        } else if (event instanceof ModalInteractionEvent e) {
            if (e.getModalId().equals("verifyCode")) {
                //입력받은 코드
                String currentCode = e.getValue("verifyCode").getAsString();
                var wrapper = new Object(){ InteractionHook message = null; };

                //6자리인지 확인
                if (currentCode.length() != 6) {
                    e.replyEmbeds(getEmbed("only6Characters")).setEphemeral(true).queue();
                    return;
                }

                //존재하는 코드인지 확인
                try {
                    if (!VerifyUtils.containsCode(currentCode)) {
                        e.replyEmbeds(getEmbed("notAvailableCode")).setEphemeral(true).queue();
                        return;
                    }

                    SkyExcelNetworkDiscordMain.data.setString(VerifyUtils.getPlayerUuid(currentCode).toString(), e.getUser().getId());
                    OfflinePlayer t = Bukkit.getOfflinePlayer(VerifyUtils.getPlayerUuid(currentCode));
                    if (t.isOnline()) {
                        t.getPlayer().sendMessage("架 디스코드 계정 §7(" + e.getUser().getAsTag() + ")§f과 §a연동§f되었습니다!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                //코드 삭제
                VerifyUtils.removeVerifyCode(currentCode);

                //성공 임베드 전송
                e.replyEmbeds(getEmbed("successVerify")).setEphemeral(true).queue();

                //역할 지급
                try {
                    while(wrapper.message == null) {}

                    Guild guild = e.getJDA().getGuildById(SkyExcelNetworkDiscordMain.botConfig.getString("bot_settings.guildId"));
                    if (guild == null) {
                        return;
                    }

                    Member member = guild.getMember(e.getUser());
                    if (member == null) {
                        return;
                    }

                    Role role = guild.getRoleById(SkyExcelNetworkDiscordMain.botConfig.getString("bot_settings.roles.verifiedRole"));
                    if (role == null) {
                        return;
                    }

                    guild.addRoleToMember(member, role).queue();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                //닉네임 변경
                if (SkyExcelNetworkDiscordMain.botConfig.getBoolean("bot_settings.changeDiscordName.enable")) {
                    try {
                        String nickname = SkyExcelNetworkDiscordMain.botConfig.getString("bot_settings.changeDiscordName.name")
                                .replace( "%minecraft_name%",
                                        Bukkit.getPlayer(VerifyUtils.getPlayerUuid(currentCode)).getName())
                                .replace("%discord_name%", e.getUser().getName())
                                .replace("%discord_tag%",
                                        e.getUser().getAsTag().substring(e.getUser().getAsTag().length() - 4))
                                .replace("%discord_full%", e.getUser().getAsTag());

                        Guild guild = SkyExcelNetworkDiscordMain.bot.getJDA().getGuildById(SkyExcelNetworkDiscordMain.botConfig.getString("bot_settings.guildId"));

                        guild.getMember(e.getUser()).modifyNickname(nickname).queue();
                    } catch (Exception ex) {
                        if (ex instanceof HierarchyException) {
                            return;
                        } else
                            ex.printStackTrace();
                    }
                }
            }
        }
    }

    private MessageEmbed getEmbed(String key) {
        return new EmbedBuilder()
                .setColor(embedColor)
                .setTitle(SkyExcelNetworkDiscordMain.botConfig.getString("bot_messages." + key + ".title"))
                .setDescription(SkyExcelNetworkDiscordMain.botConfig.getString("bot_messages." + key + ".description"))
                .setThumbnail(SkyExcelNetworkDiscordMain.botConfig.getString("bot_messages." + key + ".thumbnailURL"))
                .build();
    }

    private Boolean isModerator(Guild guild, User user) {
        Role role = guild.getRoleById(SkyExcelNetworkDiscordMain.botConfig.getString("bot_settings.roles.moderatorRole"));

        if (guild.getMemberById(user.getId()).getRoles().contains(role))
            return true;
        else
            return false;
    }
}