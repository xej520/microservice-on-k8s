#!/bin/bash
docker build -t api-gateway-zuul .

docker push 172.16.91.165:80/micro-service/api-gateway-zuul
     