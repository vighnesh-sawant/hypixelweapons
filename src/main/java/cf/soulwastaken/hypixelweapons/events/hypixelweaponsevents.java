package cf.soulwastaken.hypixelweapons.events;

import cf.soulwastaken.hypixelweapons.hypixelweaponsplugin;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import cf.soulwastaken.hypixelweapons.utils.hypixelweaponsutil;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class hypixelweaponsevents implements Listener {
    private final List<String> teleportList = new CopyOnWriteArrayList<>();
    private final List<String> abilityDelay = new CopyOnWriteArrayList<>();
    private final List<String> witherShieldDelay = new CopyOnWriteArrayList<>();


    @EventHandler(ignoreCancelled = false, priority = EventPriority.HIGH)
    public void onInteract(final  PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = player.getInventory().getItemInMainHand();
        if(hypixelweaponsutil.isHyperion(itemStack)  && (event.getAction()  == Action.RIGHT_CLICK_AIR
                || event.getAction()  == Action.RIGHT_CLICK_BLOCK ) ){
            if (!abilityDelay.contains(player.getName())
                    && !teleportList.contains(player.getName())) {
                abilityDelay.add(player.getName());
                teleportList.add(player.getName());
                Bukkit.getScheduler().scheduleSyncDelayedTask(hypixelweaponsplugin.getInstance(), () -> abilityDelay.remove(player.getName()),
                       hypixelweaponsplugin.getInternalConfig().abilitydelay);
                final HashSet hashSet = new HashSet<Material>();
                hashSet.add(Material.AIR);
                hashSet.add(Material.CAVE_AIR);
                hashSet.add(Material.VOID_AIR);
                final Block block = player.getTargetBlock((Set<Material>) hashSet, hypixelweaponsplugin.getInternalConfig().teleportwide);

                final Location playerLocation = player.getLocation();
                final Location teleportLocation = new Location(block.getWorld(), block.getX(),
                        block.getY(), block.getZ(), playerLocation.getYaw(), playerLocation.getPitch());

                if (teleportLocation.getBlock().getType() != Material.AIR && teleportLocation.getBlock().getType() != Material.CAVE_AIR && teleportLocation.getBlock().getType() != Material.VOID_AIR) {
                    teleportLocation.setY(teleportLocation.getY() + 1);
                }
                if (new Location(teleportLocation.getWorld(), teleportLocation.getX(), teleportLocation.getY() + 1,
                        teleportLocation.getZ()).getBlock().getType() == Material.AIR
                        && player.getLocation().add(player.getLocation().getDirection()).getBlock().getType() == Material.AIR ) {
                    teleportLocation.setX(teleportLocation.getX() - 0.5D);
                    teleportLocation.setZ(teleportLocation.getZ() - 0.5D);
                } else {
                    teleportLocation.setX(teleportLocation.getX() + 0.5D);
                    teleportLocation.setZ(teleportLocation.getZ() + 0.5D);
                }
                Vector direction  = player.getLocation().getDirection().multiply(2);

                Location twoBlockAway = player.getLocation().add(direction);

                if(twoBlockAway.getBlock().getType() != Material.AIR && twoBlockAway.getBlock().getType() != Material.CAVE_AIR && twoBlockAway.getBlock().getType() != Material.VOID_AIR && twoBlockAway.getBlock().getType() != Material.WATER) {
                    player.sendMessage(hypixelweaponsplugin.getInternalConfig().blocksInWayMessage);
                } else {
                    player.teleport(teleportLocation);
                }


                if (!witherShieldDelay.contains(player.getName())) {

                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 2));

                    witherShieldDelay.add(player.getName());
                    Bukkit.getScheduler().scheduleSyncDelayedTask(hypixelweaponsplugin.getInstance(), () -> witherShieldDelay.remove(player.getName()),
                            hypixelweaponsplugin.getInternalConfig().withershielddelay);
                }

                if (hypixelweaponsplugin.getInternalConfig().playsound) {
                    player.playSound(teleportLocation, Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1, 0.7F);

                    player.playSound(teleportLocation, Sound.ENTITY_GENERIC_EXPLODE, 1, 1F);
                }

                if (hypixelweaponsplugin.getInternalConfig().playeffect) {
                    World w = playerLocation.getWorld();


                    w.spawnParticle(Particle.EXPLOSION_HUGE,(float) teleportLocation.getX(),
                            (float) teleportLocation.getY(), (float) teleportLocation.getZ(),(int)10);
                }
                final double radius = hypixelweaponsplugin.getInternalConfig().implosionradius;
                final Random random = new Random();
                final int lowerRange = hypixelweaponsplugin.getInternalConfig().implosiondamagelower;
                final int higherRange = hypixelweaponsplugin.getInternalConfig().implosiondamagehigher;
                final int damage = random.nextInt(higherRange - lowerRange) + lowerRange;
                int enemies = 0;
                for (final Entity entity : player.getNearbyEntities(radius, radius, radius)) {
                    if (entity instanceof LivingEntity) {
                        final LivingEntity livingEntity = (LivingEntity) entity;
                        if (livingEntity.equals(player)) continue;

                        livingEntity.damage(damage);
                        enemies++;
                    }
                }

                final String damageString = String.format("%,d", enemies * damage);

                player.sendMessage(String.format(hypixelweaponsplugin.getInternalConfig().abilityMessage, enemies, damageString));

                teleportList.remove(player.getName());


            }

        }


    }

}
