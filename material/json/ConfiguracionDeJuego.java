package material.json;

public class ConfiguracionDeJuego {
	private int ancho;
	private int alto;
	private int profundo;
	private int[] jugadores = new int[5];
	private SubConfiguracionDeJuego[] subConfiguracionDeJuego = new SubConfiguracionDeJuego[10];
	
	
	public int getAncho() {
		return ancho;
	}
	public int getAlto() {
		return alto;
	}
	public int getProfundo() {
		return profundo;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public void setProfundo(int profundo) {
		this.profundo = profundo;
	}
	public SubConfiguracionDeJuego[] getSubConfiguracionDeJuego() {
		return subConfiguracionDeJuego;
	}
	public void setSubConfiguracionDeJuego(SubConfiguracionDeJuego[] subConfiguracionDeJuego) {
		this.subConfiguracionDeJuego = subConfiguracionDeJuego;
	}
	public int[] getJugadores() {
		return jugadores;
	}
	public void setJugadores(int[] jugadores) {
		this.jugadores = jugadores;
	}
	
	
}
