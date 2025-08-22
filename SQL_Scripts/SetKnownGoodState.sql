use vox_populi;

DROP PROCEDURE IF EXISTS set_known_good_state;
DELIMITER //

CREATE PROCEDURE set_known_good_state()
BEGIN
	DELETE FROM poll_votes;
    DELETE FROM poll_options;
	DELETE FROM poll;
    DELETE FROM users;
    DELETE FROM access_levels;
    
    
    ALTER TABLE poll AUTO_INCREMENT = 1;
    ALTER TABLE users AUTO_INCREMENT = 1;
    
    INSERT INTO access_levels (access_level_id, access_level_title) VALUES
    (1, "Admin"),
    (2, "User");
    
	INSERT INTO users (username, user_password, access_level) VALUES 
	("TestUserA", "TestPassword1", 2),
	("TestUserB", "TestPassword2", 2),
	("TestUserC", "TestPassword3", 2),
	("TestUserD", "TestPassword4", 2),
	("TestUserE", "TestPassword5", 2),
	("TestUserF", "TestPassword6", 2),
	("TestUserG", "TestPassword7", 2),
	("TestUserH", "TestPassword8", 2),
	("TestUserI", "TestPassword9", 2),
	("TestUserJ", "TestPassword10", 2),
	("TestUserK", "TestPassword11", 2),
	("TestUserL", "TestPassword12", 2),
	("TestUserM", "TestPassword13", 2),
	("TestUserN", "TestPassword14", 2),
	("TestUserO", "TestPassword15", 2),
	("TestUserP", "TestPassword16", 2),
	("TestUserQ", "TestPassword17", 2),
    ("TestAdmin", "Admin", 1);

	INSERT INTO poll (poll_author, poll_title, poll_description) VALUES
	(1, "Coke Or Pepsi?", "The age old question: Coke Vs. Pepsi?"),
	(2, "Which Season Do You Think is Best?", "Cold or Hot? Which season of the year do you like the most?"),
	(3, "Favorite Genre of Music?", "Pick which genre of music you like the most!"),
	(4, "Best Soda Brand?", "Which soft drink brand do you like more?"),
    (10, "Test Poll", "This is a sample poll that starts with only 3 votes.");

	INSERT INTO poll_options (poll_id, option_number, option_name) VALUES
	(1, 1, "Coke"),
	(1, 2, "Pepsi"),
	(2, 1, "Winter"),
	(2, 2, "Spring"),
	(2, 3, "Summer"),
	(2, 4, "Autumn"),
	(3, 1, "Rock"),
	(3, 2, "Pop"),
	(3, 3, "Rap"),
	(3, 4, "Classical"),
	(3, 5, "Jazz"),
	(3, 6, "R&B"),
	(3, 7, "Country"),
	(4, 1, "Coke"), -- Notice how coke can be used as an option across different polls, but can't be used twice or more in the same poll.
	(4, 2, "Pepsi"),
	(4, 3, "Dr.Pepper"),
	(4, 4, "Sprite"),
	(4, 5, "Fanta"),
    (5, 1, "Left"),
    (5, 2, "Middle"),
    (5, 3, "Right");

	INSERT INTO poll_votes(user_id, poll_id, option_number) VALUES
	(1, 1, 1),
	(2, 1, 2),
	(3, 1, 2),
	(4, 1, 2),
	(5, 1, 1),
	(6, 1, 1),
	(7, 1, 1),
	(8, 1, 1),
    (9, 1, 1),
	(10, 1, 2),
    (11, 1, 2),
	(12, 1, 2),
    (13, 1, 1),
	(14, 1, 1),
    (15, 1, 2),
    (16, 1, 2),
	(17, 1, 1),
	(1, 2, 1),
	(2, 2, 3),
	(3, 2, 4),
	(4, 2, 2),
	(5, 2, 2),
	(6, 2, 3),
	(7, 2, 3),
	(8, 2, 1),
    (9, 2, 4),
	(10, 2, 4),
    (11, 2, 4),
	(12, 2, 2),
    (13, 2, 4),
	(14, 2, 1),
    (15, 2, 3),
    (16, 2, 4),
	(17, 2, 2),
	(1, 3, 1),
	(2, 3, 4),
	(3, 3, 7),
	(4, 3, 2),
	(5, 3, 1),
	(6, 3, 1),
	(7, 3, 5),
	(8, 3, 6),
    (9, 3, 5),
	(10, 3, 5),
    (11, 3, 1),
	(12, 3, 2),
    (13, 3, 2),
	(14, 3, 1),
    (15, 3, 3),
    (16, 3, 3),
	(17, 3, 2),
	(1, 4, 1),
	(2, 4, 2),
	(3, 4, 3),
	(4, 4, 3),
	(5, 4, 3),
	(6, 4, 5),
	(7, 4, 4),
	(8, 4, 1),
    (9, 4, 3),
	(10, 4, 3),
    (11, 4, 1),
	(12, 4, 2),
    (13, 4, 4),
	(14, 4, 4),
    (15, 4, 5),
    (16, 4, 1),
	(17, 4, 3),
    (10, 5, 1),
    (11, 5, 1),
    (12, 5, 3);
    
END //
