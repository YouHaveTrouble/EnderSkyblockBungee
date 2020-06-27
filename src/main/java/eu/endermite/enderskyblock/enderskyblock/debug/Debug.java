package eu.endermite.enderskyblock.enderskyblock.debug;

import eu.endermite.enderskyblock.enderskyblock.EnderSkyblock;
import net.md_5.bungee.api.ChatColor;

public class Debug {

    /**
     * @param message to display in console as debug message
     */
    public static void debugMessage(String message) {
        if (EnderSkyblock.getConfig().isDebugEnabled()) {
            EnderSkyblock.getPlugin().getLogger().info(ChatColor.AQUA+"DEBUG: "+ message);
        }
    }

    /**
     * @param message to display in console as debug message
     * @param exception exception for printing stacktrace
     */
    public static void debugMessage(String message, Exception exception) {
        if (EnderSkyblock.getConfig().isDebugEnabled()) {
            EnderSkyblock.getPlugin().getLogger().info(ChatColor.AQUA+"DEBUG: "+ message);
            exception.printStackTrace();
        }
    }

}
