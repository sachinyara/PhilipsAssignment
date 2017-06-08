package demo.healthcare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import demo.healthcare.entity.Institution;
@Repository
public class InstitutionDao {
	
	@Autowired
	EntityManagerFactory emf;
	
	public List<Institution> get(){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("select i from Institution i");
		List<Institution> institutions = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return institutions;
	}
	
	public Institution get(Long id){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Institution institution = em.find(Institution.class, id);
		em.getTransaction().commit();
		em.close();
		return institution;
	}
	
	public Institution save(Institution institution){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(institution);
		em.getTransaction().commit();
		em.close();	
		return institution;
	}
	
	public Institution update(Institution institution){		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(institution);
		em.getTransaction().commit();
		em.close();	
		return institution;
	}
	
	public void delete(Long id){		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Institution institution = em.find(Institution.class, id);
		if(institution != null)
			em.remove(institution);
		em.getTransaction().commit();
		em.close();	
	}
}
