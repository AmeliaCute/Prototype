package fr.vx.rpg.classes.Crafting;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.vx.rpg.RPG;
import fr.vx.rpg.utils.CustomBlockData;
import fr.vx.rpg.utils.Serializer;

public class CauldronCraft implements Listener {

	private ItemStack result;
	private List<ItemStack> ingredients;
	
	public CauldronCraft(ItemStack result) {
		
		this.result = result;
	}
	
	public void register() {
		
		Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
		
	}
	
	public void setIngredients(List<ItemStack> ingredients) {
		
		this.ingredients = ingredients;
		
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		
		Block block = event.getBlock();
		
		if (block.getType() == Material.CAULDRON) {
			
			CustomBlockData craftState = new CustomBlockData(block, RPG.getPlugin(RPG.class));
			craftState.clear();
			
		}
		
	}
	
	@EventHandler
	public void onCraft(PlayerDropItemEvent event) {
		
		if (event.getItemDrop() != null) {
			
			List<ItemStack> craftIngredients = this.ingredients;
			ItemStack result = this.result;
			
			new BukkitRunnable() {
				
				@Override
				public void run() {
					
					if (event.getItemDrop().isOnGround()) {
						
						ItemStack itemDropped = event.getItemDrop().getItemStack();
						Location location = event.getItemDrop().getLocation();	
						
						if (location.getBlock().getType() == Material.CAULDRON) {
								
				
							NamespacedKey key = new NamespacedKey(RPG.getPlugin(RPG.class), "cauldron-items");
							ItemStack[] new_ingredients;
							CustomBlockData craftState = new CustomBlockData(location.getBlock(), RPG.getPlugin(RPG.class));
							if (craftState.has(key, PersistentDataType.STRING)) {
									
								ItemStack[] ingredients = Serializer.convertStringToItemStackArray(craftState.get(key, PersistentDataType.STRING));
								new_ingredients = new ItemStack[ingredients.length + 1];
									
								for (int i = 0; i < ingredients.length; i++) {
									new_ingredients[i] = ingredients[i];
								}
									
								new_ingredients[ingredients.length] = itemDropped;
								craftState.set(key, PersistentDataType.STRING, Serializer.convertItemStackArrayToString(new_ingredients));
								
							} else {
									
								new_ingredients = new ItemStack[1];
								new_ingredients[0] = itemDropped;
								craftState.set(key, PersistentDataType.STRING, Serializer.convertItemStackArrayToString(new_ingredients));

							}
							
							if (craftIngredients.containsAll(Arrays.asList(new_ingredients))) {
								
								location.getWorld().dropItemNaturally(location.add(0, 1, 0), result);
								craftState.clear();
								System.out.println("CRAFTE");
							}
							
							for (int i = 0; i < new_ingredients.length; i++) {
								System.out.println(new_ingredients[i].getType().toString());
							}
							event.getItemDrop().remove();
							
						}
						
						this.cancel();
						
					}

				}
				
			}.runTaskTimer(RPG.getPlugin(RPG.class), 0, 1);
			
		}
		
	}
	
}
