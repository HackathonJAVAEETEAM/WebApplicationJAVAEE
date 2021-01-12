package be.helha.aemt.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.helha.aemt.dao.SectionDAO;
import be.helha.aemt.entities.Section;

@Stateless
@LocalBean
public class GestionSectionEJB implements IGestionSectionRemote {

	@EJB
	private SectionDAO sectionDao;
	
	@Override
	public List<Section> findAll() {
		return sectionDao.findAll();
	}
	
}
