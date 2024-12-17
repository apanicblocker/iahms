DROP database if exists iahms;
CREATE database iahms;

use iahms;

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

DROP TABLE IF EXISTS sys_user;
CREATE TABLE sys_user(
    `user_id` BIGINT AUTO_INCREMENT COMMENT '用户ID' ,
    `username` VARCHAR(100)   COMMENT '用户名;登录用' ,
    `nickname` VARCHAR(100)   COMMENT '昵称' ,
    `password` VARCHAR(255)   COMMENT '密码' ,
    `gender` VARCHAR(100)   COMMENT '性别' ,
    `avatar` VARCHAR(255)   COMMENT '头像地址' ,
    `phone_number` VARCHAR(32)   COMMENT '电话号码' ,
    `login_ip` VARCHAR(128)   COMMENT '最后登录IP' ,
    `login_date` TIMESTAMP   COMMENT '最后登录时间' ,
    `status` BOOLEAN  DEFAULT 1 COMMENT '启用状态' ,
    `del_flag` BOOLEAN  DEFAULT 0 COMMENT '删除标识' ,
    `remark` VARCHAR(500)   COMMENT '备注' ,
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
    `is_default` BOOLEAN  DEFAULT FALSE COMMENT '是否默认' ,
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
    `data_scope` VARCHAR(100)   COMMENT '数据范围' ,
    `status` BOOLEAN  DEFAULT TRUE COMMENT '启用状态' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    `remark` VARCHAR(500)   COMMENT '备注' ,
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
    `origin` VARCHAR(100)   COMMENT '客源' ,
    `name` VARCHAR(100)   COMMENT '姓名;真实姓名' ,
    `id_number` VARCHAR(32)   COMMENT '身份证号' ,
    `phone_number` VARCHAR(32)   COMMENT '电话号码' ,
    `gender` VARCHAR(100)   COMMENT '性别' ,
    `birthday` DATE   COMMENT '生日' ,
    `region` VARCHAR(255)   COMMENT '地区' ,
    `email` VARCHAR(255)   COMMENT '邮箱' ,
    `checkin_times` INT  DEFAULT 0 COMMENT '入住次数' ,
    `total_spent` DECIMAL(10,2)  DEFAULT 0 COMMENT '总消费金额' ,
    `last_spent_time` TIMESTAMP   COMMENT '最近消费时间' ,
    `black_flag` BOOLEAN  DEFAULT FALSE COMMENT '黑名单标识' ,
    `del_flag` BOOLEAN  DEFAULT FALSE COMMENT '删除标识' ,
    `remark` VARCHAR(255)   COMMENT '备注' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (customer_id)
)  COMMENT = '客户';

alter table hotel_customer modify column `checkin_times` INT  DEFAULT 0 COMMENT '入住次数'

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
    `description` VARCHAR(900)   COMMENT '房型描述' ,
    `remark` VARCHAR(500)   COMMENT '备注' ,
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
    `checkin_type` VARCHAR(100)   COMMENT '入住类型' ,
    `checkin_status` VARCHAR(100)   COMMENT '入住状态' ,
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
)  COMMENT = '入住记录';

