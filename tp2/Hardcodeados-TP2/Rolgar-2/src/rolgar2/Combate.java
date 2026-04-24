package src.rolgar2;

import src.estructuras.listas.ListaSimplementeEnlazada;
import src.modelo.Dado;
import src.modelo.Personaje;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;
import src.rolgar2.entidad.Efectos;
import src.rolgar2.entidad.Enemigo;
import src.rolgar2.entidad.Entidad;
import src.rolgar2.entidad.Jugador;
import src.utils.Validaciones;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.max;

/**
 * Clase que gestiona los combates entre entidades en el juego.
 * Maneja el cálculo de daño, efectos críticos, escudos y la finalización de combates.
 */
public class Combate {

    private static final int MINIMA_CANTIDAD_PERSONAJES_COMBATE = 2;
    private final int[] posicionCombate;
    private final List<Entidad> entidadesEnCombate;

    /**
     * Crea un nuevo combate en la posición especificada.
     * 
     * @param posicionCombate posición donde ocurre el combate, no puede ser null
     * @throws RuntimeException si la posición es null o inválida
     * @throws RuntimeException si hay menos de 2 entidades en la posición
     * @pre {@code posicionCombate != null}
     * @pre {@code getEntidadesEnCombate().size() >= 2}
     */
    public Combate(int[] posicionCombate) {
        Validaciones.validarDistintoDeNull(posicionCombate, "posicionCombate");

        for (int i = 0; i < posicionCombate.length; i++) {
            Validaciones.validarMayorQueCero(posicionCombate[i], "posicionCombate[" + i + "]");
        }

        this.posicionCombate = posicionCombate;
        this.entidadesEnCombate = new ListaSimplementeEnlazada<>();
        this.entidadesEnCombate.addAll(getEntidadesEnCombate());

        Validaciones.validarMayorIgual(entidadesEnCombate.size(), MINIMA_CANTIDAD_PERSONAJES_COMBATE, "cantidad entidades", "2" );

        for (Entidad entidad : this.entidadesEnCombate) {
            entidad.setCombate(this);
        }
    }

    /**
     * Permite a un jugador intentar escapar del combate.
     * La probabilidad de éxito depende de la configuración del juego.
     * 
     * @param jugador jugador que intenta escapar, no puede ser null
     * @throws RuntimeException si el jugador no está en combate
     * @pre {@code jugador.estaEnCombate()}
     */
    public void escapar(Jugador jugador) {
        Validaciones.validarTrue(jugador.estaEnCombate(), "jugador");

        Dado dado = new Dado();

        if (dado.tirar() <= ConfiguracionesRolgar2.getProbabilidadEscapar() * dado.getCaras()) {
            Salida.mostrarMensaje("Escapaste de la pelea");
            jugador.setCombate(null);
            AdministradorAcciones.mover(jugador);
            this.entidadesEnCombate.remove(jugador);
        } else {
            Salida.mostrarMensaje("No pudiste puede escapar");
        }
    }

    /**
     * Realiza un ataque de una entidad contra un objetivo en el combate.
     * Calcula el daño considerando fuerza, dados, escudos y críticos.
     * 
     * @param entidad entidad que realiza el ataque, no puede ser null
     * @throws RuntimeException si la entidad es null
     * @pre {@code entidad != null}
     */
    public void atacar(Entidad entidad) {
        Validaciones.validarDistintoDeNull(entidad, "entidad");

        List<Entidad> entidadesEnemigas = verificarObjetivos(entidad, this.getEntidadesEnCombate());

        Entidad objetivo = null;

        switch (entidad) {
            case Jugador _ -> {
                if (entidadesEnemigas.isEmpty()) {
                    Salida.mostrarMensaje("No hay entidades a las que atacar");
                    return;
                }

                Salida.mostrarListaDePersonjes(entidadesEnemigas);
                Salida.mostrarMensajeMismaLinea("Objetivo: ");
                objetivo = Entrada.seleccionar(entidadesEnemigas);
            }
            case Enemigo _ -> {
                objetivo = entidadesEnemigas.getFirst();
            }
            default -> {}
        }

        Validaciones.validarDistintoDeNull(objetivo, "objetivo");

        float vidaRestanteDelObjetivo = (objetivo.getVida() - calcularDanio(entidad, objetivo));

        objetivo.setVida(max(0, vidaRestanteDelObjetivo));

        verificarEntidadMuerta(objetivo);
    }

