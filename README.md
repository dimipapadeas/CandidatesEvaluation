# SpringBoot-Angular

A Sandbox project that has the goal to assess the development skills of a Web Developer candidate by completing
exercises.

The exercises require Java, HTML, Typescript/Javascript skills to complete.

These instructions include everything the candidate needs to know on how to download the sources of this simplified web
application on a personal computer as well as how to deploy and run it. The instructions begin with going through the
installation process. After the application is successfully installed and running, the candidate is ready to start with
the exercises that follow. Each exercise awards a different amount of points totaling 100 points.

The use of any IDE and/or editor to edit the source code is allowed. It is totally acceptable, that the candidate gets
any help and information he/she needs from the internet. However it is not acceptable to ask for another person's help.

Nevertheless, the candidate is encouraged to contact the assessor for any questions he/she might have, be it he/she
needs any additional information, or he/she requires some help for completing the installation and/or an exercise. If
the candidate has difficulties for example to complete the installation, he/she could ask for help from the assessor in
order to be able to go on with the exercises and gain points.

The assessor should provide contact information to the candidate.

The candidate has to locate the files needed to complete the exercises. The exercises cover both front-end and back-end
implementation tasks.

## Installation

To install and run the application an active internet connection is required. Before you can build this project, you
must install and configure the following dependencies on your machine:

### 1: JDK 17

Download and install any version of the JDK 17. Install JDK in a folder whose path does not contain spaces. Make sure to
also set the environmental variable JAVA_HOME in your system. The variable must point to your JDK folder (
absolute path). The path of the "\jre\bin" folder under the JDK folder should also be **prepended** to the PATH system
variable.

### 2: Maven Version 3.x

Download and install any version of Maven 3. Install Maven in a folder whose path does not contain spaces. Make sure to
also set the environmental variables M2_HOME and M2 in your system. The M2_HOME variable must point to the maven
folder (absolute path). The M2 variable must point to the "bin" folder under the maven folder (absolute path). The
absolute path of the "bin" folder under the maven folder (M2) should also be **prepended** to the PATH system variable.

### 3: Git (Optional)

You need to install the Git command line client to download the sources of the application.

### 4: Source code

Download the sources of the application. If you installed Git client in the previous step, then navigate with the
command line to a folder in which you want to download the source code. Run the following command:

```
git clone https://github.com/DimiTerz/SpringBootAngular.git
```

Alternatively, if you have not installed Git client in the previous step, you can download the zip from

```
https://
```

In any case make sure that the folder with the application's sources is saved under a path which does not contain
spaces.

### 5: Node.js (Optional)

Node.js is used to run a development web server and build the project. Depending on your system, you can install Node.js
either from source or as a pre-packaged bundle.

### 6: Compile and start App

Navigate to the project folder and execute the command

```
mvn spring-boot:run
```

This should start the application.

Navigate to http://localhost:8080 in your browser to open the application. There are 3 available Users you can use to
login to the application. Username and password are:

```
"admin", "JDoe" and "ScottS".
```

To stop the application, just terminate the execution of the previous command. After making changes to the code stop the
application and run again

```
mvn spring-boot:run
```

More information regarding the components of the Application can be found in further README files.

## Excercices

The following tasks award points up to a maximum of 100.

### Installation (xx points)

### Exercise 1 (xx points)

### Exercise 2 (xx points)

### Exercise 3 (xx points)

### Exercise 4 (xx points)

### Exercise 5 (xx points)

### Extra Credit Exercise

## Delivery

When finished the candidate should compress the root folder of the project and send it to the assessor by email. Along
with the compressed file a screenshot of the candidate's browser with the Application running should be sent in order to
verify that the installation was successfully completed.

The deadline to send the compressed file is determined by the assessor. Normally it should be exactly three days after
the test has started.

Thank you for participating and good luck!!!
