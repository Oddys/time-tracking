package org.oddys.timetracking.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum CommandFactory {
    COMMAND_FACTORY;
    Logger log = LogManager.getLogger();

    public Command getCommand(String name) {
        Command command = null;
        try {
            command = Command.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            // An attempt to create a non-existing command
        }
        return command;
    }
}
