![alt text](https://ibb.co/g7c2HnC)

# Esta es la practica de Diseño de Aplicaciones Web de la Universidad Rey Juan Carlos de Móstoles. 
En ella participamos:
- David
- Kike
- Rodrigo
- Daniel 
- Adrian Gómez de Juan

El objetivo de la practica es crear una aplicacion web lo mas parecido a como se haría ea nivel profesional. 

En nuestro caso, nos ha tocado la parte de Definiciones, que permitirá crear y gestionar conceptos y generar ejercicios partiendo de ellas. 

A esta aplicacion web deberán poder entrar 3 tipos de usuarios:
- Visitante: Será la persona que visita la web sin necesidad de logearse para poder consultar y buscar contenido.
- Estudiante: Usuario que debrá logearse con usuario y contraseña, y que tendra acceso a ciertos elementos, ademas de aparecer datos suyos. Podrá consultar y buscar contenido, ademas de responder preguntas creadas por el Docente.
- Docente: Este usuario será el que tenga control total sobre la web. Podrá crear y eliminar tanto unidades como conceptos. Ademas podrá corregir las respuesta de los Estudiantes.

Como herramienta web para llevar el control de las fases, hemos usado Trello: https://trello.com/b/KiafFItY/daw (hay que cambiarlo a publico)



# Fase 2.

![Pagina Principal](https://ibb.co/g7c2HnC)

En esta fase deberemos:
- Implementar nuestra aplicacion web con Spring Boot usando la base de datos MySQL.
- Cargar datos de ejemplo en la base de datos para probar el funcionamiento de la aplicacion.
- Se deberá paginar aquellas paginas que tengas mas de 10 elementos usando Javascript y una llamada AJAX al servidor. 
- Nuestra web deberá gestionar los usuarios usando Spring Security. Solo un usuario docente (administrador) y dos docentes que podran registrarse mediante un formulario.
- Se debe implementar un mecanismo de subida de imagenes, en la que podamos asignar una imagen a cada concepto/definición.
- Nuestra web deberá gestionar los usuarios usando Spring Security. Solo un usuario docente (administrador) y dos docentes que podran registrarse mediante un formulario.
- Cuando se produzca un error en la aplicacion, la pagina de error, deberá tener un aspecto similar al de la web.

# Navegación

![Login](https://ibb.co/6gh7g1D)

![Vista Docente](https://ibb.co/SdprVDX)

![Subida de Imagenes](https://ibb.co/1ZwXyC9)

![Éxito](https://ibb.co/2cj04F1)

![Vista Estudiante](https://ibb.co/0QLLzb8)

![Error](https://ibb.co/CKCvCWd)

![Concepto](https://ibb.co/hXcGV69)


# Entorno de desarrollo

Para poder arrancar y ejecutar nuestra aplicacion web, deberemos instala Spring Tools Suite para poder programar la aplicacion. Cuando creamos un nuevo proyecto Spring, debemos activas las dependencias Web, Mustache, JPA y MySQL, y Security. Podremos activas tambien las devtools para que se actualice instantaneamente nuestro proyecto. 

Todo esto no sería posible sin descargarnos un Server para alojar nuestra web y la base de datos. Para ello, nos hemos descargado MySQL Server y MySQL Workbench (herramienta visual de diseño y administración de bases de datos).













# web-db-security example

This is a basic web project implemented with SpringBoot. It uses the following features:
* Mustache: a web template sytem using database and security.
* Database: Based on SpringData, JPA and MySQL
* Security: Https support, users with different roles, URLs authorization based on roles and XSRF

## Environment requirements

* Java 8 (it could work with newer versions, but not tested)
* MySQL Server:
  * Accesible in localhost and 3306 port (default one).
  * Schema 'books' created
  * Root user with password 'pass'
  * You can start dockerized MySQL with the following command:

```
$ docker run --rm --name mysql -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=books mysql:5.7
```
