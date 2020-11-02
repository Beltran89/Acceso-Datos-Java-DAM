package controlador;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Producto;
import negocio.GestorProducto;

/**
 * Servlet implementation class ControladorAltaProducto
 */
@WebServlet("/altaProducto")
public class ControladorAltaProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /*
     * Cuando en nuestro index le demos al enlace catologo articulos entrara por esta peticion get
     * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		GestorProducto gp = new GestorProducto();
		List<Producto> listaProductos = gp.listar();
		request.setAttribute("listaProductos", listaProductos);
		request.getRequestDispatcher("principal.jsp").forward(request, response);
		
	}

	/*
	 * Cuando realice el formulario al darle al boton alta entrara por aqui.
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Recogeremos los datos que hemos hemos recibido de alta.html, y los gardaremos todos en variables
		 * de tipo String
		 * */
		String codigo = request.getParameter("codigo");
		String descripcion = request.getParameter("descripcion");
		String minimo = request.getParameter("minimo");
		String precio = request.getParameter("precio");
		String stock = request.getParameter("stock");
		/*
		 * Realizaremos una conversion de los datos recogidos al tipo de dato que lo tenemos en nuestrra tabla
		 * */
		float fMinimo= Float.parseFloat(minimo);
		float fPrecio= Float.parseFloat(precio);
		float fStock= Float.parseFloat(stock);
		
		/*
		 * Crearemos un nuevo objeto Producto y le añadiremos los datos recogidos
		 * */
		Producto p = new Producto();
		p.setCodigo(codigo);
		p.setDescripcion(descripcion);
		p.setMinimo(fMinimo);
		p.setPrecio(fPrecio);
		p.setStock(fStock);
		
		/*
		 * Nos comunicaremos con nuestra capa gestora, realizando su metodo alta() con el objeto producto como arguemtno
		 * Dicho metodo nos devolvera una respuesta
		 * */
		GestorProducto gp = new GestorProducto();
		String respuesta = gp.alta(p);
		
		/*
		 * Para mostrar los productos crearemos una lista de productos y se la solicitaremos al gestor.
		 * Posteriormete se la pasaremos a nuestra vista jsp
		 * 
		 * */
		List<Producto> listaProductos = gp.listar();
		request.setAttribute("listaProductos", listaProductos);
		
		
		/*
		 * En base a la respuesta recibida entraremos en el metodo switch, y realizremos la accion en base a esa respuesta.
		 * La respuesta que recibamos se encargara el GestorProducto
		 * */
		switch (respuesta) {
		case "-0":
			/*
			 * Esta es la respuesta que mostaremos en nuestro jsp, en caso de que no haya conexion retornaremos dicho mesaje,
			 *  mediante requestDisptcher le decimos a donde queremos ir,
			 * */
			request.setAttribute("mensajeError", "No se ha obtenido conexion");
			request.getRequestDispatcher("principal.jsp").forward(request, response);
			break;
		case "-1":
			/*
			 * Esta es la respuesta que mostaremos en nuestro jsp, en caso de que el usuario haya metido mas o menos
			 * de 4 caracteres en el campo de codigo, mediante requestDisptcher le decimos a donde queremos ir,
			 * */
			request.setAttribute("mensajeError", "El codigo tiene que contener 4 Caracteres");
			request.getRequestDispatcher("principal.jsp").forward(request, response);
			break;
		case "-2":
			/*
			 * Esta es la respuesta que mostaremos en nuestro jsp, en caso de que el usuario haya introduccido 0
			 * en el campo precio, mediante requestDisptcher le decimos a donde queremos ir,
			 * */
			request.setAttribute("mensajeError", "El precio tiene que ser mayor de 0");
			request.getRequestDispatcher("principal.jsp").forward(request, response);
			break;
		default:
			/*
			 * En caso de que el usuario haya introduccido todo bien se ira al jsp y mostraremos el mensaje del articulo añadido
			 * 
			 * */
			request.setAttribute("mensaje", "Producto dado de alta con codigo: " + respuesta);
			request.getRequestDispatcher("principal.jsp").forward(request, response);
			break;
		}

	}

}
