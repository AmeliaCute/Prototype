package fr.vx.rpg;

import fr.vx.rpg.classes.Crafting.CauldronCraft;
import fr.vx.rpg.classes.Debug.spawn;
import fr.vx.rpg.classes.House.impl.Houses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import fr.vx.rpg.classes.Item.impl.Items;

public final class RPG extends JavaPlugin {

    @Override
    public void onEnable() {
    	
    	Items.register();
        Houses.Register();
        CauldronCraft wtf = new CauldronCraft(new ItemStack(Material.DIAMOND_SWORD));
        List<ItemStack> ingredients = new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Material.DIAMOND)));
        wtf.setIngredients(ingredients);
        wtf.register();
        this.getCommand("spawn").setExecutor(new spawn());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
