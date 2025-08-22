USE vox_populi;

-- CALL set_known_good_state();

SELECT * FROM users;

SELECT * FROM poll
INNER JOIN users u ON poll_author = user_id;

SELECT * FROM poll_options;

SELECT * FROM poll_votes;

SELECT u.username, p.poll_title, pv.poll_id, pv.option_number, po.option_name FROM poll_votes pv
INNER JOIN poll_options po ON po.poll_id = pv.poll_id
AND po.option_number = pv.option_number
INNER JOIN users u ON u.user_id = pv.user_id
INNER JOIN poll p ON p.poll_id = pv.poll_id
ORDER BY poll_id ASC, username;

