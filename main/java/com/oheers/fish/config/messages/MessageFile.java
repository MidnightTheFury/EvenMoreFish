package com.oheers.fish.config.messages;

import com.oheers.fish.EvenMoreFish;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class MessageFile {

    private final EvenMoreFish plugin;
    private FileConfiguration messageConfig;

    public MessageFile(EvenMoreFish plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {

        File messageFile = new File(this.plugin.getDataFolder(), "messages.yml");

        if (!messageFile.exists()) {
            messageFile.getParentFile().mkdirs();
            this.plugin.saveResource("messages.yml", false);
        }

        this.messageConfig = new YamlConfiguration();

        try {
            this.messageConfig.load(messageFile);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }

        EvenMoreFish.messageFile = this;
    }

    public FileConfiguration getConfig() {
        if (this.messageConfig == null) reload();
        return this.messageConfig;
    }

}
