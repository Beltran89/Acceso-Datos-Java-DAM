package basex;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.basex.BaseXXQDataSource;

public class _01_Productos {
	public static void main(String[] args) {
		/*
		 * Obtenemos el datasource a la base de datos baseX
		 */
		XQDataSource xds = new BaseXXQDataSource();
		//Este futuro objeto se encargar� de las conexiones
		XQConnection con;
		
		//Este futuro objeto se encargar� de las consultas xquery
		XQExpression expr;//como el statement en JDBC
		
		//Este futuro objeto se encargara de manegar los resultados
		//obtenidos (como un ResultSet)
		XQResultSequence result;//como el ResultSet JDBC
		String sentencia;

		try {
			//Establecemos los parametros de conexi�n y le pedimos 
			//una conexi�n
			xds.setProperty("serverName", "localhost");
			xds.setProperty("port", "1984");
			con = xds.getConnection("admin", "admin");
		} catch (XQException e) {
			System.out.println("Error al establecer la conexi�n con BaseX");
			System.out.println(e.getMessage());
			return;
		}
		
		System.out.println("Establecida la conexi�n con BaseX");
		//Creamos la sentencia xquery
		// con "fn:collection('almacen')" indicamos a que base de datos
		// de las que tenemos queremos acceder (seria como el schema)
		// luego con "//productos" le decimos que queremos buscar todas
		// las etiquetas productos, esten donde esten
		// con "return $pro/producto" decimos que queremos seleccionar
		// los productos que esten dentro de "//productos", que es el valor
		// de "$pro"
		sentencia = "for $pro in fn:collection('almacen')//productos return $pro/producto";

		try {
			//Con esto creamos el objeto que me creara las sentencias
			//y me las ejecutar�.
			expr = con.createExpression();
			//Generamos la xquery que vamos a mandar a la consulta
			result = expr.executeQuery(sentencia);
		} catch (XQException e) {
			System.out.println("Error al ejecutar la sentencia XQuery");
			System.out.println(e.getMessage());
			return;
		}
		
		try {
			//al igual que un resultset, nos recorremos el objeto
			//que tiene los resultados
			while (result.next()) {
				/*
				 *  Con el m�todo getItemAsString() obtenemos todo el 
				 *  contenido XML de cada elemento; 
				 *  getItemAsString() recibe como argumento par�metros de 
				 *  serializaci�n. Podemos dejarlo a null normalmente
				 */
				System.out.println(result.getItemAsString(null));
				
			}
		} catch (XQException e) {
			System.out.println("Error al recorrer los elementos obtenidos");
			System.out.println(e.getMessage());
		}
		
		try {
			con.close();
		} catch (XQException e) {
			System.out.println("Error al cerrar la conexi�n con BaseX");
			System.out.println(e.getMessage());
		}
	}
}