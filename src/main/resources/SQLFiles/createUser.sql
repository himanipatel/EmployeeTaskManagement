CREATE USER 'emsDbuser'@'localhost' IDENTIFIED BY 'emsDbAdmin!';

GRANT ALL PRIVILEGES ON ems_db.* TO 'emsDbuser'@'localhost' WITH GRANT OPTION;

SHOW GRANTS FOR 'emsDbuser'@'localhost';