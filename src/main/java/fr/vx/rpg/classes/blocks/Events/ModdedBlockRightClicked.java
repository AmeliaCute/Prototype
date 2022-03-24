package fr.vx.rpg.classes.blocks.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerInteractEvent;

public class ModdedBlockRightClicked extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Player player;
    private final Block block;
    private boolean isCancelled;

    public ModdedBlockRightClicked(PlayerInteractEvent event)
    {
        this.block=event.getClickedBlock();
        this.player=event.getPlayer();
    }

    public static HandlerList getHandlerList() {return HANDLER_LIST;}

    @Override
    public HandlerList getHandlers() {return HANDLER_LIST;}

    @Override
    public boolean isCancelled() {return isCancelled;}

    @Override
    public void setCancelled(boolean cancel) {isCancelled = cancel;}

    public Player getPlayer(){return player;}
    public Block getBlock(){return block;}
}
