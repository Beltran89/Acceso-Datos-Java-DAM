import java.io.Serializable;


/*
 * Implementamos la interfaz serializable para poder tener la capacidad de persistencia de sus objetos
 * 
 * */
public class Coche  implements Serializable{
	
	private static final long serialVersionUID = 1L; // Una vez implementemos la interfaz nos generera de forma automatica un SerialVersionUID
													// de esa esa forma evitaremos problemas de incompatibilidad de versiones en procesos de serializacion
	
	
	/*
	 * Nuesta clase coche se compondra de los siguientes argumentos
	 * */
	private String matricula;
    private String marca;
    private String modelo;
    private String color;
    
    public Coche (String matricula, String marca, String modelo, String color) {
    	this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
     }

     public String getMatricula() {
    	 return matricula;
     }

     public String getMarca() {
         return marca;
     }

     public String getModelo() {
         return modelo;
     }

     public String getColor() {
         return color;
     }

     
     /*
      * Nuestro metodo toString sobreescrito lo utilizacemos para los metodos de recuperacion de cualquier objeto de una forma mas completa del mismo
      * */
     @Override
     public String toString() {
        return matricula + " " + marca + " " + modelo + " " + color;
     }
}
