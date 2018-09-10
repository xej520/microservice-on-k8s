FROM 172.16.91.222:80/jdk/openjdk:8-jre

MAINTAINER XXX XXX@163.com

COPY target/user-thrift-service-1.0-SNAPSHOT.jar /user-service.jar

ENTRYPOINT ["java", "-jar", "/user-service.jar"]