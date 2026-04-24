package com.francisco.agenda;
import java.util.ArrayList;
import java.util.List;

// esta clase guardara y mostrara los contactos
public class Agenda {
	List<Contacto> contacts;
	FileHandler file;

	public Agenda() {
		this.contacts = new ArrayList<>(10);
		this.file = null;
	}

	public void addContact(Contacto contact) {
		// agregamos el contacto a nuestra estructura de datos
		this.contacts.add(contact);
		// agregamos el contacto al archivo indicado
		this.writeContact(contact);
		System.out.println("Contacto agregado!");
	}

	public void writeContact(Contacto contact) {
		// si no hay archivo no haremos nada
		if (this.file != null) {
			this.file.writeLine(contact.name + "," + contact.phone + "," + contact.email);
			contact.saved = true;
		}
	}

	public void listContacts() {
		int i = 1;
		for (Contacto contact : this.contacts) {
			System.out.printf("Contacto %d \n", i);
			System.out.printf("\tNombre: %s \n", contact.name);
			System.out.printf("\tTelefono: %s \n", contact.phone);
			System.out.printf("\tEmail: %s \n", contact.email);
			if (i != contacts.size()) {
				System.out.println();
			}
			i++;
		}
	}

	public List<Contacto> contacts() {
		return this.contacts;
	}

	public Contacto findContact(String name) {
		for (Contacto contact : this.contacts) {
			if (contact.name.equals(name)) return contact;
		}
		return null;
	}

	public boolean hasFile() {
		return this.file != null;
	}

	public void createFile(String fileName) {
		this.file = new FileHandler(fileName);
	}
}
