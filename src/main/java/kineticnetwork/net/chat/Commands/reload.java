package kineticnetwork.net.chat.Commands;

import kineticnetwork.net.chat.*;
import kineticnetwork.net.chat.Files.FileEditor;
import kineticnetwork.net.chat.config.GetItems;
import kineticnetwork.net.chat.config.config;
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

        config.load();
        GetItems words =new GetItems();
        words.getItems();
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
