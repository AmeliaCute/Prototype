package fr.vx.rpg.utils;


import java.io.ByteArrayInputStream;
import java.util.Base64;

import org.bukkit.craftbukkit.libs.org.apache.commons.io.output.ByteArrayOutputStream;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public class Serializer {
	
	public static String convertItemStackArrayToString(ItemStack what[]){
		
		 try {
			 
		     ByteArrayOutputStream bo = new ByteArrayOutputStream();
		     BukkitObjectOutputStream so = new BukkitObjectOutputStream(bo);
		     so.writeObject(what);
		     so.close();
		     return (Base64.getEncoder().encodeToString(bo.toByteArray()));
		     
		 } catch (Exception error) {
			 
		     System.out.println(error);
		     
		 }
		 
		 return null;
	}

	public static ItemStack[] convertStringToItemStackArray(String data){
		
		 try {
			 
		     byte b[] = Base64.getDecoder().decode(data);
		     ByteArrayInputStream bi = new ByteArrayInputStream(b);
		     BukkitObjectInputStream si = new BukkitObjectInputStream(bi);
		     ItemStack[] obj = (ItemStack[]) si.readObject();
		     si.close();
		     return obj;
		     
		 } catch (Exception error) {
			 
		     System.out.println(error);
		     
		 }
		 
		 return null;
	}

}
