package src.rolgar2;

/**
 * Enum que define todas las acciones que un jugador puede realizar durante su turno.
 * 
 * <p>Acciones disponibles:</p>
 * <ul>
 *   <li>MOVER: Desplazar el personaje a una celda adyacente</li>
 *   <li>ATACAR: Atacar a un enemigo en combate</li>
 *   <li>SELECCIONAR_CARTA: Usar una carta del inventario</li>
 *   <li>CREAR_ALIANZA: Crear una nueva alianza</li>
 *   <li>BORRAR_ALIANZA: Eliminar la alianza actual</li>
 *   <li>ABANDONAR_ALIANZA: Salir de la alianza actual</li>
 *   <li>AGREGAR_JUGADOR_ALIANZA: Agregar un jugador a la alianza</li>
 *   <li>QUITAR_JUGADOR_ALIANZA: Remover un jugador de la alianza</li>
 *   <li>INTERCAMBIAR_CARTA: Intercambiar cartas con un aliado</li>
 *   <li>PAUSAR: Pausar el juego</li>
 *   <li>SALIR: Abandonar el juego</li>
 *   <li>CANCELAR: Cancelar la acción actual</li>
 * </ul>
 */
public enum Acciones {
    MOVER {
        @Override
        public String toString() {
            return "Mover";
        }
    },
    ATACAR {
        @Override
        public String toString() {
            return "Atacar";
        }
    },
    SELECCIONAR_CARTA {
        @Override
        public String toString() {
            return "Seleccionar carta";
        }
    },
    CREAR_ALIANZA {
        @Override
        public String toString() {
            return "Crear alianza";
        }
    },
    BORRAR_ALIANZA {
        @Override
        public String toString() {
            return "Borrar alianza";
        }
    },
    ABANDONAR_ALIANZA {
        @Override
        public String toString() {
            return "Abandonar alianza";
        }
    },
    AGREGAR_JUGADOR_ALIANZA {
        @Override
        public String toString() {
            return "Agregar jugador a la alianza";
        }
    },
    QUITAR_JUGADOR_ALIANZA {
        @Override
        public String toString() {
            return "Quitar Jugador de la alianza";
        }
    },
    INTERCAMBIAR_CARTA {
        @Override
        public String toString() {
            return "Intercambiar carta";
        }
    },
    PAUSAR {
        @Override
        public String toString() {
            return "Pausar";
        }
    },
    SALIR {
        @Override
        public String toString() {
            return "Salir";
        }
    },
    CANCELAR {
        @Override
        public String toString() {
            return "Cancelar";
        }
    }
}
