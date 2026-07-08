fecha: {date}
tags: #algoritmos #complejidad

---

### Big O ($O$) - Cota Superior (El "Techo")
El peor escenario posible. Define un límite superior para el crecimiento del algoritmo.   Decir que un algoritmo es $O(f(n))$ significa que, a partir de cierto punto, el tiempo de ejecución **no crecerá más rápido** que $f(n)$ multiplicada por una constante.
- _Ejemplo informal:_ "En el peor de los casos, este algoritmo tardará $n^2$, o quizás menos, pero nunca más que eso".
- _Definición formal:_ $T(n) \le c \cdot f(n)$ para todo $n \ge n_0$.

### Big Omega ($\Omega$) - Cota Inferior (El "Piso")
Representa el mejor escenario posible. Define un límite inferior. Decir que un algoritmo es $\Omega(f(n))$ significa que el tiempo de ejecución **crecerá al menos tan rápido** como $f(n)$.
- _Ejemplo informal:_ "Incluso en el mejor de los casos, este algoritmo va a tardar por lo menos $n \log n$".
- _Definición formal:_ $T(n) \ge c \cdot f(n)$ para todo $n \ge n_0$.

### Big Theta ($\Theta$) - Cota Ajustada (Exactitud)
Ocurre cuando un algoritmo está acotado tanto por arriba como por abajo por la misma función. Es decir, es tanto $O(f(n))$ como $\Omega(f(n))$.
- _Ejemplo informal:_ "El algoritmo crece exactamente al ritmo de $n^2$, ni más rápido, ni más lento".
- _Nota de exámenes:_ Cuando se pide calcular la complejidad de un algoritmo, generalmente se espera que encuentres la cota más ajustada ($\Theta$), aunque por costumbre se suela responder usando la notación $O$.

## 2. El debate de notación: $T(n) = O(n^2)$ vs $T(n) \in O(n^2)$

1. $T(n) \in O(n^2)$ **(La forma matemáticamente correcta):** $O(n^2)$ no es una sola función, sino un **conjunto de funciones**. Específicamente, es el conjunto de todas las funciones que crecen igual o más lento que $n^2$ (incluye $n$, $n \log n$, $1$, etc.). Por lo tanto, lo correcto en teoría de conjuntos es decir que nuestra función $T(n)$ _pertenece_ ($\in$) a ese conjunto.
