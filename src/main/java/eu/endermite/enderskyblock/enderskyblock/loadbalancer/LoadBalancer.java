package eu.endermite.enderskyblock.enderskyblock.loadbalancer;

import eu.endermite.enderskyblock.enderskyblock.EnderSkyblock;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.HashMap;

public class LoadBalancer {

    /**
     *
     * @return HashMap of ServerInfo and amount of worlds loaded on that server
     */
    public static HashMap serversLoadState() {

        HashMap<ServerInfo,Integer> loadHashMap = new HashMap<>();
        for (ServerInfo s : EnderSkyblock.getPlugin().getProxy().getServers().values()) {



            int worldsLoaded = 0;

            //TODO ping every server for the amount of worlds loaded



            loadHashMap.put(s, worldsLoaded);
        }
        return loadHashMap;

    }

}
