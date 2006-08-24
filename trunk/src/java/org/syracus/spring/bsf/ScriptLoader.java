package org.syracus.spring.bsf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class ScriptLoader {

	protected static final int DEFAULT_BUFFERSIZE = 1024;
	protected static final String DEFAULT_ENCODING = "UTF-8";

	private int bufferSize = DEFAULT_BUFFERSIZE;
	private String encoding = DEFAULT_ENCODING;
	private Resource location;
	private List<Resource> locations;
	
	
	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public Resource getLocation() {
		return location;
	}

	public void setLocation(Resource location) {
		this.location = location;
	}

	public List<Resource> getLocations() {
		return locations;
	}

	public void setLocations(List<Resource> locations) {
		this.locations = locations;
	}

	public void afterPropertiesSet() throws Exception {
		if ( null != getLocation() ) {
			if ( null == getLocations() ) {
				List<Resource> locations = new ArrayList<Resource>();
				locations.add( getLocation() );
				setLocations( locations );
			} else {
				getLocations().add( 0, getLocation() );
			}
		}
		if ( null != getLocations() ) {
			for ( Resource location : getLocations() ) {
				checkLocation( location );
			}
		}
	}
	
	public Script getScript( String source )	{
		Script script = null;
		for ( Resource location : getLocations() ) {
			try {
				File f = new File( location.getFile(), source );
				if ( false == f.exists() ) {
					continue;
					//throw new BSFScriptingException( "Requested source file not found." );
				}
				if ( false == f.isFile() ) {
					continue;
					//throw new BSFScriptingException( "Requested source file not found." );
				}
				if ( false == f.canRead() ) {
					continue;
					//throw new BSFScriptingException( "Requested source file not found." );
				}
				
				script = loadScript( f );
				break;
			} catch( IOException e ) {
				throw new ScriptException( "Failed to load script.", e );
			} catch( BSFException e ) {
				throw new ScriptException( "Failed to load script.", e );
			}
		}
		if ( null == script ) {
			throw new ScriptException( "Requested source file '" + source + "' not found." );
		}
		return( script );
	}
	
	protected void checkLocation( Resource resource )
		throws IOException
	{
		checkLocation( resource.getFile() );
	}
	
	protected void checkLocation( File location ) {
		Assert.isTrue( location.exists(), "Location '" + location.getAbsolutePath() + "' does not exists." );
		Assert.isTrue( location.isDirectory(), "Location '" + location.getAbsolutePath() + "' is not a directory." );
	}

	protected Script loadScript( File file )
		throws IOException, BSFException
	{
		Script script = new Script();
		script.setSource( file );
		script.setLanguage( BSFManager.getLangFromFilename( file.getName() ) );
		script.setCode( readFile( file ) );
		script.setModified( file.lastModified() );
		return( script );
	}
	
	protected String readFile( File file ) throws IOException {
		FileInputStream istream = new FileInputStream( file );
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		streamCopy( istream, ostream );
		return( ostream.toString( DEFAULT_ENCODING ) );
	}
	
	protected void streamCopy( InputStream istream, OutputStream ostream )
		throws IOException
	{
		streamCopy( istream, ostream, DEFAULT_BUFFERSIZE );
	}
	
	protected void streamCopy( InputStream istream, OutputStream ostream, int size )
		throws IOException
	{
		byte[] buffer = new byte[ size ];
		int rb = 0;
		while ( -1 != ( rb = istream.read( buffer, 0, size ) ) ) {
			ostream.write( buffer, 0, rb );
		}
	}
	
}
