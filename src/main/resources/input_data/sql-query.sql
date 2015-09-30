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

alter table gsysocial_db.user add follow_count int Default 0 NOT NULL;

CREATE TABLE gsysocial_db.follower (
follow_id VARCHAR(100) NOT NULL,
email_id VARCHAR(100) NOT NULL,
joint_account_id VARCHAR(100) NOT NULL,
PRIMARY KEY(follow_id)
);
alter table gsysocial_db.follower ADD UNIQUE unique_constraint(email_id,joint_account_id);


CREATE TABLE gsysocial_db.post (
post_id VARCHAR(100) NOT NULL,
joint_account_id VARCHAR(100) NOT NULL,
file_url VARCHAR(200) NULL,
file_type VARCHAR(100) NULL,
post_text TEXT NOT NULL,
total_rating BIGINT(20) NOT NULL DEFAULT 0,
no_of_ratings INT NOT NULL DEFAULT 0,
average_rating FLOAT NOT NULL DEFAULT 0,
last_updated_time DATETIME NOT NULL,
PRIMARY KEY(post_id)
);