package kineticnetwork.net.chat.config;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import kineticnetwork.net.chat.Chat;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;

import javax.security.auth.login.Configuration;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;

public class ConfigManager {
/*


        private static ConfigManager instance;

        private final HashMap<String, HoconConfigurationLoader> loaderByFileName = new HashMap<>();
        private final HashMap<String, ConfigurationNode> nodeByFileName = new HashMap<>();
        private ConfigManager(){}

        public static ConfigManager getInstance() {
            if (instance == null)
                instance = new ConfigManager();
            return instance;
        }

        public void createNewConfig(String parent, String resourcePath, String fileName) {

            if (!Paths.get(parent).toFile().exists())
                Paths.get(parent).toFile().mkdir();

            File configFile = new File(parent, fileName);

            if (!configFile.exists()) {
                Chat.getLogger().info(fileName + " not found. Creating new file.");

                try {
                    InputStream inputStream = Chat.class.getResourceAsStream(resourcePath);

                    if (inputStream == null) {
                        Chat.getLogger().error("Error creating file: " + fileName);
                        return;
                    }

                    FileOutputStream fileOutputStream = new FileOutputStream(configFile);
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1)
                        fileOutputStream.write(buffer, 0, bytesRead);

                    fileOutputStream.close();
                    inputStream.close();
                } catch (IOException err) {
                    Chat.getLogger().error("Error creating file: " + fileName);
                    err.printStackTrace();
                }
            }

            HoconConfigurationLoader hoconLoader = HoconConfigurationLoader.builder().setPath(configFile.toPath()).build();
            ConfigurationNode configNode = null;

            try {
                configNode = hoconLoader.load();
            }
            catch (IOException err) {
                Chat.getLogger().error("Error loading config: " + fileName);
                err.printStackTrace();
            }

            if (configNode == null) {
                Chat.getLogger().error("Error loading config: " + fileName);
                return;
            }

            loaderByFileName.put(fileName, hoconLoader);
            nodeByFileName.put(fileName, configNode);
        }

        public ConfigurationNode getConfig(config config) {
            return nodeByFileName.get(config.getFileName());
        }

        public void saveConfig(config config) {
            try {
                loaderByFileName.get(config.getFileName()).save(nodeByFileName.get(config.getFileName()));
            }
            catch (IOException err) {
                Chat.getLogger().error("Error saving config: " + config.getFileName());
                err.printStackTrace();
            }
        }

        public void reloadConfig(config config) {
            ConfigurationNode configNode = null;
            try {
                configNode = loaderByFileName.get(config.getFileName()).load();
            } catch (IOException err) {
                Chat.getLogger().error("Error loading config: " + config.getFileName());
                err.printStackTrace();
            }

            if (configNode == null) {
                Chat.getLogger().error("Error loading config: " + config.getFileName());
                return;
            }

            nodeByFileName.put(config.getFileName(), configNode);
        }

        public void reloadAllConfig() {
            for (String fileName : nodeByFileName.keySet()) {
                ConfigurationNode configNode = null;
                try {
                    configNode = loaderByFileName.get(fileName).load();
                }
                catch (IOException err) {
                    Chat.getLogger().error("Error reloading config: " + fileName);
                    err.printStackTrace();
                }

                if (configNode == null) {
                    Chat.getLogger().error("Error reloading config: " + fileName);
                    continue;
                }

                nodeByFileName.put(fileName, configNode);
            }
        }

public static Configuration mainConfig;
    public static Configuration activeMutes;
    public static void initMainConfig (File file) {

        mainConfig = new Configuration(file);
        String categorymain;

        categorymain = "ChatFilter";
        mainConfig.setCategoryComment(categorymain, "Manage Modules of chatfilter");
        String[] blackList={"bob","john"};
        ChatFilter.blackListedWords = mainConfig.getStringList("BlackListed", categorymain, blackList,"Words that will trigger chatfilter Must be lowercase");
        ChatFilter.muteMessage = mainConfig.getString("Mute Message", categorymain,"You have been muted by chatfilter","Mute message");


        mainConfig.save();
    }
    public static void registerConfig(FMLPreInitializationEvent event) {
        ChatFilter.config = new File(event.getModConfigurationDirectory() + "/" +  ChatFilter.MOD_ID);
        ChatFilter.warnings = new File(event.getModConfigurationDirectory() + "/" +  ChatFilter.MOD_ID+"/"+"Reports");// player reports
        ChatFilter.warnings.mkdir();
        ChatFilter.config.mkdir();
        initMainConfig(new File(ChatFilter.config.getPath(), "main.cfg"));

    }
    public static void reloadMainConfig() {
        initMainConfig(new File(ChatFilter.config.getPath(), "main.cfg"));
    }
    public static void reloadActiveMutes() {
        initMainConfig(new File(ChatFilter.muteFile.getPath(), "activemutes.yml"));
    }
    }

 */
}

