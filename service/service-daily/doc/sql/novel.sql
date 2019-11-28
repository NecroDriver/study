/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : study

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 14/11/2019 17:52:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for novel
-- ----------------------------
DROP TABLE IF EXISTS `novel`;
CREATE TABLE `novel`
(
    `id`          int(11)                                                       NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `novel_code`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '小说编号',
    `novel_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '小说名称',
    `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '简介',
    `url`         varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '小说地址',
    `cover_img`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '封面路径',
    `flag_delete` tinyint(4)                                                    NOT NULL DEFAULT 0 COMMENT '是否删除，0：否 1：是',
    `flag_update` tinyint(4)                                                    NOT NULL DEFAULT 0 COMMENT '是否更新,0:否 1:是',
    `flag_end`    tinyint(4)                                                    NOT NULL DEFAULT 0 COMMENT '是否完结,0:否 1:是',
    `create_time` datetime(0)                                                   NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
    `creator`     varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '创建人',
    `creator_ip`  varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '创建人ip',
    `modify_time` datetime(0)                                                   NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
    `modifier`    varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '修改人',
    `modifier_ip` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '修改人ip',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
