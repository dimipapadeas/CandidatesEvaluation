# BackEnd

This Application is using [SpringBoot]() version 2.4.5.

## Data storage

For the Data storage H2 Database Engine is used. To connect to the Database via H2 Console while the App is running
navigate to [http://localhost:8080/api/h2-console](http://localhost:8080/api/h2-console). The username is sa and the
password is blank.

On Start the Application creates the Database and populates it with the initial data Automatically.

To delete the Database if something goes wrong just delete the folder `data/` under the root of the project. On the next
restart the Database will be initialized from scratch again.

## Build

Run `mvn clean install` to build the project. The build artifacts will be stored in the `target/` directory.
