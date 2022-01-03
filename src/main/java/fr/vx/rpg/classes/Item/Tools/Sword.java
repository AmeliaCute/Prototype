package fr.vx.rpg.classes.Item.Tools;

import fr.vx.rpg.RPG;

import fr.vx.rpg.classes.Item.Attributes;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
public class Sword implements Listener
{
    private Material tools;
    private String name;
    private Rarity rarity;
    private float attackdamage;
    private float attackSpeed;
    private EntityType monster;
    private boolean dropable;
    private List<Attributes> attributes;

    public Sword(Material tools, String name, Rarity rarity, List<Attributes> attributes)
    {
        this.tools = tools;
        this.name = name;
        this.rarity = rarity;
        this.attributes = attributes;
    }

    public Sword(Material tools, String name, Rarity rarity, List<Attributes> attributes, EntityType dropOn)
    {
        this.tools = tools;
        this.name = name;
        this.rarity = rarity;
        this.attributes = attributes;
        this.monster = dropOn;
        this.dropable = true;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    public ItemStack getItemStack()
    {
        ItemStack itemstack = new ItemStack(this.tools);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(rarity.getColor()+name);
        for (int i = 0; i < attributes.size(); i++) {
            itemMeta.addAttributeModifier(attributes.get(i).getAttribute(), new AttributeModifier(UUID.randomUUID(), "a", attributes.get(i).getNumber(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        }
        itemMeta.setLore(Arrays.asList("",rarity.getDescription()));
        itemstack.setItemMeta(itemMeta);
        return itemstack;
    }

    @EventHandler
    public void DropOn(EntityDeathEvent e)
    {
        EntityType monster = e.getEntityType();
        if(dropable == true && monster.equals(this.monster))
        {
            e.getDrops().clear(); e.getDrops().add(this.getItemStack());
        }
    }
}
