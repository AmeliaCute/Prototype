package fr.vx.rpg.classes.blocks;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.blocks.Events.ModdedBlockRightClicked;
import org.bukkit.*;
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
import org.bukkit.scheduler.BukkitScheduler;

import java.util.List;


public class ModdedBlock implements Listener
{
    private final Item item;
    private final boolean models;
    public ModdedBlock(Item item, boolean models)
    {
        this.item = new Item(Material.CARROT_ON_A_STICK, item.getName(), item.getRarity(), item.getPrice());
        this.models = models;
        RPG.plugin.getServer().getPluginManager().registerEvents(this, RPG.plugin);
        RPG.LOGGER.info("Registering textured block "+item.getName()+ " with id "+item.getIdentifier());
    }

    public void spawnArmorStand(ItemStack itemStack, Location location)
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

    public void set(org.bukkit.block.Block block, ModdedBlock moddedBlock)
    {
        Location location = block.getLocation();
        World world = block.getWorld();
        List<Entity> nearbyEntities = (List<Entity>) block.getWorld().getNearbyEntities(location, 1, 1, 1);

        for(Entity e : world.getEntities()){
            if(nearbyEntities.contains(e)){
                if(e.getType().equals(EntityType.ARMOR_STAND))
                {
                    e.remove();
                }
                else RPG.LOGGER.error("Exception:null Can't remove ArmorStand");
            }
        }

        if(moddedBlock.models)
        {
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
        }
        else
        {
            block.setType(Material.COBBLESTONE);
        }
        applyBlock(block);
        spawnArmorStand(moddedBlock.item.getItemStack(), block.getLocation().add(0.5, 0, 0.5));
    }

    public void place(Location location)
    {
        org.bukkit.block.Block block = location.getBlock();
        if(this.models)
        {
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
        }
        else
        {
            block.setType(Material.STONE);
        }

        applyBlock(block);
        spawnArmorStand(this.item.getItemStack(), block.getLocation().add(0.5, 0, 0.5));
    }

    @EventHandler
    public void onModdedBlockPlaced(PlayerInteractEvent event)
    {
    if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
    {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if (Item.isSame(this.item, item)) {
            org.bukkit.block.Block block = event.getClickedBlock().getLocation().add(0, 1, 0).getBlock();

            if(block.hasMetadata("custom_id") && block.getMetadata("custom_id").get(0).asString().equals(this.item.getIdentifier()))
            {
                if(event.getPlayer().isSneaking())
                {
                    if(this.models)
                    {
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
                    }
                    else
                    {
                        block.setType(Material.STONE);
                    }

                    applyBlock(block);
                    spawnArmorStand(item, block.getLocation().add(0.5, 0, 0.5));
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BASALT_PLACE, 1.5F, 0.5F);
                    item.setAmount(item.getAmount() -1);
                    event.getPlayer().updateInventory();
                }
                BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                scheduler.scheduleSyncDelayedTask(RPG.getPlugin(RPG.class), new Runnable() {
                    @Override
                    public void run() {
                        Bukkit.getPluginManager().callEvent(new ModdedBlockRightClicked(event));
                    }
                }, 20L);
            }
            else
            {
                if(this.models)
                {
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
                }
                else
                {
                    block.setType(Material.STONE);
                }

                applyBlock(block);
                spawnArmorStand(item, block.getLocation().add(0.5, 0, 0.5));
                event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BASALT_PLACE, 1.5F, 0.5F);
                item.setAmount(item.getAmount() -1);
                event.getPlayer().updateInventory();
            }
        }

    }
}

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        org.bukkit.block.Block block = event.getBlock();

        if (block.hasMetadata("custom_id") && block.getMetadata("custom_id").get(0).asString().equals(this.getItem().getIdentifier())) {
            Location BlockLocation = event.getBlock().getLocation();
            World world = BlockLocation.getWorld();
            List<Entity> nearbyEntities = (List<Entity>) BlockLocation.getWorld().getNearbyEntities(BlockLocation.add(0.5,0,0.5), 0.1, 0.1, 0.1);

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
