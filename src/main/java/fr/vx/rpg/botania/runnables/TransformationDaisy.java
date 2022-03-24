package fr.vx.rpg.botania.runnables;

import fr.vx.rpg.botania.Blocks.LivingRock;
import org.bukkit.*;
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

    public Block getBlock() {

        return this.block;

    }

    public Pair<Block, Integer> getPairFromLocation(Location location) {

        for (Pair<Block, Integer> pair : actualTransformations) {
            if (pair.getKey().getLocation().equals(location))
                return (pair);
        }
        return null;

    }

    public void checkBlocksArround() {

        if (materials.contains(block.getLocation().add(-1, 0, 1).getBlock().getType()))
            if (!containBlock(block.getLocation().add(-1, 0, 1).getBlock()))
                actualTransformations.add(Pair.of(block.getLocation().add(-1, 0, 1).getBlock(), 0));
        if (materials.contains(block.getLocation().add(0, 0, 1).getBlock().getType()))
            if (!containBlock(block.getLocation().add(0, 0, 1).getBlock()))
                actualTransformations.add(Pair.of(block.getLocation().add(0, 0, 1).getBlock(), 0));
        if (materials.contains(block.getLocation().add(1, 0, 1).getBlock().getType()))
            if (!containBlock(block.getLocation().add(1, 0, 1).getBlock()))
                actualTransformations.add(Pair.of(block.getLocation().add(1, 0, 1).getBlock(), 0));
        if (materials.contains(block.getLocation().add(1, 0, 0).getBlock().getType()))
            if (!containBlock(block.getLocation().add(1, 0, 0).getBlock()))
                actualTransformations.add(Pair.of(block.getLocation().add(1, 0, 0).getBlock(), 0));
        if (materials.contains(block.getLocation().add(-1, 0, 0).getBlock().getType()))
            if (!containBlock(block.getLocation().add(-1, 0, 0).getBlock()))
                actualTransformations.add(Pair.of(block.getLocation().add(-1, 0, 0).getBlock(), 0));
        if (materials.contains(block.getLocation().add(-1, 0, -1).getBlock().getType()))
            if (!containBlock(block.getLocation().add(-1, 0, -1).getBlock()))
                actualTransformations.add(Pair.of(block.getLocation().add(-1, 0, -1).getBlock(), 0));
        if (materials.contains(block.getLocation().add(0, 0, -1).getBlock().getType()))
            if (!containBlock(block.getLocation().add(0, 0, -1).getBlock()))
                actualTransformations.add(Pair.of(block.getLocation().add(0, 0, -1).getBlock(), 0));
        if (materials.contains(block.getLocation().add(1, 0, -1).getBlock().getType()))
            if (!containBlock(block.getLocation().add(1, 0, -1).getBlock()))
                actualTransformations.add(Pair.of(block.getLocation().add(1, 0, -1).getBlock(), 0));
    }

    @Override
    public void run() {

        checkBlocksArround();
        List<Pair<Block, Integer>> toRemove = new ArrayList<Pair<Block, Integer>>();
        for (Pair<Block, Integer> pair : actualTransformations) {
            if (pair.getKey().getType() == Material.AIR) {
                toRemove.add(pair);
                continue;
            }
            World world = pair.getKey().getWorld();
            Pair<Block, Integer> newPair = Pair.of(pair.getKey(), pair.getValue() + 1);
            Location location = pair.getKey().getLocation();
            if (newPair.getValue() % 20 == 0)
                world.spawnParticle(Particle.SPELL_INSTANT, location.add(0.5, 1.25, 0.5), 1);
            if (newPair.getValue() == 400) {
                //TODO à modifier
                Location a = newPair.getKey().getLocation();
                new LivingRock().place(a);
                toRemove.add(pair);
            } else
                actualTransformations.set(actualTransformations.indexOf(pair), newPair);
        }
        actualTransformations.removeAll(toRemove);
    }
}
