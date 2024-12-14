# CustomJoinMessage  
Developed by ✨ | Sky X Network | ✨
-
[![Discord](https://badgen.net/badge/icon/discord?icon=discord&label)](https://discord.gg/pTErYjTh5h)
[![Maintenance](https://img.shields.io/badge/Maintained%3F-no-red.svg)](https://bitbucket.org/lbesson/ansi-colors)
[![Website](https://img.shields.io/website-up-down-green-red/http/shields.io.svg)](https://skyxnetwork.net)
# Overview  

CustomJoinMessage is a lightweight and highly configurable Minecraft plugin designed specifically for Paper 1.20-1.20.1 servers. It allows server admins to customize player join and leave messages, featuring a powerful filter to block inappropriate words and characters. The plugin supports permissions management and integrates seamlessly with other custom join message plugins, making it an ideal solution for server owners looking to offer a personalized and safe player experience.  
## Features  

    Powerful Filter: Built-in filter to block special words, insults, and characters.  
    Custom Messages: Customize player join and leave messages.  
    Permissions Support: Fully compatible with popular permissions management plugins like LuckPerms, PermissionsEx, etc.  
    Quick Command: Use /jm as a faster alternative to /joinmessage for managing custom messages.  
    Configurable Prefix: Easily change the plugin's prefix via the config.yml file.  
    Supports Other CustomJoinMessage Plugins: Works seamlessly with other custom join message plugins.  
    Easy Installation: No additional dependencies required; it’s ready to use out of the box.  

## Commands and Permissions  

/jm - Quick command to manage custom messages.  

    Permission: skyxnetwork.customjoinmessage.manage  

/joinmessage - Main command to manage join and leave custom messages.  

    Permission: skyxnetwork.customjoinmessage.manage  

/joinmessage reload - Reloads the plugin's configuration.  

    Permission: skyxnetwork.customjoinmessage.reload  

/joinmessage set [join/leave] [message] - Sets a custom join or leave message.  

    Permission: skyxnetwork.customjoinmessage.set  

/joinmessage reset [join/leave] - Resets a custom join or leave message.  

    Permission: skyxnetwork.customjoinmessage.reset  

## Installation  

    Download the plugin .jar file.  
    Place it in your server's plugins folder.  
    Start or restart your server.  
    Modify the config.yml file to customize messages and settings.  
    Use /joinmessage reload to apply changes.  

## Compatibility  

This plugin is exclusively designed for Paper 1.20-1.20.1. It will not function properly on other versions or platforms like Spigot or Bukkit. No updates or extended support will be provided for these versions.  

## Example Configuration (config.yml)  
```
# The main plugin prefix.
Prefix: "§dSky X §9Network §eJoinMessage §8●⏺ "

# Bad Words Filter
Filter:
  - asshole
  - 4sshole
  - a$$hole
  - AsShOlE
  - a$$h0le
  - a5shole
  - bastard
  - b4stard
  - b@stard
  - b@st4rd
  - b4$tard
  - Bitch
  - b1tch
  - b!tch
  - cock
  - c0ck
  - c**k
  - C0cK
  - C@ck
  - C0**k
  - cunt
  - c4nt
  - c**t
  - C@nt
  - dick
  - d1ck
  - d!ck
  - fag
  - f4g
  - f@g
  - f@ggot
  - Fuck
  - f4ck
  - phuck
  - f**ck
  - f**k
  - motherfucker
  - m0therfucker
  - m0th3rfuck3r
  - M0th3rF**ker
  - M0ThErfUcKeR
  - M0thErF@cker
  - shit
  - sh1t
  - sh!t
  - s**t
  - slut
  - s1ut
  - s!ut
  - Sex
  - s3x
  - s@x
  - Nigger
  - Nigg3r
  - N1gg3r
  - N!gg3r
  - Ni99er
  - whore
  - wh0re
  - w!hore
  - w0r3
```
## Supported Permission Manager Plugins  

    LuckPerms  
    PermissionsEx  
    UltraPermissions  
    GroupManager  
    ZPermissions  
    CommandBook  

# Disclaimer  

This plugin is provided as-is and is intended for Paper 1.20-1.20.1 server owners. It will not receive updates or extended support for other server types or newer versions.
# Please consider giving a star if you like the plugin ♥️! ^^  
