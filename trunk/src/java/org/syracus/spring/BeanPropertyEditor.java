package org.syracus.spring;

import java.beans.PropertyEditorSupport;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class BeanPropertyEditor extends PropertyEditorSupport {

	protected Map<String,Object> extractProperties( String properties ) {
		Map<String,Object> ret = new HashMap<String,Object>();
		String[] props = StringUtils.split( properties, ';' );
		for ( int i = 0; i < props.length; ++i ) {
			String prop = props[i];
			String[] values = StringUtils.split( prop, '=' );
			ret.put( values[0], values[1] );
		}
		return( ret );
	}
	
}
