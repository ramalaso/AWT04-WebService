FROM openjdk:11.0.4
COPY . /AWT04-WebService
ENTRYPOINT /bin/bash
EXPOSE 8080
CMD java -jar spring.jar