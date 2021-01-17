package be.helha.aemt.ejb;

import javax.ejb.Remote;
/*
 * Remote pour l'EJB
 */
@Remote
public interface IGestionFileRemote {
	
	public void cleanTableaBeforeImport();
	
}	
