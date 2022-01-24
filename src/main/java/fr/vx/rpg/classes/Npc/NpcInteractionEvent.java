package fr.vx.rpg.classes.Npc;

import fr.vx.rpg.classes.Npc.Events.NpcRightClicked;
import fr.vx.rpg.classes.Npc.impl.interactions.DianaInteraction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcInteractionEvent implements Listener
{
    //DianaInteraction dianaInteraction = new DianaInteraction();

    @EventHandler
    public void npcInter(NpcRightClicked event)
    {
        System.out.println("adasdasdasdasdad");

    }
}
