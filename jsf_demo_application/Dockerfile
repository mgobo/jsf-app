# Imagem base para criação do container
FROM ubuntu:20.04

ENV TZ=America/Sao_Paulo

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone && \
apt-get update && \
apt-get install -y openjdk-17-jdk && \
apt-get install -y wget && \
apt-get install -y unzip && \
wget https://github.com/wildfly/wildfly/releases/download/26.1.3.Final/wildfly-26.1.3.Final.zip && \
unzip wildfly-26.1.3.Final.zip && \
mv wildfly-26.1.3.Final wildfly && \
java -version && \
apt-get autoclean

ENV LC_ALL=pt_BR.ISO-8859-1
ENV LANG=pt_BR.ISO-8859-1

EXPOSE 8080 9990

# Deploy da aplicação
COPY target/jsf_demo_application.war /wildfly/standalone/deployments
COPY config/postgresql/ /wildfly/modules/system/layers/base/org/postgresql/
COPY config/standalone.xml /wildfly/standalone/configuration/

WORKDIR /

RUN printf 'sh /wildfly/bin/standalone.sh -b=0.0.0.0 -bmanagement=0.0.0.0' >> docker-entrypoint.sh

# Inicia o dominio jsf_application_demo
ENTRYPOINT ["sh", "docker-entrypoint.sh"]