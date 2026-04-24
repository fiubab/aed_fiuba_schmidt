package src.rolgar2.elemento;

/**
 * Enum que define las propiedades que pueden tener los elementos del juego.
 * 
 * <p>Propiedades disponibles:</p>
 * <ul>
 *   <li>TRASPASABLE: El elemento permite el paso de entidades</li>
 *   <li>NO_TRASPASABLE: El elemento bloquea el paso de entidades</li>
 *   <li>PERMITE_SUBIR: La rampa permite subir de nivel</li>
 *   <li>PERMITE_BAJAR: La rampa permite bajar de nivel</li>
 * </ul>
 */
public enum Propiedades {
    TRASPASABLE,
    NO_TRASPASABLE,
    PERMITE_SUBIR,
    PERMITE_BAJAR,
}
