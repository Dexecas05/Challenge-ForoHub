# ForoHub

ForoHub es una API REST diseñada para gestionar un foro en línea, proporcionando funcionalidades CRUD (Crear, Leer, Actualizar, Eliminar) para tópicos. 
Además, cuenta con autenticación y autorización utilizando JWT (Jason Web Token) para garantizar la seguridad de la aplicación.

## Tecnologías Utilizadas

  ### Software
  
    - **Java 17**
    - **Spring Boot**: versión 3 en adelante.
    - **Maven**: versión 4 en adelante.
    - **MySQL**: versión 8 en adelante.
    - **IDE IntelliJ IDEA**: opcional, pueder ser Eclipse u otro.
    - **Insomnia**
    
  ### Dependencias

    - **Spring Security**
    - **Spring Data JPA**
    - **Spring Boot DevTools**
    - **Spring Web**
    - **MySQL Driver**
    - **Lombok**
    - **Validation**
    - **Flyway Migration
    - **Auth0 (JWT - JSON Web Token)**
    - **Springdoc OpenAPI (Swagger)** 

## Características

- **Autenticación y Autorización:** Los usuarios deben autenticarse para interactuar con la API utilizando JWT.
- **CRUD de Tópicos:** Funcionalidades para crear, listar, actualizar y eliminar tópicos.
- **Documentación:** La API está documentada utilizando Springdoc OpenAPI (Swagger).

## Instalación

  ### Prerrequisitos

- Java 17
- Maven
- MySQL (opcional, para producción)

  ### Configuración

1. Clona el repositorio:

   ```sh
   git clone https://github.com/Dexecas05/Challenge-ForoHub.git
   cd Challenge-ForoHub
   ```

2. Configura las variables de entorno para la base de datos:

   En un archivo `.env` en la raíz del proyecto, añade las siguientes variables:

   ```plaintext
   MSQL_URL=jdbc:mysql://localhost:3306/forohub
   MSQL_USERNAME=tu_usuario
   MSQL_PASSWORD=tu_contraseña
   ```

3. Construye y ejecuta la aplicación:

   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## Uso

  ### Autenticación

1. Regístrate como usuario (puedes insertar un usuario directamente en la base de datos para simplificar).
2. Autentícate mediante el endpoint `/login` con las credenciales correctas para obtener un token JWT.
3. Usa el token JWT para acceder a los demás endpoints protegidos por seguridad.

  ### Documentación de la API

La documentación de la API está disponible en: `http://localhost:8080/swagger-ui.html` 
También puedes ver la descripción de OpenAPI, en formato Json, en: `http://localhost:8080/v3/api-docs` 

  ### Endpoints Principales

- **POST /login**: Autenticar usuario y obtener token JWT.
- **POST /topicos**: Crear un nuevo tópico.
- **GET /topicos**: Listar todos los tópicos (con paginación).
- **GET /topicos/{id}**: Obtener detalles de un tópico por ID.
- **GET /topicos/primeros10**: Obtener una lista con los tópicos más recientes.
- **PUT /topicos/{id}**: Actualizar un tópico por ID.
- **DELETE /topicos/{id}**: Eliminar un tópico por ID.

## Contribuciones

¡Las contribuciones son bienvenidas! Por favor, abre un issue o un pull request para discutir cualquier cambio importante antes de realizarlo.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo `LICENSE` para obtener más información.

## Contacto

Desarrollado por [Exequiel Castillo](mailto:execas.dec@gmail.com)
