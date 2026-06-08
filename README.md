# Sanos-Salvos-BFF

Microservicio **Backend For Frontend (BFF)** encargado de centralizar y orquestar la comunicación entre el Frontend y los microservicios del ecosistema *Sanos y Salvos*.

---
# Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- Java JDK 17
- Apache Maven 3.8+
- IntelliJ IDEA (opcional)
- Postman o Insomnia

---
# Instalación
## 1. Clonar el repositorio

```bash
git clone https://github.com/TU_USUARIO/Sanos-Salvos-BFF.git
cd Sanos-Salvos-BFF
```

## 2. Instalar dependencias

### Linux / Mac

```bash
mvn clean install
```

### Windows (sin Maven global)

```powershell
.\mvnw clean install
```

Si todo salió correctamente aparecerá:

```bash
BUILD SUCCESS
```

---

# Configuración

Verifica el archivo:

```bash
src/main/resources/application.properties
```

Configuración básica:

```properties
server.port=8083
spring.application.name=sanosysalvos-bff

spring.mvc.cors.allowed-origins=http://localhost:8080,http://localhost:3000
spring.mvc.cors.allowed-methods=GET,POST,PUT,DELETE,OPTIONS
spring.mvc.cors.allowed-headers=*

microservicio.organizaciones.url=http://localhost:8081/api/organizaciones
microservicio.coincidencias.url=http://localhost:8084/api/coincidencias
microservicio.pet.url=http://localhost:8082/api/pet
microservicio.geolocalizacion.url=http://localhost:8085/api/geolocalizacion
microservicio.usuarios.url=http://localhost:8087/api/usuario
microservicio.logins.url=http://localhost:8089/api/login
microservicio.notificaciones.url=http://localhost:8086/api/notificacion
microservicio.auth.url=http://localhost:8088/api/auth
```

---

# Ejecución

## Desde IntelliJ IDEA

1. Abrir el proyecto.
2. Buscar:

```bash
BffApplication.java
```

3. Ejecutar la clase con el botón **Run ▶**.

---

## Desde consola

```bash
.\mvn spring-boot:run
```

El servicio quedará disponible en:

```bash
http://localhost:8083
```

---

# Pruebas de API

## Autenticación JWT

Antes de consumir endpoints protegidos:

1. Inicia sesión en el microservicio de autenticación.
2. Copia el token JWT recibido.
3. En Postman:
    - Authorization
    - Bearer Token
    - Pegar token

---

# Endpoint: Dashboard Principal

Obtiene información consolidada desde múltiples microservicios.

## GET

### Vía API Gateway

```http
GET http://localhost:8080/api/bff/main/dashboard?usuarioId=1&organizacionId=1
```

### Directo al BFF

```http
GET http://localhost:8083/api/bff/main/dashboard?usuarioId=1&organizacionId=1
```

## Respuesta esperada

```json
{
  "mascotas": [],
  "alertas": [],
  "notificaciones": []
}
```

---

# Endpoint: Registrar Mascota

Registra una mascota utilizando orquestación mediante OpenFeign.

## POST

```http
POST http://localhost:8080/api/bff/animales/nuevo
```

## Body

```json
{
  "nombre": "Firulais",
  "especie": "PERRO",
  "raza": "Mestizo",
  "estado": "PERDIDO",
  "organizacionId": 1
}
```

## Respuesta esperada

```json
{
  "id": 1,
  "nombre": "Firulais",
  "estado": "PERDIDO"
}
```

---

# Arquitectura

```text
Frontend (React :3000)
        ↓
API Gateway (:8080)
        ↓
BFF (:8083)
        ↓
Microservicios internos
```

El BFF utiliza:

- Spring Boot
- Spring Cloud OpenFeign
- JWT Authentication
- Arquitectura de Microservicios
- API Gateway Pattern
- Backend For Frontend Pattern