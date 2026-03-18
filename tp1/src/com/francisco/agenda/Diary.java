package com.francisco.agenda;
import java.util.ArrayList;
import java.util.List;

public class Diary {
	List<Contact> contacts = new ArrayList<>();

	public Diary() {}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
		System.out.println("Contacto agregado!");
	}

	public void listContacts() {
		int i = 1;
		for (Contact contact : this.contacts) {
			System.out.printf("Contacto n%d \n", i);
			System.out.printf("Nombre: %s \n", contact.name);
			System.out.printf("Telefono: %s \n", contact.phone);
			System.out.printf("Email: %s \n", contact.email);
			i++;
		}
	}

	public Contact findContact(String name) {
		for (Contact contact : this.contacts) {
			if (contact.name.equals(name)) return contact;
		}
		return null;
	}
}
