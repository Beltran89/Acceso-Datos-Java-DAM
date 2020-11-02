package servlts;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import net.xqj.basex.BaseXXQDataSource;
	
	@WebServlet("/Inventario")
	public class Inventario extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
	public Inventario() {
	super();
	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		XQDataSource xds = new BaseXXQDataSource();
		XQConnection con;
		XQExpression expr;
		XQResultSequence result;
		String sentencia;
		String textoHTML = "";
		try {
			xds.setProperty("serverName", "localhost");
			xds.setProperty("port", "1984");
			con = xds.getConnection("admin", "admin");
			expr = con.createExpression();
			sentencia = "for $pro in fn:collection('recibos')//productos return $pro/recibo";
			
			result = expr.executeQuery(sentencia);
			while (result.next()) {
			Node nodoProducto = result.getNode();
			textoHTML = mostrarProducto(nodoProducto, textoHTML);
			}
			con.close();
		} catch (XQException e) {
			textoHTML = "Error al obtener los datos XML <br />";
			textoHTML = textoHTML + e.getMessage();
			}
			response.setContentType("text/html");
			PrintWriter flujoEscritura=response.getWriter();
			flujoEscritura.append("<!DOCTYPE html>");
			flujoEscritura.append("<html><head><meta charset='UTF-8'>");
			flujoEscritura.append("<title>Página dinámica</title>");
			flujoEscritura.append("</head><body>");
			flujoEscritura.append("<h1>Artículos de alimentación</h1>");
			// Creamos una tabla HTML
			flujoEscritura.append("<table>");
			// Creamos la cabecera
			flujoEscritura.append("<tr>");
			flujoEscritura.append("<th>Artículo</th>");
			flujoEscritura.append("<th>Cantidad por cada unidad</th>");
			flujoEscritura.append("<th>Precio Unidad</th>");
			flujoEscritura.append("<th>Stock</th>");
			flujoEscritura.append("</tr>");
			flujoEscritura.append(textoHTML);
			flujoEscritura.append("</table>");
			flujoEscritura.append("</body></html>");
			flujoEscritura.close();
			}
			private static String mostrarProducto(Node nodo, String texto) {
			NodeList nodos = nodo.getChildNodes();
			// Creamos una fila HTML (Table Row)
			texto = texto + "<tr>";
			for (int i=0; i<nodos.getLength();i++) {
			Node nodoHijo = nodos.item(i);
			if (nodoHijo.getNodeType() == Node.ELEMENT_NODE) {
			// Creamos una celda de datos HTML (Table data)
			texto = texto + "<td>";
			texto = texto + nodoHijo.getTextContent();
			texto = texto + "</td>";
			}
			}
			texto = texto + "<tr>";
			return texto;
		}
}