package fr.vx.rpg.classes.blocks;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Item.Item;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

public class Block implements Listener {

    private final Item item;

    public Block(Item item) {

        this.item = item;
        RPG.plugin.getServer().getPluginManager().registerEvents(this, RPG.plugin);

    }

    public Item getItem() {

        return item;
    }

    public void applyBlock(org.bukkit.block.Block block) {

        block.setMetadata("custom_id", new FixedMetadataValue(RPG.plugin, item.getIdentifier()));

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        org.bukkit.block.Block block = event.getBlock();
        ItemStack item = event.getItemInHand();
        if (Item.isSame(this.item, item)) {
            applyBlock(block);
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        org.bukkit.block.Block block = event.getBlock();

        if (block.hasMetadata("custom_id") && block.getMetadata("custom_id").get(0).asString().equalsIgnoreCase(this.getItem().getIdentifier())) {
            event.setDropItems(false);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), this.getItem().getItemStack());
        }

    }

}
