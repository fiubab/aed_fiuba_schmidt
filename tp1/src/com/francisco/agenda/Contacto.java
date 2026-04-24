package com.francisco.agenda;

public class Contacto {
	public String name;
	public int phone;
	public String email;
	public boolean saved;

	public Contacto(	String name, int phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.saved = false;
	}
}
