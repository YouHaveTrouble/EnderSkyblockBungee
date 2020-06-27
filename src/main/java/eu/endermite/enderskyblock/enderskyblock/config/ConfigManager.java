package eu.endermite.enderskyblock.enderskyblock.config;

import eu.endermite.enderskyblock.enderskyblock.EnderSkyblock;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ConfigManager {

    public static void saveDefault(String configname) {
        if (!EnderSkyblock.getPlugin().getDataFolder().exists())
            EnderSkyblock.getPlugin().getDataFolder().mkdir();

        File file = new File(EnderSkyblock.getPlugin().getDataFolder(), configname);

        if (!file.exists()) {
            try (InputStream in = EnderSkyblock.getPlugin().getResourceAsStream(configname)) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Configuration loadConfig(String configname) {
        try {
            Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(EnderSkyblock.getPlugin().getDataFolder(), "config.yml"));
            return configuration;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
