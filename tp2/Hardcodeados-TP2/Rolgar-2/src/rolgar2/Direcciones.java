package src.rolgar2;

import src.rolgar2.configuracion.ConfiguracionesRolgar2;

/**
 * Enum que define las direcciones de movimiento disponibles en el juego.
 * 
 * <p>Direcciones disponibles (8 direcciones cardinales):</p>
 * <ul>
 *   <li>ARRIBA_IZQUIERDA: Movimiento diagonal hacia noroeste</li>
 *   <li>ARRIBA: Movimiento hacia norte</li>
 *   <li>ARRIBA_DERECHA: Movimiento diagonal hacia noreste</li>
 *   <li>IZQUIERDA: Movimiento hacia oeste</li>
 *   <li>DERECHA: Movimiento hacia este</li>
 *   <li>ABAJO_IZQUIERDA: Movimiento diagonal hacia sudoeste</li>
 *   <li>ABAJO: Movimiento hacia sur</li>
 *   <li>ABAJO_DERECHA: Movimiento diagonal hacia sudeste</li>
 * </ul>
 */
public enum Direcciones {
    ARRIBA_IZQUIERDA {
        @Override
        public String toString() {
            return "Noroeste";
        }
    },
    ARRIBA {
        @Override
        public String toString() {
            return "Norte";
        }
    },
    ARRIBA_DERECHA {
        @Override
        public String toString() {
            return "Noreste";
        }
    },
    IZQUIERDA {
        @Override
        public String toString() {
            return "Oeste";
        }
    },
    DERECHA {
        @Override
        public String toString() {
            return "Este";
        }
    },
    ABAJO_IZQUIERDA {
        @Override
        public String toString() {
            return "Sudoeste";
        }
    },
    ABAJO {
        @Override
        public String toString() {
            return "Sur";
        }
    },
    ABAJO_DERECHA {
        @Override
        public String toString() {
            return "Sudeste";
        }
    };

    /**
     * Obtiene la dirección correspondiente a un carácter de entrada.
     * Mapea caracteres configurados a direcciones del juego.
     *
     * @param c carácter de entrada del usuario
     * @return la dirección correspondiente al carácter
     * @throws IllegalArgumentException si el carácter no corresponde a ninguna dirección válida
     */
    public static Direcciones obtenerDireccion(char c) {
        if (c == ConfiguracionesRolgar2.getMoverArribaIzquierda()) { return ARRIBA_IZQUIERDA; }
        else if (c == ConfiguracionesRolgar2.getMoverArriba()) { return ARRIBA; }
        else if (c == ConfiguracionesRolgar2.getMoverArribaDerecha()) { return ARRIBA_DERECHA; }
        else if (c == ConfiguracionesRolgar2.getMoverIzquierda()) { return IZQUIERDA; }
        else if (c == ConfiguracionesRolgar2.getMoverDerecha()) { return DERECHA; }
        else if (c == ConfiguracionesRolgar2.getMoverAbajoIzquierda()) { return ABAJO_IZQUIERDA; }
        else if (c == ConfiguracionesRolgar2.getMoverAbajo()) { return ABAJO; }
        else if (c == ConfiguracionesRolgar2.getMoverAbajoDerecha()) { return ABAJO_DERECHA; }
        else { throw new IllegalArgumentException("Dirección invalida: " + c); }
    }
}