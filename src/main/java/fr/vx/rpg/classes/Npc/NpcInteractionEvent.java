package fr.vx.rpg.classes.Npc;

import fr.vx.rpg.classes.Npc.Events.NpcRightClicked;
import fr.vx.rpg.classes.Npc.impl.interactions.DianaInteraction;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NpcInteractionEvent implements Listener
{
    @EventHandler
    public void npcInter(NpcRightClicked event)
    {
    	switch (event.getName())
        {
            case "Diana":
                new DianaInteraction().interact(event.getPlayer());
                break;
            default:
                event.getPlayer().sendMessage(event.getName()+": Salut !");
        }
    }
}
