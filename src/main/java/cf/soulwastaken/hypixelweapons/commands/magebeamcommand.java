package cf.soulwastaken.hypixelweapons.commands;

import cf.soulwastaken.hypixelweapons.hypixelweaponsplugin;
import cf.soulwastaken.hypixelweapons.utils.hypixelweaponsutil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class magebeamcommand implements CommandExecutor  {

   public static HashMap<Player, Boolean> beamMap = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (sender.hasPermission(hypixelweaponsutil.HYPERION_GIVE_SELF_PERM)) {

                Player player = (Player) sender;
                if (beamMap.containsKey(player)) {
                    boolean beamboolean = beamMap.get(player);

                    if (beamboolean) {
                        beamMap.put(player, true);
                        sender.sendMessage(ChatColor.DARK_GREEN + "Magebeam has been enabled");


                    } else {
                        beamMap.put(player, false);
                        sender.sendMessage(ChatColor.DARK_GREEN + "Magebeam has been disabled");
                    }

                } else {
                    beamMap.put(player, true);
                    sender.sendMessage(ChatColor.DARK_GREEN + "Magebeam has been enabled");
                }


            } else {
                sender.sendMessage(ChatColor.DARK_GREEN + "This command can only be issued by a player");

            }
        } else{
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', hypixelweaponsplugin.getInternalConfig().noPermMessage));
            return false;

        }



        return true;
    }
}
