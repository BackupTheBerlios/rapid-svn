package org.syracus.rapid.history;

public interface IHistoryService {

	public void addHistory( ModuleHistory history );
	public void addHistory( ProjectHistory history );
	public void addHistory( ComponentHistory history );
	
}
