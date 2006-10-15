package org.syracus.rapid.calendar.taglib;

public class DayLink {

	private String url;
	private String target;
	private String style;
	private String classId;
	
	public DayLink() {
		
	}
	
	public DayLink( String url, String target ) {
		setUrl( url );
		setTarget( target );
	}
	
	
	
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
