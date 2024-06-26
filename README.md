# Composition-Manager

Back end uses Spring Boot, object oriented programming concepts to create and manage compositions and shapes.
In-memory H2 database is used.

Front end uses Angular and fabric js canvas for the shape drawing.
It consumes spring boot backend to persist the shapes and compositions in the in memory database.

## Instruction to run

### Back end
mvn spring-boot:run

### Front end
npm start