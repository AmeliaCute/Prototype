package fr.vx.rpg.classes.Jobs;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Item.Tools.Sword;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.inventory.ItemStack;

public class JobItem implements Listener
{
    private ItemStack itemStack;
    private JobType jobType;
    private int lvl;

    public JobItem(ItemStack itemStack, JobType jobType, int lvl)
    {
        this.itemStack = itemStack;
        this.jobType = jobType;
        this.lvl = lvl;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    @EventHandler
    public void onClick(EntityInteractEvent event)
    {
            if(event.getEntity() instanceof Player)
            {
                Player player = ((Player) event.getEntity()).getPlayer();
                if(player.getItemInHand() == itemStack)
                {
                    if(jobType == JobType.MAGE && Wizard.getLvl(player) < lvl)
                    {
                        event.setCancelled(true);player.sendMessage(ChatColor.RED+"◆ Vous n'avez pas le niveau necessaire pour utilisez cette objet.. (Niveau requie: "+lvl+")");
                    }
                    if(jobType == JobType.PALADIN && Paladin.getLvl(player) < lvl)
                    {
                        event.setCancelled(true);player.sendMessage(ChatColor.RED+"◆ Vous n'avez pas le niveau necessaire pour utilisez cette objet.. (Niveau requie: "+lvl+")");
                    }
                }
            }
    }

    public enum JobType {FORGERON(), MAGE(), PALADIN(), ;private JobType() {}}
}
