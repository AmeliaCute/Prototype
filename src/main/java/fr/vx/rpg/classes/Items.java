package fr.vx.rpg.classes;

import org.bukkit.Material;

public class Items {
	
	public static DroppedItem DIAMOND;
	public static CraftedItem TEST;
	
	public static void register() {
		
		DIAMOND = new DroppedItem(Material.DIAMOND, "§bDiamant", Rarity.VERY_RARE, 10000, Material.STONE, 100);
		
	}

}
