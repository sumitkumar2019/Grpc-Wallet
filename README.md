# Software Requirement:

* Install My Sql Server 5.7.(Environment variables automatically set during installation)
* Install java 8. (Set Environment variable)
* Install docker toolbox (Environment variables automatically set during installation)
* Install git (Set Environment variable)
* Install protobuf-java-3.6.1 (Set Environment variable)
* Install Gradle 4.9 (Set Environment variable)

# Installing Application and running

<b>Make sure Mysql server is running on port with username root and password root at port 3306.</b>

# Clone the GRPC Wallet Application

 * git clone https://github.com/sumitkumar2019/Grpc-Wallet

# Installing & running GRPC Wallet Server 

* Go to Grpc-Wallet-Server directory:- <b>cd Grpc-Wallet/Grpc-Wallet-Server</b>
* Build the application:- <b>gradle build</b>
* Run the GRPC Wallet Server:- <b>Java - jar build/libs/Grpc-Wallet-Server.0.0.1.SNAPSHOT-all.jar</b>

# Installing & running GRPC Wallet Client

* Go to Grpc-Wallet-Server directory:- <b>cd Grpc-Wallet/GRPC-Wallet-Client</b>
* Build the application:- <b>gradle build</b>
* Run the GRPC Wallet Server:- <b>Java - jar build/libs/Grpc-Wallet-Client.0.0.1.SNAPSHOT-all.jar</b>

* The wallet client should have the following CLI parameters:

 <b>-- number of concurrent users(if not available it will create new in db)</b></br>
 <b>--number of concurrent requests a user will make</b></br>
 <b>--number of rounds each thread is executing</b></br>

# Logging

* Logs:- slfj4 with log4j is configured to spit log on console


# Running Integration tests

* Go to Grpc-Wallet-Server directory:- <b>cd Grpc-Wallet-Server</b></br>
* Test the application:- <b>gradle test</b>

# Running Performence tests
 
 * Make sure Grpc-Wallet-Server is running on port 50051 
`* Inside Grpc-Wallet-Client -> Inside src/test/ -> inside package -> com.app.wallet.performence.test -> Run WalletPerformenceTest.java

# Running Inside Docker

#Install My SQL inside docker

* Install My sql inside docker using below command

 <b>docker pull mysql/mysql-server:8.0.12</b><br>
 <b>docker run --name=mydockermysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_USER=sa -e MYSQL_PASSWORD=password -p 127.0.0.1:3306:3306 -d mysql/mysql-server:8.0.12</b><br>

* Check if it is running in container and it is in healthy stateL

<b>docker ps -a<b>

* If not started check logs using:

docker logs mydockermysql

* Verify if we are able to login to mysql using username as 'sa' and password as 'password':

docker exec -it mydockermysql mysql -usa -p

* Create database walletdb inside the mysql user 'sa'

mysql> create database walletdb;

* Grant all Privileges to 'sa'
mysql> GRANT ALL PRIVILEGES ON *.* TO 'sa'@'%';
mysql> FLUSH PRIVILEGES;

# Install Grpc-Wallet-Server on Docker

* Go to Grpc-Wallet-Server directory:- cd Grpc-Wallet-Server and run below command

<b>docker build . -t wallet-server</b>

* get current ip of docker to replace with localhost

<b>docker default ip</b>

* run application on docker linking mysql container

<b>docker run --add-host="localhost:<replace with container IP>" -p 50051:50051 --name wallet-server --link mydockermysql:mysql -d wallet-server</b>

# Install Grpc-Wallet-Client

* cd Grpc-Wallet-Clinet and run below command

<b>docker build . -t wallet-client</b>

* get current ip of docker to replace with localhost

<b>docker default ip</b>

* run client application on docker 

<b>docker run --add-host="localhost:<replace with container IP>" -it <docker imageid of application> java -jar Grpc-Wallet-Client-0.0.1-SNAPSHOT-all.jar</b>


# Other Useful commands for docker

*	Show Containers - docker ps 
*	Show Images - docker images 
*	Remove Images - docker rmi <image-id>
*   Remove images forcefully - docker rmi -f <image-id> 
*   Remove container - docker rm <container-id>
* 	Stop container - docker stop <container-id>
* 	Show all active and nonactive container - docker ps -a 
* 	Show all images of active and nonactive container - docker ps -a -q 
*	default ip of docker-machine - docker-machine ip default
*	Search images on docker hub - docker search mysql 

# Technologies Used
* The following technologies are used:
<b>Java<br></b>
<b>Java Money<br></b>
<b>gRPC<br></b>
<b>MySQL or PostgreSQL<br></b>
<b>Gradle<br></b>
<b>JUnit<br></b>
<b>SLF4J<br></b>
<b>Docker<br></b>
<b>Spring<br></b>
<b>Hibernate<br></b>

# Note
<b> Total transaction RPC/s = 89 Approx  ({Total Count of RPC in 60 seconds}/60) </b>
