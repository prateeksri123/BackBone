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
CREATE TABLE `wishlist`.`product` (
  `id` INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_title` VARCHAR(255) NOT NULL,
  `product_description` LONGTEXT,
  `image_urls` VARCHAR(255) NOT NULL,
  `maximum_retail_price` NUMERIC NOT NULL,
  `selling_price` NUMERIC NOT NULL,
  `product_url` VARCHAR(255) NOT NULL,
  `in_stock` BOOLEAN NOT NULL,
  `cod_available` BOOLEAN NOT NULL,
  `emi_available` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`)
)
ALTER TABLE `wishlist`.`product_category` MODIFY COLUMN `id` VARCHAR(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL AUTO_INCREMENT;
ALTER TABLE `wishlist`.`product_category` ADD COLUMN `category_url` VARCHAR() NOT NULL AFTER `id`;
ALTER TABLE `wishlist`.`product_category` MODIFY COLUMN `category_url` TEXT NOT NULL;
ALTER TABLE `wishlist`.`product_category` MODIFY COLUMN `id` VARCHAR(255) NOT NULL;