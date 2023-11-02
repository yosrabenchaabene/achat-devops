FROM openjdk:8
WORKDIR /app
ADD target/achat-1.0.jar achat-1.0.jar
RUN apt-get update && apt-get install -y mysql-client
EXPOSE 8089
CMD ["java", "-jar", "achat-1.0.jar"]
