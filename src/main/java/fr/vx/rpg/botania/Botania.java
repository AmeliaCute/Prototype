package fr.vx.rpg.botania;

import fr.vx.rpg.botania.Pool.ManaPool;
import fr.vx.rpg.botania.flowers.PureDaisy;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Botania {

    public static PureDaisy DAISY;
    public static ManaPool MANAPOOL;

    public static List<ItemStack> ITEMS;

    public static void init() {registerItems();}

    public static void registerItems() {
        ITEMS = new ArrayList<>();
        DAISY = new PureDaisy();
        MANAPOOL = new ManaPool();
    }




}
