package _05_waitnotify;

public class Productor extends Thread{

	public String nombre;
	public Cola cola;
	
	public Productor(String nombre, Cola cola){
		super();
		this.nombre = nombre;
		this.cola = cola;
	}
	
	public void run(){
		for(int i = 1;i <= 10;i++){
			String mensaje = nombre + " - " + i;//Productor 1 - 2
			cola.addMensaje(mensaje);
		}
	}

}
