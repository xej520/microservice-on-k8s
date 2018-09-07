#!/bin/bash
docker build -t course-edge-service .

docker push 172.16.91.165:80/micro-service/course-edge-service