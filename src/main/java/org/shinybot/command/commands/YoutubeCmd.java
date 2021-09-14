package org.shinybot.command.commands;

import org.shinybot.Config;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;

import java.io.FileNotFoundException;

public class YoutubeCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {

    }

    @Override
    public String getName() {
        return "youtube";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "Displays Sith Gamers Youtube\n" +
                "Usage: " + Config.getPrefix() + "youtube";
    }
}
