package c2026_01.semana02.jueves;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Historial {


	//Imprime y guarda los texto en el historial
	public static void imprimirYGuardarHistorial(String nombreDeArchivoHistorial, String... textos) throws Exception {
		Pantalla.imprimir(textos);
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreDeArchivoHistorial, true))) {
			for(String texto: textos) {
				if (texto.equals("\n")) {
					continue;
				}
				bw.write(texto);
			}
			bw.newLine();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void mostrarHistorial(String nombreDeArchivoHistorial) throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(nombreDeArchivoHistorial))) {
			String linea;
			Pantalla.imprimir("\n", "Historial:");
			while ((linea= br.readLine()) != null) {
				Pantalla.imprimir(linea);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void eliminarHistorial(String nombreDeArchivoHistorial) throws Exception {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreDeArchivoHistorial))) {
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static String leerUltimoHistorial(String nombreDeArchivoHistorial) throws Exception {
		String ultimaLinea = "";
		try (BufferedReader br = new BufferedReader(new FileReader(nombreDeArchivoHistorial))) {
			String linea;
			Pantalla.imprimir("\n", "Historial:");
			while ((linea= br.readLine()) != null) {
				ultimaLinea = linea;
			}
		} catch (Exception e) {
			throw e;
		}
		return ultimaLinea;
	}
}
