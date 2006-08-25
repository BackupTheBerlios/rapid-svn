package org.syracus.rapid.stripes;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.syracus.spring.bsf.ScriptLoader;
import org.syracus.stripes.ScriptActionBean;

public class RapidScriptActionBean extends ScriptActionBean {

	private RapidActionBeanContext actionBeanContext;
	private ScriptLoader scriptLoader;
	
	@SpringBean("scriptLoader")
	public void setScriptLoader( ScriptLoader scriptLoader ) {
		this.scriptLoader = scriptLoader;
	}
	
	@Override
	public ScriptLoader getScriptLoader() {
		// TODO Auto-generated method stub
		return null;
	}

	public RapidActionBeanContext getContext() {
		return( this.actionBeanContext );
	}

	public void setContext(ActionBeanContext context) {
		this.actionBeanContext = (RapidActionBeanContext)context;
	}

}
