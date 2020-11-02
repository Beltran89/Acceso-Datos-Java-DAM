package _01_excepciones;

public class Main0 {
	public static void main(String[] args) {
		int nums[] = new int[4];
		try {
			System.out.println("Antes de que se genere la excepción.");
			// generar una excepción de índice fuera de límites
			nums[7] = 10;
		} catch (ArrayIndexOutOfBoundsException exc) {
			// Capturando la excepción
			System.out.println("Índice fuera de los límites!");
		}
		System.out.println("Después de que se genere la excepción.");
	}

}
