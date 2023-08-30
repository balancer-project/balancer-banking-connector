# Conector bancario de Balancer

Microservicio conector bancario de Balancer.

## Proyecto de Kotlin con Spring

Este proyecto de Kotlin hace uso de los siguientes paquetes y tecnologías (entre otros):

- Spring Framework
- Spring Boot
- Project Reactor, Spring WebFlux y Netty (cliente y servidor web [NIO](https://en.wikipedia.org/wiki/Non-blocking_I/O_(Java)))
- PostgreSQL y R2DBC
- Spring Cloud Stream y RabbitMQ
- OpenAPI Generator (plugin para Maven, tanto cliente como servidor)
- Liquibase
- Wiremock
- Maven
- Ktlint
- Jacoco

## Estructura multimódulo de Maven

El proyecto utiliza la funcionalidad multimódulo de Maven, estructurándose en distintos módulos, principalmente para implementar la división de la arquitectura hexagonal.

```
balancer-banking-connector/
└─ code/
   ├─ api/ - 🧅
   │  ├─ event-listeners - 📦 Listeners de eventos
   │  └─ rest-server - 📦 Servidor REST
   ├─ application - 📦🧅 Capa de aplicación
   ├─ boot - 📦 Arranque y configuración de la aplicación Spring Boot
   ├─ domain - 📦🧅 Capa de dominio
   ├─ infrastructure - 🧅 Capa de infraestructura
   │  ├─ database - 📦 Adaptadores a base de datos
   │  ├─ event-publishers - 📦 Publicadores de eventos
   │  ├─ plaid-web-client - 📦 Cliente web de la API de Plaid
   │  └─ repository - 📦 Repositorios
   └─ reports/
      └─ jacoco-report-aggregate - 📦 Análisis de cobertura de tests del código/
```

🧅: capas relacionadas con la arquitectura de tipo hexagonal u _onion_.

## Configuración

La configuración de la aplicación se realiza a través de ficheros de propiedades YAML. Se puede modificar el fichero
`application-standalone.yaml` en `code/boot/src/main/resources`, o bien añadir un fichero `application.yaml` en la raíz
del proyecto, fuera de `code`, que sobreescribirá las propiedades del primero.

## Arranque

Antes de arrancar la aplicación es necesario disponer de un servidor de base de datos PostgreSQL y una instancia de
RabbitMQ en ejecución. Para ello, se puede utilizar Docker Compose:

```console
$ cd .deployment/docker

$ docker compose --file docker-compose.local-dev.yaml up --detach
```

Si se ha ejecutado antes la aplicación, se recomienda limpiar el proyecto:

```console
$ mvn clean
```

Ahora es necesario instalar las dependencias de Maven a través del siguiente comando:

```console
$ mvn install
```

A continuación, se puede arrancar la aplicación utilizando este comando:

```console
$ mvn spring-boot:run
```

## Despliegue

Para desplegar la aplicación, se debe empaquetar el proyecto a través de Maven package y Spring Boot repackage:

```console
$ mvn clean package spring-boot:repackage
```

Una vez empaquetado, se puede arrancar la aplicación autocontenida:

```console
$ java -jar boot/target/balancer-banking-connector-boot-0.0.1-SNAPSHOT.jar
```
