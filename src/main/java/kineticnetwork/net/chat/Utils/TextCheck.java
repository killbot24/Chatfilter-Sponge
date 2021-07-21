package kineticnetwork.net.chat.Utils;

import kineticnetwork.net.chat.Chat;
import kineticnetwork.net.chat.Files.FileEditor;
import kineticnetwork.net.chat.Notify;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TranslatableText;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TextCheck {
        FileEditor reports= new FileEditor();
            Notify notify= new Notify();
    public boolean checkmessage(String input, Player player, String source, String extra) throws IOException {
        input=input.toLowerCase();
        String[] sepword = input.split("\\s+");


        for ( int i=0;i<sepword.length;i++) {

            Iterator hmIterator = Chat.Blacklisted.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)hmIterator.next();
                if (sepword[i].contains((CharSequence) mapElement.getKey())){
                    if (source!="Chat"){
                        Chat.getLogger().info("test3");
                        //  ChatFilter.getInstance().getLogger().info("Hit check");
                        mutePlayer(player, mapElement.getKey().toString(),mapElement.getValue().toString(),source,extra,input);
                        return true;
                    }
                    if (sepword[i].equals(mapElement.getKey())){ // word is by it self blocked use method1
                        Chat.getLogger().info("test2");
                        mutePlayer(player, mapElement.getKey().toString(),mapElement.getValue().toString(),source,extra,input);
                        return true;
                    }else{
                        Chat.getLogger().info("test");
                        possibleflag(player, mapElement.getKey().toString(),mapElement.getValue().toString(),source,extra,input);
                        return true;
                    }

                }

            }

        }



        return false;
    }
    public boolean mutePlayer(Player player, String key, String value, String source, String extra, String input) throws IOException {
        reports.report(player.getName(), key, input, source, value);// adds report
        //   addMute.addMute(player);// mutes player
        notify.imformPlayerIsMuted(player, value);// sends mute message
        //  notfiy.staff(player, input, key,value,source,extra,true);
        return true;
    }
    public boolean possibleflag(Player player, String key, String value, String source, String extra, String input) throws IOException { //Used for chat if word is contained with in another word
        reports.report(player.getName(),key, input, source, value);// adds report

      //   notfiy.staffpossibleflag(player, input, key,value,source,extra,false);
        return true;
    }
    }
