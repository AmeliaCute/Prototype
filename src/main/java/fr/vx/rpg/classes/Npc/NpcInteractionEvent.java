package fr.vx.rpg.classes.Npc;

import fr.vx.rpg.classes.Npc.Events.NpcRightClicked;
import fr.vx.rpg.classes.Npc.impl.interactions.DianaInteraction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NpcInteractionEvent implements Listener
{
    //DianaInteraction dianaInteraction = new DianaInteraction();

    @EventHandler
    public void npcInter(PlayerInteractEntityEvent event)
    {
        System.out.println("adasdasdasdasdad");
    }
}