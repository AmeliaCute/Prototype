package fr.vx.rpg.classes.Item;

import fr.vx.rpg.RPG;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class FarmingBlock implements Listener
{
    private Material block;
    private ItemStack drop;
    private int exp;
    private int respawnTimeInMinutes;

    /**
     *
     * @param block set a block need to be break
     * @param drop set the drop
     * @param exp set the exp
     * @param respawnTimeInMinutes set the respawn time of the @block
     */
    public FarmingBlock(Material block, ItemStack drop, int exp,int respawnTimeInMinutes)
    {
        this.block = block;
        this.drop = drop;
        this.exp = exp;
        this.respawnTimeInMinutes = respawnTimeInMinutes;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    @EventHandler
    public void onDestructBlock(BlockBreakEvent event)
    {
        if(event.getBlock().getType() == this.block)
        {
            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            event.setDropItems(false);
            event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), this.drop);
            event.setExpToDrop(this.exp);
            new BukkitRunnable(){
                @Override
                public void run()
                {
                    event.getBlock().setType(block);
                }
            }.runTaskLater(RPG.getPlugin(RPG.class),1200*respawnTimeInMinutes);
        }
    }
}
