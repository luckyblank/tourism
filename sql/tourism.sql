/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80011 (8.0.11)
 Source Host           : localhost:3306
 Source Schema         : shop_bd

 Target Server Type    : MySQL
 Target Server Version : 80011 (8.0.11)
 File Encoding         : 65001

 Date: 09/07/2023 18:27:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `p_id` bigint(11) NULL DEFAULT 0 COMMENT '父级评论id',
  `foreign_id` bigint(11) NULL DEFAULT NULL COMMENT '业务模块id',
  `target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回复对象',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `delete_state` int(1) NULL DEFAULT 0 COMMENT '0未删除 1已删除',
  `icon` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (2, '您说得对！！！呵呵', 4, 5, NULL, NULL, '2023-01-28 10:27:51', 0, NULL);
INSERT INTO `comment` VALUES (5, '今天写的代码一定没bug', 3, 0, NULL, NULL, '2023-01-28 13:15:55', 0, NULL);
INSERT INTO `comment` VALUES (7, '我是bug大佬', 4, 0, NULL, NULL, '2023-01-28 16:29:02', 0, NULL);
INSERT INTO `comment` VALUES (16, '大家早上好', 1, 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `comment` VALUES (36, '哈哈哈哈', 1, 0, 14, NULL, '2023-02-11 22:45:27', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (68, '加一，我也好喜欢啊呜呜', 1, 0, 16, NULL, '2023-02-23 21:50:18', 0, '1675875216748.jpg');
INSERT INTO `comment` VALUES (69, '哈呵呵', 3, 0, 14, NULL, '2023-02-26 17:54:35', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (70, '呵呵made', 3, 69, 14, NULL, '2023-02-26 17:54:43', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (71, 'sb', 1, 69, 14, NULL, '2023-02-26 17:55:45', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (72, '2222', 1, 69, 14, NULL, '2023-02-26 17:57:32', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (73, '111', 1, 69, 14, NULL, '2023-02-26 18:00:23', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (74, '222', 1, 69, 14, NULL, '2023-02-26 18:00:28', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (75, '2233232', 1, 69, 14, NULL, '2023-02-26 18:08:08', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (76, '4444', 1, 69, 14, NULL, '2023-02-26 18:08:44', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (77, '666', 1, 69, 14, NULL, '2023-02-26 18:27:09', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (78, '12212', 1, 69, 14, NULL, '2023-02-26 18:46:47', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (79, '7777', 1, 69, 14, NULL, '2023-02-26 18:49:17', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (80, '555555', 1, 69, 14, NULL, '2023-02-26 19:02:16', 1, '1675875216748.jpg');
INSERT INTO `comment` VALUES (81, '777', 33, 69, 14, NULL, '2023-02-26 19:06:49', 1, '1677162190185.jpg');
INSERT INTO `comment` VALUES (82, '优美', 33, 0, 14, NULL, '2023-02-26 19:07:22', 1, '1677162190185.jpg');
INSERT INTO `comment` VALUES (83, '2333', 3, 69, 14, NULL, '2023-02-26 21:00:22', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (84, '22222', 3, 69, 14, NULL, '2023-02-26 21:57:10', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (85, '呵呵sb', 3, 69, 14, NULL, '2023-02-27 10:16:12', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (86, '66666排骨 是不是那个子级放进里面了', 3, 69, 14, NULL, '2023-02-27 11:05:58', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (87, '哈哈哈哈哈哈', 3, 0, 19, NULL, '2023-02-28 17:41:22', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (88, '哈哈哈哈', 3, 0, 19, NULL, '2023-02-28 17:41:33', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (89, '111', 3, 0, 19, NULL, '2023-02-28 17:41:56', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (90, '222', 3, 89, 19, NULL, '2023-02-28 17:42:13', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (91, '2222', 3, 0, 19, NULL, '2023-02-28 17:42:26', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (92, '3333', 3, 91, 19, NULL, '2023-02-28 17:42:33', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (93, '55', 3, 0, 19, NULL, '2023-02-28 17:43:01', 0, '1675875375870.jpg');
INSERT INTO `comment` VALUES (94, '？？？', 33, 93, 19, NULL, '2023-02-28 17:46:25', 0, '1677162190185.jpg');
INSERT INTO `comment` VALUES (95, '怎么回事', 33, 93, 19, NULL, '2023-02-28 17:46:34', 0, '1677162190185.jpg');
INSERT INTO `comment` VALUES (96, '2333', 3, 0, 14, NULL, '2023-03-06 15:46:13', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (97, '1111', 3, 96, 14, NULL, '2023-03-06 15:46:28', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (98, '11', 3, 96, 14, NULL, '2023-03-06 15:46:42', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (99, '111', 3, 0, 14, NULL, '2023-03-06 15:46:54', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (100, '111', 3, 0, 14, NULL, '2023-03-06 15:46:58', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (101, '111', 3, 100, 14, NULL, '2023-03-06 15:47:03', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (102, '1111', 3, 0, 14, NULL, '2023-03-06 15:47:15', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (103, '111111111', 3, 102, 14, NULL, '2023-03-06 15:53:14', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (104, '11111111', 3, 0, 14, NULL, '2023-03-06 15:53:18', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (105, '你好啊', 3, 0, 14, NULL, '2023-03-09 20:42:04', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (106, '2333', 3, 105, 14, NULL, '2023-03-09 20:42:12', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (107, '2333', 3, 0, 14, NULL, '2023-03-16 19:57:59', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (108, '呜呜呜没钱睡大街', 3, 0, 14, NULL, '2023-03-16 20:00:15', 0, '1675875375870.jpg');
INSERT INTO `comment` VALUES (109, '12223', 3, 108, 14, NULL, '2023-03-23 15:39:07', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (110, '一元一个赞&nbsp;', 3, 0, 20, NULL, '2023-03-23 15:40:29', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (111, '33', 3, 0, 20, NULL, '2023-03-23 15:40:41', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (112, '一元攒攒', 3, 0, 20, NULL, '2023-03-23 15:40:53', 0, '1675875375870.jpg');
INSERT INTO `comment` VALUES (113, '快来赞赞me', 3, 0, 20, NULL, '2023-03-23 15:41:05', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (114, '欢迎大家入住', 3, 0, 23, NULL, '2023-03-26 14:52:47', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (115, 'nice', 3, 114, 23, NULL, '2023-03-26 14:52:55', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (116, 'hhhhhhhh', 53, 108, 14, NULL, '2023-03-26 18:44:14', 1, '1679827376702.png');
INSERT INTO `comment` VALUES (117, '救命啊前端', 53, 108, 14, NULL, '2023-05-07 00:06:07', 1, '1679827376702.png');
INSERT INTO `comment` VALUES (118, '还有', 3, 114, 23, NULL, '2023-05-24 16:55:18', 1, '1675875375870.jpg');
INSERT INTO `comment` VALUES (119, '大家快来吧', 53, 0, 25, NULL, '2023-05-24 16:58:02', 0, '1679827376702.png');
INSERT INTO `comment` VALUES (120, '2333', 55, 119, 25, NULL, '2023-05-24 17:02:01', 0, '1684918760850.jpg');
INSERT INTO `comment` VALUES (121, '下次发点景点图片趴谢谢啦', 55, 0, 25, NULL, '2023-05-24 17:02:44', 0, '1684918760850.jpg');
INSERT INTO `comment` VALUES (122, '我可以借你钱', 55, 108, 14, NULL, '2023-05-24 17:31:42', 1, '1684918760850.jpg');
INSERT INTO `comment` VALUES (123, '老好人你好', 56, 108, 14, NULL, '2023-05-27 13:18:42', 1, '1685164457520.png');
INSERT INTO `comment` VALUES (124, 'sb老师', 56, 108, 14, NULL, '2023-07-02 00:37:50', 0, '1685164457520.png');
INSERT INTO `comment` VALUES (125, '21677627232', 3, 108, 14, NULL, '2023-07-09 17:44:49', 0, '1675875375870.jpg');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `ID` bigint(100) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ADD_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '添加人ID',
  `ADD_TIME` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `DELETE_STATUS` int(1) NULL DEFAULT 0 COMMENT '删除标志',
  `USER_ID` varchar(46) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户ID',
  `USER_NAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `NAME` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `CONTENT` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `STATE` int(1) NULL DEFAULT 0 COMMENT '状态0未回复 1回复',
  `p_id` bigint(20) NULL DEFAULT NULL COMMENT '父级id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (38, NULL, '2023-05-20 19:19:17', 0, '3', 'lisi', '小黄', '你好，在吗。', 1, NULL);
INSERT INTO `message` VALUES (39, 1, '2023-05-20 19:19:40', 0, NULL, 'root', '小黄', '在的，上帝您好。', 2, 38);
INSERT INTO `message` VALUES (40, NULL, '2023-05-24 17:27:29', 0, '55', 'user01', '小黄1', '你好', 1, NULL);
INSERT INTO `message` VALUES (41, 1, '2023-05-24 17:30:37', 0, NULL, 'root', '小黄', 'hello,需要什么帮助吗', 2, 40);
INSERT INTO `message` VALUES (42, NULL, '2023-05-24 17:49:08', 0, '3', 'lisi', '小黄', 'hi', 0, NULL);
INSERT INTO `message` VALUES (43, NULL, '2023-05-27 13:19:52', 0, '56', 'xiaocheng', '小城', 'hello', 1, NULL);
INSERT INTO `message` VALUES (44, 1, '2023-05-27 13:20:11', 0, NULL, 'root', '小黄', 'hi', 2, 43);
INSERT INTO `message` VALUES (45, 1, '2023-07-09 16:29:44', 0, NULL, 'root', '小黄233', '4333333333333', 2, 43);
INSERT INTO `message` VALUES (46, NULL, '2023-07-09 17:50:20', 0, '3', 'lisi', '小黄', '2777218712', 1, NULL);
INSERT INTO `message` VALUES (47, 1, '2023-07-09 17:50:50', 0, NULL, 'root', '小黄233', '8732823732', 2, 46);

