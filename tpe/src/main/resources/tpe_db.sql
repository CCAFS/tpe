-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema tpe_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tpe_db` ;

-- -----------------------------------------------------
-- Schema tpe_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tpe_db` DEFAULT CHARACTER SET utf8 ;
USE `tpe_db` ;

-- -----------------------------------------------------
-- Table `tpe_db`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`category` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`category` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  `description` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `entity_class` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`level`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`level` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`level` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`region` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`region` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  `estacao1` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  `level_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `parent` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `alpha_iso` VARCHAR(10) NULL DEFAULT NULL COMMENT '',
  `estacao_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `longitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `latitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `category_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `country_code` INT(11) NULL DEFAULT NULL COMMENT '',
  `level` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `numeric_iso` SMALLINT(6) NULL DEFAULT NULL COMMENT '',
  `zoom` SMALLINT(6) NULL DEFAULT NULL COMMENT '',
  `country` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  `state_code` INT(11) NULL DEFAULT NULL COMMENT '',
  `municipality_code` INT(11) NULL DEFAULT NULL COMMENT '',
  `region` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `select` TINYINT(4) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `region_alpha_iso_UNIQUE` (`alpha_iso` ASC)  COMMENT '',
  UNIQUE INDEX `region_numeric_iso_UNIQUE` (`numeric_iso` ASC)  COMMENT '',
  INDEX `fk_region_region_idx` (`parent` ASC)  COMMENT '',
  INDEX `fk_region_category_idx` (`category_id` ASC)  COMMENT '',
  INDEX `fk_region_level` (`level_id` ASC)  COMMENT '',
  CONSTRAINT `fk_region_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `tpe_db`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_region_level`
    FOREIGN KEY (`level_id`)
    REFERENCES `tpe_db`.`level` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2207
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`station`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`station` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`station` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  `longitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `latitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `region_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `number` INT(11) NULL DEFAULT NULL COMMENT '',
  `country` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_station_region_idx` USING BTREE (`region_id` ASC)  COMMENT '',
  CONSTRAINT `fk_station_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `tpe_db`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 956
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`climate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`climate` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`climate` (
  `climate_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `station_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `level_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `category_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `author` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `source` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `year` VARCHAR(10) NULL DEFAULT NULL COMMENT '',
  `precipitation` DOUBLE NULL DEFAULT NULL COMMENT '',
  `min_temperature` DOUBLE NULL DEFAULT NULL COMMENT '',
  `max_temperature` DOUBLE NULL DEFAULT NULL COMMENT '',
  `radiation` DOUBLE NULL DEFAULT NULL COMMENT '',
  `month` TINYINT(4) NULL DEFAULT NULL COMMENT '',
  `month_planting_date` INT(11) NULL DEFAULT NULL COMMENT '',
  `latitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `longitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `region_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`climate_id`)  COMMENT '',
  INDEX `fk_climate_station_idx` (`station_id` ASC)  COMMENT '',
  INDEX `fk_climate_category_idx` (`category_id` ASC)  COMMENT '',
  INDEX `fk_climate_level` (`level_id` ASC)  COMMENT '',
  INDEX `fk_climate_region` (`region_id` ASC)  COMMENT '',
  CONSTRAINT `fk_climate_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `tpe_db`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_climate_level`
    FOREIGN KEY (`level_id`)
    REFERENCES `tpe_db`.`level` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_climate_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `tpe_db`.`region` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_climate_station`
    FOREIGN KEY (`station_id`)
    REFERENCES `tpe_db`.`station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 50992
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`crop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`crop` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`crop` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`cultivar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`cultivar` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`cultivar` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  `crop_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `vegetative_start` INT(11) NULL DEFAULT NULL COMMENT '',
  `vegetative_end` INT(11) NULL DEFAULT NULL COMMENT '',
  `fillinggrain_hfe_start` INT(11) NULL DEFAULT NULL COMMENT '',
  `reproductive_hfe_end` INT(11) NULL DEFAULT NULL COMMENT '',
  `fillinggrain_lfe_start` INT(11) NULL DEFAULT NULL COMMENT '',
  `reproductive_lfe_end` INT(11) NULL DEFAULT NULL COMMENT '',
  `fillinggrain_fe_start` INT(11) NULL DEFAULT NULL COMMENT '',
  `reproductive_fe_end` INT(11) NULL DEFAULT NULL COMMENT '',
  `reproductive_start` INT(11) NULL DEFAULT NULL COMMENT '',
  `fillinggrain_end` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_variety_crop_idx` (`crop_id` ASC)  COMMENT '',
  CONSTRAINT `fk_variety_crop`
    FOREIGN KEY (`crop_id`)
    REFERENCES `tpe_db`.`crop` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`environment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`environment` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`environment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(100) NOT NULL COMMENT '',
  `code` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `color` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `description` VARCHAR(150) NULL DEFAULT NULL COMMENT '',
  `cluster` TINYINT(4) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`, `name`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`soil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`soil` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`soil` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `code` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  `color` VARCHAR(45) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `idx_soil_pk` (`id` ASC)  COMMENT '',
  UNIQUE INDEX `soil_code_UNIQUE` (`code` ASC)  COMMENT '',
  INDEX `idx_soil_lookup` (`code` ASC)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`environment_soil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`environment_soil` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`environment_soil` (
  `env_soil_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `sowing_date` VARCHAR(50) NULL DEFAULT NULL COMMENT '',
  `prob_occurance` FLOAT NULL DEFAULT NULL COMMENT '',
  `environment_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `soil_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `region_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`env_soil_id`)  COMMENT '',
  INDEX `fk_env_soil_idx` (`soil_id` ASC)  COMMENT '',
  INDEX `fk_env_env_idx` (`environment_id` ASC)  COMMENT '',
  INDEX `fk_env_region_idx` (`region_id` ASC)  COMMENT '',
  CONSTRAINT `fk_env_soil_env`
    FOREIGN KEY (`environment_id`)
    REFERENCES `tpe_db`.`environment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_env_soil_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `tpe_db`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_env_soil_soil`
    FOREIGN KEY (`soil_id`)
    REFERENCES `tpe_db`.`soil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 169
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`tag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`tag` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`tag` (
  `tag_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `tag_name` VARCHAR(100) NOT NULL COMMENT '',
  `tag_url` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `tag_clicks` INT(11) NULL DEFAULT NULL COMMENT '',
  `created_on` TIMESTAMP NULL DEFAULT NULL COMMENT '',
  `tag_weight` INT(11) NULL DEFAULT NULL COMMENT '',
  `enabled` TINYINT(4) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`tag_id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`link`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`link` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`link` (
  `link_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `title` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `url` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `clicks` INT(11) NULL DEFAULT NULL COMMENT '',
  `created_on` DATETIME NULL DEFAULT NULL COMMENT '',
  `tag_id` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`link_id`)  COMMENT '',
  INDEX `fk_link_tag_idx` (`tag_id` ASC)  COMMENT '',
  CONSTRAINT `fk_link_tag`
    FOREIGN KEY (`tag_id`)
    REFERENCES `tpe_db`.`tag` (`tag_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`series`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`series` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`series` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(150) NULL DEFAULT NULL COMMENT '',
  `description` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`phenology_growth`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`phenology_growth` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`phenology_growth` (
  `result_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `result_date` TIMESTAMP NULL DEFAULT NULL COMMENT '',
  `cultivar_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `category_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `status` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  `region_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `station_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `Total_Days_Eme_Ipa` INT(11) NULL DEFAULT NULL COMMENT '',
  `Total_Days_Eme_Flo` INT(11) NULL DEFAULT NULL COMMENT '',
  `Total_Days_Eme_Mad` INT(11) NULL DEFAULT NULL COMMENT '',
  `Num_Days_Eme_Ipa` INT(11) NULL DEFAULT NULL COMMENT '',
  `Num_Days_IPa_Flo` INT(11) NULL DEFAULT NULL COMMENT '',
  `Num_Days_Flo_Mad` INT(11) NULL DEFAULT NULL COMMENT '',
  `Sum_Tmin_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_Tmin_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_Tmin_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_Tmin_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_Tmin_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_Tmin_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_Tmax_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_Tmax_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_Tmax_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_Tmax_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_Tmax_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_Tmax_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_RAIN_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_RAIN_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_RAIN_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `NFLV_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `NFLV_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `NFLV_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_SLA_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_SLA_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_SLA_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_NSP_Eme_Ipa` INT(11) NULL DEFAULT NULL COMMENT '',
  `Max_NSP_IPa_Flo` INT(11) NULL DEFAULT NULL COMMENT '',
  `Max_NSP_Flo_Mad` INT(11) NULL DEFAULT NULL COMMENT '',
  `Max_LAI_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_LAI_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_LAI_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WAGT_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WAGT_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WAGT_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WST_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WST_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WST_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WLVG_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WLVG_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WLVG_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WSO_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WSO_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Max_WSO_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_TRW_Eme_Ipa` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_TRW_IPa_Flo` FLOAT NULL DEFAULT NULL COMMENT '',
  `Sum_TRW_Flo_Mad` FLOAT NULL DEFAULT NULL COMMENT '',
  `ETDCUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `EVSCCUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `TRCCUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `WRR14` FLOAT NULL DEFAULT NULL COMMENT '',
  `WSO` FLOAT NULL DEFAULT NULL COMMENT '',
  `WAGT` FLOAT NULL DEFAULT NULL COMMENT '',
  `PARCUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `TS` FLOAT NULL DEFAULT NULL COMMENT '',
  `TMAXC` FLOAT NULL DEFAULT NULL COMMENT '',
  `TMINC` FLOAT NULL DEFAULT NULL COMMENT '',
  `TAVERC` FLOAT NULL DEFAULT NULL COMMENT '',
  `RAINCUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `IRCUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `RUNOFCUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `TRWCUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `EVSWCUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `DRAICUM` FLOAT NULL DEFAULT NULL COMMENT '',
  `EMD` INT(11) NULL DEFAULT NULL COMMENT '',
  `DAE` INT(11) NULL DEFAULT NULL COMMENT '',
  `soil_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `scenario` VARCHAR(50) NULL DEFAULT NULL COMMENT '',
  `window_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `year` VARCHAR(10) NULL DEFAULT NULL COMMENT '',
  `cluster` SMALLINT(6) NULL DEFAULT NULL COMMENT '',
  `latitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `longitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `sowing_date` VARCHAR(50) NULL DEFAULT NULL COMMENT '',
  `environment_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `lai` FLOAT NULL DEFAULT NULL COMMENT '',
  `actual_transpiration` FLOAT NULL DEFAULT NULL COMMENT '',
  `stress_index` FLOAT NULL DEFAULT NULL COMMENT '',
  `average_weekly_rain` FLOAT NULL DEFAULT NULL COMMENT '',
  `graphic_name` VARCHAR(60) NULL DEFAULT NULL COMMENT '',
  `cluster_code` VARCHAR(60) NULL DEFAULT NULL COMMENT '',
  `series_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `value` DOUBLE NULL DEFAULT NULL COMMENT '',
  `pcew` FLOAT NULL DEFAULT NULL COMMENT '',
  `variable` VARCHAR(60) NULL DEFAULT NULL COMMENT '',
  `name_file_id` VARCHAR(100) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`result_id`)  COMMENT '',
  INDEX `fk_yield_variety_idx` (`cultivar_id` ASC)  COMMENT '',
  INDEX `fk_result_category_idx` (`category_id` ASC)  COMMENT '',
  INDEX `fk_result_station_idx` (`station_id` ASC)  COMMENT '',
  INDEX `fk_result_region_idx` (`region_id` ASC)  COMMENT '',
  INDEX `fk_phenology_soil_idx` (`soil_id` ASC)  COMMENT '',
  INDEX `fk_result_env_idx` (`environment_id` ASC)  COMMENT '',
  INDEX `fk_phenology_growth_series` (`series_id` ASC)  COMMENT '',
  CONSTRAINT `fk_phenology_growth_series`
    FOREIGN KEY (`series_id`)
    REFERENCES `tpe_db`.`series` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_phenology_soil`
    FOREIGN KEY (`soil_id`)
    REFERENCES `tpe_db`.`soil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_result_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `tpe_db`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_result_cultivar`
    FOREIGN KEY (`cultivar_id`)
    REFERENCES `tpe_db`.`cultivar` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_result_env`
    FOREIGN KEY (`environment_id`)
    REFERENCES `tpe_db`.`environment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_result_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `tpe_db`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_result_station`
    FOREIGN KEY (`station_id`)
    REFERENCES `tpe_db`.`station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 44416
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tpe_db`.`soil_property`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tpe_db`.`soil_property` ;

CREATE TABLE IF NOT EXISTS `tpe_db`.`soil_property` (
  `property_id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `soil_id` INT(11) NULL DEFAULT NULL COMMENT '',
  `station_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `longitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `latitude` DOUBLE NULL DEFAULT NULL COMMENT '',
  `ph` FLOAT NULL DEFAULT NULL COMMENT '',
  `depth` FLOAT NULL DEFAULT NULL COMMENT '',
  `organic_matter` FLOAT NULL DEFAULT NULL COMMENT '',
  `bulky_density` FLOAT NULL DEFAULT NULL COMMENT '',
  `taxonomy` FLOAT NULL DEFAULT NULL COMMENT '',
  `organic_carbon` FLOAT NULL DEFAULT NULL COMMENT '',
  `available_soil_water` FLOAT NULL DEFAULT NULL COMMENT '',
  `cation_exchange` FLOAT NULL DEFAULT NULL COMMENT '',
  `water_capacity_wilt_point` FLOAT UNSIGNED NULL DEFAULT NULL COMMENT '',
  `water_content_field_capacity` FLOAT NULL DEFAULT NULL COMMENT '',
  `region_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `integrate_id` BIGINT(20) NULL DEFAULT NULL COMMENT '',
  `clay` FLOAT NULL DEFAULT NULL COMMENT '',
  `silt` FLOAT NULL DEFAULT NULL COMMENT '',
  `sand` FLOAT NULL DEFAULT NULL COMMENT '',
  `continent` TINYINT(4) NULL DEFAULT '0' COMMENT '',
  PRIMARY KEY (`property_id`)  COMMENT '',
  INDEX `fk_property_soil_idx` (`soil_id` ASC)  COMMENT '',
  INDEX `fk_property_station_idx` (`station_id` ASC)  COMMENT '',
  INDEX `fk_property_region` (`region_id` ASC)  COMMENT '',
  CONSTRAINT `fk_property_region`
    FOREIGN KEY (`region_id`)
    REFERENCES `tpe_db`.`region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_property_soil`
    FOREIGN KEY (`soil_id`)
    REFERENCES `tpe_db`.`soil` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_property_station`
    FOREIGN KEY (`station_id`)
    REFERENCES `tpe_db`.`station` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4570
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
