package org.shinybot;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Welcome extends ListenerAdapter {
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        super.onGuildMemberJoin(event);
        TextChannel welcomeChannel = event.getGuild().getTextChannelById(896924960155664494l);
        Color embedColor = new Color(255, 243, 0);

        EmbedBuilder builder = new EmbedBuilder()
                .setTitle("WELCOME <@" + event.getUser().getId() + ">")
                .setImage(event.getUser().getAvatarUrl())
                .setColor(embedColor)
                .setDescription("Welcome <@" + event.getUser().getId() + "> to the Shiny Duck Pond. Please follow the rules in <#896521520011173898>");

        welcomeChannel.sendMessage(builder.build()).queue();
    }
}
