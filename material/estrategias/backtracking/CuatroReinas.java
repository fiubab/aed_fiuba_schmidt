package material.estrategias.backtracking;

public class CuatroReinas {

    // Método principal
    public static void main(String[] args) {
        int n = 4; // Número de reinas y tamaño del tablero
        int[][] tablero = new int[n][n]; // Inicializamos un tablero vacío

        if (resolver4Reinas(tablero, 0)) {
            imprimirTablero(tablero);
        } else {
            System.out.println("No hay solución.");
        }
    }

    // Método para resolver el problema de 4 reinas
    public static boolean resolver4Reinas(int[][] tablero, int fila) {
        int n = tablero.length;

        // Caso base: todas las reinas han sido colocadas
        if (fila >= n) {
            return true;
        }

        // Intentar colocar una reina en cada columna de la fila actual
        for (int col = 0; col < n; col++) {
            if (esSeguro(tablero, fila, col)) {
                // Colocar la reina
                tablero[fila][col] = 1;

                // Recursivamente tratar de colocar el resto de las reinas
                if (resolver4Reinas(tablero, fila + 1)) {
                    return true;
                }

                // Si no es posible, retrocedemos (backtracking)
                tablero[fila][col] = 0;
            }
        }

        // Si no se puede colocar una reina en esta fila, retornamos false
        return false;
    }

    // Método para verificar si es seguro colocar una reina en tablero[fila][col]
    public static boolean esSeguro(int[][] tablero, int fila, int col) {
        int n = tablero.length;

        // Comprobar la misma columna en filas anteriores
        for (int i = 0; i < fila; i++) {
            if (tablero[i][col] == 1) {
                return false;
            }
        }

        // Comprobar la diagonal superior izquierda
        for (int i = fila, j = col; i >= 0 && j >= 0; i--, j--) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }

        // Comprobar la diagonal superior derecha
        for (int i = fila, j = col; i >= 0 && j < n; i--, j++) {
            if (tablero[i][j] == 1) {
                return false;
            }
        }

        // Si pasa todas las comprobaciones, es seguro colocar la reina
        return true;
    }

    // Método para imprimir el tablero
    public static void imprimirTablero(int[][] tablero) {
        int n = tablero.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((tablero[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
    }
}