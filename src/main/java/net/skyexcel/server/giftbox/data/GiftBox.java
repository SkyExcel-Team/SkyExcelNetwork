package net.skyexcel.server.giftbox.data;

import net.skyexcel.server.SkyExcelNetworkMain;
import net.skyexcel.server.giftbox.gui.CheckButton;
import net.skyexcel.server.giftbox.gui.MailBoxButton;
import net.skyexcel.server.giftbox.gui.NextButton;
import net.skyexcel.server.giftbox.gui.PreviousButton;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import skyexcel.data.file.Config;
import skyexcel.data.file.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GiftBox {
    private OfflinePlayer offlinePlayer;

    private Player player;


    private Inventory inv;

    private Config config;

    private int page = 1;

    private GUI gui;

    private final int mailBox = 4;

    private final int checkBox = 49;

    private String title = "선물함";
    private final String invPath = "inv";

    private final int[] BLACK_STAINED_GLASS_PANE = {0, 1, 2, 3, 5, 6, 7, 8,
            45, 46, 47, 51, 52, 53};

    private final int nextButton = 50;

    private final int previousButton = 48;

    public GiftBox(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        this.config = new Config("giftbox/" + offlinePlayer.getUniqueId() + "/" + page);
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
        this.gui = new GUI(config);
        if (offlinePlayer.isOnline())
            this.player = offlinePlayer.getPlayer();
    }


    public GiftBox(OfflinePlayer offlinePlayer, int page) {
        this.offlinePlayer = offlinePlayer;
        this.config = new Config("giftbox/" + offlinePlayer.getUniqueId() + "/" + page);
        this.config.setPlugin(SkyExcelNetworkMain.getPlugin());
        this.page = page;
        this.gui = new GUI(config);
        if (offlinePlayer.isOnline())
            this.player = offlinePlayer.getPlayer();
    }


    public void sendItemStack(ItemStack itemStack) {

        //만약, 플레이어의 선물함이 없을 경우
        if (getInventory().isEmpty()) {
            Inventory inv = Bukkit.createInventory(null, 54, title);

            Arrays.stream(BLACK_STAINED_GLASS_PANE).forEach(slot -> {
                inv.setItem(slot, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));
            });

            addItem(itemStack, inv, 9, 45);
            player.sendMessage("성공적으로 아이템을 보냈습니다!");

            gui.saveInventory(invPath, inv, title);
        } else {

            Inventory inv = getInventory(getInventory().size() - 1);
            if (inv != null) {
                //TODO 마지막 페이지의 인벤토리가 꽉 찼을때를 인식해서 추가를 해야함.
                if (isInventoryFull(inv)) {
                    player.sendMessage("페이지 추가됨.");
                    addPage();
                } else {

                    addItem(itemStack, inv, 9, 45);
                    player.sendMessage("성공적으로 아이템을 보냈습니다!");
                }
            }
            gui.saveInventory(invPath, inv, title);
        }
    }

    public void open(Player player) {
        List<Inventory> invArray = getInventory();
        if (!invArray.isEmpty()) {
            Inventory inv = invArray.get(0);

            new MailBoxButton().setInventory(mailBox, inv);
            new CheckButton().setInventory(checkBox, inv);
            new NextButton().setInventory(nextButton, inv);
            new PreviousButton().setInventory(previousButton, inv);

            player.openInventory(inv);

        } else {
            inv = Bukkit.createInventory(null, 54, title);

            Arrays.stream(BLACK_STAINED_GLASS_PANE).forEach(slot -> {
                inv.setItem(slot, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));
            });

            new MailBoxButton().setInventory(mailBox, inv);
            new CheckButton().setInventory(checkBox, inv);
            new NextButton().setInventory(nextButton, inv);
            new PreviousButton().setInventory(previousButton, inv);

            player.openInventory(inv);
        }
    }

    public void receiveReward(Player player, Inventory inv) {
        List<ItemStack> items = getContents(inv);
        if (!items.isEmpty()) {
            for (ItemStack itemStack : items) {
                player.getInventory().addItem(itemStack);
                itemStack.setAmount(0);
            }
            player.sendMessage("§e모든 보상을 수령하였습니다.");
            player.closeInventory();
        } else {
            player.sendMessage("§c보상이 존재하지 않습니다.");
            player.closeInventory();
        }
    }


    public void saveInventory(Inventory inv) {

        if (!getContents(inv).isEmpty()) {

        } else {
            config.delete();
        }
    }


    public void nextPage(Player player) {
        List<Inventory> invArray = getInventory();
        if (invArray.size() > 1) {
            int nextPage = 0;
            ++nextPage;
            Inventory inv = invArray.get(nextPage);
            if (inv != null) {
                new MailBoxButton().setInventory(mailBox, inv);
                new CheckButton().setInventory(checkBox, inv);
                new NextButton().setInventory(nextButton, inv);
                new PreviousButton().setInventory(previousButton, inv);

                player.openInventory(inv);
            } else {
                player.sendMessage("다음 페이지가 없습니다.");
            }

        } else {
            player.sendMessage("다음 페이지가 없습니다.");
        }
    }


    /**
     * @return 플레이어의 모든 선물함 인벤토리를 불러오는 메소드.
     * 페이징 형식으로 0,1,2,3,4,5, 이런 식으로 불러올 수 있다.
     */
    public List<Inventory> getInventory() {
        List<Inventory> inv = new ArrayList<>();

        Config path = new Config("giftbox/" + offlinePlayer.getUniqueId() + "/");
        path.setPlugin(SkyExcelNetworkMain.getPlugin());

        for (String name : path.fileListName()) {
            Config newConfig = new Config("giftbox/" + offlinePlayer.getUniqueId() + "/" + name);
            newConfig.setPlugin(SkyExcelNetworkMain.getPlugin());
            GUI newGUI = new GUI(newConfig);
            Inventory newInv = newGUI.getInventory(invPath);
            if (newInv != null)
                inv.add(newInv);
        }
        return inv;
    }

    /***
     * 번호에 맞게 페이징이 이루어진 파일을 불러올때 쓰이는 메소드이다.
     * @param index 인벤토리를 불러올 번호
     * @return 아무값이 없으면 empty값이므로 해당 메소드를 이용하기 전, 위에 @getInventory값이 비어있는지 우선 체크 후, 불러오는게 좋다.
     */
    public Inventory getInventory(int index) {
        List<Inventory> inv = new ArrayList<>();

        Config path = new Config("giftbox/" + offlinePlayer.getUniqueId() + "/");
        path.setPlugin(SkyExcelNetworkMain.getPlugin());

        for (String name : path.fileListName()) {
            Config newConfig = new Config("giftbox/" + offlinePlayer.getUniqueId() + "/" + name);
            newConfig.setPlugin(SkyExcelNetworkMain.getPlugin());
            GUI newGUI = new GUI(newConfig);
            Inventory newInv = newGUI.getInventory(invPath);
            if (newInv != null)
                inv.add(newInv);
        }
        return (!inv.isEmpty() ? inv.get(index) : null);
    }

    /**
     * 선물함으로부터 모든 아이템이 아닌, 범위 아이템을 모두 가져온다. 만약 반환값이 없으면 해당 인벤토리를 삭제하는 용도로 만들어 질것 같다.
     *
     * @param inv 불러올 인벤토리 변수
     * @return 구역으로부터 얻은 아이템 리스트
     */
    private List<ItemStack> getContents(Inventory inv) {
        List<ItemStack> itemStacks = new ArrayList<>();
        for (int i = 9; i < 45; i++) {
            ItemStack itemStack = inv.getItem(i);
            if (itemStack != null) {
                itemStacks.add(itemStack);
            }
        }
        return itemStacks;
    }


    public boolean isInventoryFull(Inventory inv) {
        List<ItemStack> itemStacks = new ArrayList<>();
        for (int i = 9; i < 45; i++) {
            ItemStack itemStack = inv.getItem(i);
            if (itemStack != null) {
                itemStacks.add(itemStack);
            }
        }
        return itemStacks.size() == 36;
    }


    /***
     * 특정 슬롯에서부터 슬롯까지 아이템을 추가해주는 메소드이다.
     * @param itemStack
     * @param inv
     * @param from 아이템 추가를 시작할 위치
     * @param to 아이템 추가를 끝낼 위치
     */
    public void addItem(ItemStack itemStack, Inventory inv, int from, int to) {

        if (itemStack != null) {
            for (int i = from; i < to; i++) {
                ItemStack newItem = inv.getItem(i);
                if (newItem == null) {
                    inv.setItem(i, itemStack);
                    break;
                }
            }
        }
    }


    public void addPage() {
        Config path = new Config("giftbox/" + offlinePlayer.getUniqueId() + "/");
        path.setPlugin(SkyExcelNetworkMain.getPlugin());
        int size = path.fileListName().size();
        ++size;
        Config newConfig = new Config("giftbox/" + offlinePlayer.getUniqueId() + "/" + size);
        newConfig.setPlugin(SkyExcelNetworkMain.getPlugin());
        GUI newGUI = new GUI(newConfig);

        Inventory newInv = Bukkit.createInventory(null, 54, title);

        Arrays.stream(BLACK_STAINED_GLASS_PANE).forEach(slot -> {
            newInv.setItem(slot, new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1));
        });

        newGUI.saveInventory(invPath, newInv, title);
    }


    public int getMailBox() {
        return mailBox;
    }

    public int getCheckBox() {
        return checkBox;
    }

    public int getPreviousButton() {
        return previousButton;
    }

    public int getNextButton() {
        return nextButton;
    }
}
