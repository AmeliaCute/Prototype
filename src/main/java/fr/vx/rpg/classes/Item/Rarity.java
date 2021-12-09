package fr.vx.rpg.classes.Item;

import org.bukkit.ChatColor;

public enum Rarity {
	
	VERY_COMMON("§fTrès commun.", ChatColor.WHITE),
	COMMON("§aCommun.", ChatColor.GREEN),
	UNCOMMON("§2Peu Commun.", ChatColor.DARK_GREEN),
	RARE("§bRare.", ChatColor.AQUA),
	VERY_RARE("§1✫", ChatColor.BLUE),
	EPIC("§d✫✫", ChatColor.LIGHT_PURPLE),
	LEGENDARY("§5✫✫✫", ChatColor.DARK_PURPLE);
	
	private final String description;
	private final ChatColor color;

	private Rarity(String description, ChatColor color) {
		
		this.description = description;
		this.color = color;
		
	}
	
	public String getDescription() { return description; }
	public ChatColor getColor() {return color;}
}
