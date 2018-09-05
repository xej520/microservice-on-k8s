# quick-start 主要流程  
- 更新各个模块的配置文件，主要修改访问地址之类的，以便进行通信
- 根据模块的开发语言选择合适的基础镜像

- 对各个模块进行mvn clean install  
- 构建各个模块的Dockerfile
- 构建各个模块的镜像  
- 针对各个编写启动脚本  
- 编写docker-compose.yaml 从而确定了各个模块之间的依赖关系 
- 启动公共组件，如mysql，redis，zookeeper服务 
- 利用docker-compose 启动开发的微服务  
   - docker-compose up -d  

# 如何访问呢？ 
## 注册用户信息  
### 先获取验证码  
    POST http://172.16.91.165:9090/user/sendVerifyCode
### 然后在主机上  
    telnet 172.16.91.165 6379 
    get 手机号  
    得到验证码，如   146672 
### 注册：
    POST  http://172.16.91.165:9090/user/register
    
## 查询
将微服务部署到172.16.91.165
1. http://172.16.91.165:9090/user/login  

        {"code":"0","message":"success","token":"a61wpjg7hgsjgca8on4tdyzgb8f1w3rf"} 
       
2. 获取token  
3. http://172.16.91.165:9090/course/courseList?token=a61wpjg7hgsjgca8on4tdyzgb8f1w3rf  
      