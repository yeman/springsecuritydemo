/*
SQLyog  v12.2.6 (64 bit)
MySQL - 8.0.12 : Database - springsecurity
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springsecurity` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `springsecurity`;

/*Table structure for table `t_group` */

DROP TABLE IF EXISTS `t_group`;

CREATE TABLE `t_group` (
  `id` bigint(32) NOT NULL COMMENT '组id',
  `group_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '组名称',
  `parent_group_id` bigint(32) DEFAULT NULL COMMENT '父id',
  `sort_order` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  `description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态启用:Y 停用:N',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组信息';

/*Data for the table `t_group` */

/*Table structure for table `t_group_role` */

DROP TABLE IF EXISTS `t_group_role`;

CREATE TABLE `t_group_role` (
  `id` bigint(32) DEFAULT NULL COMMENT '组权限id',
  `group_id` bigint(32) DEFAULT NULL COMMENT '组id',
  `role_id` bigint(32) DEFAULT NULL COMMENT '权限id',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  KEY `FK_t_group_permission_groupId` (`group_id`),
  KEY `FK_t_group_permission_roleId` (`role_id`),
  CONSTRAINT `FK_t_group_permission_groupId` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`id`),
  CONSTRAINT `FK_t_group_permission_roleId` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='组权限信息';

/*Data for the table `t_group_role` */

/*Table structure for table `t_org` */

DROP TABLE IF EXISTS `t_org`;

CREATE TABLE `t_org` (
  `id` bigint(32) NOT NULL COMMENT '机构id',
  `org_code` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '机构编码',
  `org_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '机构名称',
  `province_code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '省份编码',
  `city_code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '地市编码',
  `county_code` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '区县编码',
  `parent_id` bigint(32) DEFAULT NULL COMMENT '父机构id',
  `description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort_order` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  `enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态启用:Y 停用:N',
  `leader` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '机构负责人',
  `leader_mobie` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '机构负责人手机号',
  `leader_email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '机构负责人邮箱',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='机构信息';

/*Data for the table `t_org` */

insert  into `t_org`(`id`,`org_code`,`org_name`,`province_code`,`city_code`,`county_code`,`parent_id`,`description`,`sort_order`,`enable`,`leader`,`leader_mobie`,`leader_email`,`creator`,`create_time`,`last_modifier`,`last_modify_time`) values 
(1168081024687587330,'0000','景甜科技集团','420000','420100','420104',NULL,'景甜集团2','2','Y',NULL,'1300000000','111@gmail.com',NULL,'2019-09-02 23:35:04','root','2019-09-02 23:35:04'),
(1168155288476372993,'0001','湖北总公司','420000','420100','420104',1168081024687587330,'景甜科技湖北公司','2','Y','周大福','1300000001','111@gmail.com',NULL,NULL,NULL,NULL),
(1168155599270105090,'0002','湖南总公司','430000','430100','420104',1168081024687587330,'景甜科技湖南公司','3','Y','周大福','1300000001','111@gmail.com',NULL,NULL,NULL,NULL),
(1168156100653010946,'0003','北京总公司','110000','110100','110108',1168081024687587330,'景甜科技北京公司','3','Y','周大福','1300000001','111@gmail.com',NULL,NULL,NULL,NULL),
(1168156285340798978,'0004','江西总公司','360000','360100','360102',1168081024687587330,'景甜科技江西公司','3','Y','周大福','1300000001','111@gmail.com',NULL,NULL,NULL,NULL),
(1168156992986992642,'000101','湖北黄石分公司','420000','420200','420202',1168155288476372993,'景甜科技湖北黄石分公司','3','Y','周大福','1300000001','111@gmail.com',NULL,NULL,NULL,NULL),
(1168157250202685441,'000102','湖北武汉分公司','420000','420100','420106',1168155288476372993,'景甜科技湖北武汉分公司','3','Y','周大福','1300000001','111@gmail.com',NULL,NULL,NULL,NULL),
(1168157708019355650,'000101011','市场部','420000','420200','420202',1168156992986992642,'景甜科技湖北黄石分公司市场部','3','Y','周大福','1300000001','111@gmail.com',NULL,NULL,NULL,NULL),
(1168157800319209473,'000101012','营业部','420000','420200','420202',1168156992986992642,'景甜科技湖北黄石分公司营业部','3','Y','周大福','1300000001','111@gmail.com',NULL,NULL,NULL,NULL);

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` bigint(32) NOT NULL COMMENT '权限id',
  `permission_type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '权限类型 模块:module 菜单:menu 按钮:button 其它:other',
  `permission_code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '权限编码',
  `permission_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  `open_type` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '打开方式 页签内:tab 窗口:window',
  `icon` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '图标标识',
  `permission_expression` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '权限表达式',
  `description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '权限描述',
  `parent_id` bigint(32) DEFAULT NULL COMMENT '父权限id',
  `sort_order` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  `enable` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否启用',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

/*Data for the table `t_permission` */

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` bigint(32) NOT NULL COMMENT '角色id主键',
  `role_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `role_code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '角色编码',
  `data_scope` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '数据范围',
  `state` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态启用:Y 停用:N',
  `sort_order` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  `parent_role_id` bigint(32) DEFAULT NULL COMMENT '父角色id',
  `description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`role_name`,`role_code`,`data_scope`,`state`,`sort_order`,`parent_role_id`,`description`,`creator`,`create_time`,`last_modifier`,`last_modify_time`) values 
(1171060727711121409,'管理员','admin','ALL','Y','1',NULL,'管理员','root','2019-09-09 22:00:10','root','2019-09-09 22:00:10'),
(1171061633345241089,'部门领导','DEPT','DEPT','Y','2',NULL,'部门领导','root','2019-09-09 22:03:45','root','2019-09-09 22:03:45'),
(1171062368703201282,'超级用户','DEPT','CURRENTDEPT','Y','3',NULL,'超级用户','root','2019-09-09 22:06:41','root','2019-09-09 22:06:41'),
(1171062726515081217,'普通用户','DEPT','CURRENTDEPT','Y','3',1171062368703201282,'普通用户','root','2019-09-09 22:08:06','root','2019-09-09 22:08:06'),
(1171082363243876353,'普通用户2','DEPT','CUSTOMER','Y','3',1171062726515081217,'普通用户2','root','2019-09-09 23:26:08','root','2019-09-09 23:26:08');

/*Table structure for table `t_role_data_permission` */

DROP TABLE IF EXISTS `t_role_data_permission`;

CREATE TABLE `t_role_data_permission` (
  `id` bigint(32) NOT NULL COMMENT '角色数据权限id',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色id',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构id',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_t_role_data_permission_roleId` (`role_id`),
  KEY `FK_t_role_data_permission_orgId` (`org_id`),
  CONSTRAINT `FK_t_role_data_permission_orgId` FOREIGN KEY (`org_id`) REFERENCES `t_org` (`id`),
  CONSTRAINT `FK_t_role_data_permission_roleId` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色数据权限';

/*Data for the table `t_role_data_permission` */

insert  into `t_role_data_permission`(`id`,`role_id`,`org_id`,`creator`,`create_time`,`last_modifier`,`last_modify_time`) values 
(1171082363596197889,1171082363243876353,1168155288476372993,'root','2019-09-09 23:26:14','root','2019-09-09 23:26:14');

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `id` bigint(32) NOT NULL COMMENT '角色权限id',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色id',
  `permission_id` bigint(32) DEFAULT NULL COMMENT '权限id',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_t_role_permission_permissionId` (`permission_id`),
  KEY `FK_t_role_permission_roleId` (`role_id`),
  CONSTRAINT `FK_t_role_permission_permissionId` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`),
  CONSTRAINT `FK_t_role_permission_roleId` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色权限';

/*Data for the table `t_role_permission` */

/*Table structure for table `t_sys_dic` */

DROP TABLE IF EXISTS `t_sys_dic`;

CREATE TABLE `t_sys_dic` (
  `id` bigint(32) NOT NULL COMMENT '字典id',
  `dic_type` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '字典类型编码',
  `dic_type_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '字典类型名称',
  `enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态启用:Y 停用:N',
  `sort_order` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  `description` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统码表';

/*Data for the table `t_sys_dic` */

/*Table structure for table `t_sys_dic_item` */

DROP TABLE IF EXISTS `t_sys_dic_item`;

CREATE TABLE `t_sys_dic_item` (
  `id` bigint(32) NOT NULL COMMENT 'id',
  `dic_id` bigint(32) DEFAULT NULL COMMENT '字典表id',
  `lable_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '编码',
  `label_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '编码名称',
  `enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态启用:Y 停用:N',
  `sort_order` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  `description` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_unique_dic_item` (`dic_id`,`lable_code`),
  CONSTRAINT `FK_t_sys_dic_item_dicId` FOREIGN KEY (`dic_id`) REFERENCES `t_sys_dic` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统码表字典';

/*Data for the table `t_sys_dic_item` */

/*Table structure for table `t_sys_parameter` */

DROP TABLE IF EXISTS `t_sys_parameter`;

CREATE TABLE `t_sys_parameter` (
  `id` bigint(32) NOT NULL COMMENT 'id',
  `parameter_group` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '参数组',
  `parameter_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '参数名称',
  `parameter_key` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '参数键',
  `parameter_value` varchar(300) COLLATE utf8_bin DEFAULT NULL COMMENT '参数值',
  `system_default` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否系统默认 是:Y 否:N',
  `enable` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '状态启用:Y 停用:N',
  `sort_order` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '排序',
  `description` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_t_sys_parameter_unique` (`parameter_group`,`parameter_name`,`parameter_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='系统参数';

/*Data for the table `t_sys_parameter` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(32) NOT NULL COMMENT '主键',
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `org_id` bigint(32) DEFAULT NULL COMMENT '机构id',
  `nick_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `job_level` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '岗位级别',
  `email` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '性别 男:M 女:F',
  `mobile` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号',
  `is_lock` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '是否锁定 是:Y:否',
  `enable` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '是否启用',
  `avatar` text COLLATE utf8_bin COMMENT '头像',
  `password_expire_date` timestamp NULL DEFAULT NULL COMMENT '密码有效期',
  `account_expire_date` timestamp NULL DEFAULT NULL COMMENT '账户有效期',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_t_user_orgId` (`org_id`),
  CONSTRAINT `FK_t_user_orgId` FOREIGN KEY (`org_id`) REFERENCES `t_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表';

/*Data for the table `t_user` */

/*Table structure for table `t_user_group` */

DROP TABLE IF EXISTS `t_user_group`;

CREATE TABLE `t_user_group` (
  `id` bigint(32) NOT NULL COMMENT '用户组关系id',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户id',
  `group_id` bigint(32) DEFAULT NULL COMMENT '组id',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_t_user_group_userId` (`user_id`),
  KEY `FK_t_user_group_groupId` (`group_id`),
  CONSTRAINT `FK_t_user_group_groupId` FOREIGN KEY (`group_id`) REFERENCES `t_group` (`id`),
  CONSTRAINT `FK_t_user_group_userId` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户组信息';

/*Data for the table `t_user_group` */

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `id` bigint(32) NOT NULL COMMENT '用户角色id',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(32) DEFAULT NULL COMMENT '角色id',
  `creator` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `last_modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `last_modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_t_user_role_roleId` (`role_id`),
  KEY `fk_t_user_role_userId` (`user_id`),
  CONSTRAINT `fk_t_user_role_roleId` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `fk_t_user_role_userId` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色表';

/*Data for the table `t_user_role` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
