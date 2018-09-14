#!/bin/bash
docker stop mesos-master
docker rm mesos-master
docker run -d --net=host \
    --hostname=172.16.91.165 \
    --name=mesos-master \
    -e MESOS_PORT=5050 \
    -e MESOS_ZK=zk://172.16.91.222:2181/mesos \
    -e MESOS_QUORUM=1 \
    -e MESOS_REGISTRY=in_memory \
    -e MESOS_LOG_DIR=/var/log/mesos \
    -e MESOS_WORK_DIR=/var/tmp/mesos \
    -v "$(pwd)/log/mesos:/var/log/mesos" \
    -v "$(pwd)/work/mesos:/var/tmp/mesos" \
    172.16.91.222:80/mesos/master:1.4.1 --no-hostname_lookup --ip=172.16.91.165