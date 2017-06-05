FROM openjdk:8u131-jre

COPY target/scala-2.12/labworks-assembly-1.0.jar /home/labworks-assembly-1.0.jar
CMD ["java","-jar","/home/labworks-assembly-1.0.jar"]
EXPOSE 8080