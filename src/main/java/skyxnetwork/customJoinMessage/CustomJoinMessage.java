package skyxnetwork.customJoinMessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class CustomJoinMessage extends JavaPlugin implements Listener {

    // Map to store custom messages
    private final Map<String, String> joinMessages = new HashMap<>();
    private final Map<String, String> leaveMessages = new HashMap<>();

    @Override
    public void onEnable() {
        // Register the event listener
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("CustomJoinMessage has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomJoinMessage has been disabled.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("joinmessage")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
                return true;
            }
            Player player = (Player) sender;

            if (args.length < 2) {
                player.sendMessage(ChatColor.RED + "Usage: /joinmessage [set/reset] [join/leave] [message]");
                return true;
            }

            String action = args[0];
            String type = args[1];

            if ("set".equalsIgnoreCase(action) && args.length >= 3) {
                String message = String.join(" ", args).substring(action.length() + type.length() + 2);
                if ("join".equalsIgnoreCase(type)) {
                    joinMessages.put(player.getName(), message);
                    player.sendMessage(ChatColor.GREEN + "Custom join message set!");
                } else if ("leave".equalsIgnoreCase(type)) {
                    leaveMessages.put(player.getName(), message);
                    player.sendMessage(ChatColor.GREEN + "Custom leave message set!");
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid type! Use 'join' or 'leave'.");
                }
            } else if ("reset".equalsIgnoreCase(action)) {
                if ("join".equalsIgnoreCase(type)) {
                    joinMessages.remove(player.getName());
                    player.sendMessage(ChatColor.GREEN + "Custom join message reset!");
                } else if ("leave".equalsIgnoreCase(type)) {
                    leaveMessages.remove(player.getName());
                    player.sendMessage(ChatColor.GREEN + "Custom leave message reset!");
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid type! Use 'join' or 'leave'.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /joinmessage [set/reset] [join/leave] [message]");
            }
            return true;
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String customMessage = joinMessages.get(player.getName());
        if (customMessage != null) {
            event.setJoinMessage(ChatColor.GREEN + "[+] " + customMessage.replace("%player%", player.getName()));
        } else {
            event.setJoinMessage(null); // Let another plugin handle the default message
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String customMessage = leaveMessages.get(player.getName());
        if (customMessage != null) {
            event.setQuitMessage(ChatColor.RED + "[-] " + customMessage.replace("%player%", player.getName()));
        } else {
            event.setQuitMessage(null); // Let another plugin handle the default message
        }
    }
}
