package fr.vx.rpg.classes.Spell;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.vx.rpg.RPG;

public abstract class Spell {
	
	public Spell() {}

	public abstract float getManaCost();
	
	public abstract String getName();
	
	public abstract String getDescription();
	
	protected abstract boolean action(Player player, int tick);
	
	public void send(Player player) {
		
		//TODO Implémenté une jauge de magie / mana dans la BDD et checké si l'utilisateur en a assez
		
		new BukkitRunnable() {
			
			int tick = 0;
			
			@Override
			public void run() {
				
				tick++;
				if (action(player, tick))
					cancel();
			}
		
		}.runTaskTimer(RPG.getPlugin(RPG.class), 0, 1);
		
	}

}
