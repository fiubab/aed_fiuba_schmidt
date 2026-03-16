package com.francisco.agenda;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Agenda agenda = new Agenda();

		Scanner sc = new Scanner(System.in);
		while (option != 4) {
			System.out.print("""
					AGENDA DE CONTACTOS
					1 - Agregar contacto
					2 - Listar contactos
					3 - Buscar contacto
					4 - Salir
					Seleccione una opción: """);

			try {
				int option = sc.nextInt();
				switch (option) {
					case 1:
						agregarContacto();
						break;
					case 2:
						listarContactos();
						break;
					case 3:
						buscarContacto();
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

	static void agregarContacto() {

	}
	static void listarContactos() {}
	static void buscarContacto() {}

}
