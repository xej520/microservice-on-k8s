FROM 172.16.91.165:80/micro-service/centos

COPY message-thrift-go-service /opt/message-thrift-go-service

RUN chmod +x /opt/message-thrift-go-service

ENTRYPOINT ["/opt/message-thrift-go-service"]
