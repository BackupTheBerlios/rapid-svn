package org.syracus.rapid.events;

import java.util.Date;
import java.util.Set;

import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="events"
 */
public class Event {

	private Long id;
	
	private EventType type;
	private String title;
	private String description;
	
	private Date startDate;
	private Date endDate;
	
	private User leader;
	private Set<User> participants;
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one
	 */
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
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
	 * @hibernate.property not-null="true"
	 */
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	 * @hibernate.many-to-one
	 */
	public User getLeader() {
		return leader;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}
	/**
	 * 
	 * @return
	 * @hibernate.set table="event_participants" lazy="true" 
	 * @hibernate.key column="event_id"
	 * @hibernate.many-to-many column="user_id" class="org.syracus.rapid.realm.User"
	 */
	public Set<User> getParticipants() {
		return participants;
	}
	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property not-null="true"
	 */
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property not-null="true"
	 */
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
