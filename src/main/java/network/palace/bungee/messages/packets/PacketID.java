package network.palace.bungee.messages.packets;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PacketID {

    @AllArgsConstructor
    enum Global {
        BROADCAST(1), STAFFCHAT(2), PROXYRELOAD(3), DM(4);

        @Getter private final int id;
    }
}
