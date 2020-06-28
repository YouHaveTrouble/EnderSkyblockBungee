package eu.endermite.enderskyblock.enderskyblock;

import eu.endermite.enderskyblock.enderskyblock.cache.ConfigCache;
import eu.endermite.enderskyblock.enderskyblock.config.ConfigManager;
import eu.endermite.enderskyblock.enderskyblock.database.MySQL;
import eu.endermite.enderskyblock.enderskyblock.database.Redis;
import net.md_5.bungee.api.plugin.Plugin;
import redis.clients.jedis.Jedis;

import java.util.List;

public final class EnderSkyblock extends Plugin {

    private static EnderSkyblock plugin;
    public static EnderSkyblock getPlugin() {return plugin;}

    private static ConfigCache config;
    public static ConfigCache getConfig() {return config;}

    private static Redis redis;
    public static Redis getRedis() {return redis;}

    @Override
    public void onEnable() {
        this.getLogger().info("Enabling...");
        plugin = this;

        ConfigManager.saveDefault("config.yml");
        config = new ConfigCache();

        // Database stuff
        if (!MySQL.startConnection())
            this.getProxy().stop("EnderSkyblock cannot work without access to a database!");

        if (!MySQL.createTables())
            this.getProxy().stop("EnderSkyblock cannot work without access to a database!");

        redis = new Redis();
        getRedis().connect();

    }


    @Override
    public void onDisable() {
        MySQL.stopConnection();
        getRedis().getConnection().close();
    }
}
