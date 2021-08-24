package cf.soulwastaken.hypixelweapons.events;

import cf.soulwastaken.hypixelweapons.commands.magebeamcommand;
import cf.soulwastaken.hypixelweapons.hypixelweaponsplugin;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;

public class magebeam implements Listener {
    public static HashMap<Player, Boolean> beamMap =magebeamcommand.beamMap;
    @EventHandler(ignoreCancelled = false, priority = EventPriority.HIGH)
    public void onInteract(final PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.LEFT_CLICK_AIR
                || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (beamMap.containsKey(player)) {
                boolean beamboolean = beamMap.get(player);
                if (beamboolean) {
                    double range = (double) hypixelweaponsplugin.getInternalConfig().mrange;
                    List<Entity> targetList = player.getNearbyEntities(range, range, range);
                    BlockIterator bi = new BlockIterator(player, (int) range);
                    Entity target = null;
                    while (bi.hasNext()) {
                        Block b = bi.next();
                        int bx = b.getX();
                        int by = b.getY();
                        int bz = b.getZ();
                        if (b.getType().isSolid()) {
                            break;
                        } else {
                            for (Entity e : targetList) {

                                Location l = e.getLocation();

                                double ex = l.getX();

                                double ey = l.getY();

                                double ez = l.getZ();

                                if ((bx - .75 <= ex && ex <= bx + 1.75) && (bz - .75 <= ez && ez <= bz + 1.75) && (by - 1 <= ey && ey <= by + 2.5)) {

                                    target = e;


                                }
                            }


                        }
                    }

                    if (target == null) {

                        Location origin = player.getEyeLocation();
                        Vector direction = origin.getDirection();
                        direction.multiply(range);
                        Location destination = origin.clone().add(direction);
                        direction.normalize();
                        for (int i = 0; i < range; i++) {
                            Location loc = origin.add(direction);
                            loc.getWorld().spawnParticle(Particle.VILLAGER_ANGRY,loc,1);
                        }

                    } else {
                        if (target instanceof LivingEntity) {
                            double range1 = player.getLocation().distance(target.getLocation());
                            int damage = hypixelweaponsplugin.getInternalConfig().mdamage;
                            Location origin1 = player.getEyeLocation();
                            Vector direction1 = origin1.getDirection();
                            direction1.multiply(range1);
                            Location destination1 = origin1.clone().add(direction1);
                            direction1.normalize();
                            for (int i = 0; i < range1; i++) {
                                Location loc1 = origin1.add(direction1);
                                loc1.getWorld().spawnParticle(Particle.VILLAGER_ANGRY,loc1,1);
                            }
                            ((LivingEntity)target).damage(damage);



                        }

                    }

                }

            }


        }
    }
}
