
CREATE TABLE IF NOT EXISTS user_login_data (
    UserId INTEGER PRIMARY KEY AUTOINCREMENT,
    LoginName Varchar(32),
    PasswordHash Varchar(256),
    PasswordSalt Varchar(16)
);

CREATE TABLE IF NOT EXISTS user_personal_data (
    UserID INTEGER PRIMARY KEY,
    Age INTEGER,
    [Weight] INTEGER,
    [Height] INTEGER,
    FOREIGN KEY (UserID) REFERENCES user_login_data(UserID)
);

CREATE TABLE IF NOT EXISTS workout (
    id INTEGER PRIMARY KEY NOT NULL,
    title VARCHAR(128),
    [desc] TEXT,
    [type] VARCHAR(32),
    body_part VARCHAR(32),
    equipment VARCHAR(32),
    [level] VARCHAR(32),
    rating REAL,
    rating_desc VARCHAR(32)
);

CREATE TABLE IF NOT EXISTS previous_workouts (
    UserID INTEGER,
    WorkoutID INTEGER,
    FOREIGN KEY (UserID) REFERENCES user_login_data(UserID),
    FOREIGN KEY (WorkoutID) REFERENCES workout(id),
    PRIMARY KEY (UserID, WorkoutID)
);
