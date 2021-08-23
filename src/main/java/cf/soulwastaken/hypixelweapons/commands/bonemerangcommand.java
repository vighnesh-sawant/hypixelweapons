package cf.soulwastaken.hypixelweapons.commands;

import cf.soulwastaken.hypixelweapons.hypixelweaponsplugin;
import cf.soulwastaken.hypixelweapons.utils.hypixelweaponsutil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class bonemerangcommand implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (sender.hasPermission(hypixelweaponsutil.HYPERION_GIVE_SELF_PERM)) {

                if (sender instanceof Player) {

                    Player player = (Player) sender;

                    ItemStack bonemerang = hypixelweaponsutil.createBonemerang();

                    player.getInventory().addItem(bonemerang);
                    sender.sendMessage(ChatColor.DARK_GREEN + " Gave bonemerang to " + player.getDisplayName());
                    return true;
                }
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', hypixelweaponsplugin.getInternalConfig().noPermMessage));
                return false;
            }
        } else if (args.length == 1) {
            if (Bukkit.getPlayer(args[0]) != null) {

                if (sender.hasPermission(hypixelweaponsutil.HYPERION_GIVE_OTHERS_PERM)) {
                    Player player = Bukkit.getPlayer(args[0]);
                    ItemStack bonemerang = hypixelweaponsutil.createBonemerang();

                    player.getInventory().addItem(bonemerang);
                    sender.sendMessage(ChatColor.DARK_GREEN + " Gave bonemerang to " + player.getDisplayName());
                    return true;
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', hypixelweaponsplugin.getInternalConfig().noPermMessage));
                    return false;
                }
            } else {
                sender.sendMessage(ChatColor.DARK_RED + "That is not a valid player!");

                return false;
            }

        } else {
            sender.sendMessage(ChatColor.AQUA + "/bonemerang <player>");
            return false;

        }
        return false;
    }
}
