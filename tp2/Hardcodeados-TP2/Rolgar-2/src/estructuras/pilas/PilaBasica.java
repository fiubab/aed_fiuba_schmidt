package src.estructuras.pilas;

import java.util.List;

import src.estructuras.nodos.NodoSimplementeEnlazado;

public class PilaBasica<T> {
	//ATRIBUTOS DE CLASE --------------------------------------------------------------------------------------
	//ATRIBUTOS -----------------------------------------------------------------------------------------------

	private NodoSimplementeEnlazado<T> tope = null;
	private int tamaño = 0;

	//CONSTRUCTORES -------------------------------------------------------------------------------------------

	/**
	 * pre:
	 * post: inicializa la pila vacia para su uso
	 */
	public PilaBasica() {
		this.tope = null;
		this.tamaño = 0;
	}

	//METODOS DE CLASE ----------------------------------------------------------------------------------------
	//METODOS GENERALES ---------------------------------------------------------------------------------------
	//METODOS DE COMPORTAMIENTO -------------------------------------------------------------------------------

	/**
	 * post: indica si la cola tiene algún elemento.
	 */
	public boolean estaVacia() {
		return (this.tamaño == 0);
	}

	/**
	 * pre: el elemento no es vacio
	 * post: agrega el elemento a la pila
	 */
	public void apilar(T elemento) {
		NodoSimplementeEnlazado<T>nuevo = new NodoSimplementeEnlazado<T>(elemento);
		nuevo.setSiguiente(this.tope);
		this.tope = nuevo;
		this.tamaño++;
	}

	/**
	 * pre: el elemento no es vacio
	 * post: agrega el elemento a la pila
	 */
	public void apilar(List<T> lista) {
		//validar
		for(T dato: lista) {		
			this.apilar( dato );
		}
	}

	/**
	 * pre :
	 * post: devuelve el elemento en el tope de la pila y achica la pila en 1.
	 */
	public T desapilar() {
		T elemento = null;
		if (!this.estaVacia()) {
			elemento = this.tope.getDato();
			this.tope = this.tope.getSiguiente();
			this.tamaño--;
		}
		return elemento;
	}

	/**
	 * pre: -
	 * post: devuelve el elemento en el tope de la pila (solo lectura)
	 */
	public T obtener() {
		T elemento = null;
		if (!this.estaVacia()) {
			elemento = this.tope.getDato();
		}
		return elemento;
	}

	/**
	 * post: devuelve la cantidad de elementos que tiene la cola.
	 */
	public int contarElementos() {
		return this.tamaño;
	}

	//GETTERS SIMPLES -----------------------------------------------------------------------------------------
	//SETTERS SIMPLES -----------------------------------------------------------------------------------------	

}