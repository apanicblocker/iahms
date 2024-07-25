DROP database if exists `iahms2`;
CREATE DATABASE `iahms2`;

USE `iahms2`;

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user(
    `user_id` BIGINT NOT NULL   COMMENT '用户ID' ,
    `customer_id` BIGINT    COMMENT '客户ID' ,
    `username` VARCHAR(90)    COMMENT '用户名' ,
    `nickname` VARCHAR(90)    COMMENT '昵称' ,
    `password` VARCHAR(128)    COMMENT '密码' ,
    `avatar` VARCHAR(255)    COMMENT '头像路径' ,
    `phone_number` VARCHAR(16)    COMMENT '电话号码' ,
    `status` BOOLEAN  DEFAULT TRUE  COMMENT '是否启用' ,
    `del_flag` BOOLEAN  DEFAULT FALSE  COMMENT '删除标志' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `created_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `updated_by` BIGINT    COMMENT '更新人' ,
    `updated_time` TIMESTAMP   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (user_id)
)  COMMENT = '用户';

DROP TABLE IF EXISTS sys_dict_type;
CREATE TABLE sys_dict_type(
    `dict_id` BIGINT AUTO_INCREMENT  COMMENT '字典主键' ,
    `dict_name` VARCHAR(90)    COMMENT '字典名称' ,
    `dict_type` VARCHAR(90)    COMMENT '字典类型' ,
    `status` BOOLEAN  DEFAULT TRUE  COMMENT '启用状态' ,
    `remark` VARCHAR(255)    COMMENT '备注' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `created_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `updated_by` BIGINT    COMMENT '更新人' ,
    `updated_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (dict_id)
)  COMMENT = '字典类型';

DROP TABLE IF EXISTS sys_dict_data;
CREATE TABLE sys_dict_data(
    `dict_code` BIGINT AUTO_INCREMENT  COMMENT '字典编码' ,
    `dict_sort` INT    COMMENT '字典排序' ,
    `dict_label` VARCHAR(90)    COMMENT '字典标签;该数据展示的描述文本' ,
    `dict_value` VARCHAR(90)    COMMENT '字典键值;该数据用于程序处理的实际内容（越简单越好）' ,
    `dict_type` VARCHAR(90)    COMMENT '字典类型' ,
    `is_default` BOOLEAN  DEFAULT FALSE  COMMENT '是否默认' ,
    `status` BOOLEAN  DEFAULT TRUE  COMMENT '启用状态' ,
    `remark` VARCHAR(255)    COMMENT '备注' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `created_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `updated_by` BIGINT    COMMENT '更新人' ,
    `updated_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (dict_code)
)  COMMENT = '字典数据';

DROP TABLE IF EXISTS sys_task_record;
CREATE TABLE sys_task_record(
    `task_id` BIGINT NOT NULL   COMMENT '任务ID' ,
    `task_type_id` TINYINT    COMMENT '任务类型ID' ,
    `task_content` VARCHAR(900)    COMMENT '任务内容' ,
    `execution_time` TIMESTAMP    COMMENT '执行时间' ,
    `status` BOOLEAN  DEFAULT TRUE  COMMENT '是否启用' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (task_id)
)  COMMENT = '任务记录;暂时弃用';

DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role(
    `role_id` BIGINT AUTO_INCREMENT  COMMENT '角色ID' ,
    `role_name` VARCHAR(90)    COMMENT '角色名称' ,
    `role_key` VARCHAR(90)    COMMENT '角色权限字符' ,
    `role_sort` INT    COMMENT '显示顺序' ,
    `data_scope` TINYINT    COMMENT '数据范围;（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）' ,
    `status` BOOLEAN  DEFAULT TRUE  COMMENT '角色状态' ,
    `del_flag` BOOLEAN  DEFAULT FALSE  COMMENT '删除标志' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (role_id)
)  COMMENT = '角色';

DROP TABLE IF EXISTS sys_user_role;
CREATE TABLE sys_user_role(
    `user_id` BIGINT NOT NULL   COMMENT '用户ID' ,
    `role_id` BIGINT NOT NULL   COMMENT '角色ID' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (user_id,role_id)
)  COMMENT = '用户和角色关联';

DROP TABLE IF EXISTS hotel_customer;
CREATE TABLE hotel_customer(
    `customer_id` BIGINT NOT NULL   COMMENT '客户ID' ,
    `channel_id` TINYINT  DEFAULT 0  COMMENT '渠道ID' ,
    `name` VARCHAR(90)    COMMENT '客户姓名' ,
    `gender_id` TINYINT  DEFAULT -1  COMMENT '性别ID' ,
    `birthday` TIMESTAMP    COMMENT '生日' ,
    `phone_number` VARCHAR(16)    COMMENT '手机号' ,
    `checkin_times` INT  DEFAULT 0  COMMENT '入住次数' ,
    `remark` VARCHAR(255)    COMMENT '备注' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (customer_id)
)  COMMENT = '客户';

DROP TABLE IF EXISTS hotel_room;
CREATE TABLE hotel_room(
    `room_id` BIGINT NOT NULL   COMMENT '房间ID' ,
    `room_type_id` BIGINT    COMMENT '房型ID' ,
    `room_number` VARCHAR(16)    COMMENT '房间编号' ,
    `dirty_room` BOOLEAN  DEFAULT FALSE  COMMENT '是否脏房' ,
    `remark` VARCHAR(255)    COMMENT '备注' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (room_id)
)  COMMENT = '房间';

DROP TABLE IF EXISTS hotel_room_type;
CREATE TABLE hotel_room_type(
    `room_type_id` BIGINT NOT NULL   COMMENT '房型ID' ,
    `name` VARCHAR(90)    COMMENT '房型名称' ,
    `nickname` VARCHAR(90)    COMMENT '房型简称' ,
    `price` DECIMAL(10,2)    COMMENT '房型标价' ,
    `quantity` INT  DEFAULT 0  COMMENT '房型数量' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (room_type_id)
)  COMMENT = '房型';

DROP TABLE IF EXISTS hotel_checkin_record;
CREATE TABLE hotel_checkin_record(
    `checkin_record_id` BIGINT NOT NULL   COMMENT '入住记录ID' ,
    `order_id` BIGINT    COMMENT '订单ID' ,
    `room_id` BIGINT    COMMENT '房间ID' ,
    `room_amount` DECIMAL(10,2)    COMMENT '房费' ,
    `checkin_type_id` TINYINT    COMMENT '入住类型ID' ,
    `checkin_state_id` TINYINT    COMMENT '入住状态ID' ,
    `pre_checkin_time` TIMESTAMP    COMMENT '预计入住时间' ,
    `act_checkin_time` TIMESTAMP    COMMENT '实际入住时间' ,
    `pre_checkout_time` TIMESTAMP    COMMENT '预计退房时间' ,
    `act_checkout_time` TIMESTAMP    COMMENT '实际退房时间' ,
    `remark` VARCHAR(255)    COMMENT '备注' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (checkin_record_id)
)  COMMENT = '入住(开房)记录;主要说明开房的房间信息，房间与客户的绑定在[入住记录&客户]表';


CREATE INDEX idx_checkin_record_order_id ON hotel_checkin_record(order_id);

DROP TABLE IF EXISTS hotel_room_state_record;
CREATE TABLE hotel_room_state_record(
    `room_state_record_id` BIGINT NOT NULL   COMMENT '房态记录ID' ,
    `room_id` BIGINT    COMMENT '房间ID' ,
    `room_state_id` TINYINT    COMMENT '房态ID' ,
    `start_time` TIMESTAMP    COMMENT '开始时间' ,
    `end_time` TIMESTAMP    COMMENT '结束时间' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (room_state_record_id)
)  COMMENT = '房态记录';

DROP TABLE IF EXISTS hotel_checkin_customer_link;
CREATE TABLE hotel_checkin_customer_link(
    `checkin_record_id` BIGINT NOT NULL   COMMENT '入住记录ID' ,
    `customer_id` BIGINT NOT NULL   COMMENT '客户ID' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    PRIMARY KEY (checkin_record_id,customer_id)
)  COMMENT = '入住记录&客户';

DROP TABLE IF EXISTS txn_order;
CREATE TABLE txn_order(
    `order_id` BIGINT NOT NULL   COMMENT '订单ID' ,
    `user_id` BIGINT    COMMENT '用户ID' ,
    `order_amount` DECIMAL(10,2)    COMMENT '订单总金额' ,
    `order_state_id` TINYINT  DEFAULT 0  COMMENT '订单状态ID' ,
    `pay_timeout` TIMESTAMP    COMMENT '超时时间' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (order_id)
)  COMMENT = '订单';

DROP TABLE IF EXISTS txn_pay_record;
CREATE TABLE txn_pay_record(
    `pay_record_id` BIGINT NOT NULL   COMMENT '支付记录ID' ,
    `user_id` BIGINT    COMMENT '用户ID' ,
    `order_id` BIGINT    COMMENT '订单ID' ,
    `pay_type_id` TINYINT    COMMENT '支付类型ID' ,
    `pay_method_id` TINYINT    COMMENT '支付方式ID' ,
    `pay_amount` DECIMAL(10,2)    COMMENT '支付金额' ,
    `pay_time` TIMESTAMP    COMMENT '支付时间' ,
    `remark` VARCHAR(255)    COMMENT '备注' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (pay_record_id)
)  COMMENT = '支付记录';

DROP TABLE IF EXISTS frs_face_record;
CREATE TABLE frs_face_record(
    `face_id` BIGINT NOT NULL   COMMENT '人脸数据ID' ,
    `user_id` BIGINT    COMMENT '用户ID' ,
    `face_features` BLOB(128)    COMMENT '人脸特征' ,
    `source` VARCHAR(255)    COMMENT '数据来源' ,
    `capture_time` TIMESTAMP    COMMENT '捕获时间' ,
    `confidence_score` DECIMAL(5,2)    COMMENT '置信度分数' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (face_id)
)  COMMENT = '人脸特征';

DROP TABLE IF EXISTS shop_product;
CREATE TABLE shop_product(
    `product_id` BIGINT NOT NULL   COMMENT '商品ID' ,
    `product_type_id` BIGINT    COMMENT '商品类型ID' ,
    `product_name` VARCHAR(90)    COMMENT '商品名称' ,
    `inventory` INT    COMMENT '库存' ,
    `product_desc` VARCHAR(255)    COMMENT '商品描述' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (product_id)
)  COMMENT = '商品';

DROP TABLE IF EXISTS shop_product_type;
CREATE TABLE shop_product_type(
    `product_type_id` BIGINT NOT NULL   COMMENT '商品类型ID' ,
    `type_name` VARCHAR(90)    COMMENT '类型名称' ,
    `type_desc` VARCHAR(255)    COMMENT '类型描述' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (product_type_id)
)  COMMENT = '商品类型';

DROP TABLE IF EXISTS shop_product_record;
CREATE TABLE shop_product_record(
    `shop_record_id` BIGINT NOT NULL   COMMENT '购物记录ID' ,
    `order_id` BIGINT    COMMENT '订单ID' ,
    `product_id` BIGINT    COMMENT '商品ID' ,
    `product_number` INT    COMMENT '数量' ,
    `tenant_id` BIGINT    COMMENT '租户号' ,
    `revision` INT    COMMENT '乐观锁' ,
    `created_by` BIGINT    COMMENT '创建人' ,
    `create_time` DATETIME  DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间' ,
    `update_by` BIGINT    COMMENT '更新人' ,
    `update_time` DATETIME   ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (shop_record_id)
)  COMMENT = '购物记录';


CREATE INDEX idx_product_record_order_id ON shop_product_record(order_id);







