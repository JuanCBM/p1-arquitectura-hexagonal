<h1 align="center">Práctica 1. Módulo Patrones y arquitecturas de servicios de internet 👨🏻‍💻 </h1>

<p align="center">
  <a href="/docs" target="_blank">
    <img alt="Documentation" src="https://img.shields.io/badge/documentation-yes-brightgreen.svg" />
  </a>
  <a href="#" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg" />
  </a>
</p>

## Authors
👤 **JuanCBM**: Juan Carlos Blázquez Muñoz
* Github: [@JuanCBM](https://github.com/JuanCBM)

👤 **mahuerta**: Miguel Ángel Huerta Rodríguez
* Github: [@mahuerta](https://github.com/mahuerta)

Proyecto para realizar dos proyectos con arquitectura hexagonal, uno con Node y otro con Spring.
En arquitectura hexagonal existen 3 capas:
- Capa de presentación/aplicación
- Capa de dominio
- Capa de infraestructura

Entre cada capa tenemos los puertos (interfaces) y adaptadores (especialización de los puertos).

Las interfaces no deben usar objetos de ninguna de las dos capas entre las que se encuentran, sino DTOs.

# Ejecución de la aplicación:
## Proyecto Spring
Proyecto Java:

**1.** Ejecutamos el proyecto en nuestro IDE.

**2.** Utilizar la colección de postman facilitada en la raíz del proyecto para probar la aplicación.

**3.** Ejecutar los tests.

## Proyecto Node
**1.** Ejecutamos el siguiente comando para disponer de una BBDD mongo:
> docker run --name shopping-cart  -p 27017:27017 -d mongo:latest

**2.** Ejecutamos el proyecto en nuestro IDE.

**3.** Ejecutar los tests (npm test o jest test).

## Apuntes teóricos
**Modelo de 4+1 vistas**
- Lógica: Funcionalidad del sistema y requisitos funcionales. Diagramas de clases, interacción, objetos, paquetes y estado.
- Procesos: Cómo se comporta el sistema en ejecución. Requisitos no funcionales. Diagramas de secuencia, actividad, comunicación.
- Despliegue: Muestra los artefactos generados, agrupa las clases por artefactos, permite ver dónde va cada funcionalidad. Permite ver interfaces de comunicación con el entorno.
- Física: Hardware, qué nodos hay, en qué nodo se despliega cada componente, comunicación entre ellos.
- Escenarios (+): Casos de Uso.

**PATRONES SOLID:**
- Single Responsibility: Única razón para cambiar
- Open Closed: Abierto para extensión, cerrado modificación.
- Liskov: Permitir el cambio de implementaciones dependiendo de interfaces.
- Interface segregation: Depender del código que se usa
- Inversión de dependencias: No depender directamente de implemetaciones. Independizar las reglas de negocio de tecnologías concretas.

**PRINCIPIOS DE DISEÑO DE COMPONENTES:**

Principios de cohesión: Cómo son los componentes entre sí
- Principio de reutilización/ liberación: Si no saco versiones de mi componente, no lo puedo reutilizar.
- Principio cierre común: Si hay dos clases que cuando cambia una cambia la otra, es que ambas clases perteneden al mismo componente
- Principio de reutilización común: Si uso una clase muchas veces en un componente es porque esa clase pertenece a tu componente.

Principios de relación: Cómo se relacionan los componentes entre sí
- Principio de dependencias acíclicas: Evitar ciclos entre componentes. Se rompen con inversión de dependencias, a través de una interfaz.
- Principio de dependencias estables: Dependencias deben avanzar hacia componentes más estables, a no tener que cambiar.
- Principio de dependencias abstractas: Depender de abstracciones, no de implementaciones. Los componentes abstractos deben ser estables.

**ARQUITECTURA POR CAPAS:**
- Cada capa da servicio a su capa superior y utiliza los servicios de su capa inferior.
- Las peticiones se reciben en la capa superior o en la inferior y se propagan.
- Las dependencias entre componentes de una capa a otra sólo pueden ser de capas anteriores o siguientes.
- Los datos:
	- Los datos de implementación no deben ser expuestos por la capa que que es llamada.
	- La capa que llama debe tener su propio modelo de datos.
	
	---> Utilizaremos DTOs.
- Capas:
	- Presentación: Controladores
	- Lógica de negocio: Servicios y modelo
	- Persistencia o infraestructura: Repositorios

**DAO:**
Operaciones crud básicas sobre objetos de 1 tabla.

**Repository:**
El repositorio puede hacer cosas más complejas, objetos con sus relaciones, es capaz de traerse listas de objetos.

**DTO:**
POJO para obtener información de diferentes fuentes.




