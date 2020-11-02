import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Uf1_1Bis {

	public static void main(String[] args) {
		FileInputStream file;
		try {
			file = new FileInputStream("C:\\Users\\ruben\\Desktop\\DAM\\AccesoDatos\\PruebasUf1\\peliculas2.txt");
		} catch (IOException e) {
			System.out.println("No se ha podido abrir el fichero");
			System.out.println(e.getMessage());
			return;
		}
			
		int caracter;
		// Escribir el texto en el fichero carácter a carácter.
		try {
			caracter = file.read();
			while (caracter!=-1) {
				System.out.println( caracter);
				caracter = file.read();
			} 
		} catch (IOException e) {
			System.out.println("Error al leer el fichero");
			System.out.println(e.getMessage());
		}
		
		// Cerrar el fichero
		try {
			file.close();
		
		} catch (IOException e) {
			System.out.println("Error al cerrar el fichero");
			System.out.println(e.getMessage());
		}

	}
}
