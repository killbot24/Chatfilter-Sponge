package kineticnetwork.net.chat.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;
import kineticnetwork.net.chat.Chat;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.ConfigurationOptions;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

/**
 * Created by tjbur on 01/06/2020.
 */
public class config {
    private static File configFile;
    private static ConfigurationLoader<CommentedConfigurationNode> configManager;
    private static CommentedConfigurationNode config;


    public static void init(File rootDir) {
        configFile = new File(rootDir, "config.conf");
        configManager = HoconConfigurationLoader.builder().setPath(configFile.toPath()).build();

    }

    public static void load() {
        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
                config = configManager.load();
                configManager.save(config);
                List list = new ArrayList<>();

                // check integrity
                list.add("bob:John");
                list.add("john:dave");

                config.getNode("Blocked").setComment("Blocked word's and reason format to follow is \n Penguin:is flipper boi");
                config.getNode("Blocked").setValue(list);

                config.getNode("URL").setComment("Link to appeal");
                config.getNode("URL").setValue("Url");
                save();
            }
            config = configManager.load();
        } catch (IOException e) {
            return;
        }
    }

    public static void save() throws IOException {
            configManager.save(config);
    }

    public static CommentedConfigurationNode getNode(String... path) {
        return config.getNode((Object[]) path);
    }


}
