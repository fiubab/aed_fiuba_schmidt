fecha: {date}
tags: #algoritmos #grafos #recursion #estructuras 

---
## Que es un grafo?
Un grafo es un conjunto de nodos conectados por aristas
![[grafo.png|221]]
El primer problema en la historia que resolvieron los grafos, es el problema de los puentes de Königsberg. El gran Euler fue quien logro resolverlo
![[Pasted image 20260608173150.png|221]]
El problema consistia en encontrar un camino que recorra todos los puentes sin pasar mas de una sola vez por el mismo puente
Mas tarde volveremos sobre este problema.
# Definiciones Aburridas

Un **GRAFO** es una pareja de conjuntos $G = (V, A)$ donde $V$ es el conjunto de vértices, y $A$ es el conjunto de aristas, este último es un conjunto de pares $(u,b)$ tal que $u, b \in V$ . Para simplificar, notaremos la arista como $ab$ 
En teoría de grafos, sólo queda lo esencial del dibujo: la forma de las aristas no son relevantes, sólo importa a qué vértices están unidas. 

Un **SUBGRAFO** es un grafo $G'$, cuyos conjuntos $(V',A')$ son subconjuntos de $(V, A) = G$ 

Un **GRAFO DIRIGIDO / ORIENTADO** es un grafo cuyas aristas tienen una orientacion $(a,b) \neq (b,a)$. Diferenciandose de un grafo no orientado que posee aristas bidireccionales $(a,b) = (b,a)$ siendo que con la existencia de $(a,b)$ no es necesario que exista $(b,a)$.

El **GRADO** de un nodo o vertice es la cantidad de aristas que llegan o salen de el

Un **Ciclo** es cuando se recorre el grafo, pasando una unica vez por cada arista. Y al finalizar el ciclo se vuelve al mismo nodo de origen. (No es necesario que se recorra todo el grafo)
El ciclo es **Hamiltoniano** si se pasa por TODOS los vertices SOLO 1 VEZ antes de volver al origen.

### Caracterizacion de grafos
	1) Simple --> 2 vertices especificos son unidos por una unica arista cualquiera
	2) No simple --> multigrafo
	3) Conexo --> si cada par (a,b) de vertices esta conectado por un camino
	4) Doblemente conexo --> si cada par (a,b) tiene almenos 2 caminos que los conectan. Osea que al eliminar cualquier vertice/nodo el grafo seguira siendo conexo
	Es posible determinar si un grafo es conexo usando un algoritmo Búsqueda en anchura (BFS) o Búsqueda en profundidad (DFS).
	5) Completos --> si todos los pares posibles estan unidos por al menos una arista
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
