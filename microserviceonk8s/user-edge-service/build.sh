#!/bin/bash
docker build -t user-edge-service .

docker push 172.16.91.165:80/micro-service/user-edge-service