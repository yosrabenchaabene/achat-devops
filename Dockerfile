FROM openjdk:11
WORKDIR /app
ADD http://localhost:8081/repository/maven-releases/tn/esprit/rh/achat-1.0.jar achat-1.0.jar
EXPOSE 8089
CMD ["java", "-jar", "achat-1.0.jar"]
