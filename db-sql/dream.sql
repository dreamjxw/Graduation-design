/*
SQLyog 企业版 - MySQL GUI v8.14
MySQL - 5.6.35 : Database - graduation_design
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`graduation_design` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `graduation_design`;

/*Table structure for table `tb_attention` */

DROP TABLE IF EXISTS `tb_attention`;

CREATE TABLE `tb_attention` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关注表主键id',
        `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '用户id',
        `attention_id` varchar(50) NOT NULL DEFAULT '' COMMENT '关注人id',
        `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
        PRIMARY KEY (`id`),
        KEY `idx_user_id` (`user_id`),
        KEY `idx_attention_id` (`attention_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='关注用户表';

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
        `order_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单号',
        `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '用户id',
        `order_details` mediumtext NOT NULL COMMENT '订单详情   Json串',
        `order_totalprice` double NOT NULL DEFAULT '0' COMMENT '订单总价',
        `order_date_start` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '订单创建时间',
        `order_date_end` datetime NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '订单支付时间',
        `pay_status_id` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态',
        PRIMARY KEY (`order_id`),
        KEY `idx_order_id` (`order_id`),
        KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='订单表';

/*Table structure for table `tb_post` */

DROP TABLE IF EXISTS `tb_post`;

CREATE TABLE `tb_post` (
        `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
        `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '用户ID',
        `post_name` varchar(50) NOT NULL DEFAULT '' COMMENT '收货人姓名',
        `post_phone` varchar(50) NOT NULL DEFAULT '' COMMENT '收货人电话',
        `post_address` varchar(50) NOT NULL DEFAULT '' COMMENT '收货人地址',
        `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
        PRIMARY KEY (`id`),
        KEY `FK_tb_post` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='收货信息';

/*Table structure for table `tb_shopcart` */

DROP TABLE IF EXISTS `tb_shopcart`;

CREATE TABLE `tb_shopcart` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '购物车主键id',
        `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '用户id',
        `wine_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '红酒id',
        `wine_num` int(11) NOT NULL DEFAULT '0' COMMENT '红酒数量',
        `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
        PRIMARY KEY (`id`),
        KEY `idx_user_id` (`user_id`),
        KEY `idx_wine_id` (`wine_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='购物车表';

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户表主键',
        `user_id` varchar(50) NOT NULL DEFAULT '' COMMENT '用户id',
        `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户姓名',
        `user_sex` varchar(2) NOT NULL DEFAULT '' COMMENT '用户性别',
        `user_city` varchar(8) NOT NULL DEFAULT '' COMMENT '用户所在城市',
        `user_province` varchar(8) NOT NULL DEFAULT '' COMMENT '用户所在省份',
        `user_country` varchar(8) NOT NULL DEFAULT '' COMMENT '用户所在国家',
        `user_headimg` text NOT NULL COMMENT '用户头像url',
        `user_power` varchar(10) NOT NULL DEFAULT '' COMMENT '用户权限',
        `user_fans_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户粉丝数',
        `user_attention_num` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户关注人数',
        `user_level` int(11) NOT NULL DEFAULT '0' COMMENT '用户积分',
        `user_account` double NOT NULL DEFAULT '0' COMMENT '用户账户',
        `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
        PRIMARY KEY (`id`),
        KEY `idx_user_id_user_city` (`user_id`,`user_city`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户信息表';

/*Table structure for table `tb_wine` */

DROP TABLE IF EXISTS `tb_wine`;

CREATE TABLE `tb_wine` (
        `wine_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '红酒id',
        `wine_name` varchar(80) NOT NULL DEFAULT '' COMMENT '红酒名称',
        `wine_price` double NOT NULL DEFAULT '0' COMMENT '红酒单价',
        `wine_img` varchar(200) NOT NULL DEFAULT '' COMMENT '红酒图片',
        `wine_address` varchar(50) NOT NULL DEFAULT '' COMMENT '红酒产地',
        `wine_year` varchar(20) NOT NULL DEFAULT '' COMMENT '红酒年份',
        `wine_class` varchar(20) NOT NULL DEFAULT '' COMMENT '红酒类别',
        `wine_score` double NOT NULL DEFAULT '0' COMMENT '红酒评分',
        `wine_brand` varchar(20) NOT NULL DEFAULT '' COMMENT '红酒品牌',
        `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
        PRIMARY KEY (`wine_id`),
        KEY `idx_wine_id_wine_score` (`wine_id`,`wine_score`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='红酒属性表';

/*Table structure for table `tb_wine_stock` */

DROP TABLE IF EXISTS `tb_wine_stock`;

CREATE TABLE `tb_wine_stock` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '库存表id',
        `wine_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '红酒id',
        `wine_stock` bigint(20) NOT NULL DEFAULT '0' COMMENT '库存数量',
        `wine_real` bigint(20) NOT NULL DEFAULT '0' COMMENT '卖出数量',
        `wine_always_stock` bigint(20) NOT NULL DEFAULT '0' COMMENT '既往库存数',
        `wine_always_real` bigint(20) NOT NULL DEFAULT '0' COMMENT '既往卖出数量',
        `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '操作时间',
        PRIMARY KEY (`id`),
        KEY `idx_wine_id` (`wine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='红酒库存表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
