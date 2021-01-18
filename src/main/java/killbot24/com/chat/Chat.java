package killbot24.com.chat;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import killbot24.com.chat.Commands.check;
import killbot24.com.chat.Commands.list;
import killbot24.com.chat.Commands.reload;
import killbot24.com.chat.Commands.unmute;
import killbot24.com.chat.listener.playerchatListener;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.permission.PermissionDescription;
import org.spongepowered.api.service.permission.PermissionDescription.Builder;
import org.spongepowered.api.service.permission.PermissionService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.text.Text;


@Plugin(
        id = "chat-filter",
        name = "Chat-filter",
        description = "Chat filter",
        authors = {
                "killbot24"
        }
)
public class Chat {
    private static Chat plugin;
    private static File rootDir;
    public static String[] Blocked;
    public static String[] Whitelist;
    private String[] mutes;
    private ConfigurationNode rootNode;
    data dat= new data();
    //Todo at some point.....This is a mess redo it

    @Inject
    public Logger logger;
    @Inject
    @ConfigDir(sharedRoot = true)
    private File defaultConfigDir;
    @Listener
    public void onInit(GameInitializationEvent event) throws IOException
    {
        plugin = this;
        this.logger.info("Chatfilter Starting");
        rootDir = new File(defaultConfigDir, "chat-filter");
        // when they join!
        Sponge.getEventManager().registerListeners(this, new playerchatListener());
        //  Lang.init(rootDir);

        config.init(rootDir);



    }
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        // Hey! The server has started!
        this.logger.info("Hello Chat filter here just blocking messages!!");
        config.load();// loads config




        String message= config.getNode("Mute").getValue().toString();

       // getLogger().info(message);
        dat.setMutemessage(message);// mute message

        Function<Object, String> stringTransformer = new Function<Object,String>() {
            public String apply(Object input) {
                if (input instanceof String) {
                    return (String) input;
                } else {
                    return null;
                }
            }
        };
        //  List<String> a = rootNode.getNode("Blocked").getList(stringTransformer);
        String blacklistinput = (String) config.getNode("Blocked").getValue();
        String whitelistinput = (String) config.getNode("Whitelist").getValue();

        dat.setlogger(logger);

        /* delimiter */
        String delimiter = ",";
           String b= blacklistinput.replace("[","");
              String c =b.replace("]","");
                 String d=c.replace(" ","");
                    /* given string will be split by the argument delimiter provided. */
                   dat.setBlocked(d.split(delimiter));
                            /* delimiter */
                            String aa= whitelistinput.replace("[","");
                            String cc =aa.replace("]","");
                            String dd=cc.replace(" ","");
                            /* given string will be split by the argument delimiter provided. */
                           this.Whitelist = dd.split(delimiter);





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
                .child(list,"list")
                .child(check,"check")
                .build();

        Sponge.getCommandManager().register(plugin, reload, "filterreload");
        Sponge.getCommandManager().register(plugin, unmute, "filterunmute");
        Sponge.getCommandManager().register(plugin, Main, "filter");
        Sponge.getCommandManager().register(plugin, list, "filterlist");
        Sponge.getCommandManager().register(plugin, check, "filtercheck");

    }




    public static Chat getInstance()
    {
        return plugin;
    }
    public static Logger getLogger()
    {
        return getInstance().logger;
    }


    public File getfile() {
        return rootDir;}}

