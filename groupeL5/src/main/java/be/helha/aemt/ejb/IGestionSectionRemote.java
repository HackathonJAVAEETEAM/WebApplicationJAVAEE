package be.helha.aemt.ejb;

import java.util.List;
import javax.ejb.Remote;
import be.helha.aemt.entities.Section;
/*
 * Remote pour l'EJB
 */
@Remote
public interface IGestionSectionRemote {
	
	List<Section> findAll();	

}
