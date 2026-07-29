**tags**: #algoritmos #arboles #recursion #estructuras 

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
Si en algun momento un nodo alcanza FE -2 o +2, el subarbol se declara desbalanceado y debemos balancearlo.
Para esto estan las rotaciones:
> *Una rotación cambia la raíz local de un subárbol manteniendo intactas las relaciones del BST*
##### **Rotación Simple a la Derecha (LL/RSD)**
Se aplica cuando el subárbol izquierdo está demasiado cargado ($FE = +2$) y el peso está en el hijo izquierdo ($FE_{hijo} \ge 0$).
```Plaintext
 	    Z (+2)                Y
 	  /  \                  /   \
 	 Y    T4   ------>     X     Z
    / \                   / \   / \
   X   T3                T1 T2 T3 T4
  / \
 T1  T2
```
**Mecánica:** $Y$ sube a ocupar el lugar de $Z$. $Z$ pasa a ser el hijo derecho de $Y$, y el antiguo subárbol derecho de $Y$ ($T3$) se reasigna como el nuevo hijo izquierdo de $Z$.
##### Rotación Simple a la Izquierda (RR/RSI)
Es la operación simétrica a la anterior. Se aplica cuando el subárbol derecho está demasiado cargado ($FE = -2$) y el peso está en el hijo derecho ($FE_{hijo} \le 0$).

| **Caso de Desbalance**     | **Condición**                         | **Solución Aplicada**                                   |
| -------------------------- | ------------------------------------- | ------------------------------------------------------- |
| Izquierda-Izquierda (LL)   | $FE(Nodo) = +2$ y $FE(HijoIzq) \ge 0$ | 1 Rotación Simple a la Derecha                          |
| Derecha-Derecha (RR)   | $FE(Nodo) = -2$ y $FE(HijoDer) \le 0$ | 1 Rotación Simple a la Izquierda                        |
| Izquierda-Derecha (LR) | $FE(Nodo) = +2$ y $FE(HijoIzq) < 0$   | Rotación Izquierda en el Hijo, luego Derecha en el Nodo |
| Derecha-Izquierda (RL) | $FE(Nodo) = -2$ y $FE(HijoDer) > 0$   | Rotación Derecha en el Hijo, luego Izquierda en el Nodo |

---

### Arbol B
El Árbol B viene a solucionar el cuello de botella generado por los accesos a disco (SSD o HDD).

La estrategia consiste en diseñar nodos que ocupen exactamente **una página de disco**. Cada nodo se parametriza mediante un **orden $m$**, permitiendo almacenar hasta $m - 1$ claves y $m$ hijos por nodo.

![[Pasted image 20260726181717.png]]

Al acceder a un nodo, se carga la página entera de disco a memoria en una sola operación de I/O. Dado que el árbol alcanza un grado de ramificación mucho mayor, su altura disminuye drásticamente, reduciendo en gran medida la cantidad de accesos a disco necesarios.
##### Comparativa de Altura ($N = 1.000.000$ elementos)
- **Árbol B (con orden $m = 101$):**
    Un nodo puede contener hasta 100 claves y 101 hijos.
    - Altura del Árbol B: $h \approx \log_{100}(1.000.000) = 3$.
- **Árbol Binario de Búsqueda (Rojo-Negro o AVL, con $m = 2$):**
    Cada nodo tiene a lo sumo 2 hijos.
    - Altura del árbol binario: $h \approx \log_2(1.000.000) \approx 20$.

### Propiedades e Invariantes (Orden $m$)
Un Árbol B se parametriza mediante su **orden $m$** ($m \ge 3$) que define el máximo de hijos de un nodo:
1. **Propiedad de Hijos por Nodo:**
    - Un nodo interno con $k$ claves tiene exactamente $k + 1$ hijos.
    - Todo nodo interno (excepto la raíz) tiene entre $\lceil m/2 \rceil$ y $m$ hijos.
    - La raíz, si no es una hoja, tiene al menos 2 hijos y como máximo $m$ hijos.
2. **Propiedad de Claves por Nodo:**
    - Todo nodo (excepto la raíz) contiene entre $\lceil m/2 \rceil - 1$ y $m - 1$ claves.
    - La raíz contiene entre $1$ y $m - 1$ claves.
3. **Ordenamiento Interno:**
    - Las claves dentro de cada nodo están almacenadas en orden ascendente: $k_1 < k_2 < \dots < k_n$.
4. **Rango de Subárboles:**
    - Si un nodo tiene dos claves consecutivas $k_1$ y $k_2$, el subárbol del hijo ubicado entre ellas contiene únicamente claves dentro del rango abierto $(k_1, k_2)$.
5. **Balanceo Perfecto:**
    - Todas las hojas se encuentran exactamente a la misma profundidad $h$.
### Operaciones
##### Busqueda
1. Inicia en el nodo raíz cargado en memoria.
2. Realiza búsqueda binaria (o lineal) sobre las claves del nodo actual para hallar el primer elemento $k_i \ge \text{target}$    
3. Si $k_i == \text{target}$, la clave fue encontrada.
4. Si se alcanza una hoja y no se encuentra coincidencia, la clave no existe en el árbol.
5. Si es un nodo interno, sigue el puntero al hijo $c_i$ correspondiente, realizando la lectura del nuevo bloque de disco.

##### Insercion
Siempre se realizan en los nodos hoja
1. Se desciende recursivamente hasta la hoja adecuada
2. Se inserta la nueva clave manteniendo el orden $k_1 < k_2 < \dots$
3. **División por Desbordamiento (_Split_):**
    - Si tras la inserción el nodo contiene $m$ claves (superando el máximo permitido de $m - 1$), el nodo se divide.
    - Se selecciona la clave mediana localizada en la posición $\lceil m/2 \rceil$.
    - La clave mediana **sube** al nodo padre.
    - Las claves a la izquierda de la mediana forman un nuevo nodo con $\lceil m/2 \rceil - 1$ claves; las claves a la derecha forman otro nodo.
    - Si el padre también se llena ($m$ claves), el proceso de _split_ se propaga hacia arriba. Si la raíz se divide, se crea una nueva raíz de 1 clave y la altura del árbol incrementa en 1.

##### Eliminacion
Si la clave a eliminar está en un nodo interno, se intercambia por su antecesor o sucesor en in-order (que reside en una hoja), reduciendo el problema a eliminar en una hoja.
1. Se remueve la clave del nodo hoja.
2. **Rebalanceo por Carencia (_Underflow_):**
    - Si el nodo se queda con menos de $\lceil m/2 \rceil - 1$ claves, debe restablecer sus invariantes.
    - **Redistribución (Robo):** Si un hermano adyacente (izquierdo o derecho) tiene más del mínimo de claves, se rota una clave del hermano hacia el padre y la clave separadora del padre desciende al nodo deficitario.
    - **Fusión (_Merge_):** Si ningún hermano adyacente tiene claves para prestar, se fusiona el nodo deficitario, un hermano adyacente y la clave separadora del nodo padre en un solo nodo. Esto reduce en 1 las claves del padre, lo que puede propagar la fusión hacia arriba 