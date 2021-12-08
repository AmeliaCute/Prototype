package fr.vx.rpg.classes.Item.impl;

import fr.vx.rpg.classes.Item.CraftedItem;
import fr.vx.rpg.classes.Item.DroppedItem;
import fr.vx.rpg.classes.Item.Rarity;
import fr.vx.rpg.classes.Item.Tools.*;
import fr.vx.rpg.classes.mobs.MobDrop;
import fr.vx.rpg.classes.mobs.MobEquipment;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Items {
	
	public static DroppedItem AMETHYSTE_SHARD;
	public static DroppedItem CONDENSED_AMETHYSTE;
	public static DroppedItem GOLD_NUGGET;

	public static CraftedItem MAGIC_UPGRADE;

	public static Sword IRON_ESPADON;
	public static Sword WOODEN_SWORD;
	public static Sword SILVER_SWORD;
	public static Sword SWORD_OF_ASSASSIN;

	public static MobEquipment LARBIN;
	public static MobEquipment ZOMBIE_TANK;
	public static MobEquipment ZOMBIE_ASSASSIN;
	public static MobEquipment ZOMBIE_MAGE;

	public static ChestPlate ZOMBIE_MAGE_CHESTPLATE;

	public static Leggings ZOMBIE_MAGE_LEGGINGS;

	public static Boots ZOMBIE_MAGE_BOOTS;

	public static MobDrop ZOMBIE_TANK_SWORD;

	public static void register() {

		AMETHYSTE_SHARD = new DroppedItem(Material.PURPLE_DYE, "§fEclat d'Améthyste", Rarity.VERY_COMMON, 400, Material.PURPLE_STAINED_GLASS, 16);
		CONDENSED_AMETHYSTE = new DroppedItem(Material.PURPLE_STAINED_GLASS, "§bAméthyste condensée", Rarity.RARE, 3600, Material.PURPLE_WOOL, 64);
		GOLD_NUGGET = new DroppedItem(Material.GOLD_NUGGET, "§2Pepite d'or", Rarity.UNCOMMON, 100, Material.GOLD_ORE, 12);

		List<ItemStack> MAGIC_UPGRADE_INGREDIENTS = new ArrayList<>();
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

		IRON_ESPADON = new Sword(Material.STONE_SWORD, "Espadon en Fer", Rarity.UNCOMMON, 26, 0.4F);

		WOODEN_SWORD = new Sword(Material.WOODEN_SWORD, "Epée en Bois", Rarity.VERY_COMMON, 6, 1.2F);
		SILVER_SWORD = new Sword(Material.IRON_SWORD, "Epée en argent", Rarity.RARE, 18, 1.1F);
		SWORD_OF_ASSASSIN = new Sword(Material.NETHERITE_SWORD, "Epée de l'Assassin", Rarity.RARE, 30, 1.5F);

		ZOMBIE_MAGE_CHESTPLATE = new ChestPlate(Material.IRON_CHESTPLATE, "Plastron des zombies mages", Rarity.VERY_RARE, 15);
		ZOMBIE_MAGE_LEGGINGS = new Leggings(Material.GOLDEN_LEGGINGS, "Pantalon des zombies mages", Rarity.VERY_RARE, 10);
		ZOMBIE_MAGE_BOOTS = new Boots(Material.GOLDEN_BOOTS, "Bottes des zombies mages", Rarity.VERY_RARE, 6, 0.1F);

		LARBIN = new MobEquipment(new ItemStack(Material.IRON_HELMET), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), WOODEN_SWORD.getItemStack());
		ZOMBIE_TANK = new MobEquipment(new ItemStack(Material.IRON_HELMET), new ItemStack(Material.IRON_CHESTPLATE), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.NETHERITE_BOOTS), SILVER_SWORD.getItemStack());
		ZOMBIE_ASSASSIN = new MobEquipment(new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), SWORD_OF_ASSASSIN.getItemStack());
		ZOMBIE_MAGE = new MobEquipment(new ItemStack(Material.AIR), ZOMBIE_MAGE_CHESTPLATE.getItemStack(), ZOMBIE_MAGE_LEGGINGS.getItemStack(), ZOMBIE_MAGE_BOOTS.getItemStack(), null);

		ZOMBIE_TANK_SWORD = new MobDrop("Zombie Renforcé", SILVER_SWORD.getItemStack(), 20);

	}

}
