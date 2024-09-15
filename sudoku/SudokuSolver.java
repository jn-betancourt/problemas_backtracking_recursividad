public class SudokuSolver {

    // Dimensiones del Sudoku 9x9
    private static final int dimension = 9;

    public static void main(String[] args) {
        // Definición del tablero (0 representa celdas vacías)
        int[][] tablero = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        System.out.println("Sudoku original:");
        imprimirTablero(tablero);

        if (resolverSudoku(tablero)) {
            System.out.println("Sudoku resuelto:");
            imprimirTablero(tablero);
        } else {
            System.out.println("No se pudo resolver el Sudoku.");
        }
    }

    // Función principal para resolver el Sudoku
    public static boolean resolverSudoku(int[][] tablero) {
        // Buscar una celda vacía (valor 0)
        for (int i = 0; i < dimension; i++) {
            for (int columna = 0; columna < dimension; columna++) {
                if (tablero[i][columna] == 0) {
                    // Probar números del 1 al 9
                    for (int numero = 1; numero <= 9; numero++) {
                        if (esValido(tablero, i, columna, numero)) {
                            // Colocar el número en la celda
                            tablero[i][columna] = numero;

                            // Llamada recursiva
                            if (resolverSudoku(tablero)) {
                                return true;
                            }
                            // Si no funciona, retroceder (backtracking)
                            tablero[i][columna] = 0;
                        }
                    }
                    return false; // Si ningún número es válido, volver atrás
                }
            }
        }
        return true; // Si no quedan celdas vacías, el Sudoku está resuelto
    }

    // Función para verificar si el número es válido en la fila, columna y caja 3x3
    public static boolean esValido(int[][] tablero, int fila, int columna, int numero) {
        // Verificar la fila
        for (int j = 0; j < dimension; j++) {
            if (tablero[fila][j] == numero) {
                return false;
            }
        }

        // Verificar la columna
        for (int i = 0; i < dimension; i++) {
            if (tablero[i][columna] == numero) {
                return false;
            }
        }

        // Verificar la caja 3x3
        int inicioFila = fila - (fila % 3);
        int inicioColumna = columna - (columna % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[inicioFila + i][inicioColumna + j] == numero) {
                    return false;
                }
            }
        }

        return true;
    }

    // Función para imprimir el tablero del Sudoku
    public static void imprimirTablero(int[][] tablero) {
        for (int fila = 0; fila < dimension; fila++) {
            for (int columna = 0; columna < dimension; columna++) {
                System.out.print(tablero[fila][columna] + " ");
            }
            System.out.println();
        }
    }
}
