package src.rolgar2.elemento.carta;

import src.rolgar2.*;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;
import src.rolgar2.entidad.Efectos;
import src.rolgar2.entidad.Jugador;
import src.utils.Validaciones;

import java.io.IOException;
import java.util.Random;

import static java.lang.Math.min;

public enum TipoCarta {
    ATAQUE_DOBLE {
        @Override
        public String toString() {
            return "Ataque doble";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta ataque doble", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo){
            try { usuario.getCombate().atacar(usuario); }
            catch (RuntimeException _) {
                Salida.mostrarMensajeError("No se pudo atacar");
                return false;
            }
            try { usuario.getCombate().atacar(usuario); }
            catch (RuntimeException _) {
                Salida.mostrarMensajeError("No se pudo atacar");
                return false;
            }
            return true;
        }
    },
    CURACION {
        @Override
        public String toString() {
            return "Curación";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta curación", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo){
            usuario.setVida(min(usuario.getVida() + ConfiguracionesRolgar2.getCuracionCarta(), usuario.getVidaMaxima()));
            return true;
        }
    },
    CURACION_ALIADO {
        @Override
        public String toString() {
            return "Curación aliado";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta curación aliado", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo){
            if (usuario.getAlianza().estaEnAlianza(objetivo)) {
                objetivo.setVida(usuario.getVida() + ConfiguracionesRolgar2.getCuracionCarta());
                return true;
            }
            return false;
        }
    },
    DOBLE_MOVIMIENTO {
        @Override
        public String toString() {
            return "Doble movimiento";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta doble movimiento", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo) {
            if (usuario.estaEnCombate()) {
                usuario.getCombate().escapar(usuario);
                usuario.getCombate().escapar(usuario);
            } else {
                AdministradorAcciones.mover(usuario);
                AdministradorAcciones.mover(usuario);
            }
            return true;
        }
    },
    ESCUDO {
        @Override
        public String toString() {
            return "Escudo";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta escudo", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo){
            usuario.agregarEfecto(Efectos.ESCUDO);
            return true;
        }
    },
    INVISIBILIDAD {
        @Override
        public String toString() {
            return "Invisibilidad";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta invisibilidad", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo){
            usuario.agregarEfecto(Efectos.INVISIBILIDAD);
            return true;
        }
    },
    ROBO_CARTA {
        @Override
        public String toString() {
            return "Robo carta";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta robo de carta", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo) {
            Validaciones.validarFalse(usuario.tieneInventarioLleno(), "inventario personaje");
            Validaciones.validarFalse(objetivo.tieneInventarioVacio(), "inventario objetivo");
            if (usuario.getAlianza() != null) {
                Validaciones.validarFalse(usuario.getAlianza().estaEnAlianza(objetivo), "alianza");
            }

            int numeroRandom;

            do {
                numeroRandom = new Random().nextInt(1, ConfiguracionesRolgar2.getCantidadMaximaCartasInventario());
            } while (objetivo.posicionInventarioInvalida(numeroRandom));

            Carta carta = objetivo.quitarElemento(numeroRandom);

            usuario.guardarElemento(carta);
            return true;
        }
    },
    TELETRANSPORTACION {
        @Override
        public String toString() {
            return "Teletransportación";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta teletransportación", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo){
            Rolgar2.getMapa().quitarPersonaje(usuario, usuario.getPosicion());

            int[] nuevaPosicion;
            do {
                nuevaPosicion = Rolgar2.getMapa().generarPosicionAleatoria();
            } while (!(Rolgar2.getMapa().esCeldaTraspasable(nuevaPosicion) && Rolgar2.getMapa().tieneVecinoTraspasable(nuevaPosicion)));

            Rolgar2.getMapa().agregarPersonaje(usuario, nuevaPosicion);
            usuario.setPosicion(nuevaPosicion);
            return true;
        }
    },
    RANDOM {
        @Override
        public String toString() {
            return "Random";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta random", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo){
            Random random = new Random();
            TipoCarta nuevoTipo;

            do {
                nuevoTipo = TipoCarta.values()[random.nextInt(TipoCarta.values().length) - 1];
            } while (nuevoTipo == TipoCarta.RANDOM);

            Carta nuevaCarta = nuevoTipo.crear();

            usuario.guardarElemento(nuevaCarta);
            return true;
        }
    },
    COMODIN {
        @Override
        public String toString() {
            return "Comodín";
        }

        @Override
        public Carta crear() {
            return new Carta("Carta comodín", this);
        }

        @Override
        public boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo){
            Validaciones.validarDistintoDeNull(tipo, "tipo");
            Validaciones.validarFalse(tipo == TipoCarta.COMODIN, "tipo");

            Carta nuevaCarta = tipo.crear();

            usuario.guardarElemento(nuevaCarta);
            
            return true;
        }
    };

    /**
     * Crea una instancia de la carta asociada a este tipo.
     *
     * @return una nueva instancia de la carta correspondiente
     */
    public abstract Carta crear();

    /**
     * Ejecuta la acción de la carta asociada a este tipo.
     * El orden de parámetros es: usuario, objetivo, tipo.
     *
     * @param usuario jugador que usa la carta. No puede ser null.
     * @param objetivo jugador objetivo de la carta. Puede ser null.
     * @param tipo tipo de carta (para cartas comodín). Puede ser null.
     * @return true si se realizó con éxito, false en caso contrario.
     */
    public abstract boolean usar(Jugador usuario, Jugador objetivo, TipoCarta tipo) throws IOException;
}
