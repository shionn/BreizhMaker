CREATE DATABASE wow;
CREATE USER 'wow'@'localhost' IDENTIFIED BY 'wow';
GRANT ALL PRIVILEGES ON wow.* TO 'wow'@'localhost';
