#!/bin/bash
docker build -t course-dubbo-service .

docker push 172.16.91.165:80/micro-service/course-dubbo-service
     