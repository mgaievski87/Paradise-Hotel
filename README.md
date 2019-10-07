Thank you for your interest in my project! :)

In order to start the back-end part please make sure that you have java 12 (JRE) (https://java.com/download) and Gradle build tool installed (https://gradle.org/install/). After that in the command prompt in the project root directory run the following bootRun task: "./gradlew bootRun" The server will start on port 8080 by default. In order to run it on different port run the following: "./gradlew bootRun -Dserver.port=[some_port]" In order to start front-end part make sure to have angular installed (https://www.npmjs.com/package/angular). From Paradise-Hotel/src/main/webapp/angular2/ run "ng serve" and navigate to http://localhost:4200/ That's it. You don't need to deploy it into any servlet container. Tomcat is already embedded in the jar file as well as H2 in-memory database.

For any comments or suggestions please write to my personal email: mgaievski87@gmail.com

Kind regards, Maksym Gaievskyi
