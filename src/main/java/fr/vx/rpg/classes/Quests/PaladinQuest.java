package fr.vx.rpg.classes.Quests;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Jobs.Paladin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class PaladinQuest implements Listener
{
    public int QuestId;
    public String name;
    public int expEarned;
    public String mobToKill;

    public PaladinQuest(int QuestId, String name, int expEarned ,String mobToKill)
    {
        this.QuestId = QuestId;
        this.name = name;
        this.expEarned = expEarned;
        this.mobToKill = mobToKill;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    @EventHandler
    public void EntityKilled(EntityDeathEvent event)
    {
        if(event.getEntity().getKiller() instanceof Player)
        {
            Player player = event.getEntity().getKiller().getPlayer();
            if(!(Quest.getQuest(QuestId, player) == true))
            {
                player.sendMessage(ChatColor.DARK_PURPLE+"◆ "+ChatColor.LIGHT_PURPLE+"Quête terminer ! "+ChatColor.WHITE+"("+name+")");
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3.0F, 0.5F);
                Quest.setQuest(QuestId, player, 1);
                Paladin.addXp(player, expEarned);
            }
        }
    }
}
