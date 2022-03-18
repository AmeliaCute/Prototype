package fr.vx.rpg.classes.blocks;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Item.Item;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Collection;
import java.util.List;


public class ModdedBlock implements Listener
{
    private final Item item;
    public ModdedBlock(Item item)
    {
        this.item = new Item(Material.CARROT_ON_A_STICK, item.getName(), item.getRarity(), item.getPrice());
        RPG.plugin.getServer().getPluginManager().registerEvents(this, RPG.plugin);
    }

    public static void spawnArmorStand(ItemStack itemStack, Location location)
    {
        ArmorStand as = (ArmorStand) location.getWorld().spawn(location, ArmorStand.class);

        as.setGravity(false);
        as.setCanPickupItems(false);
        as.setVisible(false);
        LivingEntity entity = (LivingEntity) as;
        entity.getEquipment().setHelmet(itemStack);
    }

    public Item getItem()
    {
        return this.item;
    }

    public void applyBlock(org.bukkit.block.Block block) {

        block.setMetadata("custom_id", new FixedMetadataValue(RPG.plugin, item.getIdentifier()));
    }

    public void resetBlock(org.bukkit.block.Block block) {

        block.setMetadata("custom_id", new FixedMetadataValue(RPG.plugin, null));
    }

    @EventHandler
    public void onModdedBlockPlaced(PlayerInteractEvent event)
    {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
        {
            ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
            if (Item.isSame(this.item, item)) {
                org.bukkit.block.Block block = event.getClickedBlock().getLocation().add(0, 1, 0).getBlock();

                block.setType(Material.MUSHROOM_STEM);
                BlockData blockData = block.getBlockData();
                MultipleFacing mf = (MultipleFacing) blockData;
                mf.setFace(BlockFace.NORTH, false);
                mf.setFace(BlockFace.SOUTH, false);
                mf.setFace(BlockFace.EAST, false);
                mf.setFace(BlockFace.WEST, false);
                mf.setFace(BlockFace.UP, false);
                mf.setFace(BlockFace.DOWN, true);
                block.setBlockData(mf);

                applyBlock(block);
                spawnArmorStand(item, block.getLocation().add(0.5, 0, 0.5));
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BASALT_PLACE, 1.5F, 0.5F);
                item.setAmount(item.getAmount() -1);
                event.getPlayer().updateInventory();
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        org.bukkit.block.Block block = event.getBlock();

        if (block.hasMetadata("custom_id") && block.getMetadata("custom_id").get(0).asString().equals(this.getItem().getIdentifier())) {
            Location BlockLocation = event.getBlock().getLocation();
            World world = BlockLocation.getWorld();
            List<Entity> nearbyEntities = (List<Entity>) BlockLocation.getWorld().getNearbyEntities(BlockLocation, 1, 1, 1);

            for(Entity e : world.getEntities()){
                if(nearbyEntities.contains(e)){
                    if(e.getType().equals(EntityType.ARMOR_STAND))
                    {
                        e.remove();
                    }
                }
            }

            resetBlock(block);
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BASALT_BREAK, 1.5F, 0.5F);
            event.setDropItems(false);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), this.getItem().getItemStack());
        }

    }

}
