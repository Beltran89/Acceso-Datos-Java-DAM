
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LecturaXML {
	public static void main(String[] args) {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		DocumentBuilder analizador;
		Document doc;
		Node raiz;
		
		try {
			analizador = fabrica.newDocumentBuilder();
			doc = analizador.parse("concierto.xml");
			raiz = doc.getDocumentElement();
			recorrerNodos(raiz);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void recorrerNodos(Node raiz) {
		//Este metodo nos devuelve todos los nodos hijos Directos del elemento raiz "concierto"
		NodeList nodos = raiz.getChildNodes();
		
		
		//Aqui queremos pintar todos los elementos, pero evitando los nodos texto
		//que son nodos hijos directos de "cruceros"
		/*
		 * Con las siguientes lineas leeremos los nodos hijo de nodo concierto. En este caso puedo poner seguido los item ya 
		 * que no tengo elementos textos al no crear sangrias ni saltos de linea en XML.
		 * 
		 * Dichos elementos nos mostraran la fecha y hora
		 * */
		Node fecha= nodos.item(0);
		Node dia= nodos.item(1);
		System.out.println("  Fecha y hora: " + fecha.getTextContent() + "  " + dia.getTextContent());
		NodeList participantes = nodos.item(2).getChildNodes();
		System.out.println("Pacticipan los siguientes Grupos: ");
		
		
		for (int i=0; i<participantes.getLength();i++) {
			/* Iteración por los elementos Participante. EL cual se encuentra en el nodo 3 (item 2 para el IDE) y
			 * los recorreremos solo mostrando los elementos nodos, en este caso no es necesario ya que al igual que en el caso anterior 
			 * ni tenemos sangias ni saltos de linea. 
			 * Ponemos los item, ya que en este caso lo conocemos, en caso contrario deberiamos crear un bucle que los recorra todos los ndoos 
			 * y solo accediera a los que son elementos nodos y no texto.
			 * 
			 * El cual nos motrara la hora con el nombre de variable y de nodo entrada y el grupo
			 */
			
			
			Node participante =  participantes.item(i);
			if (participante.getNodeType() == Node.ELEMENT_NODE) {
				
				Node entrada = participante.getChildNodes().item(0);
				Node grupo = participante.getChildNodes().item(1);
				System.out.println( entrada.getTextContent() +  "  " + grupo.getTextContent());
	
			}
		}
	}
	
}