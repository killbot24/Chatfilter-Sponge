package kineticnetwork.net.chat.config;

import kineticnetwork.net.chat.Chat;

public class GetItems {
    public void getItems() {
        String[] split = new String[0];
        try {
            String message = config.getNode("URL").getValue().toString();
            Chat.URL = message;// Website URL
            String input = String.valueOf(config.getNode("Blocked").getValue());
            input = input.replace("[", "");
            input = input.replace("]", "");
            input = input.replace(" ", "");
            split = input.split(",");
            for (int i = 0; i < split.length; i++) {//Take config split by , add into list
                String[] item = split[i].split(":");
                Chat.Blacklisted.put(item[0], item[1]);
                Chat.getLogger().info(Chat.getInstance().Prefix + " " + item[0] + "," + item[1]);
            }
        } catch (Exception e) {
            Chat.getLogger().info(Chat.Prefix + " Issue in config formatting \n defaulting to Original List");
            Chat.Blacklisted.put("fag","Homophobic Slur");
            Chat.Blacklisted.put("faggot","Homophobic Slur");
        }


    }
}
