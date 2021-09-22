package org.shinybot.command.commands;

import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;

import java.io.FileNotFoundException;

public class ConsoleCmd implements ICommand {

    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {

    }

    @Override
    public String getName() {
        return "console";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "This is an Admin Command";
    }
}
