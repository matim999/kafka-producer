FROM openjdk:8
ADD target/kafka-producer.jar kafka-producer.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "kafka-producer.jar"]