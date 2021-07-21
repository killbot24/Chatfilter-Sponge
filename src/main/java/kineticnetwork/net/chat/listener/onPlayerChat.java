package kineticnetwork.net.chat.listener;


import kineticnetwork.net.chat.Chat;
import kineticnetwork.net.chat.Notify;
import kineticnetwork.net.chat.Utils.TextCheck;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TranslatableText;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;
import java.util.Locale;


/**
 * Created by tjbur on 01/06/2020.
 */
public class onPlayerChat {

    TextCheck check = new TextCheck();


    @Listener
    public void onChat(MessageChannelEvent.Chat event, @Root Player player) throws IOException {
      String  message  = event.getRawMessage().toPlain();

       if (check.checkmessage(message,player,"Chat","null")){// if checkchat finds word blacklisted
          event.setMessageCancelled(true);

       }
    }





}

