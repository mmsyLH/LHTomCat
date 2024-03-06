/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : 12306

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2024-03-06 16:33:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for train_admin
-- ----------------------------
DROP TABLE IF EXISTS `train_admin`;
CREATE TABLE `train_admin` (
  `adminid` varchar(20) NOT NULL COMMENT '客户ID',
  `psw` varchar(50) DEFAULT NULL COMMENT '密码',
  `photo` varchar(50) DEFAULT NULL COMMENT '头像',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `info` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客服表';

-- ----------------------------
-- Records of train_admin
-- ----------------------------

-- ----------------------------
-- Table structure for train_chat
-- ----------------------------
DROP TABLE IF EXISTS `train_chat`;
CREATE TABLE `train_chat` (
  `chatid` int(11) NOT NULL AUTO_INCREMENT COMMENT '聊天ID',
  `sendid` varchar(50) DEFAULT NULL COMMENT '发送者账号',
  `revid` varchar(50) DEFAULT NULL COMMENT '接收者账号',
  `content` varchar(200) DEFAULT NULL COMMENT '聊天内容',
  `createtime` datetime DEFAULT NULL COMMENT '聊天时间',
  `state` varchar(10) DEFAULT NULL COMMENT '状态',
  `info` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`chatid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='聊天记录表';

-- ----------------------------
-- Records of train_chat
-- ----------------------------
INSERT INTO `train_chat` VALUES ('1', 'user1', 'user2', '测试信息1', '2024-02-29 16:57:15', '在线状态？', '');
INSERT INTO `train_chat` VALUES ('2', 'user1', 'user3', '测试信息2', '2024-02-29 16:59:17', '在线状态？', '');
INSERT INTO `train_chat` VALUES ('3', 'user2', 'user1', '测试信息3', '2024-02-29 16:59:17', '在线状态？', '');

-- ----------------------------
-- Table structure for train_city
-- ----------------------------
DROP TABLE IF EXISTS `train_city`;
CREATE TABLE `train_city` (
  `cityid` int(11) NOT NULL AUTO_INCREMENT COMMENT '城市ID',
  `cityname` varchar(50) DEFAULT NULL COMMENT '城市名',
  `cityinfo` varchar(50) DEFAULT NULL COMMENT '备注',
  `provinceid` int(11) DEFAULT NULL COMMENT '省份ID',
  PRIMARY KEY (`cityid`),
  KEY `FK_PROVINCE` (`provinceid`),
  CONSTRAINT `FK_PROVINCE` FOREIGN KEY (`provinceid`) REFERENCES `train_province` (`provinceid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='城市表';

-- ----------------------------
-- Records of train_city
-- ----------------------------
INSERT INTO `train_city` VALUES ('1', '福州市', '', '1');
INSERT INTO `train_city` VALUES ('2', '厦门市', '', '1');
INSERT INTO `train_city` VALUES ('3', '泉州市', '', '1');
INSERT INTO `train_city` VALUES ('4', '广州市', '', '2');
INSERT INTO `train_city` VALUES ('5', '深圳市', '', '2');
INSERT INTO `train_city` VALUES ('6', '东莞市', '', '2');
INSERT INTO `train_city` VALUES ('7', '东城区', '', '3');
INSERT INTO `train_city` VALUES ('8', '西城市', '', '3');
INSERT INTO `train_city` VALUES ('9', '海淀市', '', '3');

-- ----------------------------
-- Table structure for train_county
-- ----------------------------
DROP TABLE IF EXISTS `train_county`;
CREATE TABLE `train_county` (
  `countyid` int(11) NOT NULL AUTO_INCREMENT COMMENT '区县ID',
  `countyname` varchar(50) DEFAULT NULL COMMENT '区县名',
  `countyinfo` varchar(50) DEFAULT NULL COMMENT '备注',
  `cityid` int(11) DEFAULT NULL COMMENT '城市ID',
  PRIMARY KEY (`countyid`),
  KEY `fk_city` (`cityid`),
  CONSTRAINT `fk_city` FOREIGN KEY (`cityid`) REFERENCES `train_city` (`cityid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='区县表';

-- ----------------------------
-- Records of train_county
-- ----------------------------
INSERT INTO `train_county` VALUES ('1', '鼓楼区', '', '1');
INSERT INTO `train_county` VALUES ('2', '台江区', '', '1');
INSERT INTO `train_county` VALUES ('3', '仓山区', '', '1');
INSERT INTO `train_county` VALUES ('4', '思明区', '', '2');
INSERT INTO `train_county` VALUES ('5', '海沧区', '', '2');
INSERT INTO `train_county` VALUES ('6', '洛江区', '', '3');
INSERT INTO `train_county` VALUES ('7', '鲤城区', '', '3');
INSERT INTO `train_county` VALUES ('8', '越秀区', '', '4');
INSERT INTO `train_county` VALUES ('9', '天河区', '', '4');
INSERT INTO `train_county` VALUES ('10', '南山区', '', '5');
INSERT INTO `train_county` VALUES ('11', '罗湖区', '', '5');
INSERT INTO `train_county` VALUES ('12', '莞城区', '', '6');
INSERT INTO `train_county` VALUES ('13', '万江区', '', '6');
INSERT INTO `train_county` VALUES ('14', '朝阳门街道', '', '7');
INSERT INTO `train_county` VALUES ('15', '建国门街道', '', '7');
INSERT INTO `train_county` VALUES ('16', '西长安街街道', '', '8');
INSERT INTO `train_county` VALUES ('17', '学院路街道', '', '9');

-- ----------------------------
-- Table structure for train_number
-- ----------------------------
DROP TABLE IF EXISTS `train_number`;
CREATE TABLE `train_number` (
  `number` varchar(20) NOT NULL COMMENT '车次名',
  `starttime` time DEFAULT NULL COMMENT '开车时间',
  `endtime` time DEFAULT NULL COMMENT '终点时间',
  `startstationid` varchar(20) DEFAULT NULL COMMENT '出发站点名称',
  `endstationid` varchar(20) DEFAULT NULL COMMENT '终点站点名称',
  `num` int(11) DEFAULT NULL COMMENT '余票',
  `money` int(11) DEFAULT NULL COMMENT '票价',
  `ntid` int(11) DEFAULT NULL COMMENT '车次类型',
  `info` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`number`),
  KEY `fk_ntid` (`ntid`),
  KEY `fk_startstationid` (`startstationid`),
  KEY `fk_endstationid` (`endstationid`),
  CONSTRAINT `fk_endstationid` FOREIGN KEY (`endstationid`) REFERENCES `train_station` (`stationid`),
  CONSTRAINT `fk_ntid` FOREIGN KEY (`ntid`) REFERENCES `train_number_type` (`ntid`),
  CONSTRAINT `fk_startstationid` FOREIGN KEY (`startstationid`) REFERENCES `train_station` (`stationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车次表';

-- ----------------------------
-- Records of train_number
-- ----------------------------
INSERT INTO `train_number` VALUES ('D2327', '07:10:00', '09:00:00', '福州', '厦门北', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6203', '10:00:00', '11:46:00', '福州', '厦门', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6204', '08:01:00', '10:18:00', '厦门', '福州', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6206', '09:06:00', '11:04:00', '厦门', '福州', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6207', '07:10:00', '09:37:00', '福州', '厦门', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6208', '10:02:00', '12:03:00', '厦门', '福州', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6212', '12:57:00', '15:02:00', '厦门', '福州', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6213', '09:06:00', '10:56:00', '福州', '厦门北', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6216', '15:04:00', '17:10:00', '厦门', '福州', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6223', '12:05:00', '14:22:00', '福州', '厦门', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6224', '18:07:00', '20:15:00', '厦门', '福州', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6226', '17:15:00', '19:25:00', '厦门', '福州', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6227', '14:19:00', '16:26:00', '福州', '厦门', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6237', '19:23:00', '21:34:00', '福州', '厦门', '100', '151', '2', '');
INSERT INTO `train_number` VALUES ('D6407', '09:22:00', '12:17:00', '福州', '龙岩', '100', '212', '2', '');
INSERT INTO `train_number` VALUES ('D6437', '13:02:00', '16:08:00', '龙岩', '福州', '100', '212', '2', '');

-- ----------------------------
-- Table structure for train_number_info
-- ----------------------------
DROP TABLE IF EXISTS `train_number_info`;
CREATE TABLE `train_number_info` (
  `infoid` int(11) NOT NULL AUTO_INCREMENT COMMENT '车次详情ID',
  `number` varchar(20) DEFAULT NULL COMMENT '车次名',
  `stationorder` int(11) DEFAULT NULL COMMENT '车站顺序',
  `stationid` varchar(20) DEFAULT NULL COMMENT '站点名称',
  `arrivetime` time DEFAULT NULL COMMENT '到站时间',
  `leavetime` time DEFAULT NULL COMMENT '离站时间',
  `num` int(11) DEFAULT NULL COMMENT '余票',
  `money` int(11) DEFAULT NULL COMMENT '距离出发站点的票价',
  `info` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`infoid`),
  KEY `fk_number` (`number`),
  KEY `fk_stationid` (`stationid`),
  CONSTRAINT `fk_number` FOREIGN KEY (`number`) REFERENCES `train_number` (`number`),
  CONSTRAINT `fk_stationid` FOREIGN KEY (`stationid`) REFERENCES `train_station` (`stationid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='车次详情表';

-- ----------------------------
-- Records of train_number_info
-- ----------------------------
INSERT INTO `train_number_info` VALUES ('1', 'D6224', '1', '厦门', '18:07:00', '18:07:00', '0', '0', '');
INSERT INTO `train_number_info` VALUES ('2', 'D6224', '2', '厦门北', '18:31:00', '18:33:00', '20', '30', '');
INSERT INTO `train_number_info` VALUES ('3', 'D6224', '3', '泉州', '18:58:00', '19:00:00', '40', '50', '');
INSERT INTO `train_number_info` VALUES ('4', 'D6224', '4', '莆田', '19:25:00', '19:27:00', '60', '90', '');
INSERT INTO `train_number_info` VALUES ('5', 'D6224', '5', '福州南', '19:58:00', '20:02:00', '80', '120', '');
INSERT INTO `train_number_info` VALUES ('6', 'D6224', '6', '福州', '20:15:00', '20:15:00', '100', '151', '');
INSERT INTO `train_number_info` VALUES ('7', 'D6226', '1', '厦门', '17:15:00', '17:15:00', '0', '0', '');
INSERT INTO `train_number_info` VALUES ('8', 'D6226', '2', '晋江', '17:53:00', '17:55:00', '0', '0', '');
INSERT INTO `train_number_info` VALUES ('9', 'D6226', '3', '泉州', '18:07:00', '18:09:00', '0', '0', '');
INSERT INTO `train_number_info` VALUES ('10', 'D6226', '4', '涵江', '18:40:00', '18:42:00', '0', '0', '');
INSERT INTO `train_number_info` VALUES ('11', 'D6226', '5', '福清', '18:55:00', '18:57:00', '0', '0', '');
INSERT INTO `train_number_info` VALUES ('12', 'D6226', '6', '福州', '19:25:00', '19:25:00', '0', '0', '');
INSERT INTO `train_number_info` VALUES ('13', 'D6216', '1', '厦门', '15:04:00', '15:04:00', '0', '0', '');
INSERT INTO `train_number_info` VALUES ('14', 'D6216', '2', '厦门北', '15:27:00', '15:29:00', '20', '30', '');
INSERT INTO `train_number_info` VALUES ('15', 'D6216', '3', '泉州', '15:55:00', '15:57:00', '40', '50', '');
INSERT INTO `train_number_info` VALUES ('16', 'D6216', '4', '莆田', '16:22:00', '16:24:00', '60', '90', '');
INSERT INTO `train_number_info` VALUES ('17', 'D6216', '5', '福州', '17:10:00', '17:10:00', '80', '120', '');

-- ----------------------------
-- Table structure for train_number_type
-- ----------------------------
DROP TABLE IF EXISTS `train_number_type`;
CREATE TABLE `train_number_type` (
  `ntid` int(11) NOT NULL AUTO_INCREMENT COMMENT '车次类型ID',
  `ntname` varchar(20) DEFAULT NULL COMMENT '车次类型名称',
  `ntinfo` varchar(20) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ntid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='车次类型';

-- ----------------------------
-- Records of train_number_type
-- ----------------------------
INSERT INTO `train_number_type` VALUES ('1', 'GC-高铁/城际', '高铁');
INSERT INTO `train_number_type` VALUES ('2', 'D-动车', '动车');
INSERT INTO `train_number_type` VALUES ('3', 'Z-直达', 'Z-直达');
INSERT INTO `train_number_type` VALUES ('4', 'T-特快', 'T-特快');
INSERT INTO `train_number_type` VALUES ('5', 'K-快速', 'K-快速');
INSERT INTO `train_number_type` VALUES ('6', '其他', '其他');

-- ----------------------------
-- Table structure for train_order
-- ----------------------------
DROP TABLE IF EXISTS `train_order`;
CREATE TABLE `train_order` (
  `orderid` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `userid` varchar(20) DEFAULT NULL COMMENT '账号',
  `number` varchar(20) DEFAULT NULL COMMENT '车次名',
  `startstationid` varchar(20) DEFAULT NULL COMMENT '出发站点名称',
  `endstationid` varchar(20) DEFAULT NULL COMMENT '终点站点名称',
  `num` int(11) DEFAULT NULL COMMENT '余票',
  `money` int(11) DEFAULT NULL COMMENT '距离出发站点的票价',
  `createtime` datetime DEFAULT NULL COMMENT '买票时间',
  `state` varchar(10) DEFAULT NULL COMMENT '状态',
  `info` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`orderid`),
  KEY `fk_number1` (`number`),
  KEY `fk_stationid1` (`startstationid`),
  KEY `fk_stationid2` (`endstationid`),
  CONSTRAINT `fk_number1` FOREIGN KEY (`number`) REFERENCES `train_number` (`number`),
  CONSTRAINT `fk_stationid1` FOREIGN KEY (`startstationid`) REFERENCES `train_station` (`stationid`),
  CONSTRAINT `fk_stationid2` FOREIGN KEY (`endstationid`) REFERENCES `train_station` (`stationid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='买票订单表';

-- ----------------------------
-- Records of train_order
-- ----------------------------
INSERT INTO `train_order` VALUES ('1', 'user1', 'D2327', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '未付款', '');
INSERT INTO `train_order` VALUES ('2', 'user2', 'D2327', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '未付款', '');
INSERT INTO `train_order` VALUES ('3', 'user3', 'D2327', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '未付款', '');
INSERT INTO `train_order` VALUES ('4', 'user4', 'D2327', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '未付款', '');
INSERT INTO `train_order` VALUES ('5', 'user5', 'D2327', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '已付款', '');
INSERT INTO `train_order` VALUES ('6', 'user1', 'D6203', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '未付款', '');
INSERT INTO `train_order` VALUES ('7', 'user1', 'D6204', '厦门', '福州', '0', '140', '2024-02-28 09:07:01', '已付款', '');
INSERT INTO `train_order` VALUES ('8', 'user1', 'D6206', '厦门', '福州', '0', '140', '2024-02-28 09:07:01', '未付款', '');
INSERT INTO `train_order` VALUES ('9', 'user1', 'D6207', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '未付款', '');
INSERT INTO `train_order` VALUES ('10', 'user1', 'D6208', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '已付款', '');
INSERT INTO `train_order` VALUES ('11', 'user1', 'D6212', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '未付款', '');
INSERT INTO `train_order` VALUES ('12', 'user1', 'D6213', '福州', '厦门北', '0', '140', '2024-02-28 09:07:01', '已付款', '');
INSERT INTO `train_order` VALUES ('17', 'user1', 'D2327', '福州', '厦门', '0', '150', '2024-02-29 16:24:25', '未付款', '');

-- ----------------------------
-- Table structure for train_order_comment
-- ----------------------------
DROP TABLE IF EXISTS `train_order_comment`;
CREATE TABLE `train_order_comment` (
  `commentid` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `orderid` int(11) DEFAULT NULL COMMENT '订单ID',
  `userid` varchar(20) DEFAULT NULL COMMENT '账号',
  `content` varchar(50) DEFAULT NULL COMMENT '评论内容',
  `createtime` datetime DEFAULT NULL COMMENT '评论时间',
  `info` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`commentid`),
  KEY `fk_order` (`orderid`),
  KEY `fk_user` (`userid`),
  CONSTRAINT `fk_order` FOREIGN KEY (`orderid`) REFERENCES `train_order` (`orderid`),
  CONSTRAINT `fk_user` FOREIGN KEY (`userid`) REFERENCES `train_user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='买票订单评论表';

-- ----------------------------
-- Records of train_order_comment
-- ----------------------------

-- ----------------------------
-- Table structure for train_province
-- ----------------------------
DROP TABLE IF EXISTS `train_province`;
CREATE TABLE `train_province` (
  `provinceid` int(11) NOT NULL AUTO_INCREMENT COMMENT '省份ID',
  `provincename` varchar(50) DEFAULT NULL COMMENT '省份名',
  `provinceinfo` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`provinceid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='省份表';

-- ----------------------------
-- Records of train_province
-- ----------------------------
INSERT INTO `train_province` VALUES ('1', '福建省', '');
INSERT INTO `train_province` VALUES ('2', '广东省', '');
INSERT INTO `train_province` VALUES ('3', '北京省', '');

-- ----------------------------
-- Table structure for train_station
-- ----------------------------
DROP TABLE IF EXISTS `train_station`;
CREATE TABLE `train_station` (
  `stationid` varchar(20) NOT NULL COMMENT '站点名',
  `stationpy` varchar(20) DEFAULT NULL COMMENT '车站名称的拼音',
  `stationinfo` varchar(20) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`stationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车站';

-- ----------------------------
-- Records of train_station
-- ----------------------------
INSERT INTO `train_station` VALUES ('123123', '12', '123');
INSERT INTO `train_station` VALUES ('1234', '123123', '123');
INSERT INTO `train_station` VALUES ('上饶', '111', '111');
INSERT INTO `train_station` VALUES ('南京南', 'nanjingnan', '北京南');
INSERT INTO `train_station` VALUES ('南平市', 'nanpingshi', '北京南');
INSERT INTO `train_station` VALUES ('南靖', 'nanjing', '北京南');
INSERT INTO `train_station` VALUES ('厦门', 'xiamen', '北京南');
INSERT INTO `train_station` VALUES ('厦门北', 'xiamenbei', '北京南');
INSERT INTO `train_station` VALUES ('台州', 'taizhou', '北京南');
INSERT INTO `train_station` VALUES ('合肥南', 'hefeinan', '北京南');
INSERT INTO `train_station` VALUES ('天津南', 'tianjingnan', '北京南');
INSERT INTO `train_station` VALUES ('婺源', 'wuyuan', '北京南');
INSERT INTO `train_station` VALUES ('宁德', 'ningde', '北京南');
INSERT INTO `train_station` VALUES ('宁波', 'ningbo', '北京南');
INSERT INTO `train_station` VALUES ('宁海', 'ninghai', '北京南');
INSERT INTO `train_station` VALUES ('平潭', 'pingtan', '北京南');
INSERT INTO `train_station` VALUES ('廊坊', 'langfang', '北京南');
INSERT INTO `train_station` VALUES ('延平', 'yanping', '北京南');
INSERT INTO `train_station` VALUES ('建瓯西', 'jiaoouxi', '北京南');
INSERT INTO `train_station` VALUES ('徐州东', 'xuzhoudong', '北京南');
INSERT INTO `train_station` VALUES ('德州东', 'dezhoudong', '北京南');
INSERT INTO `train_station` VALUES ('晋江', 'jinjiang', '北京南');
INSERT INTO `train_station` VALUES ('曲阜东', 'qufudong', '北京南');
INSERT INTO `train_station` VALUES ('杭州东', 'hanghoudong', '北京南');
INSERT INTO `train_station` VALUES ('枣庄', 'zaozhuang', '北京南');
INSERT INTO `train_station` VALUES ('水家湖', 'shoujiahu', '北京南');
INSERT INTO `train_station` VALUES ('泉州', 'quanzhou', '北京南');
INSERT INTO `train_station` VALUES ('泰安', 'taian', '北京南');
INSERT INTO `train_station` VALUES ('济南西', 'jinnanxi', '北京南');
INSERT INTO `train_station` VALUES ('涵江', 'hanjiang', '涵江');
INSERT INTO `train_station` VALUES ('温岭', 'wenling', '北京南');
INSERT INTO `train_station` VALUES ('温州南', 'wenzhounan', '北京南');
INSERT INTO `train_station` VALUES ('湖州', 'huzhou', '北京南');
INSERT INTO `train_station` VALUES ('滁州', 'chuzhou', '北京南');
INSERT INTO `train_station` VALUES ('漳州', 'zhangzhou', '北京南');
INSERT INTO `train_station` VALUES ('福州', 'fuzhou', '北京南');
INSERT INTO `train_station` VALUES ('福州南', 'fuzhounan', '北京南');
INSERT INTO `train_station` VALUES ('福清', 'fuqing', '北京南');
INSERT INTO `train_station` VALUES ('福鼎', 'fuding', '北京南');
INSERT INTO `train_station` VALUES ('莆田', 'putian', '北京南');
INSERT INTO `train_station` VALUES ('蚌埠南', 'bengpunan', '北京南');
INSERT INTO `train_station` VALUES ('铜陵北', 'tonglingbei', '北京南');
INSERT INTO `train_station` VALUES ('长乐东', 'changledong', '北京南');
INSERT INTO `train_station` VALUES ('黄山北', 'huangshanbei', '北京南');
INSERT INTO `train_station` VALUES ('龙岩', 'longyan', '北京南');

-- ----------------------------
-- Table structure for train_town
-- ----------------------------
DROP TABLE IF EXISTS `train_town`;
CREATE TABLE `train_town` (
  `townid` int(11) NOT NULL AUTO_INCREMENT COMMENT '乡镇ID',
  `townname` varchar(50) DEFAULT NULL COMMENT '乡镇名',
  `towninfo` varchar(50) DEFAULT NULL COMMENT '备注',
  `countyid` int(11) DEFAULT NULL COMMENT '区县ID',
  PRIMARY KEY (`townid`),
  KEY `FK_COUNTY` (`countyid`),
  CONSTRAINT `FK_COUNTY` FOREIGN KEY (`countyid`) REFERENCES `train_county` (`countyid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COMMENT='乡镇表';

-- ----------------------------
-- Records of train_town
-- ----------------------------
INSERT INTO `train_town` VALUES ('1', '东街街道', '', '1');
INSERT INTO `train_town` VALUES ('2', '温泉街道', '', '1');
INSERT INTO `train_town` VALUES ('3', '茶亭街道', '', '2');
INSERT INTO `train_town` VALUES ('4', '洋中街道', '', '2');
INSERT INTO `train_town` VALUES ('5', '上渡街道', '', '3');
INSERT INTO `train_town` VALUES ('6', '金山街道', '', '3');
INSERT INTO `train_town` VALUES ('7', '鼓浪屿街道', '', '4');
INSERT INTO `train_town` VALUES ('8', '中华街道', '', '4');
INSERT INTO `train_town` VALUES ('9', '海沧街道', '', '5');
INSERT INTO `train_town` VALUES ('10', '新阳街道', '', '5');
INSERT INTO `train_town` VALUES ('11', '万安街道', '', '6');
INSERT INTO `train_town` VALUES ('12', '双阳街道', '', '6');
INSERT INTO `train_town` VALUES ('13', '开元街道', '', '7');
INSERT INTO `train_town` VALUES ('14', '海滨街道', '', '7');
INSERT INTO `train_town` VALUES ('15', '洪桥街道', '', '8');
INSERT INTO `train_town` VALUES ('16', '白云街道', '', '8');
INSERT INTO `train_town` VALUES ('17', '沙河街道', '', '9');
INSERT INTO `train_town` VALUES ('18', '天园街道', '', '9');
INSERT INTO `train_town` VALUES ('19', '南山街道', '', '10');
INSERT INTO `train_town` VALUES ('20', '南头街道', '', '10');
INSERT INTO `train_town` VALUES ('21', '桂园街道', '', '11');
INSERT INTO `train_town` VALUES ('22', '黄贝街道', '', '11');
INSERT INTO `train_town` VALUES ('23', '莞城街道', '', '12');
INSERT INTO `train_town` VALUES ('24', '南城街道', '', '12');
INSERT INTO `train_town` VALUES ('25', '万江街道', '', '13');
INSERT INTO `train_town` VALUES ('26', '东城街道', '', '13');

-- ----------------------------
-- Table structure for train_user
-- ----------------------------
DROP TABLE IF EXISTS `train_user`;
CREATE TABLE `train_user` (
  `userid` varchar(20) NOT NULL COMMENT '账号',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `money` int(11) DEFAULT NULL COMMENT '余额',
  `id` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `photo` varchar(50) DEFAULT NULL COMMENT '头像',
  `createtime` datetime DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of train_user
-- ----------------------------
INSERT INTO `train_user` VALUES ('user0', 'admin', '973d260d13c0d9a968b550d7a9325433', '男', '1500', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user1', 'luohan', '973d260d13c0d9a968b550d7a9325433', '男', '100', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user10', '沈腾', '123', '男', '1400', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user2', '王宝强', '123', '男', '1100', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user3', '李晓辉', '123', '男', '1200', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user4', '王亮', '123', '男', '100', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user5', '刘德华', '123', '男', '1200', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user6', 'TFBoy', '123', '男', '1030', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user7', '张小斐', '123', '女', '40', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user8', '刀郎', '123', '男', '1050', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');
INSERT INTO `train_user` VALUES ('user9', '那英', '123', '女', '1300', '350201198606012234', '13808082323', 'c1.jpg', '2024-02-28 09:07:01');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id 主键',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `table_name_id_uindex` (`id`),
  UNIQUE KEY `table_name_username_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '973d260d13c0d9a968b550d7a9325433');
INSERT INTO `user` VALUES ('2', 'luohan', '973d260d13c0d9a968b550d7a9325433');
INSERT INTO `user` VALUES ('4', 'LuoHan2', '973d260d13c0d9a968b550d7a9325433');
INSERT INTO `user` VALUES ('5', '12312', 'c2fbc20817fbe67e848c152f1e429c48');
