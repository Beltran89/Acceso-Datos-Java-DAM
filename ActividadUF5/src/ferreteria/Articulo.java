package ferreteria;

public class Articulo {
	private String codigo;
	private String descripcion;
	private float precio;
	private float stock;
	private float minimo;

/*
 * Esta la la clase con la que vamos a mostrar los diferentes articulos de nuestra BBDD de la columna 
 * Producto.
 * */	

	public Articulo(String codigo, String descripcion, float precio, float stock, float minimo) {
	
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.minimo = minimo;
	}

	
	/*
	 * Debido a quee esta actividad solo vamos a mostar los atributos, solo implementos los metodos GET
	 * 
	 * */
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