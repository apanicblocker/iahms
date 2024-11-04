DROP database if exists iahms;
CREATE database iahms;

use iahms;

DROP TABLE IF EXISTS txn_order;
CREATE TABLE txn_order(
    `order_id` BIGINT AUTO_INCREMENT COMMENT '订单ID' ,
    `number` VARCHAR(255)   COMMENT '订单号' ,
    `customer_id` BIGINT   COMMENT '客户ID' ,
    `total_amount` DECIMAL(10,2)   COMMENT '订单总金额' ,
    `order_status` TINYINT   COMMENT '订单状态' ,
    `del_flag` BOOLEAN  DEFAULT FALSE COMMENT '删除标识' ,
    `remark` VARCHAR(255)   COMMENT '备注' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (order_id)
)  COMMENT = '订单';

DROP TABLE IF EXISTS txn_pay_record;
CREATE TABLE txn_pay_record(
    `pay_record_id` BIGINT AUTO_INCREMENT COMMENT '支付记录ID' ,
    `customer_id` BIGINT   COMMENT '客户ID' ,
    `order_id` BIGINT   COMMENT '订单ID' ,
    `pay_type` TINYINT   COMMENT '支付类型' ,
    `pay_method` TINYINT   COMMENT '支付方式' ,
    `amount` DECIMAL(10,2)   COMMENT '支付金额' ,
    `pay_time` TIMESTAMP   COMMENT '支付时间' ,
    `remark` VARCHAR(255)   COMMENT '备注' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (pay_record_id)
)  COMMENT = '支付记录';

DROP TABLE IF EXISTS frs_face_record;
CREATE TABLE frs_face_record(
    `face_record_id` BIGINT AUTO_INCREMENT COMMENT '人脸数据ID' ,
    `user_id` BIGINT   COMMENT '用户ID' ,
    `data_path` VARCHAR(255)   COMMENT '人脸数据路径' ,
    `source` VARCHAR(255)   COMMENT '数据来源' ,
    `capture_time` TIMESTAMP   COMMENT '捕获时间' ,
    `confidence_score` DECIMAL(5,2)   COMMENT '置信度分数' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (face_record_id)
)  COMMENT = '人脸特征';

DROP TABLE IF EXISTS shop_product;
CREATE TABLE shop_product(
    `product_id` BIGINT AUTO_INCREMENT COMMENT '商品ID' ,
    `product_type_id` BIGINT   COMMENT '商品类型ID' ,
    `name` VARCHAR(100)   COMMENT '商品名称' ,
    `inventory` INT   COMMENT '库存' ,
    `description` VARCHAR(900)   COMMENT '商品描述' ,
    `del_flag` BOOLEAN  DEFAULT FALSE COMMENT '删除标识' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (product_id)
)  COMMENT = '商品';

DROP TABLE IF EXISTS shop_product_type;
CREATE TABLE shop_product_type(
    `product_type_id` BIGINT AUTO_INCREMENT COMMENT '商品类型ID' ,
    `name` VARCHAR(100)   COMMENT '类型名称' ,
    `description` VARCHAR(900)   COMMENT '类型描述' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (product_type_id)
)  COMMENT = '商品类型';

DROP TABLE IF EXISTS shop_product_record;
CREATE TABLE shop_product_record(
    `product_record_id` BIGINT AUTO_INCREMENT COMMENT '购物记录ID' ,
    `order_id` BIGINT   COMMENT '订单ID' ,
    `product_id` BIGINT   COMMENT '商品ID' ,
    `quantity` INT   COMMENT '商品数量' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (product_record_id)
)  COMMENT = '购物记录';

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user(
    `user_id` BIGINT AUTO_INCREMENT COMMENT '用户ID' ,
    `username` VARCHAR(100)   COMMENT '用户名;登录用' ,
    `nickname` VARCHAR(100)   COMMENT '昵称' ,
    `password` VARCHAR(255)   COMMENT '密码' ,
    `gender` TINYINT   COMMENT '性别' ,
    `avatar` VARCHAR(255)   COMMENT '头像' ,
    `phone_number` VARCHAR(32)   COMMENT '电话号码' ,
    `status` BOOLEAN  DEFAULT TRUE COMMENT '启用状态' ,
    `del_flag` BOOLEAN  DEFAULT FALSE COMMENT '删除标识' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (user_id)
)  COMMENT = '用户';

