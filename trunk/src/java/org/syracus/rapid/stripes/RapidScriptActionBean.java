package org.syracus.rapid.stripes;

import org.syracus.stripes.ScriptActionBean;

import net.sourceforge.stripes.action.ActionBeanContext;

public class RapidScriptActionBean extends ScriptActionBean {

	private RapidActionBeanContext actionBeanContext;
	
	public RapidActionBeanContext getContext() {
		return( this.actionBeanContext );
	}

	public void setContext(ActionBeanContext context) {
		this.actionBeanContext = (RapidActionBeanContext)context;
	}

}
