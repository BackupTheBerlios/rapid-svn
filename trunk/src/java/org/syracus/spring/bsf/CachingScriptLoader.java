package org.syracus.spring.bsf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.bsf.BSFException;

public class CachingScriptLoader extends NonCachingScriptLoader {

	private Map<String,Script> cache = new HashMap<String,Script>();
	
	@Override
	protected Script findScript(String name)
		throws IOException, BSFException
	{
		Script script = this.cache.get( name );
		if ( null == script ) {
			if ( null != ( script = super.findScript( name ) ) ) {
				this.cache.put( name, script );
			}
		} else if ( script.isModified() ) {
			script.setCode( super.readFile( script.getSource() ) );
			script.setTimestamp( System.currentTimeMillis() );
		}
		return( script );
	}
	
	

	
}
