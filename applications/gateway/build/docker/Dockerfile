FROM davidcaste/alpine-java-unlimited-jce:jdk8
ADD add_1.tar /
ENTRYPOINT ["java", "-Dspring.profiles.active=container", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
