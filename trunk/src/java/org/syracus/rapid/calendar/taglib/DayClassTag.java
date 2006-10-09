package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="dayClass" body-content="scriptless"
 */
public class DayClassTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int day;
	
	/**
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
			return( AbstractCalendarTag.PROPERTY_DAYCLASS + "." + getDay() );
		}
		return( AbstractCalendarTag.PROPERTY_DAYCLASS );
	}

}
