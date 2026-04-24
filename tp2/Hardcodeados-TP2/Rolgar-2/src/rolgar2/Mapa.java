package src.rolgar2;

import src.estructuras.listas.ListaSimplementeEnlazada;
import src.estructuras.par.Par;
import src.modelo.Celda;
import src.modelo.Elemento;
import src.modelo.Tablero;
import src.rolgar2.elemento.carta.*;
import src.rolgar2.configuracion.ConfiguracionesRolgar2;
import src.rolgar2.elemento.*;
import src.rolgar2.entidad.*;
import src.utils.Validaciones;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Clase que representa el mapa tridimensional del juego.
 * Gestiona la generación del mundo, posicionamiento de entidades y elementos.
 * 
 * <p>El mapa es un tablero 3D que contiene:</p>
 * <ul>
 *   <li>Entidades (jugadores y enemigos)</li>
 *   <li>Elementos (piedras, agua, rampas, cartas)</li>
 * </ul>
 * 
 * @param <T> tipo de entidad que contiene el mapa, debe extender {@link Entidad}
 * @param <U> tipo de elemento que contiene el mapa, debe extender {@link ElementoRolgar2}
 */
public class Mapa <T extends Entidad, U extends ElementoRolgar2> {
//INTERFACES ----------------------------------------------------------------------------------------------
//ENUMERADOS ----------------------------------------------------------------------------------------------
//CONSTANTES ----------------------------------------------------------------------------------------------
//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------

    private final Random random = new Random();

//ATRIBUTOS -----------------------------------------------------------------------------------------------
    
    private final Tablero<Par<List<T>, List<U>>> tablero;
    
//ATRIBUTOS TRANSITORIOS ----------------------------------------------------------------------------------
//CONSTRUCTORES -------------------------------------------------------------------------------------------
//METODOS ABSTRACTOS --------------------------------------------------------------------------------------
//METODOS HEREDADOS (CLASE)--------------------------------------------------------------------------------
//METODOS HEREDADOS (INTERFACE)----------------------------------------------------------------------------
//METODOS DE CLASE ----------------------------------------------------------------------------------------
//METODOS GENERALES ---------------------------------------------------------------------------------------
//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

    /**
     * Crea un mapa de dimensiones especificadas con celdas vacías.
     *
     * @param ancho ancho del mapa, debe ser mayor o igual a 3
     * @param alto alto del mapa, debe ser mayor a 0
     * @param profundo profundidad del mapa, debe ser mayor o igual a 3
     * @throws RuntimeException si las dimensiones no cumplen los requisitos mínimos
     * @pre {@code ancho >= 3 && alto > 0 && profundo >= 3}
     */
    public Mapa(int ancho, int alto, int profundo) {
        final int MINIMO_LARGO_LADO = 3; // Deber haber espacio minimo para la pared y para una entidad

        if (ancho == MINIMO_LARGO_LADO) {
            Validaciones.validarMayorIgual(profundo, MINIMO_LARGO_LADO + 1, "profundo", "4");
        } else if (profundo == MINIMO_LARGO_LADO) {
            Validaciones.validarMayorIgual(ancho, MINIMO_LARGO_LADO + 1, "profundo", "4");
        }

        Validaciones.validarMayorIgual(ancho, MINIMO_LARGO_LADO, "ancho", "3");
        Validaciones.validarMayorIgual(profundo, MINIMO_LARGO_LADO, "ancho", "3");
        Validaciones.validarMayorQueCero(alto, "alto");


        this.tablero = new Tablero<>(ancho, alto, profundo);
        inicializarCeldas();
    }

    /**
     * Inicializa todas las celdas del mapa con contenido vacío.
     * Cada celda se prepara con listas vacías para entidades y elementos.
     */
    private void inicializarCeldas(){
        for(int i = 1; i <= this.tablero.getAncho(); i++){
            for(int j = 1; j <= this.tablero.getAlto(); j++){
                for(int k = 1; k <= this.tablero.getProfundo(); k++){
                    this.tablero.getCelda(i, j, k).setContenido(new Par<>(new ListaSimplementeEnlazada<>(), new ListaSimplementeEnlazada<>()));
                }
            }
        }
    }

