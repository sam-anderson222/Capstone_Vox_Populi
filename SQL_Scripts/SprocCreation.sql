USE vox_populi;

DROP PROCEDURE IF EXISTS get_poll_overviews;
DROP PROCEDURE IF EXISTS get_all_polls_with_options;
DROP PROCEDURE IF EXISTS get_results_for_poll;
DROP PROCEDURE IF EXISTS get_vote_with_option;
DROP PROCEDURE IF EXISTS delete_poll;

DELIMITER //

CREATE PROCEDURE get_poll_overviews()
BEGIN
	SELECT poll_id, poll_author, username, poll_title, poll_description FROM poll
    INNER JOIN users ON users.user_id = poll.poll_author;
	SELECT poll_id, COUNT(*) AS votes FROM poll_votes GROUP BY poll_id;
END //

CREATE PROCEDURE get_all_polls_with_options()
BEGIN
	SELECT * FROM poll;
    SELECT * FROM poll_options ORDER BY poll_id, option_number;
END //

CREATE PROCEDURE get_results_for_poll(IN pollId INT)
BEGIN
	SELECT  
    pv.option_number, 
    po.option_name,
    COUNT(*) AS number_votes
FROM poll_votes pv
INNER JOIN poll_options po
    ON pv.poll_id = po.poll_id 
   AND pv.option_number = po.option_number
WHERE pv.poll_id = pollId
GROUP BY pv.poll_id, pv.option_number, po.option_name
ORDER BY number_votes DESC;
END //

CREATE PROCEDURE get_vote_with_option(IN pollID INT, IN userID INT)
BEGIN
	SELECT pv.user_id, pv.poll_id, pv.option_number, option_name FROM poll_votes pv
	INNER JOIN poll_options po ON pv.poll_id = po.poll_id AND pv.option_number = po.option_number
	WHERE pv.poll_id = pollID AND pv.user_id = userID;
END //

CREATE PROCEDURE delete_poll(IN pollID INT)
BEGIN
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
	END;
    
    START TRANSACTION;
		DELETE FROM poll_votes WHERE poll_id = pollID;
        DELETE FROM poll_options WHERE poll_id = pollID;
        DELETE FROM poll WHERE poll_id = pollID;
	COMMIT;
END //