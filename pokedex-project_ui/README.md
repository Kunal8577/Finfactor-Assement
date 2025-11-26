# Pokedex Search Engine

Production-ready full-stack Pokédex search UI and API.

This repository contains a React + Vite frontend and a Spring Boot backend  that integrates with PokeAPI and uses an LRU cache for fast repeated queries.

Features
- Search Pokémon by name
- Fast responses with LRU caching
- Responsive, modern UI
- Detailed Pokémon info: ID, types, abilities, stats, official artwork
- Clean backend structure: Controller → Service → DTO

Tech stack
- Backend: Spring Boot (Java 17, Spring Boot 3), Maven
- Frontend: React 18, Vite, Axios, CSS

Project layout
```
Project_challenge/
├─ pokedex_project/        # Backend (Spring Boot)
│  ├─ src/main/java/com/pokedex/pokedex_project
│  │  ├─ controller
│  │  ├─ service
│  │  ├─ model
│  │  └─ PokedexProjectApplication.java
│  ├─ src/main/resources
│  └─ pom.xml
├─ pokedex-project_ui/     # Frontend (React + Vite)
│  ├─ src/
│  ├─ public/
│  └─ package.json
└─ README.md
```

Quick start

Frontend
```powershell
cd pokedex-project_ui
npm install
npm run dev
# open http://localhost:5173
```

Backend (suggested)
```bash
cd pokedex_project
mvn clean install
mvn spring-boot:run
# open http://localhost:8080
```

Environment
- `VITE_API_BASE` — frontend env var (default: `http://localhost:8080/api`).

API (expected)
- GET /api/pokemon/{name} — returns JSON with: `id`, `name`, `height`, `weight`, `baseExperience`, `images`, `types`, `abilities`, `stats`, `moves`.

Caching (backend)
- In-memory LRU cache with configurable TTL (e.g., 10 minutes) and maximum entries.

Tests & build
- Run backend tests: `mvn test`
- Build backend JAR: `mvn clean package`
- Build frontend: `npm run build`

Notes
- If you see CORS errors, enable CORS in the backend or run a local proxy and set `VITE_API_BASE` accordingly.
- If the UI overflows on small screens, I can tune layout sizes to your viewport.

Want a runnable backend added to this repo? Reply and I will scaffold a Node/Express or Spring Boot example.
