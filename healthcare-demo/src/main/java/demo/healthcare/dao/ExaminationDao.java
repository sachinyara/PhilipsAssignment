package demo.healthcare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import demo.healthcare.entity.Examination;

@Repository
public class ExaminationDao {
	@Autowired
	EntityManagerFactory emf;
	
	public List<Examination> getAll(Long patientId){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Examination> examinations = em.createNamedQuery("Examination.findByPatientId").setParameter("patientId", patientId).getResultList();
		em.getTransaction().commit();
		em.close();
		return examinations;
	}
	
	public Examination get(Long id){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Examination examination = em.find(Examination.class, id);
		em.getTransaction().commit();
		em.close();
		return examination;
	}
	
	public Examination save(Examination examination){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(examination);
		em.getTransaction().commit();
		em.close();	
		return examination;
	}
	
	public Examination update(Examination examination){		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(examination);
		em.getTransaction().commit();
		em.close();	
		return examination;
	}
	
	public void delete(Long id){		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Examination examination = em.find(Examination.class, id);
		if(examination != null)
			em.remove(examination);
		em.getTransaction().commit();
		em.close();	
	}
}
