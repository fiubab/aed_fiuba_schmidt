package com.francisco.agenda;
import java.util.List

public class Agenda {
	List<Contacto> agendaContactos;

	public void Main(List<Contacto> contactos) {
		this.agendaContactos = contactos;
	}

	public void agregarContacto(Contacto contacto) {
		this.agendaContactos.add(contacto);
		System.out.println("Contacto agregado correctamente!");
	}

	public Contacto[] listarContactos() {}

	public Contacto buscarContacto(Contacto contacto) {}
}
