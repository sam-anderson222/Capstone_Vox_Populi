USE vox_populi;

DROP TABLE IF EXISTS poll_votes;
DROP TABLE IF EXISTS poll_options;
DROP TABLE IF EXISTS poll;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS access_levels;

CREATE TABLE access_levels (
	access_level_id TINYINT NOT NULL,
    access_level_title VARCHAR(30) NOT NULL,
    PRIMARY KEY (access_level_id)
);

CREATE TABLE users (
	user_id INT NOT NULL auto_increment,
    username Varchar(30) NOT NULL UNIQUE,
    user_password Varchar(30) NOT NULL,
    access_level TINYINT NOT NULL DEFAULT 2,
    PRIMARY KEY(user_id),
	FOREIGN KEY (access_level) REFERENCES access_levels(access_level_id)
);

CREATE TABLE poll (
	poll_id INT NOT NULL auto_increment,
    poll_author INT NOT NULL,
    poll_title VARCHAR(100) NOT NULL,
    poll_description VARCHAR(200),
    PRIMARY KEY (poll_id),
    FOREIGN KEY (poll_author) REFERENCES users(user_id)
);

CREATE TABLE poll_options (
	poll_id INT NOT NULL,
    option_number TINYINT NOT NULL,
    option_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (poll_id, option_number),
    UNIQUE (poll_id, option_name),
    FOREIGN KEY (poll_id) REFERENCES poll(poll_id)
);

CREATE TABLE poll_votes (
	user_id INT NOT NULL,
    poll_id INT NOT NULL,
    option_number TINYINT NOT NULL,
    UNIQUE (user_id, poll_id),
    PRIMARY KEY (user_id, poll_id, option_number),
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY(poll_id, option_number) REFERENCES poll_options(poll_id, option_number)
);