package org.syracus.rapid.actions.components;

import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@UrlBinding("/protected/key.action")
public class KeyInputAction extends BaseComponentActionBean {

	protected static final transient Log log = LogFactory.getLog( KeyInputAction.class );
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Resolution matches() {
		if ( log.isDebugEnabled() ) {
			log.debug( "[matches] value = '" + getValue() + "'" );
		}
		String result = null;
		Integer count = getComponentService().getNumberOfModuleKeys( getValue() );
		result = count + " matches.";
		return( new StreamingResolution("text", result ) );
	}
}
