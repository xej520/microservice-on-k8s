#!/bin/bash
docker build -t 172.16.91.222:80/micro-service/user-thrift-service .

docker push 172.16.91.222:80/micro-service/user-thrift-service
