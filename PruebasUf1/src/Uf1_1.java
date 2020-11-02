import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;


public class Uf1_1 {
	
	public static void main(String[] args) {
	
				// Abrir fichero para escritura
				FileWriter file;
				try {
					file = new FileWriter("C:\\Users\\ruben\\Desktop\\DAM\\AccesoDatos\\PruebasUf1\\caracteres.txt");
				} catch (IOException e) {
					System.out.println("No se puede abrir el fichero");
					System.out.println(e.getMessage());
					return;
				}
				
				// Abrir buffer y escribir tres líneas
				BufferedWriter buffer = new BufferedWriter(file);
				try {
					String texto="lo que me sale del pepo";
					buffer.write(texto);
					
					/*
					for (int i=0; i<=255; i++) {
						buffer.write(i+": "); // Escritura de un String. La operación i+": " genera un String
						buffer.write(i); // Escritura del carácter asociado al valor de i.
						buffer.newLine();
					}*/
				} catch (IOException e) {
					System.out.println("Error al escribir en el fichero");
					System.out.println(e.getMessage());
				}
				
				// Cerrar el buffer y el fichero
				try {
					buffer.close();
					file.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el fichero");
					System.out.println(e.getMessage());
				}
				
	}
	
		
		
}

