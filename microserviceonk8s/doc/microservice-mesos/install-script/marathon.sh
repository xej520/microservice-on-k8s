#!/bin/bash
docker stop marathon
docker rm marathon
docker run -d --net=host \
	--name=marathon \
	-e HTTP_PORT=7070 \
	-e MARATHON_HTTP_PORT=7070 \
	172.16.91.222:80/mesos/marathon:v1.5.2 \
	--master zk://172.16.91.222:2181/mesos \
	--zk zk://172.16.91.222:2181/marathon
