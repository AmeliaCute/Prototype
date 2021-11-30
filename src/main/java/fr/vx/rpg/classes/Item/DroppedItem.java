package fr.vx.rpg.classes.Item;

import java.util.List;

import fr.vx.rpg.classes.mobs.impl.Mobs;
import net.minecraft.server.v1_16_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.vx.rpg.RPG;

public class DroppedItem extends Item implements Listener {

	protected int xp = 0;
	private Material dropFrom;
	
	public DroppedItem(Material material, String name, Rarity rarity, int basePrice, Material dropFrom, int xp) {
		
		super(material, name, rarity, basePrice);
		this.dropFrom = dropFrom;
		this.xp = xp;
		dropable = true;
		Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
		
	}

	public DroppedItem(Material material, String name, Rarity rarity, int basePrice, Material dropFrom, int xp, List<Enchantment> enchants, List<Integer> enchantsLvl) {
		
		this(material, name, rarity, basePrice, dropFrom, xp);
		this.enchants = enchants;
		this.enchantsLvl = enchantsLvl;
		enchanted = true;
		
	}
	
	
	@EventHandler
	public void dropFrom(BlockBreakEvent event) {
		
		if (dropable) {
			
			Block block = event.getBlock();
			
			if (block.getType() == dropFrom) {
				
				event.setDropItems(false);
				event.getPlayer().getWorld().dropItemNaturally(block.getLocation(), this.getItemStack());
				event.setExpToDrop(xp);
			}
			
		}
		
	}

}
