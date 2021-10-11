package org.shinybot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.shinybot.utility.database.SQliteDataSource;

import javax.security.auth.login.LoginException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.EnumSet;

public class bot {
    public static void main(String[] args) throws LoginException, FileNotFoundException, SQLException {
        SQliteDataSource.getConnection();

        JDA jda = JDABuilder.createDefault(
                            Config.getToken(),
                            GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_PRESENCES,
                            GatewayIntent.GUILD_EMOJIS,
                            GatewayIntent.GUILD_VOICE_STATES
                        )
                            .setDisabledCacheFlags(EnumSet.of(
                                    CacheFlag.CLIENT_STATUS,
                                    CacheFlag.ACTIVITY,
                                    CacheFlag.EMOTE,
                                    CacheFlag.VOICE_STATE
                            ))
                        .addEventListeners(new Listener(), new AutoMod(), new Welcome())
                        .setActivity(Activity.watching("your mom"))
                        .build();
    }
}