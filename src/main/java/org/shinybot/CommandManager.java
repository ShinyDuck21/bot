package org.shinybot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.Nullable;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;
import org.shinybot.command.commands.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommand> commands = new ArrayList<ICommand>();

    public CommandManager() {
        addCommand(new HelpCmd(this));
        addCommand(new PingCmd());
        addCommand(new KickCommand());
        addCommand(new TwitchCmd());
        addCommand(new YoutubeCmd());
        addCommand(new BanCmd());
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) {
            throw new IllegalArgumentException("A command with this name is already present");
        }

        commands.add(cmd);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand command : this.commands) {
            if (command.getName().equalsIgnoreCase(searchLower) || command.getAliases().contains(searchLower)) {
                return command;
            }
        }

        return null;
    }

    void handle(GuildMessageReceivedEvent event) throws FileNotFoundException {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(Config.getPrefix()), "")
                .split("\\s+");
        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if (cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }
}
