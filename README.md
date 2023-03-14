Steps to execute:

unzip the lcs.zip file
go lcs folder
Create a new jar file using maven builder.
	./mvnw clean package
 Create docker image using the docker file 
    	docker build -t merla/lcsdocker .
 Run docker image and expose the container port
	docker run -d -p 8080:8080 merla/lcsdocker:latest
 The Application will be running at "http://localhost:8080"
 Try hitting the endpoint http://localhost:8080/lcs with the request object
You can import the postman collection and try the requests



option2:
Goto project directory
mvn clean install
mvn spring-boot:run
