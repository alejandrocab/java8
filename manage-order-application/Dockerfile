FROM openjdk:8-jre-alpine
#Creamos un argumento que hace referencia al jar de la aplicacion
ARG JAR_FILE=target/*.jar
#Copia el jar
COPY ${JAR_FILE} application.jar
#Exponemos el puerto al 8082
EXPOSE 8082
#Establecemos lo que se va a ejecutar: java -jar application.jar
ENTRYPOINT ["java", "-jar","application.jar"]