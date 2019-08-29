package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandFactory {
    COMMAND_FACTORY;
    private static final Logger log = LogManager.getLogger();

    public Command getCommand(String name) {
        Command command = null;
        try {
            command = Command.valueOf(name.toUpperCase());
            log.info(command + " command is created");
        } catch (IllegalArgumentException e) {
            log.error("Failed to created a command with name '" + name + "'");
        }
        return command;
    }
}