    /**
     * Genera el mundo completo del juego.
     * 
     * <p>Genera en orden:</p>
     * <ul>
     *     <li>Anillo de piedras en los bordes</li>
     *     <li>Rampas para cambiar de nivel</li>
     *     <li>Jugadores en posiciones aleatorias</li>
     *     <li>Enemigos en posiciones aleatorias</li>
     *     <li>Agua en posiciones aleatorias</li>
     *     <li>Cartas en posiciones aleatorias</li>
     *     <li>Piedras adicionales en posiciones aleatorias</li>
     * </ul>
     * 
     * @param jugadores lista de jugadores a colocar, no puede ser null
     * @param enemigos lista de enemigos a colocar, no puede ser null
     * @throws RuntimeException si jugadores o enemigos son null
     * @throws RuntimeException si no hay espacio suficiente para generar el mapa
     * @pre {@code jugadores != null && enemigos != null}
     */
    @SuppressWarnings("unchecked")
    public void generarMapa(List<Jugador> jugadores, List<Enemigo> enemigos) {
        Validaciones.validarDistintoDeNull(this.tablero, "tablero");
        Validaciones.validarDistintoDeNull(jugadores, "jugadores");
        Validaciones.validarDistintoDeNull(enemigos, "enemigos");

        // Valido que haya espacio minimo teórico para generar mapa.
        Validaciones.validarMayorIgual(getCantidadEspacioVacio(), getEspacioMinimoRequerido(jugadores, enemigos), "espacio vacio", "espacio minimo");

        // Valido que se puedan generar todas las rampas necesarias.
        validarPosibleGeneracionRampas();

        generarAnilloPiedras();
        generarRampas();
        agregarPersonajes((List<T>) jugadores);
        agregarPersonajes((List<T>) enemigos);

        // Agrego el resto de elementos al mapa
        try { generarAgua(); } catch (RuntimeException e) { return; }
        try { generarCartas(); } catch (RuntimeException e) { return; }
        try { generarPiedras(); } catch (RuntimeException _) {}
    }

    /**
     * Calcula el espacio mínimo requerido para generar el mapa.
     * 
     * <p>Considera:</p>
     * <ul>
     *   <li>2 rampas por piso (excepto primer y último con 1)</li>
     *   <li>Anillo de piedras en los bordes</li>
     *   <li>Espacio para cada jugador y enemigo</li>
     * </ul>
     * 
     * @param jugadores lista de jugadores, no puede ser null
     * @param enemigos lista de enemigos, no puede ser null
     * @return la cantidad mínima de celdas requeridas
     * @pre {@code jugadores != null && enemigos != null}
     */
    private int getEspacioMinimoRequerido(List<Jugador> jugadores, List<Enemigo> enemigos) {
        // 2 para cada piso y 1 para el primer y último piso.
        final int CANTIDAD_TOTAL_RAMPAS = (this.getAlto() - 1) * 2;
        // Anillo de piedras al rededor del mapa: ((ancho * profundo) - ((ancho - 2) * (profundo - 2))) * alto
        final int CANTIDAD_PIEDRAS_ANILLO_EXTERIOR = (((this.getAncho() * this.getProfundo()) - ((this.getAncho() - 2) * (this.getProfundo() - 2))) * this.getAlto());
        // 1 espacio libre para cada jugador.
        final int CANTIDAD_JUGADORES = jugadores.size();
        // 1 espacio libre para cada enemigo.
        final int CANTIDAD_ENEMIGOS = enemigos.size();

        return CANTIDAD_JUGADORES + CANTIDAD_ENEMIGOS + CANTIDAD_TOTAL_RAMPAS + CANTIDAD_PIEDRAS_ANILLO_EXTERIOR;
    }

    /**
     * Genera piedras aleatoriamente en el mapa.
     * 
     * @throws RuntimeException si el espacio libre del mapa es menor al 25%
     */
    private void generarPiedras(){
        validarEspacioDisponible();

        int espacioVacio = getCantidadEspacioVacio();

        //Añado las piedras aleatoriamente dentro del mapa
        for(int i = 1; i <= this.getAncho(); i++){
            for(int j = 1; j <= this.getAlto(); j++){
                for(int k = 1; k <= this.getProfundo(); k++){
                    int valorRandom = random.nextInt(1, espacioVacio + 1);

                    if (valorRandom < espacioVacio * ConfiguracionesRolgar2.getProbabilidadGenerarPiedra()) {
                        int[] posicion = {i, j, k};

                        if (!esPosicionValidaPiedra(posicion)) {
                            continue;
                        }

                        agregarElemento(new Piedra(), posicion);
                        espacioVacio--;
                    }
                }
            }
        }
    }

