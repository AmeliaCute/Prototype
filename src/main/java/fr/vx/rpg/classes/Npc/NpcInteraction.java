package fr.vx.rpg.classes.Npc;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.History.HistoryBranch;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class NpcInteraction implements Listener
{
	
	public NpcInteraction() {}

	public abstract int HistoryLevel();
	public abstract HistoryBranch HistoryBranch();
	public abstract String NameOfNpc();


	//TODO faire une fonction dans npc.java pour savoir si un npc a été cliquer (genre comme un event)

}
