-----------------------用户表------------------------------------------
drop table if exists `sys_user`;
create table `sys_user`(
	`id` bigint not null auto_increment,
    `username` varchar(64) default null,
    `password` varchar(64) default null,
    
    
    `avatar` varchar(255) default null,
    `email` varchar(64) default null,
    `city` varchar(64) default null,
    `status` int not null,
    `gmt_last_login` datetime default null,
    
    
    `gmt_created` datetime default null,
    `gmt_update`  datetime default null,
  
    primary key(`id`),
    unique key `UK_USERNAME`(`username`) using btree
)engine=innodb default charset=utf8;


INSERT INTO `sys_user` VALUES ('1', 
                               'admin', 
                               '123456', 
                               'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg', 
                               '123@qq.com', 
                               '广州', 
                                '1',
                               '2021-01-16 16:57:32', 
                               '2020-12-30 08:38:37', 
                               '2021-01-12 22:13:53'
                              );
INSERT INTO `sys_user` VALUES (
    		                   '2', 
                               'test', 
                               '123456',
                               'https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg',
                               'test@qq.com', 
                               '北京',
    							'1',
   								 '2021-01-30 08:55:57',
                               '2021-01-30 08:20:22', 
                               '2021-01-30 08:55:57'
                              );


-----------------------角色表------------------------------------------
drop table if exists `sys_role`;
create table `sys_role`(
	`id` bigint not null auto_increment,
    `name` varchar(64) default null comment '角色名',
    `code` varchar(64) default null comment '角色编码',
    `remark` varchar(128) default null comment '备注信息',
    `status` int not null,
    
    `gmt_created` datetime default null,
    `gmt_updated` datetime default null,
    primary key(`id`)
)engine=innodb default charset=utf8;

INSERT INTO `sys_role` VALUES (
    '3',
    '普通用户',
    'normal',
    '只有基本查看功能',
     '1',
    '2021-01-04 10:09:14', 
    '2021-01-30 08:19:52'

);
INSERT INTO `sys_role` VALUES (
    '6',
    '超级管理员', 
    'admin',
    '系统默认最高权限，不可以编辑和任意修改',
     '1',
    '2021-01-16 13:29:03',
    '2021-01-17 15:50:45'
);
-----------------------用户角色表------------------------------------------
drop table if exists `sys_user_role`;
create table `sys_user_role`(
	`id` bigint not null auto_increment,
    `user_id` bigint not null ,
    `role_id` bigint not null,
    primary key(`id`)
)engine=innodb default charset=utf8;
--用户id=1 有admin和normal角色
--用户id=2 有normal角色
INSERT INTO `sys_user_role` VALUES ('1', '1', '6');
INSERT INTO `sys_user_role` VALUES ('2', '1', '3');
INSERT INTO `sys_user_role` VALUES ('3', '2', '3');


