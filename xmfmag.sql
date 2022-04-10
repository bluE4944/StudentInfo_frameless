/*
Navicat MySQL Data Transfer

Source Server         : test
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : xmfmag

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2022-04-09 18:11:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `clas_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `teac_id` int(4) unsigned NOT NULL,
  `clas_name` varchar(20) NOT NULL,
  `clas_start` date DEFAULT NULL,
  `clas_end` date DEFAULT NULL,
  `clas_week` int(1) NOT NULL,
  `clas_time` time NOT NULL,
  PRIMARY KEY (`clas_id`,`teac_id`),
  KEY `clas_id` (`clas_id`),
  KEY `FK_class_teacher_On_teac_id` (`teac_id`),
  CONSTRAINT `FK_class_teacher_On_teac_id` FOREIGN KEY (`teac_id`) REFERENCES `teacher` (`teac_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('18', '1', '周五19：20', null, null, '5', '19:20:00');
INSERT INTO `class` VALUES ('19', '2', '周六19：20', null, null, '6', '19:20:00');
INSERT INTO `class` VALUES ('20', '3', '周日19：20', null, null, '7', '19:20:00');
INSERT INTO `class` VALUES ('21', '3', '周二19：20', null, null, '2', '19:20:00');
INSERT INTO `class` VALUES ('22', '9', '周三18：30', null, null, '3', '18:30:00');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cour_id` int(6) unsigned NOT NULL AUTO_INCREMENT,
  `cour_name` varchar(20) NOT NULL,
  `cour_subject` varchar(20) NOT NULL,
  `cour_introduction` varchar(255) NOT NULL DEFAULT '默认简介',
  PRIMARY KEY (`cour_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'aa', '动力机械Ⅰ', '');
INSERT INTO `course` VALUES ('2', 'aaa', '结构与力', '');
INSERT INTO `course` VALUES ('3', 'aaa', '结构与力', '');
INSERT INTO `course` VALUES ('4', 'aaaa', '动力机械Ⅰ', '');
INSERT INTO `course` VALUES ('5', 'aadad', 'WeDo2.0', '');
INSERT INTO `course` VALUES ('6', 'asdad', 'WeDo2.0', '');
INSERT INTO `course` VALUES ('7', 'asdad', '结构与力', 'asddasd');

-- ----------------------------
-- Table structure for `family`
-- ----------------------------
DROP TABLE IF EXISTS `family`;
CREATE TABLE `family` (
  `fami_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) unsigned NOT NULL,
  `fami_name` varchar(20) NOT NULL DEFAULT '家长（默认姓名）',
  `fami_appellation` varchar(20) NOT NULL DEFAULT '家长（默认称呼）',
  `fami_phoneNumber` varchar(11) NOT NULL DEFAULT '1001（默认电话）',
  `fami_address` varchar(30) NOT NULL DEFAULT '地址（默认地址）',
  PRIMARY KEY (`fami_id`,`stu_id`),
  KEY `FK_family_student_On_stu_id` (`stu_id`),
  CONSTRAINT `FK_family_student_On_stu_id` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of family
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) unsigned NOT NULL,
  `teac_id` int(4) unsigned NOT NULL,
  `order_pirce` float(8,1) NOT NULL,
  `order_time` datetime NOT NULL,
  PRIMARY KEY (`order_id`,`stu_id`,`teac_id`),
  KEY `FK_order_student_On_stu_id` (`stu_id`),
  KEY `FK_order_teacher_On_teac_id` (`teac_id`),
  CONSTRAINT `FK_order_student_On_stu_id` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`),
  CONSTRAINT `FK_order_teacher_On_teac_id` FOREIGN KEY (`teac_id`) REFERENCES `teacher` (`teac_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `salary`
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary` (
  `sala_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `teac_id` int(4) unsigned NOT NULL,
  `sala_sum` double NOT NULL,
  `sala_detail` varchar(255) NOT NULL,
  PRIMARY KEY (`sala_id`,`teac_id`),
  KEY `FK_salary_teacher_On_teac_id` (`teac_id`),
  CONSTRAINT `FK_salary_teacher_On_teac_id` FOREIGN KEY (`teac_id`) REFERENCES `teacher` (`teac_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary
-- ----------------------------

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stu_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `clas_id` int(11) unsigned NOT NULL,
  `stu_name` varchar(10) NOT NULL,
  `stu_sex` varchar(1) NOT NULL,
  `stu_age` float(4,1) NOT NULL,
  `stu_remainPeriod` int(4) NOT NULL DEFAULT '0',
  `stu_totalPeriod` int(4) NOT NULL DEFAULT '0',
  `stu_start` date DEFAULT NULL,
  `stu_end` date DEFAULT NULL,
  PRIMARY KEY (`stu_id`,`clas_id`),
  KEY `FK_student_class_On_clas_id` (`clas_id`),
  KEY `stu_id` (`stu_id`),
  CONSTRAINT `FK_student_class_On_clas_id` FOREIGN KEY (`clas_id`) REFERENCES `class` (`clas_id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('63', '18', 'aaaa', '男', '10.0', '0', '0', '2020-04-26', null);
INSERT INTO `student` VALUES ('66', '18', '小老弟', '女', '10.5', '0', '0', '2020-06-13', null);

-- ----------------------------
-- Table structure for `student_clock`
-- ----------------------------
DROP TABLE IF EXISTS `student_clock`;
CREATE TABLE `student_clock` (
  `sclock_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) unsigned NOT NULL,
  `clas_id` int(11) unsigned NOT NULL,
  `cour_id` int(11) unsigned NOT NULL,
  `sclock_time` datetime NOT NULL,
  PRIMARY KEY (`sclock_id`,`stu_id`,`clas_id`,`cour_id`),
  KEY `FK_student_clock_stu_id_On_stu_id` (`stu_id`),
  KEY `FK_student_clock_class_On_clas_id` (`clas_id`),
  KEY `FK_student_clock_cour_On_cour_id` (`cour_id`),
  CONSTRAINT `FK_student_clock_class_On_clas_id` FOREIGN KEY (`clas_id`) REFERENCES `class` (`clas_id`),
  CONSTRAINT `FK_student_clock_cour_On_cour_id` FOREIGN KEY (`cour_id`) REFERENCES `course` (`cour_id`),
  CONSTRAINT `FK_student_clock_stu_id_On_stu_id` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student_clock
-- ----------------------------

-- ----------------------------
-- Table structure for `system`
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system` (
  `sys_id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `sname` varchar(255) NOT NULL,
  `slogin` varchar(255) NOT NULL,
  `snavName` varchar(255) NOT NULL,
  PRIMARY KEY (`sys_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system
-- ----------------------------
INSERT INTO `system` VALUES ('1', '管理系统', '欢迎登录', 'StudentInfo');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teac_id` int(4) unsigned NOT NULL AUTO_INCREMENT,
  `teac_name` varchar(20) NOT NULL,
  `teac_phoneNumber` varchar(20) NOT NULL DEFAULT '1001（默认电话）',
  `teac_address` varchar(30) NOT NULL DEFAULT '地址（默认地址）',
  PRIMARY KEY (`teac_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '张三', '默认电话', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('2', '小表弟', '默认电话', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('3', '小老弟', '默认电话', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('9', '小兄弟', '1001', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('11', '带聪明', '1101', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('16', '啊倒萨', '啊实打实打算', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('20', '阿松大', '阿松大', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('21', '十大', '撒大啊', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('22', '阿三大啊', '啊倒萨', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('23', '撒大大', '阿达萨达', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('24', 'asdff', 'dsafafa', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('25', 'asdfafa', 'sadfaf', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('26', 'asdfaf', 'sadfafa', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('27', 'asdfafa', 'sedfafa', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('28', 'asdfafa', 'sdfaf', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('29', 'asdfaf', 'sdfafa', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('30', 'asdfaf', 'sdfafa', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('31', 'sadada', 'asdad', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('32', 'asdad', 'sdada', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('33', 'asdada', 'sdaadad', '地址（默认地址）');
INSERT INTO `teacher` VALUES ('34', 'hhhh', '123456789090', '地址（默认地址）');

-- ----------------------------
-- Table structure for `teacher_clock`
-- ----------------------------
DROP TABLE IF EXISTS `teacher_clock`;
CREATE TABLE `teacher_clock` (
  `tclock_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `teac_id` int(4) unsigned NOT NULL,
  `tclock_am_onduty` datetime DEFAULT NULL,
  `tclock_am_offduty` datetime DEFAULT NULL,
  `tclock_pm_onduty` datetime DEFAULT NULL,
  `tclock_pm_offduty` datetime DEFAULT NULL,
  `tclock_night_onduty` datetime DEFAULT NULL,
  `tclock_night_offduty` datetime DEFAULT NULL,
  PRIMARY KEY (`tclock_id`,`teac_id`),
  KEY `clock_teac_id` (`teac_id`),
  CONSTRAINT `clock_teac_id` FOREIGN KEY (`teac_id`) REFERENCES `teacher` (`teac_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_clock
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(2) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` char(200) CHARACTER SET utf8mb4 NOT NULL,
  `phoneNumber` varchar(11) NOT NULL,
  `password` varchar(20) NOT NULL DEFAULT '123456',
  `administer` int(1) NOT NULL DEFAULT '2',
  `lastTime` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '13075846548', '123456', '1', '2020-04-07 15:52:15');
INSERT INTO `user` VALUES ('2', '小老弟', '123456', '123456', '1', '2020-04-09 21:20:38');
INSERT INTO `user` VALUES ('18', 'asfda', 'adsfa', '123456', '1', '2020-06-13 10:40:18');
INSERT INTO `user` VALUES ('19', 'asdfaf', 'sadfaf', '123456', '2', '2020-04-05 21:42:51');
INSERT INTO `user` VALUES ('20', '1111', '1111', '123456', '2', '2020-04-13 13:23:32');
INSERT INTO `user` VALUES ('21', 'asdfaf', 'sadfaf', '123456', '2', '2020-04-05 21:43:08');
INSERT INTO `user` VALUES ('22', 'asdfa', 'adsfa', '123456', '2', '2020-04-05 21:43:22');
INSERT INTO `user` VALUES ('23', 'asdfa', 'sadfaf', '123456', '2', '2020-04-05 21:43:30');
INSERT INTO `user` VALUES ('24', 'asdfaf', 'adsfa', '123456', '2', '2020-04-05 21:43:42');
INSERT INTO `user` VALUES ('25', 'sdafaf', 'sadfa', '123456', '2', '2020-04-05 21:43:49');
INSERT INTO `user` VALUES ('26', 'asfda', 'sdfa', '123456', '2', '2020-04-05 21:43:58');
INSERT INTO `user` VALUES ('27', 'asdfa', 'sdafa', '123456', '2', '2020-04-05 21:44:05');
INSERT INTO `user` VALUES ('28', 'wqeq', 'qweqe', '123456', '2', '2020-04-06 22:56:28');
INSERT INTO `user` VALUES ('29', '1231', '2131', '123456', '2', '2020-04-06 22:45:12');
