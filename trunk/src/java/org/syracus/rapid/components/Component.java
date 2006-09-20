package org.syracus.rapid.components;

import java.util.Set;

import org.syracus.rapid.files.ComponentAttachement;
import org.syracus.rapid.history.ComponentHistory;
import org.syracus.rapid.realm.User;

/**
 * 
 * @author snwiem
 * @hibernate.class table="components"
 */
public class Component extends ABaseComponent {

	private Module module;
	private Project project;
	private User leader;
	private Set<ComponentAttachement> attachements;
	private Set<ComponentHistory> history;
	
	/**
	 * 
	 * @return
	 * @hibernate.set lazy="true" cascade="all-delete-orphan" inverse="true"
	 * @hibernate.key column="component"
	 * @hibernate.one-to-many class="org.syracus.rapid.history.ComponentHistory"/>
	 */
	public Set<ComponentHistory> getHistory() {
		return history;
	}
	public void setHistory(Set<ComponentHistory> history) {
		this.history = history;
	}
	/**
	 * 
	 * @return
	 * @hibernate.set lazy="true" cascade="all-delete-orphan" inverse="true"
	 * @hibernate.key column="component"
	 * @hibernate.one-to-many class="org.syracus.rapid.files.ComponentAttachement"/>
	 */
	public Set<ComponentAttachement> getAttachements() {
		return attachements;
	}
	public void setAttachements(Set<ComponentAttachement> attachements) {
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
	
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="false"
	 */
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="false"
	 */
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	public String dump() {
		StringBuffer sb = new StringBuffer();
		sb.append( "Component{" );
		sb.append( "id=" + getId() );
		sb.append( ",key=" + getKey() );
		sb.append( ",name=" + getName() );
		sb.append( ",description=" + getDescription() );
		sb.append( ",creator=" + getCreator() );
		sb.append( ",modifier=" + getModifier() );
		sb.append( ",module=" + getModule() );
		sb.append( ",project=" + getProject() );
		sb.append( "}" );
		return( sb.toString() );
	}
}