DROP TABLE IF EXISTS hotel_room_status_record;
CREATE TABLE hotel_room_status_record(
    `room_status_record_id` BIGINT AUTO_INCREMENT COMMENT '房态记录ID' ,
    `room_id` BIGINT   COMMENT '房间ID' ,
    `room_status` VARCHAR(100)   COMMENT '房间状态' ,
    `start_time` TIMESTAMP   COMMENT '开始时间' ,
    `end_time` TIMESTAMP   COMMENT '结束时间' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (room_status_record_id)
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

DROP TABLE IF EXISTS sys_oper_log;
CREATE TABLE sys_oper_log(
    `oper_id` BIGINT AUTO_INCREMENT COMMENT '日志主键' ,
    `title` VARCHAR(100)   COMMENT '模块标题' ,
    `business_type` VARCHAR(100)   COMMENT '业务类型' ,
    `method` VARCHAR(100)   COMMENT '方法名称' ,
    `request_method` VARCHAR(32)   COMMENT '请求方式' ,
    `operator_type` VARCHAR(100)   COMMENT '操作类别' ,
    `oper_name` VARCHAR(100)   COMMENT '操作人员' ,
    `oper_url` VARCHAR(255)   COMMENT '请求URL' ,
    `oper_ip` VARCHAR(128)   COMMENT '主机地址' ,
    `oper_location` VARCHAR(255)   COMMENT '操作地点' ,
    `oper_param` VARCHAR(2000)   COMMENT '请求参数' ,
    `json_result` VARCHAR(2000)   COMMENT '返回参数' ,
    `status` BOOLEAN  DEFAULT TRUE COMMENT '操作状态' ,
    `error_msg` VARCHAR(2000)   COMMENT '错误消息' ,
    `oper_time` TIMESTAMP   COMMENT '操作时间' ,
    `cost_time` BIGINT   COMMENT '消耗时间' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    PRIMARY KEY (oper_id)
)  COMMENT = '操作日志记录';


CREATE INDEX idx_sys_oper_log_bt ON sys_oper_log(business_type);
CREATE INDEX idx_sys_oper_log_s ON sys_oper_log(status);
CREATE INDEX idx_sys_oper_log_ot ON sys_oper_log(oper_time);

DROP TABLE IF EXISTS hotel_order;
CREATE TABLE hotel_order(
    `order_id` BIGINT AUTO_INCREMENT COMMENT '订单ID' ,
    `number` VARCHAR(255)   COMMENT '订单号' ,
    `customer_id` BIGINT   COMMENT '客户ID' ,
    `total_amount` DECIMAL(10,2)   COMMENT '订单总金额' ,
    `order_status` VARCHAR(100)   COMMENT '订单状态' ,
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

DROP TABLE IF EXISTS hotel_pay_record;
CREATE TABLE hotel_pay_record(
    `pay_record_id` BIGINT AUTO_INCREMENT COMMENT '支付记录ID' ,
    `customer_id` BIGINT   COMMENT '客户ID' ,
    `order_id` BIGINT   COMMENT '订单ID' ,
    `pay_type` VARCHAR(100)   COMMENT '支付类型' ,
    `pay_method` VARCHAR(100)   COMMENT '支付方式' ,
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

DROP TABLE IF EXISTS hotel_product;
CREATE TABLE hotel_product(
    `product_id` BIGINT AUTO_INCREMENT COMMENT '商品ID' ,
    `product_type_id` BIGINT   COMMENT '商品类型ID' ,
    `name` VARCHAR(100)   COMMENT '商品名称' ,
    `price` DECIMAL(10,2)   COMMENT '价格' ,
    `inventory` INT   COMMENT '库存' ,
    `description` VARCHAR(900)   COMMENT '商品描述' ,
    `del_flag` BOOLEAN  DEFAULT FALSE COMMENT '删除标识' ,
    `remark` VARCHAR(500)   COMMENT '备注' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (product_id)
)  COMMENT = '商品';

DROP TABLE IF EXISTS hotel_product_type;
CREATE TABLE hotel_product_type(
    `product_type_id` BIGINT AUTO_INCREMENT COMMENT '商品类型ID' ,
    `name` VARCHAR(100)   COMMENT '类型名称' ,
    `description` VARCHAR(900)   COMMENT '类型描述' ,
    `remark` VARCHAR(500)   COMMENT '备注' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (product_type_id)
)  COMMENT = '商品类型';

DROP TABLE IF EXISTS hotel_product_record;
CREATE TABLE hotel_product_record(
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

DROP TABLE IF EXISTS gen_table_column;
CREATE TABLE gen_table_column(
    `column_id` BIGINT AUTO_INCREMENT COMMENT '编号' ,
    `table_id` BIGINT   COMMENT '归属表编号' ,
    `column_name` VARCHAR(200)   COMMENT '列名称' ,
    `column_comment` VARCHAR(500)   COMMENT '列描述' ,
    `column_type` VARCHAR(100)   COMMENT '列类型' ,
    `java_type` VARCHAR(500)   COMMENT 'JAVA类型' ,
    `java_field` VARCHAR(200)   COMMENT 'JAVA字段名' ,
    `is_pk` BOOLEAN   COMMENT '是否主键' ,
    `is_increment` BOOLEAN   COMMENT '是否自增' ,
    `is_required` BOOLEAN   COMMENT '是否必填' ,
    `is_insert` BOOLEAN   COMMENT '是否为插入字段' ,
    `is_edit` BOOLEAN   COMMENT '是否编辑字段' ,
    `is_list` BOOLEAN   COMMENT '是否列表字段' ,
    `is_query` BOOLEAN   COMMENT '是否查询字段' ,
    `query_type` VARCHAR(200)  DEFAULT 'EQ' COMMENT '查询方式' ,
    `html_type` VARCHAR(200)   COMMENT '显示类型' ,
    `dict_type` VARCHAR(200)  DEFAULT '' COMMENT '字典类型' ,
    `sort` INT   COMMENT '排序' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (column_id)
)  COMMENT = '代码生成业务表字段';

DROP TABLE IF EXISTS gen_table;
CREATE TABLE gen_table(
    `table_id` BIGINT AUTO_INCREMENT COMMENT '编号' ,
    `table_name` VARCHAR(200)   COMMENT '表名称' ,
    `table_comment` VARCHAR(500)   COMMENT '表描述' ,
    `sub_table_name` VARCHAR(100)   COMMENT '关联子表的表名' ,
    `sub_table_fk_name` VARCHAR(100)   COMMENT '子表关联的外键名' ,
    `class_name` VARCHAR(100)   COMMENT '实体类名称' ,
    `tpl_category` VARCHAR(200)  DEFAULT 'crud' COMMENT '使用的模板;crud单表操作 tree树表操作' ,
    `package_name` VARCHAR(100)   COMMENT '生成包路径' ,
    `module_name` VARCHAR(30)   COMMENT '生成模块名' ,
    `business_name` VARCHAR(30)   COMMENT '生成业务名' ,
    `function_name` VARCHAR(50)   COMMENT '生成功能名' ,
    `function_author` VARCHAR(50)   COMMENT '生成功能作者' ,
    `gen_type` VARCHAR(100)  DEFAULT 0 COMMENT '生成代码方式;0zip压缩包 1自定义路径' ,
    `gen_path` VARCHAR(200)  DEFAULT '/' COMMENT '生成路径;不填默认项目路径' ,
    `options` VARCHAR(900)   COMMENT '其他生成选项' ,
    `tenant_id` BIGINT   COMMENT '租户号' ,
    `revision` INT   COMMENT '乐观锁' ,
    `create_by` BIGINT   COMMENT '创建人' ,
    `create_time` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间' ,
    `update_by` BIGINT   COMMENT '更新人' ,
    `remark` VARCHAR(500)   COMMENT '备注' ,
    `update_time` TIMESTAMP  ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间' ,
    PRIMARY KEY (table_id)
)  COMMENT = '代码生成业务表';

