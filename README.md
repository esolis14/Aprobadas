# Aprobadas

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
