package org.syracus.rapid.calendar.taglib;


/**
 * 
 * @author snwiem
 * @jsp.tag name="titleFormat" body-content="scriptless"
 */
public class TitleFormatTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_TITLEFORMAT );
	}
	
}