    private boolean esPosicionValidaPiedra(int[] posicion) {
        if (!celdaVacia(posicion)) {
            return false;
        }

        Celda<Par<List<T>, List<U>>>[][][] vecinos = getCelda(posicion).getVecinos();

        for (int i = 0; i < vecinos.length; i++) {
            for (int j = 0; j < vecinos[0].length; j++) {
                Celda<Par<List<T>, List<U>>> vecino = vecinos[i][1][j];

                if (vecino == null) {
                    continue;
                }

                Rampa rampa = getRampa(vecino);

                if (rampa != null && Arrays.equals(posicion, getVecino(vecino, rampa.getDireccion()).getPosicion())) {
                    return false;
                }
            }
        }

        return true;
    }

    private Rampa getRampa(Celda<Par<List<T>, List<U>>> celda){
        for (U elemento : getElementos(celda)) {
            if (elemento instanceof Rampa rampa) {
                return rampa;
            }
        }

        return null;
    }

    /**
     * <p>Genera un anillo de piedras al rededor de cada piso del mapa.</p>
     * <p>Recorre todas las celdas del tablero, y cuando llega a un borde crea y agrega una piedra.</p>
     */
    private void generarAnilloPiedras(){
        for(int i = 1; i <= this.getAncho(); i++){
            for(int j = 1; j <= this.getAlto(); j++){
                for(int k = 1; k <= this.getProfundo(); k++){
                    if(i == 1 || i == this.getAncho() || k == 1 || k == this.getProfundo()){
                        int[] posicion = {i, j, k};
                        agregarElemento(new Piedra(), posicion);
                    }
                }
            }
        }
    }

    /**
     * Crea elementos de tipo {@link Agua} y los agrega al mapa
     * @throws RuntimeException si el espacio libre del mapa es menos del 25%
     */
    private void generarAgua(){
        validarEspacioDisponible();

        final int MAXIMO_NIVEL_AGUA = 3;
        int espacioVacio = getCantidadEspacioVacio();

        //Añado agua aleatoriamente dentro del mapa
        for(int i = 1; i <= this.getAncho(); i++){
            for(int j = 1; j <= this.getAlto(); j++){
                for(int k = 1; k <= this.getProfundo(); k++){
                    int randomValue = random.nextInt(1, espacioVacio + 1);

                    if (randomValue < espacioVacio * ConfiguracionesRolgar2.getProbabilidadGenerarAgua()) {
                        int[] posicion = {i, j, k};

                        if (!esCeldaTraspasable(getCelda(posicion)) || hayPersonaje(posicion)) {
                            continue;
                        }

                        int randomNivel = random.nextInt(1, MAXIMO_NIVEL_AGUA + 1);
                        Agua agua = new Agua(randomNivel);
                        agregarElemento(agua, posicion);
                        espacioVacio--;
                    }
                }
            }
        }
    }

    /**
     * Crea elementos de tipo {@link Carta} y los agrega al mapa
     * @throws RuntimeException si el espacio libre del mapa es menos del 25%
     */
    private void generarCartas(){
        validarEspacioDisponible();

        int espacioVacio = getCantidadEspacioVacio();

        //Añado carta aleatoriamente dentro del mapa
        for(int i = 1; i <= this.getAncho(); i++){
            for(int j = 1; j <= this.getAlto(); j++){
                for(int k = 1; k <= this.getProfundo(); k++){
                    int randomValue = random.nextInt(1, espacioVacio + 1);

                    if (randomValue < espacioVacio * ConfiguracionesRolgar2.getProbabilidadGenerarCarta()) {
                        int[] posicion = {i, j, k};

                        if (!esCeldaTraspasable(getCelda(posicion)) || hayPersonaje(posicion)) {
                            continue;
                        }

                        TipoCarta tipoRandom = TipoCarta.values()[random.nextInt(TipoCarta.values().length)];
                        Carta nuevaCarta = tipoRandom.crear();

                        agregarElemento(nuevaCarta, posicion);
                        espacioVacio--;
                    }
                }
            }
        }
    }

