### 本项目的目的是： 
1. 了解微服务
2. 了解rpc请求，如thrift，dubbo 以及 restful请求方式
3. 重点关注如何将微服务docker化
4. 重点关注如何将服务编排化
# 用户服务
- 用户登录
- 用户注册
- 用户基本信息查询 
- 无状态(无session)
- 单点登录

# 课程服务
- 登录验证 
- 课程的curd

# 信息服务(作用)
- 发送邮件
- 发送短信 

# 用户edgeservice 

# 课程edgeservcie  

# API GATEWAY  


# 流程图 
![](https://note.youdao.com/yws/public/resource/9c8584c6ec980aee585665c38a65bf9d/xmlnote/63F2E6EDCCC4400AA5484C6CE90CD6F7/20061)

## 说明  
- zookeeper 是用来发现服务，注册服务的  

# 安装部署thrift 
## 下载thrift  
    wget http://mirrors.hust.edu.cn/apache/thrift/0.11.0/thrift-0.11.0.tar.gz  
## 解压thrift  
    tar -zxvf thrift-0.11.0.tar.gz  
## 将解压后的文件，移动到合适的目录下  
    在官方下载的tar包中已经有了configure脚本，  
    如果是首次下载的源码文件没有configure脚本，需要使用bootstrap.sh自动生成。
## 进入thrift文件里 
### 安装依赖  
    yum install -y install automake bison flex gcc gcc-c++ git libboost1.55 libevent-dev libssl-dev libtool make pkg-config

### 进行配置（两种方式吧）
- ./configure  (使用默认配置)
- ./configure --with-cpp --with-boost --with-python --without-csharp --with-java --without-erlang --without-perl --with-php --without-php_extension --without-ruby --without-haskell  --without-go

### 编译 安装
    make && make install   
### 测试是否安装成功？ 
    #thrift
    Usage: thrift [options] file
       
    Use thrift -help for a list of options 
        
## 安装过程报的问题 
### 缺少automake-1.15 动态库？  
    wget ftp://mirrors.ustc.edu.cn/gnu/automake/automake-1.15.tar.xz
    tar xf automake-1.15.tar.xz 
    cd automake-1.15/
    ./configure && make && make install  

## thrift例子  
### 编写一个thrift文件 demo.thrift
    namespace  java com.xej.thrift.demo
    namespace  py thrift.demo
    namespace  go com.xej.thrift.demo
    
    service DemoService{
    	void sayHello(1:string name);
    }

### 生成java，py, go  类型的文件
    thrift --gen java demo.thrift 
    thrift --gen py demo.thrift
    thrift --gen go demo.thrift
如果有相应的文件夹生成，说明没问题   

## 问题？
### 其他系统如何使用我们的单点登录呢，或者对接？要使得很方便才可以的  
    一般使用客户端jar包的形式
因此需要单独创建一个模块，用来给其他系统进行单点登录的jar  

# 数据库
## miscro-server-user 数据库  (下面是对应的SQL文件miscro-server-user.sql)
   ```$xslt
/*
Navicat MySQL Data Transfer

Source Server         : xej-mysql
Source Server Version : 50718
Source Host           : 172.16.91.165:3306
Source Database       : miscro-server-user

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-09-03 19:16:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pe_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `pe_teacher`;
CREATE TABLE `pe_teacher` (
  `user_id` int(11) NOT NULL,
  `intro` varchar(64) NOT NULL,
  `stars` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pe_teacher
-- ----------------------------
INSERT INTO `pe_teacher` VALUES ('1', '北京大学', '5');
INSERT INTO `pe_teacher` VALUES ('2', '清华大学', '6');

-- ----------------------------
-- Table structure for `pe_user`
-- ----------------------------
DROP TABLE IF EXISTS `pe_user`;
CREATE TABLE `pe_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `real_name` varchar(32) NOT NULL,
  `mobile` varchar(32) NOT NULL,
  `email` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pe_user
-- ----------------------------
INSERT INTO `pe_user` VALUES ('1', 'xiaoqiang', 'e10adc3949ba59abbe56e057f20f883e', 'yyyy', '12345678', 'xxxx@163.com');
INSERT INTO `pe_user` VALUES ('2', 'xiaoqiang2', 'e10adc3949ba59abbe56e057f20f883e', 'realNamdxxx', '12345678', 'xxxx@163.com');
INSERT INTO `pe_user` VALUES ('3', 'xiaoqiang3', 'e10adc3949ba59abbe56e057f20f883e', 'realName-->xxx', '12345678', 'xxxx@163.com');

```

## miscro-server-course 数据库  (下面是对应的SQL文件miscro-server-course.sql)  
```$xslt
/*
Navicat MySQL Data Transfer

Source Server         : xej-mysql
Source Server Version : 50718
Source Host           : 172.16.91.165:3306
Source Database       : miscro-server-course

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-09-03 19:20:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `pe_course`
-- ----------------------------
DROP TABLE IF EXISTS `pe_course`;
CREATE TABLE `pe_course` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(64) NOT NULL,
  `description` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pe_course
-- ----------------------------
INSERT INTO `pe_course` VALUES ('1', '大学英语四级', '非常好的免费课程');

-- ----------------------------
-- Table structure for `pr_user_course`
-- ----------------------------
DROP TABLE IF EXISTS `pr_user_course`;
CREATE TABLE `pr_user_course` (
  `user_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pr_user_course
-- ----------------------------
INSERT INTO `pr_user_course` VALUES ('2', '1');

```  




