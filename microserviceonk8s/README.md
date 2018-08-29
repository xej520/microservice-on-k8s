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


