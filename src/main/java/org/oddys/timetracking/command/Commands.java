package org.oddys.timetracking.command;

public enum Commands {
    SIGNIN {
        {
            command = SignInCommand.getInstance();
        }
    },

    SIGNOUT {
        {
            command = SignOutCommand.getInstance();
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

    SHOW_ACTIVITIES {
        {
            command = ShowActivitiesCommand.getInstance();
        }
    },

    REQUEST_ACTIVITY {
        {
            command = RequestActivityCommand.getInstance();
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
    },

    ADD_USER {
        {
            command = AddUserCommand.getInstance();
        }
    },

    ADD_ACTIVITY {
        {
            command = AddActivityCommand.getInstance();
        }
    };

    Command command;

    public Command getCommand() {
        return command;
    };
}