    /**
     * Crea elementos de tipo {@link Rampa} y los agrega al mapa.
     * Para cada nivel genera una rampa de subida y otra de bajada en el nivel superior,
     * garantizando que siempre se puedan generar en posiciones válidas.
     *
     * @throws RuntimeException si no se pueden generar rampas en algún nivel después de múltiples intentos
     */
    private void generarRampas() {
        for (int nivel = this.getAlto(); nivel > 1; nivel--) {
            boolean rampaGenerada = false;

            while (!rampaGenerada) {
                int[] posicionInferior = generarPosicionAleatoria();
                posicionInferior[1] = nivel; // Establezco que la altura sea el nivel actual
                int[] posicionSuperior = new int[]{posicionInferior[0], nivel - 1, posicionInferior[2]};

                if (!celdaVacia(posicionInferior) || !celdaVacia(posicionSuperior)) {
                    continue;
                }

                List<Direcciones> direccionesValidas = getDireccionesValidasRampa(posicionInferior, posicionSuperior);

                if (direccionesValidas.isEmpty()) {
                    continue;
                }

                // Elegir una dirección aleatoria de las válidas
                Direcciones direccionElegida = direccionesValidas.get(random.nextInt(direccionesValidas.size()));

                // Crear y agregar las rampas
                Rampa rampaSubida = new Rampa(direccionElegida, Propiedades.PERMITE_SUBIR);
                Rampa rampaBajada = new Rampa(getDireccionContraria(direccionElegida), Propiedades.PERMITE_BAJAR);

                agregarElemento(rampaSubida, posicionInferior);
                agregarElemento(rampaBajada, posicionSuperior);

                rampaGenerada = true;
            }
        }
    }

    /**
     * Obtiene las direcciones válidas para colocar un par de rampas.
     * Una dirección es válida si:
     * - La rampa inferior tiene su vecino en esa dirección traspasable o vacío
     * - La rampa superior tiene su vecino en la dirección contraria traspasable o vacío
     *
     * @param posicionInferior Posición de la rampa de subida
     * @param posicionSuperior Posición de la rampa de bajada
     * @return Lista de direcciones válidas
     */
    private List<Direcciones> getDireccionesValidasRampa(int[] posicionInferior, int[] posicionSuperior) {
        List<Direcciones> direccionesValidas = new ListaSimplementeEnlazada<>();

        for (Direcciones direccion : Direcciones.values()) {
            Direcciones direccionContraria = getDireccionContraria(direccion);

            // Obtener vecino en la dirección para la rampa inferior
            Celda<Par<List<T>, List<U>>> vecinoInferior = getVecino(getCelda(posicionInferior).getPosicion(), direccion);
            // Obtener vecino en la dirección contraria para la rampa superior
            Celda<Par<List<T>, List<U>>> vecinoSuperior = getVecino(getCelda(posicionSuperior).getPosicion(), direccionContraria);

            // Verificar que ambos vecinos sean válidos (existan, estén vacíos o sean traspasables)
            if (esVecinoValidoParaRampa(vecinoInferior) && esVecinoValidoParaRampa(vecinoSuperior)) {
                direccionesValidas.add(direccion);
            }
        }

        return direccionesValidas;
    }

    /**
     * <p>Obtiene la celda vecina en una dirección específica.</p>
     *
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @param direccion Dirección del vecino
     * @return Celda vecina o null si no existe
     * @throws RuntimeException si la posición es {@code null} o inválida
     */
    public Celda<Par<List<T>, List<U>>> getVecino(int[] posicion, Direcciones direccion) {
        validarPosicion(posicion);
        return getVecino(getCelda(posicion), direccion);
    }

    /**
     * <p>Obtiene la celda vecina en una dirección específica.</p>
     *
     * @param celda Celda de referencia
     * @param direccion Dirección del vecino
     * @return Celda vecina o null si no existe
     */
    public Celda<Par<List<T>, List<U>>> getVecino(Celda<Par<List<T>, List<U>>> celda, Direcciones direccion) {
        if (celda == null) {
            return null;
        }

        Celda<Par<List<T>, List<U>>>[][][] vecinos = celda.getVecinos();

        final int ARRIBA = 0;
        final int ABAJO = 2;
        final int IZQUIERDA = 0;
        final int DERECHA = 2;
        final int CENTRO = 1;
        final int NIVEL = 1;

        // Mapeo de direcciones a índices en la matriz de vecinos [x][y][z]
        // Centro es [1][1][1], entonces:
        return switch (direccion) {
            case ARRIBA_IZQUIERDA -> vecinos[IZQUIERDA][NIVEL][ARRIBA];
            case ARRIBA -> vecinos[CENTRO][NIVEL][ARRIBA];
            case ARRIBA_DERECHA -> vecinos[DERECHA][NIVEL][ARRIBA];
            case IZQUIERDA -> vecinos[IZQUIERDA][NIVEL][CENTRO];
            case DERECHA -> vecinos[DERECHA][NIVEL][CENTRO];
            case ABAJO_IZQUIERDA -> vecinos[IZQUIERDA][NIVEL][ABAJO];
            case ABAJO -> vecinos[CENTRO][NIVEL][ABAJO];
            case ABAJO_DERECHA -> vecinos[DERECHA][NIVEL][ABAJO];
        };
    }

