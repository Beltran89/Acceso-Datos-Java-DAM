package ferreteria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




	public class Catalogo {
		
	private String mensaje;
	private ArrayList<Articulo> articulos;
	
	public Catalogo() {
		
		articulos = new ArrayList<Articulo>();
		// Paso 1: Cargar el driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Lo ha cargado");
		/* Completa aqu� la l�nea para cargar el driver
		* en memoria.
		*/
		} catch (ClassNotFoundException e) {
		mensaje = "No se ha encontrado el driver para MySQL";
		System.out.println(" no lo carga");
		return;
		}
		
		
		// Paso 2: Establecer conexi�n con la base de datos
		String cadenaConexion ="jdbc:mysql://localhost:3306/ferreteria_relacion";
		String user = "root";
		String pass = "";
		
		//String pass = "amelia";
		Connection con;
		
		try {
			con = DriverManager.getConnection(cadenaConexion, user, pass);
  			System.out.println("Se ha establecido la conexi�n con la Base de datos ferreteria");
		/*
		* Completa aqu� la l�nea para obtener conexi�n con la
		BD.
		*/
		} catch (SQLException e) {
		mensaje = "No se ha podido establecer la conexi�n con la BD, "+ e.getMessage();
		return;
		}
		
		// Paso 3: Llenar la colecci�n de art�culos
		try {
			PreparedStatement sentencia = con.prepareStatement("SELECT * FROM ALMACEN");
			ResultSet rs = sentencia.executeQuery();
		/*
		* Completa aqu� el c�digo para obtener el objeto
		ResultSet rs
		* con todas las filas de la tabla PRODUCTO.
		*/
			while (rs.next()) {
				System.out.print(rs.getString("COD_ARTICULO"));
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
      				}
      			System.out.println();
				/*
				* Construye un objeto Articulo llamado arti con
					los* datos de la fila a la que apunta el cursor del
					* ResultSet.
					* A�ade el objeto arti a la colecci�n articulos
					* declarada en la parte superior de este
					constructor.
					*/
			}
		
		} catch (SQLException e) {
			mensaje = "Error al obtener el cat�logo, " + e.getMessage();
			return;
		}
		
		// Si llega aqu� es que todo ha ido bien y se ha obtenido el cat�logo.
		
		// Paso 4: Cerrar la conexi�n
			try {
				
				con.close();
				
			} catch (SQLException e) {
				e.getStackTrace();
				return;
			}
		
		}
	
		public String getMensaje() {
		return mensaje;
		}
		
		public ArrayList<Articulo> getArticulos() {
		return articulos;
	}
}