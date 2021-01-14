package be.helha.aemt.ejb;

import javax.ejb.Remote;

@Remote
public interface IGestionFileRemote {
	
	public void cleanTableaBeforeImport();
	
}	