    /**
     * Verifica si un vecino es válido para colocar una rampa.
     * Un vecino es válido si:
     * - No existe (borde del mapa - se permite)
     * - Está vacío
     * - Solo contiene elementos traspasables
     *
     * @param vecino Celda vecina a verificar
     * @return true si el vecino es válido
     */
    private boolean esVecinoValidoParaRampa(Celda<Par<List<T>, List<U>>> vecino) {
        if (vecino == null) {
            return false;
        }

        // verifico que no tenga personajes
        if (vecino.getContenido().getValor1() != null && !this.getEntidades(vecino).isEmpty()) {
            return false;
        }

        return esCeldaTraspasable(vecino);
    }

    /**
     * Agrega todos los personajes de la lista pasada por parametro al mapa
     * @param personajes debe ser distinto de {@code null}
     */
    private void agregarPersonajes(List<T> personajes){
        if (personajes == null) return;

        for (T personaje : personajes){
            agregarPersonaje(personaje);
        }
    }

    /**
     * <p>Coloca un personaje en una posicion aleatoria del mapa.</p>
     * <p>Para agregarlo la celda debe estar vacia y debe tener minimo una celda vecina vacia.</p>
     * @param personaje debe ser distinto de {@code null}
     */
    private void agregarPersonaje(T personaje) {
        Validaciones.validarDistintoDeNull(personaje, "personaje");

        int[] posicion;

        do {
            posicion = generarPosicionAleatoria();
        } while (!celdaVacia(posicion) || !tieneVecinoTraspasable(posicion));

        agregarPersonaje(personaje, posicion);
    }

    /**
     * <p>Agrega un personaje en una posición específica del mapa.</p>
     *
     * @param personaje debe ser distinto de {@code null}
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @throws RuntimeException si el personaje o la posición son {@code null}, si la posición es inválida,
     *                          o si el personaje ya existe en la celda
     */
    public void agregarPersonaje(T personaje, int[] posicion) {
        validarPosicion(posicion);
        Validaciones.validarDistintoDeNull(personaje, "personaje");

        Validaciones.validarFalse(getCelda(posicion).getContenido().getValor1().contains(personaje), "personaje");

        getCelda(posicion).getContenido().getValor1().add(personaje);
        personaje.setPosicion(posicion);
    }

    /**
     * <p>Quita un personaje de una posición específica del mapa.</p>
     *
     * @param personaje debe ser distinto de {@code null}
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @throws RuntimeException si el personaje o la posición son {@code null}, si la posición es inválida,
     *                          o si el personaje no existe en la celda
     */
    public void quitarPersonaje(T personaje, int[] posicion) {
        validarPosicion(posicion);
        Validaciones.validarDistintoDeNull(personaje, "personaje");

        Celda<Par<List<T>, List<U>>> celda = getCelda(posicion);

        Validaciones.validarTrue(celda.getContenido().getValor1().contains(personaje), "personaje");

        celda.getContenido().getValor1().remove(personaje);

    }

    /**
     * <p>Agrega un elemento en una posición específica del mapa.</p>
     *
     * @param elemento debe ser distinto de {@code null}
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @throws RuntimeException si el elemento o la posición son {@code null}, o si la posición es inválida
     */
    @SuppressWarnings("unchecked")
    private void agregarElemento(Elemento elemento, int[] posicion) {
        validarPosicion(posicion);
        Validaciones.validarDistintoDeNull(elemento, "elemento");

        getCelda(posicion).getContenido().getValor2().add((U) elemento);
    }

