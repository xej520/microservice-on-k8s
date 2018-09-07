FROM 172.16.91.165:80/micro-service/openjdk:8-jre

MAINTAINER XXX XXX@163.com

COPY target/user-thrift-service-1.0-SNAPSHOT.jar /user-service.jar

ENTRYPOINT ["java", "-jar", "/user-service.jar"]