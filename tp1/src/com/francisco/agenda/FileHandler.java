package com.francisco.agenda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.util.List;

import java.io.IOException;

public class FileHandler {
	public String fileName;

	public FileHandler(String fileName) {
		File file = new File(fileName);
		try {
			boolean created = file.createNewFile();
			this.fileName = fileName;
		} catch (Exception e) {
			System.out.println("Error" + e.getMessage());
		}
	}

	public void writeLine(String text) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName, true))) {
			writer.write(text);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			System.out.println("Error" + e.getMessage());
		}
	}

	public List<String> read(int nContacts) {
		if (nContacts == 0) nContacts = 10;
		try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
			List<String> contactsList = reader.readAllLines();
			reader.close();
			return contactsList;
		} catch (IOException e) {
			System.out.println("Error" + e.getMessage());
			return null;
		}
	}
}
