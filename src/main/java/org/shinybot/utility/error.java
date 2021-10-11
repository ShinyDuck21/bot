package org.shinybot.utility;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.shinybot.command.ICommand;

import java.io.FileNotFoundException;

public class error {
    public static void sendMissingArgsEmbed(TextChannel channel, Member author, ICommand currnetCmd) throws FileNotFoundException {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("MISSING ARGUMENTS").setColor(author.getColor()).setDescription(currnetCmd.getHelp())
                .setFooter("ERROR");

        channel.sendMessage(embed.build()).queue();
    }
}
