# ProyectoFinalProgra3-OrganigramaEmpresarial
## Organigrama Empresarial

Sistema backend en Spring Boot para gestionar una estructura jerárquica tipo árbol, aplicada al caso de uso de un organigrama empresarial.

Ejemplo base:
Gerencia > Jefatura TI > Desarrollador

## Arquitectura general
El proyecto se organizará en dos módulos principales:

- `organigrama-core`: contratos, DTOs, interfaces y lógica del motor de árboles.
- `organigrama-app`: aplicación Spring Boot, controladores, servicios, repositorios y configuración de persistencias.

## Estrategias del motor
- `custom`: árbol implementado con estructura propia.
- `collections`: árbol implementado usando Collections del JDK.

Selector esperado:
```properties
app.tree-strategy=collections
```

```properties
app.tree-strategy=custom
```

## Persistencias
- `memory`
- `postgres`
- `mongo` o `neo4j`

Selector esperado:
```properties
app.storage=postgres
```

## Reparto del equipo

| Integrante | Responsabilidad |
|----------|------------------|
| A | Memoria, motor custom, OpenAPI y esqueleto multimódulo |
| B | PostgreSQL, motor collections, modelo ER, scripts SQL y datos de prueba |
| C | MongoDB/Neo4j, configuración, selectores y frontend |

## Flujo de trabajo
- `main` protegida.
- No se trabaja directo sobre `main`.
- Todo cambio entra por Pull Request.
- Ramas por feature.
- Mínimo 3 commits significativos por integrante por semana.
- Cada PR debe incluir descripción, evidencia y enlace a tarjeta de Trello.
