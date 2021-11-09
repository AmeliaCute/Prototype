package Rpg.classes;

import java.util.List;

import org.bukkit.Material;

public abstract class Item {
	
	private Material material;
	private String name;
	private List<String> description;
	private Rarity rarity;
	private int basePrice;
	private boolean dropable = false;
	
	public Item(Material material, String name, List<String> description, Rarity rarity, int basePrice) {
		
		this.material = material;
		this.name = name;
		this.description = description;
		this.rarity = rarity;
		this.basePrice = basePrice;
		
	}
	
	public Item(Material material, String name, List<String> description, Rarity rarity, int basePrice, Material dropFrom) {
		
		this(material, name, description, rarity, basePrice);
		dropable = true;
		
	}
	
	public Material getMaterial() {
		
		return material;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public List<String> getDescrition() {
		
		return description;
		
	}
	
	public Rarity getRarity() {
		
		return rarity;
		
	}
	
	public int getPrice() {
		
		return basePrice;
		
	}
	
	public boolean isDropable() {
		
		return dropable;
		
	}

}
