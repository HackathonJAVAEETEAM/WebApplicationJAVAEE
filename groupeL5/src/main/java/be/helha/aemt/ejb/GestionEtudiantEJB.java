package be.helha.aemt.ejb;

import java.util.List;

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

	@Override
	public List<Etudiant> findAll() {
		return etudiantDao.findAll();
	}

}
