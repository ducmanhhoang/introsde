import java.io.File;
import java.io.FileReader;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.HealthProfile;
import model.Person;
import dao.PeopleStore;

public class HealthProfileWriter {  	
	public static PeopleStore people = new PeopleStore();

	public static void initializeDB() {
		HealthProfile hp = new HealthProfile(68.0, 1.72);
		Person john = new Person(new Long(1), "John", "Doe", "1985-03-20", hp);
		people.getData().add(john);
		
		hp = new HealthProfile(60.0, 1.2);
		Person yen = new Person(new Long(2), "Yen", "Hoang", "2000-04-21", hp);
		people.getData().add(yen);
		
		hp = new HealthProfile(99, 2);
		Person nam = new Person(new Long(3), "Nam", "Le", "1988-01-21", hp);
		people.getData().add(nam);
		
		hp = new HealthProfile(50, 60);
		Person huong = new Person(new Long(4), "Huong", "Nguyen", "2006-08-12", hp);
		people.getData().add(huong);
		
		hp = new HealthProfile(60, 1.5);
		Person thao = new Person(new Long(5), "Thao", "Vu", "2003-04-23", hp);
		people.getData().add(thao);
		
		hp = new HealthProfile(62, 1.30);
		Person luan = new Person(new Long(6), "Luan", "Hoang", "2000-06-24", hp);
		people.getData().add(luan);
		
		hp = new HealthProfile(102, 1.70);
		Person trung = new Person(new Long(7), "Trung", "Hoang", "1988-08-10", hp);
		people.getData().add(trung);
		
		hp = new HealthProfile(93, 1.90);
		Person van = new Person(new Long(8), "Van", "Nguyen", "1990-01-15", hp);
		people.getData().add(van);
		
		hp = new HealthProfile(72, 1.12);
		Person bach = new Person(new Long(9), "Bach", "Ngo", "2005-03-19", hp);
		people.getData().add(bach);
		
		hp = new HealthProfile(70, 1.83);
		Person phuong = new Person(new Long(10), "Phuong", "Dao", "1993-05-16", hp);
		people.getData().add(phuong);
		
		hp = new HealthProfile(123, 2);
		Person phuc = new Person(new Long(11), "Phuc", "Vu", "1991-07-19", hp);
		people.getData().add(phuc);
		
		hp = new HealthProfile(69, 1.70);
		Person quynh = new Person(new Long(12), "Quynh", "Nguyen", "1996-09-11", hp);
		people.getData().add(quynh);
		
		hp = new HealthProfile(89, 1.32);
		Person hoi = new Person(new Long(13), "Hoi", "Nguyen", "2006-08-22", hp);
		people.getData().add(hoi);
		
		hp = new HealthProfile(103, 1.67);
		Person anh = new Person(new Long(14), "Anh", "Nguyen", "1997-06-18", hp);
		people.getData().add(anh);
		
		hp = new HealthProfile(121, 1.88);
		Person linh = new Person(new Long(15), "Linh", "Nguyen", "1993-04-15", hp);
		people.getData().add(linh);
		
		hp = new HealthProfile(90, 1.78);
		Person vinh = new Person(new Long(16), "Vinh", "Ngo", "1998-02-17", hp);
		people.getData().add(vinh);
		
		hp = new HealthProfile(78, 1.65);
		Person lan = new Person(new Long(17), "Lan", "Ngo", "1984-01-19", hp);
		people.getData().add(lan);
		
		hp = new HealthProfile(80, 1.70);
		Person loan = new Person(new Long(18), "Loan", "Nguyen", "1985-05-23", hp);
		people.getData().add(loan);
		
		hp = new HealthProfile(65, 1.64);
		Person diep = new Person(new Long(19), "Diep", "Tran", "1994-03-16", hp);
		people.getData().add(diep);
		
		hp = new HealthProfile(85, 1.79);
		Person tuan = new Person(new Long(20), "Tuan", "Le", "1992-07-18", hp);
		people.getData().add(tuan);
		
		hp = new HealthProfile(97, 1.90);
		Person tuong = new Person(new Long(21), "Tuong", "Nong", "1986-09-20", hp);
		people.getData().add(tuong);
	}	

	public static void main(String[] args) throws Exception {
		
		initializeDB();
		
		JAXBContext jc = JAXBContext.newInstance(PeopleStore.class);
        Marshaller m = jc.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(people,new File("people.xml")); // marshalling into a file
        m.marshal(people, System.out);			  // marshalling into the system default output
    }
}
