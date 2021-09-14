package org.shinybot.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.shinybot.Config;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;

import java.io.FileNotFoundException;

public class TwitchCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {
        EmbedBuilder builder = new EmbedBuilder();

        Member member = ctx.getMember();

        builder.setTitle("Twitch").setColor(member.getColor()).setDescription("Why Don't you follow Sith Gamer on Twitch")
                .addField("Link", "https://www.twitch.tv/sith_gameryt?ab_channel=sith_gameryt", true)
                .setThumbnail("https://toppng.com/uploads/preview/vk-share-button-twitch-logo-png-round-115632587509jpncx0qwu.png");

        ctx.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "twitch";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "Shows Sith_Gamers Twitch\n" +
                "Usage: "+ Config.getPrefix() + "twitch";
    }
}
