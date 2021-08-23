package cf.soulwastaken.hypixelweapons.events;

import cf.soulwastaken.hypixelweapons.hypixelweaponsplugin;
import cf.soulwastaken.hypixelweapons.utils.hypixelweaponsutil;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;
import org.bukkit.util.Vector;
import org.bukkit.inventory.EntityEquipment; //trying to not use depre methods but cant figure it out so...

public class bonemerang implements Listener {
    @EventHandler
    public void onInteract(final PlayerInteractEvent event){
        Player player = event.getPlayer();
        final ItemStack itemStack = player.getInventory().getItemInMainHand();
        final int slot = player.getInventory().getHeldItemSlot();
        ItemStack bonemerang = hypixelweaponsutil.createBonemerang();
        ItemStack bonemerangghast = hypixelweaponsutil.createGhasttear();
        final int damageconfig = hypixelweaponsplugin.getInternalConfig().bdamage;
        final Location location1 = player.getLocation();
        location1.add(0,1,0);
        if(hypixelweaponsutil.isBonemerang(itemStack) ){
            player.playSound(player.getLocation(),Sound.ENTITY_ENDER_PEARL_THROW,1f,1f);
            ArmorStand as = (ArmorStand) player.getWorld().spawnEntity(location1, EntityType.ARMOR_STAND);
            Location destination = player.getLocation().add(player.getLocation().getDirection().multiply(5));

            as.setArms(true);
            as.setGravity(false);
            as.setVisible(false);
            as.setMarker(true);
            as.setItemInHand(new ItemStack(Material.BONE));
            as.setRightArmPose(new EulerAngle(Math.toRadians(0), Math.toRadians(120), Math.toRadians(0)));
            player.getInventory().removeItem(itemStack);
            player.getInventory().setItem(slot , bonemerangghast);
            Vector vector = destination.subtract(player.getLocation()).toVector();
            new BukkitRunnable(){

                int distance = 40;
                int i = 0;

                public void run(){

                    EulerAngle rot = as.getRightArmPose();
                    EulerAngle rotnew = rot.add(0, 20, 0);
                    as.setRightArmPose(rotnew);

                    if(i >= distance){
                        as.teleport(as.getLocation().subtract(vector.normalize()));
                        if(i >= distance * 2){
                            as.remove();
                            if(player.getInventory().firstEmpty() != -1){
                                player.getInventory().setItem(slot , itemStack);
                            }
                            else{
                                player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                            }
                            cancel();
                        }
                    }
                    else{
                        as.teleport(as.getLocation().add(vector.normalize()));
                    }

                    i++;

                    for(Entity entity : as.getLocation().getChunk().getEntities()){
                        if(!as.isDead()) {
                            if (as.getLocation().distanceSquared(entity.getLocation()) < 1) {
                                if (entity != player) {
                                    if(entity instanceof LivingEntity) {
                                        LivingEntity livingentity = (LivingEntity) entity;
                                        livingentity.damage(damageconfig, player);
                                    }
                                }
                            }
                        }
                    }
                    if(as.getTargetBlockExact(1) != null && !as.getTargetBlockExact(1).isPassable()){
                        if(!as.isDead()){
                            as.remove();
                            if(player.getInventory().firstEmpty() != -1){
                                player.getInventory().setItem(slot , itemStack);
                            }
                            else{
                                player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
                            }
                            cancel();
                        }
                    }

                }
            }.runTaskTimer(hypixelweaponsplugin.getInstance(), 1L, 1L);

            event.setCancelled(true);


        }

    }
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getCurrentItem() != null) {
            if(hypixelweaponsutil.isGhasttear(event.getCurrentItem())){
                event.setCancelled(true);
                return;

            }
        }

    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (e.getItemDrop() != null) {
            if(hypixelweaponsutil.isGhasttear(e.getItemDrop().getItemStack())){
                e.setCancelled(true);

            }


        }


    }
}
