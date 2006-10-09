package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="headerStyle" body-content="scriptless"
 */
public class HeaderStyleTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_HEADERSTYLE );
	}

	
	
}
