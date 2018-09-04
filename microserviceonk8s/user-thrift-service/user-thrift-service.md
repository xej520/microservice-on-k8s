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
   