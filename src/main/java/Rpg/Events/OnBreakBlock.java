package Rpg.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBreakBlock implements Listener
{
    @SuppressWarnings("unlikely-arg-type")
    @EventHandler
    public static void onBreakBlock(BlockBreakEvent e)
    {
        Player player = e.getPlayer();
        Block block = e.getBlock();

        if(block.equals(Material.PRISMARINE))
        {
            block.getDrops().clear();

        }
    }

}
