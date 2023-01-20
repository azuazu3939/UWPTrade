package azuazu3939.uwptrade;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SpawnEntity {

    public void getVillager(@NotNull World world, Location loc) {

        LivingEntity entity = (LivingEntity) world.spawnEntity(loc, EntityType.VILLAGER);
        entity.getPersistentDataContainer().set(Objects.requireNonNull(NamespacedKey.fromString("tradable", UWPTrade.inst())), PersistentDataType.STRING, "true");
        entity.setGravity(false);
        entity.setSilent(true);
        entity.customName(Component.text(ChatColor.translateAlternateColorCodes('&', "&b&l交易")));
        entity.setCustomNameVisible(true);
    }
}
