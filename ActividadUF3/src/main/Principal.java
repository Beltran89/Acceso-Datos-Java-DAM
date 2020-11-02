package main;

import java.sql.PreparedStatement;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import modelo.entidad.*;

public class Principal {

	private static Scanner lector;
	private static EntityManagerFactory factoria;
	private static EntityManager em;

	public static void main(String[] args) {
		
		/*
		 * Con la clase Persistence invocamos al metodo estatico createEntityManagerFactory()pasando como argumento 
		 * el name"XXX" de la linea <persistence-unit> en el archivo persistencia.xml.
		 * 
		 * Crearemos tambien un objeto EntityManagerFactory el cual actuara como administrador de clases de entidad para operacion CRUD.
		 * 
		 * Crearemos un objeto scanner para la introduccion del usuario de datos.
		 * 
		 * Por medio de un bucle Do/While mostraremos el menu que lo tendremos en un metodo y un condicional switch para los difentes metodos que
		 * explicare mas adelante
		 * */

		factoria = Persistence.createEntityManagerFactory("ActividadUF3");
		em = factoria.createEntityManager();
		lector = new Scanner(System.in);
		int opc;

		do {

			mostrarMenu();

			opc = lector.nextInt();

			lector.nextLine(); // Recogemos el retorno de carro.

			System.out.println();

			switch (opc) {
			case 1:
				listarClientes();
				break;
			case 2:
				consultaFactura();
				break;
			case 3:
				totalFacturas();
				break;
			case 4:
				listadoProductosVendidos();
				break;
			case 6:
				insertarCliente();
				break;
			default:
				break;
			}

			/*
			 * En caso de que el usuario elija una opcion distinata de 5 llamaremos al
			 * metodo para cerrar la conexion con la bbdd y tambien cerraremos la entrada
			 * por teclado
			 * 
			 * 
			 */

		} while (opc != 5);

		lector.close();

	}

	public static void mostrarMenu() {

		System.out.println();

		System.out.println("GESTION DE ALMACEN");

		System.out.println("--------------------------------------");

		System.out.println("1. Listado de Clientes");

		System.out.println("2. Consultar Factura");

		System.out.println("3. Suma Importes de Ventas");

		System.out.println("4. Descripcion de cada Articulo junto al numero de unidades vendidas");

		System.out.println("5. Terminar programa");
		System.out.println("6. Añadir Cliente");

		System.out.println("--------------------------------------");

		System.out.println("¿Qué opción eliges?");

	}

	/*
	 * Por medio de la clase TypedQuery, vamos a realizar las correspondientes consultas con nuesta base de datos.
	 * En este caso en concreto llamaremos al objeto EntityManager llamado em he instanciado anteriormente, y por medio de el metodo
	 * .createNamedQuery y pasandole como argumento el nombre de @NamedQuery que le hemos asignado en su clase no realizara la consulta
	 * ya preestablecida en su su clase, en este caso la seleccion de todos sus clientes.
	 * 
	 * los resultados de dicha consulta seran guardados en una lita Clientes el cual por medio de un bucle for each iremos leyendo
	 * y sacando los datos de cada tupla de la forma que deseemos en este caso NIF, NOMBRE y TELEFONO. El cual mostraremos en pantalla.
	 * */
	
	
	public static void listarClientes() {
		TypedQuery<Cliente> query = em.createNamedQuery("Cliente.findAll", Cliente.class);
		List<Cliente> clientes = query.getResultList();
		for (Cliente c : clientes) {
			System.out.println(c.getNif() + " - " + c.getNombre() + " - " + c.getTlf());

		}
	}
	
	
	/*
	 * Este metodo se encarga de buscar la factura y mostrarla en pantalla con la fecha de la misma, el cliente los articulos que la componen,
	 * el estado de pagada o no pagada y un precio total. 
	 * 
	 * Para ello el usuario intoducira el numero de factura, por el metodo .find he introduciendo en la misma como argumentos la clase a la que corresponde
	 * la tabla y el numero de factura realizara una busqueda, si no la encuentra mostrar un mensaje en pantalla de factura no encontrada, por el contrario
	 * si la encuentra por medio de una variable tipo Factura y a la que le hemos asignado el resultado de la busqueda del metodo .find recuperaremos los
	 * datos por medio de los getter de la clase como son la fecha, el cliente , el estado de pago.
	 * 
	 * Posteriormente por medio de un for each de Detalles que la componen y recupreando el getter correspondiente a los detalles de la factura,
	 * recorresmos la misma recuperanado en una variable de tipo Produto, los productos que se encuentren en la clase Detalle con los datos recuperados
	 * de esta menanera y por medio de sus getter obtendremos la descripcion del producto las unidades de la misma y el precio del articulo.
	 * Dichas unidades y precio se las asignaremos a una varibale con la que iremos realizando las suma de total de precio de todos de los articulos.
	 * 
	 *  Una vez finalziado el bucle for each mostaremos el precio total de la factura.
	 *  
	 *  En mi caso lo he realizado con variables, tras varias puebas no he logrado sacarlo con una consulta el precio de la misma. Algo similar a:
	 *  
	    String sql = "SELECT SUM(d.unidades*d.precio) FROM Detalle d, Factura f WHERE f.numero = ?1";
			TypedQuery<Double> query = em.createQuery(sql, Double.class);
			query.setParameter(1, numero);
			Double p = query.getSingleResult();
			System.out.println(p);
			
	 *  Solo obtenia el precio total de todas las facturas o en otra consulta el total articulos por precio de cada tupla pero sin lograr aplicarle la 
	 *  condicion where correspondiente al numero de factura
	 * */
	

