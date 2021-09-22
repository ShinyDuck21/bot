package org.shinybot.command;

import me.duncte123.botcommons.commands.ICommandContext;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class CommandContext implements ICommandContext {
    private final GuildMessageReceivedEvent event;
    private final List<String> args;
    private final Member authorMember = this.getEvent().getMember();
    private final User authorUser = this.getEvent().getAuthor();
    private final List<Role> authorRoles = authorMember.getRoles();

    public CommandContext(GuildMessageReceivedEvent event, List<String> args) {
        this.event = event;
        this.args = args;
    }

    @Override
    public Guild getGuild() {
        return this.getEvent().getGuild();
    }

    @Override
    public GuildMessageReceivedEvent getEvent() {
        return this.event;
    }

    public List<String> getArgs() {
        return this.args;
    }

    public List<Role> getAuthorRoles() {
        return this.authorRoles;
    }

    public Member getAuthorMember() {
        return this.authorMember;
    }

    public User getAuthorUser() {
        return this.authorUser;
    }
}