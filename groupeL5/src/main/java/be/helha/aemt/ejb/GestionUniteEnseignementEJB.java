package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.helha.aemt.dao.UniteEnseignementDAO;
import be.helha.aemt.entities.UniteEnseignement;
/*
 * Je rends la classe stateless et localbean 
 */
@Stateless
@LocalBean
public class GestionUniteEnseignementEJB implements IGestionUniteEnseignementRemote {

	@EJB
	private UniteEnseignementDAO ueDao;
	
	@Override
	public List<UniteEnseignement> findAll() {
		return ueDao.findAll(); 
	}

	@Override
	public UniteEnseignement add(UniteEnseignement ue) {
		return ueDao.add(ue);
	}

}
