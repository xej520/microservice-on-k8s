#!/bin/bash
docker stop marathon-lb
docker rm marathon-lb
docker run -d --net=host \
    -e PORTS=9090 \
    --name=marathon-lb \
    172.16.91.222:80/mesos/marathon-lb:v1.11.1 sse  \
    --group=external \
    --marathon http://172.16.91.165:7070