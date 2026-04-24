package src.rolgar2.entidad;

import src.modelo.Personaje;
import src.rolgar2.Combate;
import src.utils.Validaciones;

import java.util.Arrays;

/**
 * Clase base que representa una entidad en el juego.
 * Una entidad es un personaje con estadísticas de combate y posición en el mapa.
 * 
 * <p>Propiedades:</p>
 * <ul>
 *   <li>Vida: puntos de salud actuales</li>
 *   <li>Fuerza: daño base en combate</li>
 *   <li>Iniciativa: orden de turnos</li>
 *   <li>Posición: ubicación en el mapa 3D</li>
 *   <li>Combate: combate actual si está en uno</li>
 * </ul>
 */
public class Entidad extends Personaje {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
//ATRIBUTOS -----------------------------------------------------------------------------------------------

    protected float vida;
    protected Float vidaMaxima;
    protected float fuerza;
    protected int iniciativa;
    protected int[] posicion;
    protected Combate combate;

//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------

    /**
     * Crea una nueva entidad con los atributos especificados.
     *
     * @param nombre nombre de la entidad, no puede ser null ni estar vacío
     * @param vida vida inicial y máxima de la entidad, debe ser mayor o igual a 0
     * @param fuerza fuerza de la entidad, debe ser mayor a 0
     * @param iniciativa iniciativa de la entidad, debe ser mayor o igual a 0
     * @throws RuntimeException si algún parámetro no cumple las condiciones
     * @pre {@code nombre != null && !nombre.isBlank()}
     * @pre {@code vida >= 0 && fuerza > 0 && iniciativa >= 0}
     */
    public Entidad(String nombre, float vida, float fuerza, int iniciativa) {
        super(nombre);
        setVida(vida);
        setFuerza(fuerza);
        setIniciativa(iniciativa);

        this.vidaMaxima = this.vida;
    }

//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        
        if (!super.equals(o)) {
            return false;
        }
        
        Entidad entidad = (Entidad) o;
        return Float.compare(this.getVida(), entidad.getVida()) == 0 &&
               Float.compare(this.getVidaMaxima(), entidad.getVidaMaxima()) == 0 &&
               Float.compare(this.getFuerza(), entidad.getFuerza()) == 0 &&
               this.getIniciativa() == entidad.getIniciativa() &&
               Arrays.equals(this.getPosicion(), entidad.getPosicion());
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(
            super.hashCode(),
            this.getVida(),
            this.getVidaMaxima(),
            this.getFuerza(),
            this.getIniciativa(),
            Arrays.hashCode(this.getPosicion())
        );
    }

    @Override
    public String toString() {
        return "Entidad llamada " + this.getNombre() +
               " con " + this.getVida() + "/" + this.getVidaMaxima() + " de vida" +
               ", " + this.getFuerza() + " de fuerza" +
               " y " + this.getIniciativa() + " de iniciativa";
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * Verifica si la entidad está en combate.
     * 
     * @return true si la entidad está en combate, false en caso contrario
     */
    public boolean estaEnCombate(){
        return this.combate != null;
    }

//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------
//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * Obtiene la vida actual de la entidad.
     * 
     * @return la vida actual
     */
    public float getVida() {
        return this.vida;
    }

    /**
     * Obtiene la vida máxima de la entidad.
     * 
     * @return la vida máxima
     */
    public Float getVidaMaxima() {
        return this.vidaMaxima;
    }

    /**
     * Obtiene la fuerza de la entidad.
     * 
     * @return la fuerza
     */
    public float getFuerza() {
        return this.fuerza;
    }

    /**
     * Obtiene la iniciativa de la entidad.
     * 
     * @return la iniciativa
     */
    public int getIniciativa() {
        return this.iniciativa;
    }

    /**
     * Obtiene las coordenadas de posición de la entidad en el mapa.
     * 
     * @return arreglo con las coordenadas [x, y, z]
     */
    public int[] getPosicion() {
        if (this.posicion == null) { // Necesario porque sino Arrays.copyOf tira NullPointerException
            return null;
        }

        return Arrays.copyOf(this.posicion, this.posicion.length);
    }

    /**
     * Obtiene el combate en el que se encuentra la entidad.
     * 
     * @return el combate actual, o null si no está en combate
     */
    public Combate getCombate() {
        return this.combate;
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
    /**
     * Establece la vida de la entidad.
     * 
     * @param vida nueva vida, debe estar entre 0 y la vida máxima
     * @throws RuntimeException si la vida es negativa o mayor a la vida máxima
     * @pre {@code vida >= 0 && vida <= vidaMaxima}
     */
    public void setVida(float vida) {
        Validaciones.validarMayorIgualCero(vida, "vida");

        if (this.getVidaMaxima() != null) {
            Validaciones.validarFalse(vida > this.getVidaMaxima(), "vida");
        }

        this.vida = vida;
    }

    /**
     * Establece la fuerza de la entidad.
     * 
     * @param fuerza nueva fuerza, debe ser mayor a 0
     * @throws RuntimeException si la fuerza es menor o igual a 0
     * @pre {@code fuerza > 0}
     */
    protected void setFuerza(float fuerza) {
        Validaciones.validarMayorQueCero(fuerza, "fuerza");

        this.fuerza = fuerza;
    }

    /**
     * Establece la iniciativa de la entidad.
     * 
     * @param iniciativa nueva iniciativa, debe ser mayor o igual a 0
     * @throws RuntimeException si la iniciativa es negativa
     * @pre {@code iniciativa >= 0}
     */
    protected void setIniciativa(int iniciativa) {
        Validaciones.validarMayorIgualCero(iniciativa, "iniciativa");

        this.iniciativa = iniciativa;
    }

    /**
     * Establece la posición de la entidad en el mapa.
     * 
     * @param posicion arreglo con las coordenadas [x, y, z], no puede ser null
     * @throws RuntimeException si la posición es null o contiene coordenadas inválidas
     * @pre {@code posicion != null && posicion[i] > 0 para todo i}
     */
    public void setPosicion(int[] posicion) {
        Validaciones.validarDistintoDeNull(posicion, "posicion");

        for (int coordenada : posicion) {
            Validaciones.validarMayorQueCero(coordenada, "coordenada");
        }

        this.posicion = posicion;
    }


    /**
     * Establece el combate en el que se encuentra la entidad.
     * 
     * @param combate el combate puede ser null para salir del combate
     */
    public void setCombate(Combate combate) {
        this.combate = combate;
    }
}
