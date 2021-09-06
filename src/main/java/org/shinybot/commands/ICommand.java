package org.shinybot.commands;

import java.util.List;

public interface ICommand {
    void handle();
    String getName();
    default List<String> getAliases() {
        return List.of();
    }
}