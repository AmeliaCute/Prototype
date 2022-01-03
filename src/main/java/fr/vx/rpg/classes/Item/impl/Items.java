package fr.vx.rpg.classes.Item.impl;

import fr.vx.rpg.classes.Item.*;
import fr.vx.rpg.classes.Item.Tools.*;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Items {

	public static ChestPlate test;

	public static void register()
	{
		List<Attributes> test_a = new ArrayList<>();
		test_a.add(new Attributes(Attributes.Type.ENT_SPEED, 0.3));
		test_a.add(new Attributes(Attributes.Type.ARMOR, 34));
		test = new ChestPlate(Material.IRON_CHESTPLATE, "test", Rarity.LEGENDARY, test_a);
	}

}
