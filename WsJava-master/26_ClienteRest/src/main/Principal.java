package main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) throws IOException {
		
		/*
		 * Aqu�, estamos solicitando el servicio a trav�s de una URL y 
		 * un objeto HttpURLConnection. La petici�n se env�a a trav�s 
		 * del m�todo GET.
		 */
		URL objURL = new URL("http://localhost:8080/27_ServidorRest/rest/mensajeParametros/query?nombre=juan&apellidos=plaza");
		HttpURLConnection conexion = (HttpURLConnection) objURL.openConnection();
		conexion.setRequestMethod("GET");
		
		/*
		 * Obtenemos el c�digo HTTP de la petici�n y lo mostramos en pantalla.
		 * Salvo que ocurra alg�n error, el c�digo ser� 200 (OK).
		 */
		int codigoHTTP = conexion.getResponseCode();
		System.out.println("C�digo: " + codigoHTTP);
		
		/*
		 * El m�todo conexion.getInputStream() nos devuelve el flujo de datos 
		 * que necesitaremos para leer el mensaje del servidor. 
		 * Dicho flujo de datos se lo pasamos a un objeto Scanner, 
		 * que facilitar� enormemente la lectura.
		 */
		Scanner lector = new Scanner (conexion.getInputStream());
		String respuesta = "";
		while (lector.hasNextLine()) {
			respuesta = respuesta.concat(lector.nextLine());
		}
		
		lector.close();
		
		System.out.println(respuesta);
	}
}
