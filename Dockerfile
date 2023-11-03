# Utilisez une image de base Java
FROM openjdk:8-jdk-alpine

# Exposez le port sur lequel votre application Spring Boot s'exécute (par défaut, c'est le port 8080)
EXPOSE 8089

# Ajouter le fichier JAR de l'application dans le conteneur
ADD target/achat-devops-master-1.0.jar achat-devops-master-1.0.jar



# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/achat-devops-master-1.0.jar"]
