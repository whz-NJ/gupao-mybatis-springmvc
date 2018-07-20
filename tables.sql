CREATE DATABASE gp DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE TABLE `posts` (
`pid` int(11) NOT NULL AUTO_INCREMENT,
`post_name` varchar(45) DEFAULT NULL,
`blog_id` int(11) DEFAULT NULL,
PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `author` (
`aid` int(11) NOT NULL AUTO_INCREMENT,
`author_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `blog` (
`bid` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
`author_id` int(11) DEFAULT NULL,
PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `test` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`nums` int(11) NOT NULL,
`name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `idx_numbs` (`nums`)
) ENGINE=InnoDB AUTO_INCREMENT=4219 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
