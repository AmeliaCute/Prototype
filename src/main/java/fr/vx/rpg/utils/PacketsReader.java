package fr.vx.rpg.utils;

import fr.vx.rpg.RPG;
import fr.vx.rpg.classes.Npc.Events.NpcRightClicked;
import fr.vx.rpg.classes.Npc.npc;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_16_R3.EntityPlayer;
import net.minecraft.server.v1_16_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PacketsReader
{
    Channel channel;
    public static Map<UUID, Channel> a = new HashMap<UUID, Channel>();

    public void inject(Player player)
    {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        channel = craftPlayer.getHandle().playerConnection.networkManager.channel;
        a.put(player.getUniqueId(),channel);

        if(channel.pipeline().get("PacketInjector") != null) {return;}

        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>()
        {
            @Override
            protected void decode(ChannelHandlerContext channelHandlerContext, Packet<?> packet, List<Object> list) throws Exception {
                list.add(packet);
                readPacket(player, packet);
            }
        });

    }

    public void uninject(Player player)
    {
        channel = a.get(player.getUniqueId());
        if(channel.pipeline().get("PacketInjector") != null)
        {
            channel.pipeline().remove("PacketInjector");
        }
    }

    public void readPacket(Player player, Packet<?> packet)
    {
        // \/ - A activer seulement pour debug (Tres laggy)
        //System.out.println("Debug: Packet:"+packet);
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity"))
        {
            if(getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")) {return;}
            if(getValue(packet, "d").toString().equalsIgnoreCase("OFF_HAND")) {return;}
            if(getValue(packet,"action").toString().equalsIgnoreCase("INTERACT_AT")) {return;}

            int id = (int) getValue(packet, "a");
            if(getValue(packet,"action").toString().equalsIgnoreCase("INTERACT"))
            {
                for(EntityPlayer entityPlayer : RPG.npcList)
                {
                   if(entityPlayer.getId() == id)
                    {	
                	   BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                       scheduler.scheduleSyncDelayedTask(RPG.getPlugin(RPG.class), new Runnable() {
                           @Override
                           public void run() {
                        	   Bukkit.getPluginManager().callEvent(new NpcRightClicked(player, entityPlayer));
                           }
                       }, 20L);
                    }
                }
            }
        }
    }

    private Object getValue(Object instance, String name)
    {
        Object result = null;

        try
        {
            Field field = instance.getClass().getDeclaredField(name);

            field.setAccessible(true);
            result = field.get(instance);
            field.setAccessible(false);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
