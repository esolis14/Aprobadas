-- MySQL Script generated by MySQL Workbench
-- lun 15 mar 2021 17:01:02
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema aprobadas_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `aprobadas_db` ;

-- -----------------------------------------------------
-- Schema aprobadas_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `aprobadas_db` ;
USE `aprobadas_db` ;

-- -----------------------------------------------------
-- Table `aprobadas_db`.`Usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aprobadas_db`.`Usuario` ;

CREATE TABLE IF NOT EXISTS `aprobadas_db`.`Usuario` (
  `usuario_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `password` VARCHAR(32) NOT NULL,
  `nombre` VARCHAR(16) NULL,
  `apellido` VARCHAR(30) NULL,
  `tlf` VARCHAR(9) NULL,
  `email` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`usuario_id`),
  UNIQUE INDEX `usuario_id_UNIQUE` (`usuario_id` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `aprobadas_db`.`Grado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aprobadas_db`.`Grado` ;

CREATE TABLE IF NOT EXISTS `aprobadas_db`.`Grado` (
  `grado_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre_grado` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`grado_id`),
  UNIQUE INDEX `grado_id_UNIQUE` (`grado_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aprobadas_db`.`Asignatura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aprobadas_db`.`Asignatura` ;

CREATE TABLE IF NOT EXISTS `aprobadas_db`.`Asignatura` (
  `asignatura_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre_asignatura` VARCHAR(255) NOT NULL,
  `grado` INT UNSIGNED NOT NULL,
  `curso` INT NULL,
  PRIMARY KEY (`asignatura_id`),
  INDEX `grado_idx` (`grado` ASC) VISIBLE,
  UNIQUE INDEX `asignatura_id_UNIQUE` (`asignatura_id` ASC) VISIBLE,
  CONSTRAINT `grado`
    FOREIGN KEY (`grado`)
    REFERENCES `aprobadas_db`.`Grado` (`grado_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aprobadas_db`.`Oferta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aprobadas_db`.`Oferta` ;

CREATE TABLE IF NOT EXISTS `aprobadas_db`.`Oferta` (
  `oferta_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `profesor` INT UNSIGNED NOT NULL,
  `precio` DECIMAL(4,2) NOT NULL,
  `asignatura` INT UNSIGNED NOT NULL,
  `descrip` VARCHAR(255) NULL,
  PRIMARY KEY (`oferta_id`),
  UNIQUE INDEX `oferta_id_UNIQUE` (`oferta_id` ASC) VISIBLE,
  INDEX `profesor_idx` (`profesor` ASC) VISIBLE,
  UNIQUE INDEX `profesor_UNIQUE` (`profesor` ASC) VISIBLE,
  INDEX `asignatura_idx` (`asignatura` ASC) VISIBLE,
  UNIQUE INDEX `asignatura_UNIQUE` (`asignatura` ASC) VISIBLE,
  CONSTRAINT `profesor`
    FOREIGN KEY (`profesor`)
    REFERENCES `aprobadas_db`.`Usuario` (`usuario_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `asignatura`
    FOREIGN KEY (`asignatura`)
    REFERENCES `aprobadas_db`.`Asignatura` (`asignatura_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aprobadas_db`.`Solicitud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aprobadas_db`.`Solicitud` ;

CREATE TABLE IF NOT EXISTS `aprobadas_db`.`Solicitud` (
  `solicitud_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `alumno` INT UNSIGNED NOT NULL,
  `oferta` INT UNSIGNED NOT NULL,
  `estado` VARCHAR(10) NOT NULL,
  `valoracion` INT NULL,
  INDEX `alumno_idx` (`alumno` ASC) VISIBLE,
  PRIMARY KEY (`solicitud_id`),
  UNIQUE INDEX `clase_id_UNIQUE` (`solicitud_id` ASC) VISIBLE,
  INDEX `oferta_idx` (`oferta` ASC) VISIBLE,
  UNIQUE INDEX `alumno_UNIQUE` (`alumno` ASC) VISIBLE,
  UNIQUE INDEX `oferta_UNIQUE` (`oferta` ASC) VISIBLE,
  CONSTRAINT `alumno`
    FOREIGN KEY (`alumno`)
    REFERENCES `aprobadas_db`.`Usuario` (`usuario_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `oferta`
    FOREIGN KEY (`oferta`)
    REFERENCES `aprobadas_db`.`Oferta` (`oferta_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `aprobadas_db`.`Grado`
-- -----------------------------------------------------
START TRANSACTION;
USE `aprobadas_db`;
INSERT INTO `aprobadas_db`.`Grado` (`grado_id`, `nombre_grado`) VALUES (1, 'Grado en Ingeniería en Tecnología de Telecomunicación');
INSERT INTO `aprobadas_db`.`Grado` (`grado_id`, `nombre_grado`) VALUES (2, 'Grado en Ingeniería en Tecnología Industrial');

COMMIT;


-- -----------------------------------------------------
-- Data for table `aprobadas_db`.`Asignatura`
-- -----------------------------------------------------
START TRANSACTION;
USE `aprobadas_db`;
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (1, 'Álgebra', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (2, 'Análisis de Circuitos', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (3, 'Cálculo I', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (4, 'Cálculo II', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (5, 'Dispositivos y Circuitos Electrónicos', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (6, 'Economía', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (7, 'Electrónica Básica', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (8, 'Estadística', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (9, 'Física', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (10, 'Tratamiento de Señales', 1, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (11, 'Ampliación de Física', 1, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (12, 'Ampliación de Matemáticas', 1, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (13, 'Arquitectura de Redes y Servicios de Telecomunicación', 1, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (14, 'Campos Electromagneticos', 1, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (15, 'Electrónica Digital', 1, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (16, 'Fundamentos de Programación', 1, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (17, 'Programación en Entornos Distribuidos', 1, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (18, 'Teoría de la Comunicación', 1, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (19, 'Arquitectura de Sistemas de Información', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (20, 'Comunicaciones Móviles', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (21, 'Electrónica de Circuitos', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (22, 'Electrónica para la Conversión de Energía', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (23, 'Electrotecnia y Electrónica de Potencia', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (24, 'Instrumentación Electrónica', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (25, 'Planificación de Redes y Modelado', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (26, 'Procesado de Señales Multimedia', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (27, 'Redes de Acceso', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (28, 'Redes de Transporte', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (29, 'Servicios Telemáticos Avanzados', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (30, 'Sistemas de Alta Frecuencia', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (31, 'Sistemas de Radiocomunicación', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (32, 'Sistemas de Telecomunicación', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (33, 'Sistemas Digitales', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (34, 'Sistemas Electrónicos de Alimentación', 1, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (35, 'Administración de Empresas', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (36, 'Antenas y Propagación', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (37, 'Automatización y Comunicaciones Industriales', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (38, 'Circuitos de Telecomunicación', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (39, 'Comunicación en Euskera: Ingeniería', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (40, 'Comunicaciones Ópticas', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (41, 'Despliegue y Gestión de Redes y Servicios', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (42, 'Diseños Basados en Microprocesadores', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (43, 'Fundamentos de Ciencia de Materiales', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (44, 'Laboratorio de Electrónica de Comunicaciones', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (45, 'Laboratorio de Sistemas Digitales', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (46, 'Liderazgo y Emprendizaje', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (47, 'Norma y Uso de la Lengua Vasca', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (48, 'Óptica Aplicada a las Telecomunicaciones', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (49, 'Proyectos de Ingeniería', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (50, 'Radar y Sistemas de Navegación por Satélite', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (51, 'Redes y Servicios Móviles', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (52, 'Servicios Multimedia', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (53, 'Sistemas de Radio y Televisión Digital', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (54, 'Técnicas Avanzadas de Programación', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (55, 'Técnicas de Inteligencia Artificial', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (56, 'Tecnología de Ingeniería Telemática', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (57, 'Tecnología de la Instalaciones Eléctricas', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (58, 'Tecnología de Sistemas de Telecomunicación', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (59, 'Tecnología de Sistemas Electrónicos', 1, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (60, 'Álgebra', 2, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (61, 'Ampliación de Física', 2, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (62, 'Ampliación de Gráficos de Ingeniería', 2, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (63, 'Cálculo', 2, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (64, 'Física', 2, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (65, 'Gráficos de Ingeniería', 2, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (66, 'Informática', 2, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (67, 'Química', 2, 1);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (68, 'Ampliación de Ecuaciones Diferenciales', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (69, 'Ampliación de Matemáticas', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (70, 'Economía', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (71, 'Electrotecnia', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (72, 'Estadística', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (73, 'Fundamentos de Ciencia de Materiales', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (74, 'Mecánica', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (75, 'Mecánica Aplicada', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (76, 'Mecánica Fluidos', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (77, 'Termodinámica', 2, 2);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (78, 'Ampliación de métodos numéricos', 2, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (79, 'Análisis y Funcionamiento de Máquinas Eléctricas', 2, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (80, 'Automática y Control', 2, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (81, 'Cálculo Elástico de Sólidos', 2, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (82, 'Elasticidad y Resistencia de Materiales', 2, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (83, 'Electrónica General', 2, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (84, 'Tecnología Química', 2, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (85, 'Teoría de Mecanismos y Vibraciones Mecánicas', 2, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (86, 'Termotecnia', 2, 3);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (87, 'Automatización Industrial', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (88, 'Cálculo de Máquinas', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (89, 'Centrales Fluidomecánicas', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (90, 'Ciencia e Ingeniería de Materiales', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (91, 'Ciencia y Tecnología Ambiental', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (92, 'Comunicación en Euskera: Ingeniería', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (93, 'Control por Computador', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (94, 'Electrónica Industrial', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (95, 'Elementos de Máquinas', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (96, 'Energías Alternativas', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (97, 'Ingeniería de las Reacciones Químicas', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (98, 'Ingeniería Térmica', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (99, 'Integración de Tecnologías de Generación en el Sistema Eléctrico', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (100, 'Máquinas Térmicas e Hidráulicas', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (101, 'Materiales Estructurales: Comportamiento en Servicio y Mecánica de Fractura', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (102, 'Norma y Uso de la Lengua Vasca', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (103, 'Organización de Empresas', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (104, 'Procesos de Separación y Purificación', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (105, 'Proyectos de Ingeniería', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (106, 'Tecnología Eléctrica', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (107, 'Tecnología Mecánica', 2, 4);
INSERT INTO `aprobadas_db`.`Asignatura` (`asignatura_id`, `nombre_asignatura`, `grado`, `curso`) VALUES (108, 'Teoría de Estructuras y Construcción', 2, 4);

COMMIT;
