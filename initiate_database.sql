CREATE DATABASE [IF NOT EXISTS] jcibardo_blog
USE jcibardo_blog;

CREATE TABLE projects (id VARCHAR(250) PRIMARY KEY, title VARCHAR(50), FOREIGN KEY (journey_id) REFERENCES journey(id));
CREATE TABLE journey (id VARCHAR(250) PRIMARY KEY, title VARCHAR(50), organizer VARCHAR(50), place VARCHAR(50), date1 VARCHAR(250), FOREIGN KEY (date_range_id) REFERENCES date_range(id), img longblob NOT NULL, description VARCHAR(2000))
CREATE TABLE date_range(id int NOT NULL AUTO_INCREMENT PRIMARY KEY, date_r DATE)