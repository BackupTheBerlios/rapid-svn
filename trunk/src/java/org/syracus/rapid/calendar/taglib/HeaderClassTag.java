package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="headerClass" body-content="scriptless"
 */
public class HeaderClassTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_HEADERCLASS );
	}

	
	

	
}
