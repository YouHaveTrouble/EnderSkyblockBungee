package eu.endermite.enderskyblock.enderskyblock.database;

import eu.endermite.enderskyblock.enderskyblock.EnderSkyblock;
import eu.endermite.enderskyblock.enderskyblock.cache.ConfigCache;
import redis.clients.jedis.Jedis;

public class Redis {

    private Jedis jedis;

    public void connect() {
        ConfigCache config = EnderSkyblock.getConfig();

        jedis = new Jedis(config.getRedisEndpoint(), config.getRedisPort());
        if (config.getRedisAuth()) {
            jedis.auth(config.getRedisPassword());
        }
    }

    public Jedis getConnection() {
        return jedis;
    }

}