	public static void consultaFactura() {
		System.out.println("Escribe Numero Factura: ");
		int numero = lector.nextInt();
		Factura fact = em.find(Factura.class, numero);
		if (fact == null) {
			System.out.println("No se ha encontrado la factura = " + numero);

		} else {
			System.out.println("Fecha :" + fact.getFecha());
			System.out.println("Cliente :" + fact.getCliente().toString());
			
			if(fact.getPagado()==true) {
				System.out.println("===== Estado de la Factura =========");
				System.out.println("Factura Pagada");
			}else {
				System.out.println("===== Estado de la Factura =========");
				System.err.println("Factura Pendiente de Pago \n");
				
			}	
			System.out.println("");
			float total=0;
		

			for (Detalle d : fact.getDetalles()) {
				Producto p = d.getProducto();
				System.out.println(" " + p.getDescripcion() + " , " + d.getUnidades() + " unidades vendidas , precio unitario: " + p.getPrecio() + " €");
				float suma= (float) d.getUnidades() * (float) p.getPrecio();
				total += suma;
			}
			System.out.println("");
			System.out.println("Importe total : " + total);
		}
	}
	
	/*
	 * El siguiente metodo se encarga de realizar un total de las facturas, para ello con una consulta de resultado simple 
	 * el cual realiza una multiplicacion de las unidades y el precio de cada tupla y lo suma.
	 * El resultado final es mostrado por pantalla 
	 * */
	public static void totalFacturas() {
		
		TypedQuery<Double> query = em.createQuery("SELECT SUM(f.precio * f.unidades) FROM Detalle f", Double.class);
		Double totalVentas = query.getSingleResult();
		System.out.println("EL importe total de las ventas es: " + totalVentas);
		
	}
	/*
	 * Esta consulta realiza una lectura de todos los productos vendidos y los agrupa por el codigo de productos, esta vez se realiza con una coleccion de objetos
	 * Para ello tambien tenemos que crear una Lista de tipo objetos Array para su guardado y posterior lectura. tendremos que decirle que el metodo es .getResultList
	 * para que nos vaya guardardo en la Array, de lo contrario al ser varios resultados nos dara un error.
	 * 
	 * Posteriormente leemos la lista de los mismo mostarando en pantalla la posicion [0] correspondiente a la consulta p.descripcion y en la posicion [1] el resultado de la suma
	 * 
	 * */

	public static void listadoProductosVendidos() {
		TypedQuery<Object[]> query = em.createQuery("SELECT p.descripcion, SUM(d.unidades) FROM Detalle d, Producto p WHERE d.producto=p GROUP BY p.codigo", Object[].class);
		List<Object[]> producto = query.getResultList();
		
		for (Object[] o : producto) {
			System.out.println(o[0] + " - "+ o[1] + " unidades vendidas");
		}
	}
	public static void insertarCliente() {
		
  			
  			System.out.println("Introduce nif");
  			String nif= lector.nextLine();
  			
  			System.out.println("Introduce nombre");
  			String nombre = lector.nextLine();
  			
  			System.out.println("Introduce tlf");
  			String tlf = lector.nextLine();
  			
  		
  			Cliente c= new Cliente();
  			c.setNif(nif);
  			c.setNombre(nombre);
  			c.setTlf(tlf);
  			EntityTransaction et = em.getTransaction();
  			et.begin();
  			em.persist(c);
  			et.commit();
  			
		
	}
	

}