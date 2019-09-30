package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandFactory {
    COMMAND_FACTORY;
    private static final Logger LOGGER = LogManager.getLogger();

    public Command getCommand(String name) {
        Command command = EmptyCommand.getInstance();
        try {
            command = Commands.valueOf(name.toUpperCase()).getCommand();
        } catch (IllegalArgumentException | NullPointerException e) {
            LOGGER.error("Failed to created a command with a given name. Using default one");
        }
        return command;
    }
}
