package fr.vx.rpg.botania.Pool;

import fr.vx.rpg.botania.Botania;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;
import fr.vx.rpg.classes.blocks.Block;
import fr.vx.rpg.classes.blocks.ModdedBlock;
import fr.vx.rpg.utils.CustomBlockData;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;

public class ManaPool extends ModdedBlock {


    public ManaPool()
    {
        super(new Item(Material.CARROT_ON_A_STICK, "ManaPool", Rarity.UNCOMMON, 0));
        Botania.ITEMS.add(this.getItem().getItemStack());
    }
}
