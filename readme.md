## 📝 Note-Taking App

A simple RESTful API built with Spring Boot and MongoDB to manage notes.  
This project is part of project-based learning to improve backend development skills.

### 📚 Features

- Create a new note
- Get all notes
- Get a note by ID
- Update a note
- Delete a note

### 🛠 Tech Stack

- Language: Java
- Framework: Spring Boot
- Database: MongoDB
- Tools: IntelliJ IDEA, Postman

### 🧱 Entity Structure

private ObjectId id;
private String title;
private String content;
private LocalDateTime createdAt;


### 📦 Setup Instructions

bash
### 1. Clone the repository
git clone https://github.com/amiruddinsiddiqui/Notes-Taking-App.git
cd Note-Taking-App

### 2. Configure MongoDB in application.properties
spring.data.mongodb.uri=mongodb://localhost:27017/notedb

### 3. Run the application
./mvnw spring-boot:run


### 🚀 API Endpoints

GET     /note/getnote            → Get all notes  
GET     /note/getbyid/{id}       → Get note by ID  
POST    /note/postnote           → Create a new note  
PUT     /note/update{id}         → Update a note  
DELETE  /note/delbyid/{id}       → Delete a note


### 🎯 Learning Goals

- Build REST API using Spring Boot
- Integrate MongoDB with Spring Data
- Practice CRUD operations
- Handle JSON requests and responses

### 📌 Future Enhancements

- Add user authentication (JWT)
- Enable end-to-end encryption (E2EE)
- Tag-based filtering and search
- Swagger/OpenAPI documentation
- Frontend client (React or mobile)