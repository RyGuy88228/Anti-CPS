package us.ryguy.anticps.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> complete = new ArrayList<>();
        if(args.length == 1) {
            complete.add("getvalue");
            complete.add("setvalue");
            Collections.sort(complete);
            return complete;
        }else if(args.length == 2 && (args[0].equalsIgnoreCase("setvalue") || args[0].equalsIgnoreCase("getvalue"))) {
            complete.add("rightclick");
            complete.add("leftclick");
            Collections.sort(complete);
            return complete;
        }
        return null;
    }
}
