FROM amazoncorretto:17.0.6-al2023
MAINTAINER indiagator
COPY build/libs/cbtskillwisetransaction-0.0.1-SNAPSHOT-plain.jar app.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","/app.jar"]