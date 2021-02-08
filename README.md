# IBMTest
Prueba IBM

## Como ejecutar

1. Ejecutar mvn clean install sobre el proyecto
2. Acceder a la carpeta target generada
3. Ejecutar el comando java - jara IBMTest-1.0.0.jar [ARGUMENTO]

El argumento debe ser un número entero
	
	
## Ruta de salida

Los ficheros se generarán en la carpeta "Export" que se encuentra en la base del proyecto. Los documentos generados tendrán como prefijo el instante de su creación para su ordenación y evitar nombres repetidos.

## Dependencias usadas

Se han utilizado las siguientes dependencias de maven en el proyecto:
-org.apache.commons.commons-lan3:3.1 -> Dependencia para la utilización de clases de utilidades
-mysql.mysql-connector-java:8.0.16 -> Dependencia utilizada para crear la conexión con la base de datos

Además de estas dependencias, se han utilizado los plugins de maven:
-maven-compiler-plugin -> para establecer la versión 8 de java
-maven-jar-plugin y maven-dependency-plugin -> para la generación del jar


## Notas

-El proyecto se ha llevado a cabo utilizando el IDE de IntellIJ
-Se ha utilizado la base de datos online de https://www.freemysqlhosting.net/

Autor: David Sariego Fernández