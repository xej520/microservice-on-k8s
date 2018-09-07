#!/bin/bash
docker build -t message-thrift-go-service .

docker push 172.16.91.165:80/micro-service/message-thrift-go-service