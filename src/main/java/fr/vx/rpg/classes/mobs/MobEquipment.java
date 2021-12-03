package fr.vx.rpg.classes.mobs;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MobEquipment
{
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack Leggings;
    private ItemStack Boots;
    private ItemStack tool;

    public MobEquipment(ItemStack helmet, ItemStack chestplate, ItemStack Leggings, ItemStack Boots, ItemStack tool)
    {
        this.helmet = helmet;
        this.chestplate = chestplate;
        this.Leggings = Leggings;
        this.Boots = Boots;
        if(tool == null){ this.tool = new ItemStack(Material.AIR);}
        else {this.tool = tool;}
    }

    public ItemStack getHelmet(){return helmet;}
    public ItemStack getChestplate(){return chestplate;}
    public ItemStack getLeggings(){return Leggings;}
    public ItemStack getBoots(){return Boots;}
    public ItemStack getTool(){return tool;}
}
