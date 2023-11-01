FROM openjdk:11
WORKDIR /app
# Download the JAR artifact from your Nexus repository
RUN wget http://192.168.1.16:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar
EXPOSE 8089
CMD ["java", "-jar", "achat-1.0.jar"]
