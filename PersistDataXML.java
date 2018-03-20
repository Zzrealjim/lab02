package persistentdata;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
// Eric McCreath - 2015
// Example of saving and loading info using XML
// minor modification - setting encoding Michael Curtotti - 2017
public class PersistDataXML {
	private static final String PERSON = "person";
	private static final String AGE = "age";
	private static final String NAME = "name";
	String name;
	int age;
	
	public PersistDataXML(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public PersistDataXML() {
	
	}
	
    static public PersistDataXML load(String filename) {
    	File f = new File(filename);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		PersistDataXML res = new PersistDataXML();
		
		try {
			// load the xml tree
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(f);
			
			// parse the tree and obtain the person info
			Node person = doc.getFirstChild();
			
			NodeList nl = person.getChildNodes();
			for (int i =0;i< nl.getLength();i++) {
				Node n = nl.item(i);
				if (n.getNodeName().equals(NAME)) {
					res.name = n.getTextContent();
				} else if (n.getNodeName().equals(AGE)) {
					res.age = Integer.parseInt(n.getTextContent());
				}
			}
			
		} catch (Exception e) {
			System.err.println("Problem loading " + filename);
		}
		return res;
	}
	
    public void save(String filename) {
    	File f = new File(filename);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			// make the xml tree
			db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element person = doc.createElement(PERSON);
			
			Element en = doc.createElement(NAME);
			en.appendChild(doc.createTextNode(name));
			person.appendChild(en);
			
			Element ea = doc.createElement(AGE);
			ea.appendChild(doc.createTextNode(Integer.toString(age)));
			person.appendChild(ea);
			
			doc.appendChild(person);
			// save the xml file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			// set xml encoding to utf-8
			transformer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
			
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(f);
			transformer.transform(source, result);
		} catch (Exception e) {
			System.err.println("Problem saving " + filename);
		}
    }
    
    public void show() {
    	System.out.println("Name : " + name);
    	System.out.println("Age : " + age);
    }
    
    public static void main(String[] args) {
		PersistDataXML data = new PersistDataXML("Hugh",10);
		data.save("data.xml");
		PersistDataXML dataload = load("data.xml");
		dataload.show();
	}
	
	
}
