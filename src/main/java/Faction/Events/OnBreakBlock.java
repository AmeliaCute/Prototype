package Faction.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBreakBlock
{

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
