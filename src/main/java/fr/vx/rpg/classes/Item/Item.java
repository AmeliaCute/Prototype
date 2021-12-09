package fr.vx.rpg.classes.Item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {
	
	private Material material;
	private String name;
	private String description;
	private Rarity rarity;
	private float basePrice;
	protected boolean enchanted = false;
	protected List<Enchantment> enchants;
	protected List<Integer> enchantsLvl;
	protected boolean dropable = false;
	protected boolean craftable = false;
	
	public Item(Material material, String name, Rarity rarity, float basePrice) {
		
		this.material = material;
		this.name = name;
		this.description = rarity.getDescription();
		this.rarity = rarity;
		this.basePrice = basePrice;
		
	}
	
	public Item(Material material, String name, Rarity rarity, float basePrice, List<Enchantment> enchants, List<Integer> enchantsLvl) {
			
		this(material, name, rarity, basePrice);
		this.enchants = enchants;
		this.enchantsLvl = enchantsLvl;
		enchanted = false;
		
	}
	
	public Material getMaterial() {
		
		return material;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public String getDescription() {
		
		return description;
		
	}
	
	public Rarity getRarity() {
		
		return rarity;
		
	}
	
	public float getPrice() {
		
		return basePrice;
		
	}
	
	public boolean isDropable() {
		
		return dropable;
		
	}
	
	public boolean isCraftable() {
		
		return craftable;
		
	}
	
	public boolean isEnchanted() {
		
		return enchanted;
		
	}
	
	public ItemStack getItemStack() {
		
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(rarity.getColor()+name);
		itemMeta.setLore(Arrays.asList("", description));
		
		if (enchanted) {
			
			for (int i = 0; i < enchants.size(); i++) {
				
				itemMeta.addEnchant(enchants.get(i), enchantsLvl.get(i), true);
				
			}
		}
		
		item.setItemMeta(itemMeta);
		return item;
				
	}

}
