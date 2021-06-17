package kineticnetwork.net.chat.Files;

import kineticnetwork.net.chat.Chat;
import org.spongepowered.api.entity.living.player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static kineticnetwork.net.chat.Chat.getLogger;

public class FilePrep {
    FileEditor fe=new FileEditor();
    public void unmutePlayer(Player player,Player Sender) {
        //If sender is null is console

    }
    public void displayInfractions(Player suspect, Player Sender) throws FileNotFoundException {
        File file = new File(Chat.getInstance().getfile().getAbsoluteFile() + "/Warnings", suspect + ".yml");
        //If sender is null is console
        List infraction= fe.displayInfractions(file);
        String[] user = new String[infraction.size()];
        user = (String[]) infraction.toArray(user);
        if (Sender!=null){
        Chat.getInstance().getLogger().info("Player's infraction");
        for (int i = 0; i < user.length; ++i) {
            Chat.getInstance().getLogger().info( user[i]);
        }
         }
        else {
          //  Sender.sendMessage("Player's infraction");
            for (int i = 0; i < user.length; ++i) {
          //      Sender.sendMessage(user[i]);
            }
        }
         }
}
