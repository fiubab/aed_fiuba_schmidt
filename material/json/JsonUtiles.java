package material.json;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtiles {

	public static void main(String[] args) throws Exception {
		casoDeEstudio2DeJson();
	}
	
	public static void casoDeEstudio1DeJson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		{
			//Creamos un JSON
			ObjectNode configuracionJson = mapper.createObjectNode();
			configuracionJson.put("ancho", 3);
			configuracionJson.put("alto", 4);
			configuracionJson.put("profundo", 5);
			
			//Convertimos a String
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(configuracionJson);
			System.out.println(json);
			
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("config.json"), configuracionJson);
		}
		
		{
			//Leer la configuracion
			JsonNode configuracionJson = mapper.readTree(new File("config.json"));
			
			int ancho = configuracionJson.get("ancho").asInt();
			int alto = configuracionJson.get("alto").asInt();
			System.out.println(ancho + " " + alto);
		}
	}
	
	public static void casoDeEstudio2DeJson() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		
		{ //Escribir el JSON en un archivo
			ConfiguracionDeJuego configuracionJson = new ConfiguracionDeJuego();
			configuracionJson.setAncho(10);
			configuracionJson.setAlto(15);
			configuracionJson.setProfundo(20);
			configuracionJson.getSubConfiguracionDeJuego()[0] = new SubConfiguracionDeJuego();
			configuracionJson.getSubConfiguracionDeJuego()[0].setCantidadDeCartas(50);
			
			configuracionJson.getSubConfiguracionDeJuego()[1] = new SubConfiguracionDeJuego();
			configuracionJson.getSubConfiguracionDeJuego()[1].setCantidadDeCartas(51);
			
			configuracionJson.getSubConfiguracionDeJuego()[2] = new SubConfiguracionDeJuego();
			configuracionJson.getSubConfiguracionDeJuego()[2].setCantidadDeCartas(52);
			
			configuracionJson.getJugadores()[0] = 9;
			configuracionJson.getJugadores()[1] = 19;
			configuracionJson.getJugadores()[2] = 29;
			mapper.writeValue(new File("config.json"), configuracionJson);
			
			//https://jsonformatter.org/
		}
		
		{
			ConfiguracionDeJuego configuracionJson = mapper.readValue(new File("config.json"), ConfiguracionDeJuego.class);
			System.out.println(configuracionJson.getAncho() + " " + configuracionJson.getAlto());
		}
	}
}
