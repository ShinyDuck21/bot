package org.shinybot.utility;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.shinybot.command.ICommand;

public class DiscordLogger {
    public static void sendLogMessage(GuildMessageReceivedEvent event, Guild g, ICommand currentCommand, Member... target) {
        TextChannel logChannel = g.getTextChannelById(895820195153707018l);

        Member author = event.getMember();
        User authorUser = author.getUser();

        EmbedBuilder builder = new EmbedBuilder().setTitle("LOG: " + currentCommand.getName())
                .setDescription("The Command " + currentCommand.getName() + " was used. Info on the command: ")
                .setColor(author.getColor())
                .addField("Author:",  "<@" + author.getId() +  ">", false)
                .setFooter("LOGS");

        if (!(target == null)) {
            builder.addField("Target:", "<@" + author.getId() + ">", false);
        }

        logChannel.sendMessage(builder.build()).queue();
    }
}