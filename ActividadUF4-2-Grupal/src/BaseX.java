import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import net.xqj.basex.BaseXXQDataSource;

public class BaseX {
	public static void main(String[] args) {
		/*
		 * Obtenemos el datasource a la base de datos baseX
		 */
		XQDataSource xds = new BaseXXQDataSource();
		//Este futuro objeto se encargaro de las conexiones
		XQConnection con;

		//Este futuro objeto se encargaro de las consultas xquery
		XQExpression expr;//como el statement en JDBC

		//Este futuro objeto se encargara de manegar los resultados
		//obtenidos (como un ResultSet)
		XQResultSequence result;//como el ResultSet JDBC
		String sentencia;

		try {
			//Establecemos los parametros de conexion y le pedimos
			//una conexion
			xds.setProperty("serverName", "localhost");
			xds.setProperty("port", "1984");
			con = xds.getConnection("admin", "admin");
		} catch (XQException e) {
			System.out.println("Error al establecer la conexion con BaseX");
			System.out.println(e.getMessage());
			return;
		}

		System.out.println("Establecida la conexion con BaseX");
		System.out.println("");

		//Sentecia XQUERY para la busqueda en nuestro BASEX.En este caso ademas lo ordenamos por codigo
		sentencia = "for $detalles in fn:collection('recibos')//detalle order by $detalles/codigo return $detalles";
		try {

			expr = con.createExpression();

			result = expr.executeQuery(sentencia);
		} catch (XQException e) {
			System.out.println("Error al ejecutar la sentencia XQuery");
			System.out.println(e.getMessage());
			return;
		}

		try {
			/*
			 * Tenemos la posibilidad de recorenos los resultados como si
			 * se tratase de un arbol DOM. Acceso a sus nodos Elementos y Texto
			 * */
			while (result.next()) {
				Node nodoRecibos = result.getNode();
				mostrarNodos(nodoRecibos);
			}
		} catch (XQException e) {
			System.out.println("Error al recorrer los elementos obtenidos");
			System.out.println(e.getMessage());
		}

		try {
			con.close();
		} catch (XQException e) {
			System.out.println("Error al cerrar la conexion con BaseX");
			System.out.println(e.getMessage());
		}
	}

	/*
	 * En nuestro metodo recoreremos los diferentes nodos y recogeremos en una variable
	 * el dato de nuesto XML que deseamos para posteriormente mostrarlo en pantalla
	 *
	 * En este caso conocemos la ubicacion del mismo y lo daremos como argumento
	 *
	 * */
	private static void mostrarNodos(Node nodo) {
		NodeList nodos = nodo.getChildNodes();
		Node codigo = nodos.item(1);
		Node descripcion = nodos.item(3);
		Node unidades = nodos.item(5);
		Node precio = nodos.item(7);
		System.out.println("  " + codigo.getTextContent() + "  " + descripcion.getTextContent() + " - " + unidades.getTextContent() + " unidades a " + precio.getTextContent());
	}
}

