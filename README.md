# Maven + Jenkins Demo

Requirements:
- Java 17+
- Maven 3.9+

Run locally:
    mvn clean package

Expected:
    BUILD SUCCESS

Artifact:
    target/maven-jenkins-demo-1.0-SNAPSHOT.jar

Jenkins Freestyle:
1. Put this project in the Jenkins workspace, or later configure Git SCM.
2. Build Steps -> Execute Windows batch command:
       mvn clean package
3. Click Build Now.
4. Later add Post-build Action -> Archive artifacts:
       target/*.jar