-----------------------系统菜单表------------------------------------------
drop table if exists `sys_menu`;
--给路由用，tab组件component用，菜单menu管理用
create table `sys_menu`(
   `id` bigint not null auto_increment,
    `parent_id` bigint default null comment '父菜单ID，一级菜单为0',
    `name` varchar(64) default null,
    
    `path` varchar(255) default null  comment '路由路径 /sys/roles',
    `perms` varchar(255) default null comment '授权吗如 sys:user:list',
    `component` varchar(255) default null comment 'import 拼接的组件名，2级菜单才有',
    `type` int not null comment '类型 0目录 1菜单  2按钮',
    `status` int not null,
    
    `icon` varchar(64) default null comment '一级二级图标',
    `order_num` int default null comment '排序？',
    
    
    `gmt_created` datetime default null,
    `gmt_updated` datetime default null,
    primary key(`id`)
)engine=innodb default charset=utf8;

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`) 
	VALUES ('1', '0', '系统管理', '', 'sys:manage', '', '0', 'el-icon-s-operation', '1', '2021-01-15 18:58:18', '2021-01-15 18:58:20', '1');


INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`) 
	VALUES ('2', '1', '用户管理', '/sys/users', 'sys:user:list', 'sys/User', '1', 'el-icon-s-custom', '1', '2021-01-15 19:03:45', '2021-01-15 19:03:48', '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('3', '1', '角色管理', '/sys/roles', 'sys:role:list', 'sys/Role', '1', 'el-icon-rank', '2', '2021-01-15 19:03:45', '2021-01-15 19:03:48', '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`) 
	VALUES ('4', '1', '菜单管理', '/sys/menus', 'sys:menu:list', 'sys/Menu', '1', 'el-icon-menu', '3', '2021-01-15 19:03:45', '2021-01-15 19:03:48', '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('5', '0', '系统工具', '', 'sys:tools', null, '0', 'el-icon-s-tools', '2', '2021-01-15 19:06:11', null, '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`) 
	VALUES ('6', '5', '数字字典', '/sys/dicts', 'sys:dict:list', 'sys/Dict', '1', 'el-icon-s-order', '1', '2021-01-15 19:07:18', '2021-01-18 16:32:13', '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('7', '3', '添加角色', '', 'sys:role:save', '', '2', '', '1', '2021-01-15 23:02:25', '2021-01-17 21:53:14', '0');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('9', '2', '添加用户', null, 'sys:user:save', null, '2', null, '1', '2021-01-17 21:48:32', null, '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('10', '2', '修改用户', null, 'sys:user:update', null, '2', null, '2', '2021-01-17 21:49:03', '2021-01-17 21:53:04', '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('11', '2', '删除用户', null, 'sys:user:delete', null, '2', null, '3', '2021-01-17 21:49:21', null, '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`) 
	VALUES ('12', '2', '分配角色', null, 'sys:user:role', null, '2', null, '4', '2021-01-17 21:49:58', null, '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('13', '2', '重置密码', null, 'sys:user:repass', null, '2', null, '5', '2021-01-17 21:50:36', null, '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`) 
	VALUES ('14', '3', '修改角色', null, 'sys:role:update', null, '2', null, '2', '2021-01-17 21:51:14', null, '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`) 
	VALUES ('15', '3', '删除角色', null, 'sys:role:delete', null, '2', null, '3', '2021-01-17 21:51:39', null, '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('16', '3', '分配权限', null, 'sys:role:perm', null, '2', null, '5', '2021-01-17 21:52:02', null, '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('17', '4', '添加菜单', null, 'sys:menu:save', null, '2', null, '1', '2021-01-17 21:53:53', '2021-01-17 21:55:28', '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`)
	VALUES ('18', '4', '修改菜单', null, 'sys:menu:update', null, '2', null, '2', '2021-01-17 21:56:12', null, '1');

INSERT INTO `sys_menu`(`id`, `parent_id` ,`name`,`path`,`perms`, `component`,`type`,`icon`, `order_num`, `gmt_created`, `gmt_updated`,`status`) 
	VALUES ('19', '4', '删除菜单', null, 'sys:menu:delete', null, '2', null, '3', '2021-01-17 21:56:36', null, '1');



-----------------------角色菜单表------------------------------------------
drop table if exists `sys_role_menu`;
create table `sys_role_menu`(
    `id` bigint not null auto_increment,
    `role_id` bigint not null,
    `menu_id` bigint not null,
    primary key(`id`)
)engine=Innodb default charset=utf8;

-- amdin有所有
INSERT INTO `sys_role_menu` VALUES ('60', '6', '1');
INSERT INTO `sys_role_menu` VALUES ('61', '6', '2');
INSERT INTO `sys_role_menu` VALUES ('67', '6', '3');
INSERT INTO `sys_role_menu` VALUES ('72', '6', '4');
INSERT INTO `sys_role_menu` VALUES ('76', '6', '5');
INSERT INTO `sys_role_menu` VALUES ('77', '6', '6');
INSERT INTO `sys_role_menu` VALUES ('68', '6', '7');
INSERT INTO `sys_role_menu` VALUES ('62', '6', '9');
INSERT INTO `sys_role_menu` VALUES ('63', '6', '10');
INSERT INTO `sys_role_menu` VALUES ('64', '6', '11');
INSERT INTO `sys_role_menu` VALUES ('65', '6', '12');
INSERT INTO `sys_role_menu` VALUES ('66', '6', '13');
INSERT INTO `sys_role_menu` VALUES ('69', '6', '14');
INSERT INTO `sys_role_menu` VALUES ('70', '6', '15');
INSERT INTO `sys_role_menu` VALUES ('71', '6', '16');
INSERT INTO `sys_role_menu` VALUES ('73', '6', '17');
INSERT INTO `sys_role_menu` VALUES ('74', '6', '18');
INSERT INTO `sys_role_menu` VALUES ('75', '6', '19');
INSERT INTO `sys_role_menu` VALUES ('96', '3', '1');
INSERT INTO `sys_role_menu` VALUES ('97', '3', '2');
INSERT INTO `sys_role_menu` VALUES ('98', '3', '3');
INSERT INTO `sys_role_menu` VALUES ('99', '3', '4');
INSERT INTO `sys_role_menu` VALUES ('100', '3', '5');
INSERT INTO `sys_role_menu` VALUES ('101', '3', '6');