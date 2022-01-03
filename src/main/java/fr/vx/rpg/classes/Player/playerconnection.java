package fr.vx.rpg.classes.Player;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Jobs.Job;
import fr.vx.rpg.classes.Quests.Quest;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerconnection implements Listener
{
    private RPG rpg;
    public playerconnection(RPG rpg) { this.rpg = rpg; }

    @EventHandler
    public void onConnection(PlayerJoinEvent event)
    {
        Job.CreateAccount(event.getPlayer());
        Quest.CreateAccount(event.getPlayer());
        if(!Quest.getQuest(1, event.getPlayer()))
        {
            event.getPlayer().sendMessage(ChatColor.DARK_PURPLE+"◆ "+ChatColor.LIGHT_PURPLE+"Quête terminer ! "+ChatColor.WHITE+"(Le debut d'une nouvelle vie..)");
            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3.0F, 0.5F);
            Quest.setQuest(1, event.getPlayer(), 1);
        }
    }
}
