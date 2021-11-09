package Rpg;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class Materials
{
    public static ItemStack Customize(int ID, boolean hasEnchantment)
    {
        MaterialEnum material = MaterialEnum.getFromID(ID);
        Material type;
        ItemStack itemStack = new ItemStack(material.material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(material.getName());
        itemMeta.setLore(material.getDescription());
        if(hasEnchantment == true) { itemMeta.addEnchant(material.getEnchantment(), material.getEnchantmentlvl(), false); }
        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static void Drop(int objectID, Block block, Player player, boolean hasEnchantment)
    {
        Location BlockLocationBase = block.getLocation();
        Location BlockLocation = BlockLocationBase.add(0.5,0.5,0.5);
        player.giveExp(MaterialEnum.getFromID(objectID).getXp());
        player.getWorld().dropItem(BlockLocation, Materials.Customize(objectID, hasEnchantment));
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

        PRISMARINE_SHARD(2,"§fÉclat de prismarine",0, Arrays.asList("","§fTres commun."), Material.PRISMARINE_SHARD, 4),
        PRISMARINE(1,"§fPrismarine",0, Arrays.asList("","§fTres commun."), Material.PRISMARINE);

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

        private static Map<Integer, MaterialEnum> ID_MAP = new HashMap<>();
        private static Map<Integer, MaterialEnum> MATERIAL_MAP = new HashMap<>();

        private MaterialEnum(int id, String name,int rarity,List description, Material material) { this.id = id;this.name = name;this.rarity = rarity;this.description = description;this.material = material; }
        private MaterialEnum(int id, String name,int rarity,List description, Material material, Enchantment enchantment, int enchantmentlvl) { this.id = id;this.name = name;this.rarity = rarity;this.description = description;this.material = material;this.enchantment = enchantment;this.enchantmentlvl = enchantmentlvl; }
        private MaterialEnum(int id, String name,int rarity,List description, Material material, int xp) { this.id = id;this.name = name;this.rarity = rarity;this.description = description;this.material = material;this.xp = xp;
        }

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
    }
}