    /**
     * Verifica y filtra los objetivos válidos para atacar.
     * Excluye entidades invisibles, aliados y la entidad atacante.
     * 
     * @param entidad entidad que realiza el ataque
     * @param entidadesEnemigas lista de entidades enemigas potenciales
     * @return lista de objetivos válidos para atacar
     */
    private List<Entidad> verificarObjetivos(Entidad entidad, List<Entidad> entidadesEnemigas){
        List<Entidad> objetivosValidos = new ListaSimplementeEnlazada<>();
        objetivosValidos.addAll(entidadesEnemigas);

        objetivosValidos.remove(entidad);

        // Los personajes invisibles no se puede atacar
        for (Entidad enemigo : objetivosValidos) {
            if (enemigo instanceof Jugador objetivo && objetivo.tieneEfecto(Efectos.INVISIBILIDAD)){
                objetivosValidos.remove(objetivo);
            }
        }

        if (entidad instanceof Jugador jugador) {
            Validaciones.validarTrue(jugador.estaEnCombate(), "jugador");

            if (jugador.getAlianza() != null){
                for (Personaje personaje : jugador.getAlianza().getMiembros()) {
                    if (personaje instanceof Jugador miembro) {
                        objetivosValidos.remove(miembro);
                    }
                }
            }
        }

        return objetivosValidos;
    }

    /**
     * Verifica si una entidad ha muerto y realiza las acciones necesarias.
     * 
     * @param entidad entidad a verificar
     */
    private void verificarEntidadMuerta(Entidad entidad){
        if (entidad.getVida() <= 0) {
            if (entidad instanceof Jugador jugador) {
                jugador.setAlianza(null);
            }

            Rolgar2.getMapa().quitarPersonaje(entidad, entidad.getPosicion());
            Salida.mostrarMensaje(entidad.getNombre() + " eliminado.");
        }
    }

    /**
     * Elimina del combate las entidades que se han movido fuera de la posición de combate.
     */
    public void eliminarEntidadesFueraDeCombate() {
        for (Entidad entidad : this.getEntidadesEnCombate()) {
            if (!Arrays.equals(entidad.getPosicion(), this.posicionCombate)) {
                entidad.setCombate(null);
            }
        }
    }

    /**
     * Calcula el daño infligido por un atacante a un objetivo.
     * Considera fuerza base, tirada de dado D6, escudo del objetivo y golpes críticos.
     * El cálculo sigue la fórmula: (Fuerza + TiradaD6) × ModificadorEscudo × MultiplicadorCrítico
     * Garantiza que el daño resultante nunca sea negativo.
     *
     * @param atacante entidad que realiza el ataque, no puede ser {@code null}
     * @param objetivo entidad que recibe el daño, no puede ser {@code null}
     * @return cantidad de daño a aplicar (siempre un valor positivo o cero)
     * @throws RuntimeException si {@code atacante} es {@code null}
     * @throws RuntimeException si {@code objetivo} es {@code null}
     * @pre {@code atacante != null}
     * @pre {@code objetivo != null}
     * @post {@code \result >= 0}
     * @post El daño calculado incluye todos los modificadores aplicables
     * @see #aplicarEscudo(float)
     * @see #aplicarCritico(float)
     * @see #esCritico()
     */
    private float calcularDanio(Entidad atacante, Entidad objetivo) {
        Validaciones.validarDistintoDeNull(atacante, "atacante");
        Validaciones.validarDistintoDeNull(objetivo, "objetivo");

        Dado d6 = new Dado();
        float resultado = atacante.getFuerza() + d6.tirar();

        // Aplicar reducción de escudo si el objetivo es un personaje con escudo activo
        if (objetivo instanceof Jugador) {
            if (((Jugador) objetivo).tieneEfecto(Efectos.ESCUDO)){
                resultado = aplicarEscudo(resultado);
            }
        }

        // Aplicar multiplicador de crítico si corresponde
        if (esCritico()) {
            resultado = aplicarCritico(resultado);
            Salida.mostrarMensaje("Daño critico!");
        }

        return max(0, resultado);
    }

    /**
     * Aplica el efecto reductor del escudo al daño recibido.
     * El daño se multiplica por el factor de reducción definido en las constantes.
     * Valida que el factor de escudo esté en el rango [0,1] para evitar comportamientos inesperados.
     *
     * @param danio el daño original antes de aplicar la reducción del escudo, debe ser positivo o cero
     * @return el daño modificado después de aplicar la reducción del escudo
     * @throws RuntimeException si {@code danio < 0}
     * @throws RuntimeException si {@code Constantes.EFECTO_ESCUDO} no está en el rango [0,1]
     * @pre {@code danio >= 0}
     * @pre {@code Constantes.EFECTO_ESCUDO >= 0 && Constantes.EFECTO_ESCUDO <= 1}
     * @post {@code \result == danio * Constantes.EFECTO_ESCUDO}
     * @post {@code \result <= danio} (el daño nunca aumenta por el escudo)
     * @post {@code \result >= 0} (el daño nunca es negativo)
     */
    private float aplicarEscudo(float danio) {
        Validaciones.validarMayorIgualCero(danio, "danio");
        Validaciones.validarRango(ConfiguracionesRolgar2.getEfectoEscudo(), 0, 1, "Efecto de escudo");

        return danio * ConfiguracionesRolgar2.getEfectoEscudo();
    }

