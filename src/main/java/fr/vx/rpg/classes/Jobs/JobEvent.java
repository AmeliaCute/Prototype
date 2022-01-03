package fr.vx.rpg.classes.Jobs;

import fr.vx.rpg.classes.Item.impl.Items;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class JobEvent implements Listener //Bordel
{
    @EventHandler
    public void LumberJackBreakingWood(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location blockLocation = block.getLocation();
        int dropMultiplier = Lumberjack.getLvl(player);

        if(block.getType().equals(Material.OAK_LOG))
        {
            event.setCancelled(true);
            blockLocation.getWorld().dropItemNaturally(blockLocation, new ItemStack(Material.OAK_LOG, 1+dropMultiplier));
        }
        if(block.getType().equals(Material.SPRUCE_LOG))
        {
            event.setCancelled(true);
            blockLocation.getWorld().dropItemNaturally(blockLocation, new ItemStack(Material.SPRUCE_LOG, 1+dropMultiplier));
        }
        if(block.getType().equals(Material.ACACIA_LOG))
        {
            event.setCancelled(true);
            blockLocation.getWorld().dropItemNaturally(blockLocation, new ItemStack(Material.ACACIA_LOG, 1+dropMultiplier));
        }
        if(block.getType().equals(Material.BIRCH_LOG))
        {
            event.setCancelled(true);
            blockLocation.getWorld().dropItemNaturally(blockLocation, new ItemStack(Material.BIRCH_LOG, 1+dropMultiplier));
        }
        if(block.getType().equals(Material.DARK_OAK_LOG))
        {
            event.setCancelled(true);
            blockLocation.getWorld().dropItemNaturally(blockLocation, new ItemStack(Material.DARK_OAK_LOG, 1+dropMultiplier));
        }
    }

    @EventHandler
    public void MinerBreakingBlock(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location blockLocation = block.getLocation();

        switch (block.getType())
        {
            case IRON_ORE:
                event.setCancelled(true);
                block.setType(Material.COBBLESTONE);
                break;
            case PURPLE_STAINED_GLASS:
                event.setCancelled(true);
                block.setType(Material.COBBLESTONE);
                break;
            case PURPLE_WOOL:
                event.setCancelled(true);
                block.setType(Material.COBBLESTONE);
                break;
        }
    }

}
