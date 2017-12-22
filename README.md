# kukulkancraftsman
 
# Instalación local utilizando Yarn (Recomendada)
1. Instalar [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) desde el sitio web de Oracle. 
2. (Opcional) Instalar Maven. El proyecto tiene un Wrapper de Maven que instalar la version adecuada, sin embargo es posible instalarlo desde los paquetes binarios.
3. Instalar [Git](https://git-scm.com/) desde el sition web.
4. Instalar [Node.js](https://nodejs.org/es/) desde el sitio web (utilizar la version LTS)
5. Instalar [Yar](https://yarnpkg.com/en/docs/install) desde el sitio Web
7. Instalar Bower: `yarn global add bower`
8. Instalar Gulp: `yarn global add gulp-cli`

Nota: Si tienes problemas utilizando las herramientas anteriores de manera global, asegurate que tienes la ruta siguiente en tu variable de ambiente `PATH` la siguiente ruta `$HOME/.config/yarn/global/node_modules/.bin`

On Para Mac o Linux: export PATH="$PATH:`yarn global bin`:$HOME/.config/yarn/global/node_modules/.bin"

# Instalar paquetes con Bower

### Instalar un paquete en el archivo bower.json
`$ bower install PACKAGE --save`
### Instalar los paquetes listados en el archivo bower.json
`$ bower install`


# Para inyectar dependencias

Inyectar depedencias front-end en el codigo:
 `gulp inject`

Para generar las constantes AngularJS:
 `gulp ngconstant:dev`

Para hacer los pasos anteriores con un unico comando:
 `gulp install`
 
 
## Template key-params

#### Project

`projectName` : the name of the project

#### Entity

`entity`: Name of the entity

`entityHyphenNotation`: hyphen notation of the entity

`entityHyphenNotationPlural`: hyphen notation in plural format of the entity

`entityCamelCase`: entity in camel-case notation

`entityCamelCasePlural`: entity in camel-case plural format

`hasBlobProperties`: true if the entity has blob properties, false otherwise

`hasTimeProperties`: true if the entity has time properties, false otherwise

`hasLocalDate`: true if the entity has LocalDate properties, false otherwise

## Properties

### Entity

#### General Information

`property.name`: the name of the property in camelCase format 

`property.type`: the type of the property in camelCase format 

`property.qualifiedName`: the qualifiedName in java world 

`property.columnType`: the type of the property in the datamodel representation 

`property.literal`: true if the property is String, false otherwise 

#### For Blob o Clob

`property.blob`: true if the property is blob, false otherwise 

`property.clob`: true if the property is clob, false otherwise 

#### For numbers

`property.time`: true if the property is time (LocalDate, Instant, ZoneDateTime), false otherwise 

`property.localDate`: true if the property is localDate, false otherwise 

`property.instant`: true if the property is instant, false otherwise 

`property.zoneDateTime`: true if the property is zoneDateTime, false otherwise 

#### For numbers

`property.number`: true if the property is a Number (Integer, Long, Double, Float, BidDecimal), false otherwise 

`property.long`: true if the property is Long, false otherwise 

`property.integer`: true if the property is Integer, false otherwise 

`property.float`: true if the property is Float, false otherwise 

`property.double`: true if the property is Double, false otherwise 

`property.bigDecimal`: true if the property is bigDecimal, false otherwise 

#### Constraint

`property.hasConstraint`: true if the property is has constraints, false otherwise. 

`property.constraint.nullable`: true if the property can be null, false otherwise.

`property.constraint.primaryKey`:

`property.constraint.unique`:

`property.constraint.uniqueConstraintName`:

`property.constraint.deleteCascade`:

`property.constraint.deferrable`:

`property.constraint.initiallyDeferred`:

#### For text - Constraint

`property.constraint.minLength`:

`property.constraint.maxLength`:

`property.constraint.pattern`:

#### For Numbers (Integer, Long, Float, Double) - Constraint

`property.constraint.minNumber`:

`property.constraint.maxNumber`:

#### For Blob - Constraint

`property.constraint.minByte`:

`property.constraint.maxByte`:


### template quick reference

`property.columnType?contains("TIMESTAMP")`

`property.columnType?cap_first`

#### Create docker image

Run the following command

	mvn clean package -Pprod docker:build -DskipTests

#### Update docker image

1. In a terminal, set the environment variable **DOCKER_ID_USER** as your username in Docker Cloud.

	    export DOCKER_ID_USER="danimaniarqsoft"

2. Log in to Docker Cloud using the docker login command.

	    docker login

3. Tag your image using docker tag.

	    docker tag kukulkan $DOCKER_ID_USER/kukulkan

4. Push your image to Docker Hub using docker push (making the same replacements as in the previous step).

	    docker push $DOCKER_ID_USER/kukulkan

Check that the image you just pushed appears in Docker Cloud.

Go to Docker Cloud and navigate to the Repositories tab and confirm that your image appears in this list.
