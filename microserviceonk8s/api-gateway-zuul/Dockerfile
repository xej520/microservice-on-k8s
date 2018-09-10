FROM 172.16.91.222:80/jdk/openjdk:8-jre

MAINTAINER XXX XXX@163.com

COPY target/api-gateway-zuul-1.0-SNAPSHOT.jar /api-gateway-zuul.jar

ENTRYPOINT ["java", "-jar", "/api-gateway-zuul.jar"]