package fr.vx.rpg.classes.Jobs;

import fr.vx.rpg.RPG;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHurt(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        LivingEntity livingEntity = (LivingEntity) event.getEntity();
        if (event.getDamager() instanceof Player)
        {
            if(((Player) event.getDamager()).getPlayer().getInventory().getItemInMainHand() == itemStack)
            {
                Player player = (Player) event.getDamager();
                if(jobType.equals(JobType.PALADIN))
                {
                    if(Paladin.getLvl(player) < lvl)
                    {
                        event.setCancelled(true);
                        livingEntity.setHealth(livingEntity.getMaxHealth());
                        player.sendMessage(ChatColor.RED + "◆ " + ChatColor.LIGHT_PURPLE + "Vous n'avez pas le niveau pour utiliser cette objet.");
                        return;
                    }
                }
                if(jobType.equals(JobType.BUCHERON))
                {
                    if(Paladin.getLvl(player) < lvl)
                    {
                        event.setCancelled(true);
                        livingEntity.setHealth(livingEntity.getMaxHealth());
                        player.sendMessage(ChatColor.RED + "◆ " + ChatColor.LIGHT_PURPLE + "Vous n'avez pas le niveau pour utiliser cette objet.");
                        return;
                    }
                }
                if(jobType.equals(JobType.MAGE))
                {
                    if(Wizard.getLvl(player) < lvl)
                    {
                        event.setCancelled(true);
                        livingEntity.setHealth(livingEntity.getMaxHealth());
                        player.sendMessage(ChatColor.RED + "◆ " + ChatColor.LIGHT_PURPLE + "Vous n'avez pas le niveau pour utiliser cette objet.");
                        return;
                    }
                }
                if(jobType.equals(JobType.FORGERON))
                {
                    if(Paladin.getLvl(player) < lvl)
                    {
                        event.setCancelled(true);
                        livingEntity.setHealth(livingEntity.getMaxHealth());
                        player.sendMessage(ChatColor.RED + "◆ " + ChatColor.LIGHT_PURPLE + "Vous n'avez pas le niveau pour utiliser cette objet.");
                        return;
                    }
                }
            }else return;
        }
    }

    public enum JobType {FORGERON(), MAGE(), PALADIN(),BUCHERON, MINEUR, ;private JobType() {}}
}