-- ----------------------------
-- Table structure for scenic_spot
-- ----------------------------
DROP TABLE IF EXISTS `scenic_spot`;
CREATE TABLE `scenic_spot`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ADD_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '添加人ID',
  `ADD_TIME` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `DELETE_STATUS` int(1) NULL DEFAULT 0 COMMENT '删除标志',
  `MODIFY_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '修改人ID',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `SPOT_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '景点名称',
  `SPOT_INTRO` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '景点简介',
  `SPOT_STAR` int(1) NULL DEFAULT NULL COMMENT '景点星级',
  `SPOT_ADDRESS` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '景点地址',
  `OPEN_TIME` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '开放时间',
  `TICKETS_MESSAGE` double NULL DEFAULT NULL COMMENT '门票',
  `STATE` int(1) NULL DEFAULT NULL COMMENT '状态',
  `IMG_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图片',
  `order_type` int(1) NULL DEFAULT 3 COMMENT '订单类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scenic_spot
-- ----------------------------
INSERT INTO `scenic_spot` VALUES (1, 1, '2023-01-20 21:06:28', 0, 1, '2023-02-09 01:26:22', '广西南宁一日游', '鼎湖山位于广西省南宁市境东北部，距南宁市区18公里，由10多座山组成，总面积11.33平方公里。主峰海拔1000米，是珠江三角洲地区的最高峰。鼎湖山以其是集风景旅游、科学研究、宗教朝拜于一体的胜地而被称为岭南四大名山之首  。\n鼎湖山的得名，众说纷纭，有说是因山顶有湖，四时不涸，故名顶湖；有说是因中峰圆秀，山麓诸峰三歧, 远望有如鼎峙，故名鼎湖；又有民间传说黄帝曾赐鼎于此，故习惯称作鼎湖山。 \n1979年鼎湖山是我国第一个国家级自然保护区，是我国首批世界 生物圈保护，是联合国教科文组织人与生物圈（MAB)定位研究站，被誉为\"活的自然博物馆\"。其负离子含量为全国之最享有\"天然氧吧\"之美称', 4, '南宁市青秀区', '8:00-17:00', 100, 2, '1675877179863.png', 3);
INSERT INTO `scenic_spot` VALUES (2, 1, '2023-01-21 11:35:20', 0, 1, '2023-02-09 01:29:02', '广西鼎湖山', '鼎湖山位于广西省南宁市境东北部，距南宁市区18公里，由10多座山组成，总面积11.33平方公里。主峰海拔1000米，是珠江三角洲地区的最高峰。鼎湖山以其是集风景旅游、科学研究、宗教朝拜于一体的胜地而被称为岭南四大名山之首  。\n鼎湖山的得名，众说纷纭，有说是因山顶有湖，四时不涸，故名顶湖；有说是因中峰圆秀，山麓诸峰三歧, 远望有如鼎峙，故名鼎湖；又有民间传说黄帝曾赐鼎于此，故习惯称作鼎湖山。 \n1979年鼎湖山是我国第一个国家级自然保护区，是我国首批世界 生物圈保护，是联合国教科文组织人与生物圈（MAB)定位研究站，被誉为\"活的自然博物馆\"。其负离子含量为全国之最享有\"天然氧吧\"之美称', 3, '南宁市青秀区', '8:00-17:00', 20, 1, '1675877339701.png', 3);
INSERT INTO `scenic_spot` VALUES (3, 1, '2023-02-04 12:56:02', 0, NULL, '2023-02-04 12:56:07', '广西南宁天塔', '鼎湖山位于广西省南宁市境东北部，距南宁市区18公里，由10多座山组成，总面积11.33平方公里。主峰海拔1000米，是珠江三角洲地区的最高峰。鼎湖山以其是集风景旅游、科学研究、宗教朝拜于一体的胜地而被称为岭南四大名山之首  。\n鼎湖山的得名，众说纷纭，有说是因山顶有湖，四时不涸，故名顶湖；有说是因中峰圆秀，山麓诸峰三歧, 远望有如鼎峙，故名鼎湖；又有民间传说黄帝曾赐鼎于此，故习惯称作鼎湖山。 \n1979年鼎湖山是我国第一个国家级自然保护区，是我国首批世界 生物圈保护，是联合国教科文组织人与生物圈（MAB)定位研究站，被誉为\"活的自然博物馆\"。其负离子含量为全国之最享有\"天然氧吧\"之美称', 5, '南宁市青秀区', '9:00-18:00', 100, 1, '1675875518671.png', 3);
INSERT INTO `scenic_spot` VALUES (4, 1, '2023-02-07 10:23:27', 0, 1, '2023-02-09 01:31:39', '火星一日游', '鼎湖山位于广西省南宁市境东北部，距南宁市区18公里，由10多座山组成，总面积11.33平方公里。主峰海拔1000米，是珠江三角洲地区的最高峰。鼎湖山以其是集风景旅游、科学研究、宗教朝拜于一体的胜地而被称为岭南四大名山之首  。\n鼎湖山的得名，众说纷纭，有说是因山顶有湖，四时不涸，故名顶湖；有说是因中峰圆秀，山麓诸峰三歧, 远望有如鼎峙，故名鼎湖；又有民间传说黄帝曾赐鼎于此，故习惯称作鼎湖山。 \n1979年鼎湖山是我国第一个国家级自然保护区，是我国首批世界 生物圈保护，是联合国教科文组织人与生物圈（MAB)定位研究站，被誉为\"活的自然博物馆\"。其负离子含量为全国之最享有\"天然氧吧\"之美称', 5, '南宁市青秀区', '8:00-17:00', 100000, 1, '1675877496702.png', 3);
INSERT INTO `scenic_spot` VALUES (5, 1, '2023-02-09 01:35:09', 0, NULL, NULL, '广州乌镇', '乌镇是一个历史悠久，文化氛围浓郁的水乡古镇。这时除了具备小桥、流水、人家的水乡风情和精巧雅致的民居建筑之外，更多地飘逸着一股浓郁的历史和文化气息。在这座小镇，历史上曾出过64个进士，161个举人。现代中国的文学巨匠茅盾，也诞生在这个小镇上', 5, '广州市越秀区', '8:00-17:00', 100, 1, '1675877690974.jpg', 3);
INSERT INTO `scenic_spot` VALUES (6, 1, '2023-02-09 01:36:15', 0, NULL, NULL, '广州光棍山', '乌镇是一个历史悠久，文化氛围浓郁的水乡古镇。这时除了具备小桥、流水、人家的水乡风情和精巧雅致的民居建筑之外，更多地飘逸着一股浓郁的历史和文化气息。在这座小镇，历史上曾出过64个进士，161个举人。现代中国的文学巨匠茅盾，也诞生在这个小镇上', 3, '广州市越秀区', '9:00-17:00', 300, 1, '1675877690974.jpg', 3);
INSERT INTO `scenic_spot` VALUES (7, 1, '2023-02-09 01:39:41', 0, NULL, NULL, '广西南宁天塔', '乌镇是一个历史悠久，文化氛围浓郁的水乡古镇。这时除了具备小桥、流水、人家的水乡风情和精巧雅致的民居建筑之外，更多地飘逸着一股浓郁的历史和文化气息。在这座小镇，历史上曾出过64个进士，161个举人。现代中国的文学巨匠茅盾，也诞生在这个小镇上', 5, '南宁市青秀区', '8:00-19:00', 666, 1, '1675877179863.png', 3);
INSERT INTO `scenic_spot` VALUES (8, 1, '2023-02-16 20:11:30', 0, NULL, NULL, '乌镇', '桂林地区属岩溶地貌，这些特殊的地貌与景象万千的漓江及其周围美丽迷人的田园风光融为一体，形成了独具一格、驰名中外的“山清、水秀、洞奇、石美”的“桂林山水”。这时的山，平地拔起，千姿百态;漓江的水，蜿蜒曲折，明洁如镜;山多有洞，洞幽景奇，瑰丽壮观;洞中怪石，鬼斧神工，琳琅满目，而自上而下应有“山水甲天下”的赞誉', 3, '桂林市象山区', '8:00-17:00', 666, 1, '1676549475431.jpg', 3);
INSERT INTO `scenic_spot` VALUES (9, 1, '2023-02-16 20:17:47', 0, NULL, NULL, '北戴河', '北戴河位于河北省秦皇岛市西南，南临渤海，北靠联峰山，海滩漫长曲折，沙软潮平，林木苍翠，水面凉气轻拂，是著名的海滨避暑胜地。盛夏季节，这里气候宜人，游人置身其间，心旷神怡，暑气皆消。这时还是神州九大观日处之一，北戴河观日处位于北戴河海滨，东北端的鹰角亭为最佳地点', 3, '秦皇岛市北戴河区\n', '8:00-17:00', 88, 1, '1675877496702.png', 3);
INSERT INTO `scenic_spot` VALUES (10, 1, '2023-02-16 20:27:39', 0, NULL, NULL, '​九寨沟', '以绝天下的原始、神秘而闻名。自然景色兼有湖泊、瀑布、雪山、森林之美，有“童话世界”的美誉。这时雪峰玉立，青山流水，交相辉映。这时的瀑布、溪流更是迷人，如飞珠撒玉，异常雄伟秀丽。其中有千年古木，奇花异草，四时变化，色彩纷呈，倒影斑斓，气象万千，是夏季消暑的理想之地', 3, '济南市历下区', '8:00-17:00', 2, 1, '1675877496702.png', 3);
INSERT INTO `scenic_spot` VALUES (11, 1, '2023-02-20 14:04:58', 0, 1, '2023-02-20 14:07:21', '神农架冰山', '神农架冰山位于湖北省，这里有奇特的风洞、雷洞、闪洞、雾洞等自然奇观。其中神秘莫测的冰洞里还有冰柱、冰剑、冰坠、冰塔、冰珠等，千种奇象，万种神态，寒光皎洁，妖娆夺目。如果你喜欢，还可以在这里做一次惊心动魄的漂流。总的来说，在炎炎的夏季来这里感受一份难得的凉爽，是最好不过的事情了', 3, '武汉市东西湖区', '8:00-17:00', 888, 1, '1676873238354.png', 3);
INSERT INTO `scenic_spot` VALUES (13, 1, '2023-02-28 14:28:48', 0, NULL, NULL, '武汉江滩', '江滩位于长江武汉市区西北岸，面积160万平方米，与沿江大道景观相邻，与龙王庙景点相连，与江汉路步行街相接，与黄鹤楼景区相望，与长江百舸争流相映，构成武汉市中心区独具魅力的景观中心。“两江四堤八林带，火树银花不夜天”，这是一位诗人对武汉江滩美景的赞颂。在武汉三镇的水景中，武汉江滩可谓是这座滨江城市中一道最美丽的风景。三三两两的市民或游人悠闲地走在绿树繁花之中，穿行于鹅卵石铺就的小径，享受着忙碌生活中难得的闲适。江滩有三层观水平台，其中高层观景台比马路高1米多，第二层是观水平台，一年之中有两个月会被水淹掉，到了汛期会有如在海边的感觉，最低一层的平台，在枯水季节会现出沙滩，人们可以拾级而下，和江水靠得更近', 2, '武汉市江汉区', '7:00-23:00', 5, 1, '1677565697553.png', 3);
INSERT INTO `scenic_spot` VALUES (14, NULL, '2023-02-28 14:33:35', 0, NULL, NULL, '海口江滩', '海口江滩海口市中心区独具魅力的景观中心。海口江滩可谓是这座滨江城市中一道最美丽的风景。三三两两的市民或游人悠闲地走在绿树繁花之中，穿行于鹅卵石铺就的小径，享受着忙碌生活中难得的闲适。江滩有三层观水平台，其中高层观景台比马路高1米多，第二层是观水平台，一年之中有两个月会被水淹掉，到了汛期会有如在海边的感觉，最低一层的平台，在枯水季节会现出沙滩，人们可以拾级而下，和江水靠得更近', 4, '海口市美兰区', '7:00-23:00', 100, 1, '1675877496702.png', 3);

-- ----------------------------
-- Table structure for strategy
-- ----------------------------
DROP TABLE IF EXISTS `strategy`;
CREATE TABLE `strategy`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ADD_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '添加人ID',
  `ADD_TIME` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `DELETE_STATUS` int(1) NULL DEFAULT 0 COMMENT '删除标志',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `IMG_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'logo图片地址',
  `TITLE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `RATING` int(1) NULL DEFAULT NULL COMMENT '等级',
  `SUMMARY` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `INTRO_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容图片地址',
  `STATE` int(1) NULL DEFAULT 1 COMMENT '状态',
  `liked` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '点赞数',
  `comments` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '评论数',
  `collects` int(20) UNSIGNED NULL DEFAULT 0 COMMENT '收藏数',
  `product_type` int(10) NULL DEFAULT 1 COMMENT '攻略业务类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of strategy
