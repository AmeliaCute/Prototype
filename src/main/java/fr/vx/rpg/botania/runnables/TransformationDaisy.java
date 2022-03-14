package fr.vx.rpg.botania.runnables;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TransformationDaisy extends BukkitRunnable {

    private final List<Material> materials = Arrays.asList(Material.OAK_WOOD, Material.COBBLESTONE);
    private List<Pair<Block, Integer>> actualTransformations = new ArrayList<Pair<Block, Integer>>();


    private final Block block;
    private boolean transformation = false;

    public TransformationDaisy(Block block) {

        this.block = block;

    }

    private boolean containBlock(Block block) {

        for (Pair<Block, Integer> pair : actualTransformations) {

            if (pair.getKey().equals(block))
                return true;

        }
        return false;

    }

    public void checkBlocksArround() {

        if (materials.contains(block.getLocation().add(-1, 0, 1).getBlock().getType()) && !containBlock(block.getLocation().add(-1, 0, 1).getBlock()))
            actualTransformations.add(Pair.of(block.getLocation().add(-1, 0, 1).getBlock(), 0));
        else
            actualTransformations.remove(block.getLocation().add(-1, 0, 1).getBlock());
        if (materials.contains(block.getLocation().add(0, 0, 1).getBlock().getType()) && !containBlock(block.getLocation().add(0, 0, 1).getBlock()))
            actualTransformations.add(Pair.of(block.getLocation().add(0, 0, 1).getBlock(), 0));
        else
            actualTransformations.remove(block.getLocation().add(0, 0, 1).getBlock());
        if (materials.contains(block.getLocation().add(1, 0, 1).getBlock().getType()) && !containBlock(block.getLocation().add(1, 0, 1).getBlock()))
            actualTransformations.add(Pair.of(block.getLocation().add(1, 0, 1).getBlock(), 0));
        else
            actualTransformations.remove(block.getLocation().add(1, 0, 1).getBlock());
        if (materials.contains(block.getLocation().add(1, 0, 0).getBlock().getType()) && !containBlock(block.getLocation().add(1, 0, 0).getBlock()))
            actualTransformations.add(Pair.of(block.getLocation().add(1, 0, 0).getBlock(), 0));
        else
            actualTransformations.remove(block.getLocation().add(1, 0, 0).getBlock());
        if (materials.contains(block.getLocation().add(-1, 0, 0).getBlock().getType()) && !containBlock(block.getLocation().add(-1, 0, 0).getBlock()))
            actualTransformations.add(Pair.of(block.getLocation().add(-1, 0, 0).getBlock(), 0));
        else
            actualTransformations.remove(block.getLocation().add(-1, 0, 0).getBlock());
        if (materials.contains(block.getLocation().add(-1, 0, -1).getBlock().getType()) && !containBlock(block.getLocation().add(-1, 0, -1).getBlock()))
            actualTransformations.add(Pair.of(block.getLocation().add(-1, 0, -1).getBlock(), 0));
        else
            actualTransformations.remove(block.getLocation().add(-1, 0, -1).getBlock());
        if (materials.contains(block.getLocation().add(0, 0, -1).getBlock().getType()) && !containBlock(block.getLocation().add(0, 0, -1).getBlock()))
            actualTransformations.add(Pair.of(block.getLocation().add(0, 0, -1).getBlock(), 0));
        else
            actualTransformations.remove(block.getLocation().add(0, 0, -1).getBlock());
        if (materials.contains(block.getLocation().add(1, 0, -1).getBlock().getType()) && !containBlock(block.getLocation().add(1, 0, -1).getBlock()))
            actualTransformations.add(Pair.of(block.getLocation().add(1, 0, -1).getBlock(), 0));
        else
            actualTransformations.remove(block.getLocation().add(1, 0, -1).getBlock());
    }

    @Override
    public void run() {

        checkBlocksArround();
        List<Pair<Block, Integer>> toRemove = new ArrayList<Pair<Block, Integer>>();
        for (Pair<Block, Integer> pair : actualTransformations) {
            Pair<Block, Integer> newPair = Pair.of(pair.getKey(), pair.getValue() + 1);
            if (newPair.getValue() == 400) {
                //TODO à modifier
                newPair.getKey().setType(Material.DIAMOND_BLOCK);
                toRemove.add(pair);
            } else
                actualTransformations.set(actualTransformations.indexOf(pair), newPair);
        }
        actualTransformations.removeAll(toRemove);
    }
}