    /**
     * <p>Quita un elemento de una posición específica del mapa.</p>
     *
     * @param elemento debe ser distinto de {@code null}
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @throws RuntimeException si el elemento o la posición son {@code null}, si la posición es inválida,
     *                          o si el elemento no existe en la celda
     */
    public void quitarElemento(U elemento, int[] posicion) {
        validarPosicion(posicion);
        Validaciones.validarDistintoDeNull(elemento, "elemento");

        Validaciones.validarTrue(getElementos(posicion).contains(elemento), "elemento");

        getCelda(posicion).getContenido().getValor2().remove(elemento);
    }

    /**
     * Valida que sea posible generar las rampas necesarias en cada nivel del mapa.
     * - Cada nivel intermedio debe poder tener al menos 2 rampas
     * - El primer nivel (más alto) debe poder tener al menos 1 rampa de bajada
     * - El último nivel (más bajo) debe poder tener al menos 1 rampa de subida
     *
     * @throws RuntimeException si no es posible generar las rampas necesarias en algún nivel
     */
    private void validarPosibleGeneracionRampas() {
        for (int nivel = this.getAlto(); nivel > 1; nivel--) {
            int cantidadPosicionesValidasRampa = contarPosicionesValidasRampas(nivel);
            int rampasMinimasRequeridas = getRampasMinimasRequeridas(nivel);

            Validaciones.validarMayorIgual(cantidadPosicionesValidasRampa, rampasMinimasRequeridas, "cantidad posiciones validas rampa", "cantidad minima rampas requerida");
        }
    }

    /**
     * Cuenta cuántas posiciones válidas existen para colocar rampas en un nivel específico.
     *
     * @param nivel El nivel a verificar
     * @return Cantidad de posiciones válidas encontradas
     */
    private int contarPosicionesValidasRampas(int nivel) {
        int cantidadPosicionesValidas = 0;

        // Recorrer todas las posiciones posibles en este nivel
        for (int x = 1; x <= this.getAncho(); x++) {
            for (int z = 1; z <= this.getProfundo(); z++) {
                int[] posicionInferior = new int[]{x, nivel, z};
                int[] posicionSuperior = new int[]{x, nivel - 1, z};

                if (!celdaVacia(posicionInferior) || !celdaVacia(posicionSuperior)) {
                    continue;
                }

                // Verificar si existe al menos una dirección válida
                List<Direcciones> direccionesValidas = getDireccionesValidasRampa(posicionInferior, posicionSuperior);

                if (!direccionesValidas.isEmpty()) {
                    cantidadPosicionesValidas++;
                }
            }
        }

        return cantidadPosicionesValidas;
    }

    /**
     * Determina cuántas rampas mínimas se requieren en un nivel específico.
     * - Primer nivel (más alto): 1 rampa de bajada
     * - Último nivel (más bajo): 1 rampa de subida
     * - Niveles intermedios: 2 rampas (1 de subida y 1 de bajada)
     *
     * @param nivel El nivel a verificar
     * @return Cantidad mínima de rampas requeridas
     */
    private int getRampasMinimasRequeridas(int nivel) {
        if (nivel == this.getAlto() || nivel == 1) { // El primer nivel (más alto) y el último nivel (más bajo) requieren 1 rampa
            return 1;
        }

        return 2;
    }

    /**
     * Valida que haya espacio libre en el mapa
     */
    private void validarEspacioDisponible() {
        Validaciones.validarMayorIgual(getCantidadEspacioVacio(), this.tablero.getCantidadDeCeldas() * ConfiguracionesRolgar2.getPorcentajeMaximaOcupacionMapa(), "espacio libre", "capacidad maxima");
    }

    /**
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero.
     */
    private void validarPosicion(int[] posicion) {
        Validaciones.validarDistintoDeNull(posicion, "posicion");

        for (int i = 0; i < posicion.length; i++) {
            Validaciones.validarMayorQueCero(posicion[i], "posicion [" + i + "]");
        }
    }

    /**
     * Genera una posicion aleatoria dependiendo de las dimensiones del tablero
     * @return una posicion aleatoria.
     * @throws RuntimeException si el tablero es {@code null}
     */
    public int[] generarPosicionAleatoria() {
        Validaciones.validarDistintoDeNull(this.tablero, "tablero");

        int nuevaPosicionX = random.nextInt(1, this.getAncho() + 1);
        int nuevaPosicionY = random.nextInt(1, this.getAlto() + 1);
        int nuevaPosicionZ = random.nextInt(1, this.getProfundo() + 1);

        return new int[]{nuevaPosicionX, nuevaPosicionY, nuevaPosicionZ};
    }

//METODOS DE CONSULTA DE ESTADO ---------------------------------------------------------------------------

