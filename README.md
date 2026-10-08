# Práctica 1 - Videoteca: De la ventana a los ficheros

Este repositorio contiene la resolución de la Práctica 1 de la asignatura de **Acceso a Datos (DAM)**. 

El proyecto consiste en implementar la capa lógica y de persistencia de un sistema de gestión para una videoteca, partiendo de una interfaz gráfica (UI) y un contrato de operaciones previamente definidos.

## 🏛️ Arquitectura del Proyecto

El desarrollo sigue un modelo de capas dividido en dos equipos:

- **Equipo A (Código Proporcionado):** 
  - **Capa 1 (UI):** Ventana gráfica desarrollada en Swing que captura las interacciones del usuario.
  - **Capa 2 (Contrato):** Interfaz Java (`ServicioVideoteca` y `Formato`) que define la frontera de comunicación entre la vista y la lógica.
- **Equipo B (Mi Trabajo):**
  - **Capa 3 (Negocio):** Implementación de las reglas en `ServicioVideotecaImpl` y `GeneradorIds`.
  - **Capa 4 (Persistencia):** Clases encargadas de leer y escribir en los distintos formatos (`GestorTextoPlano`, `GestorJSON`, `GestorXML`, `AlmacenImagenes`, `Configuracion`, etc.).
  - **Capa 5 (Ficheros):** Almacenamiento real en disco (`.txt`, `.json`, `.xml`, `.dat` y `.properties`).

## 🛠️ Decisiones Técnicas Implementadas

Para el manejo de los ficheros estructurados, se requiere elegir y programar al menos una variante para lectura y escritura. A continuación se marcan las implementaciones desarrolladas en esta práctica:

**Exportación / Importación JSON:**
- [ ] Gson
- [X] Jackson

**Exportación / Importación XML (Lectura):**
- [ ] SAX
- [X] JAXB
- [ ] DOM

**Exportación XML (Escritura):**
- [X] JAXB
- [ ] DOM

## ✅ Progreso de Tareas

- [X] **Tarea 1:** Exportar a texto plano.
- [X] **Tarea 2:** Exportar a JSON.
- [X] **Tarea 3:** Exportar a XML.
- [X] **Tarea 4:** Importar de texto plano.
- [X] **Tarea 5:** Importar de JSON.
- [X] **Tarea 6:** Importar de XML.
- [X] **Tarea 7:** Configuración y arranque (lectura de `conf.properties`).
- [X] **Tarea 8:** Gestión de imágenes (fichero de acceso directo con `imagenes.dat` e `indice.dat`).
- [X] **Tarea 9:** Creación de categorías y películas en memoria.

## ⚙️ Configuración y Ejecución

El proyecto está gestionado con **Maven**, por lo que el archivo `pom.xml` ya incluye todas las dependencias necesarias. 

**Importante para la ejecución local:**
El código asume la existencia de un directorio de configuración que **no** se sube al control de versiones. Para ejecutar el proyecto correctamente:
1. Crea una carpeta llamada `config/` en la raíz del proyecto.
2. Dentro de ella, crea el archivo `conf.properties`.
3. Asegúrate de que los archivos `imagenes.dat` e `indice.dat` (proporcionados en el material de la práctica) se encuentren en las rutas correspondientes.

*Ejemplo de `conf.properties`:*
```properties
sobrescribir=false
importacion_json=jackson
exportacion_json=jackson
importacion_xml=sax
exportacion_xml=jaxb
