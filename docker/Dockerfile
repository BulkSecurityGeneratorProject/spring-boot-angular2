FROM java

MAINTAINER "Djamel HAMAS"


VOLUME /tmp

ENV JAVA_OPT -Dspring.profiles.active=dev

ADD backend.jar app.jar
RUN bash -c 'export LC_ALL=C'
RUN bash -c 'touch /app.jar'

CMD java $JAVA_OPT -jar /app.jar