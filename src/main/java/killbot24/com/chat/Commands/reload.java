package killbot24.com.chat.Commands;

import killbot24.com.chat.*;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

import java.util.List;
import java.util.function.Function;

/**
 * Created by tjbur on 10/06/2020.
 */
public class reload  extends Chat implements CommandExecutor {
        Logic log=new Logic();
        data dat= new data();
    private String[] Blocked;
    @Override
    public CommandResult execute(CommandSource commandSource, CommandContext commandContext) throws CommandException {
        Boolean bla=true;// to stop error
        config.load();




        String message= config.getNode("Mute").getValue().toString();
       // getLogger().info(message);
        dat.setMutemessage(message);

        //  this.logger.info(Blocked[0].toString()+" What ever array is. and lenght "+Blocked.length);

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
        Chat.Whitelist = dd.split(delimiter);
        datastorage store = new datastorage();
        store.Readmute();
        getLogger().info("Reloaded");
        return CommandResult.success();

    }
}
