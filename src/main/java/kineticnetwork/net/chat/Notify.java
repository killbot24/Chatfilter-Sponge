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
        Text mute = Text.of(TextColors.DARK_GRAY , "[" , TextColors.RED , "Chat Filter" , TextColors.DARK_GRAY , "]:\n", TextColors.RED, "You have been muted by chat filter. You may appeal here \n", Chat.URL, TextColors.DARK_GRAY, "\n[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]",TextColors.WHITE, reason);
        player.sendMessage(mute);
    }

    public void imformPlayerIsMuteda(Player player) {
        //Runs on mute check
        // Text m=  Text.builder(dat.getmutemessage()).toText();
        Text mute = Text.of(TextColors.DARK_GRAY , "[" , TextColors.RED , "Chat Filter" , TextColors.DARK_GRAY , "]:\n", TextColors.RED, "You have been muted by chat filter. You may appeal here ", Chat.URL, TextColors.DARK_GRAY);
        player.sendMessage(mute);
    }
/*
    //Tell Staff
    public void imformStaff(Player player, String message, String blocked, String type, String reason) {
        if (type=="Anvil"){
            Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.RED, "Chat Filter", TextColors.DARK_GRAY, "]- ", TextColors.GRAY, player.getName(), TextColors.RED, " Has attempted to name a item: ", TextColors.GRAY, TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, TextColors.RED, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.GRAY, blocked, "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
            Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
        }
        else if (type=="Sign") {
            Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.RED, "Chat Filter", TextColors.DARK_GRAY, "]- ", TextColors.GRAY, player.getName(), TextColors.RED, " Has attempted to put on a sign: ", TextColors.GRAY, TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, TextColors.RED, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.GRAY, blocked, "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
            Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
        }else {
            Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.RED, "Chat Filter", TextColors.DARK_GRAY, "]- ", TextColors.GRAY, player.getName(), TextColors.RED, " Has attempted to say: ", TextColors.GRAY, TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, TextColors.RED, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.GRAY, blocked, "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
            Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
        }
        // set text for message

    }

    public void imformStaffPossibleflag(Player player, String message, String blocked, String type, String reason) {
        if (type == "Sign") {
            Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, "Chat Filter", TextColors.DARK_GRAY, "]\n", TextColors.BLUE, "POSSIBLE FLAG \n", TextColors.GRAY, player.getName(), TextColors.RED, " Has attempted to put on a sign: ", TextColors.GRAY, TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, TextColors.RED, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.GRAY, blocked, "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
            Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
            return;
        }
        if (type == "Anvil") {
            Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, "Chat Filter", TextColors.DARK_GRAY, "]\n", TextColors.BLUE, "POSSIBLE FLAG \n", TextColors.GRAY, player.getName(), TextColors.RED, " Has attempted to put on a item: ", TextColors.GRAY, TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, TextColors.RED, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.GRAY, blocked, "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
            Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
            return;
        } else {
            Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.AQUA, "Chat Filter", TextColors.DARK_GRAY, "]\n", TextColors.BLUE, "POSSIBLE FLAG \n", TextColors.GRAY, player.getName(), TextColors.RED, " Has attempted to say: ", TextColors.GRAY, TranslatableText.builder(message).toText(), "\n", TextColors.DARK_GRAY, TextColors.RED, "Trigger", TextColors.DARK_GRAY, "]-", TextColors.GRAY, blocked, "\n", TextColors.DARK_GRAY, "[", TextColors.RED, "Reason", TextColors.DARK_GRAY, "]", reason);
            // set text for message
            Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
        }
    }
*/
    public void listMutedPlayersToConsole(List<String> MutedPlayers,List<String> FlagedPlayers) {
        Chat.getLogger().info("Muted Players \n" + MutedPlayers.toString()+"\n watched Players \n"+FlagedPlayers.toString());

    }

    public void listMutedPlayersToPlayer(List<String> MutedPlayers, Player player,List<String> FlagedPlayers) { //Displays on login & on request
        Text text = Text.of(TextColors.RED, "Players muted-", TranslatableText.builder(MutedPlayers.toString()).toText(),"\n Flagged Players \n",FlagedPlayers.toString());// set text for message
        player.sendMessage(text);
    }

    public void listInfractionsPlayer(List<String> Infractions, Player player, String Suspect) {
        Text text = Text.of(TranslatableText.builder(Suspect).toText(), TextColors.DARK_GRAY, "\n[", TextColors.DARK_RED, "Player's Infraction's", TextColors.DARK_GRAY, "]\n", TextColors.WHITE, TranslatableText.builder(Infractions.toString()).toText());// set text for message
        player.sendMessage(text);
    }

    public void imformStaffAnvil(Player player, String message) {
        Text text = Text.of(TextColors.DARK_GRAY, "[", TextColors.RED, "Chat Filter", TextColors.DARK_GRAY, "]- ", TextColors.DARK_GRAY, "\n[", TextColors.RED, "Anvil Alert", TextColors.DARK_GRAY, "]- ", TextColors.GRAY, player.getName(), TextColors.DARK_GRAY, TextColors.RED, "\nHas named a item ", TextColors.DARK_GRAY, TextColors.GRAY, TranslatableText.builder(message).toText());
        // set text for message
        Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
    Chat.getLogger().info(Chat.Prefix+"[Chatfilter]-[Anvil Alert] "+player.getName()+" Has named a item called "+message);
    }

    public void listInfractionsConsole(List<String> Infractions, String Suspect) {
        Chat.getLogger().info(Suspect + " infractions \n" + Infractions.toString());
    }
    public void signAlert(String Signdata, Player player, String cords) { //Displays on login & on request
        Text text = Text.of(TextColors.DARK_GRAY,"[",TextColors.RED,Chat.Prefix,TextColors.DARK_GRAY,"]",TextColors.DARK_GRAY,"[",TextColors.RED,"Sign Alert",TextColors.DARK_GRAY,"]-[",TextColors.RED,"Player",TextColors.DARK_GRAY,"] ",TextColors.WHITE,player.getName(),TextColors.RED, "\n Lines split by - \n",TextColors.WHITE, TranslatableText.builder(Signdata).toText(),TextColors.RED,"\nAt Cords :",TextColors.WHITE,cords);// set text for message
        Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(text));   // messages all staff online
    }
    private String extra = "null";//Any extra things such as sign cords

    public void staff(Player player, String message, String trigger, String reason, String source, String extra, boolean confirmed) {
        Text output=Text.of("Shit");
                if (source == "Chat") {
                    if (reason.equalsIgnoreCase("blacklisted-term")){
                        output = Text.of(TextColors.DARK_GRAY , "[" , TextColors.RED , "Chat Filter" , TextColors.DARK_GRAY , "]:",TextColors.WHITE  , player.getName() , TextColors.RED , " Has attempted to say: " , TextColors.GRAY , "Blacklisted-Term", " " , TextColors.DARK_GRAY , "\n[" , TextColors.RED , "Trigger word" , TextColors.DARK_GRAY , "]:" , TextColors.WHITE , "Blacklisted-Term" , TextColors.DARK_GRAY , "\n[" , TextColors.RED , "Contact Higher Staff Immediately" , TextColors.DARK_GRAY , "]:");
                        getLogger().info("[Chat Filter]:" , player.getName() , " Has attempted to say " , message);
                    }else if (confirmed == false) {
                        output = Text.of(TextColors.DARK_GRAY , "[" , TextColors.BLUE , "POSSIBLE FLAG" , TextColors.DARK_GRAY , "]:\n" , TextColors.DARK_GRAY , "[" , TextColors.RED , "Chat Filter" , TextColors.DARK_GRAY ,"]" , TextColors.WHITE  , player.getName() , TextColors.RED , " Has attempted to say: " , TextColors.GRAY , message , " " , TextColors.DARK_GRAY , "\n[", TextColors.RED , "Trigger word" , TextColors.DARK_GRAY , "]:" , TextColors.WHITE , trigger , TextColors.DARK_GRAY , "\n[" , TextColors.RED , "Reason" , TextColors.DARK_GRAY , "]:" , TextColors.WHITE , reason);
                        getLogger().info("[Chat Filter]:" , player.getName() , " Has attempted to say " , message);
                    } else {
                        output = Text.of(TextColors.DARK_GRAY , "[" , TextColors.RED , "Chat Filter" , TextColors.DARK_GRAY , "]:",TextColors.WHITE  , player.getName() , TextColors.RED , " Has attempted to say: " , TextColors.GRAY , message , " " , TextColors.DARK_GRAY , "\n[" , TextColors.RED , "Trigger word" , TextColors.DARK_GRAY , "]:" , TextColors.WHITE , trigger , TextColors.DARK_GRAY , "\n[" ,TextColors.RED , "Reason",TextColors.DARK_GRAY , "]:" , TextColors.WHITE , reason);
                        getLogger().info("[Chat Filter]:" , player.getName() , " Has attempted to say " , message);
                    }
                    } else if (source == "Anvil") {
                        output = Text.of(TextColors.DARK_GRAY , "[" , TextColors.RED , "Chat Filter" , TextColors.GRAY , "]:" , player.getName() , TextColors.RED , " Has named a item called: " , TextColors.GRAY , message , " " , TextColors.DARK_GRAY , "\n[", TextColors.RED , "Trigger word" , TextColors.DARK_GRAY , "]:" , TextColors.WHITE , trigger , TextColors.DARK_GRAY , "\n[" , TextColors.RED , "Reason" , TextColors.DARK_GRAY , "]:" , TextColors.WHITE , reason);
                        getLogger().info("[Chat Filter]:" , player.getName() , " Has named a item called: " , message);
                    } else if (source == "Sign") {
                        output = Text.of(TextColors.DARK_GRAY , "[", TextColors.RED , "Chat Filter" , TextColors.GRAY , "]:" , player.getName() , TextColors.RED , " Has placed a sign: " , TextColors.GRAY , message , " " , TextColors.DARK_GRAY , "\n[" , TextColors.RED ,"Trigger word" , TextColors.DARK_GRAY , "]:" , TextColors.WHITE , trigger , TextColors.DARK_GRAY , "\n[" , TextColors.RED , "Reason" , TextColors.DARK_GRAY , "]:" , TextColors.WHITE , reason , TextColors.DARK_GRAY , "\n[" , TextColors.RED , "Cords" , TextColors.DARK_GRAY , "]:" , TextColors.WHITE , extra);
                        getLogger().info("[Chat Filter]:" , player.getName() , " Has attempted to place a sign with message " , message);
                    }
        Text finalOutput = output; //Idk why this is a thing, It complains otherwise.
        Sponge.getServer().getOnlinePlayers().stream().filter(pl -> pl.hasPermission("ct.base")).forEach(pl -> pl.sendMessage(finalOutput));   // messages all staff online

            }
        }




