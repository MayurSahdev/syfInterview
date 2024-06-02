create table users(
id int NOT NULL auto_increment primary key,
user_name varchar(50) not null unique,
password varchar(100));

create table image_detail(
id int NOT NULL auto_increment primary key,
user_name varchar(50) not null,
image_name varchar(50) not null unique,
created_date datetime not null,
CONSTRAINT FK_userName FOREIGN KEY (user_name) REFERENCES Users(user_name));
