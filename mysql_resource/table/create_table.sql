
CREATE TABLE `accounts` (
`id` int(11) NOT NULL,
`account_id` varchar(45) NOT NULL,
`password` varchar(45) NOT NULL,
`name` varchar(45) NOT NULL,
PRIMARY KEY (`id`),
KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8