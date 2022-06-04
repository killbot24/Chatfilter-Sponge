package kineticnetwork.net.chat.Commands;

import kineticnetwork.net.chat.*;
import kineticnetwork.net.chat.Files.FileEditor;
import kineticnetwork.net.chat.config.GetItems;
import kineticnetwork.net.chat.config.config;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import java.io.IOException;
import java.util.function.Function;

/**
 * Created by tjbur on 10/06/2020.
 */
public class reload  extends Chat implements CommandExecutor {

    private String[] Blocked;
    @Override
    public CommandResult execute(CommandSource commandSource, CommandContext commandContext) throws CommandException {
        config config=new config();
        String[] split = new String[0];
        config.reload();
       try {
            Chat.URL = config.getNode("URL").getString();
            Chat.getLogger().info(String.valueOf(config.getNode("URL").getValue()));
            String input = String.valueOf(config.getNode("Blocked").getString());
            Chat.getLogger().info(input);
            input = input.replace("[", "");
            input = input.replace("]", "");
             input = input.replace(" ", "");
            split = input.split(",");

            for (int i = 0; i < split.length; i++) {//Take config split by , add into list
                String[] item = split[i].split("=");
                Chat.Blacklisted.put(item[0], item[1]);
                Chat.getLogger().info(Chat.getInstance().Prefix + " " + item[0] + "," + item[1]);
            }
        } catch (Exception e) {
            Chat.getLogger().info(Chat.Prefix + " Issue in config formatting \n defaulting to Original List ");
            e.printStackTrace();
            Chat.Blacklisted.put("fag","Homophobic Slur");
            Chat.Blacklisted.put("faggot","Homophobic Slur");
        }
        FileEditor store = new FileEditor();
        try {
            store.reloadMutes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getLogger().info("Reloaded");
        return CommandResult.success();

    }

}
