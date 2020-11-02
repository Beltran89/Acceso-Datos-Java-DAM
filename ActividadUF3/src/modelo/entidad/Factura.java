package modelo.entidad;

import java.io.Serializable;
import java.sql.Date.*;
import java.util.*;

import javax.persistence.*;

/*
 * La anotaci�n @Entity que indica que se trata de una clase de entidad.
 * Esta anotaci�n define lo que se llama una consulta con nombre. Bajo el identificador Producto.findAll podemos hacer referencia a la 
 * consulta SELECT f FROM Facturas f
 * */
@Entity
@NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f")
public class Factura implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id //Esta anotaci�n se coloca delante de un atributo para indicar que se trata de la clave principal
	private int numero;
	@Temporal(TemporalType.DATE) // Esta anotaci�n est� colocada delante del atributo fecha para indicar c�mo debe ser convertido
								 //el campo FECHA de la base de datos en el atributo fecha de la clase de entidad
	private Date fecha;
	private boolean pagado;
	
	/*
	 * La anotaci�n @OneToMany indica que existe una asociaci�n de tipo uno a muchos entre la tabla Factura y la tabla Detalles
	 * 
	 * */
	@OneToMany(mappedBy = "factura")
	private List<Detalle> detalles;
	
	/*
	 * Con la anotacion @ManyToOne realizamos la bi-directional many-to-one asociada a Cliente
	 * La anotacion @JoinColum(name="XXXX"), acompa�a una anotaci�n de tipo @ManyToOne para indicar cu�l es la columna que establece la
	 * asociaci�n.
	 * */
	@ManyToOne
	@JoinColumn(name = "NIF")
	private Cliente cliente;

	public Factura() {
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean getPagado() {
		return this.pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public List<Detalle> getDetalles() {
		return this.detalles;
	}

	public void setDetalles(List<Detalle> detalles) {
		this.detalles = detalles;
	}

	public Detalle addDetalle(Detalle detalle) {
		getDetalles().add(detalle);
		detalle.setFactura(this);
		return detalle;
	}

	public Detalle removeDetalle(Detalle detalle) {
		getDetalles().remove(detalle);
		detalle.setFactura(null);
		return detalle;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}

