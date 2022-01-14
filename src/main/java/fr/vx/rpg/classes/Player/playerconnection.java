package fr.vx.rpg.classes.Player;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Jobs.Job;
import fr.vx.rpg.classes.Npc.npc;
import fr.vx.rpg.classes.Quests.Quest;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class playerconnection implements Listener
{

    @EventHandler
    public void onConnection(PlayerJoinEvent event)
    {
        if(Quest.getQuest("welcome", event.getPlayer()) == 0)
        {
            Quest.finishedQuestMessage("Un nouveau monde!", event.getPlayer());
        }
        Job.CreateAccount(event.getPlayer());
        Quest.CreateAccount(event.getPlayer());

        if(npc.getNpcList() ==null || npc.getNpcList().isEmpty()) {return;}
        npc.addJoinPackets(event.getPlayer());
    }
}
