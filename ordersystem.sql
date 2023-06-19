/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50739 (5.7.39-log)
 Source Host           : localhost:3306
 Source Schema         : ordersystem

 Target Server Type    : MySQL
 Target Server Version : 50739 (5.7.39-log)
 File Encoding         : 65001

 Date: 18/06/2023 21:55:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business`  (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `restaurant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`b_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of business
-- ----------------------------

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_id` int(11) NOT NULL,
  `phone_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`c_id`) USING BTREE,
  INDEX `t_id`(`t_id`) USING BTREE,
  INDEX `phone_num`(`phone_num`) USING BTREE,
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`t_id`) REFERENCES `diningtable` (`t_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 1, '1');
INSERT INTO `customer` VALUES (2, 1, '123');
INSERT INTO `customer` VALUES (3, 1, '1212');
INSERT INTO `customer` VALUES (4, 2, '123213123');
INSERT INTO `customer` VALUES (5, 3, '21342343');
INSERT INTO `customer` VALUES (6, 4, '123123123123');
INSERT INTO `customer` VALUES (7, 5, '13863636363');
INSERT INTO `customer` VALUES (8, 6, '123');

-- ----------------------------
-- Table structure for diningtable
-- ----------------------------
DROP TABLE IF EXISTS `diningtable`;
CREATE TABLE `diningtable`  (
  `t_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diningtable
-- ----------------------------
INSERT INTO `diningtable` VALUES (1, 1);
INSERT INTO `diningtable` VALUES (2, 1);
INSERT INTO `diningtable` VALUES (3, 1);
INSERT INTO `diningtable` VALUES (4, 1);
INSERT INTO `diningtable` VALUES (5, 1);
INSERT INTO `diningtable` VALUES (6, 1);

-- ----------------------------
-- Table structure for meal
-- ----------------------------
DROP TABLE IF EXISTS `meal`;
CREATE TABLE `meal`  (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `meal_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of meal
-- ----------------------------
INSERT INTO `meal` VALUES (1, '123', 33.99);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `o_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_id` int(11) NOT NULL,
  `c_id` int(11) NOT NULL,
  `phone_num` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cost_money` decimal(10, 2) NULL DEFAULT 0.00,
  PRIMARY KEY (`o_id`) USING BTREE,
  INDEX `t_id`(`t_id`) USING BTREE,
  INDEX `c_id`(`c_id`) USING BTREE,
  INDEX `phone_num`(`phone_num`) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`t_id`) REFERENCES `diningtable` (`t_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`c_id`) REFERENCES `customer` (`c_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`phone_num`) REFERENCES `customer` (`phone_num`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for order_meal
-- ----------------------------
DROP TABLE IF EXISTS `order_meal`;
CREATE TABLE `order_meal`  (
  `o_id` int(11) NOT NULL,
  `m_id` int(11) NOT NULL,
  PRIMARY KEY (`o_id`, `m_id`) USING BTREE,
  INDEX `m_id`(`m_id`) USING BTREE,
  CONSTRAINT `order_meal_ibfk_1` FOREIGN KEY (`o_id`) REFERENCES `order` (`o_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `order_meal_ibfk_2` FOREIGN KEY (`m_id`) REFERENCES `meal` (`m_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_meal
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
