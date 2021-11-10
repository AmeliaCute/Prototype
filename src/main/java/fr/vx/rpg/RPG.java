package fr.vx.rpg;

import org.bukkit.plugin.java.JavaPlugin;

import fr.vx.rpg.classes.Items;

public final class RPG extends JavaPlugin {

    @Override
    public void onEnable() {
    	
    	Items.register();
    	
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
