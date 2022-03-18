package fr.vx.rpg.classes.blocks;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Item.Item;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class Block implements Listener {

    private final Item item;
    private boolean[] multipleFacing;
    private boolean isTexturedItem = false;

    public Block(Item item) {
        this.item = item;
        RPG.plugin.getServer().getPluginManager().registerEvents(this, RPG.plugin);
    }

    public Block(Item item, boolean[] multipleFacing) {
        this.isTexturedItem = true;
        this.multipleFacing = multipleFacing;
        this.item = item;
        RPG.plugin.getServer().getPluginManager().registerEvents(this, RPG.plugin);
    }

    public Item getItem() {

        return item;
    }

    public void applyBlock(org.bukkit.block.Block block) {

        block.setMetadata("custom_id", new FixedMetadataValue(RPG.plugin, item.getIdentifier()));
    }

    public void applyMultipleFacing(org.bukkit.block.Block block, boolean[] multipleFacing)
    {
        block.setType(Material.MUSHROOM_STEM);
        BlockData blockData = block.getBlockData();
        MultipleFacing mf = (MultipleFacing) blockData;
        mf.setFace(BlockFace.NORTH, multipleFacing[0]);
        mf.setFace(BlockFace.SOUTH, multipleFacing[1]);
        mf.setFace(BlockFace.EAST, multipleFacing[2]);
        mf.setFace(BlockFace.WEST, multipleFacing[3]);
        mf.setFace(BlockFace.UP, multipleFacing[4]);
        mf.setFace(BlockFace.DOWN, multipleFacing[5]);
        block.setBlockData(mf);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        org.bukkit.block.Block block = event.getBlock();
        ItemStack item = event.getItemInHand();
        if (Item.isSame(this.item, item)) {
            applyBlock(block);
            if(this.isTexturedItem)
            {
                applyMultipleFacing(block, this.multipleFacing);
            }
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        org.bukkit.block.Block block = event.getBlock();

        if (block.hasMetadata("custom_id") && block.getMetadata("custom_id").get(0).asString().equals(this.getItem().getIdentifier())) {
            event.setDropItems(false);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), this.getItem().getItemStack());
        }

    }

}
