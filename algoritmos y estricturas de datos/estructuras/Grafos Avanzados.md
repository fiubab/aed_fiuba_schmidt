fecha: {date}
tags: #algoritmos

---
## 2. Recorridos Fundamentales en Grafos

### 2.1 Búsqueda en Profundidad (DFS - Depth-First Search)
Explora lo más profundo posible a lo largo de cada rama antes de retroceder. Se puede implementar de forma recursiva o iterativa mediante el uso explícito de una pila.

- **Complejidad Temporal:** $\mathcal{O}(V^2)$ con Matriz de Adyacencia; $\mathcal{O}(V + A)$ con Listas de Adyacencia.
- **Complejidad Espacial:** $\mathcal{O}(V)$ debido a la pila de llamadas o pila explícita.

#### Aplicaciones Avanzadas de DFS

**Test de Aciclicidad (Detección de Ciclos):**
Mantiene un estado para cada nodo: *No Visitado*, *En Recorrido Actual* (activo en la pila recursiva), y *Procesado Completamente*.
Si durante la exploración se encuentra un adyacente marcado como *En Recorrido Actual*, se detecta una arista de retroceso (*back-edge*), confirmando la presencia de un ciclo.

**Puntos de Articulación y Biconexión:**
Un punto de articulación (o vértice de corte) es un nodo cuya eliminación desconecta el grafo.
Se utiliza la función $\text{bajo}(u)$, que calcula el número de orden mínimo alcanzable desde $u$ o sus descendientes mediante aristas de retroceso:

$$\text{bajo}(u) = \min \begin{cases} \text{numero}(u) \\ \text{numero}(w) & \text{para aristas de retroceso } (u, w) \\ \text{bajo}(x) & \text{para cada hijo } x \text{ en el árbol DFS} \end{cases}$$

* **Criterio de Articulación:**
  - La raíz del árbol DFS es punto de articulación si y solo si tiene 2 o más hijos.
  - Un nodo $u$ no raíz es punto de articulación si tiene al menos un hijo $x$ tal que $\text{bajo}(x) \ge \text{numero}(u)$.

**Componentes Fuertemente Conexas (Algoritmo de Kosaraju):**
1. **Paso 1:** Ejecutar DFS en $G$ calculando el orden de finalización de cada vértice.
2. **Paso 2:** Construir el grafo transpuesto $G'$ invirtiendo la dirección de todas las aristas.
3. **Paso 3:** Ejecutar DFS en $G'$ procesando los vértices en orden decreciente según el tiempo de finalización obtenido en el Paso 1. Cada árbol resultante representa una Componente Fuertemente Conexa.

---

### 2.2 Búsqueda en Anchura (BFS - Breadth-First Search)
Explora el grafo nivel por nivel desde un nodo inicial utilizando una Cola (FIFO).

- **Complejidad Temporal:** $\mathcal{O}(V^2)$ con Matriz de Adyacencia; $\mathcal{O}(V + A)$ con Listas de Adyacencia.
- **Propiedad Clave:** En grafos sin peso, BFS encuentra el camino más corto (en número de aristas) desde la fuente a cualquier otro nodo.

---

### 2.3 Ordenamiento Topológico
Aplica exclusivamente a Grafos Dirigidos Acíclicos (DAG). Asigna un orden lineal a los vértices respetando las dependencias de precedencia (si existe arista $u \to v$, $u$ aparece antes que $v$).

- **Vía DFS (Postorden Inverso):** Al finalizar el procesamiento recursivo de un nodo, este se inserta al frente de una lista. Complejidad: $\mathcal{O}(V + A)$.
- **Vía BFS (Algoritmo de Kahn):**
  1. Calcula el grado de entrada ($\text{indegree}$) de cada vértice.
  2. Encola los nodos con $\text{indegree} = 0$.
  3. Al desencolar un nodo, lo añade a la secuencia de salida y decrementa en 1 el grado de entrada de sus adyacentes. Si algún adyacente llega a 0, se encola.

---

## 3. Problemas de Caminos Más Cortos

### 3.1 Un Solo Origen: Algoritmo de Dijkstra
Encuentra la distancia mínima desde un nodo fuente a todos los demás vértices en grafos con pesos no negativos.

- **Estrategia de Diseño:** Greedy (Voraz).
- **Funcionamiento:** Mantiene una estimación de distancia $D[v]$ para cada nodo y un conjunto $S$ de nodos procesados. Selecciona repetidamente el nodo $w \notin S$ con el menor $D[w]$, lo añade a $S$ y relaja sus aristas adyacentes:
  $$D[v] = \min(D[v], D[w] + M[w, v])$$
- **Complejidades:**
  - Implementación Básica (Arreglo + Matriz): $\mathcal{O}(V^2)$.
  - Implementación Optimizada (Listas de Adyacencia + Min-Heap / Cola de Prioridad): $\mathcal{O}((V + A) \log V)$.

---

### 3.2 Todos los Pares de Vértices: Algoritmo de Floyd-Warshall
Calcula la distancia mínima entre cualquier par de vértices en grafos dirigidos o no dirigidos. Permite pesos negativos pero no ciclos con peso total negativo.

- **Estrategia de Diseño:** Programación Dinámica.
- **Ecuación de Recurrencia:** Sea $D^{(k)}[i][j]$ la distancia más corta de $i$ a $j$ considerando solo los nodos del conjunto $\{1, 2, \dots, k\}$ como intermediarios:
  $$D^{(k)}[i][j] = \min\left( D^{(k-1)}[i][j], \, D^{(k-1)}[i][k] + D^{(k-1)}[k][j] \right)$$
