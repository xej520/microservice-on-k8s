 #!/bin/bash
 docker stop course-dubbo-service
 docker rm course-dubbo-service
 docker run -it --name course-dubbo-service course-dubbo-service:latest --zk.address=172.16.91.165  --mysql.address=172.16.91.165