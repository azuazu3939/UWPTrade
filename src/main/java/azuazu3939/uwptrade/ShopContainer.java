package azuazu3939.uwptrade;

import io.lumine.mythic.bukkit.MythicBukkit;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static azuazu3939.uwptrade.UWPTrade.*;

public class ShopContainer {

    public static @NotNull Inventory getShopContainer() {

        Inventory inv = Bukkit.createInventory(new ShopHolder(), 54, Component.text(ChatColor.translateAlternateColorCodes('&', "&b&l交易")));

        for (int i = 0; i <= 53; i++) {
            inv.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
        for (int i = 19; i <= 25; i++) {
            inv.setItem(i, new ItemStack(Material.AIR));
        }
        for (int i = 28; i <= 34; i++) {
            inv.setItem(i, new ItemStack(Material.AIR));
        }
        for (int i = 37; i <= 43; i++) {
            inv.setItem(i, new ItemStack(Material.AIR));
        }

        if (getItems.isEmpty()) {
            List<String> items = chanceMythicItem();
            if (items != null) {

                ItemStack item1 = MythicBukkit.inst().getItemManager().getItemStack(items.get(0));
                ItemStack item2 = MythicBukkit.inst().getItemManager().getItemStack(items.get(1));
                ItemStack item3 = MythicBukkit.inst().getItemManager().getItemStack(items.get(2));
                inv.setItem(1, setRequireItemStack(item1));
                inv.setItem(2, setRequireItemStack(item2));
                inv.setItem(3, setRequireItemStack(item3));
            }
        } else {
            ItemStack item1 = MythicBukkit.inst().getItemManager().getItemStack(getItems.get(0));
            ItemStack item2 = MythicBukkit.inst().getItemManager().getItemStack(getItems.get(1));
            ItemStack item3 = MythicBukkit.inst().getItemManager().getItemStack(getItems.get(2));
            inv.setItem(1, setRequireItemStack(item1));
            inv.setItem(2, setRequireItemStack(item2));
            inv.setItem(3, setRequireItemStack(item3));
        }

        if (getItems1.isEmpty()) {
            List<String> items1 = chanceMythicItemResult();
            if (items1 != null) {
                ItemStack item1 = MythicBukkit.inst().getItemManager().getItemStack(items1.get(0));
                ItemStack item2 = MythicBukkit.inst().getItemManager().getItemStack(items1.get(1));
                ItemStack item3 = MythicBukkit.inst().getItemManager().getItemStack(items1.get(2));
                inv.setItem(5, setResultItemStack(item1));
                inv.setItem(6, setResultItemStack(item2));
                inv.setItem(7, setResultItemStack(item3));
            }
        } else {
            ItemStack item1 = MythicBukkit.inst().getItemManager().getItemStack(getItems1.get(0));
            ItemStack item2 = MythicBukkit.inst().getItemManager().getItemStack(getItems1.get(1));
            ItemStack item3 = MythicBukkit.inst().getItemManager().getItemStack(getItems1.get(2));
            inv.setItem(5, setResultItemStack(item1));
            inv.setItem(6, setResultItemStack(item2));
            inv.setItem(7, setResultItemStack(item3));
        }
        return inv;
    }

    @Contract("_ -> param1")
    public static @NotNull ItemStack setResultItemStack(@NotNull ItemStack item) {

        ItemMeta meta = item.getItemMeta();
        if (item.getItemMeta().hasLore()) {
            List<Component> list = item.lore();
            Objects.requireNonNull(list).add(list.size(), Component.text(ChatColor.translateAlternateColorCodes('&', "&b&l交易報酬アイテム&f: 交易で獲得可能のアイテムです。")));
            meta.lore(list);
        } else {
            List<Component> list = new ArrayList<>();
            list.add(0, Component.text(ChatColor.translateAlternateColorCodes('&', "&b&l交易報酬アイテム&f: 交易で獲得可能のアイテムです。")));
            meta.lore(list);
        }
        item.setItemMeta(meta);

        return item;
    }

    @Contract("_ -> param1")
    public static @NotNull ItemStack setRequireItemStack(@NotNull ItemStack item) {

        ItemMeta meta = item.getItemMeta();
        if (item.getItemMeta().hasLore()) {
            List<Component> list = item.lore();
            Objects.requireNonNull(list).add(list.size(), Component.text(ChatColor.translateAlternateColorCodes('&', "&a&l交易要求アイテム&f: 交易に必要なアイテムです。")));
            meta.lore(list);
            item.setItemMeta(meta);
            list.add(list.size(), Component.text(ChatColor.translateAlternateColorCodes('&', "&a&l交易要求アイテム&fを空のスロットに入れ、")));
            meta.lore(list);
            item.setItemMeta(meta);
            list.add(list.size(), Component.text(ChatColor.translateAlternateColorCodes('&', "&f閉じることで確立で&b&l交易報酬アイテム&fを獲得可能。")));
            meta.lore(list);
        } else {
            List<Component> list = new ArrayList<>();
            list.add(0, Component.text(ChatColor.translateAlternateColorCodes('&', "&a&l交易要求アイテム&f: 交易に必要なアイテムです。")));
            meta.lore(list);
            item.setItemMeta(meta);
            list.add(list.size(), Component.text(ChatColor.translateAlternateColorCodes('&', "&a&l交易要求アイテム&fを空のスロットに入れ、")));
            meta.lore(list);
            item.setItemMeta(meta);
            list.add(list.size(), Component.text(ChatColor.translateAlternateColorCodes('&', "&f閉じることで確立で&b&l交易報酬アイテム&fを獲得可能。")));
            meta.lore(list);
        }
        item.setItemMeta(meta);

        return item;
    }
}
