package c2026_01.semana05.jueves.tarea.v1;

public class Cadena {
	//CONSTANTES
	public static double ANCHO = Eslabon.ANCHO;
	public static int ANCHO_MAXIMO = 1000000000;
	
	//ATRIBUTOS
	private int cantidadEslabones;
	private Eslabon[] cadena;
	private double largoEslabones;
	private double largo;
		
	//CONSTRUCTORES
	
	/*
	 Metodo Constructor:
	 	@parametros;
	 		-	int cantidadEslabonesMaxima: cantidad maxima de eslabones permitido para la cadena
	 		-	double largoEslabones: cuanto mide cada eslabon individual
	  	@exceptiones:
	  		- RuntimeException: si se introduce un maximo menor estricto que 1
	  	
	  */
	public Cadena(int cantidadEslabonesMaxima, double largoEslabones) {
		if (cantidadEslabones >= 1) {
			setCantidadEslabones(1);
		    setLargoEslabones(largoEslabones);
		    cadena = new Eslabon[cantidadEslabonesMaxima];
		    cadena[0] = new Eslabon(largoEslabones);
		    largo = largoEslabones;	
		}else {
			throw new RuntimeException("La cadena tiene que tener al menos un eslabon");
		}
			
	}
			
	//METODOS DE CLASE
	
	/*
	 Metodo agregarEslabon
	  	Añade un nuevo eslabon a la cadena
	  	
	  	@Excepciones:
	  		-	RuntimeException: Si ya se alcanzó la cantidad maxima ingresada de eslabones
	 */
	public void agregarEslabon(){
		if (cantidadEslabones == cadena.length) {
			throw new RuntimeException("Cantidad maxima de eslabones alcanzada");
			
		}else {
			cadena[cantidadEslabones] = new Eslabon(largoEslabones);
			cantidadEslabones++;
			largo+= largoEslabones;
			System.out.println("Se ha añadido un eslabon");
		}
			
	}

	/*
	 Metodo quitarEslabon
	  	Remueve un eslabon de la cadena
	  	
	  	@Excepciones:
	  		-	RuntimeException: Si ya se alcanzó la cantidad minima (1) de eslabones
	 */
	public void quitarEslabon() {
		if (cantidadEslabones == 1) {
			throw new RuntimeException("Cantidad minima de eslabones alcanzada");
		}else {
			cadena[cantidadEslabones] = null;
			cantidadEslabones--;
			System.out.println("Se ha quitado un eslabon");
			largo-= largoEslabones;
			}
	}
	
	/*
	 Metodo largoTotalCadena
	 	muestra el largo total de la cadena
	 */
	public void largoTotalCadena() {
		System.out.println("La cadena mide "+largo+" cm");
	}
		
		
	//GETTERS
	public int getCantidadEslabones() {
		return cantidadEslabones;
	}
	public Eslabon[] getCadena() {
		return cadena;
	}
	public double getLargo() {
		return largo;
	}
	public double getLargoEslabones() {
		return largoEslabones;
	}
				
	//SETTERS (solo se pueden setear la cantidad de Eslabones y el largo de eslabones porque los demas dependen de eso)
	public void setCantidadEslabones(int cantidadEslabones) {
		this.cantidadEslabones = cantidadEslabones;
	}
	public void setLargoEslabones(double largoEslabones) {
		this.largoEslabones = largoEslabones;
	}

}