    /**
     * @return la cantidad de espacio vacio actualmente en el mapa.
     */
    public int getCantidadEspacioVacio(){
        Validaciones.validarDistintoDeNull(this.tablero, "tablero");

        int resultado = 0;

        for(int i = 1; i <= this.getAncho(); i++){
            for(int j = 1; j <= this.getAlto(); j++){
                for(int k = 1; k <= this.getProfundo(); k++){
                    if (celdaVacia(tablero.getCelda(i, j, k).getPosicion())) {
                        resultado++;
                    }
                }
            }
        }

        return resultado;
    }

    /**
     * Una celda se considera vacia si su lista de personajes y de enemigos son {@code null} o están vacias.
     * @param posicion debe ser distinto de {@code null}.
     * @return si la celda esta vacia.
     */
    public boolean celdaVacia(int[] posicion){
        validarPosicion(posicion);

        return celdaVacia(getCelda(posicion));
    }

    /**
     * Una celda se considera vacia si su lista de personajes y de enemigos son {@code null} o están vacias.
     * @param celda debe ser distinto de {@code null}.
     * @return si la celda esta vacia.
     */
    public boolean celdaVacia(Celda<Par<List<T>, List<U>>> celda){
        Validaciones.validarDistintoDeNull(celda, "celda");
        return (getElementos(celda) == null || getElementos(celda).isEmpty()) &&
                (getEntidades(celda) == null || getEntidades(celda).isEmpty());
    }

    /**
     * @param posicion no puede ser {@code null} y sus coordenadas deben ser mayores que cero.
     * @return sí hay minimo un vecino libre al rededor de la posicion en su misma altura pasada por parametro.
     */
    public boolean tieneVecinoTraspasable(int[] posicion){
        validarPosicion(posicion);

        Celda<Par<List<T>, List<U>>>[][][] vecinos = getCelda(posicion).getVecinos();

        // Para cada vecino en el mismo nivel de la celda verifico si hay minimo un vecino sin elementos no traspasables
        for (int i = 0; i < vecinos.length; i++) {
            for (int j = 0; j < vecinos[i].length; j++) {
                if (i == 1 && j == 1) { // celda central
                    continue;
                }

                Celda<Par<List<T>, List<U>>> vecino = vecinos[i][1][j];

                if ((vecino == null) || (!esCeldaTraspasable(vecino))) {
                    continue;
                }

                return true;
            }
        }

        return false;
    }

    /**
     * <p>Verifica si una celda en la posición especificada es traspasable.</p>
     * <p>Una celda es traspasable si no contiene elementos con la propiedad {@link Propiedades#NO_TRASPASABLE}.</p>
     *
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @return {@code true} si la celda es traspasable, {@code false} en caso contrario
     * @throws RuntimeException si la posición es {@code null} o inválida
     */
    public boolean esCeldaTraspasable(int[] posicion){
        validarPosicion(posicion);

        return esCeldaTraspasable(getCelda(posicion));
    }


    /**
     * <p>Verifica si una celda es traspasable.</p>
     * <p>Una celda es traspasable si no contiene elementos con la propiedad {@link Propiedades#NO_TRASPASABLE}.</p>
     * <p>Solo verifica elementos de tipo {@link ElementoRolgar2}.</p>
     *
     * @param celda debe ser distinto de {@code null}
     * @return {@code true} si la celda es traspasable, {@code false} en caso contrario
     * @throws RuntimeException si la celda es {@code null}
     */
    public boolean esCeldaTraspasable(Celda<Par<List<T>, List<U>>> celda){
        Validaciones.validarDistintoDeNull(celda, "celda");

        for (ElementoRolgar2 elemento : this.getElementos(celda)) {
            if (elemento.getPropiedades().contains(Propiedades.NO_TRASPASABLE)) {
                return false;
            }
        }

        return true;
    }

    /**
     * <p>Verifica si hay al menos un personaje en la posición especificada.</p>
     *
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @return {@code true} si hay al menos un personaje en la celda, {@code false} en caso contrario
     * @throws RuntimeException si la posición es {@code null} o inválida
     */
    public boolean hayPersonaje(int[] posicion){
        validarPosicion(posicion);
        return hayPersonaje(getCelda(posicion));
    }

