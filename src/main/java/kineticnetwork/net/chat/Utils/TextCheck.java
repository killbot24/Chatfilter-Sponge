package kineticnetwork.net.chat.Utils;

import kineticnetwork.net.chat.Chat;
import kineticnetwork.net.chat.Files.FileEditor;
import kineticnetwork.net.chat.Files.FilePrep;
import kineticnetwork.net.chat.Notify;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TranslatableText;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class TextCheck {
    FileEditor reports = new FileEditor();
    Notify notify = new Notify();
    FilePrep fp = new FilePrep();

    public boolean checkmessage(String input, Player player, String source, String extra) throws IOException {
        input = input.toLowerCase();
        String[] sepword = input.split("\\s+");


        for (int i = 0; i < sepword.length; i++) {

            Iterator hmIterator = Chat.Blacklisted.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                if (sepword[i].contains((CharSequence) mapElement.getKey())) {
                   if (source == "Anvil"&&sepword[i].equals(mapElement.getKey())) { // source is not chat
                        //  ChatFilter.getInstance().getLogger().info("Hit check");
                        mutePlayer(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return true;
                    }
                    if (sepword[i].equals(mapElement.getKey())) { // 100% is blacklisted
                        mutePlayer(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return true;
                    } else {// Maybe...
                        possibleflag(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return true;
                    }

                }

            }

        }


        return false;
    }

    public boolean mutePlayer(Player player, String key, String value, String source, String extra, String input) throws IOException {
        reports.report(player.getName(), key, input, source, value);// adds report
        reports.mutePlayer(player);// mutes player
        notify.imformPlayerIsMuted(player, value);// sends mute message
        notify.imformStaff(player, input, key, source, value);
        Chat.getLogger().info(Chat.Prefix + ": " + player.getName() + " Has been muted for :" + key + " \n Message " + input + " Reason: " + value);
        return true;
    }

    public boolean possibleflag(Player player, String key, String value, String source, String extra, String input) throws IOException { //Used for chat if word is contained with in another word
        reports.report(player.getName(), key, input, source, value);// adds report
        if (fp.isPlayerFlaged(player) == false) {
            reports.flagPlayer(player);
        }
        notify.imformStaffPossibleflag(player, input, key, source, value);
        Chat.getLogger().info(Chat.Prefix + ": " + player.getName() + " Has been flagged :" + key + " \n Message " + input + " Reason: " + value);
        return true;
    }
}
