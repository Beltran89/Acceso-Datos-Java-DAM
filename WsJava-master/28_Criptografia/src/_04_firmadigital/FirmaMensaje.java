package _04_firmadigital;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;

public class FirmaMensaje {
	public static void main(String[] args) {
		
		System.out.println("VAMOS A CREAR UNA FIRMA DIGITAL");
		try {
			//Generamos el par de claves y la clave privada
			KeyPairGenerator generadorDeClaves = KeyPairGenerator.getInstance("DSA");
			KeyPair claves = generadorDeClaves.generateKeyPair();
			PrivateKey clavePrivada = claves.getPrivate();
			
			//Utilizamos el algoritmo SHA256withDSA, si se utiliza DSA a secas utiliza 
			//SHA de 128 y da un error
			Signature firmadorVerificador = Signature.getInstance("SHA256withDSA");
			
			//inicializamos la firma con la clave privada
			firmadorVerificador.initSign(clavePrivada);
			
			byte[] mensajeSecreto = "CriptaMagica".getBytes();
			
			//introducimos el mensaje que queremos firmar
			firmadorVerificador.update(mensajeSecreto);
			
			//pasamos el mensaje a un resultado hash
			byte[] firmaDigital = firmadorVerificador.sign();
			
			System.out.println("Firma: " + new String(firmaDigital));
			
			MensajeFirmado mf = new MensajeFirmado(claves.getPublic(), firmaDigital);
			guardar(mf);
			
		} catch (GeneralSecurityException e) {
			System.out.println("Error al crear la firma digital");
			System.out.println("Excepción de tipo: " + e.getClass().getName());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void guardar(MensajeFirmado mf) {
		try {
			FileOutputStream fichero = new FileOutputStream("firma.obj");
			ObjectOutputStream buffer = new ObjectOutputStream(fichero);
			buffer.writeObject(mf);
			buffer.close();
			fichero.close();
			System.out.println("El fichero de firma digital se ha creado con éxito");
		} catch (IOException e) {
			System.out.println("Error al grabar fichero de firma digital");
			System.out.println("Excepción de tipo: " + e.getClass().getName());
			System.out.println(e.getMessage());
		}
	}
}