-- ----------------------------
INSERT INTO `strategy` VALUES (14, 1, '2023-02-11 22:41:25', 0, '2023-02-23 21:49:42', '1677160170874.jpg', '优美的山啊', 4, '香格里拉的自然景色是雪山、冰川、峡谷、森林、草甸、湖泊、美丽、明朗、安然、闲逸、悠远、知足、宁静、和谐，是人们美好理想的归宿。在7月到8月间，避开如涌的人群，把自己放逐在自然，听风的呼唤，听鸟的鸣叫，听流水的声音，聆听自己的心声，这是真正的香格里拉', NULL, 1, 7, 0, 4, 1);
INSERT INTO `strategy` VALUES (19, 3, '2023-02-28 12:09:40', 1, '2023-03-06 14:26:26', '1677557327370.png', '南宁文华酒店', 5, '真的太好了菽粟23333', NULL, 1, 1, 0, 0, 1);
INSERT INTO `strategy` VALUES (20, 3, '2023-02-28 17:21:14', 0, '2023-03-23 15:39:56', '1677576031605.png', '便捷星星旅游网', 4, '真的太喜欢这个网站了比其他订票网站便宜啊啊啊笔芯', NULL, 1, 1, 0, 1, 0);
INSERT INTO `strategy` VALUES (21, 3, '2023-03-06 14:22:35', 1, NULL, '1677576031605.png', '23333', 5, '2333333333', NULL, 1, 0, 0, 0, 2);
INSERT INTO `strategy` VALUES (23, 3, '2023-03-26 14:52:23', 0, '2023-03-26 18:40:57', '1679827252792.png', '酒店推荐', 5, '太喜欢了推荐233', NULL, 1, 1, 0, 2, 1);
INSERT INTO `strategy` VALUES (24, 53, '2023-05-24 16:56:33', 1, NULL, '1684918590145.md', '211222222222', 5, '1212221212222222222222', NULL, 1, 1, 0, 0, 0);
INSERT INTO `strategy` VALUES (25, 53, '2023-05-24 16:57:37', 0, NULL, '1684918624855.jpg', '优美的梧桐山', 5, '真的太贴近大自然了啦', NULL, 1, 2, 0, 2, 3);
INSERT INTO `strategy` VALUES (26, 3, '2023-05-25 15:09:42', 0, '2023-05-25 15:12:55', '1684998531183.png', '打卡白元江', 5, '真的太美了景色，真的爱了爱了', NULL, 1, 0, 0, 0, 3);
INSERT INTO `strategy` VALUES (27, 3, '2023-05-25 15:10:41', 0, '2023-05-25 15:13:12', '1684998639014.png', '快来五星酒店', 5, '环境真的超好，五星服务。', NULL, 1, 1, 0, 1, 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `visible` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0',
  `perms` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '#',
  `create_by` bigint(20) NULL DEFAULT NULL,
  `update_by` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del` int(11) NULL DEFAULT 0,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '权限分配管理', 'dept', 'system/dept/index', '0', '0', 'system:dept:list', '#', 1, NULL, '2023-01-19 17:44:27', NULL, 0, NULL, -1);
INSERT INTO `sys_menu` VALUES (2, '测试', 'test', 'system/test/index', '0', '0', 'system:test:list', '#', 1, NULL, '2023-01-19 17:44:31', NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (3, '总数', 'carManager', '', '0', '0', 'system:car:count', '#', 1, NULL, '2023-01-19 16:24:00', NULL, 0, '管理员', 79);
INSERT INTO `sys_menu` VALUES (4, '新增和修改', 'carManager', NULL, '0', '0', 'system:car:saveOrUpdateCar', '#', 1, NULL, '2023-01-19 17:44:00', NULL, 0, NULL, 79);
INSERT INTO `sys_menu` VALUES (5, '查询', 'carManager', NULL, '0', '0', 'system:car:findCarById', '#', 1, NULL, '2023-01-19 17:44:04', NULL, 0, NULL, 79);
INSERT INTO `sys_menu` VALUES (6, '删除', 'carManager', NULL, '0', '0', 'system:car:deleteById', '#', 1, NULL, '2023-01-19 17:44:07', NULL, 0, NULL, 79);
INSERT INTO `sys_menu` VALUES (7, '更新状态', 'carManager', NULL, '0', '0', 'system:car:updateStates', '#', 1, NULL, '2023-01-19 17:44:10', NULL, 0, NULL, 79);
INSERT INTO `sys_menu` VALUES (8, '所有类型', 'carManager', NULL, '0', '0', 'system:car:typeAll', '#', 1, NULL, '2023-01-19 17:44:15', NULL, 0, NULL, 79);
INSERT INTO `sys_menu` VALUES (9, '所有状态', 'carManager', NULL, '0', '0', 'system:car:stateAll', '#', 1, NULL, '2023-01-19 17:44:19', NULL, 0, NULL, 79);
INSERT INTO `sys_menu` VALUES (10, '所有总数', 'hotelManager', NULL, '0', '0', 'system:hotel:count', '#', 1, NULL, '2023-01-19 22:56:21', NULL, 0, NULL, 78);
INSERT INTO `sys_menu` VALUES (11, '在线总数', 'hotelManager', NULL, '0', '0', 'system:hotel:count1', '#', 1, NULL, '2023-01-19 22:56:24', NULL, 0, NULL, 78);
INSERT INTO `sys_menu` VALUES (12, '新增和修改', 'hotelManager', NULL, '0', '0', 'system:hotel:saveOrUpdateHotel', '#', 1, NULL, '2023-01-19 22:56:28', NULL, 0, NULL, 78);
INSERT INTO `sys_menu` VALUES (13, '查找', 'hotelManager', NULL, '0', '0', 'system:hotel:findHotelById', '#', 1, NULL, '2023-01-19 22:56:31', NULL, 0, NULL, 78);
INSERT INTO `sys_menu` VALUES (14, '删除', 'hotelManager', NULL, '0', '0', 'system:hotel:deleteById', '#', 1, NULL, '2023-01-19 22:56:35', NULL, 0, NULL, 78);
INSERT INTO `sys_menu` VALUES (15, '更新状态', 'hotelManager', NULL, '0', '0', 'system:hotel:updateStates', '#', 1, NULL, '2023-01-19 22:56:38', NULL, 0, NULL, 78);
INSERT INTO `sys_menu` VALUES (16, '所有类型', 'hotelManager', NULL, '0', '0', 'system:hotel:typeAll', '#', 1, NULL, '2023-01-19 22:56:41', NULL, 0, NULL, 78);
INSERT INTO `sys_menu` VALUES (17, '所有状态', 'hotelManager', NULL, '0', '0', 'system:hotel:stateAll', '#', 1, NULL, '2023-01-19 22:56:45', NULL, 0, NULL, 78);
INSERT INTO `sys_menu` VALUES (18, '更新状态', 'adminManager', NULL, '0', '0', 'system:admin:updateStates', '#', NULL, NULL, NULL, NULL, 0, NULL, 71);
INSERT INTO `sys_menu` VALUES (19, '查询', 'adminManager', NULL, '0', '0', 'system:admin:findAdminById', '#', NULL, NULL, NULL, NULL, 0, NULL, 71);
INSERT INTO `sys_menu` VALUES (20, '删除', 'adminManager', NULL, '0', '0', 'system:admin:deleteById', '#', NULL, NULL, NULL, NULL, 0, NULL, 71);
INSERT INTO `sys_menu` VALUES (21, '新增管理员', 'adminManager', NULL, '0', '0', 'system:admin:saveOrUpdateAdmin', '#', NULL, NULL, NULL, NULL, 0, NULL, 71);
INSERT INTO `sys_menu` VALUES (22, '所有类型', 'adminManager', NULL, '0', '0', 'system:admin:typeAll', '#', NULL, NULL, NULL, NULL, 0, NULL, 71);
INSERT INTO `sys_menu` VALUES (23, '所有状态', 'adminManager', NULL, '0', '0', 'system:admin:stateAll', '#', NULL, NULL, NULL, NULL, 0, NULL, 71);
INSERT INTO `sys_menu` VALUES (26, '所有总数', 'insuranceManager', NULL, '0', '0', 'system:insurance:count', '#', NULL, NULL, NULL, NULL, 0, NULL, 77);
INSERT INTO `sys_menu` VALUES (27, '在线总数', 'insuranceManager', NULL, '0', '0', 'system:insurance:count1', '#', NULL, NULL, NULL, NULL, 0, NULL, 77);
INSERT INTO `sys_menu` VALUES (28, '新增修改', 'insuranceManager', NULL, '0', '0', 'system:insurance:saveOrUpdateInsurance', '#', NULL, NULL, NULL, NULL, 0, NULL, 77);
INSERT INTO `sys_menu` VALUES (29, '查询', 'insuranceManager', NULL, '0', '0', 'system:insurance:findInsuranceById', '#', NULL, NULL, NULL, NULL, 0, NULL, 77);
INSERT INTO `sys_menu` VALUES (30, '删除', 'insuranceManager', NULL, '0', '0', 'system:insurance:deleteById', '#', NULL, NULL, NULL, NULL, 0, NULL, 77);
INSERT INTO `sys_menu` VALUES (31, '更新状态', 'insuranceManager', NULL, '0', '0', 'system:insurance:updateStates', '#', NULL, NULL, NULL, NULL, 0, NULL, 77);
INSERT INTO `sys_menu` VALUES (32, '类型', 'insuranceManager', NULL, '0', '0', 'system:insurance:typeAll', '#', NULL, NULL, NULL, NULL, 0, NULL, 77);
INSERT INTO `sys_menu` VALUES (33, '状态', 'insuranceManager', NULL, '0', '0', 'system:insurance:stateAll', '#', NULL, NULL, NULL, NULL, 0, NULL, 77);
INSERT INTO `sys_menu` VALUES (34, '省份', 'adminManager', NULL, '0', '0', 'system:admin:findProvinceByid', '#', NULL, NULL, NULL, NULL, 0, NULL, 71);
INSERT INTO `sys_menu` VALUES (42, '所有总数', 'scenicSpotManager', NULL, '0', '0', 'system:scenicSpot:count', '#', NULL, NULL, NULL, NULL, 0, NULL, 76);
INSERT INTO `sys_menu` VALUES (43, '在线总数', 'scenicSpotManager', NULL, '0', '0', 'system:scenicSpot:count1', '#', NULL, NULL, NULL, NULL, 0, NULL, 76);
INSERT INTO `sys_menu` VALUES (44, '新增修改', 'scenicSpotManager', NULL, '0', '0', 'system:scenicSpot:saveOrUpdateScenicSpot', '#', NULL, NULL, NULL, NULL, 0, NULL, 76);
INSERT INTO `sys_menu` VALUES (45, '查询', 'scenicSpotManager', NULL, '0', '0', 'system:scenicSpot:findScenicSpotById', '#', NULL, NULL, NULL, NULL, 0, NULL, 76);
INSERT INTO `sys_menu` VALUES (46, '删除', 'scenicSpotManager', NULL, '0', '0', 'system:scenicSpot:deleteById', '#', NULL, NULL, NULL, NULL, 0, NULL, 76);
INSERT INTO `sys_menu` VALUES (47, '更新状态', 'scenicSpotManager', NULL, '0', '0', 'system:scenicSpot:updateStates', '#', NULL, NULL, NULL, NULL, 0, NULL, 76);
INSERT INTO `sys_menu` VALUES (48, '状态', 'scenicSpotManager', NULL, '0', '0', 'system:scenicSpot:stateAll', '#', NULL, NULL, NULL, NULL, 0, NULL, 76);
INSERT INTO `sys_menu` VALUES (50, '总数', 'messageManager', NULL, '0', '0', 'system:message:count', '#', NULL, NULL, NULL, NULL, 0, NULL, 75);
INSERT INTO `sys_menu` VALUES (51, '在线总数', 'messageManager', NULL, '0', '0', 'system:message:saveOrUpdateMessage', '#', NULL, NULL, NULL, NULL, 0, NULL, 75);
INSERT INTO `sys_menu` VALUES (52, '删除', 'messageManager', NULL, '0', '0', 'system:message:deleteById', '#', NULL, NULL, NULL, NULL, 0, NULL, 75);
INSERT INTO `sys_menu` VALUES (53, '修改状态', 'messageManager', NULL, '0', '0', 'system:message:updateStates', '#', NULL, NULL, NULL, NULL, 0, NULL, 75);
INSERT INTO `sys_menu` VALUES (54, '状态', 'messageManager', NULL, '0', '0', 'system:message:stateAll', '#', NULL, NULL, NULL, NULL, 0, NULL, 75);
INSERT INTO `sys_menu` VALUES (55, '列表', 'messageManager', NULL, '0', '0', 'system:message:findAll', '#', NULL, NULL, NULL, NULL, 0, NULL, 75);
INSERT INTO `sys_menu` VALUES (57, '总数', 'routerManager', NULL, '0', '0', 'system:routel:count', '#', NULL, NULL, NULL, NULL, 0, NULL, 73);
INSERT INTO `sys_menu` VALUES (58, '在线总数', 'routerManager', NULL, '0', '0', 'system:routel:count1', '#', NULL, NULL, NULL, NULL, 0, NULL, 73);
INSERT INTO `sys_menu` VALUES (59, '新增修改', 'routerManager', NULL, '0', '0', 'system:routel:saveOrUpdateRoutel', '#', NULL, NULL, NULL, NULL, 0, NULL, 73);
INSERT INTO `sys_menu` VALUES (60, '查询', 'routerManager', NULL, '0', '0', 'system:routel:findRoutelById', '#', NULL, NULL, NULL, NULL, 0, NULL, 73);
INSERT INTO `sys_menu` VALUES (61, '删除', 'routerManager', NULL, '0', '0', 'system:routel:deleteById', '#', NULL, NULL, NULL, NULL, 0, NULL, 73);
INSERT INTO `sys_menu` VALUES (62, '更新状态', 'routerManager', NULL, '0', '0', 'system:routel:updateStates', '#', NULL, NULL, NULL, NULL, 0, NULL, 73);
INSERT INTO `sys_menu` VALUES (63, '状态', 'routerManager', NULL, '0', '0', 'system:routel:stateAll', '#', NULL, NULL, NULL, NULL, 0, NULL, 73);
INSERT INTO `sys_menu` VALUES (64, '总数', 'orderManager', NULL, '0', '0', 'system:order:count', '#', 1, NULL, '2023-02-02 10:15:35', NULL, 0, NULL, 72);
INSERT INTO `sys_menu` VALUES (65, '在线总数', 'orderManager', NULL, '0', '0', 'system:order:count1', '#', 1, NULL, '2023-02-02 10:15:32', NULL, 0, NULL, 72);
INSERT INTO `sys_menu` VALUES (66, '新增修改', 'orderManager', NULL, '0', '0', 'system:order:updateOrder', '#', 1, NULL, '2023-02-02 10:15:29', NULL, 0, NULL, 72);
INSERT INTO `sys_menu` VALUES (67, '类型', 'orderManager', NULL, '0', '0', 'system:order:productTypeALL', '#', 1, NULL, '2023-02-02 10:15:27', NULL, 0, NULL, 72);
INSERT INTO `sys_menu` VALUES (68, '状态', 'orderManager', NULL, '0', '0', 'system:order:stateAll', '#', 1, NULL, '2023-02-02 10:15:23', NULL, 0, NULL, 72);
INSERT INTO `sys_menu` VALUES (69, '用户新增修改', 'adminManager', NULL, '0', '0', 'system:admin:saveOrUpdateUser', '#', NULL, NULL, NULL, NULL, 0, NULL, 71);
INSERT INTO `sys_menu` VALUES (70, '管理员修改', 'adminManager', NULL, '0', '0', 'system:admin:updateAdmin', '#', 1, NULL, '2023-02-28 18:42:37', NULL, 0, NULL, 71);
INSERT INTO `sys_menu` VALUES (71, '用户管理', 'adminManager', NULL, '0', '0', NULL, '#', NULL, NULL, NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (72, '订单管理', 'orderManager', NULL, '0', '0', NULL, '#', NULL, NULL, NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (73, '路线管理', 'routerManager', NULL, '0', '0', NULL, '#', NULL, NULL, NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (75, '留言管理', 'messageManager', NULL, '0', '0', NULL, '#', NULL, NULL, NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (76, '景点管理', 'scenicSpotManager', NULL, '0', '0', NULL, '#', NULL, NULL, NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (77, '保险管理', 'insuranceManager', NULL, '0', '0', NULL, '#', NULL, NULL, NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (78, '酒店管理', 'hotelManager', NULL, '0', '0', NULL, '#', NULL, NULL, NULL, NULL, 0, NULL, 1);
INSERT INTO `sys_menu` VALUES (79, '车票管理', 'carManager', NULL, '0', '0', NULL, '#', NULL, NULL, NULL, NULL, 0, NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` bigint(4) NULL DEFAULT NULL,
  `del` bigint(4) NULL DEFAULT 1,
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', '2323234', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (2, '超级管理员', '323244', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (3, '用户', '2334345', 0, 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (4, '测试管理员', '1688481585415', 1, 0, 'root', NULL, '2023-07-04 22:39:39', NULL, '测试权限');
INSERT INTO `sys_role` VALUES (5, NULL, '1688794967704', 0, 1, 'root', NULL, '2023-07-08 13:42:40', NULL, NULL);
INSERT INTO `sys_role` VALUES (6, 'wewe', '1688795137468', 0, 1, 'root', NULL, '2023-07-08 13:45:34', NULL, '334343434');
INSERT INTO `sys_role` VALUES (7, 'wewe', '1688795179638', 0, 1, 'root', NULL, '2023-07-08 13:46:14', NULL, '334343434');
INSERT INTO `sys_role` VALUES (8, '用户名44', '1688796583425', 1, 0, 'root', 'root', '2023-07-08 14:09:37', '2023-07-08 14:20:40', '3222222222');
INSERT INTO `sys_role` VALUES (9, '34444444', '1688797016715', 0, 1, 'root', NULL, '2023-07-08 14:16:51', NULL, NULL);
INSERT INTO `sys_role` VALUES (10, '444444', '1688797112233', 0, 1, 'root', NULL, '2023-07-08 14:18:31', NULL, '34');
INSERT INTO `sys_role` VALUES (11, '45454', '1688797183468', 0, 1, 'root', NULL, '2023-07-08 14:19:41', NULL, '4554');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(100) NULL DEFAULT 1,
  `menu_id` bigint(100) NULL DEFAULT NULL,
  `role_menu_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_accredit` int(4) NULL DEFAULT NULL,
  `is_deleted` bigint(4) NULL DEFAULT NULL,
  `create_by` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  INDEX `role_id`(`role_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 2, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 3, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 4, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 5, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 6, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 7, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 8, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 10, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 9, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 11, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 12, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 14, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 15, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 16, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 13, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 17, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 19, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 22, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 23, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 26, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 27, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 28, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 29, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 30, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 31, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 32, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 33, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 34, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 42, NULL, 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 43, '33323232', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 44, '32443534', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 45, '245346', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 46, '455647', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 47, '34466335', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 48, '4344556', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 18, '5454546', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 24, '234544545', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 49, '23423555', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 50, '44356767', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 51, '57757784', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 52, '43454677', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 53, '5344533', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 54, '3654567', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 55, '4565766', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 56, '465566666666', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 57, 'er4454334', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 58, '44354656', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 59, '3434344', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 60, '3455545', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 61, '3443443554', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 62, '3433544', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 63, '4334553', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 64, '3435544', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 65, '4343345', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 66, '3434344', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 67, '334455', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 68, '344455', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 69, '434554', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 20, '4434334', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 21, '344345', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (1, 70, '344455', 0, 0, NULL, NULL);
INSERT INTO `sys_role_menu` VALUES (8, 18, '1688879656054', 0, 1, 'lisi', '2023-07-09 13:14:08');
INSERT INTO `sys_role_menu` VALUES (8, 64, '1688879650869', 0, 1, 'lisi', '2023-07-09 13:14:08');
INSERT INTO `sys_role_menu` VALUES (8, 18, '1688879885284', 0, 0, 'root', '2023-07-09 13:17:57');
INSERT INTO `sys_role_menu` VALUES (8, 19, '1688879878135', 0, 0, 'root', '2023-07-09 13:17:57');
INSERT INTO `sys_role_menu` VALUES (8, 20, '1688879879720', 0, 0, 'root', '2023-07-09 13:17:57');
INSERT INTO `sys_role_menu` VALUES (8, 64, '1688879881747', 0, 0, 'root', '2023-07-09 13:17:57');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(200) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(200) NOT NULL DEFAULT 0,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '被授权账号',
  `add_admin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权人',
  `create_time` datetime NULL DEFAULT NULL,
  `del` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_role_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `user_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 'root', 'root', NULL, '1', '3434343455');
INSERT INTO `sys_user_role` VALUES (2, 2, '2', 'root', NULL, '0', '4334434555');
INSERT INTO `sys_user_role` VALUES (3, 3, 'list', 'root', NULL, '0', '4554456655');
INSERT INTO `sys_user_role` VALUES (3, 4, 'l4', 'lisi', '2023-07-04 22:42:49', '1', '1688481776927');
INSERT INTO `sys_user_role` VALUES (48, 2, 'don99', 'root', '2023-07-08 15:16:18', '0', '1688800583900');
INSERT INTO `sys_user_role` VALUES (40, 2, 'hzw090', 'root', '2023-07-08 15:17:43', '0', '1688800669790');
INSERT INTO `sys_user_role` VALUES (52, 4, 'hzw11', 'root', '2023-07-08 15:18:30', '0', '1688800715658');
INSERT INTO `sys_user_role` VALUES (40, 8, 'hzw090', 'root', '2023-07-08 15:19:30', '0', '1688800779517');
INSERT INTO `sys_user_role` VALUES (49, 2, 'hzw111', 'root', '2023-07-08 15:25:25', '1', '1688801127519');
INSERT INTO `sys_user_role` VALUES (49, 2, 'hzw111', 'root', '2023-07-08 18:04:10', '1', '1688810659312');

-- ----------------------------
-- Table structure for t_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `Product_Id` bigint(10) NULL DEFAULT NULL,
  `USER_ID` bigint(10) NULL DEFAULT NULL,
  `ADD_TIME` datetime NULL DEFAULT NULL,
  `Product_Type` int(1) NULL DEFAULT NULL,
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收藏标题或名字',
  `collections` int(20) UNSIGNED NULL DEFAULT 0 COMMENT '收藏数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 111 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_collection
-- ----------------------------
INSERT INTO `t_collection` VALUES (86, 3, 3, '2023-03-26 14:50:48', 3, '广西南宁天塔', 0);
INSERT INTO `t_collection` VALUES (87, 23, 3, '2023-03-26 14:52:35', 5, '2333好习', 0);
INSERT INTO `t_collection` VALUES (89, 14, 53, '2023-05-07 00:06:16', 5, '优美的山啊', 0);
INSERT INTO `t_collection` VALUES (98, 23, 53, '2023-05-07 19:26:17', 5, '酒店推荐', 0);
INSERT INTO `t_collection` VALUES (100, 25, 53, '2023-05-24 16:57:43', 5, '优美的梧桐山', 0);
INSERT INTO `t_collection` VALUES (101, 25, 55, '2023-05-24 17:02:57', 5, '优美的梧桐山', 0);
INSERT INTO `t_collection` VALUES (103, 14, 55, '2023-05-24 17:31:50', 5, '优美的山啊', 0);
INSERT INTO `t_collection` VALUES (104, 1, 3, '2023-05-24 18:03:25', 1, '南宁文化酒店22', 0);
INSERT INTO `t_collection` VALUES (106, 27, 3, '2023-05-25 15:10:56', 5, '快来快来优惠五星酒店', 0);
INSERT INTO `t_collection` VALUES (110, 1, 3, '2023-07-09 17:42:53', 2, '广州市——江门市', 0);

-- ----------------------------
-- Table structure for t_insurance_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_insurance_user_info`;
CREATE TABLE `t_insurance_user_info`  (
  `id` bigint(40) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `insurance_id` bigint(40) NOT NULL COMMENT '保险id',
  `add_tiem` datetime NULL DEFAULT NULL,
  `delete_state` int(1) NULL DEFAULT 0,
  `user_id` bigint(20) NOT NULL COMMENT '投保人',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投保人姓名',
  `ic_code` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证',
  `insurance_company` int(1) NULL DEFAULT 1 COMMENT '保险公司',
  `type` int(1) NULL DEFAULT 1 COMMENT '保险类型',
  `state` int(1) NULL DEFAULT 0 COMMENT '0未支付 1已支付',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_insurance_user_info
-- ----------------------------
INSERT INTO `t_insurance_user_info` VALUES (1, 1621770616034680833, '2023-02-04 19:54:14', 0, 1, '夏鸥', '12781288399277291', 0, 1, 1);
INSERT INTO `t_insurance_user_info` VALUES (2, 2, '2023-02-10 13:58:57', 0, 3, '小黄', '127812883992772911', 0, 0, 1);
INSERT INTO `t_insurance_user_info` VALUES (3, 5, '2023-02-10 14:07:32', 0, 3, '小黄', '127812883992772911', 2, 1, 1);
INSERT INTO `t_insurance_user_info` VALUES (4, 2, '2023-02-14 20:37:27', 0, 6, 'dong', '123456789111111119', 0, 0, 0);
INSERT INTO `t_insurance_user_info` VALUES (5, 5, '2023-02-21 14:25:29', 0, 3, '小黄', '127812883992772911', 2, 1, 1);
INSERT INTO `t_insurance_user_info` VALUES (6, 5, '2023-02-28 18:17:11', 0, 52, '小王', '123456789012345678', 2, 1, 1);
INSERT INTO `t_insurance_user_info` VALUES (7, 5, '2023-05-20 19:07:31', 0, 53, '小黄233', '150422200709075521', 2, 1, 0);

-- ----------------------------
-- Table structure for t_notic
-- ----------------------------
DROP TABLE IF EXISTS `t_notic`;
CREATE TABLE `t_notic`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `delete_state` int(1) NULL DEFAULT 0 COMMENT '0未删除 1删除',
  `state` int(1) NULL DEFAULT 1 COMMENT '0未发布 1发布',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notic
-- ----------------------------
INSERT INTO `t_notic` VALUES (3, '三、细化管理措施，规范游览秩序。旅游景区要配备必要人员设备，加强清洁消毒，严格落实体温筛检等防控措施，结合实际，配套使用“健康码”核验等手段。发现可疑人员应当劝阻其进入，进行暂时隔离，并立即通知当地卫生健康部门及时处置。要优化设置游览线路，防止线路规划不合理导致游客扎堆拥挤现象。要加强巡视巡查，指导游客做好安全防护，保持购票、游览、休息、餐饮等场所人员间距。要加强各类旅游设备设施和消防装备器材安全隐患排查治理，强化野外火源管控。要在旅游景区出入口、重要参观点等容易形成人员聚集的区域设置专人，加强疏导，避免拥堵，确保防控到位。', '2023-02-13 23:23:42', '2023-05-24 17:40:57', 0, 1, '注意！！游客请注意啊');
INSERT INTO `t_notic` VALUES (4, '666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666', '2023-02-13 23:45:24', NULL, 1, 1, '优美');
INSERT INTO `t_notic` VALUES (6, '如果遇到麻烦请留言，我们会第一时间联系您的。', '2023-05-24 17:41:54', '2023-05-24 17:45:45', 0, 1, '遇到问题怎么办？不要急');
INSERT INTO `t_notic` VALUES (7, '祝大家国庆节快乐，国庆小长假怎么过，当然是去旅游啦', '2023-05-24 17:47:34', NULL, 0, 1, '国庆小长假怎么过');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `ID` bigint(100) NOT NULL COMMENT '主键',
  `ADD_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '添加人ID',
  `ADD_TIME` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `DELETE_STATUS` int(1) NULL DEFAULT 0 COMMENT '删除标志',
  `MODIFY_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '修改人ID',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `USER_NAME` varchar(46) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `PRODUCT_ID` bigint(20) NULL DEFAULT NULL COMMENT '产品ID',
  `PRODUCT_NAME` varchar(46) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '产品名称',
  `FEE` double(8, 2) NULL DEFAULT NULL COMMENT '订单费用',
  `PRODUCT_TYPE` int(1) NULL DEFAULT NULL COMMENT '产品类型0：车票1酒店2路线3景点4保险',
  `STATE` int(1) NULL DEFAULT 0 COMMENT '订单状态，1：未支付；2：已支付；3：已核销；4：已取消；5：退款中；6：已退款',
  `ORDER_CODE` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '订单编号',
  `ORDER_TIME` datetime NULL DEFAULT NULL COMMENT '订单日期',
  `SETOFF_TIME` date NULL DEFAULT NULL COMMENT '预定日期',
  `LINK_TEL` varchar(46) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `PEOPLE_COUNT` int(10) NULL DEFAULT 1 COMMENT '人数',
  `REQUIREMENT` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '特殊要求',
  `IC_CODE` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `IMG_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `refund_time` datetime NULL DEFAULT NULL COMMENT '退款时间',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '支付时间',
  `day` int(10) NULL DEFAULT NULL COMMENT '天数',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (99, NULL, '2023-02-28 15:59:02', 1, NULL, NULL, 3, 'lisi', 1, '广西南宁一日游', 100.00, 3, 0, 'fe943219-5b07-4aab-8643-8b4872828cc9', '2023-02-28 15:59:02', '2023-02-21', '18777471822', 1, '21111', '127812883992772911', '1675877179863.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (777, NULL, '2023-02-28 15:59:37', 1, NULL, NULL, 3, 'lisi', 6, '南宁市——广州市', 80.00, 0, 0, '67a759b8-c74f-4e2b-a38c-f014f6cfe088', '2023-02-28 15:59:37', '2019-03-20', '18777471822', 1, '222', '127812883992772911', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1677607773, NULL, '2023-02-28 18:09:34', 1, NULL, NULL, 52, 'hzw11', 4, '火星一日游', 1000.00, 3, 0, 'c59e308a-5755-4aac-bfc0-70489d744dd4', '2023-02-28 18:09:34', '2023-02-14', '18777562211', 1, '1222222', '123456789012345678', '1675877496702.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1677607849, NULL, '2023-02-28 18:10:50', 0, NULL, NULL, 52, 'hzw11', 4, '梧州市——广州市', 80.00, 0, 4, '44bc4a0b-b76e-4881-adf0-265ba3580eba', '2023-02-28 18:10:50', '2019-03-19', '18777562211', 1, '212', '123456789012345678', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1677608100, NULL, '2023-02-28 18:15:01', 1, NULL, NULL, 52, 'hzw11', 4, '火星一日游', 1000.00, 3, 4, '6aa88fde-e3dc-4a97-94eb-e3dc2530dbd5', '2023-02-28 18:15:01', '2023-02-09', '18777672211', 1, '12221', '123456789012345678', '1675877496702.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1677608230, NULL, '2023-02-28 18:17:11', 0, NULL, NULL, 52, 'hzw11', 5, '酒店保险', 80.00, 4, 1, '7f60b6c6-e79d-4f55-8973-397fd068e69e', '2023-02-28 18:17:11', '2023-02-28', '18777567277', 1, '保我一生平安 ', '123456789012345678', '1676004768578.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1678394593, NULL, '2023-03-09 20:43:14', 1, NULL, NULL, 3, 'lisi', 4, '梧州市——广州市', 80.00, 0, 4, '9571ba35-1196-47e0-bf0d-5fa36af3dab4', '2023-03-09 20:43:14', '2019-03-19', '18777562212', 1, '好的', '127812883992772911', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1679495447, NULL, '2023-03-22 14:30:48', 1, NULL, NULL, 3, 'lisi', 4, '梧州市——广州市', 80.00, 0, 4, '7c50ab4f-7fb7-4e4c-ad82-5d23cc97922a', '2023-03-22 14:30:48', '2019-03-19', '18777562277', 1, '哈哈哈', '127812883992772911', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1679842131, NULL, '2023-03-26 14:48:52', 1, NULL, NULL, 3, 'lisi', 4, '梧州市——广州市', 80.00, 0, 4, 'dca6aa69-cee0-4ee2-91ec-90a35812de0b', '2023-03-26 14:48:52', '2019-03-19', '18777586671', 1, '2333', '127812883992772911', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1679854791, NULL, '2023-03-26 18:19:52', 0, NULL, NULL, 3, 'lisi', 4, '梧州市——广州市', 80.00, 0, 4, 'ae2793e3-5f19-42b9-82b3-ed48cd7b12a6', '2023-03-26 18:19:52', '2019-03-19', '18777564421', 1, '23333', '127812883992772911', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1679860401, NULL, '2023-03-26 19:53:22', 0, NULL, NULL, 53, 'hzw22', 4, '梧州市——广州市', 80.00, 0, 1, '5a544e18-e66a-4f1e-8997-fb198ff08340', '2023-03-26 19:53:22', '2019-03-19', '18777674421', 1, '2333', '150422200709075521', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1679860770, NULL, '2023-03-26 19:59:31', 0, NULL, NULL, 53, 'hzw22', 1, '广西南宁一日游', 100.00, 3, 0, '1dd13375-1361-4181-93ed-504db5775c4f', '2023-03-26 19:59:31', '2023-03-22', '18777672212', 1, '23333', '150422200709075521', '1675877179863.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1683462303, NULL, '2023-05-07 12:25:03', 0, NULL, NULL, 53, 'hzw22', 4, '火星一日游', 1000.00, 3, 0, 'c770c3b3-0a21-4dac-9c64-28fd5e305982', '2023-05-07 12:25:03', NULL, '', 1, '', '150422200709075521', '1675877496702.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1684609650, NULL, '2023-05-20 19:07:30', 0, NULL, NULL, 53, 'hzw22', 5, '酒店保险', 80.00, 4, 0, 'be9b27b4-60ef-4337-a685-909c44c3251a', '2023-05-20 19:07:30', '2023-05-17', '18777675545', 1, '123', '150422200709075521', '1676004768578.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1685193326, NULL, '2023-05-27 13:15:26', 0, NULL, NULL, 56, 'xiaocheng', 4, '梧州市——广州市', 80.00, 0, 4, 'c64cb653-1110-4830-96f8-123d7d4262d0', '2023-05-27 13:15:26', '2019-03-17', '18777561824', 1, '有元素', '450422200107096645', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1685193467, NULL, '2023-05-27 13:17:48', 1, NULL, NULL, 56, 'xiaocheng', 1, '南宁文化酒店22', 3900.00, 1, 0, 'fe7782e9-901b-4a6d-b1de-af5621cafdbb', '2023-05-27 13:17:48', '2023-05-11', '18777471822', 1, '11', '450422200107096645', '1677566652518.png', NULL, NULL, 26);
INSERT INTO `t_order` VALUES (1685197779, NULL, '2023-05-27 14:29:40', 0, NULL, NULL, 56, 'xiaocheng', 4, '梧州市——广州市', 80.00, 0, 4, '1c231458-4353-4519-b3f8-d1d56fefd0d7', '2023-05-27 14:29:40', '2019-03-17', '18777564424', 1, '2333', '450422200107096645', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1688924744, NULL, '2023-07-09 17:45:45', 0, NULL, NULL, 3, 'lisi', 6, '南宁市——广州市', 80.00, 0, 4, 'fc9dda55-ef9e-49e6-8da5-54e0cf20fd7e', '2023-07-09 17:45:45', '2019-03-20', '18777674421', 1, 'hdjs', '127812883992772911', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (1592870226100228, NULL, '2023-02-04 18:38:11', 0, 1, '2023-02-20 13:57:49', 1, 'root', 8, '梧州天塔酒店', 600.00, 1, 1, '3261fc02-af6a-49d9-a1c8-a6af24efa91b', '2023-02-04 18:38:11', '2023-02-04', '121828212', 2, '零食备好', '12781288399277291', '1675879957315.png', NULL, NULL, 1);
INSERT INTO `t_order` VALUES (1612472456839174, NULL, '2023-02-04 19:54:14', 1, 1, '2023-02-07 01:05:32', 1, 'root', 1621770616034680800, '酒店保险', 160.00, 4, 2, '9d30e054-b932-44e9-9eb0-e1d47908a88d', '2023-02-04 19:54:14', '2023-02-04', '123445662332', 2, '零食备好', '12781288399277291', '1675879957315.png', NULL, NULL, 1);
INSERT INTO `t_order` VALUES (3454330527023105, NULL, '2023-02-09 19:01:34', 1, NULL, NULL, 3, 'lisi', 11, '桂林天塔公寓', 200.00, 1, 1, '12dda873-1b95-4442-a7a7-ce4f6bb324cf', '2023-02-09 19:01:34', '2023-02-09', NULL, 2, '零食备好', '127812883992772911', '1675879957315.png', NULL, NULL, 1);
INSERT INTO `t_order` VALUES (3470823201439747, NULL, '2023-02-09 20:05:35', 1, NULL, NULL, 3, 'lisi', 1, '广州文化酒店', 750.00, 1, 1, '7070ba6c-840c-4d71-9d04-0d67f849aefb', '2023-02-09 20:05:35', '2023-02-09', NULL, 1, '很少很少', '127812883992772911', '1675879957315.png', NULL, NULL, 5);
INSERT INTO `t_order` VALUES (3484287923912708, NULL, '2023-02-09 20:57:50', 1, NULL, NULL, 3, 'lisi', 1, '广州文化酒店', 300.00, 1, 1, '3cfaf905-6225-4efc-b9bd-20faa2583f63', '2023-02-09 20:57:50', '2023-02-10', NULL, 1, '323232', '127812883992772911', '1675879957315.png', NULL, NULL, 2);
INSERT INTO `t_order` VALUES (3484927874039813, NULL, '2023-02-09 21:00:18', 1, NULL, NULL, 3, 'lisi', 1, '广州文化酒店', 300.00, 1, 1, 'df70be0b-c1c2-40d9-89c0-2d2733704e6e', '2023-02-09 21:00:18', '2023-02-10', NULL, 1, '灌水广告', '127812883992772911', '1675879957315.png', NULL, NULL, 2);
INSERT INTO `t_order` VALUES (3485872766844934, NULL, '2023-02-09 21:03:59', 1, NULL, NULL, 3, 'lisi', 1, '广州文化酒店', 150.00, 1, 1, '91028dfc-bf40-482d-9fff-48b0cbad8722', '2023-02-09 21:03:59', '2023-02-09', NULL, 1, '62717', '127812883992772911', '1675879957315.png', NULL, NULL, 1);
INSERT INTO `t_order` VALUES (3486624386121735, NULL, '2023-02-09 21:06:53', 1, NULL, NULL, 3, 'lisi', 1, '广州文化酒店', 150.00, 1, 0, 'ee3e6eec-8ef7-4905-914e-d498865f2743', '2023-02-09 21:06:53', '2023-02-09', NULL, 1, '62717', '127812883992772911', '1675879957315.png', NULL, NULL, 1);
INSERT INTO `t_order` VALUES (3486693105598472, NULL, '2023-02-09 21:07:10', 1, NULL, NULL, 3, 'lisi', 1, '广州文化酒店', 150.00, 1, 0, '5e1c3c32-347c-4711-8c00-88f4f564c8aa', '2023-02-09 21:07:10', '2023-02-09', '1142445521', 1, '更好傻瓜和水果和', '127812883992772911', '1675879957315.png', NULL, NULL, 1);
INSERT INTO `t_order` VALUES (3487646588338185, NULL, '2023-02-09 21:10:52', 1, NULL, NULL, 3, 'lisi', 1, '广州文化酒店', 150.00, 1, 1, '82163c37-9e4e-4601-b823-cc91c40347c9', '2023-02-09 21:10:52', '2023-02-16', '323232', 1, '32322', '127812883992772911', '1675879957315.png', NULL, NULL, 1);
INSERT INTO `t_order` VALUES (3518982669729802, NULL, '2023-02-09 23:12:27', 1, NULL, NULL, 3, 'lisi', 1, '广西南宁一日游', 200.00, 3, 4, 'e77079db-22e8-49de-a923-65d21426dd5d', '2023-02-09 23:12:27', '2023-02-15', '17822891221', 2, '会很尴尬的武功', '127812883992772911', '1675879957315.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (3520387124035595, NULL, '2023-02-09 23:17:55', 1, NULL, NULL, 3, 'lisi', 1, '广西南宁一日游', 300.00, 3, 4, '1d2b28bb-1b24-4600-9747-d9c0e3aa0ef6', '2023-02-09 23:17:55', '2023-02-15', '16617721878', 4, '哈哈哈哈哈哈哈', '127812883992772911', '1675879957315.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (3520666296909836, NULL, '2023-02-09 23:18:59', 1, NULL, NULL, 3, 'lisi', 1, '广西南宁一日游', 100.00, 3, 4, '287cbe73-0af5-40e2-8668-e3d2425d19f3', '2023-02-09 23:18:59', '2023-02-15', '116526621', 1, '的是东北那边', '127812883992772911', '1675879957315.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (3747431980204033, NULL, '2023-02-10 13:58:57', 0, NULL, NULL, 3, 'lisi', 2, '自保旅游保险', 100.00, 4, 1, '03fc3128-33ca-4d43-846d-619269e478aa', '2023-02-10 13:58:57', '2023-02-16', '16272828292', 1, '保我啊啊啊', '127812883992772911', '1675879957315.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (3749639593394178, NULL, '2023-02-10 14:07:32', 0, NULL, NULL, 3, 'lisi', 5, '酒店保险', 80.00, 4, 1, '25fa0d49-d8fb-47a6-adc4-79078fba11b4', '2023-02-10 14:07:32', '2023-02-15', '12126263', 1, '1给v给VS v', '127812883992772911', '1675879957315.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (3794264303599619, NULL, '2023-02-10 17:00:42', 1, NULL, NULL, 3, 'lisi', 7, '广西南宁天塔酒店', 600.00, 1, 4, '665e286d-a1fc-4370-9bbf-97d8c5da1f73', '2023-02-10 17:00:42', '2023-02-11', '12561667233', 1, '12712838322', '127812883992772911', '1675879259513.png', NULL, NULL, 2);
INSERT INTO `t_order` VALUES (3794637965754372, NULL, '2023-02-10 17:02:08', 1, NULL, NULL, 3, 'lisi', 2, '广西鼎湖山', 20.00, 3, 1, 'd88488a9-b72d-4cd0-955c-459422143ee1', '2023-02-10 17:02:08', NULL, '1216126776', 1, '2121121', '127812883992772911', '1675877339701.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (3822606792785925, NULL, '2023-02-10 18:50:40', 1, NULL, NULL, 3, 'lisi', 1, '广西南宁一日游', 600.00, 3, 0, 'a48da289-2e11-4c62-911e-f3cb4cabf81f', '2023-02-10 18:50:40', '2023-02-15', '12451551265', 2, '合适的时间和机会电话多少', '127812883992772911', '1675877179863.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (4962637142032385, NULL, '2023-02-13 20:34:35', 1, NULL, NULL, 3, 'lisi', 8, '南宁--广州', 80.00, 0, 1, '5fca4ec5-c8a0-4bc9-a889-e6c6c2968f8e', '2023-02-13 20:34:35', '2023-02-13', '166666666666', 1, '23333333', '127812883992772911', NULL, NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (5318346333487105, NULL, '2023-02-14 19:34:55', 1, NULL, NULL, 6, 'dog', 1, '广州文化酒店', 450.00, 1, 4, '9cd576d8-aaab-412c-a434-acd10eedcebe', '2023-02-14 19:34:55', '2023-02-22', '12217367762', 1, '哈哈哈哈哈哈', '123456789111111119', '1675879259513.png', NULL, NULL, 3);
INSERT INTO `t_order` VALUES (5322340653072386, NULL, '2023-02-14 19:50:24', 0, NULL, NULL, 6, 'dog', 4, '梧州——广州', 80.00, 0, 0, '295b46fd-c24a-420f-9831-26a5b1808801', '2023-02-14 19:50:24', '2019-03-19', '1281821318', 1, '就是看不见的世界', '123456789111111119', '1675877179863.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (5327275570495491, NULL, '2023-02-14 20:09:33', 0, NULL, NULL, 6, 'dog', 4, '梧州——广州', 80.00, 0, 0, '2aa78944-2540-457a-afae-b278ceef5c71', '2023-02-14 20:09:33', '2019-03-19', '12345678919', 1, '就撒娇就哈桑', '123456789111111119', '1675877179863.png', NULL, NULL, NULL);
INSERT INTO `t_order` VALUES (5330350767079428, NULL, '2023-02-14 20:21:29', 0, NULL, NULL, 6, 'dog', 1, '广州文化酒店', 150.00, 1, 0, 'ecd46325-30ff-4e00-ba18-7d50711f9abd', '2023-02-14 20:21:29', '2023-02-15', '12345678911', 1, '1221213', '123456789111111119', '1675879259513.png', NULL, NULL, 1);

-- ----------------------------
-- Table structure for t_routel
-- ----------------------------
DROP TABLE IF EXISTS `t_routel`;
CREATE TABLE `t_routel`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ADD_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '添加人ID',
  `ADD_TIME` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `DELETE_STATUS` int(1) NULL DEFAULT 0 COMMENT '删除标志',
  `MODIFY_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '修改人ID',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `TITLE` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `START_SITE` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '出发地点',
  `END_SITE` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '结束地点',
  `END_TIME` datetime NULL DEFAULT NULL COMMENT '结束日期',
  `START_TIME` datetime NULL DEFAULT NULL COMMENT '出团日期',
  `DAY` int(16) NULL DEFAULT NULL COMMENT '持续天数',
  `PRODUCT_CODE` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '产品编号',
  `PRICE` double NULL DEFAULT NULL COMMENT '价格',
  `STATE` int(1) NULL DEFAULT 1 COMMENT '状态',
  `IMG_URL` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图片',
  `INTRO` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '介绍',
  `order_type` int(1) NULL DEFAULT 2 COMMENT '订单类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_routel
-- ----------------------------
INSERT INTO `t_routel` VALUES (1, NULL, '2023-01-21 11:54:19', 0, 1, '2023-03-26 14:45:31', '广州市——江门市', '广州市', '江门市', '2023-02-09 01:11:57', '2023-02-14 01:12:00', 5, 'aaec1ff4-5f94-4744-a57a-1dbc772a0fe1', 300, 1, '1675875518671.png', '白沙祠——圭峰山——开平碉楼', 2);
INSERT INTO `t_routel` VALUES (2, 1, '2023-02-04 21:37:22', 1, 1, '2023-02-09 01:04:41', '南宁市——江门市', '南宁市', '江门市', '2023-01-24 20:54:19', '2023-01-21 20:54:19', 3, 'aaec1ff4-5f94-4744-a57a-1dbc772a0fe1', 1000, 1, '1675875879610.png', '白沙祠——圭峰山——开平碉楼', 2);
INSERT INTO `t_routel` VALUES (3, 1, '2023-02-07 10:55:05', 1, NULL, NULL, '南宁市——广州市', '南市宁', '广州市', '2023-01-24 20:54:19', '2023-01-21 20:54:19', 3, '49c259a3-930f-49a5-aed0-f1d2ca03bf73', 1000, 1, '1675875518671.png', '白沙祠——圭峰山——开平碉楼', 2);
INSERT INTO `t_routel` VALUES (4, 1, '2023-02-07 10:55:31', 0, 1, '2023-02-09 01:05:11', '桂林市——广州市', '桂林市', '广州市', '2023-01-24 20:54:19', '2023-01-21 20:54:19', 3, '9a59361e-c32e-49cc-926c-cbb7b2e85671', 1000, 1, '1675875906583.png', '白沙祠——圭峰山——开平碉楼', 2);
INSERT INTO `t_routel` VALUES (5, 1, '2023-02-07 10:55:42', 1, 1, '2023-02-09 01:05:33', '百色市——广州市', '百色', '广州市', '2023-01-24 20:54:19', '2023-01-21 20:54:19', 18, '5eb6a295-f1be-4ab5-8f8e-da6fb6d9cb1c', 10000, 1, '1675875929352.png', '白沙祠——圭峰山——开平碉楼', 2);
INSERT INTO `t_routel` VALUES (6, 1, '2023-02-09 01:08:15', 0, NULL, NULL, '广州市——成都市', '广州市', '成都市', '2023-02-20 08:00:00', '2023-02-16 08:00:00', 4, '16e0aacb-37a0-42e2-93c4-12e4eb31567b', 888, 1, '1675876094147.jpg', '白沙祠——圭峰山——开平碉楼', 2);
INSERT INTO `t_routel` VALUES (7, 1, '2023-02-09 01:09:08', 0, NULL, NULL, '南宁市——四川市', '南宁市', '成都市', '2023-02-12 08:00:00', '2023-02-09 09:08:43', 3, '5ec64b70-6fc1-40d9-9d27-2c43c97a4fc5', 555, 1, '1675876146889.jpg', '白沙祠——圭峰山——开平碉楼', 2);
INSERT INTO `t_routel` VALUES (8, 1, '2023-02-09 01:10:14', 0, NULL, NULL, '广州市——武汉市', '武汉市', '广州市', '2023-02-11 09:09:48', '2023-02-09 09:09:43', 2, 'e67de938-c538-4bfd-bb8e-b5cb492b1360', 999, 1, '1675876212859.jpg', '白沙祠——圭峰山——开平碉楼', 2);
INSERT INTO `t_routel` VALUES (9, 1, '2023-02-09 01:20:22', 1, NULL, NULL, '海口市——武汉市', '海口市', '武汉市', '2023-02-11 08:00:00', '2023-02-09 09:19:26', 100, 'f3a29be3-1177-4134-b257-139fd18b47b6', 9999, 1, '1675876820494.jpg', NULL, 2);
INSERT INTO `t_routel` VALUES (10, 1, '2023-02-28 16:51:24', 0, NULL, NULL, '唐山市——秦皇岛市', '唐山市', '秦皇岛市', '2023-02-28 08:00:00', '2023-02-27 08:00:00', 1, '79f3a2e5-2945-4caf-9de7-f2b749d61c83', 100, 1, '1677574266698.png', '8888888888888888888888888888888888888888888888', 2);
INSERT INTO `t_routel` VALUES (11, 1, '2023-02-28 16:58:51', 0, NULL, NULL, '百色市——广州市', '百色市', '广州市', '2023-01-24 20:54:19', '2023-01-21 20:54:19', 3, '467bd90f-37a8-432f-b1eb-7d82228c8e48', 1000, 1, '1675876094147.jpg', '白沙祠——圭峰山——开平碉楼', 2);
INSERT INTO `t_routel` VALUES (12, 1, '2023-02-28 17:12:07', 0, NULL, NULL, '南宁市——广州市', '南宁市', '广州市', '2023-01-24 20:54:19', '2023-01-21 20:54:19', 3, '9dbfc2f7-a775-44f9-ac35-7a9b9128372f', 1000, 1, '1675876094147.jpg', '白沙祠——圭峰山——开平碉楼', 2);

-- ----------------------------
-- Table structure for t_strategy_num
-- ----------------------------
DROP TABLE IF EXISTS `t_strategy_num`;
CREATE TABLE `t_strategy_num`  (
  `strategy_id` bigint(50) NOT NULL COMMENT '攻略id',
  `collect_count` int(100) NULL DEFAULT 0 COMMENT '攻略收藏数',
  `number_of_likes` int(100) NULL DEFAULT 0 COMMENT '点赞数',
  `delete_states` int(1) NULL DEFAULT 0 COMMENT '0为为删除 1为删除',
  `add_time` datetime NULL DEFAULT NULL COMMENT '发表时间',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '攻略标题',
  `user_id` bigint(50) NOT NULL COMMENT '发表人id',
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_strategy_num
-- ----------------------------
INSERT INTO `t_strategy_num` VALUES (4, 0, 2, 0, '2023-02-01 00:19:48', '广西一日游攻略', 4, 1);

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` bigint(100) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `state` int(1) NULL DEFAULT 1 COMMENT '1为发布',
  `user_type` int(1) NULL DEFAULT 2 COMMENT '0为超级管理员，1管理员，2用户',
  `create_by` bigint(60) NULL DEFAULT NULL COMMENT '创建人id',
  `update_by` bigint(30) NULL DEFAULT NULL COMMENT '更新人id',
  `delete_status` int(1) NULL DEFAULT 0 COMMENT '0为不删除',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `link_tel` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `add_time` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `ic_code` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `province` bigint(20) NULL DEFAULT NULL COMMENT '省份',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `id`(`id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES (43, '8882', '$2a$10$oqmtRLVGCoocVKQEbsR8HeOrE9P2sEPaKLgDiNs1p1J/1/3OEhe4C', 0, 2, NULL, NULL, 1, '小张', '18777472222', NULL, '2023-02-23 23:49:42', '450522200182882222', 4, '1677167369942.png', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (48, 'don99', '$2a$10$7kYymxajhEwnPOTOYQRbseVS1N8uQhKnUVdltW1RAm.ng182LhaGW', 1, 2, NULL, NULL, 0, '小李子233', '18777472266', '2023-06-26 16:12:27', '2023-02-26 20:04:54', '450422200010828222', 3, '1677413088868.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (57, 'hzw001', '$2a$10$GitpO3b06K1d3vV5Aad9AeztLrgbkRbf5HCVljMQa27sXRWkKkD8G', 1, 2, NULL, NULL, 0, '小黄233', '18777564421', NULL, '2023-05-27 14:28:30', '450422200109086645', 18, '1685167770867.png', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (40, 'hzw090', '$2a$10$u/Zv3zTNWSqh4YQhPuf.7OHOD4HyWZEXpeHfwp4CgMsi9dg069oTu', 1, 2, NULL, NULL, 0, '小黄', '13666666666', NULL, '2023-02-23 23:39:59', '12781288399277291', NULL, '1675875216748.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (52, 'hzw11', '$2a$10$uMQ7IlEmvsYvO9zTWSctduZyLM9HJqJcFa/qv0GTL4s.kRm4vZVYK', 1, 2, NULL, NULL, 0, '小王', '12345678901', NULL, '2023-02-28 17:54:10', '123456789012345678', 16, '1677578046617.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (49, 'hzw111', '$2a$10$clk/eud2DkvY2F3fkhIheO0z.purue30NPoMitpwk8PpFYMYxIcMq', 1, 2, NULL, NULL, 0, '小杰', '18777655521', NULL, '2023-02-26 20:53:58', '450422200807087722', 3, '1677413517467.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (53, 'hzw22', '$2a$10$ksBeQVnD.V0jwSl2SBUhreAAK11Tu/MsCYUmPAJbkrIqOn2EWEqna', 1, 2, NULL, NULL, 0, '小黄233', '18777456632', '2023-03-26 19:52:42', '2023-03-26 18:43:13', '150422200709075521', 18, '1679827376702.png', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (3, 'lisi', '$2a$10$4uON919r3C6d5.8rZrnI6.OAQpE3aMWgRjAloch5TiX220uFCUcou', 1, 2, 1, 1, 0, '小黄', '12366728332', '2023-05-24 15:14:22', '2023-01-28 10:23:55', '127812883992772911', 18, '1675875375870.jpg', '');
INSERT INTO `tb_admin` VALUES (7, 'l小杰', '$2a$10$qZueidhy9kwnoR9v8BjU7u5spdict1rFvbtdHCirCF3yvMM5eAGCa', 1, 2, NULL, NULL, 1, '非常丰富', '12453553535', NULL, '2023-02-20 17:26:23', '123456789111111111', 3, '1676885180664.png', '');
INSERT INTO `tb_admin` VALUES (1, 'root', '$2a$10$zmWpqGdxQRPtQFQhWy/vJuACa79WDtvE/fFp9gVAB.5DEQ67WtD5S', 1, 1, 1, NULL, 0, '小黄233', '12182821213', '2023-03-23 12:07:32', '2023-01-28 10:23:51', '12781288399277291', 4, '1675875216748.jpg', '');
INSERT INTO `tb_admin` VALUES (17, 'root12', '$2a$10$EBNIwosM8AC9gx3GUtBTJ.b4lnt6WU5ROIePUmpKrm/tJTAgJpirS', 1, 1, NULL, NULL, 0, '216721', '17656272722', NULL, '2023-02-21 13:36:11', '123457892222222211', 3, '1676957765798.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (39, 'root222', '$2a$10$J1Os6kLD41g1EysgZk.jd.rGWzbbPHD9iyeY8hSTmbuL.ban707nK', 1, 1, NULL, NULL, 0, '哈哈哈', '18777272662', NULL, '2023-02-23 22:49:31', '450422200109929921', 5, '1677163597740.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (55, 'user01', '$2a$10$mxhqweb5Qy44gGh4nuiKi.AIy8XkjIF8T2Jcvnip9GINLecw9WKbS', 1, 2, NULL, NULL, 0, '小黄1', '18777471825', NULL, '2023-05-24 16:59:34', '250422200107086645', 18, '1684918760850.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (4, 'wang', '$2a$10$nbiBDuypMwaqm7hzn2kmr.RYYe7O5RVFGnIqkph.NvgJhkfg0fAJG', 2, 2, 1, NULL, 1, '寒假社会实践', '12345678911', '2023-02-06 23:57:03', '2023-02-04 17:54:49', '127812883992772911', 7, '1675875216748.jpg', '');
INSERT INTO `tb_admin` VALUES (32, 'xiao', '$2a$10$TWmBTmbdZIeMN9y03W72n.pPzd98SnEdYur/3HRay8jPwuhg589wG', 1, 2, NULL, NULL, 1, '小黄', '13666666666', NULL, '2023-02-21 19:53:58', '12781288399277291', NULL, '1675875216748.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (56, 'xiaocheng', '$2a$10$SLGaQRMrbF35iosuwJcS5uMBVlkoJw0uWt60GhIP6OrmNGpUd6WgS', 1, 2, NULL, NULL, 0, '小城', '18777575543', NULL, '2023-05-27 13:14:21', '450422200107096645', 18, '1685164457520.png', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (42, 'yu72', '$2a$10$YkFgZjcojINgTiwfM6FHKO6ZTJdHqtcEjiXkS2r13WOQEPr2WoT.K', 1, 2, NULL, NULL, 0, '88838', '19882882877', NULL, '2023-02-23 23:48:01', '450422200109929921', 3, '1677163267405.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (33, 'yuyu', '$2a$10$l3fj1V3A698tBg1cYRVaN.LKcWsxJ6OLXOm2IFG6xPuYQF/HOsrGy', 1, 2, NULL, NULL, 0, '小张', '18777471227', NULL, '2023-02-23 22:25:09', '450422200100809262', 3, '1677162190185.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (34, 'yuyu2', '$2a$10$SIydQV8xowFBgDoowOUmm.9EEv2qT.dC1D.HFX1JItqgX43Lvkjxu', 1, 2, NULL, NULL, 1, '秀爱', '18772727272', NULL, '2023-02-23 22:28:28', '450422200108092211', 6, '1677162481618.png', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (36, 'yuyu7', '$2a$10$mdZySAx4q3/uikFZsHrNmeSmH3oZId50Ki0sDLXRGiaHE7al1TQPe', 1, 2, NULL, NULL, 0, '秀爱', '18772727272', NULL, '2023-02-23 22:30:43', '450422200108092211', 6, '1677162481618.png', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (47, 'yuyu71', '$2a$10$jCS3MiB/ZG.iQaex0WJmXeWwh3HbQthigr6DMd0QOVIHpT9djLQFa', 1, 2, NULL, NULL, 0, 'hdhs', '18777272727', '2023-02-24 01:11:00', '2023-02-24 00:17:43', '450422200102020621', 4, '1677169057587.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (37, 'yuyu7782', '$2a$10$DM6hN66G3WmauVFO6kkOZu.wASEcmqKb4jkF4dQtVr8wLWQmGMk3K', 1, 2, NULL, NULL, 0, '哈哈哈', '18777726663', NULL, '2023-02-23 22:36:04', '450422200109929921', 3, '1677162949452.png', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (38, 'yuyu778211', '$2a$10$GC3Fbh0Z/Dm6fTnH3KCxUeTJSPdIhBdKVoewcFP1P.1H92RHTQPOy', 1, 2, NULL, NULL, 1, '88838', '19882882877', NULL, '2023-02-23 22:41:57', '450422200109929921', 3, '1677163267405.jpg', '2071252817@qq.com');
INSERT INTO `tb_admin` VALUES (9, '小6', '$2a$10$t942xFplth6DYQSxs0YzYeSxgZ7N.EfebZQz1r00DBH5zrmXVfL0u', 1, 2, NULL, NULL, 1, '777', '18777475545', NULL, '2023-02-20 19:24:59', '123456789111111111', NULL, '1676892297859.jpg', '');
INSERT INTO `tb_admin` VALUES (6, '小华', '$2a$10$hh/QsoF5t7yffz8a5eh6PO9QSmRnrBlKjRZa7aRCad0rIfMPOVvve', 1, 2, NULL, NULL, 0, 'dong', '12345678911', NULL, '2023-02-14 13:38:25', '123456789111111119', 5, '1676353098549.png', '');
INSERT INTO `tb_admin` VALUES (8, '小妮子', '$2a$10$oBB3JZLSGhyFxbSxRUZd7OzhOQBL0vksT8PcG4xgL.nTPOhzOq4fW', 1, 2, NULL, NULL, 1, '212121', '18777475545', NULL, '2023-02-20 17:45:07', '123456789111111111', NULL, NULL, '');
INSERT INTO `tb_admin` VALUES (10, '小张', '$2a$10$nKAPNiWgRBOEEK2aIz1iNOrHk95M3cjwIwNABmt.X7O..LglPRYnC', 1, 2, NULL, NULL, 1, '张三', '12345678911', NULL, '2023-02-21 00:35:43', '123456789111111122', 3, '1676910928309.png', '');
INSERT INTO `tb_admin` VALUES (5, '程序员公敌大bug', '$2a$10$xV.k3sb4kiD6E9Oaf.8ZuO3O7lSOSMcOwFUIx92pGt1ndZ6IyOKJS', 1, 2, NULL, NULL, 1, '小花', '1234567891', '2023-02-14 12:11:03', '2023-02-14 12:04:46', '12781288399277291', 4, '1675875375870.jpg', '');

-- ----------------------------
-- Table structure for tb_car
-- ----------------------------
DROP TABLE IF EXISTS `tb_car`;
CREATE TABLE `tb_car`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ADD_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '添加人ID',
  `ADD_TIME` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `DELETE_STATUS` int(1) NULL DEFAULT 0 COMMENT '删除标志 0',
  `MODIFY_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '修改人ID',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `TITLE` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车票标题',
  `START_PLACE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发地点',
  `END_PLACE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达地点',
  `START_DATE` date NULL DEFAULT NULL COMMENT '出发日期',
  `Time_Range` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开点时间段',
  `NEED_TIME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '耗时',
  `GATHER_PLACE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上车集中地',
  `TYPE` int(1) NULL DEFAULT 1 COMMENT '车的类型，0是高铁，1是火车',
  `Intermediate_Stop` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经停站',
  `IMG_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `STATE` int(1) NULL DEFAULT 1 COMMENT '状态 0注销，1发布，2待发布',
  `REMARK` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `PRICE` double NULL DEFAULT NULL COMMENT '价格',
  `Intermediate_Time` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '45' COMMENT '换乘时间',
  `Interchange_Station` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '换乘站',
  `order_type` int(1) NULL DEFAULT 0 COMMENT '订单类型',
  `train_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车次',
  `train_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '火车名',
  `time_end` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结束时间段',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_car
-- ----------------------------
INSERT INTO `tb_car` VALUES (4, NULL, '2023-01-19 21:21:17', 1, 1, '2023-06-26 22:17:21', '梧州市——广州市', '梧州市', '唐山市', '2019-03-17', '9:00', '4时00分', '梧州东', 1, '桂林', NULL, 2, '请准时到达上车地点，过时不候，谢谢。', 80, '30分', '百色233', 0, 'G7271', '和谐1号', '8:00');
INSERT INTO `tb_car` VALUES (5, NULL, '2023-01-21 11:14:13', 0, 1, '2023-02-07 01:59:13', '南宁市——广州市', '南宁市', '广州市', '2019-03-21', '9:00', '4时00分', '南宁东', 1, '百色-桂林北-广州', NULL, 0, '请准时到达上车地点，过时不候，谢谢。', 80, '45分', '桂林北', 0, 'G7272', '和谐2号', '13:00');
INSERT INTO `tb_car` VALUES (6, NULL, '2023-01-21 12:34:20', 0, 1, '2023-02-07 01:29:15', '南宁市——广州市', '南宁市', '广州市', '2019-03-21', '9:00', '4时00分', '南宁东', 1, NULL, NULL, 1, '请准时到达上车地点，过时不候，谢谢。', 80, '30分', '南宁北', 0, 'G7271', '和谐1号', '13:00');
INSERT INTO `tb_car` VALUES (7, 1, '2023-02-07 01:56:06', 0, NULL, NULL, '南宁市——广州市', '南宁市', '广州市', '2023-02-21', '9:30', '3时30分', '南宁东', 1, '桂林-百色-广州南', NULL, 1, '注意时间', 80, '35分', '百色', 0, 'G7272', '和谐2号', '13:00');
INSERT INTO `tb_car` VALUES (8, 1, '2023-02-07 15:07:50', 0, NULL, NULL, '南宁市——广州市', '南宁市', '广州市', '2023-02-07', '9:30', '3时30分', '南宁东', 0, '桂林-百色-广州南', NULL, 1, '注意时间！！！', 80, '30分', '百色', 0, 'K1223', '和谐5号', '13:00');
INSERT INTO `tb_car` VALUES (9, 1, '2023-02-07 15:19:12', 1, NULL, NULL, '南宁市——广州市', '南宁市', '广州市', NULL, '9:35', '3时25分', '南宁东', 0, '桂林-百色-广州南', NULL, 1, '注意时间！！！', 80, '30分', '百色', 0, 'K1223', '和谐6号', '12:30');
INSERT INTO `tb_car` VALUES (10, 1, '2023-02-23 18:32:21', 1, 1, '2023-02-23 19:25:04', '南宁市——广州市', '南宁市', '广州市', '2023-02-25', '12:30', '3时30分', '北京东', 1, '北京-桂林-广州', NULL, 1, '注意时间', 66, '1时20分', '桂林', 0, 'G8888', '和谐5号', '15:30');
INSERT INTO `tb_car` VALUES (11, 1, '2023-02-28 13:07:03', 0, NULL, NULL, '武汉市——广州市', '武汉市', '广州市', '2023-02-28', '9:00', '3时30分', '武汉高铁站', 1, '湖南-成都-贵阳', NULL, 1, '注意时间', 66, '2时30分', '成都', 0, 'G7888', NULL, '15:00');
INSERT INTO `tb_car` VALUES (12, 1, '2023-06-26 16:27:32', 0, 1, '2023-06-26 16:32:03', '梧州市-唐山市', '梧州市', '唐山市', '2023-06-07', '9:30', '3时30分', '北京高铁站', 1, '广州市-武汉市', NULL, 1, '222222222', 999, '30分', '武汉高铁站', 0, 'G2882', NULL, '19:30');

-- ----------------------------
-- Table structure for tb_hotel
-- ----------------------------
DROP TABLE IF EXISTS `tb_hotel`;
CREATE TABLE `tb_hotel`  (
  `ID` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ADD_USER_ID` bigint(46) NULL DEFAULT NULL COMMENT '添加人ID',
  `ADD_TIME` datetime NULL DEFAULT NULL COMMENT '添加时间',
  `DELETE_STATUS` int(1) NULL DEFAULT 0 COMMENT '删除标志',
  `MODIFY_USER_ID` bigint(46) NULL DEFAULT NULL COMMENT '修改人ID',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `HOTEL_NAME` varchar(46) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '酒店名称',
  `HOTEL_INTRO` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '酒店简介',
  `HOTEL_STAR` int(1) NULL DEFAULT 3 COMMENT '酒店星级',
  `LINK_PHONE` varchar(46) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系方式',
  `ADDRESS` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `STATE` int(1) NULL DEFAULT 1 COMMENT '状态 0发布 1待发布',
  `IMG_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '图片',
  `PRICE` double(8, 2) NULL DEFAULT 0.00 COMMENT '价格',
  `HOTEL_NUM` int(10) NULL DEFAULT NULL COMMENT '房间数',
  `Room_Type_Information` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '房型信息',
  `Facility_Information` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '不同星级有不同设施信息',
  `Booking_Instructions` varchar(1000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '订房须知',
  `Scenic_Spot_Id` bigint(10) NULL DEFAULT NULL COMMENT '景区附近酒店',
  `TYPE` int(1) NULL DEFAULT 1 COMMENT '房型：0民宿1酒店2公寓',
  `STAY_DAY` int(10) NULL DEFAULT NULL COMMENT '住宿天数',
  `order_type` int(1) NULL DEFAULT 1 COMMENT '订单类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_hotel
-- ----------------------------
INSERT INTO `tb_hotel` VALUES (1, 1, '2023-01-19 19:49:26', 0, 1, '2023-03-26 14:44:52', '南宁文化酒店22', '广西南宁文化酒店是广西牛逼集团旗下第888888间连锁酒店，按五星级标准设计和管理的涉外商务型酒店。酒店位于天河区商住繁华路段：天河北路。 酒店楼高100层，拥有25100间各类客房，包括行政楼层、女士楼层、非吸烟楼层均可随时任意感受宽带信息的快感；100间多功能宴会（会议）中心、裕景轩中餐厅让您品尝地道的高档粤菜。', 4, '12345678911', '南宁市青秀区', 1, '1677566652518.png', 150.00, NULL, '标准间房内设两张单人床或一张双人床', '早餐免费西点饮料,空调热水前台服务二十四小时,提供干净洗浴用品，附近有停车场，', '禁止携带。宠物！！！', 1, 1, 1, 1);
INSERT INTO `tb_hotel` VALUES (2, 2, '2023-01-19 23:04:32', 0, 1, '2023-02-21 20:19:05', '文华公寓', '酒店位于海阳路779号,位于上南路和海阳路路口，交通便利，临近上海世博展览馆、新国际博览中心、梅赛德斯奔驰文化中心、东方体育中心、太古里购物中心、上海宋城演艺中心，距离新国际博览中心十几分钟车程，门口交通便捷，地铁8号线杨思站、地铁6号线上南路站，步行至酒店10分钟，公交周南线、782、976、986、177等多条线路可直达世博馆、龙阳路、东方明珠、人民广场、外滩等景点，周边设施配套齐全，紧靠杨思医院、宝丰生活广场，餐饮、购物十分方便，是您出行、商务，会议的理想选择。', 4, '123456799011', '南宁市青秀区', 1, '1675879259513.png', 400.00, NULL, '标准间房内设两张单人床或一张双人床', '早餐免费西点饮料,空调热水前台服务二十四小时,提供干净洗浴用品，附近有停车场，', '酒店允许携带儿童入住。18岁及以上客人按成人收费\n床铺：每间客房最多容纳1名17岁及以下儿童，和成人共用现有床铺。部分房型政策根据您所选的房型有所不同，请具体查看预定房型政策每间客房的加床/加婴儿床政策根据您所选的房型有所不同，请具体查看预定房型政策', 2, 2, 2, 1);
INSERT INTO `tb_hotel` VALUES (7, 1, '2023-02-04 13:02:12', 0, NULL, NULL, '广西南宁天塔酒店', '广西南宁天塔酒店是广西牛逼集团旗下第888888间连锁酒店，按五星级标准设计和管理的涉外商务型酒店。酒店位于天河区商住繁华路段：天河北路。 酒店楼高100层，拥有25100间各类客房，包括行政楼层、女士楼层、非吸烟楼层均可随时任意感受宽带信息的快感；100间多功能宴会（会议）中心、裕景轩中餐厅让您品尝地道的高档粤菜。', 5, '0750-6699148', '南宁市青秀区', 1, '1675879259513.png', 300.00, NULL, '高级套间由七至八间房组成的套间，走廊有小酒吧。两个卧室分开，男女卫生间分开，设有客厅、书房、会议室、随员室、警卫室、餐厅厨房设施，有室内花园', '早餐免费西点饮料,空调热水前台服务二十四小时,提供干净洗浴用品，附近有停车场，', '禁止携带。宠物！！！', 4, 1, NULL, 1);
INSERT INTO `tb_hotel` VALUES (8, 1, '2023-02-04 13:28:32', 0, 1, '2023-02-07 10:35:06', '梧州天塔酒店', '广西梧州天塔酒店是广西牛逼集团旗下第888888间连锁酒店，按五星级标准设计和管理的涉外商务型酒店。酒店位于天河区商住繁华路段：天河北路。 酒店楼高100层，拥有25100间各类客房，包括行政楼层、女士楼层、非吸烟楼层均可随时任意感受宽带信息的快感；100间多功能宴会（会议）中心、裕景轩中餐厅让您品尝地道的高档粤菜。', 5, '12345678922', '广州市天河区', 1, '1675879886227.png', 1000.00, NULL, '标准间房内设两张单人床或一张双人床', '早餐免费西点饮料,空调热水前台服务二十四小时,提供干净洗浴用品，附近有停车场，', '禁止携带。宠物！！！', 4, 1, NULL, 1);
INSERT INTO `tb_hotel` VALUES (9, 1, '2023-02-08 16:41:47', 0, 1, '2023-02-09 02:13:06', '梧州天塔民宿', '广西梧州天塔酒店是广西牛逼集团旗下第888888间连锁酒店，按五星级标准设计和管理的涉外商务型酒店。酒店位于天河区商住繁华路段：天河北路。 酒店楼高100层，拥有25100间各类客房，包括行政楼层、女士楼层、非吸烟楼层均可随时任意感受宽带信息的快感；100间多功能宴会（会议）中心、裕景轩中餐厅让您品尝地道的高档粤菜。', 5, '0750-6699148', '广州市天河区', 1, '1675879984432.png', 300.00, NULL, '标准间房内设两张单人床或一张双人床', '台球厅、居酒屋、电子厅、小型KTV包厢', '禁止携带。宠物！！！', 4, 0, NULL, 1);
INSERT INTO `tb_hotel` VALUES (10, 1, '2023-02-08 16:42:33', 0, NULL, NULL, '南宁天塔民宿', '广西南宁天塔酒店是广西牛逼集团旗下第888888间连锁酒店，按五星级标准设计和管理的涉外商务型酒店。酒店位于天河区商住繁华路段：天河北路。 酒店楼高100层，拥有25100间各类客房，包括行政楼层、女士楼层、非吸烟楼层均可随时任意感受宽带信息的快感；100间多功能宴会（会议）中心、裕景轩中餐厅让您品尝地道的高档粤菜。', 5, '0750-6699148', '南宁市青秀区', 1, '1675879886227.png', 100.00, NULL, '标准间房内设两张单人床或一张双人床', '台球厅、居酒屋、电子厅、小型KTV包厢', '禁止携带。宠物！！！', 5, 0, NULL, 1);
INSERT INTO `tb_hotel` VALUES (11, 1, '2023-02-08 16:42:59', 0, 1, '2023-02-09 02:12:40', '桂林天塔公寓', '广西桂林天塔酒店是广西牛逼集团旗下第888888间连锁酒店，按五星级标准设计和管理的涉外商务型酒店。酒店位于天河区商住繁华路段：天河北路。 酒店楼高100层，拥有25100间各类客房，包括行政楼层、女士楼层、非吸烟楼层均可随时任意感受宽带信息的快感；100间多功能宴会（会议）中心、裕景轩中餐厅让您品尝地道的高档粤菜。', 5, '0750-6699148', '桂林市七星区', 1, '1675879957315.png', 100.00, NULL, '标配套间由1至4间房组成的套间，走廊有小酒吧。两个卧室分开，男女卫生间分开，设有客厅、书房、餐厅厨房设施', '前台保险柜 商务中心 会议设施 商品部 安全消防系统 咖啡厅 大堂报纸 无烟楼层 无障碍通道 24小时热水 中餐厅西餐厅 宴会厅 免费自动擦鞋机 多功能厅 多种规格电源插座 遮光窗帘 手动窗帘 书桌备用床具床具：毯子、被、针线包 空调国内长途电话 国际长途电话 有线频道 液晶电视机 免费瓶装水电热水壶 独立淋浴间 吹风机 拖鞋 浴室 化妆放大镜 房内保险箱(部分) 咖啡壶/茶壶(部分) 迷你吧(部分) 浴缸(部分) 浴衣(部分) 所有公共及私人场所严禁吸烟暖气 衣柜/衣橱(部分)电脑(部分)220V电压插座 免费停车有电梯免费wifi', '禁止携带。宠物！！！', 6, 2, NULL, 1);
INSERT INTO `tb_hotel` VALUES (12, 1, '2023-02-08 16:43:39', 0, NULL, NULL, '武汉天塔公寓', '武汉天塔酒店是广西牛逼集团旗下第888888间连锁酒店，按五星级标准设计和管理的涉外商务型酒店。酒店位于天河区商住繁华路段：天河北路。 酒店楼高100层，拥有25100间各类客房，包括行政楼层、女士楼层、非吸烟楼层均可随时任意感受宽带信息的快感；100间多功能宴会（会议）中心、裕景轩中餐厅让您品尝地道的高档粤菜。', 2, '0750-6699148', '武汉市江岸区', 1, '1675879886227.png', 100.00, NULL, '高级套间由七至八间房组成的套间，走廊有小酒吧。两个卧室分开，男女卫生间分开，设有客厅、书房、会议室、随员室、警卫室、餐厅厨房设施，有的还有室内花园', '前台保险柜 商务中心 会议设施 商品部 安全消防系统 咖啡厅 大堂报纸 无烟楼层 无障碍通道 24小时热水 中餐厅西餐厅 宴会厅 免费自动擦鞋机 多功能厅 多种规格电源插座 遮光窗帘 手动窗帘 书桌备用床具床具：毯子、被、针线包 空调国内长途电话 国际长途电话 有线频道 液晶电视机 免费瓶装水电热水壶 独立淋浴间 吹风机 拖鞋 浴室 化妆放大镜 房内保险箱(部分) 咖啡壶/茶壶(部分) 迷你吧(部分) 浴缸(部分) 浴衣(部分) 所有公共及私人场所严禁吸烟暖气 衣柜/衣橱(部分)电脑(部分)220V电压插座 免费停车有电梯免费wifi', '酒店允许携带儿童入住。18岁及以上客人按成人收费\n床铺：每间客房最多容纳1名17岁及以下儿童，和成人共用现有床铺。部分房型政策根据您所选的房型有所不同，请具体查看预定房型政策每间客房的加床/加婴儿床政策根据您所选的房型有所不同，请具体查看预定房型政策', 6, 2, NULL, 1);
INSERT INTO `tb_hotel` VALUES (13, 1, '2023-02-20 14:22:29', 0, NULL, NULL, '帝国酒店', '哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈', 5, '187777777722', '武汉市江岸区', 1, '1676874117303.png', 999.00, NULL, '1', '2', '酒店允许携带儿童入住。18岁及以上客人按成人收费\n床铺：每间客房最多容纳1名17岁及以下儿童，和成人共用现有床铺。部分房型政策根据您所选的房型有所不同，请具体查看预定房型政策每间客房的加床/加婴儿床政策根据您所选的房型有所不同，请具体查看预定房型政策', 5, 1, NULL, 1);
INSERT INTO `tb_hotel` VALUES (14, 1, '2023-02-23 19:31:36', 0, 1, '2023-02-23 19:33:26', '藤校公寓', '酒店位于苏州工业园金鸡湖畔，主要目标客户为广大商旅人士；酒店边上24小时的全家超市，餐饮一条街。酒店秉持为客人提供“安全、干净、舒适”的住宿环境的理念，向客户提供更新的宾客体验和更高的服务水平：宽敞舒适的大床房，24小时中央空调及热水供应，楼层还设有净水器以方便客人使用；所有房间覆盖WIFI和网络电视，为入住增添缤纷乐趣；内设自动按摩椅、人脸识别自动售货机、，全方位的为客人提供便利条件。酒店有停车场，住店客人免费停车(车位有限，先到先得)、一楼有休息区，有瑞幸咖啡和网咖，二楼有台球，5楼有健身房等，客人住宿之余享受轻松的休闲娱乐。', 5, '18777471822', '武汉市江岸区', 1, '1675879886227.png', 666.00, NULL, '牛批', '酒店边上24小时的全家超市，餐饮一条街。酒店秉持为客人提供“安全、干净、舒适”的住宿环境的理念，向客户提供更新的宾客体验和更高的服务水平：宽敞舒适的大床房，24小时中央空调及热水供应，楼层还设有净水器以方便客人使用；所有房间覆盖WIFI和网络电视，为入住增添缤纷乐趣；内设自动按摩椅、人脸识别自动售货机、，全方位的为客人提供便利条件。酒店有停车场，住店客人免费停车(车位有限，先到先得)、一楼有休息区，有瑞幸咖啡和网咖，二楼有台球，5楼有健身房等，客人住宿之余享受轻松的休闲娱乐。', '酒店允许携带儿童入住。18岁及以上客人按成人收费\n床铺：每间客房最多容纳1名17岁及以下儿童，和成人共用现有床铺。部分房型政策根据您所选的房型有所不同，请具体查看预定房型政策每间客房的加床/加婴儿床政策根据您所选的房型有所不同，请具体查看预定房型政策', 3, 2, NULL, 1);
INSERT INTO `tb_hotel` VALUES (15, 1, '2023-02-23 21:02:22', 0, NULL, NULL, '广州明华酒店', '五星级酒店。', 5, '187762662626', '广州市天河区', 1, '1675879886227.png', 999.00, NULL, '66666', '666', '66666', 6, 1, NULL, 1);
INSERT INTO `tb_hotel` VALUES (16, 1, '2023-02-28 14:39:05', 0, 1, '2023-02-28 14:39:33', '海口大华酒店', '酒店位于海阳路779号,门口交通便捷，地铁8号线杨思站、地铁6号线上南路站，步行至酒店10分钟，公交周南线、782、976、986人民广场、外滩等景点，周边设施配套齐全，紧靠杨思医院、宝丰生活广场，餐饮、购物十分方便，是您出行、商务，会议的理想选择。', 3, '18777472256', '海口市美兰区', 1, '1675879886227.png', 188.00, NULL, '标准间房内设两张单人床或一张双人床', '早餐免费西点饮料,空调热水前台服务二十四小时,提供干净洗浴用品，附近有停车场，', '酒店允许携带儿童入住。18岁及以上客人按成人收费\n床铺：每间客房最多容纳1名17岁及以下儿童，和成人共用现有床铺。部分房型政策根据您所选的房型有所不同，请具体查看预定房型政策每间客房的加床/加婴儿床政策根据您所选的房型有所不同，请具体查看预定房型政策', 14, 1, NULL, 1);

-- ----------------------------
-- Table structure for tb_insurance
-- ----------------------------
DROP TABLE IF EXISTS `tb_insurance`;
CREATE TABLE `tb_insurance`  (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ADD_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '添加人id',
  `ADD_TIME` datetime NULL DEFAULT NULL COMMENT '保险公司',
  `DELETE_STATUS` int(1) NULL DEFAULT 0 COMMENT '0为未删除',
  `MODIFY_USER_ID` bigint(20) NULL DEFAULT NULL COMMENT '修改人id',
  `MODIFY_TIME` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `TITLE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `INSURANCE_COMPANY` int(1) NULL DEFAULT NULL COMMENT '保险公司',
  `PRICE` double NULL DEFAULT NULL COMMENT '价格',
  `TYPE` int(1) NULL DEFAULT NULL COMMENT '保险类型',
  `RESUME` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保险介绍',
  `STATE` int(1) NULL DEFAULT 1 COMMENT '保险状态 0注销 1 发布 2待发布',
  `IMG_URL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保险图片',
  `order_type` int(1) NULL DEFAULT 4 COMMENT '订单类型',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_insurance
-- ----------------------------
INSERT INTO `tb_insurance` VALUES (1, 1, '2023-01-20 12:09:21', 1, NULL, NULL, '保你一生平安', 1, 666, 1, NULL, 1, '1675766536633.png', 4);
INSERT INTO `tb_insurance` VALUES (2, NULL, NULL, 1, 1, '2023-02-10 12:52:35', '自保旅游保险', 0, 100, 0, '提供意外伤害、意外医疗费用补偿等5项保障', 1, '1676004754155.png', 4);
INSERT INTO `tb_insurance` VALUES (3, NULL, NULL, 1, NULL, NULL, '自保旅游保险888', 0, 100, 0, '提供意外伤害、意外医疗费用补偿等5项保障', 1, '1675766536633.png', 4);
INSERT INTO `tb_insurance` VALUES (4, NULL, NULL, 1, NULL, NULL, '自保旅游保险888', 0, 100, 0, '提供意外伤害、意外医疗费用补偿等5项保障', 1, '1675766536633.png', 4);
INSERT INTO `tb_insurance` VALUES (5, 1, '2023-02-04 15:20:21', 0, 1, '2023-02-10 12:52:50', '酒店保险', 2, 80, 1, '提供意外伤害、意外医疗费用补偿等5项保障', 1, '1676004768578.png', 4);
INSERT INTO `tb_insurance` VALUES (6, 1, '2023-02-04 15:20:43', 0, 1, '2023-02-10 12:53:18', '景区保险', 0, 80, 2, '提供意外伤害、意外医疗费用补偿等5项保障', 1, '1676004795986.png', 4);
INSERT INTO `tb_insurance` VALUES (7, 1, '2023-02-04 15:20:58', 0, 1, '2023-02-10 12:53:38', '出行保险', 3, 80, 3, '提供意外伤害、意外医疗费用补偿等5项保障', 1, '1676004816602.png', 4);
INSERT INTO `tb_insurance` VALUES (8, 1, '2023-02-04 15:21:09', 0, 1, '2023-02-10 12:53:57', '路线保险', 0, 80, 4, '提供意外伤害、意外医疗费用补偿等5项保障', 1, '1676004834923.png', 4);
INSERT INTO `tb_insurance` VALUES (9, 1, '2023-02-07 15:50:33', 1, NULL, NULL, '治疗呸', 3, 777, 0, '走过路过不要买', 1, '1675766536633.png', 4);
INSERT INTO `tb_insurance` VALUES (10, 1, '2023-02-07 16:32:10', 1, NULL, NULL, '一生无视', 0, 777, 1, '2333333', 1, '1675766536633.png', 4);
INSERT INTO `tb_insurance` VALUES (11, 1, '2023-02-07 16:42:38', 1, 1, '2023-02-07 17:15:09', '景区保险', 2, 888, 0, '不买摆吧', 1, '1676004834923.png', 4);
INSERT INTO `tb_insurance` VALUES (12, 1, '2023-02-07 17:17:51', 1, 1, '2023-02-10 12:55:10', '景区保险', 2, 11, 2, '保你平安出入景区', 1, '1676004834923.png', 4);
INSERT INTO `tb_insurance` VALUES (13, 1, '2023-02-08 23:45:22', 1, NULL, NULL, 'czh', 0, 9999, 0, 'hahahha', 1, '1676004834923.png', 4);
INSERT INTO `tb_insurance` VALUES (14, 1, '2023-02-20 15:57:28', 0, NULL, NULL, '保你无忧快来', 2, 737, 0, '国会大厦国会大厦', 1, '1676879845881.png', 4);

-- ----------------------------
-- Table structure for tb_province
-- ----------------------------
DROP TABLE IF EXISTS `tb_province`;
CREATE TABLE `tb_province`  (
  `id` bigint(16) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `province` varchar(16) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '省份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_province
-- ----------------------------
INSERT INTO `tb_province` VALUES (1, '北京');
INSERT INTO `tb_province` VALUES (2, '天津');
INSERT INTO `tb_province` VALUES (3, '上海');
INSERT INTO `tb_province` VALUES (4, '重庆');
INSERT INTO `tb_province` VALUES (5, '河北');
INSERT INTO `tb_province` VALUES (6, '河南');
INSERT INTO `tb_province` VALUES (7, '云南');
INSERT INTO `tb_province` VALUES (8, '辽宁');
INSERT INTO `tb_province` VALUES (9, '黑龙江');
INSERT INTO `tb_province` VALUES (10, '湖南');
INSERT INTO `tb_province` VALUES (11, '安徽');
INSERT INTO `tb_province` VALUES (12, '山东');
INSERT INTO `tb_province` VALUES (13, '新疆');
INSERT INTO `tb_province` VALUES (14, '江苏');
INSERT INTO `tb_province` VALUES (15, '浙江');
INSERT INTO `tb_province` VALUES (16, '江西');
INSERT INTO `tb_province` VALUES (17, '湖北');
INSERT INTO `tb_province` VALUES (18, '广西');
INSERT INTO `tb_province` VALUES (19, '甘肃');
INSERT INTO `tb_province` VALUES (20, '山西');
INSERT INTO `tb_province` VALUES (21, '内蒙古');
INSERT INTO `tb_province` VALUES (22, '陕西');
INSERT INTO `tb_province` VALUES (23, '吉林');
INSERT INTO `tb_province` VALUES (24, '福建');
INSERT INTO `tb_province` VALUES (25, '贵州');
INSERT INTO `tb_province` VALUES (26, '广东');
INSERT INTO `tb_province` VALUES (27, '青海');
INSERT INTO `tb_province` VALUES (28, '西藏');
INSERT INTO `tb_province` VALUES (29, '四川');
INSERT INTO `tb_province` VALUES (30, '宁夏');
INSERT INTO `tb_province` VALUES (31, '海南');
INSERT INTO `tb_province` VALUES (32, '台湾');
INSERT INTO `tb_province` VALUES (33, '香港');
INSERT INTO `tb_province` VALUES (34, '澳门');

SET FOREIGN_KEY_CHECKS = 1;
