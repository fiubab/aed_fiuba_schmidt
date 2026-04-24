package material.estrategias.backtracking;

public class ColoreadoGrafos {

    // Método principal
    public static void main(String[] args) {
        // Ejemplo de grafo: Matriz de adyacencia
        int[][] grafo = {
            {0, 1, 1, 1}, // Nodo 0 está conectado con 1, 2 y 3
            {1, 0, 1, 0}, // Nodo 1 está conectado con 0 y 2
            {1, 1, 0, 1}, // Nodo 2 está conectado con 0, 1 y 3
            {1, 0, 1, 0}  // Nodo 3 está conectado con 0 y 2
        };

        int numColores = 3; // Máximo número de colores
        int[] colores = new int[grafo.length]; // Array para almacenar los colores asignados

        if (resolverColoreado(grafo, numColores, colores, 0)) {
            imprimirColores(colores);
        } else {
            System.out.println("No es posible colorear el grafo con " + numColores + " colores.");
        }
    }

    // Método para resolver el problema de coloreado de grafos usando backtracking
    public static boolean resolverColoreado(int[][] grafo, int numColores, int[] colores, int nodo) {
        // Caso base: si todos los nodos están coloreados, retornamos true
        if (nodo == grafo.length) {
            return true;
        }

        // Intentamos asignar un color a este nodo
        for (int color = 1; color <= numColores; color++) {
            if (esSeguro(grafo, colores, nodo, color)) {
                // Asignamos el color
                colores[nodo] = color;

                // Recursivamente tratamos de colorear el resto de los nodos
                if (resolverColoreado(grafo, numColores, colores, nodo + 1)) {
                    return true;
                }

                // Si no es posible, desasignamos el color (backtracking)
                colores[nodo] = 0;
            }
        }

        // Si no podemos asignar ningún color a este nodo, retornamos false
        return false;
    }

    // Método para verificar si es seguro asignar un color a un nodo
    public static boolean esSeguro(int[][] grafo, int[] colores, int nodo, int color) {
        for (int i = 0; i < grafo.length; i++) {
            // Si el nodo es adyacente y tiene el mismo color, no es seguro
            if (grafo[nodo][i] == 1 && colores[i] == color) {
                return false;
            }
        }
        return true;
    }

    // Método para imprimir la asignación de colores
    public static void imprimirColores(int[] colores) {
        System.out.println("Colores asignados a los nodos:");
        for (int i = 0; i < colores.length; i++) {
            System.out.println("Nodo " + i + " -> Color " + colores[i]);
        }
    }
}