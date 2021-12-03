package fr.vx.rpg.classes.Item.Tools;

import fr.vx.rpg.RPG;

import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;

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
public class Sword extends Item implements Listener
{
    private Material tools;
    private String name;
    private Rarity rarity;
    private float attackdamage;
    private float attackSpeed;
    private EntityType monster;
    private boolean dropable;

    public Sword(Material tools, String name, Rarity rarity, float attackdamage, float attackSpeed)
    {
        super(tools, name, rarity, attackdamage);
        this.tools = tools;
        this.name = name;
        this.rarity = rarity;
        this.attackdamage = attackdamage;
    }

    public Sword(Material tools, String name, Rarity rarity, float attackdamage, float attackSpeed, EntityType dropOn)
    {
        super(tools, name, rarity, attackdamage);
        this.tools = tools;
        this.name = name;
        this.rarity = rarity;
        this.attackdamage = attackdamage;
        this.attackSpeed = attackSpeed;
        this.monster = dropOn;
        this.dropable = true;
        Bukkit.getPluginManager().registerEvents(this, RPG.getPlugin(RPG.class));
    }

    @Override
    public ItemStack getItemStack()
    {
        ItemStack itemstack = new ItemStack(this.tools);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(this.name);
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", attackdamage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
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
