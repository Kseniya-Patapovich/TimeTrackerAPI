FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /ttapp
COPY . /ttapp/.
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17-alpine
WORKDIR /ttapp
COPY --from=builder /ttapp/target/*.jar /ttapp/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/ttapp/*.jar"]