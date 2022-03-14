package fr.vx.rpg.classes.Item.impl;

import fr.vx.rpg.classes.Item.*;
import fr.vx.rpg.classes.Item.Tools.*;
import fr.vx.rpg.classes.Spell.Spell;
import fr.vx.rpg.classes.Spell.impl.FireballSpell;
import fr.vx.rpg.classes.Spell.impl.Spells;
import fr.vx.rpg.classes.mobs.MobEquipment;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Items {

	public static ChestPlate test;
	public static Tool test2;
	public static Tool test3;
	public static Tool EpeeDeDiana;
	public static MobEquipment larbin;

	public static FarmingBlock test4;
	public static Item test5;
	public static FarmingBlock test6;

	public static Food test7;

	public static MagicWand SpellTest;
	public static List<ItemStack> items = new ArrayList<ItemStack>();

	public static void register()
	{
		List<Attributes> test_a = new ArrayList<>();
		test_a.add(new Attributes(Attributes.Type.ENT_SPEED, 0.3));
		test_a.add(new Attributes(Attributes.Type.ARMOR, 34));
		test = new ChestPlate(Material.IRON_CHESTPLATE, "test", Rarity.LEGENDARY, test_a);

		test2 = new Tool(Material.IRON_HOE, "Houe mysterieuse", Arrays.asList(new Attributes(Attributes.Type.ATT_DAMA, 12)), Rarity.RARE, Arrays.asList("dd", "dd"), 13000);
		test3 = new Tool(Material.GOLDEN_SWORD, "Epee des dieux", Arrays.asList(new Attributes(Attributes.Type.ATT_DAMA, 100000), new Attributes(Attributes.Type.ATT_SPEED, 999999999)), Rarity.RARE,Arrays.asList("Ungivable item"), 999999999);
		EpeeDeDiana = new Tool(Material.IRON_SWORD, "Epee de Diana", Arrays.asList(new Attributes(Attributes.Type.ATT_DAMA, 14)), Rarity.UNCOMMON, Arrays.asList("Cette epee vous a été donnez par Diana","Mais pourquoi tant de gentillesse ?"), 1300);

		larbin = new MobEquipment(new ItemStack(Material.GOLDEN_HELMET),new ItemStack(Material.GOLDEN_CHESTPLATE),new ItemStack(Material.GOLDEN_LEGGINGS),new ItemStack(Material.GOLDEN_BOOTS),new ItemStack(Material.GOLDEN_SWORD));

		test4 = new FarmingBlock(Material.IRON_ORE, test3.getItemStack(), 13000, 1);

		test5 = new Item(Material.SPRUCE_WOOD, "Bois de sapin", Rarity.UNCOMMON, 120);
		test6 = new FarmingBlock(Material.SPRUCE_WOOD, test5.getItemStack(), 300, 1);

		test7 = new Food("Idk",Material.BEETROOT_SOUP,Rarity.LEGENDARY,15,12,1.1,0,10.5, 5);

		List<Spell> connard = new ArrayList<>();
		connard.add(new FireballSpell());
		SpellTest = new MagicWand(Material.BLAZE_ROD,1,"TEST_WAND",Rarity.UNCOMMON,Arrays.asList("Lorem Ipsum blablabla"), 1200, 200, connard);

		items.add(test.getItemStack());
		items.add(test2.getItemStack());
		items.add(test3.getItemStack());
		items.add(EpeeDeDiana.getItemStack());
		items.add(test5.getItemStack());
		items.add(test7.getItemStack());
		items.add(SpellTest.getItemstack());
	}

}
