package modelo.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/*
 * La anotación @Entity que indica que se trata de una clase de entidad.
 * Esta anotación define lo que se llama una consulta con nombre. Bajo el identificador Producto.findAll podemos hacer referencia a la 
 *  consulta SELECT p FROM Producto p
 * */

@Entity
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id //Esta anotación se coloca delante de un atributo para indicar que se trata de la clave principal
	private String codigo;
	private String descripcion;
	private float minimo;
	private float precio;
	private float stock;

	/*
	 * La anotación @OneToMany indica que existe una asociación de tipo uno a muchos entre la tabla Producto y la tabla Detalles
	 * 
	 * */
	@OneToMany(mappedBy = "producto")
	private List<Detalle> detalles;

	public Producto() {
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getMinimo() {
		return this.minimo;
	}

	public void setMinimo(float minimo) {
		this.minimo = minimo;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getStock() {
		return this.stock;
	}

	public void setStock(float stock) {
		this.stock = stock;
	}

	public List<Detalle> getDetalles() {
		return this.detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public Detalle addDetalle(Detalle detalle) {
		getDetalles().add(detalle);
		detalle.setProducto(this);
		return detalle;
	}

	public Detalle removeDetalle(Detalle detalle) {
		getDetalles().remove(detalle);
		detalle.setProducto(null);
		return detalle;
	}
}