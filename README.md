# Apiurl - Consumo de APIs en Java

Este proyecto en Java demuestra cómo realizar peticiones HTTP (`GET` y `POST`) a servicios REST externos utilizando las librerías nativas `java.net.http` y la biblioteca `org.json` para el procesamiento de datos en formato JSON.

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java (JDK 11+)
* **IDE:** NetBeans / VS Code / IntelliJ IDEA
* **Librerías:** 
  * `java.net.http.HttpClient`
  * `org.json` (`JSONArray`, `JSONObject`)

---

## 🚀 Funcionalidades

- **Petición GET:** Consulta datos desde un endpoint público (`https://jsonplaceholder.typicode.com/posts`) procesando las respuestas en formato JSON.
- **Petición POST:** Envía datos estructurados en formato JSON con la cabecera `Content-Type: application/json`.
- **Manejo de Errores:** Validaciones contra respuestas nulas y captura de excepciones de red/I/O.

---

## 📋 Requisitos Previos

1. **Java Development Kit (JDK):** Versión 11 o superior instalada.
2. **Librería org.json:** Asegúrate de incluir el archivo `.jar` de `org.json` en las dependencias o carpeta *Libraries* de tu entorno de desarrollo.

---

## ⚙️ Instalación y Configuración

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/chaustrexp/Apiurl.git](https://github.com/chaustrexp/Apiurl.git)
