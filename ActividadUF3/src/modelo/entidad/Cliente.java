package modelo.entidad;

import java.io.Serializable;

import javax.persistence.*;
import java.util.List;
/*
 * La anotación @Entity que indica que se trata de una clase de entidad.
 * Esta anotación define lo que se llama una consulta con nombre. Bajo el identificador Ciente.findAll podemos hacer referencia a la 
 * consulta SELECT c FROM Cliente c
 * La anotación @Table, que indica el nombre de la tabla física en la base de datos
 * */

@Entity
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")
@Table(name="clientes")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id  //Esta anotación se coloca delante de un atributo para indicar que se trata de la clave principal
	private String nif;
	private String nombre;
	private String tlf;

	/*
	 * La anotación @OneToMany indica que existe una asociación de tipo uno a muchos entre la tabla Cliente y la tabla Factrura
	 * 
	 * */
	@OneToMany(mappedBy = "cliente")
	private List<Factura> facturas;

	public Cliente() {
	}

	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTlf() {
		return this.tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	public Factura addFactura(Factura factura) {
		getFacturas().add(factura);
		factura.setCliente(this);
		return factura;
	}

	public Factura removeFactura(Factura factura) {
		getFacturas().remove(factura);
		factura.setCliente(null);
		return factura;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nif + " - " + nombre ;
	}
}