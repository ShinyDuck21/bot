package org.shinybot.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.shinybot.CommandManager;
import org.shinybot.Config;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;

import java.io.FileNotFoundException;
import java.util.EnumMap;
import java.util.List;

public class HelpCmd implements ICommand {
    private CommandManager manager = null;

    public HelpCmd(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {
        int i = 0;
        if (i == 1) {
            List<String> args = ctx.getArgs();
            TextChannel channel = ctx.getChannel();
            Member member = ctx.getMember();

            if (args.isEmpty()) {
                EmbedBuilder builder = new EmbedBuilder()
                        .setTitle("List of Commands")
                        .setColor(member.getColor());

                StringBuilder desc = builder.getDescriptionBuilder();
                manager.getCommands().forEach(ICommand -> {
                    desc.append("'").append(ICommand.getName()).append("'\n");
                });

                channel.sendMessage(builder.build()).queue();
                return;
            }

            String search = args.get(0);
            ICommand cmd = manager.getCommand(search);
            if (cmd == null) {
                channel.sendMessage("Nothing found for " + search).queue();
                return;
            }

            EmbedBuilder builder = new EmbedBuilder()
                    .setTitle(cmd.getName())
                    .setDescription(cmd.getHelp())
                    .setColor(member.getColor());

            channel.sendMessage(builder.build()).queue();
            i++;
        }
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "Shows the list of commands in the bot\n" +
                "Usage: " + Config.getPrefix() + "help [command]";
    }

    @Override
    public List<String> getAliases() {
        return List.of("commands", "cmds", "commandlist");
    }
}
