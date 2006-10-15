package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="weekdayClass" body-content="scriptless"
 */
public class WeekdayClassTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_WEEKDAYCLASS );
	}

}
