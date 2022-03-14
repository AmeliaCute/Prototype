package fr.vx.rpg.classes.Item;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import fr.vx.rpg.utils.Maths;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

	private final String identifier;
	private Material material;
	private String name;
	private String description;
	private Rarity rarity;
	private float basePrice;
	protected boolean enchanted = false;
	protected List<Enchantment> enchants;
	protected List<Integer> enchantsLvl;
	protected boolean dropable = false;
	protected boolean craftable = false;

	/**
	 *
	 * @param material set a Material of the Bukkit Class Material
	 * @param name Set a name to your Item
	 * @param rarity set the Rarity
	 * @param basePrice set the Default price
	 */
	public Item(Material material, String name, Rarity rarity, float basePrice) {
		
		this.material = material;
		this.name = name;
		this.description = rarity.getDescription();
		this.rarity = rarity;
		this.basePrice = basePrice;
		this.identifier = Maths.toAsciiInteger(name).toString();
		
	}

	/**
	 *
	 * @param material set a Material of the Bukkit Class Material
	 * @param name Set a name to your Item
	 * @param rarity set the Rarity
	 * @param basePrice set the Default price
	 * @param enchants set the Enchantments
	 * @param enchantsLvl set the Enchantments Level
	 */
	public Item(Material material, String name, Rarity rarity, float basePrice, List<Enchantment> enchants, List<Integer> enchantsLvl) {
			
		this(material, name, rarity, basePrice);
		this.enchants = enchants;
		this.enchantsLvl = enchantsLvl;
		enchanted = false;
		
	}

	public String getIdentifier() {

		return this.identifier;

	}

	public Material getMaterial() {
		
		return material;
		
	}
	
	public String getName() {
		
		return name;
		
	}
	
	public String getDescription() {
		
		return description;
		
	}
	
	public Rarity getRarity() {
		
		return rarity;
		
	}
	
	public float getPrice() {
		
		return basePrice;
		
	}
	
	public boolean isDropable() {
		
		return dropable;
		
	}
	
	public boolean isCraftable() {
		
		return craftable;
		
	}
	
	public boolean isEnchanted() {
		
		return enchanted;
		
	}
	
	public ItemStack getItemStack() {
		
		ItemStack item = new ItemStack(material);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(rarity.getColor() + name);
		itemMeta.setLore(Arrays.asList("",rarity.getDescription(), ChatColor.GOLD+""+basePrice+" pieces"));
		
		if (enchanted) {
			
			for (int i = 0; i < enchants.size(); i++) {
				
				itemMeta.addEnchant(enchants.get(i), enchantsLvl.get(i), true);
			}
		}

		item.setItemMeta(itemMeta);
		net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
		NBTTagCompound itemCompound = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
		itemCompound.setString("custom_id", this.identifier);
		nmsItem.setTag(itemCompound);
		item = CraftItemStack.asBukkitCopy(nmsItem);
		return item;
				
	}

	public static boolean isSame(Item item, ItemStack itemStack) {

		net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
		if (nmsItem.hasTag()) {
			NBTTagCompound itemCompound = nmsItem.getTag();
			if (itemCompound.getString("custom_id").equalsIgnoreCase(item.getIdentifier())) {
				System.out.println("1234");
				return true;
			}
		}
		return false;
	}

}
