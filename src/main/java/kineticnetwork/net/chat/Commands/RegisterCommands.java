package kineticnetwork.net.chat.Commands;

import kineticnetwork.net.chat.Chat;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public class RegisterCommands {
    public void registerCommands(){
        CommandSpec reload = CommandSpec.builder()
                .description(Text.of("reloads chat filter"))
                .permission("ct.reload")
                .executor(new reload())
                .build();

        CommandSpec list = CommandSpec.builder()
                .description(Text.of("lists all active mutes"))
                .permission("ct.base")
                .executor(new list())
                .build();

        CommandSpec unmute = CommandSpec.builder()
                .description(Text.of("unmutes player"))
                .permission("ct.base")
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.string(Text.of("Player"))))
                .executor(new unmute())
                .build();
        CommandSpec UnFlag = CommandSpec.builder()
                .description(Text.of("unwatch player"))
                .permission("ct.base")
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.string(Text.of("Player"))))
                .executor(new Unflag())
                .build();

        CommandSpec check = CommandSpec.builder()
                .description(Text.of("checks player"))
                .permission("ct.base")
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.user(Text.of("player"))))
                .executor(new check())
                .build();

        CommandSpec Main = CommandSpec.builder()
                .description(Text.of("All chat tools commands"))
                .child(unmute,"unmute") // links to command unmute
                .child(reload,"reload")
                .child(UnFlag,"unwatch")
                .child(list,"list")
                .child(check,"check")
                .build();

        Sponge.getCommandManager().register(Chat.plugin, reload, "chatfilterreload");
        Sponge.getCommandManager().register(Chat.plugin, unmute, "chatfilterunmute");
        Sponge.getCommandManager().register(Chat.plugin, UnFlag, "chatfilterunwatch");
        Sponge.getCommandManager().register(Chat.plugin, Main, "chatfilter");
        Sponge.getCommandManager().register(Chat.plugin, list, "chatfilterlist");
        Sponge.getCommandManager().register(Chat.plugin, check, "chatfiltercheck");
    }
}
