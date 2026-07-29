fecha: {date}
tags: #algoritmos #grafos #recursion #estructuras 

---

El primer problema en la historia que resolvieron con grafos, fue el problema de los puentes de Königsberg. El gran Euler fue quien logro resolverlo
![[Pasted image 20260608173150.png|221]]
El problema consistia en encontrar un camino que recorra todos los puentes sin pasar mas de una sola vez por el mismo puente
Mas tarde volveremos sobre este problema.

---

**GRAFO:** Pareja de conjuntos $G = (V, A)$ donde $V$ es el conjunto de vértices, y $A$ es el conjunto de aristas, $A$ es un conjunto de pares $(u,b)$ tal que $u, b \in V$.
En teoría de grafos, sólo queda lo esencial del dibujo: la forma de las aristas no son relevantes, sólo importa a qué vértices están unidas. 
Un **SUBGRAFO** es un grafo $G'$, cuyos conjuntos $(V',A')$ son subconjuntos de $(V, A) = G$ 

**GRAFO DIRIGIDO / ORIENTADO:** Las aristas tienen una orientacion => $(a,b) \neq (b,a)$. Diferenciandose de un grafo no orientado que posee aristas bidireccionales $(a,b) = (b,a)$ siendo que con la existencia de $(a,b)$ no es necesaria $(b,a)$.

Un **GRAFO PONDERADO / CON PESO:** Cada arista tiene asociado un valor numérico o peso $w(u, v)$ que representa costos, distancias, capacidades o tiempos.

El **GRADO** de un nodo o vertice es la cantidad de aristas que llegan o salen de el

Un **Ciclo** es cuando se recorre el grafo, pasando una unica vez por cada arista. Y al finalizar el ciclo se vuelve al mismo nodo de origen. (No es necesario que se recorra todo el grafo)
El ciclo es **Hamiltoniano** si se pasa por TODOS los vertices SOLO 1 VEZ antes de volver al origen.

## Conceptos Clave de Topología
- **Adyacencia:** Se dice que un nodo $v$ es adyacente a $u$ si existe una arista $(u, v) \in A$.
- **Camino:** Secuencia de vértices $v_1, v_2, \dots, v_n$ tal que $(v_i, v_{i+1}) \in A$ para todo $1 \le i < n$.
- **Recorrido:** Es un camino que **NO repite aristas** (aunque sí puede volver a pasar por el mismo vértice).
- **Longitud de un camino:** Cantidad total de aristas que conforman el camino (o la suma de sus pesos en grafos ponderados).
- **Ciclo:** Camino donde el nodo inicial y el final coinciden ($v_1 = v_n$), y los vértices intermedios son distintos.
- **Conexidad:**
  - **Grafo no dirigido conexo:** Existe al menos un camino entre cualquier par de vértices.
  - **Grafo dirigido fuertemente conexo:** Existe un camino dirigido en ambas direcciones entre cualquier par de vértices.
  - **Grafo dirigido débilmente conexo:** El grafo subyacente (reemplazando arcos por aristas no dirigidas) es conexo.
- **Arbol libre:** Grafo no dirigido, conexo y acíclico. Para $n$ vértices, contiene exactamente $n - 1$ aristas.
- **Arbol generador:** subgrafo que conecta todos los vértices de un grafo original sin formar ciclos.

---

## Caracterización de Grafos
1. **Simple:** Dos vértices específicos están unidos por una única arista.
2. **No simple (Multigrafo):** Existen múltiples aristas entre los mismos vértices o bucles/lazos.
3. **Conexo:** Cada par de vértices $(a, b)$ está conectado por al menos un camino.
4. **Doblemente conexo (Biconexo):** Cada par de vértices $(a, b)$ tiene al menos dos caminos independientes que los conectan. Esto implica que al eliminar cualquier vértice/nodo, el grafo seguirá siendo conexo.
5. **Completo:** Todos los pares posibles de vértices están unidos por una arista.
> [!tip] Algoritmos de verificación
Es posible determinar si un grafo es conexo utilizando algoritmos de **Búsqueda en Anchura (BFS)** o **Búsqueda en Profundidad (DFS)**.
###### Especiales
- Bipartitos:
		Cumplen que
		1. Puede expresarse como  $G = (V1 \cup V2, A)$
		2. $V1$ y $V2$ son no vacios
		3. Cada $a \in A$ contiene un vertice de $V1$ y uno de $V2$
		4. No existen aristas que unan vertices del mismo conjunto
