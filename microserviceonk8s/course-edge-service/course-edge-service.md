# course-edge-service docker化  
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
       server.port=8081
       
       #dubbo config
       spring.dubbo.application.name=course-service
       spring.dubbo.registry.address=zookeeper://${zk.address}:2181
       #spring.dubbo.registry.address=multicast://224.5.6.8:1234
       spring.dubbo.scan=com.bonc.course
       
       user.edge.service.addr=user-edge-service:8082
   #### 进行打包，上传到本地私有仓库  
     mvn clean install  
     或者 
     利用intellij idea 自带的工具  
   #### 将工程上传到服务器上，（因为开发环境是wind，上传到centos服务器）  
 &ensp;  
 &ensp;  
        
   #### 编写构建镜像脚本build.sh  
       #!/bin/bash
       docker build -t course-edge-service . 
     
   #### 编写Dockerfile  
     FROM openjdk:8-jdk-alpine
     
     MAINTAINER XXX XXX@163.com
     
     COPY target/course-edge-service-1.0-SNAPSHOT.jar /course-edge-service.jar
     
     ENTRYPOINT ["java", "-jar", "/course-edge-service.jar"]
   #### 编写启动脚本start.sh  
     #!/bin/bash
     docker stop course-edge-service
     docker rm course-edge-service
     docker run -it --name course-edge-service course-edge-service:latest --zk.address=172.16.91.165
  &ensp;  
  &ensp;  
  &ensp;  
  &ensp;  
  &ensp;  
  &ensp;  
                