package kineticnetwork.net.chat.Utils;

import com.sun.istack.internal.logging.Logger;
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

    /* public boolean checkmessage(String input, Player player, String source, String extra) throws IOException {
        input = input.toLowerCase();
        String[] sepword = input.split("\\s+");


        for (int i = 0; i < sepword.length; i++) {

            Iterator hmIterator = Chat.Blacklisted.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                if (sepword[i].contains((CharSequence) mapElement.getKey())) {
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
    }*/
    //New
    private String extra = "null";//Any extra things such as sign cords


    public int checkmessage(String input, Player player, String source, String extra) throws IOException {
       // Logger.getLogger("Debug").info(player.getName());
        String[] sepword = removeInvalids(input).split("\\s+");

        for (int i = 0; i < sepword.length; i++) {

            Iterator hmIterator = Chat.Blacklisted.entrySet().iterator();
            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry) hmIterator.next();
                if (sepword[i].contains((CharSequence) mapElement.getKey())) {
                    if (source != "Chat") {
                        Chat.getLogger().info("[Chatfilter] [Player]:"+player+" has attempted to say :"+mapElement.getKey().toString()+" soruce "+source);
                        mutePlayer(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return 2;
                    }
                    if (sepword[i].equalsIgnoreCase(String.valueOf(mapElement.getKey()))) { // word is by it self blocked use method1
                        // notfiy.sendresponse(player, mapElement.getValue().toString());// sends mute message
                        Chat.getLogger().info("[Chatfilter] [Player]:"+player+" has attempted to say :"+mapElement.getKey().toString()+" soruce "+source);
                        mutePlayer(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return 2;
                    } else {
                        Chat.getLogger().info("[Chatfilter](Possible flag) [Player]:"+player+" has attempted to say :"+mapElement.getKey().toString()+" soruce "+source);
                        possibleflag(player, mapElement.getKey().toString(), mapElement.getValue().toString(), source, extra, input);
                        return 1;
                    }
                }

            }

        }
        return 0;
    }

    public boolean mutePlayer(Player player, String key, String value, String source, String extra, String input) throws IOException {
        reports.report(player.getName(), key, input, source, value);// adds report
        reports.mutePlayer(player);
            notify.imformPlayerIsMuted(player, value);
        if (!value.contains("Blacklisted Term")) {

           // notfiy.staff(player, input, key, value, source, extra, true);
            notify.staff(player, input, key,value, source,extra, true);
        }else {
         //   notify.imformStaff(player, "Message removed for security", "BlackListed Term", source, value);
            notify.staff(player, "Message removed for security", "BlackListed Term","Higher Action", source,extra, true);
        }
        return true;
    }

    public boolean possibleflag(Player player, String key, String value, String source, String extra, String input) throws IOException { //Used for chat if word is contained with in another word
        reports.report(player.getName(), key, input, source, value);// adds report
        reports.flagPlayer(player);// adds tp watch list
        if (!value.contains("Blacklisted Term")) {
          //  notfiy.staff(player, input, key, value, source, extra, false);
            notify.staff(player, input, key,value, source,extra, false);
        }else {
           // notfiy.staff(player, "Message removed for security", "BlackListed Term", "Higher Action", source, extra, false);
           // notify.staff(player, "Message removed for security", "BlackListed Term", source, value);
            notify.staff(player, "Message removed for security", "BlackListed Term","Higher Action", source,extra, false);
        }
//Todo Maybe change how the messeage is so can use spigot code, Eg notify.staff() instead of possible and imform staff.
        return true;
    }

    private String removeInvalids(String input) {
        String mString;
        mString = input.replace("?", "");
        mString = mString.replace(".", " "); //To avoid merging messages together its a space
        mString = mString.replace(",", " ");

        return mString;
    }
}
