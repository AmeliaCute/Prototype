package fr.vx.rpg.classes.Player;

import fr.vx.rpg.classes.Jobs.Job;
import fr.vx.rpg.classes.Npc.npc;
import fr.vx.rpg.classes.Quests.Quest;
import fr.vx.rpg.utils.PacketsReader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerconnection implements Listener
{

    @EventHandler
    public void onConnection(PlayerJoinEvent event)
    {
        if(!event.getPlayer().hasPlayedBefore())
        {
            Quest.finishedQuestMessage("Un nouveau monde!", event.getPlayer());
        }
        Job.CreateAccount(event.getPlayer());
        Quest.CreateAccount(event.getPlayer());
        Coins.addPlayer(event.getPlayer());

        if(npc.getNpcList() !=null)
        {
            if(!npc.getNpcList().isEmpty())
            {
                npc.addJoinPackets(event.getPlayer());
                for(int i = 1; i < npc.getNpcList().size(); i++)
                {
                    npc.addNPCPackets(npc.getNpcList().get(i));
                }
            }
        }

        PacketsReader reader = new PacketsReader();
        reader.inject(event.getPlayer());
    }

    @EventHandler
    public void onDisconnect(PlayerQuitEvent event)
    {
        PacketsReader reader = new PacketsReader();
        reader.uninject(event.getPlayer());
    }
}
