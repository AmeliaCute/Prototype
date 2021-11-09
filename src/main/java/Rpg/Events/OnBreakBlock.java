package Rpg.Events;

import Rpg.Materials;
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
    public void onBreakBlock(BlockBreakEvent e)
    {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        e.setDropItems(false);

        if(block.getType().equals(Material.PRISMARINE)) { Materials.Drop(2, block, player, false); Materials.DropExp(2, player); }
        if(block.getType().equals(Material.PURPLE_STAINED_GLASS)) { Materials.Drop(4, block, player, false); Materials.DropExp(4, player);}
    }

}
