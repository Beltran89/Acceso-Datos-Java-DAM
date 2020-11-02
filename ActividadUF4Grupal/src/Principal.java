
import java.util.concurrent.SynchronousQueue;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Principal {
	public static void main(String[] args) {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance(); //obtener el objeto DocumentBuilder a partir de su método newInstante().
		DocumentBuilder analizador;
		/*
		 * La clase DocumentBuilder , situada en el paquete javax.xml.parsers, representa un analizador
		 * o parser cuyos objetos nos permiten construir el árbol DOM a partir del documento XML por
		 * medio de su método parse().
		 */
		Document doc;
		Node raiz;
		
		try {
			analizador = fabrica.newDocumentBuilder();
			doc = analizador.parse("recibos.xml");//Es tarea del objeto DocumentBuilder analizar el contenido del documento XML y devolver el objeto Document con el árbol DOM
			raiz = doc.getDocumentElement(); //El método getDocumentElement() de la clase Document devuelve el objeto Node que representa el nodo raíz
			
			recorrerNodos(raiz);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void recorrerNodos(Node raiz) {
		//este metodo nos devuelve todos los nodos hijos Directos del elemento raiz "cruceros"
		NodeList nodos = raiz.getChildNodes();
	
		for (int i=0; i<nodos.getLength();i++) {
		/*
		 * Por medio de est este nodo recorreremos los diferentes recibos
		 * */
		Node recibo= nodos.item(i);
	
		/*
		 * Para evitar la lectura de los elementos texto de las sangrias establecvecmos un condicional
		 * if que solo acceda en los elementos nodos
		 * 
		 * */
			if (recibo.getNodeType() == Node.ELEMENT_NODE) {
				/*
				 * Crearemos una lista de nodos hijo de recibo, los cuales lerremos y mostraremos en
				 * pantalla el numero de factura y la fecha de la misma
				 * */
				
				NodeList list= recibo.getChildNodes();
				Node numeroFactura= list.item(1);
				Node fecha= list.item(3);
				System.out.println("  Recibo numero : " + numeroFactura.getTextContent());
				System.out.println("  Fecha : " + fecha.getTextContent());
				
				/*
				 * Por medio de del siguiente bucle for accederesmos al nodo detalle, ubicado
				 * en en el elemento 5 de nuestro XML
				 * EL mismo lo recorreremos y recogeremos los datos solo de elementos nodos
				 * mostrando en pantalla el codigo, descripcion, unidades y precio correspondientes a al nodo detalle
				 * */
				
				for (int j=5; j<list.getLength();j++) {
					
				Node detalles= list.item(j);
				
					if (detalles.getNodeType() == Node.ELEMENT_NODE) {
						NodeList detalle = detalles.getChildNodes();
						Node codigo = detalle.item(1);
						Node descripcion = detalle.item(3);
						Node unidades = detalle.item(5);
						Node precio = detalle.item(7);
						System.out.println(codigo.getTextContent() + "  " + descripcion.getTextContent() + " " + unidades.getTextContent() + " unidades al precio de " + precio.getTextContent());
					}
					
				}
		System.out.println("");
			}
			
		}
	}
		
	
}