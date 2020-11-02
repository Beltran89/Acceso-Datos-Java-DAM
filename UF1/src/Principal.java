import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;


public class Principal {
	static Scanner lector;
	
	public static void main(String[] args) throws FileNotFoundException {
		lector = new Scanner(System.in);
        ArrayList<Coche> coches = new ArrayList<Coche>();
        int opc = 0;
        File file = new File("coches.dat"); // Creamos el objeto File que va asociado a un archivo el cual vamos a utilizar en nuestro programa
        
        /**
         * 
         * Con el objeto File creado comprobamos por medio de un condicional If si dicho archivo ya existe, de ser asi, leeremos los objetos del
         * mismo y lo guardaremos en un arrayList de Objetos Coche. Realizaremos la instancia de FileInputStream y ObjectInputStream en forma de
         * autoclouse. 
         * Ademas de guardar los objetos en una ArrayList mostraremos un mensaje de que que se han leido objetos para informacion del usuario
         * En caso contrario mostraremos un mensaje de informacion al usuario "Lectura de Archivo Incorrecta"
         * 
         * */
       
        if(file.exists()) {
			try (FileInputStream inStream= new FileInputStream(file);ObjectInputStream inObject  = new ObjectInputStream(inStream)){
				coches = (ArrayList<Coche>)inObject.readObject();
				System.out.println("Objeto/s leido/s");
				
			} catch (IOException |ClassNotFoundException e) {
				System.out.println("Lectura de Archivo Incorrecta");
				} 
	        }
        
        
          /*
           * Por medio de un bucle while mostraremos el menu y el usuario elegira entre las diferentes opciones de nuestro programa mientras
           * dicha eleccion no sea igual a 6.
           * 
           * Dentro del Switch haremos la llamada a los metodos correspondiente a la opcion elegida por el usuario siempre que no sea 6.
           * Si la opcion es 6 dara una vuelta por el bucle while ya que la entrada de datos de usuario se encuentra en la misma, pero sin realizar 
           * ninguna accion.
           * */
        while (opc!=6) {
        	mostrarMenu();
        	opc = lector.nextInt();
            lector.nextLine(); // Para recoger el retorno de carro.

            	switch (opc) {
				case 1:
					nuevoCoche(coches);
					break;
				case 2:
					borrarCoche(coches);
					break;
				case 3:
					consultarCoche(coches);
					break;
				case 4:
					listadoCoches(coches);
					break;
				case 5:
					exportarCoches(coches);
				default:
					break;
				}
                    

         }

        /*
         * 
         * Si la opcion es 6 tras recorrer el bucle while 1 vez sin realizar ninguna accion, saldra del mismo y por medio de los objetos FileOutStram
         * y ObjectOutputStream procedera a sobreescribir el archivo "coche.dat", no le incluimos el argumento true ya que queremos una nuevo 
         * archivo con los datos que hay actualmente en nuestra ArrayList, ya que hemos podido agregar nuevos coches o eliminarlos. Cerraremos el 
         * lector y nuestro programa habra finalizado. No cerramos los objetos  FileOutStream y ObjectOutputStream ya que lo realizamos autoclose.
         * 
         * No es necesario abrir el fichero ya que lo tenemos abierto al principio del programa.
         *
         * */
		try (FileOutputStream fileOut= new FileOutputStream("coches.dat"); ObjectOutputStream buffer= new ObjectOutputStream(fileOut)){
			buffer.writeObject(coches);
		} catch (IOException e) {
			e.printStackTrace();
		}
		lector.close();
}

	
	/*
	 * Es metedo solo se encargara de mostrar el menu en pantalla cada vez que es llamado por el bucle While
	 * */
	public static void mostrarMenu() {
		System.out.println("                   COCHES MATRICULADOS");
		System.out.println("---------------------------------------");
		System.out.println("1. Añadir nuevo coche");
		System.out.println("2. Borrar coche");
		System.out.println("3. Consultar coche");
		System.out.println("4. Listado de coches");
		System.out.println("5. Exportar coches a archivo de texto");
		System.out.println("6. Terminar programa");
		System.out.println("---------------------------------------");
		System.out.println("¿Qué opción eliges?");
	}
	
	
	/*
	 * El siguiente metdodo se encarga de introducir un nuevo vehiculo en nuestra ArrayList, para ello se le solicita al usuario, la matricula
	 * la marca, el modelo, y el color, y procedemos a introduccirlo en la ArrayList por medio del metodo .Add y creando un nuevo objeto Coche y pasandole como
	 * argumento los datos facilitados por el usuario.
	 * La matricula la pasamos a letras mayusculas y eliminamos psoibles espacio que pueda poner el usuario como las matriculas reales para realizar la misma opcion en la opcion de borrarCoche y consultarCcohe
	 * de esta manera nos aseguramos que lo va abuscar siempre correctamente
	 * 
	 * */
	public static void nuevoCoche(ArrayList<Coche> coches) {
		System.out.println("Introduce Matricula:");
		String matricula= lector.nextLine();
		matricula = matricula.toUpperCase();
		matricula=matricula.replace(" ", "");
		System.out.println("Introduce Marca:");
		String marca= lector.nextLine();
		System.out.println("Introduce Modelo:");
		String modelo= lector.nextLine();
		System.out.println("Introce Color:");
		String color= lector.nextLine();
		
		coches.add(new Coche(matricula, marca, modelo, color));
		System.out.println("Vehiculo Añadido");
	}
	
	
	/*
	 * Este metodo se encargara de eliminar un vehiculo por medio de la matricula. Le solicitaremos al usuario la matricula, la cual convertiremos
	 * en mayusculas y eliminaremos los espacios. De esta manera eliminaremos el vehiculo concreto que deseamos, ya que con un otro metodo de busqueda 
	 * diferente a equals y con esta manera de numeros y letras podriamos eliminar un vehiculo con los mismos numeros pero diferentes letras. (ejem .contains)
	 * 
	 * Lo primero sera saber si nuestra base de datos esta vacia, de encontrarse vacia nos mostrara un mensaje de Base de Datos Vacia, de lo contrario 
	 * realizara la busqueda de la misma
	 * 
	 * Tras la introduccion de la matricula y la conversion a los entandares de nuestro programa recorreremos nuestra ArrayList por medio de un bucle
	 * for each, si la matricula coincide procederamos a borrarla y mostraremos al usuario un menasaje de Vehiculo eliminado, si no se encuentra en 
	 * nuesta base de datos le mostraremos un mensaje de "vehiculo no encontrado"
	 * 
	 * He incluido el control de la excepcion ConcurrentModificationException, ya que si eliminamos el ultimo vehiculo de la ArrayList no salta dicha
	 * excepcion, de esta manera el programa sigue funcionando y le mostramos al usuario un mensaje de que dicha base de datos se encuentra vacia
	 * 
	 * */
	public static void borrarCoche(ArrayList<Coche> coches) {
		
		if (!coches.isEmpty()) {
			System.out.println("Introduce Matricula");
			String matriculaBuscada= lector.nextLine();
			matriculaBuscada= matriculaBuscada.toUpperCase();
			matriculaBuscada=matriculaBuscada.replace(" ", "");
			
			try{
				for (Coche coche : coches) {
			
					if (coche.getMatricula().equals(matriculaBuscada)) {
						coches.remove(coche);
						System.out.println("Vehiculo Borrado");
					}else {
						System.out.println("Vehiculo No Encontrado");
					}
				}
				
			}catch (ConcurrentModificationException e) {
				System.out.println("Se ha eliminado el ultimo vehiculo de la Base de Datos");
			}
		}else {
			System.out.println("Base de Datos Vacia");
		}
	}
	
	
	
