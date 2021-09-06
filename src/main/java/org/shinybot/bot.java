package org.shinybot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.util.EnumSet;

public class bot {
    private bot() throws LoginException, FileNotFoundException {
        JDABuilder.createDefault(
                Config.getToken(),
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES
                )
                .setDisabledCacheFlags(EnumSet.of(
                        CacheFlag.CLIENT_STATUS,
                        CacheFlag.ACTIVITY,
                        CacheFlag.EMOTE,
                        CacheFlag.VOICE_STATE
                ))
                .addEventListeners(new Listener())
                .setActivity(Activity.playing("HALLO"))
                .build();
    }

    public static void main(String[] args) throws LoginException, FileNotFoundException {
        new bot();
    }
}
