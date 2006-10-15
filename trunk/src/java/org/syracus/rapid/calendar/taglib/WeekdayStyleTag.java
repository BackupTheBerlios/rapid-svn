package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="weekdayStyle" body-content="scriptless"
 */
public class WeekdayStyleTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_WEEKDAYSTYLE );
	}

}
