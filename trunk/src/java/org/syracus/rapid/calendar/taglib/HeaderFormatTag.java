package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="headerFormat" body-content="scriptless"
 */
public class HeaderFormatTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_HEADERFORMAT );
	}

	
	
}
