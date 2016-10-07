package utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;

public class DateTimeAdapter extends XmlAdapter<String, DateTimeHandler> {

	public DateTimeHandler unmarshal(String v) throws Exception {
		return new DateTimeHandler(v);
	}

	public String marshal(DateTimeHandler v) throws Exception {
		return v.toString();
	}

}