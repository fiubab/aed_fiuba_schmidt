tags: #algoritmos #arboles #recursion #estructuras 

---
Estructura de datos que representa los datos en un orden jerarquico. Es posible tener mas de un elemento inmediatamente siguiente. No sigue un orden lineal como un array.

Sus componentes son:
- **Raiz** --> es el primer nodo (no tiene padre)
- **Hoja** --> nodo sin hijos
- **SubArbol** --> un nodo X junto con sus hijos
- **Grado** --> numero de hijos que tiene la rama mas larga del arbol
- **Altura de un nodo** --> Long del camino mas largo del nodo a la hoja
![[Pasted image 20260722164116.png|418]]
---
### ABB/BST (Arbol binario de busqueda)
- Cada nodo tiene max 2 hijos (grado 2)
- a la izq los valores deben ser menores a la raiz
- a la der los valores deben ser mayores a la raiz
  ![[Pasted image 20260722164745.png]]

### AVL
Es un BST pero se diferencia de este con que siempre esta balanceado.
Esto se consigue asignandole a cada **nodo $N$** una propiedad $altura$ y asi definimos un factor de equilibrio $$FE(N) = \text{altura}(\text{subárbol izquierdo}) - \text{altura}(\text{subárbol derecho})$$
Un árbol mantiene la propiedad AVL si y solo si, para **cada nodo** $N$ del árbol:
$$FE(N) \in \{-1, 0, 1\}$$
Si en algun momento un nodo alcanza FE -2 o +2, el subarbol se declara desbalanceado y debemos reestructurarlo.
Para esto estan las rotaciones:
`Una rotación cambia la raíz local de un subárbol manteniendo intactas las relaciones del BST`
- #### **Rotación Simple a la Derecha (LL)**
  Se aplica cuando el subárbol izquierdo está demasiado cargado ($FE = +2$) y el peso está en el hijo izquierdo ($FE_{hijo} \ge 0$).
	```Plaintext
		   Z (+2)                 Y
	     /  \                  /   \
	    Y    T4   ------>     X     Z
	   / \                   / \   / \
	   X   T3                T1 T2 T3 T4
	  / \
	 T1  T2
	```
	**Mecánica:** $Y$ sube a ocupar el lugar de $Z$. $Z$ pasa a ser el hijo derecho de $Y$, y el antiguo subárbol derecho de $Y$ ($T3$) se reasigna como el nuevo hijo izquierdo de $Z$.

- #### Rotación Simple a la Izquierda (RR)
	Es la operación simétrica a la anterior. Se aplica cuando el subárbol derecho está demasiado cargado ($FE = -2$) y el peso está en el hijo derecho ($FE_{hijo} \le 0$).
---
