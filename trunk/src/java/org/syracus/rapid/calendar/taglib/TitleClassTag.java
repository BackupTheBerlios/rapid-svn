package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="titleClass" body-content="scriptless"
 */
public class TitleClassTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_TITLECLASS );
	}


}
