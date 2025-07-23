# Etapa 1: Build da aplicação com Maven Wrapper
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o projeto completo para o container
COPY . .

# Compila o projeto e gera o .jar (sem rodar testes)
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final de execução
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia apenas o .jar gerado para o novo container
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar o app
ENTRYPOINT ["java", "-jar", "app.jar"]
