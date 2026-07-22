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

### Tipos Avanzados de Arboles

##### ABB/BST (Arbol binario de busqueda)
- Cada nodo tiene max 2 hijos (grado 2)
- a la izq los valores deben ser menores a la raiz
- a la der los valores deben ser mayores a la raiz
  ![[Pasted image 20260722164745.png]]
