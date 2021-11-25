package fr.vx.rpg.classes.Item;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import fr.vx.rpg.RPG;

public class CraftedItem extends Item {
	
	private Recipe recipe;
	private boolean shapeless = false;
	private int amountCrafted;
	private boolean craftable = true;
	
	public CraftedItem(Material material, String name, Rarity rarity, int basePrice, List<ItemStack> ingredients, int amountCrafted) {
		
		super(material, name, rarity, basePrice);
		this.amountCrafted = amountCrafted;
		NamespacedKey key = new NamespacedKey(RPG.getPlugin(RPG.class), material.name());
		ItemStack result = this.getItemStack();
		result.setAmount(amountCrafted);
		recipe = new ShapelessRecipe(key, result);
		
		for (ItemStack ingredient : ingredients) {
			
			((ShapelessRecipe) recipe).addIngredient(new RecipeChoice.ExactChoice(ingredient));
			
		}
		
		shapeless = true;
		Bukkit.addRecipe(recipe);
		
	}

	public CraftedItem(Material material, String name, Rarity rarity, int basePrice, List<Enchantment> enchants, List<Integer> enchantsLvl, List<ItemStack> ingredients, int amountCrafted) {
		
		this(material, name, rarity, basePrice, ingredients, amountCrafted);
		this.enchants = enchants;
		this.enchantsLvl = enchantsLvl;
		enchanted = true;
		
	}
	
	public CraftedItem(Material material, String name, Rarity rarity, int basePrice, String[] shape, Map<Character, ItemStack> ingredientsMap, int amountCrafted) {
		
		super(material, name, rarity, basePrice);
		this.amountCrafted = amountCrafted;
		NamespacedKey key = new NamespacedKey(RPG.getPlugin(RPG.class), material.name());
		ItemStack result = this.getItemStack();
		result.setAmount(amountCrafted);
		recipe = new ShapedRecipe(key, result);
		((ShapedRecipe) recipe).shape(shape[0], shape[1], shape[2]);
		
		for (Entry<Character, ItemStack> entry : ingredientsMap.entrySet()) {
			
			((ShapedRecipe) recipe).setIngredient(entry.getKey(), new RecipeChoice.ExactChoice(entry.getValue()));
			
		}
		
		Bukkit.addRecipe(recipe);
		
	}
	
	public CraftedItem(Material material, String name, Rarity rarity, int basePrice, List<Enchantment> enchants, List<Integer> enchantsLvl, String[] shape, Map<Character, ItemStack> ingredientsMap,int amountCrafted) {
		
		this(material, name, rarity, basePrice, shape, ingredientsMap, amountCrafted);
		this.enchants = enchants;
		this.enchantsLvl = enchantsLvl;
		enchanted = true;
		
	}
	
	public Recipe getRecipe() {
		
		if (shapeless)
			return (ShapelessRecipe) recipe;
		return (ShapedRecipe) recipe;
		
	}
	
}
