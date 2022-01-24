package fr.vx.rpg.classes.Npc.Events;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NpcRightClicked extends Event implements Cancellable
{

    private final Player player;
    private final EntityPlayer entityPlayer;

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private boolean isCancelled;

    public NpcRightClicked(Player player, EntityPlayer entityPlayer)
    {
        this.player = player;
        this.entityPlayer = entityPlayer;
    }

    public Player getPlayer() {return player;}

    public EntityPlayer getRightClicked() {return entityPlayer;}

    public String getName() { return entityPlayer.getName();}

    @Override
    public HandlerList getHandlers() {return HANDLER_LIST;}

    public static HandlerList getHandlerList() {return HANDLER_LIST;}

    @Override
    public boolean isCancelled() {return isCancelled;}

    @Override
    public void setCancelled(boolean cancel) {isCancelled = cancel;}

}
