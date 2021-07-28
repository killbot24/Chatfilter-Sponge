package kineticnetwork.net.chat.Files;

import kineticnetwork.net.chat.Chat;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TranslatableText;
import org.spongepowered.api.text.format.TextColors;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tjbur on 06/06/2020.
 */
public class FileEditor extends Chat {
    private String[] mutes;
    private String[] flaged;
    FilePrep fp = new FilePrep();
    private Player Staff = null; //For remove mute optional input

    public List getInfractions(String suspect) throws IOException {
        File file = fp.getUserFile(suspect);
        return Files.readAllLines(file.toPath());
    }

    public void reloadMutes() throws IOException {
        File file = fp.getMuteFile();
        Scanner myReader = null;
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            myReader = new Scanner(file);
            List<?> lmutes;
            lmutes = Files.readAllLines(file.toPath());
            mutes = new String[lmutes.size()];
            mutes = (String[]) lmutes.toArray(mutes);
            Chat.mutes = mutes;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        myReader.close();
    }
    public void getFlaged() throws IOException { //Gets flaged players
        File file = fp.getFlagFile();
        Scanner myReader = null;
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            myReader = new Scanner(file);
            List<?> lmutes;
            lmutes = Files.readAllLines(file.toPath());
            flaged = new String[lmutes.size()];
            flaged  = (String[]) lmutes.toArray(flaged);
            Chat.flaged  = flaged ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        myReader.close();
    }

    public void mutePlayer(Player player) throws IOException {
        File file = fp.getMuteFile();
        final PrintWriter out = new PrintWriter(new FileWriter(file, true));
        try {
            out.write("\n" + player.getName() + "\n");
            out.close();
            this.reloadMutes();
        } catch (Exception e) {
            this.getLogger().info("[Warning] Issue writing file");
        }
    }
    public void flagPlayer(Player player) throws IOException {
        File file = fp.getFlagFile();
        final PrintWriter out = new PrintWriter(new FileWriter(file, true));
        try {
            out.write("\n" + player.getName() + "\n");
            out.close();
            this.reloadMutes();
        } catch (Exception e) {
            this.getLogger().info("[Warning] Issue writing file");
        }
    }

    public void unmutePlayer(String player, Player Staff) throws IOException {
        reloadMutes();

        if (Arrays.asList(Chat.mutes).contains(player)) {
            //Todo Move to notify
            Text message = Text.of(TextColors.RED, TranslatableText.builder(player + " is not muted"));
            Staff.sendMessage(message);

        } else {
            File file = fp.getMuteFile();
            FileWriter fw = new FileWriter(file);

            Arrays.asList(Chat.mutes).remove(player);
            for (int i = 0; i < Chat.mutes.length; ++i) {
                fw.write(Chat.mutes[i]);
            }
            if (Staff == null) {
                getLogger().info(player + " is no longer muted");
            } else {
                Text message = Text.of(TextColors.RED, TranslatableText.builder(player + " is un muted"));
                Staff.sendMessage(message);
            }
            this.reloadMutes();
        }

    }
    public void unFlagPlayer(String player, Player Staff) throws IOException {
        getFlaged();

        if (Arrays.asList(Chat.flaged).contains(player)) {
            Text message = Text.of(TextColors.RED, TranslatableText.builder(player + " is not Flagged"));
            Staff.sendMessage(message);

        } else {
            File file = fp.getFlagFile();
            FileWriter fw = new FileWriter(file);

            Arrays.asList(Chat.flaged).remove(player);
            for (int i = 0; i < Chat.flaged.length; ++i) {
                fw.write(Chat.flaged[i]);
            }
            if (Staff == null) {
                getLogger().info(player + " is no longer Flagged");
            } else {

                Text message = Text.of(TextColors.RED, TranslatableText.builder(player + " is un Flagged"));
                Staff.sendMessage(message);
            }
            this.reloadMutes();
        }

    }

    public void report(String player, String trigger, String warning, String Type, String reason) throws IOException {
        createfolder();
        File file = new File(getfile().getAbsoluteFile() + "/Warnings", player + ".yml");
        PrintWriter out = new PrintWriter(new FileWriter(file, true));
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                getLogger().info(player + "'s data file could not be created!");
                e1.printStackTrace();
            }
        } else {
            try {
                out.write("\n" + timeStamp + "[Trigger word]:" + trigger + " [Warning type]: " + Type + " [Message]: " + warning + " [Reason]: " + reason);
                out.close();
            } catch (Exception e2) {
                this.getLogger().info("[Warning] Issue writing file");
            }
        }
    }

    public void createfolder() {
        final File warningfolder = new File(getfile().getAbsoluteFile(), "Warnings");
        if (!warningfolder.exists()) {
            warningfolder.mkdir();
        }
    }

    public void logunmute(String player, String mute) throws IOException {
        final File file = new File(getfile().getAbsoluteFile(), "log.yml");
        final PrintWriter out = new PrintWriter(new FileWriter(file, true));
        final String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e1) {
                getLogger().info("[Chat filter]: log file error");
                e1.printStackTrace();
            }
        }
        try {
            out.write("\n" + timeStamp + " " + player + " unmute " + mute + "\n");
            out.close();

        } catch (Exception e) {
            this.getLogger().info("[Warning] Issue writing file");
        }
    }
}
