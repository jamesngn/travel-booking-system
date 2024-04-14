# Name: Dockerfile
# Path: mysql/Dockerfile

FROM mysql:8.0.26

# Environment variables
ARG MYSQL_DATABASE
ARG MYSQL_USER
ARG MYSQL_PASSWORD

ENV MYSQL_ROOT_PASSWORD=${MYSQL_PASSWORD}
ENV MYSQL_USER=${MYSQL_USER}
ENV MYSQL_DATABASE=${MYSQL_DATABASE}

COPY ./TravelBookingSystem.sql /docker-entrypoint-initdb.d/TravelBookingSystem.sql


EXPOSE 3306
CMD ["mysqld"]