package org.syracus.rapid.files;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Hibernate;

public abstract class AbstractFile {

	private Long id;
	private String fileName;
	private Long fileSize;
	private String contentType;
	private Date fileDate;
	private Blob content;
	

	public InputStream getContentStream() throws SQLException {
		return( getContent().getBinaryStream() );
	}
	
	public void setContentStream( InputStream stream ) throws IOException {
		setContent( Hibernate.createBlob( stream ) );
	}
	
	public void setContentData( byte[] data ) {
		setContent( Hibernate.createBlob( data ) );
	}
	
	/**
	 * 
	 * @return
	 * @hibernate.property type="blob"
	 */
	public Blob getContent() {
		return content;
	}
	public void setContent(Blob content) {
		this.content = content;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * 
	 * @return
	 * @hibernate.id generator-class="native"
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public Date getFileDate() {
		return fileDate;
	}

	public void setFileDate(Date fileDate) {
		this.fileDate = fileDate;
	}
	
	
}
