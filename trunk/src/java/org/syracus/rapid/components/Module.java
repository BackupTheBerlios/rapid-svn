package org.syracus.rapid.components;

import java.util.List;

import org.syracus.rapid.files.ModuleAttachement;
import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="modules"
 */
public class Module extends ABaseComponent {

	private User leader;
	private List<ModuleAttachement> attachements;
	
	/**
	 * 
	 * @return
	 * @hibernate.set lazy="true" cascade="all-delete-orphan" table="module_files"
	 * @hibernate.key column="module_id"
	 * @hibernate.one-to-many class="org.syracus.rapid.files.ModuleAttachement"/>
	 */
	public List<ModuleAttachement> getAttachements() {
		return attachements;
	}
	public void setAttachements(List<ModuleAttachement> attachements) {
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
