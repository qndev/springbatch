# Spring Batch Project

This project is a simple implementation of a Spring Batch application.

## Technologies Used

- Java
- Spring Boot
- Spring Batch
- Maven

## Project Structure

The project is structured into different packages and classes:

- `BatchDataSourceConfiguration.java`: This class is responsible for setting up the data source for the batch processing. It also initializes the database using a script.

- `BatchConfiguration.java`: This class is responsible for configuring the Spring Batch infrastructure. It sets up the `JobRepository`, `JobExplorer`, `JobLauncher`, `JobRegistry`, `JobBuilderFactory`, and `StepBuilderFactory`.

- `DefaultBatchConfigurer.java`: This class is an implementation of the BatchConfigurer interface provided by Spring Batch. This class is used to configure the main components of a Spring Batch application, including the JobRepository, JobLauncher, and JobExplorer.

- `JobConfiguration.java`: This class is responsible for defining the batch job and its steps. It uses Spring Batch's JobBuilderFactory and StepBuilderFactory to create a job and its steps.

## Setup and Run

To run this project, you need to have Java and Maven installed on your machine.

1. Clone the repository to your local machine.
2. Navigate to the project directory.
3. Run `mvn clean install` to build the project.
4. Run `mvn spring-boot:run` to start the application.

## Contributing

Contributions are welcome. Please feel free to submit a pull request or open an issue.

## License

This project is licensed under the Apache License 2.0.
