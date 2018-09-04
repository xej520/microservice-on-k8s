# api-gateway-zuul docker化  
更新pom文件，添加插件依赖:  

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
  ### docker化主要步骤：
   #### 更新application.properties配置文件中的属性设置
       server.port=8080
       
       
       #zuul
       #/course/**这个路径下的请求，全部转发到下面的URL上http://course-service:8081/course/
       zuul.routes.course.path=/course/**
       zuul.routes.course.url=http://course-edge-service:8081/course/
       #zuul.routes.course.url=http://127.0.0.1:8081/course/
       
       zuul.routes.user.path=/user/**
       zuul.routes.user.url=http://user-edge-service:8082/user/
       #zuul.routes.user.url=http://127.0.0.1:8082/user/
   #### 进行打包，上传到本地私有仓库  
     mvn clean install  
     或者 
     利用intellij idea 自带的工具  
   #### 将工程上传到服务器上，（因为开发环境是wind，上传到centos服务器）  
 &ensp;  
 &ensp;  
        
   #### 编写构建镜像脚本build.sh  
       #!/bin/bash
       docker build -t api-gateway-zuul . 
     
   #### 编写Dockerfile  
     FROM openjdk:8-jdk-alpine
     
     MAINTAINER XXX XXX@163.com
     
     COPY target/api-gateway-zuul-1.0-SNAPSHOT.jar /api-gateway-zuul.jar
     
     ENTRYPOINT ["java", "-jar", "/api-gateway-zuul.jar"]
   #### 编写启动脚本start.sh  
     #!/bin/bash
     docker stop api-gateway-zuul
     docker rm api-gateway-zuul
     docker run -it --name api-gateway-zuul api-gateway-zuul:latest 
  &ensp;  
  &ensp;  
  &ensp;  
  &ensp;  
  &ensp;  
  &ensp;  
                