package com.francisco.agenda;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	public String fileName;
	public BufferedWriter writer;
	public BufferedReader reader;

	public FileHandler(String fileName) {
		try {
			this.writer = new BufferedWriter(new FileWriter(fileName, true));
			this.reader = new BufferedReader(new FileReader(fileName));
		} catch (IOException e) {
			  System.out.println("Error" + e.getMessage());
		}
	}
}
