#!/bin/sh bash
thrift --gen py -out ../  message.thrift

#生成java api
thrift --gen java -out ../../message-thrift-service-api/src/main/java message.thrift