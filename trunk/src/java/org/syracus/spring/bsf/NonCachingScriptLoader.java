package org.syracus.spring.bsf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class NonCachingScriptLoader implements ScriptLoader, InitializingBean {

	public static final String DEFAULT_ENCODING = "UTF-8";
	public static final int DEFAULT_BUFFERSIZE = 1024;
	
	private List<Resource> locations;
	private String encoding = DEFAULT_ENCODING;
	
	
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setLocation( Resource location ) {
		if ( null == this.locations ) {
			this.locations = new ArrayList<Resource>();
		}
		this.locations.add( 0, location );
	}
	
	public void setLocations( Resource[] locations ) {
		if ( null == locations ) {
			this.locations = new ArrayList<Resource>();
		}
		this.locations.addAll( Arrays.asList( locations ) );
	}
	
	public List<Resource> getLocations() {
		return( this.locations );
	}

	public void afterPropertiesSet() throws Exception {
		// check configured locations
		for ( Resource location : this.locations ) {
			File file = location.getFile();
			if ( !file.exists() ) {
				new IOException( "Resource '" + location.getFilename() + "' does not exist." );
			}
			if ( !file.isDirectory() ) {
				new IOException( "Resource '" + location.getFilename() + "' is not a directory." );
			}
			if ( !file.canRead() ) {
				new IOException( "Unable to access resource '" + location.getFilename() + "'." );
			}
		}
	}

	public Script getScript(String name) {
		try {
			return( findScript( name ) );
		} catch( IOException e ) {
			throw new ScriptException( "Failed to load script '" + name + "'", e );
		} catch( BSFException e ) {
			throw new ScriptException( "Failed to load script '" + name + "'", e );
		}
	}
	
	protected Script findScript( String name )
		throws IOException, BSFException
	{
		for ( Resource location : this.locations ) {
			File source = new File( location.getFile(), name );
			if ( source.exists() && source.canRead() ) {
				return( loadScript( source ) );
			}
		}
		return( null );
	}
	
	protected Script loadScript( File file )
		throws IOException, BSFException
	{
		String code = readFile( file );
		
		Script script = new Script();
		script.setSource( file );
		script.setCode( code );
		script.setTimestamp( System.currentTimeMillis() );
		script.setLanguage( BSFManager.getLangFromFilename( file.getName() ) );
		return( script );
	}
	
	protected String readFile( File file )
		throws IOException
	{
		FileInputStream istream = null;
		try {
			istream = new FileInputStream( file );
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			streamCopy( istream, ostream, DEFAULT_BUFFERSIZE );
			return( ostream.toString( getEncoding() ) );
		} finally {
			if ( null != istream ) {
				try {
					istream.close();
				} catch( IOException e ) {
					// do nothing
				}
			}
		}
	}
	
	protected void streamCopy( InputStream istream, OutputStream ostream, int bufferSize )
		throws IOException
	{
		byte[] buffer = new byte[ bufferSize ];
		int rb = -1;
		while ( -1 != ( rb = istream.read( buffer, 0, bufferSize ) ) ) {
			ostream.write( buffer, 0, rb );
		}
	}

}
