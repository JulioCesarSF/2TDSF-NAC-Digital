package br.com.fiap.converters;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dateToCalendarConverter")
public class DateToCalendarConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String str) {
		Date data = null;
		Calendar c = Calendar.getInstance();
	    try {
	        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	        java.util.Date dt = formatter.parse(str);
	        data = new Date(dt.getTime());
	        c.setTime(data);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return c;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Calendar c =((Calendar) arg2);
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		String data = s.format(c.getTime());
		return data;
	}

}