## Como lo representamos?
Tenemos 3 formas (son las mas comunes y usadas):
1) **Lista de adyacencia**
	Lista o hashmap de size ($V$ = cant de vertices) donde cada indice es un vertice y tiene como valor los vertices adyacentes a el.
	 `Es  conveniente usarla en casi todo escenario, pero brilla para recorrer el grafo y en grafos dispersos`
2) **Matriz de adyacencia**
   Matriz cuadrada $V \times V$ ($V =$ conjunto de vertices). Cada fila y columna representan los vertices. Si 2 $(Vi,Vj)$ estan conectados la celda $matriz[i][j]$ tendra un 1 o el peso de la arista, sino un 0.
   `Usarla con grafos densos (cantidad de aristas cerca del maximo posible), para busquedas instantaneas de relaciones entre vertices y para grafos chicos (< 1000 vertices). 
   ==No usar para grafos con muchos nodos==
3) **Lista de aristas**
   Un array con cada par de nodos conectados y/o su peso $(v1,v2)$ o $(v1,v2, peso)$
   `Usarla solo cuando el algoritmo itera sobre todas las aristas sin importar los nodos`
# Como recorrerlos?
Las formas para recorrer un grafo son 2:
- #### En profundidad (DFS depth first search)
	1. Se marcan todos los nodos como no visitados
	2. Empiezo por X nodo y lo marco como visitado
	3. Sigo con sus nodos adyacentes no visitados (2,3,4)
	4. Si ya visite todos sus adyacentes voy a buscar al nodo anterior
	Asi hasta llegar a un nodo que no tenga adyacentes no visitados
	`Los vecinos son apilados para poder desapilar al querer volver atras cuando un nodo no tiene vecinos no visitados. Por lo general se utiliza como pila las llamadas recursivas`  
- #### En anchura (BFS Breadth first search)
	1. Se marcan todos los nodos como no visitados
	2. Empiezo por X nodo y lo marco como visitado
	3. Luego marco a cada nodo adyacente a X como visitado 
	4. Continuo con los adyacentes del primer adyacente a X, luego los del segundo...
	Asi hasta llegar a un nodo que no tenga adyacentes no visitados
	`Los vecinos son encolados para poder ir recorriendo en orden los vecinos del nodo visitado`

```python
grafo = {
    'A': ['B', 'C'],
    'B': ['A', 'D', 'E'],
    'C': ['A', 'F'],
    'D': ['B'],
    'E': ['B', 'F'],
    'F': ['C', 'E']
}

from collections import deque

def bfs(grafo, nodo_inicio):
    visitados = set()
    cola = deque([nodo_inicio])
    visitados.add(nodo_inicio) # Lo marcamos al entrar a la cola
    
    recorrido = []
    
    while cola:
        # 1. Sacamos el primero de la cola (FIFO)
        actual = cola.popleft()
        recorrido.append(actual)
        
        # 2. Exploramos sus vecinos inmediatos
        for vecino in grafo[actual]:
            if vecino not in visitados:
                visitados.add(vecino)
                cola.append(vecino)
                
    return recorrido

print("BFS:", bfs(grafo, 'A')) 
# Salida: ['A', 'B', 'C', 'D', 'E', 'F']


def dfs(grafo, nodo_actual, visitados=None, recorrido=None):
    # Inicializamos el estado en la primera llamada
    if visitados is None:
        visitados = set()
        recorrido = []
        
    # 1. Marcamos y procesamos el nodo actual
    visitados.add(nodo_actual)
    recorrido.append(nodo_actual)
    
    # 2. Profundizamos recursivamente en cada vecino disponible
    for vecino in grafo[nodo_actual]:
        if vecino not in visitados:
            dfs(grafo, vecino, visitados, recorrido)
            
    return recorrido

print("DFS:", dfs(grafo, 'A'))
# Salida: ['A', 'B', 'D', 'E', 'F', 'C']
```
