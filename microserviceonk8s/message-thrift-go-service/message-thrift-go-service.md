# message-thrift-go-service 模块 docker化步骤  
> 这个模块是用go语言开发的，如果在window上进行编译的话，得到的二进制只能在window环境上运行，  
因此需要copy到centos环境上，进行编译  

## 环境准备：
 - 需要将生成的代码miscroserviceonk8s，拷贝到gopath的src路径下，跟window上配置是一样的  
 - centos系统上要有go 环境，并且设置了gopath路径
 - 将thrift依赖的go 库，拷贝到$gopaht/src下,如git.apache.org/thrift.git  
 如下如所示：  
 ![](https://note.youdao.com/yws/public/resource/ca7c2468223e3c4a80c4e24b70ff9608/xmlnote/C452EB76235140F687696975BEAE44CE/20094)  
 
 ## 主要步骤：
 - 将microserviceonk8s工程拷贝到centos服务器上  
 - 将上面生成的二进制包message-thrift-go-service拷贝到message-thrift-go-service模块下 
 - 下载centos:latest 镜像，
    - 不知道为啥openjdk系列的镜像，不能运行此服务，可能是bash不兼容的问题  
    - docker pull centos 
 - 编写Dockerfiler文件  
      
         FROM centos
            
         COPY message-thrift-go-service /opt/message-thrift-go-service
            
         RUN chmod +x /opt/message-thrift-go-service
            
         ENTRYPOINT ["/opt/message-thrift-go-service"]  
 - 构建镜像  
        
        docker build -t message-thrift-go-service .   
 - 编写启动脚本start.sh  
 
        #!/bin/bash
        docker stop message-thrift-go-service
        docker rm message-thrift-go-service
        docker run -it --name message-thrift-go-service message-thrift-go-service:latest
 
  
&ensp;  
&ensp;  
&ensp;  
&ensp;  
&ensp;  
&ensp;  
&ensp;  
&ensp;  
