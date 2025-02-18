# File Sharing Application

## Overview

This project is a file-sharing application built using Spring Boot. It allows users to upload, share, and delete files. The application provides a user-friendly interface for managing files and supports secure file transfers.

## Features

- **Upload Files**: Users can upload files to the server.
- **Share Files**: Users can generate shareable links for files.
- **Delete Files**: Users can delete files from the server.
- **List Files**: Users can view a list of all uploaded files.

## Project Structure
.gitattributes .gitignore .idea/ .mvn/ .vscode/ HELP.md mvnw mvnw.cmd pom.xml ReadMe.md src/ main/ java/ com/example/Filesharingapp/ controller/ FileController.java entity/ FileEntity.java service/ FileService.java FileServiceImplementation.java repository/ FileRepository.java model/ FileModel.java FilesharingappApplication.java resources/ application.properties static/ styles/ css/ home-style.css list-style.css share-files.css templates/ home.html list-files.html share-files.html test/ java/ com/example/Filesharingapp/ FilesharingappApplicationTests.java target/

## Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL

### Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/filesharingapp.git
   cd filesharingapp

2. Configure the database: Update the application.properties file with your MySQL database credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/filesharing_app
spring.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3.Build the project:
./mvnw clean install

4.Run the application:
./mvnw spring-boot:run

5.Access the application: Open your browser and navigate to http://localhost:8080.

Project Details
Controllers
FileController: Handles HTTP requests for file operations.
Services
FileService: Interface defining file operations.
FileServiceImplementation: Implementation of FileService.
Repositories
FileRepository: JPA repository for FileEntity.
Entities
FileEntity: Represents a file in the database.
Models
FileModel: Data transfer object for file operations.
Templates
home.html: Home page template.
list-files.html: Template for listing files.
share-files.html: Template for sharing files.
Static Resources
CSS Files: Styles for the application.
License
This project is licensed under the Apache License 2.0. See the LICENSE file for details.

Acknowledgements
Spring Boot
Thymeleaf
Lombok
MySQL
Contact
For any inquiries, please contact aryanrajtalekar@gmail.com. 