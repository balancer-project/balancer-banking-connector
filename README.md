# Conector bancario de Balancer

Microservicio conector bancario de Balancer.

## Proyecto de Kotlin con Spring

Este proyecto de Kotlin hace uso de los siguientes paquetes (entre otros):

- Spring Framework
- Spring Boot
- OpenAPI Generator (plugin para Maven, tanto cliente como servidor)
- Project Reactor y Spring WebFlux
- Netty (cliente y servidor web [NIO](https://en.wikipedia.org/wiki/Non-blocking_I/O_(Java)))
- Ktlint

## Estructura multimódulo de Maven

El proyecto utiliza la funcionalidad multimódulo de Maven, estructurándose en distintos módulos, principalmente para implementar la división de la arquitectura hexagonal.

```
balancer-banking-connector/
└─ code/
   ├─ api/
   │  └─ rest-server - 📦 Servidor REST
   ├─ application - 📦🧅 Capa de aplicación
   ├─ boot - 📦 Arranque y configuración de la aplicación Spring Boot
   ├─ domain - 📦🧅 Capa de dominio
   ├─ infrastructure - 🧅 Capa de infraestructura
   │  ├─ plaid-web-client - 📦 Cliente web de la API de Plaid
   │  └─ repository - 📦 Repositorios
   └─ reports/
      └─ jacoco-report-aggregate - 📦 Análisis de cobertura de tests del código/

```

## Arranque

Para arrancar la aplicación, es necesario haber instalado las dependencias de Maven a través del siguiente comando:

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
