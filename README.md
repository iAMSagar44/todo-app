# Full-Stack Application with React, Spring Boot, and MySQL

I have been building a lot of tutorials on how to get started with Spring Security. In almost all of these tutorials, I have focused on the backend with Spring Boot and Spring Security.


I have not really talked about the frontend. In this tutorial, I will show you how to build a full-stack application with Spring Boot, Spring Security, and React.js. The same concepts would apply no matter what frontend framework you are using.

## Getting Started

You should be familiar with the following technologies:

- Backend:
    - [Spring Boot](https://spring.io/projects/spring-boot)
    - [Java 17](https://openjdk.org/projects/jdk/17/)
    - [Spring Web](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#spring-web)
    - [Spring Security](https://spring.io/projects/spring-security)
    - [Spring Data JPA](https://spring.io/projects/spring-data-jpa#overview)
- Frontend:
    - [React.js](https://reactjs.org)
    - [Tailwind CSS](https://tailwindcss.com/)

### Prerequisites

- Node.js version 16.0 or higher
- Java 17 or higher
- Docker Engine or Docker Desktop

### Running the application

To run the application, you need to start the backend and the frontend. You can do this by running each application in a separate terminal.

- Running it locally using H2 in memory database
   
   - Update application.properties with spring.profiles.active=local, and run the following commands in the Terminal

   -  ```bash
      cd backend
      ./mvnw spring-boot:run
      ```

   -   ```bash
        cd frontend
        npm install
        npm start
       ```
- Running it on an environment using Docker

    - Update application.properties with spring.profiles.active=dev, and run the following commands in the Terminal

    -  ```bash
        cd backend
        docker-compose up
        # This runs the mysql container and then the app container. Ensure you update the docker-compose file with the app's image.
        ```