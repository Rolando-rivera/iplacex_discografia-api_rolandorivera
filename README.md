# API REST Discográfica – Spring Boot

API REST Spring para administración de discografías favoritas de empleados de IPLACEX.

Este proyecto expone servicios REST para registrar y consultar información relacionada con artistas, álbumes y/o canciones favoritas de los empleados, utilizando **Spring Boot** y **MongoDB Atlas** como base de datos en la nube.

---

## Tecnologías utilizadas

- Java 17 (o versión compatible)
- Spring Boot
- Spring Data MongoDB
- MongoDB Atlas
- Gradle
- Docker (multi-stage build para despliegue)
- Render (Web Service para ejecutar la API)

---

## Características principales

- CRUD básico para los recursos de la discografía (por ejemplo: artistas, discos, canciones).
- Conexión remota a MongoDB Atlas mediante cadena de conexión segura.
- Despliegue en la nube usando:
  - Repositorio público en GitHub.
  - Imagen Docker multi-stage (build + runtime).
  - Web Service en Render.

---

## Autor Rolando Rivera
