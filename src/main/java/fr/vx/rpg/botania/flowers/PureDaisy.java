package fr.vx.rpg.botania.flowers;

import fr.vx.rpg.RPG;
import fr.vx.rpg.botania.runnables.TransformationDaisy;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PureDaisy extends Item implements Listener {

    public PureDaisy() {

        super(Material.POPPY, "Pure Daisy", Rarity.UNCOMMON, 0);
        RPG.plugin.getServer().getPluginManager().registerEvents(this, RPG.plugin);

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        ItemStack item = event.getItemInHand();

        if (Item.isCustomItem(item) && item.getType().equals(Material.POPPY)) {

            new TransformationDaisy(event.getBlock()).runTaskTimer(RPG.plugin, 0, 1);

        }

    }


}
