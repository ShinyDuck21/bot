package org.shinybot.command.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import org.shinybot.Config;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;

import java.io.FileNotFoundException;
import java.util.List;

public class BanCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {
        TextChannel channel = ctx.getChannel();
        Message message = ctx.getMessage();
        Member member = ctx.getMember();
        List<String> args = ctx.getArgs();

        if (args.size() < 2 || message.getMentionedMembers().isEmpty()) {
            org.shinybot.utility.error.sendMissingArgsEmbed(channel, member);
            return;
        }

        final Member target = message.getMentionedMembers().get(0);
        final User user = member.getUser();

        if (!member.canInteract(target) || member.hasPermission(Permission.BAN_MEMBERS)) {
            channel.sendMessageFormat("Silly @%s you can't ban people", user.getName()).queue();
            return;
        }

        if (!target.hasPermission(Permission.BAN_MEMBERS)) {
            channel.sendMessageFormat("You can't ban this person");
            return;
        }

        final Member selfMember = ctx.getSelfMember();

        if (!selfMember.canInteract(target) || selfMember.hasPermission(Permission.BAN_MEMBERS)) {
            channel.sendMessageFormat("I am missing permissions").queue();
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
        }

        final String reason = String.join(" ", args.subList(1, args.size()));

        ctx.getGuild().ban(target, 1, reason).reason(reason).queue(
                (__) -> channel.sendMessage("User is banned").queue(),
                (error) -> channel.sendMessageFormat("Could not kick ", error.getMessage()).queue()
        );
    }

    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "Bans a user\n" +
                "Usage: "+ Config.getPrefix() + "@<user> <reason>";
    }
}
