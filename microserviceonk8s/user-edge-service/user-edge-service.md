# user-edge-service docker化 
### 更新pom文件，添加插件依赖  
    
        <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                        <executions>
                            <execution>
                                <goals>
                                    <goal>repackage</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
  ### 主要步骤：
  #### 更新application.properties配置文件中的属性设置
       server.name=user-edge-service
       server.port=8082
       
       #设置名字user-thrift-service，dockers启动时，添加link参数，
       #不同模块之间的通信，就可以通过名字来进行通信了
       #thrift.user.ip=127.0.0.1
       thrift.user.ip=user-thrift-service
       thrift.user.port=7911
       
       thrift.message.ip=message-thrift-service
       thrift.message.port=9090
       
       #redis的配置
       #spring.redis.host=172.16.91.165
       #redis的地址，是变化的，肯不原因是，我们的微服务里并没有开发redis服务，是
       #微服务外部的，因此，设置成变量的形式
       spring.redis.host=${redis.address}
       spring.redis.port=6379
       spring.redis.timeout=3000
  #### 进行打包，上传到本地私有仓库  
    mvn clean install  
    或者 
    利用intellij idea 自带的工具  
  #### 将工程上传到服务器上，（因为开发环境是wind，上传到centos服务器）  
&ensp;  
&ensp;  
       
 
  #### 编写Dockerfile  
    FROM openjdk:8-jdk-alpine
    
    MAINTAINER XXX XXX@163.com
    
    COPY target/user-edge-service-1.0-SNAPSHOT.jar /user-edge-service.jar
    
    ENTRYPOINT ["java", "-jar", "/user-edge-service.jar"]
  #### 编写构建镜像脚本build.sh  
     #!/bin/bash
     docker build -t user-edge-service . 
     
  
  #### 编写启动脚本start.sh  
    #!/bin/bash
    docker stop user-edge-service
    docker rm user-edge-service
    docker run -it --name user-edge-service user-edge-service:latest --address.address=172.16.91.165
 &ensp;  
 &ensp;  
 &ensp;  
 &ensp;  
 &ensp;  
 &ensp;  
                         