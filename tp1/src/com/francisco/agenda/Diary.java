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

	public Contact findContact(String name) {
		for (Contact contact : this.contacts) {
			if (contact.name.equals(name)) return contact;
		}
		return null;
	}
}
