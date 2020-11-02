package modelo.entidad;

import java.io.Serializable;
import javax.persistence.*;

	
/*
	 * La anotación @Entity que indica que se trata de una clase de entidad.
	 * Esta anotación define lo que se llama una consulta con nombre. Bajo el identificador Producto.findAll podemos hacer referencia a la 
	 * consulta SELECT d FROM Destalles d
	 * */

@Entity
@NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d")
public class Detalle implements Serializable {
	private static final long serialVersionUID = 1L;

	/*
	 * ELa anotacion @Embeddable, indica que el atributo que sigue es una clave
	 * primaria, pero además indica que no se trata de un dato elemental, sino de un
	 * objeto definido en otra clase, en este caso la clase DetallePK.
	 * 
	 */
	@EmbeddedId
	private DetallePK id;
	private float precio;
	private int unidades;

	/*
	 * Con la anotacion @ManyToOne realizamos la bi-directional many-to-one asociada a  Factura
	 * La anotacion @JoinColum(name="XXXX"), acompaña una anotación de tipo @ManyToOne para indicar cuál es la columna que establece la
	 * asociación.
	 * */

	@ManyToOne
	@JoinColumn(name = "NUMERO")
	private Factura factura;

	/*
	 * Con la anotacion @ManyToOne realizamos la bi-directional many-to-one asociada a Producto
	 * La anotacion @JoinColum(name="XXXX"), acompaña una anotación de tipo @ManyToOne para indicar cuál es la columna que establece la
	 * asociación.
	 * */
	@ManyToOne
	@JoinColumn(name = "CODIGO")
	private Producto producto;

	public Detalle() {
	}

	public DetallePK getId() {
		return this.id;
	}

	public void setId(DetallePK id) {
		this.id = id;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return this.unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
}