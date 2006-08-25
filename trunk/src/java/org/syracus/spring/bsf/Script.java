package org.syracus.spring.bsf;

import java.io.File;

public class Script {

	private File source;
	private String code;
	private long timestamp;
	private String language;
	
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public File getSource() {
		return source;
	}
	public void setSource(File source) {
		this.source = source;
	}
	
	public boolean isModified() {
		long lastModified = this.source.lastModified();
		return( lastModified > this.timestamp );
	}
	
	
}
