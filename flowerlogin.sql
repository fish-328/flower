/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 26/06/2025 00:33:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for flowerlogin
-- ----------------------------
DROP TABLE IF EXISTS `flowerlogin`;
CREATE TABLE `flowerlogin`  (
  `user-id` int(0) NOT NULL AUTO_INCREMENT,
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`user-id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flowerlogin
-- ----------------------------
INSERT INTO `flowerlogin` VALUES (1, '123456', '1234567');
INSERT INTO `flowerlogin` VALUES (2, '1234567', '1234567');
INSERT INTO `flowerlogin` VALUES (4, '1', '11');

SET FOREIGN_KEY_CHECKS = 1;
