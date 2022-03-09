package fr.vx.rpg.classes.Spell.impl;

import java.util.ArrayList;

import fr.vx.rpg.classes.Spell.Spell;

public class Spells {
	
	public static ArrayList<Spell> SPELLS = new ArrayList<Spell>();
	
	public static void register() {
		
		SPELLS.add(new FireballSpell());
		
	}

}