    /**
     * <p>Verifica si hay al menos un personaje en la celda especificada.</p>
     *
     * @param celda debe ser distinto de {@code null}
     * @return {@code true} si hay al menos un personaje en la celda, {@code false} en caso contrario
     * @throws RuntimeException si la celda es {@code null}
     */
    public boolean hayPersonaje(Celda<Par<List<T>, List<U>>> celda) {
        Validaciones.validarDistintoDeNull(celda, "celda");
        return celda.getContenido().getValor1() != null && !celda.getContenido().getValor1().isEmpty();
    }

//GETTERS REDEFINIDOS -------------------------------------------------------------------------------------
//GETTERS INICIALIZADOS -----------------------------------------------------------------------------------
//GETTERS COMPLEJOS ---------------------------------------------------------------------------------------

    /**
     * <p>Obtiene la dirección contraria a la dirección especificada.</p>
     *
     * @param direccion la dirección de la cual se quiere obtener la contraria
     * @return la dirección contraria, o {@code null} si la dirección no es válida
     */
    public Direcciones getDireccionContraria(Direcciones direccion) {
        switch (direccion) {
            case ARRIBA_IZQUIERDA -> { return Direcciones.ABAJO_DERECHA; }
            case ARRIBA -> { return Direcciones.ABAJO; }
            case ARRIBA_DERECHA ->  { return Direcciones.ABAJO_IZQUIERDA; }
            case IZQUIERDA -> { return Direcciones.DERECHA; }
            case DERECHA -> { return Direcciones.IZQUIERDA; }
            case ABAJO_IZQUIERDA ->  { return Direcciones.ARRIBA_DERECHA; }
            case ABAJO ->  { return Direcciones.ARRIBA; }
            case ABAJO_DERECHA ->   { return Direcciones.ARRIBA_IZQUIERDA; }
        }

        return null;
    }



//GETTERS SIMPLES -----------------------------------------------------------------------------------------

    /**
     * <p>Obtiene la lista de personajes en la posición especificada.</p>
     *
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @return la lista de personajes en la celda
     * @throws RuntimeException si la posición es {@code null} o inválida, o si la celda es {@code null}
     */
    public List<T> getEntidades(int[] posicion){
        Validaciones.validarDistintoDeNull(getCelda(posicion), "celda");

        return getEntidades(getCelda(posicion));
    }

    public List<T> getEntidades(Celda<Par<List<T>, List<U>>> celda){
        Validaciones.validarDistintoDeNull(celda, "celda");

        return celda.getContenido().getValor1();
    }

    /**
     * <p>Obtiene la lista de elementos en la posición especificada.</p>
     *
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @return la lista de elementos en la celda
     * @throws RuntimeException si la posición es {@code null} o inválida, o si la celda es {@code null}
     */
    public List<U> getElementos(int[] posicion){
        Celda<Par<List<T>, List<U>>> celda = getCelda(posicion);
        Validaciones.validarDistintoDeNull(celda, "celda");

        return getElementos(celda);
    }

    public List<U> getElementos(Celda<Par<List<T>, List<U>>> celda){
        Validaciones.validarDistintoDeNull(celda, "celda");

        return celda.getContenido().getValor2();
    }

    /**
     * <p>Obtiene el ancho del tablero.</p>
     *
     * @return el ancho del tablero
     */
    public int getAncho(){
        return this.tablero.getAncho();
    }

    /**
     * <p>Obtiene la altura del tablero.</p>
     *
     * @return la altura del tablero
     */
    public int getAlto(){
        return this.tablero.getAlto();
    }

    /**
     * <p>Obtiene la profundidad del tablero.</p>
     *
     * @return la profundidad del tablero
     */
    public int getProfundo(){
        return this.tablero.getProfundo();
    }

    /**
     * <p>Obtiene la celda en la posición especificada.</p>
     *
     * @param posicion debe ser distinto de {@code null} y cada coordenada debe ser mayor que cero
     * @return la celda en la posición especificada
     * @throws RuntimeException si la posición es {@code null} o inválida
     */
    public Celda<Par<List<T>, List<U>>> getCelda(int[] posicion) {
        validarPosicion(posicion);

        return this.tablero.getCelda(posicion[0], posicion[1], posicion[2]);
    }

//SETTERS COMPLEJOS----------------------------------------------------------------------------------------
//SETTERS SIMPLES -----------------------------------------------------------------------------------------
}