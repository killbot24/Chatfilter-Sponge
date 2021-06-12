package kineticnetwork.net.chat.Commands;

import kineticnetwork.net.chat.Files.FileEditor;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;

/**
 * Created by tjbur on 22/06/2020.
 */
public class list implements CommandExecutor
{
    FileEditor store = new FileEditor();
    @Override
    public CommandResult execute(CommandSource commandSource, CommandContext commandContext) throws CommandException {
        if (commandSource instanceof ConsoleSource) {
           // log.Listplayersa();
        }
        if (commandSource instanceof Player) {
            Player player = (Player) commandSource;

             //   log.Listplayers(player);
            }



        // lists all actively muted players
        return CommandResult.success();
    }
}
