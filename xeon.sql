/*
MySQL Data Transfer

Source Server Version : 100136
Source Host           : localhost
Source Database       : xeon

Target Server Type    : MYSQL
Target Server Version : 100136
File Encoding         : 65001

Date: 2018-12-22 16:11:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cuentas
-- ----------------------------
DROP TABLE IF EXISTS `cuentas`;
CREATE TABLE `cuentas` (
`id_cuentas`  int(11) NOT NULL ,
`usuario`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`clave`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`apodo`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`rol`  int(11) NOT NULL ,
`pregunta`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`respuesta`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`amigos`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`enemigos`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`conectado`  tinyint(4) NOT NULL ,
`baneado`  tinyint(4) NOT NULL ,
`ultima_ip`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`ultima_conexion`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`banco_objetos`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`banco_kamas`  int(11) NOT NULL ,
`cercado`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`abonado`  int(11) NOT NULL ,
PRIMARY KEY (`id_cuentas`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci

;

-- ----------------------------
-- Records of cuentas
-- ----------------------------
BEGIN;
INSERT INTO `cuentas` VALUES ('1', 'test', 'test', 'tester', '1', 'delete', 'si', '', '', '0', '0', '127.0.0.1', '', '', '0', '', '0'), ('2', 'test2', 'test2', 'tester2', '1', 'delete', 'si', '', '', '0', '0', '127.0.0.1', '', '', '0', '', '0');
COMMIT;

-- ----------------------------
-- Table structure for personajes
-- ----------------------------
DROP TABLE IF EXISTS `personajes`;
CREATE TABLE `personajes` (
`id_personajes`  int(11) NOT NULL ,
`propietario`  int(11) NOT NULL ,
`nombre`  varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL ,
`genero`  tinyint(11) NOT NULL ,
`clase`  smallint(11) NOT NULL ,
`color1`  int(11) NOT NULL ,
`color2`  int(11) NOT NULL ,
`color3`  int(11) NOT NULL ,
PRIMARY KEY (`id_personajes`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=latin1 COLLATE=latin1_swedish_ci

;

-- ----------------------------
-- Records of personajes
-- ----------------------------
BEGIN;
INSERT INTO `personajes` VALUES ('1', '1', 'Xafer', '0', '8', '0', '0', '0');
COMMIT;
SET FOREIGN_KEY_CHECKS=1;
