# Stage 1: Build l'application avec Maven
FROM maven:3.9.4 AS build
WORKDIR /app
COPY pom.xml /app
COPY src /app/src
# Exécution de la commande mvn clean package pour reconstruire le fichier JAR de l'application
RUN mvn clean package

# Stage2: Utilisation de l'image OpenJDK 11 depuis Docker Hub
FROM openjdk:11

# Copie du fichier JAR de l'application depuis le build précédent
ADD target/achat-1.0.jar achat-1.0.jar

# Exposition du port de l'application Spring Boot
EXPOSE 8089

# Commande d'exécution de l'application
ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]