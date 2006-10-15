package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="weekdayLength" body-content="scriptless"
 */
public class WeekdayLengthTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_WEEKDAYLENGTH );
	}

	@Override
	protected Object getPropertyValue() {
		return( new Integer( getBodyContent().getString() ) );
	}
	
	

}
