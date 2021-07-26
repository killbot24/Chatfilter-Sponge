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
    public void imformPlayerIsMuted(Player player, String reason) {
        // Text m=  Text.builder(dat.getmutemessage()).toText();
        Text mute = Text.of(Chat.Prefix, TextColors.RED, "You have been muted by chat filter. You may appeal here ", Chat.URL, TextColors.DARK_GRAY, "\n[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
        player.sendMessage(mute);
    }

    public void imformPlayerIsMuteda(Player player) { //Todo rename this
        //Runs on mute check
        // Text m=  Text.builder(dat.getmutemessage()).toText();
        Text mute = Text.of(Chat.Prefix, TextColors.RED, "You have been muted by chat filter. You may appeal here ", Chat.URL, TextColors.DARK_GRAY);
        player.sendMessage(mute);
    }

    //Tell Staff
    public void imformStaff(Player player, String message, String blocked, String type, String reason) {
        if (type=="Anvil"){
            Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.RED, "Chat Filter", TextColors.DARK_GRAY, "]- ", TextColors.GRAY, player.getName(), TextColors.RED, " Has attempted to name a item: ", TextColors.GRAY, TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, TextColors.RED, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.GRAY, blocked, "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
            Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online

        }
        if (type=="Chat") {
            Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.RED, "Chat Filter", TextColors.DARK_GRAY, "]- ", TextColors.GRAY, player.getName(), TextColors.RED, " Has attempted to say: ", TextColors.GRAY, TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, TextColors.RED, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.GRAY, blocked, "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
            Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
        }
        // set text for message

    }

    public void imformStaffPossibleflag(Player player, String message, String blocked, String type, String reason) {
        if (type != "Chat") {
        } else {
            Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, "Chat Filter", TextColors.DARK_GRAY, "]\n", TextColors.BLUE, "POSSIBLE FLAG \n", TextColors.GRAY, player.getName(), TextColors.RED, " Has attempted to say: ", TextColors.GRAY, TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, TextColors.RED, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.GRAY, blocked, "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
            // set text for message
            Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
        }
    }

    public void listMutedPlayersToConsole(List<String> MutedPlayers) {
        Chat.getLogger().info("Muted Players \n" + MutedPlayers.toString());

    }

    public void listMutedPlayersToPlayer(List<String> MutedPlayers, Player player) { //Displays on login & on request
        Text text = Text.of(TextColors.RED, "Players muted-", TranslatableText.builder(MutedPlayers.toString()).toText());// set text for message
        player.sendMessage(text);
    }

    public void listInfractionsPlayer(List<String> Infractions, Player player, String Suspect) {
        Text text = Text.of(TranslatableText.builder(Suspect).toText(), TextColors.DARK_GRAY, "\n[", TextColors.DARK_RED, "Player's Infraction's", TextColors.DARK_GRAY, "]\n", TextColors.WHITE, TranslatableText.builder(Infractions.toString()).toText());// set text for message
        player.sendMessage(text);
    }

    public void imformStaffAnvil(Player player, String message) {
        Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.RED, "Chat Filter", TextColors.DARK_GRAY, "]- ", TextColors.DARK_GRAY, "\n[", TextColors.RED, "Anvil Alert", TextColors.DARK_GRAY, "]- ", TextColors.GRAY, player.getName(), TextColors.DARK_GRAY," [", TextColors.RED, "Message", TextColors.DARK_GRAY, "]-",TextColors.GRAY, TranslatableText.builder(message).toText());
        // set text for message
        Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
    }

    public void listInfractionsConsole(List<String> Infractions, String Suspect) {
        Chat.getLogger().info(Suspect + " infractions \n" + Infractions.toString());
    }

}




