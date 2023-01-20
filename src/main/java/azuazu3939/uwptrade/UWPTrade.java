package azuazu3939.uwptrade;

import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.items.MythicItem;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class UWPTrade extends JavaPlugin {

    private static UWPTrade trade;
    public UWPTrade() {trade = this;}
    public static UWPTrade inst() {return trade;}

    static final List<String> getItems = new ArrayList<>();
    static final List<String> getItems1 = new ArrayList<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("shopCreate")).setExecutor(new CreateShop());

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new UWPTradeListener(), this);

        getItems.clear();
        getItems1.clear();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static @Nullable List<String> chanceMythicItem() {

        List<String> mythicItems = new ArrayList<>();
        try {
            for (String string: UWPTrade.inst().getConfig().getStringList("Shop.DailyShop.PlayerItem")) {
                Optional<MythicItem> item = MythicBukkit.inst().getItemManager().getItem(string);
                if (item.isPresent()) {mythicItems.add(string);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mythicItems.size() == 0) {
            return null;
        } else {
            for (int i = 1; i <= 3; i++) {
                int num = new Random().nextInt(mythicItems.size());
                getItems.add(mythicItems.get(num));
            }
            return getItems;
        }
    }

    public static @Nullable List<String> chanceMythicItemResult() {

        List<String> mythicItemList = new ArrayList<>();
        try {
            for (String string: UWPTrade.inst().getConfig().getStringList("Shop.DailyShop.ResultItem")) {
                Optional<MythicItem> item = MythicBukkit.inst().getItemManager().getItem(string);
                if (item.isPresent()) {mythicItemList.add(string);}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (mythicItemList.size() == 0) {
            return null;
        } else {
            for (int i = 1; i <= 3; i++) {
                int num = new Random().nextInt(mythicItemList.size());
                getItems1.add(mythicItemList.get(num));
            }
            return getItems1;
        }
    }
}