    /**
     * Determina si el ataque resulta en un golpe crítico.
     * La probabilidad se calcula basada en las constantes de configuración del juego.
     * Realiza una tirada de dado especial para críticos y compara con el umbral calculado.
     *
     * @return {@code true} si el resultado de la tirada es crítico, {@code false} en caso contrario
     * @throws RuntimeException si {@code Constantes.CANTIDAD_CARAS_DADO_CRITICO} es menor o igual a cero
     * @throws RuntimeException si {@code Constantes.PROBABILIDAD_CRITICO} no está en el rango [0,1]
     * @pre {@code Constantes.CANTIDAD_CARAS_DADO_CRITICO > 0}
     * @pre {@code Constantes.PROBABILIDAD_CRITICO >= 0 && Constantes.PROBABILIDAD_CRITICO <= 1}
     * @post Retorna verdadero con probabilidad {@code Constantes.PROBABILIDAD_CRITICO}
     * @post El resultado es consistente con las tiradas de dados del sistema
     */
    private boolean esCritico() {
        Validaciones.validarMayorQueCero(ConfiguracionesRolgar2.getCantidadCarasDadoCritico(), "La cantidad de caras del Dado de Critico");
        Validaciones.validarRango(ConfiguracionesRolgar2.getProbabilidadCritico(), 0, 1, "Probabilidad de crítico");

        Dado dadoCritico = new Dado(ConfiguracionesRolgar2.getCantidadCarasDadoCritico());

        float umbralCritico = ConfiguracionesRolgar2.getProbabilidadCritico() * ConfiguracionesRolgar2.getCantidadCarasDadoCritico();

        return dadoCritico.tirar() <= umbralCritico;
    }

    /**
     * Aplica el multiplicador de daño crítico al daño base.
     * Valida que el multiplicador sea mayor que cero para evitar comportamientos inesperados.
     *
     * @param danio el daño base antes del crítico, debe ser positivo o cero
     * @return el daño multiplicado por el factor crítico
     * @throws RuntimeException si {@code danio < 0}
     * @throws RuntimeException si {@code Constantes.MULTIPLICADOR_CRITICO} es menor o igual a cero
     * @pre {@code danio >= 0}
     * @pre {@code Constantes.MULTIPLICADOR_CRITICO > 0}
     * @post {@code \result == danio * Constantes.MULTIPLICADOR_CRITICO}
     * @post {@code \result >= danio} (el crítico siempre aumenta el daño)
     * @post {@code \result >= 0} (el daño nunca es negativo)
     */
    private static float aplicarCritico(float danio) {
        Validaciones.validarMayorIgualCero(danio, "daño");
        Validaciones.validarMayorQueCero(ConfiguracionesRolgar2.getMultiplicadorCritico(), "Multiplicador de Critico");

        return danio * ConfiguracionesRolgar2.getMultiplicadorCritico();
    }

    /**
     * Finaliza el combate y libera a todas las entidades.
     */
    public void finalizar(){
        for (Entidad entidad : this.entidadesEnCombate){
            entidad.setCombate(null);
        }
    }

    /**
     * Obtiene la lista de entidades que están en el combate.
     * 
     * @return lista de entidades en combate
     */
    private List<Entidad> getEntidadesEnCombate() {
        List<Entidad> entidadesEnCombate = new ListaSimplementeEnlazada<>();

        for (Personaje personaje : Rolgar2.getMapa().getEntidades(this.posicionCombate)) {
            if (personaje instanceof Entidad entidad) {
                entidadesEnCombate.add(entidad);
            }
        }

        return entidadesEnCombate;
    }

    /**
     * Verifica si el combate ha finalizado.
     * El combate termina cuando solo quedan miembros de una alianza o un jugador.
     * 
     * @return true si el combate ha finalizado, false en caso contrario
     */
    public boolean combateFinalizado() {
        if (getEntidadesEnCombate().size() < MINIMA_CANTIDAD_PERSONAJES_COMBATE) {
            return true;
        }

        AlianzaRolgar2<Jugador> alianza = null;

        int cantidadJugadoresMismaAlianza = 0;
        boolean hayEnemigos = false;

        for (Entidad entidad : this.getEntidadesEnCombate()) {
            switch (entidad) {
                case Jugador jugador -> {
                    if (alianza == null) {
                        if (jugador.getAlianza() != null) {
                            cantidadJugadoresMismaAlianza++;
                        }
                        alianza = jugador.getAlianza();
                    } else if (!Objects.equals(jugador.getAlianza(), alianza)) {
                        return false;
                    } else {
                        cantidadJugadoresMismaAlianza++;
                    }
                }
                case Enemigo _ -> hayEnemigos = true;
                default -> {}
            }
        }

        return !hayEnemigos && (cantidadJugadoresMismaAlianza == this.getEntidadesEnCombate().size());
    }
}