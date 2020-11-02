import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Principal {

	public static void main(String[] args) {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		DocumentBuilder analizador;
		Document doc;
		
		try {
			analizador = fabrica.newDocumentBuilder();
			// Creamos nuevo documento
			doc = analizador.newDocument();
			// Añadimos elemento raiz
			Element concierto = doc.createElement("concierto");
			doc.appendChild(concierto);
			// Añadimos tres contactos al elemento raíz agenda.
			agregarConcierto(concierto, doc);
			// Guardamos en disco el nuevo documento XML.
			guardar(doc);
			
			System.out.println("El archivo se ha creado con éxito");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void agregarConcierto(Element concierto, Document doc) {
		
		//Agregamos el concierto, con su fecha y hora
		Element fecha = doc.createElement("fecha");
		Text textoFecha = doc.createTextNode("20 - Oct - 2018");
		fecha.appendChild(textoFecha);
		concierto.appendChild(fecha);
		Element hora = doc.createElement("hora");
		Text textoHora = doc.createTextNode("21:30");
		hora.appendChild(textoHora);
		concierto.appendChild(hora);
		
		
		//Agregamos el nodo participantes el cual sera hijo del concierto al igual que la fecha y la hora
		Element participantes = doc.createElement("participantes");
		concierto.appendChild(participantes);
		
		//Agregamos el primer participante al nodo participantes, con los elementos hora y grupo
		Element participante = doc.createElement("participante");
		participantes.appendChild(participante);
		Element entrada = doc.createElement("entrada");
		Text textoEntrada = doc.createTextNode("21:30");
		entrada.appendChild(textoEntrada);
		participante.appendChild(entrada);
		Element grupo = doc.createElement("grupo");
		Text textoGrupo = doc.createTextNode("Las Ardillas de Dakota");
		grupo.appendChild(textoGrupo);
		participante.appendChild(grupo);
		
		//Agregamos el segundo participante al nodo participantes, con los elementos hora y grupo
		participante = doc.createElement("participante");
		participantes.appendChild(participante);
		entrada = doc.createElement("entrada");
		textoEntrada = doc.createTextNode("22:15");
		entrada.appendChild(textoEntrada);
		participante.appendChild(entrada);
		participantes.appendChild(participante);
		grupo = doc.createElement("grupo");
		textoGrupo = doc.createTextNode("Fito y Fitipaldis");
		grupo.appendChild(textoGrupo);
		participante.appendChild(grupo);
		
		//Agregamos el tercer participante al nodo participantes, con los elementos hora y grupo
		participante = doc.createElement("participante");
		participantes.appendChild(participante);
		entrada = doc.createElement("entrada");
		textoEntrada = doc.createTextNode("23:00");
		entrada.appendChild(textoEntrada);
		participante.appendChild(entrada);
		grupo = doc.createElement("grupo");
		textoGrupo = doc.createTextNode("Cosplay");
		grupo.appendChild(textoGrupo);
		participante.appendChild(grupo);
		
		
		
	}
	
	private static void guardar(Document doc) throws TransformerException {
		//Fabrica de Transformes
		TransformerFactory fabricaConversor = TransformerFactory.newInstance();
		
		//Creamos el objeto Transfomer, que nos permitira serializar el arbol DOM a un fichero
		Transformer conversor = fabricaConversor.newTransformer();
		
		//Creamos la fuente de la cual sacaremos el arbol Dom
		DOMSource fuente = new DOMSource(doc); 
		
		//Creamos el flujo de salida, al fichero que queremos.
		StreamResult resultado = new StreamResult(new File("concierto.xml"));
		
		//Por ultimo, serializamos los datos
		conversor.transform(fuente, resultado);
	}

}
