package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="dayStyle" body-content="scriptless"
 */
public class DayStyleTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int day = 0;
	
	/**
	 * 
	 * @return
	 * @jsp.attribute required="false" rtexprvalue="true"
	 */
	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}


	@Override
	protected String getProperty() {
		if ( 0 < getDay() ) {
			return( AbstractCalendarTag.PROPERTY_DAYSTYLE + "." + getDay() );
		}
		return( AbstractCalendarTag.PROPERTY_DAYSTYLE );
	}
	
}
