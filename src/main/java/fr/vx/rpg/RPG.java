package fr.vx.rpg;

import fr.vx.rpg.botania.Botania;
import fr.vx.rpg.classes.Crafting.CauldronCraft;
import fr.vx.rpg.classes.Debug.RpgCmd;
import fr.vx.rpg.classes.Debug.RpgCmdTbCompletion;
import fr.vx.rpg.classes.House.House;
import fr.vx.rpg.classes.House.impl.Houses;

import fr.vx.rpg.classes.Jobs.JobRank;
import fr.vx.rpg.classes.Jobs.impl.jobs;
import fr.vx.rpg.classes.Npc.NpcInteraction;
import fr.vx.rpg.classes.Npc.NpcInteractionEvent;
import fr.vx.rpg.classes.Npc.impl.citizens;
import fr.vx.rpg.classes.Npc.npc;
import fr.vx.rpg.classes.Player.Chats.Chat;
import fr.vx.rpg.classes.Player.Chats.chatcommands;
import fr.vx.rpg.classes.Player.playerconnection;
import fr.vx.rpg.classes.Quests.impl.quests;
import fr.vx.rpg.classes.Spell.impl.Spells;
import fr.vx.rpg.classes.teams.coopCommand;
import fr.vx.rpg.handlers.SpawnRunnable;
import fr.vx.rpg.utils.MySql;
import fr.vx.rpg.utils.PacketsReader;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import fr.vx.rpg.classes.Item.impl.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RPG extends JavaPlugin
{

    public static List<EntityPlayer> npcList = new ArrayList<EntityPlayer>();
    public MySql mySql = new MySql();
    public static String MODID = "rpg";
    public static Plugin plugin;

    @Override
    public void onEnable()
    {
        plugin = RPG.getPlugin(RPG.class);
        PluginManager pm = Bukkit.getPluginManager();
        //mySql.connect();
        Botania.registerItems();

    	Items.register();
        //Houses.Register();
        //quests.register();
        //jobs.Register();
        Spells.register();

        citizens.register();
        if(!Bukkit.getOnlinePlayers().isEmpty())
        {
            for(Player player : Bukkit.getOnlinePlayers())
            {
                PacketsReader reader = new PacketsReader();
                reader.inject(player);
            }
        }
        
        //TODO tester
        new SpawnRunnable().runTaskTimer(this, 0, 200);
        CauldronCraft wtf = new CauldronCraft(new ItemStack(Items.test2.getItemStack()), JobRank.Novice);
        List<ItemStack> ingredients = new ArrayList<ItemStack>(Arrays.asList(new ItemStack(Material.IRON_INGOT), new ItemStack(Material.STICK)));
        wtf.setIngredients(ingredients);
        wtf.register();

        pm.registerEvents(new playerconnection(), this);
        pm.registerEvents(new NpcInteractionEvent(), this);
        pm.registerEvents(new Chat(), this);

        this.getCommand("rpg").setExecutor(new RpgCmd());
        this.getCommand("coop").setExecutor(new coopCommand());
        this.getCommand("chat").setExecutor(new chatcommands());
        this.getCommand("rpg").setTabCompleter(new RpgCmdTbCompletion());
        
    }

    @Override
    public void onDisable()
    {
        mySql.disconnect();

        if(!Bukkit.getOnlinePlayers().isEmpty())
        {
            for(Player player : Bukkit.getOnlinePlayers())
            {
                PacketsReader reader = new PacketsReader();
                reader.uninject(player);
            }
        }
    }

}
