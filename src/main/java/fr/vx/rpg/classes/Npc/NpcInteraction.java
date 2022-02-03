package fr.vx.rpg.classes.Npc;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.House.History.HistoryBranch;
import fr.vx.rpg.classes.Npc.Events.NpcRightClicked;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public abstract class NpcInteraction implements Listener
{

	public abstract int MinimumHistoryLevel();
	public abstract boolean PlayerDontHaveHlMessage();
	public abstract HistoryBranch HistoryBranch();
	public abstract String NameOfNpc();
	protected abstract int action(Player player);

	public void interact(Player player)
	{
		action(player);
	}


}
