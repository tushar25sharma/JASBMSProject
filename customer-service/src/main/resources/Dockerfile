FROM openjdk:24-slim
VOLUME /tmp
ARG JAR_FILE=target/customer-service-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]