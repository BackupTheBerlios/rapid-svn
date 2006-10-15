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
	public static final String PROPERTY_DAYLINK = "day.link";
	public static final String PROPERTY_WEEKDAYCLASS = "weekday.class";
	public static final String PROPERTY_WEEKDAYSTYLE = "weekday.style";
	public static final String PROPERTY_WEEKDAYLENGTH = "weekday.length";
	
	private Hashtable<String,Object> properties = new Hashtable<String,Object>();

	public Hashtable<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Hashtable<String, Object> properties) {
		this.properties = properties;
	}
	
	public Object getProperty( String key ) {
		return( getProperties().get( key ) );
	}
	
	public void setProperty( String key, Object value ) {
		getProperties().put( key, value );
	}
	
}
