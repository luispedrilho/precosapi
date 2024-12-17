# Usar a imagem oficial do OpenJDK como base
FROM openjdk:17-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo pom.xml (se estiver usando Maven)
COPY pom.xml .

# Baixar as dependências do Maven
RUN mvn dependency:go-offline

# Copiar o código fonte
COPY src /app/src

# Compilar o projeto com Maven
RUN mvn clean install

# Expor a porta
EXPOSE 8080

# Iniciar a aplicação
CMD ["java", "-jar", "target/precosapi-0.0.1-SNAPSHOT.jar"]
