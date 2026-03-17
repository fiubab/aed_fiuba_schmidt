package com.francisco.agenda;
import java.util.ArrayList;
import java.util.List;

public class Diary {
	static List<Contact> contacts;

	public void Main() {
		contacts = new ArrayList<Contact>();
	}

	public void addContact(Contact contact) {
		contacts.add(contact);
		System.out.println("Contacto agregado!");
	}

	public void listContacts() {
		int i = 1;
		for (Contact contact : contacts) {
			System.out.printf("Contacto n%I \n", i);
			System.out.printf("Nombre: %s \n", contact.name);
			System.out.printf("Telefono: %s \n", contact.phone);
			System.out.printf("Email: %s \n", contact.email);
			i++;
		}
		return;
	}

	public Contact findContact(String name) {
		for (Contact contact : contacts) {
			if (contact.name == name) return contact;
		}
		return null;
	}
}
