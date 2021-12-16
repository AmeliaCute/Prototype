package fr.vx.rpg.classes.Player;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Jobs.Job;
import fr.vx.rpg.classes.Quests.Quest;
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
    }
}
