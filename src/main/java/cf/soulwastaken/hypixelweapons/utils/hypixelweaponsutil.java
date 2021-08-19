package cf.soulwastaken.hypixelweapons.utils;

import cf.soulwastaken.hypixelweapons.hypixelweaponsplugin;
import org.bukkit.NamespacedKey;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class hypixelweaponsutil {
    public static final NamespacedKey HYPERION_KEY = new NamespacedKey(hypixelweaponsplugin.getInstance(),"Hyperion");
    public static final String HYPERION_USE_PERM = "hypixelweapons.hyperion.use";
    public static final String HYPERION_GIVE_SELF_PERM = "hypixelweapons.hyperion.give.self";
    public static final String HYPERION_GIVE_OTHERS_PERM = "hypixelweapons. hyperion.give.others";
    public static final String HYPIXEL_WEAPONS_RELOAD_PERM = "hypixelweapons.reload";
    private static final ArrayList<Permission> perms = new ArrayList<>();
    private static final ArrayList lore = new ArrayList<String>(Arrays.asList("§7Gear Score: §d1102 §8(3189)",
            "§7Damage: §c+317 §e(+30) §8(+1,008.06)",
            "§7Strength: §c+382 §e(+30) §6[+5] §9(Withered +197) §8(+1,214.76)",
            "§7Crit Damage: §c+60% §8(+190.8%)",
            " ",
            "§7Intelligence: §a+404 §8(+1,284.72)",
            "§7Ferocity: §a+35 §8(+52.5)",
            " ",
            "§d§lChimera V, §9Critical VI",
            "§9Smite VII, First Strike IV, Giant Killer VI",
            "§9Execute V, Lethality VI, Ender Slayer VI",
            "§9Cubism V, Impaling III, Vampirism VI",
            "§9Life Steal IV, Looting IV, Luck VI",
            "§9Scavenger IV, Experience IV, Thunderlord VI",
            "§9Telekinesis I, Vicious V",
            " ",
            "§b⬧ Music Rune",
            "§8Requires level 15",
            " ",
            "§7Deals +§c50% §7damage to",
            "§7Withers. Grants §c+1 ❁ Damage",
            "§7and §a+2 §b✎ Intelligence",
            "§7per §cCatacombs §7level.",
            " ",
            "§7Your Catacombs Level: §c27",
            " ",
            "§aScroll Abilities:",
            "§6Item Ability: Wither Impact §e§lRIGHT CLICK",
            "§7Teleport §a10 Blocks §7ahead of",
            "§7you. Then implode dealing",
            "§c138,208.2 §7damage to nearby",
            "§7enemies. Also applies the wither",
            "§7shield scroll ability reducing",
            "§7damage taken and granting an",
            "§7absorption shield for §e5",
            "§7seconds.",
            "§8Mana Cost: §3300",
            " ",
            "§9Withered Bonus",
            "§7Grants §a+1 §c❁ Strength §7per",
            "§cCatacombs §7level.",
            " ",
            "§d§l§kA§a §d§lMYTHIC DUNGEON SWORD §kA"));


    private hypixelweaponsutil() {}
    public static ItemStack createHyperion(){
        ItemStack hyperion = new ItemStack(Material.IRON_SWORD);
        ItemMeta meta = hyperion.getItemMeta();
        String itemname = hypixelweaponsplugin.getInternalConfig().itemname;
        meta.setDisplayName(itemname);
        meta.setLore(lore);
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.DAMAGE_ALL,5,true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        hyperion.setItemMeta(meta);
        return hyperion;
    }
    public static boolean isHyperion(ItemStack stack){
        String itemname = hypixelweaponsplugin.getInternalConfig().itemname;
        if(stack == null || stack.getType() != Material.IRON_SWORD || !stack.hasItemMeta() || !stack.getItemMeta().hasDisplayName())return false;
        else if(stack.getItemMeta().getDisplayName().equals(itemname))return true;
        else return false;
    }
    public static boolean registerHyperionRecipe(){
        ItemStack hyperion = hypixelweaponsutil.createHyperion();
        ShapedRecipe hyperionrecipe = new ShapedRecipe(HYPERION_KEY,hyperion);
        hyperionrecipe.shape(" N ",
                " N ",
                " S "
        );
        hyperionrecipe.setIngredient('N',Material.NETHER_STAR);
        hyperionrecipe.setIngredient('S',Material.STICK);
        boolean success = Bukkit.addRecipe(hyperionrecipe);
        if(success)hypixelweaponsplugin.getInstance().getLogger().fine("Registered recipe: " + HYPERION_KEY.getNamespace() + ":"+HYPERION_KEY.getKey());
        else hypixelweaponsplugin.getInstance().getLogger().fine("Failed to register recipe: " + HYPERION_KEY.getNamespace() + ":"+HYPERION_KEY.getKey());


        return success;

    }
    public static boolean unregisterHyperionRecipe(){
        boolean success = Bukkit.removeRecipe(HYPERION_KEY);
        if(success)hypixelweaponsplugin.getInstance().getLogger().fine("Unregistered recipe: " + HYPERION_KEY.getNamespace() + ":"+HYPERION_KEY.getKey());
        else hypixelweaponsplugin.getInstance().getLogger().fine("Failed to unregister recipe: " + HYPERION_KEY.getNamespace() + ":"+HYPERION_KEY.getKey());


        return success;

    }
    public static void registerPermissions(){
        perms.add(new Permission(HYPERION_USE_PERM,"Allows player to use the HYPERION",PermissionDefault.TRUE));
        perms.add(new Permission(HYPERION_GIVE_SELF_PERM,"Allows player to give themselves the  HYPERION",PermissionDefault.OP));
        perms.add(new Permission(HYPERION_GIVE_OTHERS_PERM,"Allows player give others an HYPERION",PermissionDefault.OP));
        perms.add(new Permission(HYPIXEL_WEAPONS_RELOAD_PERM,"Allows players to reload the config",PermissionDefault.OP));
        for(Permission perm : perms){
            Bukkit.getPluginManager().addPermission(perm);

            hypixelweaponsplugin.getInstance().getLogger().fine("Registered Permission: " + perm.getName());
        }
    }
    public static void unregisterPermissions(){

        for(Permission perm : perms){
            Bukkit.getPluginManager().removePermission(perm);
            hypixelweaponsplugin.getInstance().getLogger().fine("Unregistered Permission: " + perm.getName());
        }


        perms.clear();
    }



}
