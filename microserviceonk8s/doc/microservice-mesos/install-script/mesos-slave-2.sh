#!/bin/bash
docker stop mesos-slave
docker rm mesos-slave
docker run -d --net=host \
    --privileged \
    --name=mesos-slave \
    -e MESOS_PORT=5051 \
    -e MESOS_MASTER=zk://172.16.91.222:2181/mesos \
    -e MESOS_SWITCH_USER=0 \
    -e MESOS_CONTAINERIZERS=docker,mesos \
    -e MESOS_LOG_DIR=/var/log/mesos \
    -e MESOS_WORK_DIR=/var/tmp/mesos \
    -v "$(pwd)/log/mesos:/var/log/mesos" \
    -v "$(pwd)/work/mesos:/var/tmp/mesos" \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v /sys:/sys \
    -v /usr/local/bin/docker:/usr/local/bin/docker \
    172.16.91.222:80/mesos/slave:1.4.1  --no-systemd_enable_support \
    --no-hostname_lookup --ip=172.16.91.167