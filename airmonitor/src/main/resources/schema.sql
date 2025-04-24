CREATE TABLE IF NOT EXISTS `building` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `address` varchar(255),
  `longitude` double,
  `latitude` double,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  CONSTRAINT `unique_location` UNIQUE (`longitude`, `latitude`)
);

CREATE TABLE IF NOT EXISTS `room` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `building_id` int,
  `floor_number` int,
  `room_number` int,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  CONSTRAINT `fk_room_building` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`)
  ON DELETE CASCADE,  -- When deleting a building, delete related rooms as well
  CONSTRAINT `unique_room_per_building` UNIQUE (`building_id`, `room_number`)
);

CREATE TABLE IF NOT EXISTS `indoor_air` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `room_id` int,
  `temperature` double,
  `humidity` double,
  `co2` double,
  `dust` double,
  `light` double,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  CONSTRAINT `fk_indoor_air_room` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `outdoor_air` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `building_id` int,
  `temperature` double,
  `humidity` double,
  `wind_speed` double,
  `pressure` double,
  `clouds` int,
  `longitude` double,
  `latitude` double,
  `created_at` date NOT NULL,
  `created_by` varchar(20) NOT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL,
  CONSTRAINT `fk_outdoor_air_building` FOREIGN KEY (`building_id`) REFERENCES `building` (`id`)
  ON DELETE CASCADE
);