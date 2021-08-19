package cf.soulwastaken.hypixelweapons;

import cf.soulwastaken.hypixelweapons.commands.hyperion;
import cf.soulwastaken.hypixelweapons.events.hypixelweaponsevents;
import cf.soulwastaken.hypixelweapons.utils.hypixelweaponsutil;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import cf.soulwastaken.hypixelweapons.configs.Config;

public class hypixelweaponsplugin extends JavaPlugin {
    private final Logger logger = Logger.getLogger("Hypixelweapons");
    private static hypixelweaponsplugin plugin;
    private static Config config;
    @Override
    public void onEnable(){
        plugin = this;
        config = new Config();
        Bukkit.getPluginManager().registerEvents(new hypixelweaponsevents(), this);
        hypixelweaponsutil.registerHyperionRecipe();
        hypixelweaponsutil.registerPermissions();
        this.getCommand("hyperion").setExecutor(new hyperion());
        logger.info("Hypixelweapons has successfully been enabled ");

    }
    @Override
    public void onDisable() {
        hypixelweaponsutil.unregisterHyperionRecipe();
        hypixelweaponsutil.unregisterPermissions();
        logger.info("Hypixelweapons has successfully shut down ");



    }
    public Logger getLogger(){
        return logger;
    }
    public static hypixelweaponsplugin getInstance(){
        return plugin;
    }
    public static Config getInternalConfig(){
        return config;
    }


}




