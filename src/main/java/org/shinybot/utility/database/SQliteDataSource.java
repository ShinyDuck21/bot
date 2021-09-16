package org.shinybot.utility.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQliteDataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(SQliteDataSource.class);
    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        try {
            final File dbfile = new File("database.db");

            if (!dbfile.exists()) {
                if (dbfile.createNewFile()) {
                    LOGGER.info("CREATED DATABASE FILE");
                } else {
                    LOGGER.warn("COULD NOT CREATE DATABASE FILE");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        config.setJdbcUrl("jdbc:sqlite:database.db");
        config.setConnectionTestQuery("SELECT 1");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);

        try (final Statement statement = getConnection().createStatement()) {

            // language=SQLite
            statement.execute("CREATE TABLE IF NOT EXISTS guild_settings (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "guild_id VARCHAR(20) NOT NULL," +
                    "prefix VARCHAR(255) NOT NULL DEFAULT '!'" +
                    ");");

            LOGGER.info("TABLE INITIALISED");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private SQliteDataSource() {

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
