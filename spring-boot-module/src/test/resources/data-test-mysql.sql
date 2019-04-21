DROP TABLE items;
DROP TABLE audititem;

CREATE TABLE IF NOT EXISTS items
(
id BIGINT auto_increment PRIMARY KEY,
name VARCHAR(40) NOT NULL,
status VARCHAR(255) NOT NULL,
deleted BOOLEAN DEFAULT FALSE NOT NULL
 );

 CREATE TABLE IF NOT EXISTS audititem
(
id BIGINT auto_increment PRIMARY KEY,
item_id BIGINT NOT NULL,
action VARCHAR(255) NOT NULL,
date TIMESTAMP
 );

INSERT INTO items (id,name,status) values (1,'test_item1','READY');
INSERT INTO items (id,name,status) values (2,'test_item2','COMPLETED');
INSERT INTO items (id,name,status) values (3,'test_item3','READY');