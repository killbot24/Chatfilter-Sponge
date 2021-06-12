package kineticnetwork.net.chat.listener;

import kineticnetwork.net.chat.Files.listMutedPlayers;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.network.ClientConnectionEvent;

public class onPlayerJoin {
    listMutedPlayers listMPlayers=new listMutedPlayers();
    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event, @Root Player player) {
        if (player.hasPermission("cf.base")){
            listMPlayers.Listplayers(player);
        }

    }
}
