package org.syracus.rapid.stripes;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Base action class for all rapid internal action beans.
 * Just following pattern as descriped on stripes homepage.
 * 
 * @author snwiem
 *
 */
public class RapidActionBean implements ActionBean {

	private RapidActionBeanContext actionBeanContext;
	
	public RapidActionBeanContext getContext() {
		return( this.actionBeanContext );
	}

	public void setContext(ActionBeanContext context) {
		this.actionBeanContext = (RapidActionBeanContext)context;
	}

}
