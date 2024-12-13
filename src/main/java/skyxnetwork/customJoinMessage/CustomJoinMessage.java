package com.example.customjoinmessage;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomJoinMessage extends JavaPlugin implements Listener {

    private File userdataFile;
    private FileConfiguration userdataConfig;

    private final Map<String, String> joinMessages = new HashMap<>();
    private final Map<String, String> leaveMessages = new HashMap<>();

    @Override
    public void onEnable() {
        // Initialize userdata file
        createUserDataFile();

        // Load messages from userdata.yml
        loadMessages();

        // Register the event listener
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("CustomJoinMessage has been enabled.");
    }

    @Override
    public void onDisable() {
        // Save messages to userdata.yml
        saveMessages();
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
                    userdataConfig.set("joinMessages." + player.getName(), message);
                    saveUserDataFile();
                    player.sendMessage(ChatColor.GREEN + "Custom join message set!");
                } else if ("leave".equalsIgnoreCase(type)) {
                    leaveMessages.put(player.getName(), message);
                    userdataConfig.set("leaveMessages." + player.getName(), message);
                    saveUserDataFile();
                    player.sendMessage(ChatColor.GREEN + "Custom leave message set!");
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid type! Use 'join' or 'leave'.");
                }
            } else if ("reset".equalsIgnoreCase(action)) {
                if ("join".equalsIgnoreCase(type)) {
                    joinMessages.remove(player.getName());
                    userdataConfig.set("joinMessages." + player.getName(), null);
                    saveUserDataFile();
                    player.sendMessage(ChatColor.GREEN + "Custom join message reset!");
                } else if ("leave".equalsIgnoreCase(type)) {
                    leaveMessages.remove(player.getName());
                    userdataConfig.set("leaveMessages." + player.getName(), null);
                    saveUserDataFile();
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

    private void createUserDataFile() {
        userdataFile = new File(getDataFolder(), "userdata.yml");
        if (!userdataFile.exists()) {
            userdataFile.getParentFile().mkdirs();
            saveResource("userdata.yml", false);
        }
        userdataConfig = YamlConfiguration.loadConfiguration(userdataFile);
    }

    private void saveUserDataFile() {
        try {
            userdataConfig.save(userdataFile);
        } catch (IOException e) {
            getLogger().severe("Could not save userdata.yml!");
            e.printStackTrace();
        }
    }

    private void loadMessages() {
        if (userdataConfig.contains("joinMessages")) {
            userdataConfig.getConfigurationSection("joinMessages").getKeys(false).forEach(key ->
                    joinMessages.put(key, userdataConfig.getString("joinMessages." + key)));
        }
        if (userdataConfig.contains("leaveMessages")) {
            userdataConfig.getConfigurationSection("leaveMessages").getKeys(false).forEach(key ->
                    leaveMessages.put(key, userdataConfig.getString("leaveMessages." + key)));
        }
    }

    private void saveMessages() {
        userdataConfig.set("joinMessages", joinMessages);
        userdataConfig.set("leaveMessages", leaveMessages);
        saveUserDataFile();
    }
}