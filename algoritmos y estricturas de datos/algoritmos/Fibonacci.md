#algoritmos #recursion

---

La secuencia de fibonacci y el algoritmo para describirla son muy conocidos en la ensenanza de algoritmos y estructuras de datos.

La secuencia de fibonacci son numeros los cuales son definidos por la suma de sus dos numeros anteriores.
Como casos base tendremos al 0 y al 1, que son los primeros 2 numeros de esta secuencia.

**First 15 Numbers in the Sequence:**  
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610.

La implementacion mas famosa del algoritmo para resolver esta secuencia es la forma recursiva

```Java
static int fibonacciR(int n) {
	if (n < 1) return 0;
	if (n == 1) return 0;
	if (n == 2) return 1;
	if (n == 3) return 1;
	if (n == 4) return 2;
	return (fibonacciR(n-1) + fibonacciR(n-2));
}
```

Pero hay una forma poco usada, que es mucho mas eficiente e incluso mas facil de entender. La iterativa.

```Java
    static int fibonacciI(int n) {
        if (n < 1) return 0;
        if (n == 1) return 0;
        if (n == 2) return 1;
        if (n == 3) return 1;

        int iterator = n;
        int prev = 1;
        int last = 1;
        int res = 0;

        while (iterator > 3) {
            res = prev + last;
            prev = last;
            last = res;
            iterator--;
        }
        return res;
    }
```