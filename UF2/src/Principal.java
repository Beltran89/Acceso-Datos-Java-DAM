import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.integration.jboss.ExtendedMysqlExceptionSorter;

public class Principal {

              private static Scanner lector;

              public static void main(String[] args) {

            	  /*
            	   * Esta primera parte del codigo se encargara de llamar metodo para establecer la conexion que se describe mas adelante
            	   * Realizara una primera compobacion de que dicha conexion se ha realizado, por el contrario nos retornara un null
            	   * que le hara entrar en el condicional,
            	   * 
            	   * */
            	  
                            Connection con = abrirConexionBD();

                            if (con == null) {
                            	System.out.println(con);
                                        return ;
                            }
                            lector = new Scanner(System.in);// apertura entrada al sistema

                            int opc; // varibale de la opcion del menu

                            
                     /*
                      * Entraremos en un bucle do/while que nos mostrara el menu, asignaremos la entrada del teclado a la variable opc,
                      * la cual al recogerla entraremos en un codicional switch con la opcion elegida por el usuario, dependiendo de la 
                      * opcion llamaremos al metodo de dicha opcion.
                      *
                      * */
                            
                            do {

                                    mostrarMenu();

                                    opc = lector.nextInt();

                                    lector.nextLine(); // Recogemos el retorno de carro.

                                    System.out.println();
                                    
                                    switch (opc) {
                    				case 1:
                    					listarProductos(con);
                    					break;
                    				case 2:
                    					nuevoProducto(con);
                    					break;
                    				case 3:
                    					actualizarStock(con);
                    					break;
                    				case 4:
                    					borrarArticulo(con);
                    					break;
                    				default:
                    					break;
                    				}

                             /*
                              * En caso de que el usuario elija una opcion distinata de 5 llamaremos al metodo para cerrar la conexion con la bbdd
                              * y tambien cerraremos la entrada por teclado
                              * 
                              * 
                              * */ 
                                    
                             } while (opc != 5);

                             cerrarConexion(con); // Pasamos como argumento la conexión

                             lector.close();

              }

              /*
               * Con este metodo establareceremos la conexion con la bbdd, lo primero sera cargar los Drivers Class.forName("com.mysql.jdbc.Driver")
               * Dicho Driver será usado por jdbc para las conexiones
               * 
               * Tendremos una cadena de conexion te tipo string que contendran los valores de jdbc (protocolo de conexion), mysql (subprotocolo),
               * la direccion de la bbdd en este caso el localhost ya que la tenemos en nuestro sistema, el puerto por el que nos vamos a conectar
               * (3306) y la bbdd o esquema al que deseamos conectarnos (ferreteria)
               * Tendremos otros dos String en el que estableceremos el user/pass de nuestra bbdd
               * 
               * Por medio de DriverManager gestionaremos la conexion, y aqui le pasaemos como argumento la cadena de conexion anterior, user/pass
               * anteriormente descritas
               * 
               * En el caso de conectar correctamente mostraremos un mensaje de que se ha establecido la conexion, en caso contrario entraremos en 
               * el catch y mostraremos que no se ha estabecido conexion y el error de la misma
               * 
               * Por ultimo retornaremos la varible "con" con los datos de la conexion la cual pasaremos como argumentos a los metodos siguientes
                * */
              private static Connection abrirConexionBD() {

            	  
          		try {
          			Class.forName("com.mysql.jdbc.Driver");
          			System.out.println("Driver Funcionado");
          			
          		} catch (ClassNotFoundException e) {
          			System.out.println("No se ha encontrado el driver para MySQL");
          			
          		}
          		
          	
          		//String cadenaConexion = "jdbc:mysql://192.168.1.130:3306/ferreteria";
          		//String user = "root";
          		//String pass = "root"; 
          		String cadenaConexion = "jdbc:mysql://188.127.166.73:3306/productos";
          		String user = "ruben";
          		String pass = "ruben"; 
          		Connection con = null;
          		
          		try {
          			
          			con = DriverManager.getConnection(cadenaConexion, user, pass);
          			System.out.println("Se ha establecido la conexión con la Base de datos ferreteria");
          			System.out.println(cadenaConexion + user + pass);
          		} catch (SQLException e) {
          			System.out.println("No se ha podido establecer la conexión con la BD");
          			System.out.println(e.getMessage());
          	
          		}
    
                                             return con;

              }

              
              /*
               * Este metodo simplemente se encargara de cerrar la conexion con la bbdd, se le llamara cuando el usuario elija la opc 5
               * siempre que acabemos es muy importante cerrar dicha conexion
               * 
               * */
              private static void cerrarConexion(Connection con) {
            	  try {
          			con.close();
          		} catch (SQLException e) {
          			System.out.println("No se ha podido cerrar la conexión con la BD");
          			System.out.println(e.getMessage());
          			return;
          		}
          		System.out.println("Se ha cerrado la base de datos");

          	}

