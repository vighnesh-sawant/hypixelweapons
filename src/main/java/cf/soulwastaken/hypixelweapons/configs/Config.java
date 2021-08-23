package cf.soulwastaken.hypixelweapons.configs;

import cf.soulwastaken.hypixelweapons.hypixelweaponsplugin;
import org.bukkit.configuration.file.FileConfiguration;


public class Config {


    public static final String NO_PERMISSION_MESSAGE = "locale.no_permission_message";
    public static final String BLOCKS_IN_WAY_MESSAGE = "locale.blocks_in_way_message";
    public static final String ABILITY_MESSAGE = "locale.ability_message";
    public static final String TELEPORT_WIDE = "hyperion.teleport_wide";
    public static final String IMPLOSION_RADIUS = "hyperion.implosion_radius";
    public static final String IMPLOSION_DAMAGE_LOWER_RANGE = "hyperion.implosion_damage_lower_range";
    public static final String IMPLOSION_DAMAGE_HIGHER_RANGE = "hyperion.implosion_damage_higher_range";
    public static final String ABILITY_DELAY = "hyperion.ability_delay";
    public static final String WITHER_SHIELD_DELAY = "hyperion.wither_shield_delay";
    public static final String PLAY_SOUND = "hyperion.play_sound";
    public static final String PLAY_EFFECT = "hyperion.play_effect";
    public static final String ITEM_NAME = "hyperion.item_name";
    public static final String B_ITEM_NAME = "bonemerang.item_name";
    public static final String B_DAMAGE = "bonemerang.damage";
    public String noPermMessage, blocksInWayMessage, abilityMessage, itemname , bitemname;
    public int teleportwide, implosionradius, implosiondamagelower, implosiondamagehigher, abilitydelay, withershielddelay , bdamage;
    public boolean playsound, playeffect;


    public Config() {
        setDefaults();
        loadConfig();
    }

    public void loadConfig() {
        FileConfiguration config = hypixelweaponsplugin.getInstance().getConfig();
        noPermMessage = config.getString(NO_PERMISSION_MESSAGE, "§cYou don't have permission to execute this command!");
        blocksInWayMessage = config.getString(BLOCKS_IN_WAY_MESSAGE, "§c There are blocks in the way.");
        abilityMessage = config.getString(ABILITY_MESSAGE, "§7Your Implosion hit §c%1$s §7enemies for §c%2$s.00 §7damage.");
        teleportwide = config.getInt(TELEPORT_WIDE, 10);
        implosionradius = config.getInt(IMPLOSION_RADIUS, 6);
        implosiondamagelower = config.getInt(IMPLOSION_DAMAGE_LOWER_RANGE, 10);
        implosiondamagehigher = config.getInt(IMPLOSION_DAMAGE_HIGHER_RANGE, 15);
        abilitydelay = config.getInt(ABILITY_DELAY, 2);
        withershielddelay = config.getInt(WITHER_SHIELD_DELAY, 100);
        playsound = config.getBoolean(PLAY_SOUND, true);
        playeffect = config.getBoolean(PLAY_EFFECT, true);
        itemname = config.getString(ITEM_NAME, "§dWithered Hyperion §c✪✪✪✪§6✪");
        bitemname = config.getString(B_ITEM_NAME, "§dSpiritual Bonemerang §c✪✪✪✪§6✪");
        bdamage = config.getInt(B_DAMAGE, 15);
    }

    public void setDefaults() {
        FileConfiguration config = hypixelweaponsplugin.getInstance().getConfig();
        config.addDefault(NO_PERMISSION_MESSAGE, "§cYou don't have permission to execute this command!");
        config.addDefault(BLOCKS_IN_WAY_MESSAGE, "§c There are blocks in the way.");
        config.addDefault(ABILITY_MESSAGE, "§7Your Implosion hit §c%1$s §7enemies for §c%2$s.00 §7damage.");
        config.addDefault(TELEPORT_WIDE, 10);
        config.addDefault(IMPLOSION_RADIUS, 6);
        config.addDefault(IMPLOSION_DAMAGE_LOWER_RANGE, 10);
        config.addDefault(IMPLOSION_DAMAGE_HIGHER_RANGE, 15);
        config.addDefault(ABILITY_DELAY, 2);
        config.addDefault(WITHER_SHIELD_DELAY, 100);
        config.addDefault(PLAY_SOUND, true);
        config.addDefault(PLAY_EFFECT, true);
        config.addDefault(ITEM_NAME, "§dWithered Hyperion §c✪✪✪✪§6✪");
        config.addDefault(B_ITEM_NAME, "§dSpiritual Bonemerang §c✪✪✪✪§6✪");
        config.addDefault(B_DAMAGE, 15);

        config.options().copyDefaults(true);
        hypixelweaponsplugin.getInstance().saveConfig();
    }

    public void saveConfig() {
        FileConfiguration config = hypixelweaponsplugin.getInstance().getConfig();
        config.set(NO_PERMISSION_MESSAGE, noPermMessage);
        config.set(BLOCKS_IN_WAY_MESSAGE, blocksInWayMessage);
        config.set(ABILITY_MESSAGE, abilityMessage);
        config.set(TELEPORT_WIDE, teleportwide);
        config.set(IMPLOSION_RADIUS, implosionradius);
        config.set(IMPLOSION_DAMAGE_LOWER_RANGE, implosiondamagelower);
        config.set(IMPLOSION_DAMAGE_HIGHER_RANGE, implosiondamagehigher);
        config.set(ABILITY_DELAY, abilitydelay);
        config.set(WITHER_SHIELD_DELAY, withershielddelay);
        config.set(PLAY_SOUND, playsound);
        config.set(PLAY_EFFECT, playeffect);
        config.set(ITEM_NAME, itemname);
        config.set(B_ITEM_NAME, bitemname);
        config.set(B_DAMAGE, bdamage);
        hypixelweaponsplugin.getInstance().saveConfig();

    }

    public void reloadConfig() {
        loadConfig();
    }


}
