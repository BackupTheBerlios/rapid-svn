package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="calendarClass" body-content="scriptless"
 */
public class CalendarClassTag extends AbstractPropertyTag {

	private static final long serialVersionUID = 1L;

	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_CALENDARCLASS );
	}

}
