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
    },

    FIND_USER {
        {
            command = FindUserCommand.getInstance();
        }
    },

    SHOW_USER_ACTIVITIES {
        {
            command = ShowUserActivitiesCommand.getInstance();
        }
    },

    SHOW_ACTIVITY_RECORDS {
        {
            command = ShowActivityRecordsCommand.getInstance();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    };
}
