package fr.vx.rpg.classes.Item.impl;

import fr.vx.rpg.classes.Item.CraftedItem;
import fr.vx.rpg.classes.Item.DroppedItem;
import fr.vx.rpg.classes.Item.Rarity;
import org.bukkit.Material;
import java.util.LinkedList;
import java.util.List;

public class Items {
	
	public static DroppedItem AMETHYSTE_SHARD;
	public static DroppedItem CONDENSED_AMETHYSTE;
	public static DroppedItem GOLD_NUGGET;

	public static CraftedItem MAGIC_UPGRADE;
	
	public static void register() {

		AMETHYSTE_SHARD = new DroppedItem(Material.PURPLE_DYE, "§fEclat d'Améthyste", Rarity.VERY_COMMON, 400, Material.PURPLE_STAINED_GLASS, 16);
		CONDENSED_AMETHYSTE = new DroppedItem(Material.PURPLE_STAINED_GLASS, "§bAméthyste condensée", Rarity.RARE, 3600, Material.PURPLE_WOOL, 64);
		GOLD_NUGGET = new DroppedItem(Material.GOLD_NUGGET, "§2Pepite d'or", Rarity.UNCOMMON, 100, Material.GOLD_ORE, 12);

		List MAGIC_UPGRADE_INGREDIENTS = new LinkedList();
		MAGIC_UPGRADE_INGREDIENTS.add(GOLD_NUGGET.getItemStack());
		MAGIC_UPGRADE_INGREDIENTS.add(AMETHYSTE_SHARD.getItemStack());
		MAGIC_UPGRADE_INGREDIENTS.add(GOLD_NUGGET.getItemStack());
		MAGIC_UPGRADE_INGREDIENTS.add(AMETHYSTE_SHARD.getItemStack());
		MAGIC_UPGRADE_INGREDIENTS.add(CONDENSED_AMETHYSTE.getItemStack());
		MAGIC_UPGRADE_INGREDIENTS.add(AMETHYSTE_SHARD.getItemStack());
		MAGIC_UPGRADE_INGREDIENTS.add(GOLD_NUGGET.getItemStack());
		MAGIC_UPGRADE_INGREDIENTS.add(AMETHYSTE_SHARD.getItemStack());
		MAGIC_UPGRADE_INGREDIENTS.add(GOLD_NUGGET.getItemStack());
		MAGIC_UPGRADE = new CraftedItem(Material.CREEPER_BANNER_PATTERN, "§1Amelioration magique", Rarity.VERY_RARE, 7600, MAGIC_UPGRADE_INGREDIENTS, 1);
	}

}
