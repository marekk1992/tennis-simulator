# Tennis Simulator console application

## 1. About Tennis Simulator
Application provides ability:
    
- to enter a player by specifying his/her name, gender and ATP/WTA rating;
- choose players for a simulation;
- simulate match serve-by-serve.

Match is simulated according to real tennis game structure: Serve-Game-Set-Match.

## 2. How to run this app
Before running this application, I assume that you have successfully installed the following tools on your computer:
- Git - for cloning repository from GitHub;
- Maven - for building and running project from terminal;
- JDK - required for Maven compilation;

`Step 1` - build project.

Navigate to root of the project and execute command:

    $ mvn clean package

`Step 2` - run application.

Execute command:

    $ mvn exec:java

Application is started. Follow console messages to simulate your match.