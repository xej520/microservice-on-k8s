#!/bin/bash
docker stop message-thrift-go-service
docker rm message-thrift-go-service
docker run -it --name message-thrift-go-service message-thrift-go-service:latest