package org.shinybot.command.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.shinybot.Config;
import org.shinybot.command.CommandContext;
import org.shinybot.command.ICommand;
import org.shinybot.AutoMod;

import java.io.FileNotFoundException;
import java.util.List;

public class addBadWordCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {
        User user = ctx.getAuthor();
        Member member = ctx.getMember();
        List<String> args = ctx.getArgs();

        if (!member.hasPermission(Permission.MANAGE_PERMISSIONS)) {
            ctx.getChannel().sendMessage("<@" + user.getId() + "> can't ban words").queue();
            return;
        }

        AutoMod.badWords.add(args.get(0));
    }

    @Override
    public String getName() {
        return "addWord";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "Adds bad words\n" +
                "Usage: " + Config.getPrefix() + "addword <word>";
    }
}
