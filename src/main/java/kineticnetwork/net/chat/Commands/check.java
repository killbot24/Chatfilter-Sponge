package kineticnetwork.net.chat.Commands;

import kineticnetwork.net.chat.Chat;
import kineticnetwork.net.chat.Files.FileEditor;
import kineticnetwork.net.chat.Files.FilePrep;
import kineticnetwork.net.chat.Notify;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.event.filter.cause.Root;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by tjbur on 22/06/2020.
 */
public class check implements CommandExecutor {

    FileEditor store = new FileEditor();
    FilePrep Fp = new FilePrep();
    Notify notify=new Notify();
    @Override
    public CommandResult execute(@Root CommandSource commandSource, CommandContext commandContext) throws CommandException {
        List infractions = null;
        Optional<User> user = commandContext.<User>getOne("player");
        try {
            infractions = store.getInfractions(user.get().getName()); //Gets infractions
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (commandSource instanceof ConsoleSource) {
            notify.listInfractionsConsole(infractions, (user.get().getName()));

        }
        if (commandSource instanceof Player) {
            Player player = (Player) commandSource;
            notify.listInfractionsPlayer(infractions, player, user.get().getName());

        }
        return CommandResult.success();
    }
}
