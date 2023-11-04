FROM openjdk:11

# Copie du fichier JAR de l'application depuis le build précédent
COPY target/achat-1.0.jar achat-1.0.jar

# Exposition du port de l'application Spring Boot
EXPOSE 8089

# Commande d'exécution de l'application
ENTRYPOINT ["java", "-jar", "achat-1.0.jar"]
