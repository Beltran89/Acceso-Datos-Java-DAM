package persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidad.Producto;


public class DaoProducto {

	private EntityManager em;
	
	private boolean abrirConexion(){
		try {
			/*
			 * Lo primero que tendremos que hacer sera abrir una conexion, para ello los datos de la misma estan
			 * en nuestro persistence.xml
			 * */
			EntityManagerFactory factoria = Persistence.createEntityManagerFactory("ActividadUF5_MVC");
			em = factoria.createEntityManager();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private boolean cerrarConexion(){
		try {
			em.close();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * metodo que inserta en la bbdd una persona
	 * Por medio de la Factoria creada en la conexion, y el metodo recibido como argumento persistiremos el 
	 * Producto p. En caso de no concectar retornaremos un 0
	 * @return 0 en caso de que no haya conexion, el id en caso de que
	 * se haya dado de alta
	 */
	public String insertar(Producto p) {
		if(!abrirConexion()) {
			return "0";
		}
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(p);
		et.commit();
		cerrarConexion();
		//una vez persistido se me actualiza el objeto con su codigo, y podemos devolverlo
		return p.getCodigo();
	}
	
	@SuppressWarnings("unchecked")
	public List<Producto> listar() {
		if(!abrirConexion()) {
			return null;
		}
		
		/*
		 * Para hacer la consulta debemos de usar JPQL, la cual la añadiremos a una List de objetos Productos
		 * y la retornaremos 
		 * */
		Query query = em.createQuery("select p from Producto p");
		List<Producto> listaProductos = query.getResultList();
		return listaProductos;
	}
}
