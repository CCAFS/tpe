SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tpe_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tpe_db` ;
CREATE SCHEMA IF NOT EXISTS `tpe_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `tpe_db` ;

-- -----------------------------------------------------
-- Table `tpe_db`.`crop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`crop` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`crop` (
  `crop_id` INT NOT NULL AUTO_INCREMENT,
  `crop_name` VARCHAR(100) NULL,
  PRIMARY KEY (`crop_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`cultivar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`cultivar` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`cultivar` (
  `cultivar_id` BIGINT NOT NULL AUTO_INCREMENT,
  `cultivar_name` VARCHAR(100) NULL,
  `crop_id` INT NULL,
  PRIMARY KEY (`cultivar_id`),
  INDEX `fk_variety_crop_idx` (`crop_id` ASC),
  CONSTRAINT `fk_variety_crop`
    FOREIGN KEY (`crop_id`)
    REFERENCES `tpe_db`.`crop` (`crop_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`category` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`category` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(100) NULL,
  `category_description` VARCHAR(255) NULL,
  `entity_class` VARCHAR(45) NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`region` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`region` (
  `region_id` BIGINT NOT NULL AUTO_INCREMENT,
  `region_name` VARCHAR(100) NULL,
  `region_parent` BIGINT NULL,
  `region_alpha_iso` VARCHAR(10) NULL,
  `longitude` DOUBLE NULL,
  `latitude` DOUBLE NULL,
  `altitude` DOUBLE NULL,
  `category_id` INT NULL,
  `region_level` VARCHAR(45) NULL,
  `region_numeric_iso` SMALLINT NULL,
  PRIMARY KEY (`region_id`),
  INDEX `fk_region_region_idx` (`region_parent` ASC),
  INDEX `fk_region_category_idx` (`category_id` ASC),
  UNIQUE INDEX `region_alpha_iso_UNIQUE` (`region_alpha_iso` ASC),
  UNIQUE INDEX `region_numeric_iso_UNIQUE` (`region_numeric_iso` ASC),
  CONSTRAINT `fk_region_region`
    FOREIGN KEY (`region_parent`)
    REFERENCES `tpe_db`.`region` (`region_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_region_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `tpe_db`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`station` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`station` (
  `station_id` BIGINT NOT NULL AUTO_INCREMENT,
  `station_name` VARCHAR(100) NULL,
  `station_longitude` DOUBLE NULL,
  `station_latitude` DOUBLE NULL,
  `region_id` BIGINT NULL,
  `station_altitude` DOUBLE NULL,
  `station_number` INT NULL,
  PRIMARY KEY (`station_id`),
  INDEX `fk_station_region_idx` USING BTREE (`region_id` ASC),
  CONSTRAINT `fk_station_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `tpe_db`.`region` (`region_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`model`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`model` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`model` (
  `model_id` BIGINT NOT NULL AUTO_INCREMENT,
  `model_description` TEXT NULL,
  `model_date` TIMESTAMP NULL,
  `cultivar_id` BIGINT NULL,
  `number` INT NULL,
  `model_title` VARCHAR(255) NULL,
  PRIMARY KEY (`model_id`),
  INDEX `fk_model_variety_idx` (`cultivar_id` ASC),
  CONSTRAINT `fk_model_cultivar`
    FOREIGN KEY (`cultivar_id`)
    REFERENCES `tpe_db`.`cultivar` (`cultivar_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`soil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`soil` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`soil` (
  `soil_id` INT NOT NULL AUTO_INCREMENT,
  `soil_name` VARCHAR(45) NULL,
  `soil_code` VARCHAR(45) NULL,
  PRIMARY KEY (`soil_id`),
  UNIQUE INDEX `soil_code_UNIQUE` (`soil_code` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`scenario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`scenario` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`scenario` (
  `scenario_id` INT NOT NULL AUTO_INCREMENT,
  `scenario` VARCHAR(150) NULL,
  PRIMARY KEY (`scenario_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`window_sowing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`window_sowing` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`window_sowing` (
  `window_id` INT NOT NULL AUTO_INCREMENT,
  `start_date` DATETIME NULL,
  `end_date` DATETIME NULL,
  `window_title` VARCHAR(255) NULL,
  `cultivar_id` BIGINT NULL,
  `model_id` BIGINT NULL,
  PRIMARY KEY (`window_id`),
  INDEX `fk_window_cultivar_idx` (`cultivar_id` ASC),
  INDEX `fk_window_model_idx` (`model_id` ASC),
  CONSTRAINT `fk_window_cultivar`
    FOREIGN KEY (`cultivar_id`)
    REFERENCES `tpe_db`.`cultivar` (`cultivar_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_window_model`
    FOREIGN KEY (`model_id`)
    REFERENCES `tpe_db`.`model` (`model_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`phenology_growth`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`phenology_growth` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`phenology_growth` (
  `result_id` BIGINT NOT NULL,
  `result_date` TIMESTAMP NULL,
  `cultivar_id` BIGINT NULL,
  `model_id` BIGINT NULL,
  `category_id` INT NULL,
  `region_id` BIGINT NULL,
  `station_id` BIGINT NULL,
  `Total_Days_Eme_Ipa` INT NULL,
  `Total_Days_Eme_Flo` INT NULL,
  `Total_Days_Eme_Mad` INT NULL,
  `Num_Days_Eme_Ipa` INT NULL,
  `Num_Days_IPa_Flo` INT NULL,
  `Num_Days_Flo_Mad` INT NULL,
  `Sum_Tmin_Eme_Ipa` FLOAT NULL,
  `Sum_Tmin_IPa_Flo` FLOAT NULL,
  `Sum_Tmin_Flo_Mad` FLOAT NULL,
  `Max_Tmin_Eme_Ipa` FLOAT NULL,
  `Max_Tmin_IPa_Flo` FLOAT NULL,
  `Max_Tmin_Flo_Mad` FLOAT NULL,
  `Sum_Tmax_Eme_Ipa` FLOAT NULL,
  `Sum_Tmax_IPa_Flo` FLOAT NULL,
  `Sum_Tmax_Flo_Mad` FLOAT NULL,
  `Max_Tmax_Eme_Ipa` FLOAT NULL,
  `Max_Tmax_IPa_Flo` FLOAT NULL,
  `Max_Tmax_Flo_Mad` FLOAT NULL,
  `Sum_RAIN_Eme_Ipa` FLOAT NULL,
  `Sum_RAIN_IPa_Flo` FLOAT NULL,
  `Sum_RAIN_Flo_Mad` FLOAT NULL,
  `NFLV_Eme_Ipa` FLOAT NULL,
  `NFLV_IPa_Flo` FLOAT NULL,
  `NFLV_Flo_Mad` FLOAT NULL,
  `Max_SLA_Eme_Ipa` FLOAT NULL,
  `Max_SLA_IPa_Flo` FLOAT NULL,
  `Max_SLA_Flo_Mad` FLOAT NULL,
  `Max_NSP_Eme_Ipa` INT NULL,
  `Max_NSP_IPa_Flo` INT NULL,
  `Max_NSP_Flo_Mad` INT NULL,
  `Max_LAI_Eme_Ipa` FLOAT NULL,
  `Max_LAI_IPa_Flo` FLOAT NULL,
  `Max_LAI_Flo_Mad` FLOAT NULL,
  `Max_WAGT_Eme_Ipa` FLOAT NULL,
  `Max_WAGT_IPa_Flo` FLOAT NULL,
  `Max_WAGT_Flo_Mad` FLOAT NULL,
  `Max_WST_Eme_Ipa` FLOAT NULL,
  `Max_WST_IPa_Flo` FLOAT NULL,
  `Max_WST_Flo_Mad` FLOAT NULL,
  `Max_WLVG_Eme_Ipa` FLOAT NULL,
  `Max_WLVG_IPa_Flo` FLOAT NULL,
  `Max_WLVG_Flo_Mad` FLOAT NULL,
  `Max_WSO_Eme_Ipa` FLOAT NULL,
  `Max_WSO_IPa_Flo` FLOAT NULL,
  `Max_WSO_Flo_Mad` FLOAT NULL,
  `Sum_TRW_Eme_Ipa` FLOAT NULL,
  `Sum_TRW_IPa_Flo` FLOAT NULL,
  `Sum_TRW_Flo_Mad` FLOAT NULL,
  `ETDCUM` FLOAT NULL,
  `EVSCCUM` FLOAT NULL,
  `TRCCUM` FLOAT NULL,
  `WRR14` FLOAT NULL,
  `WSO` FLOAT NULL,
  `WAGT` FLOAT NULL,
  `PARCUM` FLOAT NULL,
  `TS` FLOAT NULL,
  `TMAXC` FLOAT NULL,
  `TMINC` FLOAT NULL,
  `TAVERC` FLOAT NULL,
  `RAINCUM` FLOAT NULL,
  `IRCUM` FLOAT NULL,
  `RUNOFCUM` FLOAT NULL,
  `TRWCUM` FLOAT NULL,
  `EVSWCUM` FLOAT NULL,
  `DRAICUM` FLOAT NULL,
  `EMD` INT NULL,
  `DAE` INT NULL,
  `soil_id` INT NULL,
  `scenario_id` INT NULL,
  `window_id` INT NULL,
  `year` VARCHAR(10) NULL,
  PRIMARY KEY (`result_id`),
  INDEX `fk_yield_variety_idx` (`cultivar_id` ASC),
  INDEX `fk_result_model_idx` (`model_id` ASC),
  INDEX `fk_result_category_idx` (`category_id` ASC),
  INDEX `fk_result_station_idx` (`station_id` ASC),
  INDEX `fk_result_region_idx` (`region_id` ASC),
  INDEX `fk_phenology_soil_idx` (`soil_id` ASC),
  INDEX `fk_phenoloy_scenario_idx` (`scenario_id` ASC),
  INDEX `fk_phenology_window_idx` (`window_id` ASC),
  CONSTRAINT `fk_result_cultivar`
    FOREIGN KEY (`cultivar_id`)
    REFERENCES `tpe_db`.`cultivar` (`cultivar_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_result_model`
    FOREIGN KEY (`model_id`)
    REFERENCES `tpe_db`.`model` (`model_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_result_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `tpe_db`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_result_station`
    FOREIGN KEY (`station_id`)
    REFERENCES `tpe_db`.`station` (`station_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_result_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `tpe_db`.`region` (`region_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_phenology_soil`
    FOREIGN KEY (`soil_id`)
    REFERENCES `tpe_db`.`soil` (`soil_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_phenoloy_scenario`
    FOREIGN KEY (`scenario_id`)
    REFERENCES `tpe_db`.`scenario` (`scenario_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_phenology_window`
    FOREIGN KEY (`window_id`)
    REFERENCES `tpe_db`.`window_sowing` (`window_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`climate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`climate` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`climate` (
  `climate_id` BIGINT NOT NULL AUTO_INCREMENT,
  `irradiance` FLOAT NULL,
  `tmin` FLOAT NULL,
  `tmax` FLOAT NULL,
  `day` INT NULL,
  `station_id` BIGINT NULL,
  `category_id` INT NULL,
  `precipitation` FLOAT NULL,
  `author` VARCHAR(255) NULL,
  `source` VARCHAR(255) NULL,
  `year` VARCHAR(10) NULL,
  `recordedOn` DATETIME NULL,
  PRIMARY KEY (`climate_id`),
  INDEX `fk_climate_station_idx` (`station_id` ASC),
  INDEX `fk_climate_category_idx` (`category_id` ASC),
  CONSTRAINT `fk_climate_station`
    FOREIGN KEY (`station_id`)
    REFERENCES `tpe_db`.`station` (`station_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_climate_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `tpe_db`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`soil_property`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`soil_property` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`soil_property` (
  `property_id` BIGINT NOT NULL AUTO_INCREMENT,
  `category_id` INT NULL,
  `soil_id` INT NULL,
  `station_id` BIGINT NULL,
  `mean_ds` FLOAT NULL,
  `std_dev_ds` FLOAT NULL,
  `mean_teta_s` FLOAT NULL,
  `std_dev_teta_s` FLOAT NULL,
  `mean_cc` FLOAT NULL,
  `std_dev_cc` FLOAT NULL,
  `mean_teta_r` FLOAT NULL,
  `std_dev_teta_r` FLOAT NULL,
  `mean_ks` FLOAT NULL,
  `std_dev_ks` FLOAT NULL,
  `mean_om` FLOAT NULL,
  `std_dev_om` FLOAT NULL,
  `mean_hz_de_fn` FLOAT NULL,
  `std_dev_hz_de_fn` FLOAT NULL,
  `mean_clay` FLOAT NULL,
  `std_dev_clay` FLOAT NULL,
  `mean_sand` FLOAT NULL,
  `std_dev_sand` FLOAT NULL,
  `no_of_profiles` INT NULL,
  `lower_limit` FLOAT NULL,
  `soil_water_available` FLOAT NULL,
  `ks` FLOAT NULL,
  `wcmin` FLOAT NULL,
  `year` INT NULL,
  `model_id` BIGINT NULL,
  `organic_carbon` FLOAT NULL,
  `water_cont_field_capacity` FLOAT NULL,
  `water_cont_wilting_point` FLOAT NULL,
  `ph` FLOAT NULL,
  `depth` FLOAT NULL,
  `organic_matter` FLOAT NULL,
  `taxonomy` INT NULL,
  `buky_density` FLOAT NULL,
  `cation_exchange` FLOAT NULL,
  `available_soil_water` FLOAT NULL,
  `longitude` DOUBLE NULL,
  `latitude` DOUBLE NULL,
  PRIMARY KEY (`property_id`),
  INDEX `fk_property_soil_idx` (`soil_id` ASC),
  INDEX `fk_property_category_idx` (`category_id` ASC),
  INDEX `fk_property_station_idx` (`station_id` ASC),
  INDEX `fk_property_model_idx` (`model_id` ASC),
  CONSTRAINT `fk_property_soil`
    FOREIGN KEY (`soil_id`)
    REFERENCES `tpe_db`.`soil` (`soil_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_property_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `tpe_db`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_property_station`
    FOREIGN KEY (`station_id`)
    REFERENCES `tpe_db`.`station` (`station_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_property_model`
    FOREIGN KEY (`model_id`)
    REFERENCES `tpe_db`.`model` (`model_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`grouping`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`grouping` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`grouping` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `tmin` FLOAT NULL,
  `tmax` FLOAT NULL,
  `cultivar` BIGINT NULL,
  `precipiation` FLOAT NULL,
  `irradiance` FLOAT NULL,
  `category_id` INT NULL,
  `soil_id` INT NULL,
  PRIMARY KEY (`group_id`),
  INDEX `fk_environment_cultivar_idx` (`cultivar` ASC),
  INDEX `fk_environment_category_idx` (`category_id` ASC),
  INDEX `fk_group_soil_idx` (`soil_id` ASC),
  CONSTRAINT `fk_group_cultivar`
    FOREIGN KEY (`cultivar`)
    REFERENCES `tpe_db`.`cultivar` (`cultivar_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_group_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `tpe_db`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_group_soil`
    FOREIGN KEY (`soil_id`)
    REFERENCES `tpe_db`.`soil` (`soil_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tpe_db`.`window_steps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`window_steps` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`window_steps` (
  `step_id` INT NOT NULL AUTO_INCREMENT,
  `window_id` INT NULL,
  `sowing_date` DATETIME NULL,
  PRIMARY KEY (`step_id`),
  INDEX `fk_step_window_idx` (`window_id` ASC),
  CONSTRAINT `fk_step_window`
    FOREIGN KEY (`window_id`)
    REFERENCES `tpe_db`.`window_sowing` (`window_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
