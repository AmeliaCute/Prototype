package fr.vx.rpg.classes.Item.Tools;

import fr.vx.rpg.classes.Item.Attributes;
import fr.vx.rpg.classes.Item.Item;
import fr.vx.rpg.classes.Item.Rarity;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Axe
{
    private final Type type;
    private final String name;
    private final int Efficiency;
    private final short Durability;
    private final Rarity Rarity;

    private boolean HasAttributes = false;
    private List<Attributes> attributes;

    public Axe (Type type, String name, int Efficiency, short Durability, Rarity rarity)
    {
        this.type = type;
        this.name = name;
        this.Efficiency = Efficiency;
        this.Durability = Durability;
        this.Rarity = rarity;
    }
    public Axe (Type type, String name, int Efficiency, short Durability, List<Attributes> attributes, Rarity rarity)
    {
        this.HasAttributes = true;
        this.attributes = attributes;
        this.type = type;
        this.name = name;
        this.Efficiency = Efficiency;
        this.Durability = Durability;
        this.Rarity = rarity;
    }

    public ItemStack getItemStack()
    {
        ItemStack a = new ItemStack(type.getMaterial());
        ItemMeta b = a.getItemMeta();

        b.setDisplayName(Rarity.getColor() + name);
        b.setLore(Arrays.asList("",Rarity.getDescription()));
        if(HasAttributes)
        {
            for (int i = 0; i < attributes.size(); i++) {
                b.addAttributeModifier(attributes.get(i).getAttribute(), new AttributeModifier(UUID.randomUUID(), "a", attributes.get(i).getNumber(), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            }
        }
        a.setItemMeta(b);

        return a;
    }

    public enum Type
    {
        WOOD(Material.WOODEN_AXE),
        GOLD(Material.GOLDEN_AXE),
        IRON(Material.IRON_AXE),
        DIAMOND(Material.DIAMOND_AXE),
        NETHERITE(Material.NETHERITE_AXE),
        ;
        private Material material;
        private Type(Material material)
        { this.material = material; }

        public Material getMaterial()
        { return material;}
    }
}
