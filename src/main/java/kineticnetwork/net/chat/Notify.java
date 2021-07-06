package kineticnetwork.net.chat;


import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;


import org.spongepowered.api.text.Text;

import org.spongepowered.api.text.TranslatableText;
import org.spongepowered.api.text.format.TextColors;

import java.util.List;


/**
 * Created by tjbur on 08/06/2020.
 */
public class Notify extends Chat {
        //Tell player
    public void imformPlayerIsMuted(Player player,String reason){
        // Text m=  Text.builder(dat.getmutemessage()).toText();
        Text mute = Text.of(Chat.Prefix, "You have been muted by chat filter. You may appeal here ", Chat.URL, TextColors.DARK_GRAY, "\n[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
        player.sendMessage(mute);
    }
        //Tell Staff
    public void imformStaff(Player player, String message, String blocked, String type,String reason){
        Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, "Chat Filter", TextColors.DARK_GRAY, "]- ", TextColors.WHITE, player.getName(), TextColors.DARK_GRAY, "[", TextColors.GOLD, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.WHITE, blocked, TextColors.DARK_GRAY, "[", TextColors.GOLD, "Source", TextColors.DARK_GRAY, "]-", TextColors.WHITE, type, TextColors.DARK_GRAY, "[", TextColors.DARK_GREEN, "Message", TextColors.DARK_GRAY, "]-", TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
        // set text for message
        Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
    }
    public void listMutedPlayersToConsole(List<String> MutedPlayers){
        Chat.getLogger().info("Muted Players \n"+MutedPlayers.toString());

    }
    public void listMutedPlayersToPlayer(List<String> MutedPlayers,Player player){ //Displays on login & on request
        Text text = Text.of(TextColors.RED,"Players muted-" ,TranslatableText.builder(MutedPlayers.toString()).toText());// set text for message
        player.sendMessage(text);
    }

    public void listInfractionsPlayer(List<String> Infractions, Player player, String Suspect){
        Text text = Text.of(TranslatableText.builder(Suspect).toText(),TextColors.DARK_GRAY,"\n[",TextColors.DARK_RED,"Player's Infraction's",TextColors.DARK_GRAY,"]\n",TextColors.WHITE ,TranslatableText.builder(Infractions.toString()).toText());// set text for message
        player.sendMessage(text);
    }
    public void imformStaffAnvil(Player player, String message){
        Text text = Text.of(TextColors.DARK_GRAY,  "[",TextColors.AQUA,"Chat Filter",TextColors.DARK_GRAY,"]- ",TextColors.DARK_GRAY,  "[",TextColors.AQUA,"Anvil Alert",TextColors.DARK_GRAY,"]- ",TextColors.WHITE,player.getName(),"[",TextColors.DARK_GREEN,"Message",TextColors.DARK_GRAY,"]-",TranslatableText.builder(message).toText());
        // set text for message
        Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
    }
    public void listInfractionsConsole(List<String> Infractions,  String Suspect){
        Chat.getLogger().info(Suspect+" infractions \n"+Infractions.toString());
    }

}



