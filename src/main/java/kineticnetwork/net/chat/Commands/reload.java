package kineticnetwork.net.chat.Commands;

import kineticnetwork.net.chat.*;
import kineticnetwork.net.chat.Files.FileEditor;
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
        Boolean bla = true;// to stop error
        config.load();


        String message = config.getNode("Blocked").getValue().toString();

        Function<Object, String> stringTransformer = new Function<Object, String>() {
            public String apply(Object input) {
                if (input instanceof String) {
                    return (String) input;
                } else {
                    return null;
                }
            }
        };
        //  List<String> a = rootNode.getNode("Blocked").getList(stringTransformer);
        String blacklistinput = String.valueOf(config.getNode("Blocked").getValue().toString());

        /* delimiter */
        String delimiter = ",";
        String b = blacklistinput.replace("[", "");
        String c = b.replace("]", "");
        String d = c.replace(" ", "");
        /* given string will be split by the argument delimiter provided. */
        // dat.setBlocked(d.split(delimiter));
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
