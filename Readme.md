Mowers Application

**Execute:**

Create file.txt with values

First line should contain a pair or integer numbers with the plateau info size for X and Y, separate by space.

`Example: 5 5`

Following contain the mower information. First line with the mowers position, and second line with the movements for the mower.

`Example:
line 1: 1 2 N
line 2: LMLMLMLMM`

java -jar target\mower-application-1.0-SNAPSHOT.jar file.txt

To see kafka events, it needs to be running, in the folder you can execute `docker-compose up`

**Tech stack**

* DDD
* Java
* Spring Boot
* Maven
* MockK
* Kafka to publish event when mower has changed

**TODO**
Add integration test
Add another client example api-rest