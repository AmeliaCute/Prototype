package fr.vx.rpg.botania;

import fr.vx.rpg.botania.Item.BotaniaItems;
import fr.vx.rpg.utils.logger;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class Botania {

    public static fr.vx.rpg.utils.logger LOGGER = new logger("BOTANIA");

    public static List<ItemStack> ITEMS;

    public static void init() {
        LOGGER.info("Starting..");
        registerItems();}

    public static void registerItems() {
        LOGGER.info("Registering items");
        ITEMS = new ArrayList<>();
        BotaniaItems.init();
        LOGGER.info(ITEMS.size()+" items has been registered");
    }




}
