package in.bhargavrao.stackoverflow.natty.commandlists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.tunaki.stackoverflow.chat.Message;
import fr.tunaki.stackoverflow.chat.Room;
import fr.tunaki.stackoverflow.chat.event.PingMessageEvent;
import in.bhargavrao.stackoverflow.natty.commands.Alive;
import in.bhargavrao.stackoverflow.natty.commands.Check;
import in.bhargavrao.stackoverflow.natty.commands.Commands;
import in.bhargavrao.stackoverflow.natty.commands.Help;
import in.bhargavrao.stackoverflow.natty.commands.Hi;
import in.bhargavrao.stackoverflow.natty.commands.OptIn;
import in.bhargavrao.stackoverflow.natty.commands.OptOut;
import in.bhargavrao.stackoverflow.natty.commands.SpecialCommand;
import in.bhargavrao.stackoverflow.natty.commands.Status;
import in.bhargavrao.stackoverflow.natty.services.RunnerService;
import in.bhargavrao.stackoverflow.natty.utils.CheckUtils;

/**
 * Created by bhargav.h on 28-Oct-16.
 */
public class GMTsCommandsList {
    public void mention(Room room, PingMessageEvent event, RunnerService service, String sitename, String siteurl, boolean isReply){

        if(CheckUtils.checkIfUserIsBlacklisted(event.getUserId()))
            return;

        Message message = event.getMessage();
        List<SpecialCommand> commands = new ArrayList<>(Arrays.asList(
            new Alive(message),
            new Check(message, sitename, siteurl),
            new Hi(message, event.getUserId()),
            new Help(message),
            new OptIn(message),
            new OptOut(message),
            new Status(message)
        ));
        commands.add(new Commands(message,commands));
        for(SpecialCommand command: commands){
            if(command.validate()){
                command.execute(room);
            }
        }
        System.out.println(event.getMessage().getContent());
    }
}
