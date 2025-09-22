# Use uma imagem base oficial do OpenJDK
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o JAR da aplicação para o diretório de trabalho do contêiner
COPY target/pts-api-perimetry-assessment-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação vai rodar
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
