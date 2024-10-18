
CREATE TABLE user_login_data(
    UserId INTEGER PRIMARY KEY AUTOINCREMENT,
    LoginName Varchar(32),
    PasswordHash Varchar(256),
    PasswordSalt Varchar(16),
    CONSTRAINT unique_user UNIQUE (UserId)
);

CREATE TABLE workout (
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
