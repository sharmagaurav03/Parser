
//In case if the solution db is already existing.
DROP DATABASE IF EXISTS solution;

//db for our solution.
CREATE DATABASE solution;

// drop table if the table is existing.
DROP TABLE IF EXISTS solution.blocked_ips;

//create the table if it does not exist.
CREATE TABLE IF NOT EXISTS solution.blocked_ips (
    id INT AUTO_INCREMENT NOT NULL,
    ip VARCHAR(32) NOT NULL,
    reason VARCHAR(64) NOT NULL,
    PRIMARY KEY (id)
)  ENGINE=INNODB;