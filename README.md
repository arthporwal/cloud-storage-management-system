
# Cloud Storage Management System

A cloud-native document management platform enabling secure file upload, storage, retrieval, and metadata management. Backend deployed on AWS EC2 with AWS S3 for scalable, durable object storage.

## Features

- Secure file upload and download
- File metadata management
- Backend workflow automation for document operations
- RESTful APIs built with Spring Boot
- Cloud deployment on AWS EC2, with AWS S3 for object storage

## Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Database:** MySQL
- **Cloud:** AWS EC2 (hosting), AWS S3 (object storage)
- **Build Tool:** Maven

## Getting Started

### Prerequisites
- Java 17+ *(update if different)*
- Maven
- MySQL running locally
- AWS account with an S3 bucket configured (if running the storage integration locally)

### Setup
```bash
git clone https://github.com/arthporwal/cloud-storage-management-system.git
cd cloud-storage-management-system
```

Configure `src/main/resources/application.properties` with your database and AWS credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/[your_db_name]
spring.datasource.username=[username]
spring.datasource.password=[password]

aws.s3.bucket=[your_bucket_name]
aws.access.key=[use environment variables, not hardcoded values]
aws.secret.key=[use environment variables, not hardcoded values]
```

Run the application:
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## API Endpoints

*(Fill in your real endpoints — example structure below)*

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/files/upload` | Upload a file to S3 |
| GET | `/api/files/{id}` | Download/retrieve a file |
| GET | `/api/files` | List all files with metadata |
| DELETE | `/api/files/{id}` | Delete a file |

## Project Structure

```
src/main/java/
├── controller/    # REST endpoints
├── service/       # Business logic, S3 integration
├── repository/    # Data access layer
├── model/         # Entity classes
```
