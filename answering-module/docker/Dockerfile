FROM amazoncorretto:17

EXPOSE 9090

COPY build/libs/answering-module-0.0.1.jar ./app/app.jar

WORKDIR ./app

ENTRYPOINT ["java","-jar","app.jar"]