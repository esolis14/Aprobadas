# Aprobadas

### Configuración BASE DE DATOS

1. Instalar MySQL.
2. Crear un nuevo usuario "aprobadas" con contraseña "password":
```
	mysql -u root -p
	CREATE USER 'aprobadas'@'localhost' IDENTIFIED BY 'password';
```
	
3. Crear base de datos "aprobadas":
```
	CREATE DATABASE aprobadas;
```
4. Conceder todos los privilegios al nuevo usuario:
```
	GRANT ALL PRIVILEGES ON aprobadas TO 'aprobadas'@'localhost';
```
5. Al ejecutar el proyecto, se crean las tablas en la base de datos. (Puede que haga falta añadir JDK el proyecto: descargar y seleccionar versión 11).
6. Para testear el login, crear manualmente a través de la consola de mysql dos usuarios con diferentes privilegios (comandos en archivo CreateUsers).

### Conventions

#### Pull Requests

Añadir ```Closes #<issue #>``` a la descripción del PR para que la issue correspondiente se cierre automáticamente al fusionar con master.

#### Branches

El nombre de las ramas debe incluir el tipo, el número de issue y una breve descripción siguiendo el siguiente formato:

```
<Branch type>/<issue #>_<descriptive_title>
```
Por ejemplo,
```
Features:        feat/1_formulario_login

Bug fixes:       fix/2_database_connection

Adding Tests:    test/3_login_usuarios

Chores:          chore/4_setting_up_config_file
```
#### Commits

Los mensajes de commit seguirán una sintaxis similar a las ramas:

```
<Commit type>(<issue #>):<description>
```
Por ejemplo,

```
feat(1): añadido formulario de login
```
