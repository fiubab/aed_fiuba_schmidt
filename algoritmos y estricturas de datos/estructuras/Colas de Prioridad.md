---
fecha: 2026-07-27
tags:
  - algoritmos
---
---
## TDA Cola de Prioridad y Estructura Heap (Montículo)

### Tipo de Dato Abstracto (TDA) Cola con Prioridad
A diferencia de una cola convencional (FIFO), en una **Cola con Prioridad** cada elemento se almacena como un par ordenado $(\text{clave}, \text{prioridad})$. 

* **Prioridades:** Pueden repetirse entre diferentes elementos.
* **Comportamiento al Encolar:** Un nuevo elemento se posiciona por delante de todos aquellos elementos que tengan una prioridad inferior. Si no existen elementos con menor prioridad, se ubica al final.
* **Comportamiento al Desencolar:** Remueve y retorna siempre el elemento ubicado al frente de la cola (aquel con la mayor prioridad en Max-Heaps o menor prioridad en Min-Heaps).

#### Primitivas del TDA:
1. `CrearColaPrioridad()`: Inicializa una cola vacía.
2. `DestruirColaPrioridad()`: Libera los recursos asociados a la cola.
3. `Encolar(elemento, prioridad)`: Inserta un nuevo elemento reorganizándolo según su prioridad.
4. `Desencolar()`: Extrae el elemento prioritario del frente.

---

### Árboles Heap o Montículos
Un **Heap** (o Montículo) es un árbol binario que cumple dos propiedades fundamentales:

1. **Propiedad de Estructura (Completo o Casi Completo):** 
   * Es **completo** si todos sus niveles están totalmente poblados (la cantidad de nodos en cada nivel es una potencia de $2$).
   * Es **casi completo** si todos sus niveles están llenos excepto el último (nivel de las hojas), en el cual los nodos existentes deben estar agrupados obligatoriamente a la izquierda.
2. **Propiedad de Orden Parcial:**
   * **Max-Heap (Heap de Máximo):** El valor de cada nodo es mayor o igual que el valor de sus hijos. La raíz contiene el valor máximo.
   * **Min-Heap (Heap de Mínimo):** El valor de cada nodo es menor o igual que el valor de sus hijos. La raíz contiene el valor mínimo.
   * *Nota:* No existe una relación de orden predefinida entre los nodos hermanos del mismo nivel.

---

### Representación Secuencial en Vector / Arreglo
Gracias a su propiedad de árbol casi completo, un Heap de $n$ elementos se representa eficientemente en un arreglo contiguo de posiciones $0, 1, \dots, n-1$ almacenando los nodos por niveles de izquierda a derecha.

#### Fórmulas de Navegación por Índices:
Dado un nodo en la posición $k$:
* **Hijo Izquierdo:** $\text{izq} = 2k + 1$
* **Hijo Derecho:** $\text{der} = 2k + 2$

Dado un nodo hijo en la posición $h$:
* **Nodo Padre:** $\text{padre} = \left\lfloor \frac{h - 1}{2} \right\rfloor$ (utilizando división entera).

---

### Operaciones Fundamentales en un Heap

#### A. Alta / Inserción (`Acolar`)
1. Se inserta el nuevo elemento en la posición inmediatamente posterior a la última hoja disponible ($\text{posición } n$).
2. Se incrementa el tamaño del heap $n \to n + 1$.
3. **Flotar / Sift-Up (Reestructuración hacia arriba):** Se compara el elemento recién ingresado con su nodo padre. Si violan la propiedad de orden (ej. en un Min-Heap, si el hijo es menor que el padre), se intercambian. Este proceso se repite recursivamente o mediante un bucle hasta alcanzar la raíz o encontrar un padre ordenado.
* **Complejidad Temporal:** $\mathcal{O}(\log n)$, pues la altura máxima recorrida es $\lfloor \log_2 n \rfloor$.

#### B. Baja / Extracción de la Raíz (`Desacolar`)
1. Se remueve la raíz (elemento con la máxima o mínima prioridad).
2. Se reemplaza la raíz vacante por el elemento almacenado en la última hoja del arreglo.
3. Se decrementa el tamaño del heap $n \to n - 1$.
4. **Hundir / Sift-Down / Heapify (Reestructuración hacia abajo):** Se compara la nueva raíz con sus dos hijos. Si viola la propiedad de orden, se intercambia con el hijo de menor valor (en Min-Heap) o mayor valor (en Max-Heap). El proceso se repite descendiendo nivel por nivel hasta restablecer el orden o llegar a una hoja.
* **Complejidad Temporal:** $\mathcal{O}(\log n)$.

#### C. Modificación de Prioridad
En algoritmos voraces (como Dijkstra o Prim), se requiere actualizar la prioridad de un nodo existente. Si la prioridad mejora, el nodo se desplaza hacia arriba (**Sift-Up**) a un costo de $\mathcal{O}(\log n)$.

---

### Armado de un Heap en Tiempo Lineal ($\mathcal{O}(n)$ - Build-Heap)
Dado un arreglo desordenado de $n$ elementos, se puede transformar en un heap válido ejecutando el algoritmo `Heapify` descendente (**Sift-Down**) comenzando desde el último nodo interno (el último nodo que posee hijos, en la posición $\left\lfloor \frac{n}{2} \right\rfloor - 1$) y retrocediendo índice a índice hasta llegar a la raíz (posición $0$).

#### Demostración del Costo Lineal:
* La cantidad de nodos a una altura $h$ respecto de las hojas es $\le \left\lceil \frac{n}{2^{h+1}} \right\rceil$.
* Un nodo a altura $h$ realiza a lo sumo $h$ descendimientos.
* El costo total acumulado se expresa mediante la serie:
  $$T(n) = \sum_{h=0}^{\lfloor \log n \rfloor} \frac{n}{2^{h+1}} \cdot h = n \sum_{h=0}^{\lfloor \log n \rfloor} \frac{h}{2^{h+1}} \le n \sum_{h=0}^{\infty} \frac{h}{2^h} = \mathcal{O}(n)$$

---

### Algoritmo de Ordenamiento Heapsort
Heapsort es un método de ordenamiento in-place basado en la estructura de montículo.

#### Etapas del Algoritmo:
1. **Construcción del Heap (Fase Lineal):** Se construye un **Max-Heap** sobre el arreglo de $n$ elementos para ordenar de forma creciente (o un **Min-Heap** para orden decreciente). Tiempo: $\mathcal{O}(n)$.
2. **Extracción e Intercambio Repetido (Fase Logarítmica):**
   * Mientras el tamaño de la zona activa del heap sea mayor a $1$:
     * Se intercambia el elemento de la raíz (posición $0$, el máximo actual) con el último elemento de la zona activa ($n-1$).
     * Se reduce el tamaño activo del heap en $1$ ($n \to n - 1$), dejando el elemento máximo fijado en su posición final al final del arreglo.
     * Se restaura la propiedad de Max-Heap aplicando `Heapify` en la posición $0$ sobre el área reducida.

#### Análisis de Complejidad de Heapsort:
* **Complejidad Temporal:**
  * Construcción: $\mathcal{O}(n)$.
  * Extracción de $n-1$ elementos con `Heapify`: $(n-1) \cdot \mathcal{O}(\log n) = \mathcal{O}(n \log n)$.
  * **Total Peor / Mejor / Promedio Caso:** $\mathcal{O}(n \log n)$.
* **Complejidad Espacial:** $\mathcal{O}(1)$ (Algoritmo In-Place / No requiere memoria auxiliar proporcional).