              /*
               * Este metodo simplemente nos mostrara un menu para el usuario
               * 
               * */
              public static void mostrarMenu() {

                           System.out.println();

                           System.out.println("GESTION DE ALMACEN");

                           System.out.println("--------------------------------------");

                           System.out.println("1. Listado de artículos");

                           System.out.println("2. Añadir nuevo artículo");

                           System.out.println("3. Actualizar stock");

                           System.out.println("4. Eliminar Articulo");
                           
                           System.out.println("5. Terminar programa");

                           System.out.println("--------------------------------------");

                           System.out.println("¿Qué opción eliges?");

              }

              /*
               * Con este metodo listaremos los datos de nuestra base de datos. Para ello declaramos una instancia a la clase PreparedStatement
               * la cual al instanciarla lo haremos con el argumento de la conexion y le estableceremos en el argumento de dicha instancia la 
               * consulta SQL que deseamos.
               * 
               * Declararemos un objeto  ResultSet con el que recorremos la bbdd, seria un similar un cursor
               * 
               * 
               * El objeto ResultSet actúa como un cursor que permite desplazarse a través del conjunto de registros hacia delante, 
               * hacia atrás, al primero y al último. El método next() sitúa el puntero en el siguiente registro y retorna true si no es 
               * final de fichero, con lo que podemos utilizar su resultado para realizar un recorrido secuencial mientras siga retornando true.
               * 
               * Mientra nos deplazamos por los registros mostaremos en pantalla en cada linea los datos de la misma, para ello por la variable del
               * objeto ResultSet, llamaremos al metodo .getXXX pasando como arguemnto el nombre la la columna que deseamos. En el metodo .getXXX
               * tendremos que sustituir las XXX por el tipo de dato que es cada columna
               * 
               * Por ultimo recogemos en dos variables ( stock y stockMinimo) los datos de ese registro con el mismo nombre para comprobarlos, si el
               * stok esta por debajo del stock minimo nos mostara un mensaje con la cantidad que falta para llegar al minimo
               * */
              
              
              private static void listarProductos(Connection con) {
            	  	
            	  try {
          		
          			PreparedStatement sentencia = con.prepareStatement("SELECT * FROM BATIDOS");
        			ResultSet rs = sentencia.executeQuery();
	      				
	      				while (rs.next()) {
		      				/*System.out.print(rs.getString("COD_ARTICULO"));
		      				System.out.print(" - "); 
		      				System.out.print(rs.getString("ARTICULO"));
		      				System.out.print(" - "); 
		      				System.out.print("PRECIO " + rs.getDouble("PRECIO"));
		      				System.out.print(" - "); 
		      				System.out.print("STOCK "+ rs.getInt("STOCK"));
		      				System.out.print(" - ");
		      				System.out.print("MINIMO " +rs.getInt("STOCK_MIN"));
		      				System.out.print(" - "); 
		      				int stock= rs.getInt("STOCK");
		      				int stock_min=rs.getInt("STOCK_MIN");
			      				if ( stock < stock_min ) {
			      					int diferencia= stock_min - stock;
			      					System.out.print("Hay que reponer " + diferencia + " unidades");
			      				}*/
	      					
	      					System.out.print(rs.getString("id"));
	      					System.out.print(" - "); 
	      					System.out.print(rs.getString("nombre"));
	      					System.out.print(" - "); 
	      					System.out.print("PRECIO " + rs.getDouble("precio"));
		      				System.out.print(" - ");
			      			System.out.println();
      				}
      				
      				} catch (SQLException e) {
      					System.out.println("Error al realizar el listado de productos");
      					System.out.println(e.getMessage());
      				}	
    
      			}
                              
