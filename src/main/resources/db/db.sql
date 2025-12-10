-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Tiendadb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Tiendadb` ;

-- -----------------------------------------------------
-- Schema Tiendadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Tiendadb` DEFAULT CHARACTER SET utf8mb3 ;
SHOW WARNINGS;
USE `Tiendadb` ;

-- -----------------------------------------------------
-- Table `Tiendadb`.`categorias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Tiendadb`.`categorias` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Tiendadb`.`categorias` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;
CREATE UNIQUE INDEX `category_name_UNIQUE` ON `Tiendadb`.`categorias` (`category_name` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Tiendadb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Tiendadb`.`users` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Tiendadb`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL,
  `password` CHAR(60) NOT NULL,
  `email` VARCHAR(90) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;
CREATE UNIQUE INDEX `Username_UNIQUE` ON `Tiendadb`.`users` (`username` ASC) VISIBLE;


SHOW WARNINGS;
CREATE UNIQUE INDEX `email_UNIQUE` ON `Tiendadb`.`users` (`email` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Tiendadb`.`dnis`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Tiendadb`.`dnis` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Tiendadb`.`dnis` (
  `dni_id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(9) NOT NULL,
  `front_img` VARCHAR(45) NULL DEFAULT NULL,
  `back_img` VARCHAR(45) NULL DEFAULT NULL,
  `Users_user_id` INT NOT NULL,
  PRIMARY KEY (`dni_id`, `Users_user_id`),
  CONSTRAINT `fk_dnis_users1`
    FOREIGN KEY (`Users_user_id`)
    REFERENCES `Tiendadb`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;
CREATE UNIQUE INDEX `number_UNIQUE` ON `Tiendadb`.`dnis` (`number` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `fk_dnis_users1_idx` ON `Tiendadb`.`dnis` (`Users_user_id` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Tiendadb`.`productos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Tiendadb`.`productos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Tiendadb`.`productos` (
  `producto_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(150) NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `price` DOUBLE NOT NULL,
  `image_url` VARCHAR(150) NOT NULL,
  `category_id` INT NOT NULL, 
  PRIMARY KEY (`producto_id`),
  CONSTRAINT fk_productos_categorias
    FOREIGN KEY (category_id)
    REFERENCES categorias(category_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb3;

 

SHOW WARNINGS;
CREATE UNIQUE INDEX `name_UNIQUE` ON `Tiendadb`.`productos` (`product_name` ASC) VISIBLE;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Tiendadb`.`users_bought_productos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Tiendadb`.`users_bought_productos` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Tiendadb`.`users_bought_productos` (
  `Users_user_id` INT NOT NULL,
  `productos_producto_id` INT NOT NULL,
  PRIMARY KEY (`Users_user_id`, `productos_producto_id`),
  CONSTRAINT `fk_Users_has_bought_productos_productos1`
    FOREIGN KEY (`productos_producto_id`)
    REFERENCES `Tiendadb`.`productos` (`producto_id`),
    ON DELETE CASCADE,
  CONSTRAINT `fk_Users_has_productos_Users1`
    FOREIGN KEY (`Users_user_id`)
    REFERENCES `Tiendadb`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;

SHOW WARNINGS;
CREATE INDEX `fk_Users_has_productos_productos1_idx` ON `Tiendadb`.`users_bought_productos` (`productos_producto_id` ASC) VISIBLE;

SHOW WARNINGS;
CREATE INDEX `fk_Users_has_productos_Users1_idx` ON `Tiendadb`.`users_bought_productos` (`Users_user_id` ASC) VISIBLE;

SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;