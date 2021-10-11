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

public class removeBadWordCmd implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws FileNotFoundException {
        Member member = ctx.getMember();
        User user = ctx.getAuthor();
        List<String> args = ctx.getArgs();

        if (!member.hasPermission(Permission.MANAGE_PERMISSIONS)) {
            ctx.getChannel().sendMessage("You can't remove words from the filter <@" + user.getId() + ">").queue();
            return;
        }

        AutoMod.badWords.remove(args.get(0));
    }

    @Override
    public String getName() {
        return "removeword";
    }

    @Override
    public String getHelp() throws FileNotFoundException {
        return "Removes a word from the filter\n" +
                "Usage " + Config.getPrefix() + "removeword <word>";
    }
}
