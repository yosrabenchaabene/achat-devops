FROM openjdk:8
WORKDIR /app
ADD target/achat-1.0.jar achat-1.0.jar
RUN apt-get update && apt-get install -y wget && \
    wget https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-8.0.27.tar.gz && \
    tar -xzf mysql-connector-java-8.0.27.tar.gz && \
    cp mysql-connector-java-8.0.27/mysql-connector-java-8.0.27.jar /app/mysql-connector-java.jar && \
    rm -rf mysql-connector-java-8.0.27 && \
    rm -f mysql-connector-java-8.0.27.tar.gz && \
    apt-get remove -y wget && \
    apt-get clean
EXPOSE 8089
CMD ["java", "-jar", "achat-1.0.jar"]
