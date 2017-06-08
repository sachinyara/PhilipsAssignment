package demo.healthcare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import demo.healthcare.entity.Patient;

@Repository
public class PatientDao {
	@Autowired
	EntityManagerFactory emf;
	
	public Patient get(Long id){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Patient patient = em.find(Patient.class, id);
		em.getTransaction().commit();
		em.close();	
		return patient;
	}
	public List<Patient> getAll(Long institutionId){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Patient> patients = em.createNamedQuery("Patient.findByInstitutionId").setParameter("institutionId", institutionId).getResultList();
		em.getTransaction().commit();
		em.close();	
		return patients;
	}
	
	public Patient save(Patient patient){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
		em.close();	
		return patient;
	}
	
	public Patient update(Patient patient){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(patient);
		em.getTransaction().commit();
		em.close();	
		return patient;
	}
	
	public void delete(Long id){		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Patient patient = em.find(Patient.class, id);
		if(patient != null)
			em.remove(patient);
		em.getTransaction().commit();
		em.close();	
	}
}
