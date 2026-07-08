how to fecha: {date}
tags: #algoritmos #recursion #complejidad #complejidad 

---

Los algoritmos recursivos dan origen a expresiones de recurrencia al calcular la complejidad de los mismos.

Las formas de resolucion que veremos son:
- metodo de expansion
- resolucion de eq de recurrencia (No en este curso)
- usando algun teorema (Maestro)

Pero primero debemos traducir el codigo a una ecuacion de recurrencia para poder evaluarlo.

## Ecuacion de recurrencia
Esto se hace de la sig manera:
- n -> valor de entrada
- caso base -> complejidad del caso que corta la recursion
- f(n) -> complejidad de las operaciones previas a la llamada recursiva
- recursion -> a (cant de llamadas) * como reducimos el problema

```python
def factorial(n):
    if n <= 1:
        return 1
    return n * factorial(n - 1)
```
Analisis
- n: int n
- f(n) trabajo local: evaluar el if + multiplicar n por un numero. O(1)
- recursion: a = 1, n pasa a ser n - 1. T(n - 1)
- Eq final: $T(n) = T(n - 1) + O(1)$

```python
def binary_search
(
	buscado: int, lista: list, 
	inicio: int, fin: int 
) -> int:
    media: int = (inicio + fin) // 2
    
    if (lista[media] == buscado): 
        return media

    if (buscado < lista[media]):
        return bb_recursiva(buscado, lista, inicio, media - 1)
    else:
        return bb_recursiva(buscado, lista, media + 1, fin)
```

- n: tamano de la lista
- f(n): O(1)
- recursion: a = 1 (por el if else), n pasa a n/2 pues descartamos la mitad del array en cada llamada recursiva. T(n/2) 
- Eq final: T(n) = T(n/2) + O(1)

```python
def merge_sort(lista):
    if len(lista) <= 1: return lista
    
    mitad = len(lista) // 2
    mitad_izq = merge_sort(lista[:mitad])
    mitad_der = merge_sort(lista[mitad:])
    
    return mezclar_listas(mitad_izq, mitad_der)
```

- n: lista.size
- f(n): mezclar_listas recorre las 2 mitades de la lista. O(n)
- recursion: a = 2, pues siempre se llama 2 veces. y n pasa a ser n/2. 2T(n/2)
- Eq final: T(n) = O(n) + 2T(n/2)

Ahora bien, todo muy lindo. Pero como se que complejidad tiene realmente el algoritmo usando la notacion big O?

# De eq a big O
#### > Teorema maestro
El metodo mas rapido y directo en los problemas "divide and conquer" con ecuaciones de este estilo: $$T(n) = aT(n/b) + O(n^d)$$
1. Identifica las variables:
    - $a$: Número de subproblemas (llamadas recursivas).
    - $b$: Factor por el que se divide el problema ($n/b$).
    - $d$: Exponente del trabajo local fuera de la recursión ($n^d$).
2. Calcula el valor crítico: $c = \log_b(a)$
3. Compara $c$ y $d$:
    - **Caso 1 (**$c > d$**):** El trabajo se concentra en las hojas. **Complejidad:** $O(n^c)$
    - **Caso 2 (**$c = d$**):** El trabajo está equilibrado por nivel. **Complejidad:** $O(n^d \log n)$
    - **Caso 3 (**$c < d$**):** El trabajo local en la raíz domina. **Complejidad:** $O(n^d)$

###### Teorema Maestro por Sustracción
$$T(n) = aT(n-b) + O(n^k)$$
1. Aplica la regla según el valor de $a$:    
    - **Si** $a < 1$**:** La complejidad dominada por el trabajo local es $O(n^k)$.
    - **Si** $a = 1$**:** La complejidad polinómica es $O(n^{k+1})$.
    - **Si** $a > 1$**:** La complejidad es exponencial. En su forma de cota superior, se expresa como $O(n^k \cdot a^{n/b})$. _(Nota: La cota ajustada exacta es_ $\Theta(a^{n/b})$_)._


#### > Expansion / Sustitucion
El mejor caso de uso es cuando la recurrencia es de este estilo: $$T(n) = aT(n - b) + O(n^k)$$Lo que hacemos es expandir la recurrencia hasta poder generalizarla a una formula para luego sustituir en la original.
- **Ejemplo Costo Lineal ($T(n) = T(n-1) + c$)**
- Paso 1: Escribir la ecuación y su primera expansión.  
	Ecuación original: $T(n) = T(n-1) + c$. Ahora reemplazo n por (n-1)  
	Sabemos que $T(n-1) = T(n-2) + c$. Sustituimos esto en la original: $T(n) = [T(n-2) + c] + c = \mathbf{T(n-2) + 2c}$
- Paso 2: Expandir nuevamente para confirmar el patrón.
	Sabemos que $T(n-2) = T(n-3) + c$. Sustituimos: $T(n) = [T(n-3) + c] + 2c = \mathbf{T(n-3) + 3c}$
- Paso 3: Generalizar a '**$k$**' pasos. 
	Al observar las iteraciones, vemos que el número que resta a $n$ es igual al coeficiente de $c$. Después de $k$ expansiones, la fórmula es: $T(n) = \mathbf{T(n-k) + kc}$
- Paso 4: Forzar el caso base.
	La recursión se detiene cuando llegamos al caso base, típicamente $T(1)$. Para que $T(n-k)$ se convierta en $T(1)$, necesitamos que $n-k = 1$. Despejando $k$, obtenemos: $k = n-1$
- Paso 5: Resolver y obtener el Big O.
	Sustituimos $k$ en nuestra fórmula general: $T(n) = T(n - (n-1)) + (n-1)c$ $T(n) = T(1) + cn - c$ Sabiendo que $T(1)$ es una constante, la ecuación final es un polinomio de grado 1 ($cn$). Eliminando constantes, la **Complejidad es** $O(n)$.
