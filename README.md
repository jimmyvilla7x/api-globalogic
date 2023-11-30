# Microservicio de Creación y Consulta de Usuarios por Token (generado previamente)

    Este proyecto es un microservicio desarrollado con Spring Boot 2.5.14 y Gradle 7.4.
    Proporciona endpoints para la creación y consulta de usuarios los cuales se pueden revisar:

# Instrucciones de Construcción y Ejecución

# 1. **Clonar el Repositorio:**
    git clone https://github.com/jimmyvilla7x/api-globalogic.git

    Ejecutar comando: ./gradlew clean build

# 2. **Levantar Proyecto**
    Opción 1: Para levantar de linea de comando se puede ejecutar lo sgte: ./gradlew bootRun
    Opción 2: Abrir con algún IDE y ejecutar el proyecto

# 3. **Probar Metódos**
    (Dejo Curl's disponible para Importar directo en postman)

    ## Metódo /sign-up

    curl --location --request POST 'http://localhost:8080/api/users/sign-up' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "email": "jim.vil@gmail.com",
    "name": "Jim",
    "password": "a2asfGfdfdf4",
    "phones": [
        {
        "citycode": 7,
        "countrycode": "25",
        "number": 87650009
        }
    ]
    }'

    ## Metódo /login (se debe reeemplazar el header de autorización por el generado previamente)

    curl --location --request GET 'http://localhost:8080/api/users/login' \
    --header 'Authorization: eyJhbGciOiJub25lIn0.eyJzdWIiOiJqaW1teS52aWxsYUBnbWFpbC5jb20iLCJpYXQiOjE3MDEyNzQ4MzEsImV4cCI6MTcwMjEzODgzMX0.'

    (Una vez consultado el token cambia por lo cual hay que usar el nuevo).

# 4. Swagger 
    En la siguiente URL se pueden ver los métodos: http://localhost:8080/swagger-ui/index.html

# 5. Base datos h2 

    En la siguiente URL se pueden ver http://localhost:8080/h2-console/login.jsp
    Usuario: sa
    Password: 123456
    (***Accesos se pueden cambiar en application properties***)

# 5. Prubas Unitarias y Coverage

    Para generar reportes se pueden ejecutar estos 2 comandos y revisar en la sgtes rutas:

    ./gradlew test
    /build/reports/tests/test/classes/com.globallogic.test.TestApplicationTests.html

    ./gradlew jacocoTestReport
    /build/reports/jacoco/test/html/com.globallogic.test.service/UserService.html


    