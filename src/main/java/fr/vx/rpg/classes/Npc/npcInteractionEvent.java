package fr.vx.rpg.classes.Npc;

import fr.vx.rpg.classes.Jobs.Wizard;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class npcInteractionEvent implements Listener
{
    @EventHandler
    public void onNpcInteract(PlayerInteractEntityEvent event)
    {
        Player player = event.getPlayer();
        if(event.getRightClicked().getType() == EntityType.PLAYER)
        {
            switch(event.getRightClicked().getName())
            {
                case "§lDiana":
                    if(Wizard.getLvl(player) < 1) {player.sendMessage("§lDiana§r: Tu n'a pas le niveau necessaire.. :/"); return;}
                    player.sendMessage("§lDiana§r: Hey j'ai besoin de ton aide!");
                    break;
                default:

                    break;
            }
        }
    }
}
