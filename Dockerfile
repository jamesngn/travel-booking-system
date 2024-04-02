FROM tomcat:10-jdk17
ADD target/TravelBookingSystem.war /usr/local/tomcat/webapps/TravelBookingSystem.war
EXPOSE 8080
CMD ["catalina.sh", "run"]