package kineticnetwork.net.chat.listener;

import kineticnetwork.net.chat.Chat;
import kineticnetwork.net.chat.Notify;
import kineticnetwork.net.chat.Utils.TextCheck;
import org.spongepowered.api.data.manipulator.mutable.tileentity.SignData;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.block.tileentity.ChangeSignEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.io.IOException;
import java.util.List;

public class onSignUse {
    Notify notify = new Notify();
    TextCheck check = new TextCheck();
    @Listener
    public void onChangeSignEvent(ChangeSignEvent event, @Root Player player) throws IOException {
        //Todo
        SignData sign = event.getText();

        String playername = player.getName();
        World world = player.getWorld();
        String worldname = world.getName();
        int x = event.getTargetTile().getLocation().getBlockX();
        int y = event.getTargetTile().getLocation().getBlockY();
        int z = event.getTargetTile().getLocation().getBlockZ();
        Location<World> location = world.getLocation(x, y, z);
        String signdata= sign.getListValue().get().get(0).toPlain()+"-"+sign.getListValue().get().get(1).toPlain()+"-"+sign.getListValue().get().get(2).toPlain()+"-"+sign.getListValue().get().get(3).toPlain();
        notify.signAlert(signdata,player,worldname+","+x+","+y+","+z);
        try {
            for (int i=0;i<4;i++){
              //  Chat.getLogger().info(sign.getListValue().get().get(i).toPlain());
            if (check.checkmessage(sign.getListValue().get().get(i).toPlain(), player, "Sign", worldname+"-"+x+"-"+y+"-"+z)) {
                event.setCancelled(true);
            }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
