FROM openjdk:8-jdk-alpine
COPY target/ExpenseReimbursementSystem-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Djava.net.preferIPv4Stack=true","-jar","/app.jar"]
RUN javac src/main/java/content/ERSMain.java
CMD java -classpath src/main/java content.ERSMain