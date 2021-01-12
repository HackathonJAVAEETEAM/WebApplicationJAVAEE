package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.Remote;

import be.helha.aemt.entities.Etudiant;

@Remote
public interface IGestionEtudiantRemote {
	
	public Etudiant add(Etudiant etudiant);
	
	public List<Etudiant> findAll();
	
	public List<Etudiant> findWithParam(String classe);
}
