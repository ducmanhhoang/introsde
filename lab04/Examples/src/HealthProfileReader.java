import java.io.FileReader;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.Person;
import dao.PeopleStore;

public class HealthProfileReader {
	public static PeopleStore people = new PeopleStore();

	DocumentBuilderFactory factory = null;
	DocumentBuilder builder = null;
	Document doc = null;
	XPathFactory xPathFactory = null;
	XPath xPath = null;
	XPathExpression expr = null;
	NodeList pNodes = null;
	NodeList nList = null;

	String exprPerson = "/people/person";
	String exprWeights = "/people/person/healthprofile/weight/text()";
	String exprHights = "/people/person/healthprofile/height/text()";

	public HealthProfileReader() throws Exception {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		doc = builder.parse("people.xml");
		xPathFactory = XPathFactory.newInstance();
		xPath = xPathFactory.newXPath();
	}

	public static void main(String[] args) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
		System.out.println();
		System.out.println("Output from our XML File: ");
		Unmarshaller um = jc.createUnmarshaller();
		PeopleStore people = (PeopleStore) um.unmarshal(new FileReader("people.xml"));
		List<Person> list = people.getData();
		for (Person person : list) {
			System.out.println("Person: " + person.getFirstname() + " born " + person.getBirthdate());
		}

		new HealthProfileReader().getWeight();
		new HealthProfileReader().getHight();
		new HealthProfileReader().printPeople();
		new HealthProfileReader().printHealthProfile(1);
	}

	public void printPeople() throws Exception {
		expr = xPath.compile(exprPerson);
		pNodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < pNodes.getLength(); i++) {
			Element ePerson = (Element) pNodes.item(i);
			System.out.println("Person ID: " + ePerson.getAttribute("id"));
			System.out.println("Full name: " + ePerson.getElementsByTagName("firstname").item(0).getTextContent() + " "
					+ ePerson.getElementsByTagName("lastname").item(0).getTextContent());
			System.out.println("Birthdate: " + ePerson.getElementsByTagName("birthdate").item(0).getTextContent());

			NodeList hpNodes = ePerson.getElementsByTagName("healthprofile");
			for (int j = 0; j < hpNodes.getLength(); j++) {
				Element eHP = (Element) hpNodes.item(j);
				System.out.println("Last update: " + eHP.getElementsByTagName("lastupdate").item(0).getTextContent());
				System.out.println("Weight: " + eHP.getElementsByTagName("weight").item(0).getTextContent());
				System.out.println("Height: " + eHP.getElementsByTagName("height").item(0).getTextContent());
				System.out.println("BMI: " + eHP.getElementsByTagName("bmi").item(0).getTextContent());
				System.out.println("--------------------------------------");
				System.out.println();
			}
		}
	}

	public void printHealthProfile(long id) throws Exception {
		String expr = "/people/person[@id='" + id + "']";

		pNodes = (NodeList) xPath.compile(expr).evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; null != pNodes && i < pNodes.getLength(); i++) {
			Element ePerson = (Element) pNodes.item(i);
			System.out.println("Person ID: " + ePerson.getAttribute("id"));
			System.out.println("Full name: " + ePerson.getElementsByTagName("firstname").item(0).getTextContent() + " "
					+ ePerson.getElementsByTagName("lastname").item(0).getTextContent());
			System.out.println("Birthdate: " + ePerson.getElementsByTagName("birthdate").item(0).getTextContent());

			NodeList hpNodes = ePerson.getElementsByTagName("healthprofile");
			for (int j = 0; j < hpNodes.getLength(); j++) {
				Element eHP = (Element) hpNodes.item(j);
				System.out.println("Last update: " + eHP.getElementsByTagName("lastupdate").item(0).getTextContent());
				System.out.println("Weight: " + eHP.getElementsByTagName("weight").item(0).getTextContent());
				System.out.println("Height: " + eHP.getElementsByTagName("height").item(0).getTextContent());
				System.out.println("BMI: " + eHP.getElementsByTagName("bmi").item(0).getTextContent());
				System.out.println("--------------------------------------");
				System.out.println();
			}
		}
	}

	public void getWeight() throws Exception {
		expr = xPath.compile(exprWeights);
		nList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		System.out.println("Gets all wights of people:");
		for (int i = 0; i < nList.getLength(); i++) {
			System.out.println(nList.item(i).getNodeValue());
		}
	}

	public void getHight() throws Exception {
		expr = xPath.compile(exprHights);
		nList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		System.out.println("Gets all hights of peolple:");
		for (int i = 0; i < nList.getLength(); i++) {
			System.out.println(nList.item(i).getNodeValue());

		}
	}
}
