package org.shinybot.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.shinybot.Config;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;

import java.io.FileNotFoundException;

public class YoutubeCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {
        Member member = ctx.getMember();
        EmbedBuilder builder = new EmbedBuilder().setTitle("Youtube").setColor(member.getColor())
                .setThumbnail("https://logos-world.net/wp-content/uploads/2020/04/YouTube-Emblem.png")
                .setDescription("If you haven't subed to sith gamer. Do so now.")
                .addField("Link:", "https://www.youtube.com/channel/UCh8W5OtX7HtarcwBtVToQdQ/sub_confirmation", true);

        ctx.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "youtube";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "Displays Sith Gamers Youtube\n" +
                "Usage: " + Config.getPrefix() + "youtube";
    }
}
