package org.oddys.timetracking.command;

public enum Commands {
    LOGIN {
        {
            command = LoginCommand.getInstance();
        }
    },

    LOGOUT {
        {
            command = LogoutCommand.getInstance();
        }
    },

    CHANGE_LANG {
        {
            command = ChangeLangCommand.getInstance();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    };
}
