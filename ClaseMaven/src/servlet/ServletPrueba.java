package servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import modelo.entidad.Producto;

/**
 * Servlet implementation class ServletPrueba
 */
@WebServlet("/ServletPrueba")
public class ServletPrueba extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrueba() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*String id = request.getParameter("id");
		int iId = Integer.parseInt(id);*/
		
		EntityManagerFactory factoria = Persistence.createEntityManagerFactory("ClaseMaven");
		EntityManager em = factoria.createEntityManager();
		TypedQuery<Producto> query = em.createNamedQuery("Producto.findAll", Producto.class);
		List<Producto> productos = query.getResultList();
		for (Producto p : productos) {
			System.out.println(p.toString());
			response.getWriter().append("<br>" +p.toString()+"</br>");

		}
		
		/*Producto p = em.find(Producto.class, "CAJ1");
		System.out.println(p);
		
		System.out.println("Fin de obtener persona");
		response.getWriter().append("<h1>Bienvenidos a nuestro servlet</h1>").append(p.toString());*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
