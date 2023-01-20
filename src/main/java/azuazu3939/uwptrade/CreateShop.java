package azuazu3939.uwptrade;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class CreateShop implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        try {
            if (sender instanceof Player player) {
                if (!player.hasPermission("uwpTrade.command.shopCreate")) {
                    player.sendMessage(ChatColor.RED + "権限がありません。");
                    return true;
                }

                Set<Material> set = new HashSet<>();
                set.add(Material.WATER);
                set.add(Material.AIR);
                set.add(Material.LAVA);
                set.add(Material.LIGHT);
                Block block = player.getTargetBlock(set, 30).getRelative(BlockFace.UP);
                if (block == null) {
                    player.sendMessage(ChatColor.RED + "そこにShopは設置できません。");
                    return true;
                }
                Location loc  = block.getLocation();
                World world = player.getWorld();
                Location setLoc = new Location(world, loc.getX() + 0.5, loc.getY(), loc.getZ() + 0.5);
                new SpawnEntity().getVillager(world, setLoc);
                player.sendMessage(ChatColor.GREEN + "交易ショップを設置しました。");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