DROP TABLE IF EXISTS sys_dict_type;
CREATE TABLE sys_dict_type(
    `dict_id` BIGINT AUTO_INCREMENT COMMENT '字典主键' ,
    `dict_name` VARCHAR(100)   COMMENT '字典名称' ,
    `dict_type` VARCHAR(100)   COMMENT '字典类型' ,
    `status` BOOLEAN  DEFAULT TRUE COMMENT '启用状态' ,
    `remark` VARCHAR(255)   COMMENT '备注' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (dict_id)
)  COMMENT = '字典类型';

DROP TABLE IF EXISTS sys_dict_data;
CREATE TABLE sys_dict_data(
    `dict_code` BIGINT AUTO_INCREMENT COMMENT '字典编码' ,
    `dict_sort` INT   COMMENT '字典排序' ,
    `dict_label` VARCHAR(100)   COMMENT '字典标签;给用户看的描述文本' ,
    `dict_value` VARCHAR(100)   COMMENT '字典键值;给程序处理的实际数据（越简单越好）' ,
    `dict_type` VARCHAR(100)   COMMENT '字典类型' ,
    `status` BOOLEAN  DEFAULT TRUE COMMENT '启用状态' ,
    `remark` VARCHAR(255)   COMMENT '备注' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (dict_code)
)  COMMENT = '字典数据';

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role(
    `role_id` BIGINT AUTO_INCREMENT COMMENT '角色ID' ,
    `role_name` VARCHAR(100)   COMMENT '角色名称' ,
    `role_key` VARCHAR(100)   COMMENT '权限字符' ,
    `sort_number` INT   COMMENT '排序数字' ,
    `data_scope` TINYINT   COMMENT '数据范围' ,
    `status` BOOLEAN  DEFAULT TRUE COMMENT '启用状态' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (role_id)
)  COMMENT = '角色';

DROP TABLE IF EXISTS sys_user_role_link;
CREATE TABLE sys_user_role_link(
    `user_id` BIGINT NOT NULL  COMMENT '用户ID' ,
    `role_id` BIGINT NOT NULL  COMMENT '角色ID' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (user_id,role_id)
)  COMMENT = '角色&用户';

DROP TABLE IF EXISTS hotel_customer;
CREATE TABLE hotel_customer(
    `customer_id` BIGINT AUTO_INCREMENT COMMENT '客户ID' ,
    `origin` TINYINT   COMMENT '客源' ,
    `name` VARCHAR(100)   COMMENT '姓名;真实姓名' ,
    `id_number` VARCHAR(32)   COMMENT '身份证号' ,
    `phone_number` VARCHAR(32)   COMMENT '电话号码' ,
    `gender` TINYINT   COMMENT '性别' ,
    `birthday` TIMESTAMP   COMMENT '生日' ,
    `region` VARCHAR(255)   COMMENT '地区' ,
    `email` VARCHAR(255)   COMMENT '邮箱' ,
    `checkin_times` INT   COMMENT '入住次数' ,
    `del_flag` BOOLEAN  DEFAULT FALSE COMMENT '删除标识' ,
    `remark` VARCHAR(255)   COMMENT '备注' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (customer_id)
)  COMMENT = '客户;该表是 user 表的一个属性扩展表';

