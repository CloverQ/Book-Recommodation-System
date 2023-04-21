CREATE DATABASE IF NOT EXISTS book;
USE book;
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
	`user_id` INT UNSIGNED AUTO_INCREMENT,
	`user_name` VARCHAR(50) NOT NULL,
	`password` VARCHAR(50) NOT NULL,
	`user_role` VARCHAR(50) NOT NULL,
	`DOB` DATE,
	`nick_name` VARCHAR(50),
	`email` VARCHAR(100),
	`gender` VARCHAR(20),
	`avatar` VARCHAR(100),
	PRIMARY KEY (`user_id`),
	UNIQUE KEY (`user_name`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'user table';

DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
	`book_id` INT UNSIGNED AUTO_INCREMENT,
	`book_title` VARCHAR(200) NOT NULL,
	`img_url` VARCHAR(200) NOT NULL,
	`book_isbn` VARCHAR(40) NOT NULL,
	`book_author` VARCHAR(200) NOT NULL,
	`category` VARCHAR(200) NOT NULL,		
	`book_rating` DECIMAL(2,1) NOT NULL,
	`publication_date` DATE NOT NULL,
	`publisher` VARCHAR(200) NOT NULL,
	`book_desc` VARCHAR(10000) NOT NULL,
	`total_readers` INT default 0,
	PRIMARY KEY (`book_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'book table';

DROP TABLE IF EXISTS `author`;
CREATE TABLE IF NOT EXISTS `author` (
	`author_id` INT UNSIGNED AUTO_INCREMENT,
	`author_name` VARCHAR(200) NOT NULL,
	PRIMARY KEY (`author_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'author table';

DROP TABLE IF EXISTS `book2author`;
CREATE TABLE IF NOT EXISTS `book2author` (
	`author_id` INT NOT NULL,
	`book_id` INT NOT NULL,
	KEY (`author_id`),
	KEY (`book_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'book table';

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
	`category_id` INT UNSIGNED AUTO_INCREMENT,
	`category_name` VARCHAR(40) NOT NULL,
	PRIMARY KEY (`category_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'category table';

DROP TABLE IF EXISTS `book2category`;
CREATE TABLE IF NOT EXISTS `book2category` (
	`book_id` INT UNSIGNED NOT NULL,
	`category_id` INT UNSIGNED NOT NULL,
	KEY (`book_id`),
	KEY (`category_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'Intermediate table for book and category';

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
	`comment_id` INT UNSIGNED AUTO_INCREMENT,
	`book_id` INT NOT NULL,
	`user_id` INT NOT NULL,
	`user_name` VARCHAR(50) NOT NULL,
	`content` VARCHAR(500) NOT NULL,
	PRIMARY KEY (`comment_id`),
	KEY (`book_id`),
	KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'comments table';

DROP TABLE IF EXISTS `user2favorite`;
CREATE TABLE IF NOT EXISTS `user2favorite` (
	`user_id` INT NOT NULL,
	`book_id` INT NOT NULL,
	`book_title` VARCHAR(200) NOT NULL,
	`img_url` VARCHAR(200) NOT NULL,
	`create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
	KEY (`book_id`),
	KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT 'favorite table';



select * from user2favorite uf, users u where uf.user_id=u.user_id and u.user_id=2