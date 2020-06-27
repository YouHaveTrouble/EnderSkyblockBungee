package eu.endermite.enderskyblock.enderskyblock;

import eu.endermite.enderskyblock.enderskyblock.cache.ConfigCache;
import eu.endermite.enderskyblock.enderskyblock.config.ConfigManager;
import eu.endermite.enderskyblock.enderskyblock.database.MySQL;
import net.md_5.bungee.api.plugin.Plugin;

public final class EnderSkyblock extends Plugin {

    private static EnderSkyblock plugin;
    public static EnderSkyblock getPlugin() {return plugin;}

    private static ConfigCache config;
    public static ConfigCache getConfig() {return config;}

    @Override
    public void onEnable() {
        this.getLogger().info("Enabling...");
        plugin = this;

        ConfigManager.saveDefault("config.yml");
        config = new ConfigCache();

        // conecting to the database
        if (!MySQL.startConnection())
            this.getProxy().stop("EnderSkyblock cannot work without access to a database!");

        if (!MySQL.createTables())
            this.getProxy().stop("EnderSkyblock cannot work without access to a database!");
    }


    @Override
    public void onDisable() {
        // Nothing here yet...
    }
}
