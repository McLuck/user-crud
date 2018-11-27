FROM openjdk:8-jdk-alpine
MAINTAINER mcluck.ti@gmail.com
ARG versao

RUN mkdir /opt
RUN mkdir /opt/user-crud

#ADD "http://nexus:8081/repository/maven-releases/com/lucasisrael/user-crud-rest/${versao}/user-crud-rest-${versao}.jar" /opt/user-crud/sicat-user-crud-rest.jar
ADD "target/user-crud-${versao}.jar" /opt/user-crud/user-crud.jar
ADD env.conf /opt/user-crud/env.conf
ADD start-app.sh /opt/user-crud/start-app.sh

RUN chmod +x /opt/user-crud/env.conf
RUN chmod +x /opt/user-crud/start-app.sh
RUN chmod +x /opt/user-crud/user-crud.jar
WORKDIR /opt/user-crud

EXPOSE 9000
EXPOSE 5000

ENTRYPOINT ["/opt/user-crud/start-app.sh"]
