#!/bin/bash
docker build -t user-thrift-service .

docker push 172.16.91.165:80/micro-service/user-thrift-service
