package org.syracus.rapid.calendar.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class CalendarLinkTag extends TagSupport {

	private String link;

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public int doStartTag() throws JspException {
		MonthCalendarTag tag = (MonthCalendarTag)super.findAncestorWithClass( this, MonthCalendarTag.class );
		applyLink( tag, getLink() );
		return( EVAL_PAGE );
	}
	
	protected abstract void applyLink( MonthCalendarTag calendar, String link );
	

	
}
