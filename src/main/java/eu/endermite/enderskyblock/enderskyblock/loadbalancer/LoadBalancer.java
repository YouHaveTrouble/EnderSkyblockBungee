package eu.endermite.enderskyblock.enderskyblock.loadbalancer;

import net.md_5.bungee.api.config.ServerInfo;

import java.util.HashMap;


public class LoadBalancer {

    /**
     *
     * @return HashMap of ServerInfo and amount of worlds loaded on that server
     */
    public static HashMap serversLoadState() {

        HashMap<ServerInfo, HashMap> servers = new HashMap<>();

        //TODO redis call to check amount of loaded worlds on every server

        return servers;

    }

}
