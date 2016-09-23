import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java. util.Date;

import pojos.HealthProfile;
import pojos.Person;


public class HealthProfileReader {
	
	public static long personId = 0;
	public static Map<Long,Person> database = new HashMap<Long,Person>();
	
	public void createPerson(String firstName, String lastName, Date birthDate) {
		Person person = new Person(firstName, lastName, birthDate);
		personId = personId + 1;
		database.put(personId, person);
	}
	
	public void displayHealthProfile(long personId) {
		Person person = database.get(personId);
		System.out.println("Name: " + person.getFirstname() + " " + person.getLastname());
		System.out.println("Date of birth: " + person.getBirthDate());
		System.out.println("Health profile: " + person.gethProfile().toString());
	}
	
	public void updateHealthProfile(long personId, double height, double weight) {
		Person person = database.get(personId);
		person.sethProfile(new HealthProfile(height, weight));
		database.remove(personId);
		database.put(personId, person);
	}
	
	public static void main(String[] args) {
		try {
			HealthProfileReader app = new HealthProfileReader();
			//Create some peoples
			String expectedPattern = "MM/dd/yyyy";
		    SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		    String txtdate = "09/22/1988";
		    Date date = formatter.parse(txtdate);
			app.createPerson("Person", "First", date);
			
			txtdate = "03/21/1991";
			date = formatter.parse(txtdate);
			app.createPerson("Person", "Second", date);
			
			txtdate = "02/12/1992";
			date = formatter.parse(txtdate);
			app.createPerson("Person", "Third", date);
			
			txtdate = "05/17/1993";
			date = formatter.parse(txtdate);
			app.createPerson("Person", "Fourth", date);
			
			txtdate = "07/26/1996";
			date = formatter.parse(txtdate);
			app.createPerson("Person", "Fiveth", date);
			
			//Print info of a person.
			app.displayHealthProfile(4);
			
			//Update a health profile of a person
			app.updateHealthProfile(4, 1.90, 90);
			
			//Print info of a person.
			app.displayHealthProfile(4);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}