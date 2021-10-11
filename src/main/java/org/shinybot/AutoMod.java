package org.shinybot;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AutoMod extends ListenerAdapter {
    public static ArrayList<String> badWords = new ArrayList<String>();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        super.onGuildMessageReceived(event);
        badWords.add("nigger");
        badWords.add("nigga");
        badWords.add("bitch");
        try {
            User user = event.getAuthor();

            String raw = event.getMessage().getContentRaw().toLowerCase();

            for (String badWord : badWords) {
                if (raw.contains(badWord)) {
                    event.getMessage().delete().queue();
                    event.getChannel().sendMessage("You can't say that <@" + user.getId() + ">" + ". Please be respectful in our community.").queue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
