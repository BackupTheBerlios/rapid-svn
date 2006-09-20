package org.syracus.rapid.issues;

/**
 * 
 * @author snwiem
 * @hibernate.class table="issue_types"
 */
public class IssueType {

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
	 * @hibernate.property
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
