package eu.endermite.enderskyblock.enderskyblock.loadbalancer;

import eu.endermite.enderskyblock.enderskyblock.EnderSkyblock;
import eu.endermite.enderskyblock.enderskyblock.debug.Debug;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.HashMap;


public class LoadBalancer {

    /**
     *
     * @return HashMap of ServerInfo and amount of worlds loaded on that server
     */
    public static HashMap serversLoadState() {

        HashMap<ServerInfo, HashMap> servers = new HashMap<>();

        for (ServerInfo s : EnderSkyblock.getPlugin().getProxy().getServers().values()) {

            HashMap<String, Object> serverData = new HashMap<>();
            final int[] worldsLoaded = {0};

            s.ping(new Callback<ServerPing>() {
                @Override
                public void done(ServerPing result, Throwable error) {

                    String motd = result.getDescriptionComponent().toPlainText();
                    if (!motd.startsWith("ESBDATA")) {
                        return;
                    }
                    String[] info = motd.split(" ");
                    Debug.debugMessage("Server: "+ s.getName()+":");
                    for (String s : info) {
                        if (s.startsWith("w:")) {
                            s = s.replace("w:", "");
                            serverData.put("worlds", Integer.parseInt(s));
                            Debug.debugMessage("Worlds loaded: "+s);
                        }
                        if (s.startsWith("t:")) {
                            s = s.replace("t:", "");
                            serverData.put("mode", s);
                            Debug.debugMessage("Server mode: "+s);
                        }
                    }
                }
            });

            if (serverData.get("worlds") != null && serverData.get("mode") != null)
                servers.put(s, serverData);
        }

        return servers;

    }

}
