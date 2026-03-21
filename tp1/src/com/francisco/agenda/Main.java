package com.francisco.agenda;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static void main(String[] args) {
		Diary diary = new Diary();
		Scanner sc = new Scanner(System.in);

		boolean exec = true;
		while (exec) {
			System.out.print("""
					-------------------
					AGENDA DE CONTACTOS
					1 - Agregar contacto
					2 - Listar contactos
					3 - Buscar contacto
					4 - Guardar contactos en archivo
					0 - Salir
					Seleccione una opción:\t""");
			try {
				// por problemas con el buffer de Scanner usamos siempre nextLine y parseamos a int
				// porque al usar nextInt queda algo en el buffer y se saltea el nextLine siguiente
				int option = Integer.parseInt(sc.nextLine());
				switch (option) {
					case 1:
						addContact(diary, sc);
						break;
					case 2:
						listContacts(diary);
						break;
					case 3:
						findContact(diary, sc);
						break;
					case 4:
						contactsFile(diary, sc);
						break;
					case 0:
						System.out.println("Exiting...");
						exec = false;
						break;
					default:
						System.out.println("Por favor seleccione una opcion valida");
				}
			} catch (NumberFormatException e) {
					System.out.println("Opcion ingresada no valida");
			}
		}
		sc.close();
	}

	static void addContact(Diary diary, Scanner sc) {
		System.out.println("Inserte datos de contacto");

		try {
			System.out.print("Nombre: ");
			String name = sc.nextLine();

			System.out.print("Telefono: ");
			int phone = Integer.parseInt(sc.nextLine());

			System.out.print("Email: ");
			String email = sc.nextLine();

			Contact c = new Contact(name, phone, email);
			diary.addContact(c);
			return;
		} catch (InputMismatchException | NumberFormatException e) {
			System.out.println("Opcion ingresada no valida");
			addContact(diary, sc);
		}
	}

	static void listContacts(Diary diary) {
		if (diary.contacts.isEmpty()) {
			System.out.println("Agenda vacia");
			return;
		}
		if (diary.contacts.size() == 10) {
			System.out.println("Agenda llena");
			return;
		}
		diary.listContacts();
		return;
		}

	static void findContact(Diary diary, Scanner sc) {
		if (diary.contacts.isEmpty()) {
			System.out.println("Agenda vacia");
			return;
		}
		System.out.print("Nombre de contacto: ");
		String name = sc.nextLine();
		Contact c = diary.findContact(name);
		if (c == null) {
			System.out.println("No encontrado");
			return;
		}
		System.out.println("Nombre: " + c.name);
		System.out.println("Telefono: " + c.phone);
		System.out.println("Email: " + c.email);
		return;
	}

	static void saveContacts(Diary diary) {
		if (diary.hasFile()) {
			for (Contact contact : diary.contacts()) {
				diary.writeContact(contact);
			}
		}
		return;
	}

	static void contactsFile(Diary diary, Scanner sc) {
		if (!diary.hasFile()) {
			System.out.print("Ingrese nombre del archivo: ");
			String fileName = sc.nextLine();
			diary.createFile(fileName);
		}
		if (diary.contacts().isEmpty()) {
			return;
		}
		saveContacts(diary);
		System.out.println("Contactos guardados");
		return;
		}
}
