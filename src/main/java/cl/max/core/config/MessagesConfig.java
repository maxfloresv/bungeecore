package cl.max.core.config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class MessagesConfig {
    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        Plugin Core = Bukkit.getServer().getPluginManager().getPlugin("Core");
        File data_folder = Core.getDataFolder();
        file = new File(data_folder, "messages.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            Core.saveResource("messages.yml", false);
        }

        customFile = new YamlConfiguration();
        try {
            customFile.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public static FileConfiguration get() {
        return customFile;
    }

    public static void save() {
        try {
            customFile.save(file);
        } catch (IOException e) {
            System.out.println("Hubo un error al guardar el archivo messages.yml: " + e.getMessage());
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
