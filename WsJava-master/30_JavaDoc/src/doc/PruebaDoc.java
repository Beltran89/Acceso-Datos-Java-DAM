package doc;
import java.util.Date;

/**
 * Ejemplo de creaci�n de documentaci�n para Javadoc.<br/>
 * Podemos incluir etiquetas HTML para enriquecer el texto.
 * 
 * Aqui deberia ir una descripci�n de lo que hace la clase
 * 
 * En java doc tenemos adem�s anotaciones, para que cuando generemos
 * la documentaci�n o la veamos mediante un IDE como eclipse este
 * mejor representada.
 * 
 * @author Nombre del autor.
 * @version 1.0
 * @since 25-10-2018.
 *
 */
public class PruebaDoc {
	// PROPIEDADES DE CLASE
	/**
	 * Aqui iria una descripci�n de lo que representa esta propiedad
	 */
	private String texto;
	/**
	 * Valor numerico entero.
	 */
	private Integer numero;

	// ZONA DE CONSTRUCTORES
	/**
	 * Comentario de metodo simple. Podriamos add informaci�n sobre este constructor
	 */
	public PruebaDoc() {

	}

	// ZONA DE LOGICA
	/**
	 * Otro comentario de metodo simple. Al no tener parametros de entrada ni de 
	 * salida, simplemente deberiamos aportar una descripci�n de este m�todo
	 * lo que hace
	 */
	public void metodoUno() {
		// COMENTARIO DE LINEA, ESTOS COMENTARIOS NO SON DE JAVADOC
		// SEGUNDA LINEA DE COMENTARIO
		/*
		 * COMENTARIOS DE VARIAS LINEAS, NO CONFUNDIR CON JAVADOC
		 */

	}

	/**
	 * Aqui ir�a una descripci�n del metodo, pero como adem�s tenemos parametros
	 * de entrada y de salida, deberiamos crear anotaciones que representen y
	 * expliquen los parametros de entrada y los parametros de salida
	 * 
	 * @param numero
	 *            Valor numerico entero.
	 * @param fecha
	 *            Valor fecha.
	 * @return Cadena devuelta.
	 */
	public String metodoDos(Integer numero, Date fecha) {

		return null;
	}

	/**
	 * Aqui ir�a una descripci�n del metodo y sus parametros de entrada y de salida
	 * pero como adem�s tenemos una excepci�n tambien podemos anotarla para explicar
	 * cuando se arroja dicha excepcion
	 * 
	 * @param texto
	 *            Valor texto.
	 * @return Valor numerico.
	 * @throws Exception
	 *             Excepcion dada. {@link Exception#getLocalizedMessage()}
	 */
	public Integer metodoTres(String texto) throws RuntimeException {

		return null;
	}

}