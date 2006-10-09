package org.syracus.rapid.calendar.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.TagAdapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractPropertyTag extends BodyTagSupport {

	protected static final transient Log log = LogFactory.getLog( AbstractPropertyTag.class );
	
	protected abstract String getProperty();

	@Override
	public int doAfterBody() throws JspException {
		if ( log.isDebugEnabled() ) {
			log.debug( "[doAfterBody] BEGIN" );
		}
		JspTag parent = getParent();
		
		
		if ( parent instanceof TagAdapter ) {
			parent = ((TagAdapter)parent).getAdaptee();
			if ( log.isDebugEnabled() ) {
				log.debug( "[doAfterBody] adaptee is '" + parent.getClass().getName() + "'" );
			}
		}
		if ( parent instanceof AbstractCalendarTag ) {
			AbstractCalendarTag calendarTag = (AbstractCalendarTag)parent;
			String value = getBodyContent().getString();
			if ( log.isDebugEnabled() ) {
				log.debug( "[doAfterBody] value = '" + value + "'" );
			}
			String property = getProperty();
			if ( log.isDebugEnabled() ) {
				log.debug( "[doAfterBody] property = '" + property + "'" );
			}
			calendarTag.setProperty( property, value );
		} else if ( log.isErrorEnabled() ) {
			log.error( "Parent '" + parent.getClass().getName() + "' is not of type AbstractCalendarTag." );
		}
		if ( log.isDebugEnabled() ) {
			log.debug( "[doAfterBody] END" );
		}
		return( SKIP_BODY );
	}

	
}
