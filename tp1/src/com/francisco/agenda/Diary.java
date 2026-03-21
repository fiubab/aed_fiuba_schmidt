package com.francisco.agenda;
import java.util.ArrayList;
import java.util.List;

public class Diary {
	List<Contact> contacts = new ArrayList<>(10);
	FileHandler file = null;

	public Diary() {
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
		this.writeContact(contact);
		System.out.println("Contacto agregado!");
	}

	public void writeContact(Contact contact) {
		if (this.file != null) {
			this.file.writeLine(contact.name + "," + contact.phone + "," + contact.email);
		}
		return;
	}

	public void listContacts() {
		int i = 1;
		for (Contact contact : this.contacts) {
			System.out.printf("Contacto %d \n", i);
			System.out.printf("\tNombre: %s \n", contact.name);
			System.out.printf("\tTelefono: %s \n", contact.phone);
			System.out.printf("\tEmail: %s \n", contact.email);
			if (i != contacts.size()) {
				System.out.println("     ---     ");
			}
			i++;
		}
		return;
	}

	public List<Contact> contacts() {
		return this.contacts;
	}

	public Contact findContact(String name) {
		for (Contact contact : this.contacts) {
			if (contact.name.equals(name)) return contact;
		}
		return null;
	}

	public boolean hasFile() {
		return this.file != null;
	}

	public void createFile(String fileName) {
		if (file != null) {
			System.out.println("Archivo ya existente " + this.file);
			return;
		}
		this.file = new FileHandler(fileName);
		return;
	}

	public void readFromFile(String fileName) {}
}
