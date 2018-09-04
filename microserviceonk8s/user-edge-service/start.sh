#!/bin/bash
docker stop user-edge-service
docker rm user-edge-service
docker run -it --name user-edge-service user-edge-service:latest --redis.address=172.16.91.165