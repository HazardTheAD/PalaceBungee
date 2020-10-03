package network.palace.bungee.listeners;

import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import network.palace.bungee.PalaceBungee;
import network.palace.bungee.handlers.ProtocolConstants;

public class ProxyPing implements Listener {

    @EventHandler
    public void onProxyPing(ProxyPingEvent event) {
        try {
//            List<String> info = new ArrayList<>(TinkOS.getInfo());
//            ServerPing.PlayerInfo[] infolist = new ServerPing.PlayerInfo[info.size()];
//            for (int i = 0; i < info.size(); i++) {
//                infolist[i] = new ServerPing.PlayerInfo(ChatColor.translateAlternateColorCodes('&', info.get(i)), "");
//            }
            ServerPing response = event.getResponse();
            ServerPing.Protocol p = response.getVersion();
            int remoteProtocol = event.getConnection().getVersion();

            if (remoteProtocol > ProtocolConstants.HIGHEST_VERSION.getProtocolId()) {
                p.setProtocol(ProtocolConstants.HIGHEST_VERSION.getProtocolId());
            } else if (remoteProtocol < ProtocolConstants.LOWEST_VERSION.getProtocolId()) {
                p.setProtocol(ProtocolConstants.LOWEST_VERSION.getProtocolId());
            } else {
                p.setProtocol(remoteProtocol);
            }

            p.setName(ProtocolConstants.getVersionString());

            event.setResponse(new ServerPing(p, new ServerPing.Players(2000, PalaceBungee.getInstance().getProxy().getOnlineCount(), null),
                    "", ""));

//            if (TinkOS.isMaintenance()) {
//                event.setResponse(new ServerPing(p, new ServerPing.Players(0, 0, infolist),
//                        ChatColor.translateAlternateColorCodes('&', TinkOS.getMOTDMmaintenance()),
//                        TinkOS.getServerIcon()));
//            } else {
//                event.setResponse(new ServerPing(p, new ServerPing.Players(2000,
//                        tracker ? TinkOS.getProxyServer().getOnlineCount() : TinkOS.getOnlineCount(), infolist),
//                        ChatColor.translateAlternateColorCodes('&', TinkOS.getMOTD()), TinkOS.getServerIcon()));
//            }
        } catch (Exception ignored) {
        }
    }
}
