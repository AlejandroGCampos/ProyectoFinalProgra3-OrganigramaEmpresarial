# Arquitectura del proyecto

## Flujo general

Controller → Service → TreeAlgorithmStrategy  
Controller → Service → TreeRepository

## Módulos

### organigrama-core

Contendrá:

- DTOs comunes.
- Interfaces del motor.
- Estrategias del árbol.
- Lógica de algoritmos.

### organigrama-app

Contendrá:

- Aplicación Spring Boot.
- Controllers.
- Services.
- Repositories.
- Entities.
- Configuración.
- Persistencias.

## Tema aplicado

Gerencia
└── Jefatura TI
    └── Desarrollador

Cada nodo tendrá:

- id
- value
- parentId