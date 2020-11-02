package negocio;

import java.util.List;

import entidad.Producto;
import persistencia.DaoProducto;

/*
 * Esta capa se encargara de las reglas de negocio
 * */
public class GestorProducto {
	
	/*
	 * En este caso la reglas de negocio son las siguientes, no dara de alta el producto en su metodo alta;
	 * -Si recibe en el campo codigo distinto a 4 caracteres
	 * -Si el campo Precio pone 0
	 * */
	
	public String alta(Producto p) {
		
		if(p.getCodigo().length() != 4) {
			return "-1";
		}
		if(p.getPrecio() <= 0) {
			return "-2";
		}
		/*
		 * LLegado a este punto sin problema llamaremos a la capa DaoProducto, y su metodo insertar
		 * */
		DaoProducto daoProducto = new DaoProducto();
		String cod = daoProducto.insertar(p);
		return cod;
			
		
		
		
	}
	
	public List<Producto> listar(){
		/*
		 * Este metodo se encargara de listar, para ello llamaremos al la siguiente capa a su metodo listar
		 * */
		DaoProducto daoProducto = new DaoProducto();
		return daoProducto.listar();
	}
}
