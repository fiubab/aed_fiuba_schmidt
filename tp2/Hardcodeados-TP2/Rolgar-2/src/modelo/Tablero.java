package src.modelo;

import src.estructuras.listas.ListaSimplementeEnlazada;
import src.utils.Validaciones;

import java.util.List;
import java.util.Objects;

/**
 * Representa un tablero tridimensional de celdas para el juego.
 * 
 * <p>El tablero es una estructura 3D que contiene celdas organizadas por ancho, alto y profundidad.
 * Cada celda puede contener un valor de tipo genérico T y tiene referencias a sus celdas vecinas.</p>
 * 
 * @param <T> el tipo de contenido que almacenan las celdas del tablero
 */
public class Tablero<T> {
    //INTERFACES ----------------------------------------------------------------------------------------------
    //ENUMERADOS ----------------------------------------------------------------------------------------------
    //CONSTANTES ----------------------------------------------------------------------------------------------
    //ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
    //ATRIBUTOS -----------------------------------------------------------------------------------------------

    private final List<List<List<Celda<T>>>> celdas;

    //ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
    //CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Genera un tablero de ancho x alto x profundo.
     * 
     * @param ancho ancho del tablero, debe ser mayor o igual a 1
     * @param alto alto del tablero, debe ser mayor o igual a 1
     * @param profundo profundidad del tablero, debe ser mayor o igual a 1
     * @throws RuntimeException si alguna dimensión es menor a 1
     * @pre {@code ancho >= 1 && alto >= 1 && profundo >= 1}
     */
    public Tablero(int ancho, int alto, int profundo) {
        this(ancho, alto, profundo, null);
    }

    /**
     * Genera un tablero de ancho x alto x profundo con un valor por defecto.
     * 
     * @param ancho ancho del tablero, debe ser mayor o igual a 1
     * @param alto alto del tablero, debe ser mayor o igual a 1
     * @param profundo profundidad del tablero, debe ser mayor o igual a 1
     * @param valorPorDefecto el valor por defecto de las celdas, puede ser null
     * @throws RuntimeException si alguna dimensión es menor a 1
     * @pre {@code ancho >= 1 && alto >= 1 && profundo >= 1}
     */
    public Tablero(int ancho, int alto, int profundo, T valorPorDefecto) {
        Validaciones.validarMayorQueCero(ancho, "ancho");
        Validaciones.validarMayorQueCero(alto, "alto");
        Validaciones.validarMayorQueCero(profundo, "profundo");

        this.celdas = new ListaSimplementeEnlazada<>();

        for(int i = 1; i <= ancho; i++) {
            List<List<Celda<T>>> fila = new ListaSimplementeEnlazada<>();

            for(int j = 1; j <= alto; j++) {

                List<Celda<T>> columna = new ListaSimplementeEnlazada<>();

                for(int k = 1; k <= profundo; k++) {
                    columna.add(new Celda<>(valorPorDefecto, new int[] {i, j, k}));
                }

                fila.add(columna);
            }

            this.celdas.add(fila);
        }
        
        // Inicializar vecinos después de crear todas las celdas
        this.inicializarVecinos();
    }


     /**
     * Inicializa los vecinos de cada celda en el tablero.
     * Los vecinos incluyen las 26 celdas adyacentes en un espacio 3D.
     */
    private void inicializarVecinos() {
        for(int i = 1; i <= getAncho(); i++) {
            for(int j = 1; j <= getAlto(); j++) {
                for(int k = 1; k <= getProfundo(); k++) {
                    Celda<T> celdaActual = this.getCelda(i, j, k);
                    Celda<T>[][][] vecinos = obtenerVecinos(i, j, k);
                    celdaActual.setVecinos(vecinos);
                }
            }
        }
    }
    
    /**
     * Obtiene los vecinos de una celda en la posición (x, y, z).
     * Los vecinos incluyen las 26 celdas adyacentes en un espacio 3D.
     * Si las coordenadas no están dentro de los límites, se devuelve null.
     * @param x coordenada x basada en 1
     * @param y coordenada y basada en 1
     * @param z coordenada z basada en 1
     * @return matriz 3x3x3 con los vecinos (null si está fuera de límites)
     */
    @SuppressWarnings("unchecked")
    private Celda<T>[][][] obtenerVecinos(int x, int y, int z) {
        Celda<T>[][][] vecinos = new Celda[3][3][3];
        
        for(int dx = -1; dx <= 1; dx++) {
            for(int dy = -1; dy <= 1; dy++) {
                for(int dz = -1; dz <= 1; dz++) {
                    int nx = x + dx;
                    int ny = y + dy;
                    int nz = z + dz;
                    
                    // Verificar si las coordenadas están dentro de los límites (1-based)
                    if(nx >= 1 && nx <= getAncho() && 
                       ny >= 1 && ny <= getAlto() && 
                       nz >= 1 && nz <= getProfundo()) {
                        vecinos[dx + 1][dy + 1][dz + 1] = this.getCelda(nx, ny, nz);
                    } else {
                        vecinos[dx + 1][dy + 1][dz + 1] = null;
                    }
                }
            }
        }
        
        return vecinos;
    }

