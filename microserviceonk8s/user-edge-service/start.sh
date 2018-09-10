### 容器的启动交给mesos marathon来管理 不需要脚本
#!/bin/bash
#docker stop user-edge-service
#docker rm user-edge-service
#docker run -it --name user-edge-service user-edge-service:latest --redis.address=172.16.91.165