              /*
               * En la el siguiente metodo añadiremos los productos, para ello introduccimos en un String la sencia SQL que mas adelante añadimeros como 
               * argumento. 
               * 
               * Le solicitaremos al usuario por entrada por teclado los datos( cod_articulo, articulo, precio, stock y stock minimo) los cuales los guardaremos
               * en una variable del tipo de dato que lo tenemos en nuestra bbdd
               * 
               * Llamaremos a la clase PreparedStatement y la instanciaremos con la conexion y como argumento la linea SQL guardada con en el STring anteriormente
               * 
               * Ahora llamaremos a la variable que le hemos asignado a nuestro PreparedStatement y por medio del metodo . setXXX ( XXX nuevamente es el tipo de dato)
               * sustituiremos nuestras interrrogantes de la sentencia SQL por datos reales para ello:
               *  .getXXX (ESTA ES LA POSICION DE NUESTRA INTERROGANTE EN LA SENTECIA SQL, ESTE EL DATO A AÑADIR)
               *  
               *   Si todo ha salido correctametne mostraremos un mensaje con avisando al usuario de la sentencia ha funcionado y
               *   de las lineas afectadas
               *   
               *   De lo contrario mostaremos un mensaje de que no se ha añadido el producto 
               * */

              private static void nuevoProducto(Connection con) {
            	  try {
          		/*
          			String sql = "INSERT INTO ALMACEN (COD_ARTICULO, ARTICULO, PRECIO, STOCK, STOCK_MIN) " +
          				"VALUES (?, ?, ?,?,?)"; // en vez de poner los valores ponemos interrogantes
          			
          			System.out.println("Introduce código del nuevo Articulo (Tres letras y un numero) ");
          			String cod_art= lector.nextLine();
          			
          			System.out.println("Introduce descripción articulo");
          			String articulo = lector.nextLine();
          			
          			System.out.println("Introduce precio");
          			double precio = lector.nextDouble();
          			
          			System.out.println("Introduce stock");
          			int stock= lector.nextInt();
          			
          			System.out.println("Introduce stock minimo");
          			int stock_min= lector.nextInt();
          		
          			
          			
          			PreparedStatement sentencia;
          			sentencia = con.prepareStatement(sql);
          			
          			sentencia.setString(1, cod_art);
          			sentencia.setString(2, articulo);
          			sentencia.setDouble(3, precio);
          			sentencia.setInt(4, stock);
          			sentencia.setInt(5, stock_min);
          			
          			int afectados = sentencia.executeUpdate();
          			System.out.println("Sentencia SQL ejecutada con éxito");
          			System.out.println("Registros afectados: "+afectados);*/
            		  
            		  String sql = "INSERT INTO ALMACEN (COD_ARTICULO, ARTICULO, PRECIO, STOCK, STOCK_MIN) " +
                				"VALUES (?, ?, ?,?,?)"; 

                      String codigo = "mes2";
                      String articulo = "cocalola";
                      double precio = 1.20;
                      int cantidad = 10;
                      int stocMin= 2;

                      PreparedStatement sentencia;
                      sentencia = con.prepareStatement(sql);


                      sentencia.setString(1, codigo);
                      sentencia.setString(2, articulo);
                      sentencia.setDouble(3, precio);
                      sentencia.setInt(4, cantidad);
                      sentencia.setInt(5, stocMin);
                      int afectados = sentencia.executeUpdate();
            		  
            		  
          		} catch (SQLException e) {
          			System.out.println("Error al añadir una nuevo articulo");
          			System.out.println(e.getMessage());
          		}

              }
              
              
              /*
               * Con este metodo añadiremos mas stock a nuestros articulos. Lo primero le solicitaremos al usuario el codigo  del articulo y el stock
               * que deseamos añadir 
               * 
               * Realzizaremos una consulta del stock que tenemos actualmente en nuestra bbdd por medio de una sentencia Select y la clase
               * PreparedStatement, para ello recorremos la bbdd por medio de un cursor y el objeto Resultset. Si lo encuentra guardaremos por 
               * medio del metodo .getXXX y el argumento STOCK la cantidad de stock actual en un variable y tendremos un interruptor para saber que lo
               * ha encontrado en nuesta bbdd
               * 
               * Si el interruptor es distinto a false ( lo declaramos asi en false para que si no lo encuentra no acceda a este condicional), 
               * en una nueva variable llamda nuevoStock sumaremos la cantidad encontrada en nuestra bbdd y la cantidad introduccida por el usuario.
               * 
               * Declararemos otro objeto PreparedStatement pero con un string declarado anteriormente con la sentecia de update. Modificaremos las 
               * interrogantes por el cod_articulo en where y la cantidad nuevaStock en el Stock. (metodos .setXXX(NOMBRE DE LA COLUMNA)
               * 
               * Mostraremos un mensaje de las lineas modificadas
               * 
               * En caso de que el interrupto siga en false no 
               * realizara las operaciones anteriormente descritas y mostrar un mensaje de que el articulo no ha sido encontrado
               * 
               * */

