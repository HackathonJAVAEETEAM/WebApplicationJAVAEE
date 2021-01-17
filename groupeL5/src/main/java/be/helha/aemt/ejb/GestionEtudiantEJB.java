package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import be.helha.aemt.dao.EtudiantDAO;
import be.helha.aemt.entities.AssociationAA;
import be.helha.aemt.entities.AssociationUE;
import be.helha.aemt.entities.Etudiant;
/*
 * Je rends la classe stateless et localbean 
 */
@Stateless
@LocalBean
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

	@Override
	public List<Etudiant> findWithParam(String classe) {
		return etudiantDao.findWithParam(classe);
	}

	@Override
	public void updateUeEtudiant(Etudiant etudiant) {
		etudiantDao.updateUeEtudiant(etudiant);
	}
	
	@Override
	public void updateDelibEtudiant(Etudiant etudiant){
		etudiantDao.updateDelibEtudiant(etudiant);
	}
	
	@Override
	public Integer generatePropPae(Etudiant etudiant){
		return etudiantDao.generatePropPae(etudiant);
	}

	@Override
	public void removePropUe(Etudiant etudiant) {
		etudiantDao.removePropUe(etudiant);
	}

	@Override
	public void addPropPae(Etudiant etudiant) {
		etudiantDao.addPropPae(etudiant);
	}

}
