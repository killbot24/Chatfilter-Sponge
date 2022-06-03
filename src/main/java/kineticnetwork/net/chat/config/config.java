package kineticnetwork.net.chat.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kineticnetwork.net.chat.Chat;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;

import static kineticnetwork.net.chat.Chat.getLogger;

/**
 * Created by tjbur on 01/06/2020.
 */
   public class config {
   private static File configFile;
     private static ConfigurationLoader<CommentedConfigurationNode> configManager;
     private static CommentedConfigurationNode config;
     public void reload(){
         load();
         getLogger().info("Reloaded");
     }

    public static void init(File rootDir) {
         configFile = new File(Chat.rootDir,"config.conf");
         configManager = HoconConfigurationLoader.builder().setPath(configFile.toPath()).build();
         getLogger().info(String.valueOf(configManager));
     }

     public static void load() {
         try {
             if (!configFile.exists()) {
                 configFile.getParentFile().mkdirs();
                 configFile.createNewFile();
                 config = configManager.load();

                 List list = new ArrayList<>();
                 // check integrity
                 list.add("bob:John");
                 list.add("john:dave");

                 config.getNode("Blocked").setComment("Blocked word's and reason format to follow is \n Penguin:is flipper boi");
                 config.getNode("Blocked").setValue(list);

                 config.getNode("URL").setComment("Link to appeal");
                // config.getNode("URL").setValue("https://appeal.kineticnetwork.net/");
                 configManager.save(config);
                 save();
             }

           //  config = configManager.load();
         } catch (IOException e) {
             e.printStackTrace();
             return;
         }
         String[] split = new String[0];
         try {
             config = configManager.load();
             Chat.URL = "https://appeal.kineticnetwork.net/";
        //     Chat.getLogger().info(String.valueOf(config.getNode("URL").getValue()));
             String input = config.getNode("Blocked").getString();
             getLogger().info(input);
             input = input.replace("[", "");
             input = input.replace("]", "");
             input = input.replace(" ", "");
             split = input.split(",");

             for (int i = 0; i < split.length; i++) {//Take config split by , add into list
                 String[] item = split[i].split(":");
                 Chat.Blacklisted.put(item[0], item[1]);
                 getLogger().info(Chat.getInstance().Prefix + " " + item[0] + "," + item[1]);
             }
         } catch (Exception e) {
             getLogger().info(Chat.Prefix + " Issue in config formatting \n defaulting to Original List ");
             e.printStackTrace();
             Chat.Blacklisted.put("fag","Homophobic Slur");
             Chat.Blacklisted.put("faggot","Homophobic Slur");
         }
     }

     public static void save() throws IOException {
             configManager.save(config);
     }

     public static CommentedConfigurationNode getNode(String... path) {
         return config.getNode(path);

     }

    /*
public enum config {

    MAIN(
            Sponge.getConfigManager().getPluginConfig(Chat.getInstance())
                    .getDirectory()
                    .toString(),
            "/config.conf",
            "config.conf");

    private final String parent;
    private final String resourcePath;
    private final String fileName;

    config(String parent, String resourcePath, String fileName) {
        this.parent = parent;
        this.resourcePath = resourcePath;
        this.fileName = fileName;
    }

    public String getParent() {
        return parent;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public String getFileName() {
        return fileName;
    } */
}
