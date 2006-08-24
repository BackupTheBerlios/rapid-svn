package org.syracus.spring.bsf;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.syracus.spring.BeanPropertyEditor;

public class ScriptEnginePropertyEditor extends BeanPropertyEditor {

	@Override
	public String getAsText() {
		return super.getAsText();
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Map<String,Object> properties = super.extractProperties( text );
		ScriptEngine engine = new ScriptEngine();
		try {
			String extensions = (String)properties.get( "extensions" );
			properties.put( "extensions", parseExtensions( extensions ) );
			BeanUtils.populate( engine, properties );
			setValue( engine );
		} catch( InvocationTargetException e ) {
			throw new IllegalArgumentException( e );
		} catch( IllegalAccessException e ) {
			throw new IllegalArgumentException( e );
		}
	}
	
	protected String[] parseExtensions( String ext ) {
		String[] extensions = StringUtils.split( ext, "," );
		return( extensions );
	}
}
