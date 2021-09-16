package org.shinybot.command.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import org.shinybot.Config;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;

import java.io.FileNotFoundException;

public class TwitterCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {
        Member member = ctx.getMember();

        EmbedBuilder builder = new EmbedBuilder().setTitle("Twitter").setColor(member.getColor())
                .setThumbnail("https://logos-world.net/wp-content/uploads/2020/04/Twitter-Logo.png")
                .setDescription("If you haven't followed sith gamer on twitter")
                .addField("Link: ", "https://twitter.com/GamerSith", true);

        ctx.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "twitter";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "Returns Sith Gamers Twitter\n" +
                "Usage: " + Config.getPrefix() + "twitter";
    }
}
