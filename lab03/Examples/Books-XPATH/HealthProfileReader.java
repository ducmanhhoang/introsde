import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HealthProfileReader {

	Document doc;
	XPath xpath;

	public void loadXML() throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		doc = builder.parse("people.xml");

		// creating xpath object
		getXPathObj();
	}

	public XPath getXPathObj() {

		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		return xpath;
	}

	public Node getPersonByName(String firstname, String lastname) throws XPathExpressionException {

		XPathExpression expr = xpath
				.compile("/people/person[firstname='" + firstname + "' and lastname='" + lastname + "']");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		return node;
	}

	public Node getPersonByFirstName(String firstname) throws XPathExpressionException {

		XPathExpression expr = xpath.compile("/people/person[firstname='" + firstname + "']");
		Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
		return node;
	}

	public NodeList getPeopleByWeight(String weight, String condition) throws XPathExpressionException {
		
		XPathExpression expr = xpath.compile("//person[healthprofile/weight" + condition + "" + weight + "]");
		System.out.println("//person[weight" + condition + "" + weight + "]");
		NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		return nodes;

	}

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		HealthProfileReader hpr = new HealthProfileReader();
		hpr.loadXML();

		// getting node by book name
		Node node = hpr.getPersonByName("Paul", "Pogba");
		System.out.println("Node name: " + node.getNodeName());
		System.out.println("My childs text contents :" + node.getTextContent());

		// getting node by ISBN number
		node = hpr.getPersonByFirstName("Paul");
		System.out.println(node.getTextContent());

		// getting book by price
		NodeList nodes = hpr.getPeopleByWeight("80", ">");
		System.out.println("People have weight > 80");
		for (int i = 0; i < nodes.getLength(); i++) {
			System.out.println("Person " + i);
			System.out.println(nodes.item(i).getTextContent());
		}
	}

}