              private static void actualizarStock(Connection con){

			            try {  	
			      			
			            	
			      			System.out.println("Introduce código del Articulo (Tres letras y un numero) ");
		          			String cod_art= lector.nextLine();
			      		
		          			System.out.println("Cuantas unidades quieres reponer");
		          			int añadirStock= lector.nextInt();
		          			
		          			int stockActual=0;
		          			PreparedStatement sentenciaConsulta = con.prepareStatement("SELECT * FROM ALMACEN WHERE COD_ARTICULO=?");
		        			sentenciaConsulta.setString(1, cod_art);
		        			ResultSet rs = sentenciaConsulta.executeQuery();
		        			
		        			boolean existe= false;
		        			while (rs.next()) {
		        				stockActual = rs.getInt("STOCK");
		        			existe=true;
		        			
		        			}
		        		
		        			if(!existe==false) {
							int nuevoStock = stockActual + añadirStock;
		        			
		        			
		        			String sql = "update ALMACEN set STOCK=? WHERE COD_ARTICULO=?";
		        			PreparedStatement sentenciaModificar = con.prepareStatement(sql);
		     
		        			sentenciaModificar.setInt(1, nuevoStock);		
		        			sentenciaModificar.setString(2, cod_art);

		        			
			      			int afectados = sentenciaModificar.executeUpdate();
			      			System.out.println("El Stock se ha actualizado correctamente");
			      			
							
		        			}
		        			else {
		        				System.out.println("Articulo " + cod_art + " No Encontrado");
		        			}
		    
			      		} catch (SQLException e) {
			      			System.out.println("Error al añadir nuevo stock");
			      			System.out.println(e.getMessage());
			      			
			      		}
      	
           }	
          
              
              ////                    EXTRA            /////
              
              
           /*
            * Con el siguiente metodo vamos a borrar un articulo de nuesta bbdd
            * 
            * Para ello en un string declaramos la sentecia SQL con la sentencia delete y con la interrogante el COD_ARTICULO que le vamos
            * a solicitar al usuario.
            * 
            * Por medio del a clase PreparedStatement declaramos el objeto sentencia , y le pasamos como argumento de la sentencia String
            * 
            * Por medio de metodo setXXX le damos como argumento la posicion de nuestra interrogante y el dato que deseamos añadir, en este
            * caso la variable que hemos guardado de la entrada de usuario.
            * 
            * Por medio de un condicional if/else si se ha afectado alguna linea mostraremos el mensaje de articulo borrado y los registros afectados
            * si no se ha modificado ninguna linea mostraremos el mensaje de articulo no encontrado
            * 
            * */   
              
              private static void borrarArticulo(Connection con)  {                
              try {
      			
      			String sql = "DELETE FROM ALMACEN WHERE COD_ARTICULO=?"; 
      			
      			System.out.println("Introduce código del Articulo (Tres letras y un numero) ");
      			String cod_art= lector.nextLine();
      			
      			
      			PreparedStatement sentencia = con.prepareStatement(sql);
      			sentencia.setString(1, cod_art);
      			
      			int afectados = sentencia.executeUpdate();
      			if (afectados>0) {
      			System.out.println("Sentencia SQL ejecutada con éxito");
      			System.out.println("Articulo " + cod_art + " eliminado");
      			System.out.println("Registros afectados: "+afectados);
      			}else {
      				System.out.println("Sentencia SQL ejecutada con éxito");
      				System.out.println("Articulo " + cod_art + " No Encontrado");
      			}
      			
      		} catch (SQLException e) {
      			System.out.println("Error al borrar el articulo");
      			System.out.println(e.getMessage());
      		}
              }   

}