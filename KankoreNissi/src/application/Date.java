package application;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
	
	public static String getDate() {
	Calendar c1 = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	String Date = sdf.format(c1.getTime());
	
	return Date;
	
	}
	

}
