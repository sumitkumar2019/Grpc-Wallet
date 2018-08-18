FROM buildpack-deps:jessie-scm
ADD add_1.tar /
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/Wallet-Server.0.0.1.jar"]
EXPOSE 50051
