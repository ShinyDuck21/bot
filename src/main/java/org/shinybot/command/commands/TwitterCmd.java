package org.shinybot.command.commands;

import org.shinybot.Config;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;

import java.io.FileNotFoundException;

public class TwitterCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {

    }

    @Override
    public String getName() {
        return "twitter";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "Returns Sith Gamers Twitter\n" +
                "Usage: " + Config.getPrefix() + "twitter";
    }
}
