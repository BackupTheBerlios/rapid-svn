package org.syracus.rapid.profiles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class UserProfile {

	public static final String KEY_MAX_MODULES = "max-modules";
	public static final String DEF_MAX_MODULES = "10";
	public static final String KEY_MAX_PROJECTS = "max-projects";
	public static final String DEF_MAX_PROJECTS = "10";
	public static final String KEY_MAX_COMPONENTS = "max-components";
	public static final String DEF_MAX_COMPONENTS = "10";
	public static final String KEY_MAX_TODOS = "max-todos";
	public static final String DEF_MAX_TODOS = "10";
	
	private Long id;
	private Properties properties;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public String getProperty( String key ) {
		return( (null != this.properties) ? this.properties.getProperty( key ) : null );
	}
	
	public String getProperty( String key, String defaultValue ) {
		return( (null != this.properties) ? this.properties.getProperty( key, defaultValue ) : defaultValue );
	}
	
	public void setProperty( String key, String value ) {
		if ( null == this.properties ) {
			synchronized( this ) {
				if ( null == this.properties ) {
					this.properties = new Properties();
				}
			}
		}
		this.properties.setProperty( key, value );
	}
	
	public Enumeration getPropertyNames() {
		return( (null != this.properties) ? this.properties.propertyNames() : null );
	}
	
	public static UserProfile getDefaultProfile( Long id ) {
		UserProfile profile = new UserProfile();
		profile.setId( id );
		profile.setProperties( getDefaultProperties() );
		return( profile );
	}
	
	public static Properties getDefaultProperties() {
		Properties properties = new Properties();
		properties.setProperty( KEY_MAX_MODULES, DEF_MAX_MODULES );
		properties.setProperty( KEY_MAX_PROJECTS, DEF_MAX_PROJECTS );
		properties.setProperty( KEY_MAX_COMPONENTS, DEF_MAX_COMPONENTS );
		properties.setProperty( KEY_MAX_TODOS, DEF_MAX_TODOS );
		return( properties );
	}
	
	// some helper methods
	public static String getPropertiesAsString( Properties properties ) throws IOException {
		if ( null != properties ) {
			ByteArrayOutputStream ostream = new ByteArrayOutputStream();
			properties.store( ostream, null );
			return( ostream.toString( "UTF-8" ) );
		}
		return( null );
	}
	
	public static Properties getPropertiesFromString( String str ) throws IOException {
		ByteArrayInputStream istream = new ByteArrayInputStream( str.getBytes( "UTF-8" ) );
		Properties properties = new Properties();
		properties.load( istream );
		return( properties );
	}
	
}
