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
		/*
		 * Lo primero que hacemos es cargar los Drivers, si ocurre algun error durante la carga 
		 * entrara en el cath y nos mostrar el error de que no ha encontrado el Driver
		 * */
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		
		} catch (ClassNotFoundException e) {
		mensaje = "No se ha encontrado el driver para MySQL";
		return;
		}
		
		
		/*
		 * 
		 * Lo siguiente que hacemos es conecta con nuestra BBDD ferreteria, introduccimos el user y el password
		 * que se lo daremos como argumento a DriverManager de nuestra conexion, en caso contrario mostaremos
		 * el error producido
		 * */
		String cadenaConexion ="jdbc:mysql://localhost:3306/ferreteria_relacion";
		String user = "root";
		String pass = "";
		
		//String pass = "amelia";
		Connection con;
		
		try {
			con = DriverManager.getConnection(cadenaConexion, user, pass);
  			System.out.println("Se ha establecido la conexión con la Base de datos ferreteria");
		
		} catch (SQLException e) {
		mensaje = "No se ha podido establecer la conexión con la BD, "+ e.getMessage();
		return;
		}
		
		/*
		 * En esta parte introducicremos la sentencia Query, que es mostar todos los productos de nuestra
		 * tabla productos, los resultados los guardaremos en un objeto ResultSet
		 * 
		 * */
		try {
			
			PreparedStatement sentencia = con.prepareStatement("SELECT * FROM PRODUCTO");
			ResultSet rs = sentencia.executeQuery();
		/*
		* Con la siguiente linea mientras en nuestra variable rs, en la cual hemos guardado los objetos, ira recorriendo
		* las diferentes tuplas.
		* Recogeremos los datos de la mismas, codigo, descripcion... y los guardaremos en un nuevo objeto articulo, el cual posteriormente
		* lo añadiremos en nuestra ArrayList artriculo de objetos articulos
		* 
		*/
			while (rs.next()) {
				Articulo arti = new Articulo(rs.getString("CODIGO"),rs.getString("DESCRIPCION"), rs.getFloat("PRECIO"), rs.getFloat("STOCK"), rs.getFloat("MINIMO"));
				articulos.add(arti);		
			
			}
			mensaje = "Catologo de articulos";
		
		} catch (SQLException e) {
			mensaje = "Error al obtener el catálogo, " + e.getMessage();
			return;
		}
		
		/*
		 * Cerraremos la conexion
		 * */
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