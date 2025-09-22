# Use uma imagem base oficial do OpenJDK
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho no contêiner
WORKDIR /app

# Copie o JAR da aplicação para o diretório de trabalho do contêiner
COPY target/pts-api-bioimpedance-recommendation-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação vai rodar
EXPOSE 9091

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]


FROM eclipse-temurin:17-jdk as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["/wait-for-it.sh", "bioimpedance-recommendation-mysql:3306", "--", "java", "-jar", "app.jar"]