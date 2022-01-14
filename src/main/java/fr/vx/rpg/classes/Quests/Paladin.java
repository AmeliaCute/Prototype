package fr.vx.rpg.classes.Quests;

import fr.vx.rpg.RPG;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Paladin implements Listener
{
    private String questname;
    private String id;
    private String nameOfMob;
    private int JobsExpEarned;

    public Paladin(String id, String questname, String nameOfMob, int JobsExpEarned)
    {
        this.id = id;
        this.questname = questname;
        this.nameOfMob = nameOfMob;
        this.JobsExpEarned = JobsExpEarned;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    @EventHandler
    public void OnMobKill(EntityDeathEvent event)
    {
        if(event.getEntity().getKiller() instanceof Player)
        {
            if(event.getEntity().getName().equals(this.nameOfMob) && Quest.getQuest(id, ((Player) event.getEntity().getKiller()).getPlayer())==0)
            {
                Quest.finishQuest(id, ((Player) event.getEntity().getKiller()).getPlayer());
                Quest.finishedQuestMessage(questname, ((Player) event.getEntity().getKiller()).getPlayer());
                fr.vx.rpg.classes.Jobs.Paladin.addXp(((Player) event.getEntity().getKiller()).getPlayer(), JobsExpEarned);
            }
            else return;
        }
    }
}
