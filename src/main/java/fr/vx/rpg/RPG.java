package fr.vx.rpg;

import fr.vx.rpg.classes.Crafting.CauldronCraft;
import fr.vx.rpg.classes.Debug.RpgCmd;
import fr.vx.rpg.classes.House.impl.Houses;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.vx.rpg.classes.Jobs.JobEvent;
import fr.vx.rpg.classes.Jobs.JobRank;
import fr.vx.rpg.classes.Jobs.impl.jobs;
import fr.vx.rpg.classes.Player.playerconnection;
import fr.vx.rpg.classes.Quests.impl.quests;
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
        quests.register();
        jobs.Register();

        /*CauldronCraft wtf = new CauldronCraft(new ItemStack(Items.SILVER_SWORD.getItemStack()), JobRank.Initie);
        List<ItemStack> ingredients = new ArrayList<ItemStack>(Arrays.asList(Items.SILVER_NUGGET.getItemStack(), Items.AMETHYSTE_SHARD.getItemStack()));
        wtf.setIngredients(ingredients);
        wtf.register();*/

        pm.registerEvents(new playerconnection(this), this);
        pm.registerEvents(new JobEvent(), this);

        this.getCommand("rpg").setExecutor(new RpgCmd());
    }

    @Override
    public void onDisable()
    {
        mySql.disconnect();
    }

}
