
package _01_basico;

public class PruebaHilosThread {

	public static void main(String[] args) {
		
		HiloThread hilo1 = new HiloThread("Valor 1");
		hilo1.setName("Hilo1");//establecemos el nombre del hilo
		
		HiloThread hilo2 = new HiloThread("Valor 2");
		hilo2.setName("Hilo2");
		
		try {
			Thread.sleep(5000);//5 seg
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//arrancamos los dos hilos
		//ojo no se ejecuta run()
		//con start invocamos implicitamente el metodo run()
		hilo1.start();		
		hilo2.start();
		
		System.out.println("Programa principal parando");
	}

}
