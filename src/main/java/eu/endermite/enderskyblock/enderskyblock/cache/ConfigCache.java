package eu.endermite.enderskyblock.enderskyblock.cache;

import eu.endermite.enderskyblock.enderskyblock.config.ConfigManager;
import net.md_5.bungee.config.Configuration;

public class ConfigCache {

    Configuration config = ConfigManager.loadConfig("config.yml");

    boolean debug;
    String mysqlHost;
    String mysqlDatabase;
    String mysqlUsername;
    String mysqlPassword;
    int mysqlPort;
    boolean mysqlSsl;

    public ConfigCache() {
        // Debug status
            debug = config.getBoolean("debug");
        // Mysql information
            mysqlHost = config.getString("storage.mysql.host");
            mysqlDatabase = config.getString("storage.mysql.database");
            mysqlUsername = config.getString("storage.mysql.username");
            mysqlPassword = config.getString("storage.mysql.password");
            mysqlPort = config.getInt("storage.mysql.port");
            mysqlSsl = config.getBoolean("storage.mysql.ssl");

    }

    public boolean isDebugEnabled() {
        return debug;
    }
    public String getMysqlHost() {
        return mysqlHost;
    }
    public String getMysqlDatabase() {
        return mysqlDatabase;
    }
    public String getMysqlUsername() {
        return mysqlUsername;
    }
    public String getMysqlPassword() {
        return mysqlPassword;
    }
    public int getMysqlPort() {
        return mysqlPort;
    }
    public boolean getMysqlSsl() {
        return mysqlSsl;
    }
}
