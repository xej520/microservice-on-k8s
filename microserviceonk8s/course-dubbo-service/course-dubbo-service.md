# course-dubbo-service docker化  
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
        #dubbo 配置
        spring.dubbo.application.name=course-service
        spring.dubbo.registry.address=zookeeper://${zk.address}:2181
        #spring.dubbo.registry.address=multicast://224.5.6.8:1234
        spring.dubbo.protocol.name=dubbo
        spring.dubbo.protocol.port=20880
        
        spring.dubbo.scan=com.bonc.course.service
        
        #通过端口映射来提供服务，就不需要下面的属性了
        #spring.dubbo.protocol.host=172.16.91.30
        
        #thrift配置
        thrift.user.ip=user-thrift-service
        thrift.user.port=7911
        
        #连接数据库的配置
        #数据源
        spring.datasource.url=jdbc:mysql://${mysql.address}:3306/miscro-server-course
        spring.datasource.username=root
        spring.datasource.password=123456
        #驱动类
        spring.datasource.driver-class-name=com.mysql.jdbc.Driver
   #### 进行打包，上传到本地私有仓库  
     mvn clean install  
     或者 
     利用intellij idea 自带的工具  
   #### 将工程上传到服务器上，（因为开发环境是wind，上传到centos服务器）  
 &ensp;  
 &ensp;  
        
   #### 编写构建镜像脚本build.sh  
       #!/bin/bash
       docker build -t course-dubbo-service . 
     
   #### 编写Dockerfile  
     FROM openjdk:8-jdk-alpine
     
     MAINTAINER XXX XXX@163.com
     
     COPY target/course-dubbo-service-1.0-SNAPSHOT.jar /course-dubbo-service.jar
     
     ENTRYPOINT ["java", "-jar", "/course-dubbo-service.jar"]
   #### 编写启动脚本start.sh  
     #!/bin/bash
     docker stop course-dubbo-service
     docker rm course-dubbo-service
     docker run -it --name course-dubbo-service course-dubbo-service:latest --zk.address=172.16.91.165  --mysql.address=172.16.91.165
  &ensp;  
  &ensp;  
  &ensp;  
  &ensp;  
  &ensp;  
  &ensp;  
                