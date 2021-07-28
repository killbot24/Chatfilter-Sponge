package kineticnetwork.net.chat.listener;

import kineticnetwork.net.chat.Files.FilePrep;
import kineticnetwork.net.chat.Notify;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.network.ClientConnectionEvent;

public class onPlayerJoin {
    Notify notify=new Notify();
    FilePrep fp=new FilePrep();
    @Listener
    public void onPlayerJoin(ClientConnectionEvent.Join event, @Root Player player) {
        if (player.hasPermission("cf.base")){
            notify.listMutedPlayersToPlayer(fp.getPlayerList(),player,fp.getFlagedlist());
        }

    }
}
