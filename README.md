Pokedex Search Engine

This project is a Pokémon search engine built with a React + Vite frontend and a Spring Boot
backend.
It integrates with PokeAPI and uses an LRU cache to deliver fast responses for repeated searches.
Features:

- Search Pokémon by name
- Fast responses with in-memory LRU caching
- Responsive and modern UI
- Detailed Pokémon data (ID, types, abilities, stats, official artwork)
- Clean backend architecture: Controller → Service → DTO

Tech Stack:
Backend: Spring Boot (Java 17, Spring Boot 3), Maven
Frontend: React 18, Vite, Axios, CSS
Frontend Setup:
1. cd pokedex-project_ui
2. npm install
3. npm run dev
4. Open http://localhost:5173
Env: VITE_API_BASE (default: http://localhost:8080/api)

Backend Setup:
1. cd pokedex_project
2. mvn clean install
3. mvn spring-boot:run
4. Open http://localhost:8080

API:
GET /api/pokemon/{name}
Returns id, name, height, weight, baseExperience, images, types, abilities, stats, moves.

Caching:
In-memory LRU cache with configurable TTL and size.

Build JAR:
mvn clean package
java -jar pokedex_project-0.0.1-SNAPSHOT.jar
