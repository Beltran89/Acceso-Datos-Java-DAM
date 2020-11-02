package _04_firmadigital;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.GeneralSecurityException;
import java.security.Signature;
import java.util.Scanner;

public class AccesoConFirmaDigital {

	public static void main(String[] args) {
		
		MensajeFirmado mf;
		try {
			FileInputStream fichero = new FileInputStream("firma.obj");
			ObjectInputStream buffer = new ObjectInputStream(fichero);
			mf = (MensajeFirmado) buffer.readObject();
			buffer.close();
			fichero.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error al leer fichero de firma digital");
			System.out.println("Excepción de tipo: " + e.getClass().getName());
			System.out.println(e.getMessage());
			return;
		}
		
		Scanner lector = new Scanner(System.in);
		
		try {
			Signature firmadorVerificador = Signature.getInstance("SHA256withDSA");
			firmadorVerificador.initVerify(mf.getClavePublica());
			
			System.out.println("¿Cuál es el mensaje secreto?");
			String mensajeSecreto = lector.nextLine();
			
			firmadorVerificador.update(mensajeSecreto.getBytes());
			
			boolean ok = firmadorVerificador.verify(mf.getFirma());
			if (ok) {
				System.out.println("Verificación OK, Bienvenido al sistema");
			}
			else {
				System.out.println("La verificación ha fallado, Acceso denegado");
			}
			
			lector.close();
			
		} catch (GeneralSecurityException e) {
			System.out.println("Error en la verificación de la firma digital");
			System.out.println("Excepción de tipo: " + e.getClass().getName());
			System.out.println(e.getMessage());
			lector.close();
			return;
		}
	}
}