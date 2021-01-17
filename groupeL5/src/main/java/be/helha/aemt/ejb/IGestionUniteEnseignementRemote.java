package be.helha.aemt.ejb;

import java.util.List;
import javax.ejb.Remote;
import be.helha.aemt.entities.Etudiant;
import be.helha.aemt.entities.UniteEnseignement;
/*
 * Remote pour l'EJB
 */
@Remote
public interface IGestionUniteEnseignementRemote {
	
	public List<UniteEnseignement> findAll();
	
	public UniteEnseignement add(UniteEnseignement ue);
	
}
