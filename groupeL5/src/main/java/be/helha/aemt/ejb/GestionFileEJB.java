package be.helha.aemt.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.helha.aemt.dao.EtudiantDAO;
import be.helha.aemt.dao.FileDAO;

@Stateless
@LocalBean
public class GestionFileEJB implements IGestionFileRemote {

	@EJB
	private FileDAO fileDao;
	
	@Override
	public void cleanTableaBeforeImport() {
		fileDao.cleanTableBeforeImport();
	}

}
