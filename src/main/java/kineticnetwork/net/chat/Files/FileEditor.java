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
    public void removeMutesConsole(final String suspect) throws IOException {
        final FileWriter fw = new FileWriter(Chat.mutesFile);
        final BufferedWriter bw = new BufferedWriter(fw);
        try {
            final List<?> inputa = Files.readAllLines(Chat.mutesFile.toPath());
            inputa.remove(suspect);
            for (int i = 0; i < inputa.size(); ++i) {
                bw.write(inputa.get(i).toString());
            }
        }
        catch (Exception e) {
            getLogger().info("[Warning] Issue editing file in unmute");
        }
        this.getLogger().info(suspect + " is no longer muted");
        this.Readmute();
    }

    public List displayInfractions( File file) throws FileNotFoundException {
        final Scanner myReader = new Scanner(file);
        List<?> infraction = null;
        try {
            infraction = Files.readAllLines(file.toPath());
            myReader.close();
        }
        catch (Exception e) {
            getLogger().info("No record of player");
        }
        return infraction;
    }

    public void Readmute() {
        final File file = new File(getfile().getAbsoluteFile(), "Active-mutes.yml");
        Scanner myReader = null;
        try {
            file.createNewFile();
        }
        catch (IOException e3) {
            getLogger().info("Error in creating active mute file");
        }
        try {
            myReader = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception e2) {
                getLogger().info("Error in creating active mute file");
                e2.printStackTrace();
            }
        }
        else {
            try {
                List<?> lmutes;
                lmutes = Files.readAllLines(file.toPath());
                mutes = new String[lmutes.size()];
                mutes = (String[]) lmutes.toArray(mutes);
                Chat.mutes=mutes;
                //Todo change how mutes are stored
            }
            catch (Exception b) {
                this.getLogger().info("[Chat-Filter] " + b.getStackTrace());
            }
        }
        myReader.close();
    }

    public void addmute(final Player player) throws IOException {
        final File file = new File(getfile().getAbsoluteFile(), "Active-mutes.yml");
        final PrintWriter out = new PrintWriter(new FileWriter(file, true));
        try {
            out.write("\n" + player.getName() + "\n");
            out.close();
            this.Readmute();
        }
        catch (Exception e) {
            this.getLogger().info("[Warning] Issue writing file");
        }
    }

    public void unmute(final String player, final Player sender) throws IOException {
        Readmute();
       // Chat.getLogger().info(lmutes.size()+" List size");

        if(Arrays.asList(Chat.mutes).contains(player)){
            //Todo Move to notify
                Text message = Text.of(TextColors.RED, TranslatableText.builder(player+" is not muted"));
                sender.sendMessage(message);


            }else {
                final File file = new File(getfile().getAbsoluteFile(), "Active-mutes.yml");
                final FileWriter fw = new FileWriter(file);
                final BufferedWriter bw = new BufferedWriter(fw);

                 Arrays.asList(Chat.mutes).remove(player);
            for (int i = 0; i < Chat.mutes.length; ++i) {
               // Chat.getLogger().info(lmutes.get(i)+" List index ");
                fw.write(Chat.mutes[i]);

            }
            //Todo Move to notify
            Text message = Text.of(TextColors.RED, TranslatableText.builder(player+" is un muted"));
            sender.sendMessage(message);
            getLogger().info( player + " is no longer muted");
                this.Readmute();}

    }

    public void report(final String player, final String trigger, final String warning, final String Type, String reason) throws IOException {
        createfolder();
        final File file = new File(getfile().getAbsoluteFile() + "/Warnings", player + ".yml");
        final PrintWriter out = new PrintWriter(new FileWriter(file, true));
        final String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e1) {
                getLogger().info(player + "'s data file could not be created!");
                e1.printStackTrace();
            }
        }
        else {
            try {
                out.write("\n" + timeStamp + "[Trigger word]:" + trigger + " [Warning type]: " + Type + " [Message]: " + warning+" [Reason]: " + reason);
                out.close();
            }
            catch (Exception e2) {
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
            }
            catch (IOException e1) {
                getLogger().info("[Chat filter]: log file error");
                e1.printStackTrace();
            }}
        try {
            out.write("\n"+timeStamp+" "+ player +" unmute "+mute+ "\n");
            out.close();

        }
        catch (Exception e) {
            this.getLogger().info("[Warning] Issue writing file");
        }
    }
}
