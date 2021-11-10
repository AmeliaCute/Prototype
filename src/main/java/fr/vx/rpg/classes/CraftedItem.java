package fr.vx.rpg.classes;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import fr.vx.rpg.RPG;

public class CraftedItem extends Item {
	
	
	
	public CraftedItem(Material material, String name, Rarity rarity, int basePrice, Map<Character, ItemStack> ingredientsMap, boolean isShapeless) {
		
		super(material, name, rarity, basePrice);
		craftable = true;
		NamespacedKey key = new NamespacedKey(RPG.getPlugin(RPG.class), name);
		
		if (isShapeless) {
			
			ShapelessRecipe recipe = new ShapelessRecipe(key, this.getItemStack());
			
			for (Entry<Character, ItemStack> entry : ingredientsMap.entrySet()) {
				
				recipe.addIngredient(new RecipeChoice.ExactChoice(entry.getValue()));
				
			}
			
		} else {
			
			ShapedRecipe recipe = new ShapedRecipe(key, this.getItemStack());
			String[] shape = new String[3];
			int index = 0;
			
			for (Entry<Character, ItemStack> entry : ingredientsMap.entrySet()) {
				
				shape[index/3] += entry.getKey();
				recipe.setIngredient(entry.getKey(), new RecipeChoice.ExactChoice(entry.getValue()));
				index++;
			
			}
			
			recipe.shape(shape[0], shape[1], shape[2]);
			Bukkit.addRecipe(recipe);
				
		}
	}

	public CraftedItem(Material material, String name, Rarity rarity, int basePrice, List<Enchantment> enchants, List<Integer> enchantsLvl, Map<Character, ItemStack> ingredientsMap, boolean isShapeless) {
		
		this(material, name, rarity, basePrice, ingredientsMap, isShapeless);
		this.enchants = enchants;
		this.enchantsLvl = enchantsLvl;
		enchanted = true;
		
	}
	
	
}
