package _01_basico;

public class PruebaHilosRunnable {
	public static void main(String[] args) {
		HiloRunnable hilo1 = new HiloRunnable("Valor 1");
		Thread t1 = new Thread(hilo1);//le pasamos un objeto de tipo runnable
		t1.setName("Hilo1");//establecemos el nombre del hilo
		
		
		HiloRunnable hilo2 = new HiloRunnable("Valor 2");
		Thread t2 = new Thread(hilo2);
		t2.setName("Hilo2");
		
		try {
			Thread.sleep(5000);//5 seg
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//arrancamos los dos hilos
		//ojo no se ejecuta run()
		//con start invocamos implicitamente el metodo run()
		t1.start();		
		t2.start();
		
		System.out.println("Programa principal parando");
	}
}