	/*
	 * El siguiente metodo se encarga de localizar un vehiculo por medio de la matricula. Lo primero comprobaremos que nuesta ArrayList no este vacia.
	 * Si lo esta mostraremos un mensaje al usuario de "Base de Datos vacia" y no lo esta se solicita al usuario una matricula. En caso contrario la convertimos
	 * a mayusculas y eliminamos los espacios para que cumpla nuestro estandar. Recorremos nuestra ArrayList comprobando dichas matricula con la introducida
	 * por el usuario. De coincidir por medio del metodo .toString mostraremos los datos del Coche, y pasaremos la variable swt a true.
	 * 
	 * En caso de no encontrarlo la variable swt se mantiene en el estado de falso y mostrara el mensaje de vehiculo no encontrado.
	 * No puedo realizarlo en el mismo bucle for con un condicional if/else ya que cuando recorra el bucle si no lo encuentra en nuestro condicional else nos mostrara dicho mensaje
	 * */
	public static void consultarCoche(ArrayList<Coche> coches) {
		
		if (!coches.isEmpty()) {
			System.out.println("Introduce Matricula");
			String matriculaBuscada= lector.nextLine();
			matriculaBuscada= matriculaBuscada.toUpperCase();
			matriculaBuscada=matriculaBuscada.replace(" ", "");
			boolean swt= false;
			
				for (Coche coche : coches) {
					if (coche.getMatricula().equals(matriculaBuscada)) {
					System.out.println(coche.toString());
					swt = true;
					}
				}
				if (swt==false) {
					System.out.println("Vehiculo No Encontrado");
				}
		}else {
					System.out.println("Base de Datos Vacia");
				}
		}
		
	/*
	 * El siguiente metodo se encargara de mostrarnos todos los Coches de nuestra ArrayList, para ello lo primero que haremos es comprobar que dicha 
	 * ArrayList no esta vacia. De no estarlo la recorreremos por medio de un bucle For Each mostrando los datos de cada Coche con el metodo .toString
	 * De estar vacia mostraremos un mensaje al usuario de "Base de Datos Vacia" 
	 * 
	 * */
	public static void listadoCoches(ArrayList<Coche> coches) {
		
		if (!coches.isEmpty()) {
			for (Coche coche : coches) {
				System.out.println(coche.toString());	
			}
		}else {
			System.out.println("Base de Datos Vacia");
		}
	}
	
	/*
	 * El siguiente metodo perrmitira al usuario exportar dicha ArrayList de objetos Coche a un formato .txt para su lectura.
	 * Con un formato autoclose crearemos los objetos FileWrite y BufferWrite para la escritura de dicho archivo .txt
	 * Cada vez que exportemos dicha ArrayList sobreescribira el archivo, al no introduccirle como argumento "true" en el objeto FileWrite
	 * de esta manera evitamos la duplicidad de datos en nuestro archivo coches.txt
	 * 
	 * En caso de Excepcion mostraremos al usuario un mensaje "El Archivo No Se Ha Guardado Correctamente"
	 * 
	 * */
	public static void exportarCoches(ArrayList<Coche> coches) {
		
		try (FileWriter fileTxt = new FileWriter("coches.txt");BufferedWriter bf = new BufferedWriter(fileTxt)){
			for (Coche coche : coches) {
				bf.write((String)coche.toString()+"\n");
			}
		} catch (IOException e) {
		System.out.println("El Archivo No Se Ha Guardado Correctamente");
		}
		System.out.println("Exportado Correctamente");	
		
	}

}


