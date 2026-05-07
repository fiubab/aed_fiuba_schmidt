#algoritmos #recursion

---

El Maximo Comun Divisor (MCD) de dos numeros enteros es el mayor numero entero que divide a ambos sin dejar resto. El Algoritmo de Euclides es el metodo mas conocido y eficiente para calcularlo.

Se basa en la propiedad de que el MCD de dos numeros no cambia si reemplazamos el mayor por la diferencia entre ambos. En su version moderna, se usa el resto de la division en lugar de la diferencia, lo que acelera el proceso.

El caso base se da cuando uno de los numeros llega a 0, entonces el MCD es el otro numero.

```Java
static int mcdR(int a, int b) {
	if (b == 0) return a;
	return mcdR(b, a % b);
}
```

La version iterativa es igual de simple y evita el costo de las llamadas recursivas.

```Java
static int mcdI(int a, int b) {
	while (b != 0) {
		int temp = b;
		b = a % b;
		a = temp;
	}
	return a;
}
```
