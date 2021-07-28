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

import java.util.Optional;

public class Unflag implements CommandExecutor {
    FileEditor store = new FileEditor();

    @Override
    public CommandResult execute(CommandSource commandSource, CommandContext commandContext) throws CommandException {


        Optional<User> user = commandContext.<User>getOne("Player");
        String player = String.valueOf(user.get());// gets player
        try {
            if (commandSource instanceof ConsoleSource) {
                store.unFlagPlayer(player,null);
                store.logunmute("console", player);
            }
            if (commandSource instanceof Player) {
                Player sender = (Player) commandSource;
                store.unFlagPlayer(player, sender);
                store.logunmute(sender.getName(), player);
            }


            return CommandResult.success();
        } catch (Exception e) {
        }
        return null;
    }
}