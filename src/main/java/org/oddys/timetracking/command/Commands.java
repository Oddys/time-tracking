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
    },

    ADD_ACTIVITY_RECORD {
        {
            command = AddActivityRecordCommand.getInstance();
        }
    },

    FORWARD {
        {
            command = ForwardCommand.getInstance();
        }
    },

    SHOW_ACTIVITIES {
        {
            command = ShowActivitiesCommand.getInstance();
        }
    },

    ASSIGN_ACTIVITY {
        {
            command = AssignActivityCommand.getInstance();
        }
    },

    STOP_ACTIVITY {
        {
            command = StopActivityCommand.getInstance();
        }
    },

    SHOW_ACTIVITY_REQUESTS {
        {
            command = ShowUserActivityRequestsCommand.getInstance();
        }
    },

    CHANGE_ACTIVITY_STATUS {
        {
            command = ChangeUserActivityStatusCommand.getInstance();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    };
}
