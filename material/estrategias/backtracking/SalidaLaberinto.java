package material.estrategias.backtracking;

public class SalidaLaberinto {

    // Dimensiones del laberinto
    static int N;

    public static void main(String[] args) {
        // Ejemplo de laberinto: 1 representa un camino, 0 representa una pared
        int[][] laberinto = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
        };

        N = laberinto.length; // Asignamos el tamaño del laberinto
        int[][] solucion = new int[N][N]; // Matriz para almacenar el camino solución

        if (resolverLaberinto(laberinto, 0, 0, solucion)) {
            imprimirSolucion(solucion);
        } else {
            System.out.println("No hay solución para el laberinto.");
        }
    }

    
    // Método para resolver el laberinto utilizando backtracking
    public static boolean resolverLaberinto(int[][] laberinto, int x, int y, int[][] solucion) {
        // Caso base: si llegamos al destino (esquina inferior derecha)
        if (x == N - 1 && y == N - 1 && laberinto[x][y] == 1) {
            solucion[x][y] = 1;
            return true;
        }

        // Verificar si es seguro avanzar a la celda laberinto[x][y]
        if (esSeguro(laberinto, x, y)) {
            // Marcar esta celda como parte del camino
            solucion[x][y] = 1;

            // Moverse hacia abajo
            if (resolverLaberinto(laberinto, x + 1, y, solucion)) {
                return true;
            }

            // Moverse hacia la derecha
            if (resolverLaberinto(laberinto, x, y + 1, solucion)) {
                return true;
            }

            // Retroceder (backtracking): desmarcar esta celda
            solucion[x][y] = 0;
            return false;
        }

        return false; // No es seguro avanzar
    }

    // Método para verificar si una celda es válida y transitable
    public static boolean esSeguro(int[][] laberinto, int x, int y) {
        return (x >= 0 && x < N && y >= 0 && y < N && laberinto[x][y] == 1);
    }

    // Método para imprimir la solución del laberinto
    public static void imprimirSolucion(int[][] solucion) {
        System.out.println("Camino solución:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(solucion[i][j] + " ");
            }
            System.out.println();
        }
    }
}