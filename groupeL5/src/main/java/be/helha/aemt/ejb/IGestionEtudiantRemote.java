package be.helha.aemt.ejb;

import javax.ejb.Remote;

import be.helha.aemt.entities.Etudiant;

@Remote
public interface IGestionEtudiantRemote {
	public Etudiant add(Etudiant etudiant);
}
