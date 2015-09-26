CREATE TABLE gsysocial_db.upload (
id INT NOT NULL,
name VARCHAR(100) NOT NULL,
type VARCHAR(40),
size BIGINT(20),
content MEDIUMBLOB NOT NULL,
file_url VARCHAR(200),
PRIMARY KEY(id)
);


CREATE TABLE gsysocial_db.invite_request (
invite_id VARCHAR(100) NOT NULL,
inviter_email_id VARCHAR(100) NOT NULL,
invitee_email_id VARCHAR(100) NOT NULL,
invite_accepted BOOLEAN NOT NULL DEFAULT FALSE,
invite_rejected BOOLEAN NOT NULL DEFAULT FALSE,
joint_account_name VARCHAR(100),
PRIMARY KEY(invite_id)
);

CREATE TABLE gsysocial_db.joint_account(
joint_account_id VARCHAR(100) NOT NULL,
joint_account_name VARCHAR(100) NOT NULL,
first_email_id VARCHAR(100) NOT NULL,
first_user_name VARCHAR(100) NOT NULL,
second_email_id VARCHAR(100) NOT NULL,
second_user_name VARCHAR(100) NOT NULL,
joint_account_story TEXT NULL,
PRIMARY KEY(joint_account_id),
UNIQUE(first_email_id),
UNIQUE(second_email_id)
);

ALTER TABLE `gsysocial_db`.`user` 
DROP INDEX `user_name` ;

