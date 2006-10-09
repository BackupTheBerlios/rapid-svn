package org.syracus.rapid.calendar.taglib;

import java.util.Hashtable;

import javax.servlet.jsp.tagext.SimpleTagSupport;

public abstract class AbstractCalendarTag extends SimpleTagSupport {

	public static final String PROPERTY_CALENDARCLASS = "calendar.class";
	public static final String PROPERTY_CALENDARSTYLE = "calendar.style";
	public static final String PROPERTY_HEADERCLASS = "header.class";
	public static final String PROPERTY_HEADERSTYLE = "header.style";
	public static final String PROPERTY_HEADERFORMAT = "header.format";
	public static final String PROPERTY_TITLECLASS = "title.class";
	public static final String PROPERTY_TITLESTYLE = "title.style";
	public static final String PROPERTY_TITLEFORMAT = "title.format";
	public static final String PROPERTY_DAYCLASS = "day.class";
	public static final String PROPERTY_DAYSTYLE = "day.style";
	
	private Hashtable<String,String> properties = new Hashtable<String,String>();

	public Hashtable<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Hashtable<String, String> properties) {
		this.properties = properties;
	}
	
	public String getProperty( String key ) {
		return( getProperties().get( key ) );
	}
	
	public void setProperty( String key, String value ) {
		getProperties().put( key, value );
	}
	
}
