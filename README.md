# Sanos-Salvos-BFF

Backend For Frontend - agrega y transforma datos de multiples microservicios para el frontend

## Puerto

8083

## Base de datos

N/A

## Endpoints disponibles

POST /api/bff/auth/login
POST /api/bff/auth/register
GET /api/bff/auth/check
GET /api/bff/animales/listar
POST /api/bff/animales/nuevo
GET /api/bff/organizaciones/listar
GET /api/bff/coincidencias/listar

## Ejecucion con Docker

docker-compose up --build

## Ejecucion manual

mvn spring-boot:run

## Tecnologias

- Java 21
- Spring Boot 3.2
- Spring Security + JWT
- PostgreSQL
- Docker
