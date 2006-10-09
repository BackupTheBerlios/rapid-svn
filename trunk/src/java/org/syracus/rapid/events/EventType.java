package org.syracus.rapid.events;

/**
 * 
 * @author snwiem
 * @hibernate.class table="event_types"
 */
public class EventType {

	private Long id;
	
	private String name;
	private String icon;
	private String description;
	
	/**
	 * 
	 * @return
	 * @hibernate.property type="text"
	 */
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * 
	 * @return
	 * @hibernate.id generator-class="native"
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property not-null="true"
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}