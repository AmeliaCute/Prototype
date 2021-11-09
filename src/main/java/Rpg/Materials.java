package Rpg;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Materials
{
    public static ItemStack Customize(int ID, boolean hasEnchantment) {
        MaterialEnum material = MaterialEnum.getFromID(ID);
        ItemStack itemStack = new ItemStack(material.material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(material.getName());
        itemMeta.setLore(material.getDescription());
        if (hasEnchantment) {
            itemMeta.addEnchant(material.getEnchantment(), material.getEnchantmentlvl(), true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack SetAttack(ItemStack object, int Damage)
    {
        ItemMeta itemMeta = object.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier("generic.attack.damage", Damage, AttributeModifier.Operation.ADD_NUMBER));
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        object.setItemMeta(itemMeta);
        return object;
    }

    public static void Drop(int objectID, Block block, Player player, boolean hasEnchantment)
    {
        Location BlockLocationBase = block.getLocation();
        Location BlockLocation = BlockLocationBase.add(0.5,0.5,0.5);
        player.getWorld().dropItem(BlockLocation, Materials.Customize(objectID, hasEnchantment));
    }

    public static void DropExp(int objectID, Player player)
    {
        player.giveExp(MaterialEnum.getFromID(objectID).getXp());
    }

    public enum MaterialEnum
    {
        /*
        Notes:
        --- Couleur rareters :

        0 - Tres commun - Blanc
        1 - Commun - Vert claire
        2 - Peu Commun - Vert foncé
        3 - Rare - Bleu Claire
        4 - ⭐ - Bleu foncé
        5 - ⭐⭐ - Rose
        6 - ⭐⭐⭐ - Violet
        */

        PRISMARINE(1,"§fPrismarine",0, Arrays.asList("","§fTres commun."), Material.PRISMARINE,400,4),
        PRISMARINE_SHARD(2,"§fÉclat de prismarine",0, Arrays.asList("","§fTres commun."), Material.PRISMARINE_SHARD, 400,4),
        AMETHYSTE(3, "§bAméthyste",3,Arrays.asList("","§bRare."),Material.PURPLE_STAINED_GLASS, 100, 18),
        AMETHYSTE_SHARD(4, "§bÉclat d'améthyste",3,Arrays.asList("","§bRare."),Material.PURPLE_DYE, 100, 18),
        AMETHYSTE_SHARD_FRACTURED(5,"§dÉclat d'améthyste fracturé",5, Arrays.asList("","§d⭐⭐"), Material.POPPED_CHORUS_FRUIT, 1000, Enchantment.SWEEPING_EDGE, 1, 45),
        AMETIX_SWORD(6,"§9Epee D'ametix",4,Arrays.asList("","§9⭐"), Material.GOLDEN_SWORD, 4500, Enchantment.DURABILITY, 50),
        ;

        private int id;
        private String name;
        private int rarity;
        private List description;
        private Material material;

        //Enchantment
        private Enchantment enchantment;
        private int enchantmentlvl;

        //Other
        private int xp;
        private int baseprice;

        private static Map<Integer, MaterialEnum> ID_MAP = new HashMap<>();

        private MaterialEnum(int id, String name,int rarity,List description, Material material, int baseprice) { this.id = id;this.name = name;this.rarity = rarity;this.description = description;this.material = material;this.baseprice=baseprice; }
        private MaterialEnum(int id, String name,int rarity,List description, Material material, int baseprice, Enchantment enchantment, int enchantmentlvl) { this.id = id;this.name = name;this.rarity = rarity;this.description = description;this.material = material; this.baseprice=baseprice; this.enchantment = enchantment;this.enchantmentlvl = enchantmentlvl; }
        private MaterialEnum(int id, String name,int rarity,List description, Material material, int baseprice, Enchantment enchantment, int enchantmentlvl, int xp) { this.id = id;this.name = name;this.rarity = rarity;this.description = description;this.material = material; this.baseprice=baseprice; this.enchantment = enchantment;this.enchantmentlvl = enchantmentlvl; this.xp = xp;}
        private MaterialEnum(int id, String name,int rarity,List description, Material material, int baseprice, int xp) { this.id = id;this.name = name;this.rarity = rarity;this.description = description;this.material = material; this.baseprice=baseprice; this.xp = xp; }

        static { for(MaterialEnum material : values()) {  ID_MAP.put(material.id, material); } }
        public static MaterialEnum getFromID(int id) { return ID_MAP.get(id); }

        public int getId(){return id;}
        public String getName(){return name;}
        public int getRarity(){return rarity;}
        public List getDescription(){return description;}
        public Material getMaterial(){return material;}
        public Enchantment getEnchantment(){return enchantment;}
        public int getEnchantmentlvl(){return enchantmentlvl;}
        public int getXp(){return xp;}
        public int getBaseprice(){return baseprice;}
    }
}