    //METODOS ABSTRACTOS --------------------------------------------------------------------------------------
    //METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
    //METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
    //METODOS DE CLASE ----------------------------------------------------------------------------------------
    //METODOS GENERALES ---------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Tablero de " + this.getAncho() + " x " + this.getAlto() + " x " + this.getProfundo();
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getAncho(), getAlto(), getProfundo());

        for (int i = 1; i <= getAncho(); i++) {
            for (int j = 1; j <= getAlto(); j++) {
                for (int k = 1; k <= getProfundo(); k++) {
                    result = 31 * result + Objects.hashCode(getCelda(i, j, k));
                }
            }
        }

        return result;
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        Tablero<T> other = (Tablero<T>) obj;

        if (this.getAncho() != other.getAncho() ||
                this.getAlto() != other.getAlto() ||
                this.getProfundo() != other.getProfundo()) {
            return false;
        }

        // Comparar celda por celda
        for (int i = 1; i <= getAncho(); i++) {
            for (int j = 1; j <= getAlto(); j++) {
                for (int k = 1; k <= getProfundo(); k++) {
                    Celda<T> c1 = this.getCelda(i, j, k);
                    Celda<T> c2 = other.getCelda(i, j, k);
                    if (!Objects.equals(c1, c2)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    //METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Obtiene una celda en la posición (x, y, z).
     * 
     * @param x coordenada x basada en 1
     * @param y coordenada y basada en 1
     * @param z coordenada z basada en 1
     * @return la celda en la posición (x, y, z)
     * @throws RuntimeException si alguna coordenada es menor a 1
     * @pre {@code x >= 1 && y >= 1 && z >= 1}
     */
    public Celda<T> getCelda(int x, int y, int z) {
        Validaciones.validarMayorQueCero(x, "X");
        Validaciones.validarMayorQueCero(y, "Y");
        Validaciones.validarMayorQueCero(z, "Z");

        return this.celdas.get(x-1).get(y-1).get(z-1);
    }
    
    /**
     * Obtiene los vecinos de una celda en la posición (x, y, z).
     * 
     * @param x coordenada x basada en 1
     * @param y coordenada y basada en 1
     * @param z coordenada z basada en 1
     * @return matriz 3x3x3 con los vecinos
     * @throws RuntimeException si alguna coordenada es menor a 1
     * @pre {@code x >= 1 && y >= 1 && z >= 1}
     */
    public Celda<T>[][][] getVecinosDeCelda(int x, int y, int z) {
        Validaciones.validarMayorQueCero(x, "X");
        Validaciones.validarMayorQueCero(y, "Y");
        Validaciones.validarMayorQueCero(z, "Z");

        return getCelda(x, y, z).getVecinos();
    }

    //METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------
    //GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
    //GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
    //GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
    //GETTERS SIMPLES -----------------------------------------------------------------------------------------


    /**
     * Obtiene el ancho del tablero.
     * 
     * @return el ancho del tablero
     */
    public int getAncho() {
        return this.celdas.size();
    }

    /**
     * Obtiene el alto del tablero.
     * 
     * @return el alto del tablero
     */
    public int getAlto() {
        return this.celdas.getFirst().size();
    }

    /**
     * Obtiene la profundidad del tablero.
     * 
     * @return la profundidad del tablero
     */
    public int getProfundo() {
        return this.celdas.getFirst().getFirst().size();
    }

    /**
     * Obtiene la cantidad total de celdas del tablero.
     * 
     * @return la cantidad de celdas (ancho × alto × profundo)
     */
    public int getCantidadDeCeldas() {
        return getAncho() * getAlto() * getProfundo();
    }

    /**
     * Obtiene la cantidad de celdas libres del tablero.
     * 
     * @return la cantidad de celdas sin contenido
     */
    public int getCantidadDeCeldasLibres() {
        int cantidadDeCeldasLibres = 0;

        for(int i = 1; i <= getAncho(); i++) {
            for(int j = 1; j <= getAlto(); j++) {
                for(int k = 1; k <= getProfundo(); k++) {
                    if (getCelda(i, j, k).estaLibre()) {
                        cantidadDeCeldasLibres++;
                    }
                }
            }
        }

        return cantidadDeCeldasLibres;
    }

    //SETTERS COMPLEJOS----------------------------------------------------------------------------------------
    //SETTERS SIMPLES -----------------------------------------------------------------------------------------
}