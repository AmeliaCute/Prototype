package fr.vx.rpg;

import fr.vx.rpg.classes.House.impl.Houses;
import fr.vx.rpg.classes.mobs.Mob;
import fr.vx.rpg.classes.mobs.impl.Mobs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.vx.rpg.classes.Item.impl.Items;

public final class RPG extends JavaPlugin {

    @Override
    public void onEnable() {
    	
    	Items.register();
        Houses.Register();
        Mobs.register();
        this.getCommand("spawn").setExecutor(new spawnCommands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
