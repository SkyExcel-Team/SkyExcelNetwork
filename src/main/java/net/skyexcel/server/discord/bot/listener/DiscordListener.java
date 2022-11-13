package net.skyexcel.server.discord.bot.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.skyexcel.server.discord.SkyExcelNetworkDiscordMain;
import net.skyexcel.server.discord.utils.VerifyUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class DiscordListener implements EventListener {
    private Color embedColor;
    private final Logger log = Bukkit.getLogger();

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

                TextInput verifyCode = TextInput.create("verifyCode", "인증코드", TextInputStyle.SHORT)
                        .setPlaceholder("서버에서 발급받은 인증코드를 입력해주세요.")
                        .setRequired(true)
                        .setMinLength(6)
                        .setMaxLength(6)
                        .build();
                Modal modal = Modal.create("verifyCode", "디스코드 계정 연동")
                        .addActionRow(verifyCode)
                        .build();

                e.replyModal(modal).queue();
            }
        } else if (event instanceof ModalInteractionEvent e) {
            if (e.getModalId().equals("verifyCode")) {
                //입력받은 코드
                String currentCode = e.getValue("verifyCode").getAsString();
                var wrapper = new Object(){ InteractionHook message = null; };

                //6자리인지 확인
                if (currentCode.length() != 6) {
                    e.replyEmbeds(getEmbed("only6Characters")).setEphemeral(true).queue(msg -> wrapper.message = msg);
                    return;
                }

                //존재하는 코드인지 확인
                try {
                    if (!VerifyUtils.containsCode(currentCode)) {
                        e.replyEmbeds(getEmbed("notAvailableCode")).setEphemeral(true).queue(msg -> wrapper.message = msg);
                        return;
                    }

                    SkyExcelNetworkDiscordMain.data.setString(VerifyUtils.getPlayerUuid(currentCode).toString(), e.getUser().getId());
                    OfflinePlayer t = Bukkit.getOfflinePlayer(VerifyUtils.getPlayerUuid(currentCode));
                    if (t.isOnline()) {
                        t.getPlayer().sendMessage("디스코드 계정(" + e.getUser().getAsTag() + ")과 해당 계정이 연동되었습니다!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                //성공 임베드 전송
                e.replyEmbeds(getEmbed("successVerify")).setEphemeral(true).queue(msg -> wrapper.message = msg);

                //역할 지급
                try {
                    Guild guild = e.getJDA().getGuildById(SkyExcelNetworkDiscordMain.botConfig.getString("bot_settings.guildId"));
                    if (guild == null) {
                        wrapper.message.editOriginalEmbeds(getEmbed("notAvailableGuildId")).queue();
                        return;
                    }

                    Member member = guild.getMember(e.getUser());
                    if (member == null) {
                        wrapper.message.editOriginalEmbeds(getEmbed("notGuildMember")).queue();
                        return;
                    }

                    Role role = guild.getRoleById(SkyExcelNetworkDiscordMain.botConfig.getString("bot_settings.roles.verifiedRole"));
                    if (role == null) {
                        wrapper.message.editOriginalEmbeds(getEmbed("notAvailableRoleId")).queue();
                        return;
                    }

                    guild.addRoleToMember(member, role).queue();
                    wrapper.message.editOriginalEmbeds(getEmbed("successGiveRole")).queue();
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
                            wrapper.message.editOriginalEmbeds(getEmbed("failedModifyNick")).queue();
                            return;
                        } else
                            ex.printStackTrace();
                    }
                }

                VerifyUtils.removeVerifyCode(currentCode);
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
}