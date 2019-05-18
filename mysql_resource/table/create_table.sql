-- USE
USE kakeibo

CREATE TABLE `accounts` (
`id` int(11) NOT NULL,AUTO_INCREMENT,
`accountid` varchar(45) NOT NULL,
`password` char(60) NOT NULL,
`name` varchar(45) NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8

CREATE TABLE `income` (
  `id` int(11) NOT NULL,AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `value` int(11) NOT NULL,
  `category` varchar(45),
  `date` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8