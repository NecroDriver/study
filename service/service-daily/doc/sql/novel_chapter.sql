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

 Date: 14/11/2019 17:57:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for novel_chapter
-- ----------------------------
DROP TABLE IF EXISTS `novel_chapter`;
CREATE TABLE `novel_chapter`
(
    `id`            int(11)                                                       NOT NULL AUTO_INCREMENT,
    `novel_code`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '小说编号',
    `chapter_code`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '章节编号',
    `chapter_name`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '章节名称',
    `url`           varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '章节地址',
    `content`       text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         NOT NULL COMMENT '章节内容',
    `display_order` int(11)                                                       NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `flag_delete`   tinyint(4)                                                    NOT NULL DEFAULT 0 COMMENT '是否删除，0：否 1：是',
    `create_time`   datetime(0)                                                   NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
    `creator`       varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '创建人',
    `creator_ip`    varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '创建人ip',
    `modify_time`   datetime(0)                                                   NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '修改时间',
    `modifier`      varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '修改人',
    `modifier_ip`   varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '修改人ip',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `novel_code` (`novel_code`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

alter table novel_chapter add column chapter_code VARCHAR(20) not null default '' COMMENT '章节编号' AFTER novel_code;
