package fr.vx.rpg.classes.Gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GuiHouse
{
    public static void getInventory(Player player, int id, int price)
    {
        Inventory a = Bukkit.createInventory(null, 9, String.valueOf(id));

        ItemStack buy = new ItemStack(Material.GOLD_INGOT);
        ItemMeta buyMeta = buy.getItemMeta();

        //Todo : Check si le joueurs a l'argent.
        buyMeta.setDisplayName(ChatColor.GREEN+"Acheter cette maison ?");
        buyMeta.setLore(Arrays.asList(null, ChatColor.GOLD+"Prix: "+price+" pieces"));
        buy.setItemMeta(buyMeta);

        a.addItem(buy);

        player.updateInventory();
        player.openInventory(a);
    }
}
