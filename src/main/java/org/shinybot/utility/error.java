package org.shinybot.utility;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class error {
    public static void sendMissingArgsEmbed(TextChannel channel, Member author) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("ARGS").setColor(author.getColor()).setDescription("MISSING ARGUMENTS").setFooter("ERROR");

        channel.sendMessage(embed.build()).queue();
    }
}
