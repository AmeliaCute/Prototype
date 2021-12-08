package fr.vx.rpg.classes.mobs;

import fr.vx.rpg.RPG;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class MobDrop implements Listener
{
    private String mobName;
    private ItemStack drop;
    private int dropChance;

    public MobDrop(String mobName, ItemStack drop,int dropChance)
    {
        this.mobName = mobName;
        this.drop = drop;
        this.dropChance = dropChance;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e)
    {
        String name = e.getEntity().getName();
        e.getDrops().clear();

        if(name.equals(this.mobName))
        {
            Random random = new Random();
            int chance = random.nextInt(100);

            if(chance < this.dropChance)
            {
                e.getDrops().add(this.drop);
            }
        }
    }
}
