## user-thrift-service模块docker步骤：  
### 准备事项？  
 - 先将pom里依赖的模块，mvn install 到本地mvn仓库里

### 开始docker化 步骤：  
- 先将appliaction.properties里数据库的访问地址，由常量改成变量，如下图所示:  
    172.16.91.165--->${mysql.address}  
![](https://note.youdao.com/yws/public/resource/ca7c2468223e3c4a80c4e24b70ff9608/xmlnote/5A5FA97BD23D47C7A663499823D76878/20086)  

好处？ 
 
        此模块是由spring-boot来构建的，spring-boot 可以将模块构建成一个jar包，通过命令行的方式，就可以将变量mysql.address传入到docke里  

     
- 将user-thrift-service模块 进行打包(大概2种方式)     
    -  mvn clean install   (进入到此模块下)  
    -  使用intellij idea 自带的工具，如下面所示:  
        - 需要在pom.xml里添加插件依赖，不然打包失败:  
            ```
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
        - 如下图所示：  
        ![](https://note.youdao.com/yws/public/resource/ca7c2468223e3c4a80c4e24b70ff9608/xmlnote/9EB3A076BAE4434F8CC8C5A9194E36A4/20092)  
 - 编写对应的Dockerfile文件  
     ```
    FROM openjdk:7-jre
    
    MAINTAINER XXX XXX@163.com
    
    COPY target/user-thrift-service-1.0-SNAPSHOT.jar /user-service.jar
    
    ENTRYPOINT ["java", "-jar", "/user-service.jar"]  
    ```  
- 进入user-thrift-service主目录下，也就是Dockerfile所在的主目录

-  打镜像  
    docker build -t user-thrift-service .  

-  启动脚本start.sh  
    ```
    #!/bin/bash
    docker stop user-thrift-service
    docker rm user-thrift-service
    docker run -it --name user-thrift-service user-thrift-service:latest --mysql.address=172.16.91.165
    ```  
    
## 部署过程中遇到的问题？  
1. 项目中java版本与运行环境中的java版本不匹配  
```
Exception in thread "main" java.lang.UnsupportedClassVersionError: com/bonc/user/ServiceApplication : Unsupported major.minor version 52.0
	at java.lang.ClassLoader.defineClass1(Native Method)
	at java.lang.ClassLoader.defineClass(ClassLoader.java:808)
	at java.security.SecureClassLoader.defineClass(SecureClassLoader.java:142)
	at java.net.URLClassLoader.defineClass(URLClassLoader.java:442)
	at java.net.URLClassLoader.access$100(URLClassLoader.java:64)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:354)
	at java.net.URLClassLoader$1.run(URLClassLoader.java:348)
	at java.security.AccessController.doPrivileged(Native Method)
	at java.net.URLClassLoader.findClass(URLClassLoader.java:347)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:430)
	at org.springframework.boot.loader.LaunchedURLClassLoader.loadClass(LaunchedURLClassLoader.java:94)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:363)
	at org.springframework.boot.loader.MainMethodRunner.run(MainMethodRunner.java:46)
	at org.springframework.boot.loader.Launcher.launch(Launcher.java:87)
	at org.springframework.boot.loader.Launcher.launch(Launcher.java:50)
	at org.springframework.boot.loader.JarLauncher.main(JarLauncher.java:51)
```    
- 很明显，将openjdk:7-jre镜像，改成openjdk:8-jdk-alpine, 然后重新打镜像即可了
    