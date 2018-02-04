DROP DATABASE usersdb;
CREATE DATABASE usersdb;
USE usersdb;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1

INSERT INTO `usersdb`.`users` (`username`, `password`, `email`) VALUES ('nguyenvana', '123456', 'nva@tma.com.vn');
INSERT INTO `usersdb`.`users` (`username`, `password`, `email`) VALUES ('tranvanb', '123456', 'tvb@tma.com.vn');
INSERT INTO `usersdb`.`users` (`username`, `password`, `email`) VALUES ('nguyenvana', '123456', 'nva@tma.com.vn');
INSERT INTO `usersdb`.`users` (`username`, `password`, `email`) VALUES ('levanc', '123456', 'lvc@tma.com.vn');
INSERT INTO `usersdb`.`users` (`username`, `password`, `email`) VALUES ('ntduy', '123456', 'ntduy@tma.com.vn');
INSERT INTO `usersdb`.`users` (`username`, `password`, `email`) VALUES ('hovand', '123456', 'dvd@tma.com.vn');
