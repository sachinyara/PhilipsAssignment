package demo.healthcare.rest;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.healthcare.dao.ExaminationDao;
import demo.healthcare.dao.PatientDao;
import demo.healthcare.entity.Examination;
import demo.healthcare.entity.Patient;
import demo.healthcare.util.Utilities;

@RestController
public class PatientResource {
	@Autowired
	private PatientDao patientDao;
	
	@Autowired
	private ExaminationDao examinationDao;
	
	@RequestMapping(value="patient/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Patient> get(@PathVariable Long id){
		Patient patient = patientDao.get(id);
		if(patient != null)
			return new ResponseEntity<>(patient,HttpStatus.FOUND);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="patient/{patientId}/examinations", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Examination>> getAll(@PathVariable Long patientId){
		 List<Examination> examinations = examinationDao.getAll(patientId);
		 if(examinations != null){
			 for(Examination examination: examinations){
				 examination.setBmi(examination.getWeight()/(examination.getHeight()*examination.getHeight()));
			 }
				return new ResponseEntity<>(examinations,HttpStatus.FOUND);
		 }	
		 else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="patient", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<URI> save(@RequestBody Patient patient, HttpServletRequest request){
		patient = patientDao.save(patient);
		return new ResponseEntity<>(URI.create(request.getRequestURL()+"/"+patient.getId()),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="patient", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<URI> update(@RequestBody Patient patient, HttpServletRequest request){
		patient = patientDao.update(patient);
		return new ResponseEntity<>(URI.create(request.getRequestURL()+"/"+patient.getId()),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="patient/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Patient> delete(@PathVariable Long id){
		patientDao.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@RequestMapping(value="patient/{id}/age", method=RequestMethod.GET)
	public ResponseEntity<String> patientAge(@PathVariable Long id){
		Patient patient = patientDao.get(id);
		if(patient != null){
			String ageStr = Utilities.calculateAge(patient.getDateOfBirth());
			return new ResponseEntity<>(ageStr, HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