- **Complejidad Temporal:** $\mathcal{O}(V^3)$ (tres bucles anidados).
- **Complejidad Espacial:** $\mathcal{O}(V^2)$.

---

### 3.3 Cerradura Transitiva: Algoritmo de Warshall
Determina si existe al menos un camino (directo o indirecto) entre cualquier par de nodos en un digrafo.

- Mantiene una matriz booleana $T$ inicializada con la matriz de adyacencia.
- **Actualización Lógica:**
  $$T[i][j] = T[i][j] \lor (T[i][k] \land T[k][j])$$
- **Complejidad:** $\mathcal{O}(V^3)$ en tiempo y $\mathcal{O}(V^2)$ en espacio.

---

## 4. Árbol Abarcador Mínimo (MST - Minimum Spanning Tree)
Dado un grafo no dirigido, conexo y ponderado, un MST es un subgrafo acíclico conexo que incluye todos los vértices del grafo original reduciendo al mínimo el peso total acumulado de las aristas ($|A'| = |V| - 1$).

### 4.1 Algoritmo de Kruskal
- **Enfoque:** Basado en selección de aristas.
- **Estrategia:**
  1. Ordenar todas las aristas de menor a mayor peso.
  2. Inicializar cada nodo en su propio conjunto disjunto (Union-Find / Disjoint Sets).
  3. Iterar sobre las aristas ordenadas; si los extremos de la arista pertenecen a conjuntos diferentes, agregar la arista al MST y unir los conjuntos (evitando ciclos).
- **Complejidad:** $\mathcal{O}(A \log A)$ por el ordenamiento de aristas. Con optimizaciones de compresión de caminos y unión por rango, las operaciones de conjuntos son casi constantes ($\mathcal{O}(\alpha(V))$).

### 4.2 Algoritmo de Prim
- **Enfoque:** Basado en expansión de nodos.
- **Estrategia:**
  1. Iniciar con un nodo arbitrario en el árbol.
  2. Mantener un registro de las aristas que conectan los nodos dentro del MST con los nodos fuera del MST.
  3. Agregar iterativamente la arista de menor peso disponible que incorpore un nuevo nodo al árbol.
- **Complejidad:**
  - Con Matriz de Adyacencia: $\mathcal{O}(V^2)$ (óptimo para grafos densos).
  - Con Listas de Adyacencia y Min-Heap: $\mathcal{O}(A \log V)$ (óptimo para grafos dispersos).

---

## 5. Redes de Flujo y Flujo Máximo

### 5.1 Conceptos Fundamentales
Una Red de Flujo es un digrafo ponderado donde cada arista $(u, v)$ representa una canalización con una capacidad $c(u, v) \ge 0$, un nodo fuente $s$ y un nodo sumidero $t$.

**Restricciones del Flujo $f(u, v)$:**
1. **Capacidad:** $0 \le f(u, v) \le c(u, v)$.
2. **Conservación de Flujo:** Para todo nodo $u \neq s, t$, el flujo entrante total es igual al flujo saliente total:
   $$\sum_{w} f(w, u) = \sum_{v} f(u, v)$$

### 5.2 Algoritmo de Ford-Fulkerson
Busca iterativamente caminos aumentantes desde la fuente $s$ hasta el sumidero $t$ en el grafo residual $G_r$.

1. Inicializar el flujo $f(u, v) = 0$ para todas las aristas.
2. Construir el grafo residual con capacidad residual $r(u, v) = c(u, v) - f(u, v)$.
3. Mientras exista un camino aumentante $P$ de $s$ a $t$ en $G_r$ (hallado mediante DFS o BFS):
   - Determinar el cuello de botella $\Delta = \min_{(u, v) \in P} r(u, v)$.
   - Aumentar el flujo a lo largo de $P$ en $\Delta$ y ajustar el flujo inverso.

> **Teorema del Flujo Máximo / Corte Mínimo:** El flujo máximo transportable equivale a la capacidad del corte de menor capacidad que separa $s$ de $t$.

---

## 7. Cuadro Comparativo de Complejidades

| Algoritmo | Categoría / Problema | Técnica de Diseño | Complejidad Temporal | Complejidad Espacial |
| :--- | :--- | :--- | :--- | :--- |
| **DFS / BFS** | Recorridos Básicos | Exploración Básica | $\mathcal{O}(V + A)$ | $\mathcal{O}(V)$ |
| **Dijkstra** | Camino Mínimo (1 Origen) | Greedy | $\mathcal{O}((V + A) \log V)$ | $\mathcal{O}(V)$ |
| **Floyd-Warshall** | Caminos Mínimos (Todos) | Programación Dinámica | $\mathcal{O}(V^3)$ | $\mathcal{O}(V^2)$ |
| **Warshall** | Cerradura Transitiva | Programación Dinámica / Lógica | $\mathcal{O}(V^3)$ | $\mathcal{O}(V^2)$ |
| **Kruskal** | Árbol Abarcador Mínimo | Greedy + Union-Find | $\mathcal{O}(A \log A)$ | $\mathcal{O}(V + A)$ |
| **Prim** | Árbol Abarcador Mínimo | Greedy | $\mathcal{O}(A \log V)$ | $\mathcal{O}(V)$ |
| **Ford-Fulkerson** | Flujo Máximo | Augmenting Paths | $\mathcal{O}(A \cdot f_{\max})$ | $\mathcal{O}(V + A)$ |
| **Backtracking (General)** | NP-Hard / Combinatoria | Búsqueda Sistemática con Poda | Exponencial $\mathcal{O}(k^N)$ | $\mathcal{O}(N)$ |