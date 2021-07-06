package kineticnetwork.net.chat.Commands;

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

import java.io.IOException;

/**
 * Created by tjbur on 22/06/2020.
 */
public class list implements CommandExecutor
{
    FileEditor store = new FileEditor();
    FilePrep fp=new FilePrep();
    Notify notify=new Notify();
    @Override
    public CommandResult execute(CommandSource commandSource, CommandContext commandContext) throws CommandException {
        try {
            store.reloadMutes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (commandSource instanceof ConsoleSource) {
            notify.listMutedPlayersToConsole(fp.getPlayerList());//Outputs player mutes to console
        }
        if (commandSource instanceof Player) {
            Player player = (Player) commandSource;
            notify.listMutedPlayersToPlayer(fp.getPlayerList(), player);
        }
        return CommandResult.success();
    }
}
