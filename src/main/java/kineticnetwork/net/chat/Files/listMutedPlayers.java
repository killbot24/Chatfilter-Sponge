package kineticnetwork.net.chat.Files;

import kineticnetwork.net.chat.Chat;
import kineticnetwork.net.chat.Notify;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TranslatableText;
import org.spongepowered.api.text.format.TextColors;

import java.util.Arrays;
import java.util.List;

public class listMutedPlayers {
    Notify notify= new Notify();
    FileEditor files=new FileEditor();
    public void Listplayers(Player player){ // lists active mutes to staff on login
        List<String> players=null;
        files.Readmute();
        try{
            players  = Arrays.asList(Chat.mutes);
            notify.listMutedPlayersToPlayer(players,player);
        }
        catch (Exception e){
            //No need to spam login more
        }
    }
    public void Listplayersa(){ // lists active mutes to console
        List<String>  players=null;
        try{
            files.Readmute();
            players  = Arrays.asList(Chat.mutes);

            if (players.size()==0){
                Chat.getLogger().info("No actively muted players");
            }else {
                Chat.getLogger().info(String.valueOf(players));
            }
        }
        catch (Exception e){
            //  Text mute = Text.of(TextColors.RED, TranslatableText.builder("No actively muted players").toText());
            Chat.getLogger().info("No actively muted players");
        }
    }
}
