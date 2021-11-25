package fr.vx.rpg.classes.Item;

public enum Rarity {
	
	VERY_COMMON("§fTrès commun."),
	COMMON("§aCommun."),
	UNCOMMON("§2Peu Commun."),
	RARE("§bRare."),
	VERY_RARE("§1✫"),
	EPIC("§d✫✫"),
	LEGENDARY("§5✫✫✫");
	
	private final String description;

	private Rarity(String description) {
		
		this.description = description;
		
	}
	
	public String getDescription() {
		
		return description;
		
	}
}
