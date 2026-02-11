#  Escuderías Unidas  
Sistema de Gestión de Escuderías y Competencias de Fórmula 1  

---

##  Descripción

**Escuderías Unidas** es una aplicación desarrollada en Java que modela la gestión integral de escuderías, pilotos, contratos, autos y competencias de Fórmula 1.

El sistema permite administrar entidades del dominio, planificar carreras, registrar resultados oficiales y generar informes estadísticos, aplicando una arquitectura basada en el patrón **MVC (Modelo - Vista - Controlador)**.

El proyecto comenzó como un trabajo académico y fue posteriormente refactorizado y ampliado como proyecto personal, con el objetivo de aplicar principios de arquitectura, separación de responsabilidades y diseño orientado al dominio.

---

##  Tecnologías Utilizadas

- Java
- Swing (JFrame, JPanel, JTable, CardLayout)
- Programación Orientada a Objetos
- MySQL (modelo de base de datos diseñado)
- Arquitectura MVC

---

##  Arquitectura Actual

El sistema está estructurado en tres capas principales:

###  Modelo

Contiene:

- Entidades principales: `Piloto`, `Escuderia`, `Auto`, `Carrera`, `Contrato`, `Participacion`, etc.
- Reglas de negocio
- Validaciones de consistencia
- Estructura centralizada mediante `RegistroGeneral`

El modelo concentra la lógica interna del sistema y garantiza la coherencia del dominio.

---

###  Vista

- Implementada con Swing.
- Navegación centralizada mediante `VentanaPrincipal`.
- Uso de `CardLayout` para alternar entre pantallas.
- Cada módulo funcional se encuentra en un `JPanel` independiente.

---

###  Controlador

- Intermedia entre la vista y el modelo.
- Captura eventos de la interfaz gráfica.
- Valida entradas de usuario.
- Ejecuta operaciones sobre el modelo.

---

##  Estado Actual del Proyecto

Actualmente:

- ✔ La aplicación funciona completamente en modo escritorio.
- ✔ El modelo de dominio está implementado.
- ✔ Las reglas de negocio están definidas y aplicadas.
- ✔ El diseño de la base de datos (DER) está realizado.
- ⏳ La integración con base de datos mediante JDBC está en desarrollo.
- ⏳ Se encuentra en proceso la incorporación de una capa de persistencia (DAO / Repository).
- ⏳ Se planifica separar la lógica de negocio en una capa de servicios independiente.

---

##  Reglas de Negocio Implementadas

###  Reglas de Unicidad

- No pueden existir dos países con la misma descripción.
- No pueden existir dos escuderías con el mismo nombre.
- No pueden existir dos personas con el mismo DNI.
- El número de competencia de un piloto es único.

---

###  Reglas Temporales

- Un contrato debe tener fecha de inicio anterior a la fecha de finalización.
- Un piloto solo puede participar en una carrera si su contrato está vigente.
- No pueden existir contratos superpuestos para un mismo piloto.

---

###  Reglas de Disponibilidad

- Un piloto no puede participar en más de una carrera en la misma fecha y hora.
- Un auto no puede asignarse a más de un piloto en una misma carrera.
- Una carrera no puede tener más de 20 participantes.

---

###  Reglas de Resultados

- No pueden existir dos pilotos con la misma posición.
- Los puntos se asignan automáticamente según la posición.
- Un piloto solo puede tener un resultado por carrera.

---

##  Informes Disponibles

- Resultados por rango de fechas.
- Ranking de pilotos.
- Historial de podios.
- Autos por escudería.
- Mecánicos por escudería.
- Participaciones por circuito.

---

## Próxima Evolución Arquitectónica

El siguiente paso del proyecto es:

- Implementar persistencia real mediante JDBC.
- Crear interfaces DAO para desacoplar la base de datos.
- Incorporar una capa de servicios para centralizar reglas de negocio.
- Convertir la base de datos en la fuente única de verdad.
- Agregar restricciones de integridad (FK, UNIQUE) alineadas con las reglas del dominio.

---

## Diagramas

Las imágenes del Diagrama Entidad-Relación (DER) y del Diagrama Funcional se encuentran en la carpeta `/docs`.

Ejemplo de inclusión en el README:

```markdown
![DER](docs/der.png)
![Diagrama Funcional](docs/funcional.png)
