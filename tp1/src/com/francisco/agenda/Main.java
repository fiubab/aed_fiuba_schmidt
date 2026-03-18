package com.francisco.agenda;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	static void main(String[] args) {
		Diary diary = new Diary();
		Scanner sc = new Scanner(System.in);
		int option = 0;
		while (option != 4) {
			System.out.print("""
					AGENDA DE CONTACTOS
					1 - Agregar contacto
					2 - Listar contactos
					3 - Buscar contacto
					4 - Salir
					Seleccione una opción:\t""");

			try {
				option = sc.nextInt();
				switch (option) {
					case 1:
						addContact(diary, sc);
						break;
					case 2:
						listContacts();
						break;
					case 3:
						findContact();
						break;
					case 4:
						System.out.println("Exiting...");
						System.exit(0);
						break;
					default:
						System.out.println("Por favor seleccione una opcion valida");
				}
			} catch (InputMismatchException e) {
				System.out.println();
			}
		}
	}

	static void addContact(Diary diary, Scanner sc) {
		System.out.println("Inserte datos de contacto");
		System.out.print("Nombre: ");
		String name = sc.nextLine();
		System.out.print("Telefono: ");
		int phone = sc.nextInt();
		System.out.print("Email: ");
		String email = sc.nextLine();
		Contact c = new Contact(name, phone, email);
		diary.addContact(c);
	}
	static void listContacts() {}
	static void findContact() {}

}
