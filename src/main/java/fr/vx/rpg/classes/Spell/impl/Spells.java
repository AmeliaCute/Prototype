package fr.vx.rpg.classes.Spell.impl;

import java.util.ArrayList;

import fr.vx.rpg.classes.Spell.Spell;

public class Spells {
	
	public static ArrayList<Spell> SPELLS = new ArrayList<Spell>();
	
	public static void register() {
		
		SPELLS.add(new FireballSpell());
	}

	public static Spell get(String name)
	{
        Spell spell = null;
		for(int i = 0; i < SPELLS.size(); i++)
		{
			String x;
			x = SPELLS.get(i).getIcon().color()+SPELLS.get(i).getIcon().icon()+" "+SPELLS.get(i).getName();
			if(name.equals(x))
			{
				spell = SPELLS.get(i);
			}
		}
		return spell;
	}

	public static String name(Spell spell)
	{
		return spell.getName();
	}
}
