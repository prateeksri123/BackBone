CREATE TABLE `wishlist`.`Users` (
  `UserName` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `userId` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `FirstName` VARCHAR(255) NOT NULL,
  `LastName` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`userId`)
)
CREATE TABLE `wishlist`.`Product_Category` (
  `category_name` VARCHAR(255) NOT NULL,
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
)
DROP TABLE IF EXISTS `wishlist`.`product`;
CREATE TABLE  `wishlist`.`product` (
  `id` varchar(255) NOT NULL,
  `product_title` varchar(255) NOT NULL,
  `product_description` longtext,
  `image_urls` varchar(255) NOT NULL,
  `maximum_retail_price` decimal(10,0) NOT NULL,
  `selling_price` decimal(10,0) NOT NULL,
  `product_url` varchar(255) NOT NULL,
  `in_stock` tinyint(1) NOT NULL,
  `cod_available` tinyint(1) NOT NULL,
  `emi_available` tinyint(1) NOT NULL,
  `category_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE `wishlist`.`product_category` ADD COLUMN `category_url` VARCHAR(255) NOT NULL AFTER `id`;
ALTER TABLE `wishlist`.`product_category` MODIFY COLUMN `category_url` TEXT NOT NULL;
ALTER TABLE `wishlist`.`product_category` MODIFY COLUMN `id` VARCHAR(255) NOT NULL;