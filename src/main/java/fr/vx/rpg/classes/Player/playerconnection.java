package fr.vx.rpg.classes.Player;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Jobs.Wizard;
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
        Wizard.CreateWizardAccount(event.getPlayer());
    }
}
