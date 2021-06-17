package kineticnetwork.net.chat.Commands;

import kineticnetwork.net.chat.Files.FileEditor;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by tjbur on 22/06/2020.
 */
public class unmute implements CommandExecutor {

    FileEditor store = new FileEditor();
    @Override
    public CommandResult execute(CommandSource commandSource, CommandContext commandContext) throws CommandException {


            Optional<User> user = commandContext.<User>getOne("Player");
            String player = String.valueOf(user.get());// gets player
        if (commandSource instanceof ConsoleSource) {
            try {
                store.removeMutesConsole(player);
                store.logunmute("console",player);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } if (commandSource instanceof Player) {
            Player sender = (Player) commandSource;
            try {

                store.unmute(player, sender);
                store.logunmute(sender.getName(),player);

            } catch (IOException e) {
                e.printStackTrace();
            }}


        return CommandResult.success();
    }
}
