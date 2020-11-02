package ferreteria;

public class Articulo {
	private String codigo;
	private String descripcion;
	private float precio;
	private float stock;
	private float minimo;


	public Articulo(String codigo, String descripcion, float precio, float stock, float minimo) {
	
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.minimo = minimo;
	}
/*
* Completa aquí los métodos get para acceso
* a todas las propiedades: getCodigo(),
* getDescripcion(), etc.
*/


	public String getCodigo() {
		return codigo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public float getPrecio() {
		return precio;
	}


	public float getStock() {
		return stock;
	}


	public float getMinimo() {
		return minimo;
	}
}