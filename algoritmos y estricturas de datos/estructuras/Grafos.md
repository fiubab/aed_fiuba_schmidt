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

## Como lo representamos los gordos compu?
Las formas mas comunes son listas y/o matrices, dependiendo del algoritmo que usara el grafo y el tipo de problema. 
Listas --> - velocidad - uso de memoria
Matrices --> + velocidad + uso de memoria

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
		2. V1 y V2 son no vacios
		3. Cada $a \in A$ contiene un vertice de $V1$ y uno de $V2$
		4. No existen aristas que unan vertices del mismo conjunto


# Como recorrer los grafos?
Las formas mas utilizadas para recorrer un grafo son en profundidad o en ancho
Profundidad (DFS depth first search):
	