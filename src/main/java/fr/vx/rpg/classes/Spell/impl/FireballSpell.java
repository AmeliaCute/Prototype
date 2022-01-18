package fr.vx.rpg.classes.Spell.impl;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.vx.rpg.classes.Spell.Spell;

public class FireballSpell extends Spell {

	@Override
	public float getManaCost() {
		return 15;
	}

	@Override
	public String getName() {
		return "FireBall";
	}

	@Override
	public String getDescription() {
		return "Lance une boules de feu";
	}

	@Override
	protected boolean action(Player player, int tick) {
		
		//TODO Peut être pas optimal, à voir
		player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREBALL);
		return true;
	}

	@Override
	public int getWizardLvlRequired() {
		return 3;
	}

}
