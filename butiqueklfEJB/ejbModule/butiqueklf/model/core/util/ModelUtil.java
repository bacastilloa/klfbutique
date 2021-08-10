package butiqueklf.model.core.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ModelUtil {

	public static boolean isEmpty(String cadena) {
		if(cadena==null || cadena.length()==0)
			return true;
		return false;
	}
	
	public static int getAnioActual() {
		Date fechaActual=new Date();
		SimpleDateFormat formato=new SimpleDateFormat("yyyy");
		int anioActual=Integer.parseInt(formato.format(fechaActual));
		return anioActual;
	}
	
	public static Date addDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
				
		return cal.getTime();
	}
}
