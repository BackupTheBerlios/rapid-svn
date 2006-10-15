package org.syracus.rapid.calendar.taglib;

/**
 * 
 * @author snwiem
 * @jsp.tag name="dayLink" body-content="scriptless"
 */
public class DayLinkTag extends AbstractPropertyTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int day;
	private String target;
	private String style;
	private String classId;
	
	/**
	 * @jsp.attribute required="true" rtexprvalue="true"
	*/
	public int getDay() {
		return day;
	}


	public void setDay(int day) {
		this.day = day;
	}
	
	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	*/
	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}
	
	

	/**
	 * @jsp.attribute required="false" rtexprvalue="true"
	*/
	public String getClassId() {
		return classId;
	}


	public void setClassId(String classId) {
		this.classId = classId;
	}

	/**
	 * @jsp.attribute required="false" rtexprvalue="true" 
	*/
	public String getStyle() {
		return style;
	}


	public void setStyle(String style) {
		this.style = style;
	}


	@Override
	protected String getProperty() {
		return( AbstractCalendarTag.PROPERTY_DAYLINK + "." + getDay() );
	}


	@Override
	protected Object getPropertyValue() {
		DayLink link = new DayLink();
		link.setUrl( getBodyContent().getString() );
		link.setTarget( getTarget() );
		link.setStyle( getStyle() );
		link.setClassId( getClassId() );
		return( link );
	}
	
	

}
