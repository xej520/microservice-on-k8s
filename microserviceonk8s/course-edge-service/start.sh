 ### 容器的启动交给mesos marathon来管理 不需要脚本
 #!/bin/bash
 #docker stop course-edge-service
 #docker rm course-edge-service
 #docker run -it --name course-edge-service course-edge-service:latest --zk.address=172.16.91.165