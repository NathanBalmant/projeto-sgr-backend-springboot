# Etapa 1: Build com Maven e Java 
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia todos os arquivos do projeto para dentro do container
COPY . .

# Compila o projeto e gera o .jar, sem rodar os testes
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final só com o JDK para executar o .jar
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copia o .jar compilado da etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expondo a porta padrão do Spring Boot
EXPOSE 8080

# Comando que roda a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
