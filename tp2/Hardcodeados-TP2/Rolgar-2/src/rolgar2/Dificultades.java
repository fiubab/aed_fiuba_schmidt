package src.rolgar2;

import src.rolgar2.configuracion.ConfiguracionesRolgar2;

/**
 * Enum que define los niveles de dificultad del juego.
 * 
 * <p>Niveles disponibles:</p>
 * <ul>
 *   <li>FACIL: Configuración básica con pocos enemigos y bajo daño</li>
 *   <li>MEDIO: Configuración intermedia con enemigos moderados</li>
 *   <li>DIFICIL: Configuración avanzada con muchos enemigos y alto daño</li>
 *   <li>CUSTOM: Configuración personalizada definida por el jugador</li>
 * </ul>
 * 
 * <p>Cada dificultad define parámetros como:</p>
 * <ul>
 *   <li>Dimensiones del mapa</li>
 *   <li>Cantidad de enemigos</li>
 *   <li>Daño de los enemigos</li>
 *   <li>Iniciativa de los enemigos</li>
 * </ul>
 */
public enum Dificultades {
    FACIL{
        @Override
        public String toString() {
            return "Fácil";
        }

        @Override
        public void mostrarDescripcion(){
            int indice = FACIL.ordinal() + 1;
            System.out.println(indice + ". " + FACIL);
            System.out.println("  Niveles: " +ConfiguracionesRolgar2.getAltoMapaFacil());
            System.out.println("  Enemigos: " +ConfiguracionesRolgar2.getCantidadEnemigosFacil());
            System.out.printf("  Daño enemigo: %.0f\n", ConfiguracionesRolgar2.getDanioEnemigoFacil());
            System.out.println("  Velocidad del enemigo: " +ConfiguracionesRolgar2.getIniciativaEnemigoFacil());
        }
    },

    MEDIO{
        @Override
        public String toString() {
            return "Medio";
        }

        @Override
        public void mostrarDescripcion(){
            int indice = MEDIO.ordinal() + 1;
            System.out.println(indice + ". " + MEDIO);
            System.out.println("  Niveles: " +ConfiguracionesRolgar2.getAltoMapaMedio());
            System.out.println("  Enemigos: " +ConfiguracionesRolgar2.getCantidadEnemigosMedio());
            System.out.printf("  Daño enemigo: %.0f\n", ConfiguracionesRolgar2.getDanioEnemigoMedio());
            System.out.println("  Velocidad del enemigo: " +ConfiguracionesRolgar2.getIniciativaEnemigoMedio());
        }
    },
    DIFICIL{
        @Override
        public String toString() {
            return "Difícil";
        }

        @Override
        public void mostrarDescripcion(){
            int indice = DIFICIL.ordinal() + 1;
            System.out.println(indice + ". " + DIFICIL);
            System.out.println("  Niveles: " +ConfiguracionesRolgar2.getAltoMapaDificil());
            System.out.println("  Enemigos: " +ConfiguracionesRolgar2.getCantidadEnemigosDificil());
            System.out.printf("  Daño enemigo: %.0f\n", ConfiguracionesRolgar2.getDanioEnemigoDificil());
            System.out.println("  Velocidad del enemigo: " +ConfiguracionesRolgar2.getIniciativaEnemigoDificil());
        }
    },
    CUSTOM {
        @Override
        public String toString() {
            return "Custom";
        }

        @Override
        public void mostrarDescripcion(){
            int indice = CUSTOM.ordinal() + 1;
            System.out.println(indice + ". " + CUSTOM);
            System.out.println("  Niveles: definidos por el jugador");
            System.out.println("  Enemigos: definidos por el jugador");
            System.out.println("  Dimensiones del mapa: ancho x alto x largo (definidos por el jugador)");
        }
    };

    /**
     * Muestra la descripción de la dificultad con sus parámetros.
     */
    public abstract void mostrarDescripcion();
}
