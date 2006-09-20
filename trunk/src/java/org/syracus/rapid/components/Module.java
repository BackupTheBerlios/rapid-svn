package org.syracus.rapid.components;

import java.util.Set;

import org.syracus.rapid.files.ModuleAttachement;
import org.syracus.rapid.history.ModuleHistory;
import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="modules"
 */
public class Module extends ABaseComponent {

	private User leader;
	private Set<ModuleAttachement> attachements;
	private Set<ModuleHistory> history;
	
	/**
	 * 
	 * @return
	 * @hibernate.set lazy="true" cascade="all-delete-orphan" inverse="true"
	 * @hibernate.key column="module"
	 * @hibernate.one-to-many class="org.syracus.rapid.history.ModuleHistory"/>
	 */
	public Set<ModuleHistory> getHistory() {
		return history;
	}
	public void setHistory(Set<ModuleHistory> history) {
		this.history = history;
	}
	/**
	 * 
	 * @return
	 * @hibernate.set lazy="true" cascade="all-delete-orphan" inverse="true"
	 * @hibernate.key column="module"
	 * @hibernate.one-to-many class="org.syracus.rapid.files.ModuleAttachement"/>
	 */
	public Set<ModuleAttachement> getAttachements() {
		return attachements;
	}
	public void setAttachements(Set<ModuleAttachement> attachements) {
		this.attachements = attachements;
	}
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="true"
	 */
	public User getLeader() {
		return leader;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}
	
	
}
