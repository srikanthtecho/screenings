FROM davidcaste/alpine-java-unlimited-jce:jdk8
ADD target/* /target/
CMD ["java","-Dspring.profiles.active=container", "-jar", “/build/libs/gateway.jar"]
