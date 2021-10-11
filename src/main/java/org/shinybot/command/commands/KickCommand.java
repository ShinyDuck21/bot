package org.shinybot.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.shinybot.Config;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;
import org.shinybot.utility.DiscordLogger;

import java.io.FileNotFoundException;
import java.util.List;

public class KickCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {
        TextChannel channel = ctx.getChannel();
        Message message = ctx.getMessage();
        Member member = ctx.getMember();
        List<String> args = ctx.getArgs();

        if (args.size() < 2 || message.getMentionedMembers().isEmpty()) {
            org.shinybot.utility.error.sendMissingArgsEmbed(channel, member, new KickCommand());
            return;
        }

        final Member target = message.getMentionedMembers().get(0);
        final User user = member.getUser();

        if (!member.canInteract(target) || member.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessageFormat("Silly @%s you can't kick people", user.getName()).queue();
            return;
        }

        final Member selfMember = ctx.getSelfMember();

        if (!selfMember.canInteract(target) || selfMember.hasPermission(Permission.KICK_MEMBERS)) {
            channel.sendMessageFormat("I am missing permissions ask the owner to give my role permissions", user.getName()).queue();
            return;
        }

        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg);
        }

        final String reason = String.join(" ", args.subList(1, args.size()));

        ctx.getGuild().kick(target, reason).reason(reason).queue(
                (__) -> channel.sendMessage("User is kicked").queue(),
                (error) -> channel.sendMessageFormat("Could not kick ", error.getMessage()).queue()
        );

        DiscordLogger.sendLogMessage(ctx.getEvent(), ctx.getGuild(), new KickCommand(), target);
    }

    @Override
    public String getName() {
        return "kick";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "ADMIN COMMAND\n" +
                "Usage: " + Config.getPrefix() + "kick <@user> <reason>";
    }
}