DROP TABLE IF EXISTS hotel_room;
CREATE TABLE hotel_room(
    `room_id` BIGINT AUTO_INCREMENT COMMENT '房间ID' ,
    `room_type_id` BIGINT   COMMENT '房型ID' ,
    `price` DECIMAL(10,2)   COMMENT '标准房费' ,
    `number` VARCHAR(32)   COMMENT '房间编号' ,
    `dirty_flag` BOOLEAN   COMMENT '是否脏房' ,
    `del_flag` BOOLEAN  DEFAULT FALSE COMMENT '删除标识' ,
    `remark` VARCHAR(255)   COMMENT '备注' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (room_id)
)  COMMENT = '房间';

DROP TABLE IF EXISTS hotel_room_type;
CREATE TABLE hotel_room_type(
    `room_type_id` BIGINT AUTO_INCREMENT COMMENT '房型ID' ,
    `room_name` VARCHAR(100)   COMMENT '房型名称' ,
    `nick_name` VARCHAR(100)   COMMENT '房型别名' ,
    `price` DECIMAL(10,2)   COMMENT '房型标价' ,
    `quantity` INT   COMMENT '房型数量' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (room_type_id)
)  COMMENT = '房型';

DROP TABLE IF EXISTS hotel_checkin_record;
CREATE TABLE hotel_checkin_record(
    `checkin_record_id` BIGINT AUTO_INCREMENT COMMENT '入住记录ID' ,
    `order_id` BIGINT   COMMENT '订单ID' ,
    `room_id` BIGINT   COMMENT '房间ID' ,
    `customer_id` BIGINT   COMMENT '客户ID;主要的开房客人（出问题先找他）' ,
    `room_amount` DECIMAL(10,2)   COMMENT '房费' ,
    `checkin_type` TINYINT   COMMENT '入住类型' ,
    `checkin_status` TINYINT   COMMENT '入住状态' ,
    `pre_checkin_time` TIMESTAMP   COMMENT '预计入住时间' ,
    `act_checkin_time` TIMESTAMP   COMMENT '实际入住时间' ,
    `pre_checkout_time` TIMESTAMP   COMMENT '预计退房时间' ,
    `act_checkout_time` TIMESTAMP   COMMENT '实际退房时间' ,
    `remark` VARCHAR(255)   COMMENT '备注' ,
    `del_flag` BOOLEAN  DEFAULT FALSE COMMENT '删除标识' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (checkin_record_id)
)  COMMENT = '入住记录（开房记录）';

DROP TABLE IF EXISTS hotel_room_state_record;
CREATE TABLE hotel_room_state_record(
    `room_state_record_id` BIGINT AUTO_INCREMENT COMMENT '房态记录ID' ,
    `room_id` BIGINT   COMMENT '房间ID' ,
    `room_status` TINYINT   COMMENT '房间状态' ,
    `start_time` TIMESTAMP   COMMENT '开始时间' ,
    `end_time` TIMESTAMP   COMMENT '结束时间' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (room_state_record_id)
)  COMMENT = '房态记录';

DROP TABLE IF EXISTS hotel_checkin_customer_link;
CREATE TABLE hotel_checkin_customer_link(
    `customer_id` BIGINT NOT NULL  COMMENT '客户ID' ,
    `checkin_record_id` BIGINT NOT NULL  COMMENT '入住记录ID' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (customer_id,checkin_record_id)
)  COMMENT = '入住记录&客户';

DROP TABLE IF EXISTS hotel_tag;
CREATE TABLE hotel_tag(
    `tag_id` BIGINT AUTO_INCREMENT COMMENT '标签ID' ,
    `label` VARCHAR(100)   COMMENT '标签文本' ,
    `color` VARCHAR(100)   COMMENT '标签颜色' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (tag_id)
)  COMMENT = '标签';

DROP TABLE IF EXISTS hotel_tag_customer_link;
CREATE TABLE hotel_tag_customer_link(
    `tag_id` BIGINT NOT NULL  COMMENT '标签ID' ,
    `customer_id` BIGINT NOT NULL  COMMENT '客户ID' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (tag_id,customer_id)
)  COMMENT = '标签&客户';

