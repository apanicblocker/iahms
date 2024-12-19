-- 插入字典总表[sys_gender-性别]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('性别', 'sys_gender', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('sys_gender', '男', '1', 1, 1),
('sys_gender', '女', '2', 2, 1),
('sys_gender', '未知', '3', 3, 1);

-- 插入字典总表[sys_data_scope-数据范围]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('数据范围', 'sys_data_scope', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('sys_data_scope', '全部数据权限', '1', 1, 1),
('sys_data_scope', '自定数据权限', '2', 2, 1);

-- 插入字典总表[hotel_customer_origin-客户来源]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('客户来源', 'hotel_customer_origin', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_customer_origin', '自来客', '1', 1, 1),
('hotel_customer_origin', '手动录入', '2', 2, 1);

-- 插入字典总表[hotel_customer_source-客户来源方式]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('客户来源方式', 'hotel_customer_source', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_customer_source', '手动录入', '1', 1, 1),
('hotel_customer_source', '渠道订单', '2', 2, 1);

-- 插入字典总表[hotel_certificate_type-证件类型]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('证件类型', 'hotel_certificate_type', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_certificate_type', '身份证', '1', 1, 1),
('hotel_certificate_type', '通行证', '2', 2, 1),
('hotel_certificate_type', '军官证', '3', 3, 1),
('hotel_certificate_type', '护照', '4', 4, 1),
('hotel_certificate_type', '其他', '5', 5, 1);

-- 插入字典总表[hotel_checkin_type-入住类型]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('入住类型', 'hotel_checkin_type', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_checkin_type', '全日房', '1', 1, 1),
('hotel_checkin_type', '钟点房', '2', 2, 1),
('hotel_checkin_type', '免费房', '3', 3, 1),
('hotel_checkin_type', '自用房', '4', 4, 1);

-- 插入字典总表[hotel_checkin_status-入住状态]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('入住状态', 'hotel_checkin_status', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_checkin_status', '已预订', '1', 1, 1),
('hotel_checkin_status', '已入住', '2', 2, 1),
('hotel_checkin_status', '已取消', '3', 3, 1),
('hotel_checkin_status', '已退房', '4', 4, 1),
('hotel_checkin_status', '待付款', '5', 5, 1),
('hotel_checkin_status', '待确认', '6', 6, 1),
('hotel_checkin_status', '已拒绝', '7', 7, 1),
('hotel_checkin_status', '已关闭', '8', 8, 1);

-- 插入字典总表[hotel_room_status-房间状态]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('房间状态', 'hotel_room_status', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_room_status', '空房', '1', 1, 1),
('hotel_room_status', '在住', '2', 2, 1),
('hotel_room_status', '维修', '3', 3, 1),
('hotel_room_status', '保留', '4', 4, 1),
('hotel_room_status', '停用', '5', 5, 1);

-- 插入字典总表[hotel_pay_method-支付方式]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('支付方式', 'hotel_pay_method', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_pay_method', '现金', '1', 1, 1),
('hotel_pay_method', '微信', '2', 2, 1),
('hotel_pay_method', '支付宝', '3', 3, 1),
('hotel_pay_method', '其他', '4', 4, 1);

-- 插入字典总表[hotel_pay_type-支付类型]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('支付类型', 'hotel_pay_type', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_pay_type', '收款', '1', 1, 1),
('hotel_pay_type', '付款', '2', 2, 1),
('hotel_pay_type', '收押金', '3', 3, 1);

-- 插入字典总表[hotel_order_status-订单状态]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('订单状态', 'hotel_order_status', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_order_status', '欠款', '0', 1, 1),
('hotel_order_status', '多收', '1', 2, 1),
('hotel_order_status', '已收齐', '2', 3, 1);

-- 插入字典总表[hotel_checkout_status-结帐状态]
INSERT INTO sys_dict_type(dict_name, dict_type, revision) VALUES('结帐状态', 'hotel_checkout_status', 1);
-- 插入字典明细表
INSERT INTO sys_dict_data(dict_type, dict_label, dict_value, dict_sort, revision) VALUES
('hotel_checkout_status', '欠款', '0', 1, 1),
('hotel_checkout_status', '多收', '1', 2, 1),
('hotel_checkout_status', '已收齐', '2', 3, 1);
