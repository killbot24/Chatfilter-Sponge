package kineticnetwork.net.chat.Utils;

import kineticnetwork.net.chat.Chat;
import kineticnetwork.net.chat.Files.FileEditor;
import kineticnetwork.net.chat.Notify;
import org.spongepowered.api.entity.living.player.Player;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TextCheck {
        FileEditor reports= new FileEditor();
            Notify notify= new Notify();
    public boolean checkmessage(String input, Player player, String source, String extra) throws IOException {
        //  At some point i need this to check for sapced out letters,eg p i e (pie) and not get flagged on such things as of again
        //ChatFilter.Blacklisted.put("chickon","big fool");
        String[] sepword = input.split("\\s+");
        for (int i = 0; i < sepword.length; i++) {
            Iterator hmIterator = Chat.Blacklisted.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                Chat.getLogger().info(" "+mapElement.getKey());
                if (sepword[i].contains((CharSequence) mapElement.getKey())) {

                    reports.report(player.getName(), mapElement.getKey().toString(),  source,input, mapElement.getValue().toString());// adds report
                    reports.addmute(player);// mutes player
                    notify.imformPlayerIsMuted(player, mapElement.getValue().toString());// sends mute message
                    notify.imformStaff(player, input, mapElement.getKey().toString(), source, mapElement.getValue().toString());
                    //Player player, String message, String blocked, String type,String reason
                    return true;
                }

            }

        }
        return false;
    }}
