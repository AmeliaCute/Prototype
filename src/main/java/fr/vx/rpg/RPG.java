package fr.vx.rpg;

import fr.vx.rpg.classes.Crafting.CauldronCraft;
import fr.vx.rpg.classes.Debug.spawn;
import fr.vx.rpg.classes.House.impl.Houses;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.vx.rpg.classes.Jobs.JobEnum;
import fr.vx.rpg.classes.Jobs.Wizard;
import fr.vx.rpg.classes.Player.playerconnection;
import fr.vx.rpg.utils.MySql;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import fr.vx.rpg.classes.Item.impl.Items;

public final class RPG extends JavaPlugin
{

    public MySql mySql = new MySql();

    @Override
    public void onEnable()
    {
        PluginManager pm = Bukkit.getPluginManager();
        mySql.connect();

    	Items.register();
        Houses.Register();

        CauldronCraft wtf = new CauldronCraft(new ItemStack(Items.SILVER_INGOT.getItemStack()), JobEnum.Initie);
        List<ItemStack> ingredients = new ArrayList<ItemStack>(Arrays.asList(Items.SILVER_NUGGET.getItemStack(), Items.AMETHYSTE_SHARD.getItemStack()));
        wtf.setIngredients(ingredients);
        wtf.register();

        pm.registerEvents(new playerconnection(this), this);

        this.getCommand("spawn").setExecutor(new spawn());
    }

    @Override
    public void onDisable()
    {
        mySql.disconnect();
    }

}
