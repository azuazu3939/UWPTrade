package azuazu3939.uwptrade;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.papermc.paper.event.entity.EntityMoveEvent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

import static azuazu3939.uwptrade.ShopContainer.getShopContainer;
import static azuazu3939.uwptrade.UWPTrade.getItems;
import static azuazu3939.uwptrade.UWPTrade.getItems1;

public class UWPTradeListener implements Listener {

    @EventHandler
    public void onDamaged(@NotNull EntityDamageEvent event) {

        Entity entity = event.getEntity();

        if (entity.getPersistentDataContainer().has(Objects.requireNonNull(NamespacedKey.fromString("tradable", UWPTrade.inst())), PersistentDataType.STRING)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onMove(@NotNull EntityMoveEvent event) {

        Entity entity = event.getEntity();
        if (entity.getPersistentDataContainer().has(Objects.requireNonNull(NamespacedKey.fromString("tradable", UWPTrade.inst())), PersistentDataType.STRING)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(@NotNull PlayerInteractEntityEvent event) {

        Entity entity = event.getRightClicked();
        if (entity.getPersistentDataContainer().has(Objects.requireNonNull(NamespacedKey.fromString("tradable", UWPTrade.inst())), PersistentDataType.STRING)) {
            if (event.getPlayer().isSneaking() && event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                entity.remove();
                event.getPlayer().sendMessage(ChatColor.AQUA + "デイリーショップを消しました。");
            } else {
                event.getPlayer().openInventory(getShopContainer());
            }
        }
    }

    @EventHandler
    public void onClick(@NotNull InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().getHolder() instanceof ShopHolder) {
            if (event.getSlot() != 19 &&
                    event.getSlot() != 20 && event.getSlot() != 21 &&
                    event.getSlot() != 22 && event.getSlot() != 23 &&
                    event.getSlot() != 24 && event.getSlot() != 25 &&
                    event.getSlot() != 28 && event.getSlot() != 29 &&
                    event.getSlot() != 30 && event.getSlot() != 31 &&
                    event.getSlot() != 32 && event.getSlot() != 33 &&
                    event.getSlot() != 34 && event.getSlot() != 37 &&
                    event.getSlot() != 38 && event.getSlot() != 39 &&
                    event.getSlot() != 40 && event.getSlot() != 41 &&
                    event.getSlot() != 42 && event.getSlot() != 43) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDrag(@NotNull InventoryDragEvent event) {
        if (event.getInventory().getHolder() instanceof ShopHolder) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onCloseInv(@NotNull InventoryCloseEvent event) {

        Inventory inv = event.getInventory();
        if (inv.getHolder() instanceof ShopHolder) {
            int point = 0;
            for (int i = 19; i <= 43; i++) {
                if (i == 26 || i == 27 || i == 35 || i == 36) continue;
                if (inv.getItem(i) != null && Objects.requireNonNull(inv.getItem(i)).hasItemMeta() &&
                        Objects.requireNonNull(inv.getItem(i)).getItemMeta().getPersistentDataContainer().has(NamespacedKey.minecraft("farming"), PersistentDataType.INTEGER) && (
                        Objects.requireNonNull(inv.getItem(i)).getType() == MythicBukkit.inst().getItemManager().getItemStack(getItems.get(0)).getType() ||
                        Objects.requireNonNull(inv.getItem(i)).getType() == MythicBukkit.inst().getItemManager().getItemStack(getItems.get(1)).getType() ||
                        Objects.requireNonNull(inv.getItem(i)).getType() == MythicBukkit.inst().getItemManager().getItemStack(getItems.get(2)).getType())) {
                    Integer integer = Objects.requireNonNull(inv.getItem(i)).getItemMeta().getPersistentDataContainer().get(NamespacedKey.minecraft("farming"), PersistentDataType.INTEGER);
                    if (integer == null) continue;
                    point += Integer.parseInt(String.valueOf(integer)) * Objects.requireNonNull(inv.getItem(i)).getAmount();
                }
            }
            //4ポイント * 64 * 21で5376、念のため5375で設置。
            int random = (int) (Math.random() * 5375);
            HumanEntity player = event.getPlayer();
            if (random <= point) {
                Random ran = new Random();
                int i = ran.nextInt(3);
                ItemStack item = MythicBukkit.inst().getItemManager().getItemStack(getItems1.get(i));
                player.getInventory().addItem(item);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', item.getItemMeta().getDisplayName() + "&r&fを獲得しました。"));
            } else if (point != 0){
                player.sendMessage(ChatColor.GREEN + "残念ながら交易に失敗しました。 "  + ChatColor.WHITE + "蓄積値 " + point + "/" + 5376);
            }
        }
    }
}
