
create database newspaper;

show databases;
select * from pg_catalog.pg_tables;

use newspaper;
show tables;

 	create table Article (
 		id int primary key, 
 		title varchar(1000), 
 		text varchar(5000), 
 		regDate varchar(255)
 	);

 select * from Article;
SHOW COLUMNS FROM Article;
CREATE INDEX title_idx ON Article (title DESC);

