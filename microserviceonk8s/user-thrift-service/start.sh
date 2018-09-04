#!/bin/bash
docker stop user-thrift-service
docker rm user-thrift-service
docker run -it --name user-thrift-service user-thrift-service:latest --mysql.address=172.16.91.165