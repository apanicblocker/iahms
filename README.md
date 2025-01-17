<div>TODO：大图标</div>
<h1 align="center">iahms v0.1.1</h1>

<div align="center">
基于SpringBoot+Vue3前后端分离的民宿酒店PMS管理系统

全名：雏莺酒店管理系统\
(Iuvenis Aquilae Hotel Management System)
</div>

## ⚡系统简介⚡
iahms是一款针对中小型民宿酒店行业开发的PMS管理系统，旨在为民宿酒店提供高效、便捷的客户管理、订单处理等一站式解决方案，助力民宿酒店提升运营效率和服务质量。
TODO：细节补充

## 💥在线体验💥
演示地址：TODO

账号密码：admin/asd123

## 🌴项目背景🌴
民宿酒店行业竞争激烈，高效的PMS管理系统对于提升运营效率、 优化客户体验至关重要。本项目旨在开发一套功能完善、易于使用的民宿酒店PMS管理系统，
以满足行业需求。
TODO：细说项目背景（项目编写过程）

## ✨使用✨
### 开发环境
* JDK 21
* MySQL 8.0.40
* Redis 7.2.6
* Node.js 22.6.0

### 技术栈
| 技术          | 说明         | 版本     |
|-------------|------------|--------|
| `springboot`  | Java项目必备框架 | 3.3.4  |
| `springdoc`   | 文档生成       | 2.6.0  |
| `druid`       | 数据库连接池     | 1.2.8  |
| `mybatis`     | ORM框架      | 3.5.13 |
| `vue3`        | 前端框架       | 3.3.4  |
| `elementPlus` | 前端UI框架     | 2.3.5  |
TODO: 补全剩余技术栈内容

### 启动说明
前置准备：下载前后端代码
```shell
git clone https://github.com/apanicblocker/iahms
git clone https://github.com/apanicblocker/iahms-ui
```
安装好MySQL和Redis

后端启动
```text
1. 生成所需的数据库表
   找到后端项目根目录下的sql目录中的iahms_xxxxx.sql脚本文件(取最新的sql文件)。 导入到你新建的数据库中。

2. 在admin模块底下，找到resource目录下的application.yml文件
   配置数据库以及Redis的 地址、端口、账号密码

3. 在根目录执行mvn install

4. 找到iahms-admin模块中的IahmsApplication启动类，直接启动即可

5. 当出现以下字样即为启动成功
(♥◠‿◠)ﾉﾞ  IA启动成功   ლ(´ڡ`ლ)ﾞ
 .-------.       ____     __      
 |  _ _   \      \   \   /  /   
 | ( ' )  |       \  _. /  '      
 |(_ o _) /        _( )_ .'        
 | (_,_).' __  ___(_ o _)'         
 |  |\ \  |  ||   |(_,_)'        
 |  | \ `'   /|   `-'  /          
 |  |  \    /  \      /          
 ''-'   `'-'    `-..-'             
```
前端启动
```shell
1. npm install

2. npm run dev

3. 当出现以下字样时即为启动成功
> iahms-ui@0.0.0 dev
> vite


  VITE v5.4.11  ready in 439 ms
```

## 🙊系统功能🙊
|  | 功能    | 描述                     |
|--|-------|------------------------|
| ◯ | 用户管理  | 用户是系统操作者，该功能主要完成系统用户配置 |
| ⬤ | 系统接口  | 根据业务代码自动生成相关的api接口文档   |
| ⬤ | 客户管理  | 客户信息管理，批量给客户添加标签，设置黑名单 |
| ⬤ | 标签管理  | 自定义文本彩色标签，设置标签分组       |
|  | 房间管理  | 房间信息管理，设置房间状态，设置房间价格   |
|  | 订单管理  | 订单信息管理，设置订单状态，历史订单查询   |
|  | 统计分析  | 统计分析功能，包括客户、房间、订单等数据统计 |
|  | 配置管理  | 配置管理，包括系统配置、短信配置等      |
|  | 日志管理  | 日志管理，包括系统日志、操作日志、登录日志等 |
|  | 安全与监控 | 摄像头管理，实时监控，录像回放        |
TODO：补全系统功能

## 🐯工程结构🐯
- iahms
  - iahms-admin -- 管理后台接口模块
  - iahms-common -- 基础工具模块
  - iahms-framework -- 核心集成模块
  - iahms-generator -- 代码生成模块
  - iahms-hotel -- 酒店管理业务模块
  - iahms-system -- 系统相关业务模块

## 🎅技术文档🎅
TODO：有生之年

## 🌻注意事项🌻
TODO：注意事项
