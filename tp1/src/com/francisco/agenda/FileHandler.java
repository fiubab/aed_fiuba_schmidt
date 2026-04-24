package com.francisco.agenda;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

import java.io.IOException;

// clase para manejar la creacion y escritura del archivo
public class FileHandler {
	public String fileName;

	// constructor de la clase
	public FileHandler(String fileName) {
		File file = new File("./resources/" + fileName);
		try {
			boolean created = file.createNewFile();
			if (created) {
				System.out.println("Archivo creado correctamente, path: ./resources/" + file.getName());
			}
			this.fileName = "./resources/" + fileName;
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}
	}

	// metodo para escribir una linea
	public void writeLine(String text) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName, true))) {
			writer.write(text);
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Error" + e.getMessage());
		}
	}
}
