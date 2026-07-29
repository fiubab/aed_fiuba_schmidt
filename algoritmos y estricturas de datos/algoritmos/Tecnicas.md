fecha: {date}
tags: #algoritmos

---

## Paradigmas y Técnicas de Diseño de Algoritmos

| Técnica de Diseño         | Principio Clave        | Mecanismo                 | Ejemplos Clásicos       |
| :------------------------ | :--------------------- | :------------------------ | :---------------------- |
| **Estrategia Greedy**     | Decisiones locales     | Sin marcha atrás          | Dijkstra, Prim          |
| **Programación Dinámica** | Subproblemas solapados | Tabulación / Memorización | Floyd-Warshall, Mochila |
| **Backtracking**          | Búsqueda sistemática   | Árbol de expansión / Poda | N-Reinas, Coloreado     |

---
### Estrategia Greedy (Voraz)
* **Principio:** Toma la decisión óptima a nivel local en cada paso con la expectativa de alcanzar una solución globalmente óptima. No realiza backtracking (decisiones irreversibles).
* **Propiedades Requeridas:**
  1. **Subestructura Óptima:** La solución óptima al problema contiene soluciones óptimas a sus subproblemas.
  2. **Propiedad de Selección Voraz:** Se puede construir la solución óptima tomando decisiones locales basadas en un criterio específico sin revisar alternativas previas.
* **Ejemplos:** Algoritmos de Dijkstra, Kruskal, Prim, Problema de la Mochila Fraccionaria.

---
### Programación Dinámica (DP)
* **Principio:** Diseñada para problemas de optimización descompuestos en subproblemas que se **solapan** (se repiten en el árbol de recursión). Almacena y reutiliza los resultados previamente calculados para evitar redundancia.
* **Enfoques de Implementación:**
  * **Top-Down (Memorización):** Enfoque recursivo con almacenamiento de resultados en una tabla o diccionario.
  * **Bottom-Up (Tabulación):** Enfoque iterativo que resuelve primero los subproblemas más pequeños y construye progresivamente la tabla.
* **Ejemplos:** Algoritmo de Floyd-Warshall, Alineamiento de Secuencias (Edit Distance), Mochila 0/1, Subsecuencia Común Más Larga (LCS).

---
### Backtracking (Vuelta Atrás)
* **Principio:** Método general de exploración exhaustiva y sistemática del espacio de soluciones (expresado implícitamente como un **árbol de decisión/expansión**). Utiliza un recorrido en profundidad (DFS) y aplica **poda (pruning)** para descartar ramas parciales inaccesibles o no válidas.
* **Mecanismo:**
  1. **Elección:** Selecciona una opción para el siguiente componente de la solución $x_k$.
  2. **Validación:** Verifica si la solución parcial $[x_1, x_2, \dots, x_k]$ cumple con las restricciones.
  3. **Avanzar o Retroceder:** Si es válida, avanza a la siguiente etapa; si conduce a un estado inválido (*nodo fracaso*), retrocede deshaciendo la última elección.
* **Aplicaciones Clásicas en Grafos y Combinatoria:**
  * **Coloreado de Grafos (Número Cromático):** Asignar $m$ colores a los vértices de modo que no haya adyacentes del mismo color.
  * **Ciclos Hamiltonianos (Problema del Viajante de Comercio):** Encontrar un recorrido cerrado que visite cada nodo exactamente una vez.
  * **Resolución de Laberintos y Tableros:** Problema de las $N$-Reinas, Sudoku, Salida de Laberintos.

---
