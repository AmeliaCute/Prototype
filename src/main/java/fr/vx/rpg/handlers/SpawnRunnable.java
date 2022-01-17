package fr.vx.rpg.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnRunnable extends BukkitRunnable {
	
	@Override
	public void run() {
		
		for (Player player : Bukkit.getOnlinePlayers()) {
			
			Location location = player.getLocation();
			boolean stopCheck = false; 
			
			for (int x = -128; x < 129 && !stopCheck; x++) {
				
				if (x <= 24 && x >= -24)
					continue;
				
				for (int y = -129; y < 129 && !stopCheck; y++) {
					
					for (int z = -128; z < 129 && !stopCheck; z++) {
						
						if (z <= 24 && z >= -24)
							continue;
						
						Location upPos = location.clone().add(x, y + 1, z);
						Location spawnPos = location.clone().add(x, y, z);
						Location downPos = location.clone().add(x, y - 1, z);
						
						if (upPos.getBlock().getType() == Material.AIR && spawnPos.getBlock().getType() == Material.AIR && downPos.getBlock().getType() == Material.BEDROCK) {
								
							 	int entityNumbers = 0;							 	
							 
							 	for (Entity entity : player.getNearbyEntities(64, 64, 64))
							 		if (entity instanceof Monster)
							 			entityNumbers++;
							 	
							 	if (entityNumbers >= 40) {
							 		stopCheck = true;
							 		break;
							 	}
							 	
							 	int randX = (int)(Math.random() * 8);
							 	int randZ = (int)(Math.random() * 8);
							 	Location randUpPos = location.clone().add(x, y + 1, z);
							 	Location randPos = new Location(player.getWorld(), randX, y, randZ);
							 	Location randDownPos = location.clone().add(x, y - 1, z);
							 	while (randUpPos.getBlock().getType() != Material.AIR || randPos.getBlock().getType() != Material.AIR || randDownPos.getBlock().getType() == Material.AIR) {
								 	randUpPos = location.clone().add(x, y + 1, z);
								 	randPos = new Location(player.getWorld(), randX, y, randZ);
								 	randDownPos = location.clone().add(x, y - 1, z);
							 	}
							 	player.getWorld().spawnEntity(randPos, EntityType.BLAZE);
							 	
						}
						
					}
					
				}
				
			}
			
		}
		
	}

}
