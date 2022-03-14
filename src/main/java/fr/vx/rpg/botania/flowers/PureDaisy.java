package fr.vx.rpg.botania.flowers;

import fr.vx.rpg.RPG;
import fr.vx.rpg.botania.runnables.TransformationDaisy;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class PureDaisy extends fr.vx.rpg.classes.blocks.Block {

    List<TransformationDaisy> runnables = new ArrayList<>();

    public PureDaisy() {

        super(new Item(Material.POPPY, "Pure Daisy", Rarity.UNCOMMON, 0));

    }

    public TransformationDaisy runnablesContainBlock(Block block) {

        for (TransformationDaisy runnable : runnables) {

            if (runnable.getBlock().equals(block))
                return runnable;

        }
        return null;

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        ItemStack item = event.getItemInHand();

        if (Item.isSame(this.getItem(), item)) {

            TransformationDaisy transformation = new TransformationDaisy(event.getBlock());
            runnables.add(transformation);
            transformation.runTaskTimer(RPG.plugin, 0, 1);

        }

    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        Block block = event.getBlock();
        TransformationDaisy runnable;

        if ((runnable = runnablesContainBlock(block)) != null) {

            runnable.cancel();
            runnables.remove(runnable);

        }

    }


}
