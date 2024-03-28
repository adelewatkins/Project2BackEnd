
DROP TABLE `item` CASCADE;

DROP TABLE `cart`CASCADE;



CREATE TABLE `cart`(
`id` INT PRIMARY KEY AUTO_INCREMENT,

`buyer` VARCHAR(100),
`user_id` VARCHAR(100),
`password` VARCHAR(100),
`tel` VARCHAR(100),
`address` VARCHAR(100)

);

CREATE TABLE `item`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`item_name` VARCHAR(100),
`item_price` DOUBLE,
`item_quantity` INT,
`cart_id` INT,
FOREIGN KEY (`cart_id`) REFERENCES `cart`(`id`));

