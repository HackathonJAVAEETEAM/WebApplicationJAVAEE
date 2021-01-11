package be.helha.aemt.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import be.helha.aemt.dao.EtudiantDAO;
import be.helha.aemt.entities.Etudiant;

@Stateless
public class GestionEtudiantEJB implements IGestionEtudiantRemote {

	@EJB
	private EtudiantDAO etudiantDao;
	
	@Override
	public Etudiant add(Etudiant etudiant) {
		return etudiantDao.add(etudiant);
	}

}
