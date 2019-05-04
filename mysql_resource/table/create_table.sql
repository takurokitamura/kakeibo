-- USE
USE kakeibo

CREATE TABLE `accounts` (
`id` int(11) NOT NULL,AUTO_INCREMENT,
`accountId` varchar(45) NOT NULL,
`password` varchar(45) NOT NULL,
`name` varchar(45) NOT NULL,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB  DEFAULT CHARSET=utf8

CREATE TABLE `income` (
  `id` int(11) NOT NULL,AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `value` int(11) NOT NULL,
  `category` varchar(45) NOT NULL,
  `date` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8