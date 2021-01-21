# Cron Setup Instructions

Download Java 8 : 
https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html

Set Java Home - https://mkyong.com/java/how-to-set-java_home-environment-variable-on-mac-os-x/

Download Maven tar.gz
https://maven.apache.org/download.cgi

Set maven home : 
export PATH=$PATH:/opt/apache-maven/bin

To note if latest mac- you have to set path in zsh and not bash profile.Follow above mykong link to set zsh profile.

Install intellij ide
https://www.jetbrains.com/idea/download/#section=mac

Setup Sample Spring boot Project : 
https://www.jetbrains.com/help/idea/your-first-spring-application.html#create-new-spring-boot-project
Initialising the project will take 5 minutes time.  I Also selected lombok and Rest Repositiories while setting up project.

Run : mvn clean install ; this command should be run from the project home directory you should get build success.

You should also be able to start the Spring boot project from intellij and see the server running.

# Check out APIs
http://localhost:8080/cron/swagger-ui.html

# Add following ENV variables before starting the server.
SLACK_SIGNING_SECRET=<YOUR_SIGNIN_SECRET> 
SLACK_BOT_TOKEN=<YOUR_BOT_TOKEN>
