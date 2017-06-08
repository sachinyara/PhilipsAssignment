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

import demo.healthcare.dao.InstitutionDao;
import demo.healthcare.dao.PatientDao;
import demo.healthcare.entity.Institution;
import demo.healthcare.entity.Patient;

@RestController
public class InstitutionResource {
	@Autowired
	private InstitutionDao institutionDao;
	
	@Autowired
	private PatientDao patientDao;
	
	@RequestMapping(value="institution", method=RequestMethod.GET, produces ="application/json")
	ResponseEntity<List<Institution>> get(){
		if(true);
		List<Institution> institutions = institutionDao.get();
		ResponseEntity<List<Institution>> response = null;
		if(institutions != null)
			response = new ResponseEntity<>(institutions, HttpStatus.OK);
		else
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return response;
	}
	
	@RequestMapping(value="institution/{institutionId}/patients", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<List<Patient>> getAll(@PathVariable Long institutionId){
		 List<Patient> patients = patientDao.getAll(institutionId);
		 if(patients != null)
				return new ResponseEntity<>(patients,HttpStatus.FOUND);
			else
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="institution/{id}", method=RequestMethod.GET, produces ="application/json")
	ResponseEntity<Institution> get(@PathVariable(required=true) Long id){
		Institution institution = institutionDao.get(id);
		ResponseEntity<Institution> response = null;
		if(institution != null)
			response = new ResponseEntity<>(institution, HttpStatus.OK);
		else
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return response;
	}
	
	@RequestMapping(value="institution", method=RequestMethod.POST, consumes ="application/json")
	ResponseEntity<URI> save(@RequestBody Institution institution, HttpServletRequest request){
		institution = institutionDao.save(institution);
		return new ResponseEntity<>(URI.create(request.getRequestURL()+"/"+institution.getId()), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="institution", method=RequestMethod.PUT, consumes ="application/json")
	ResponseEntity<URI> update(@RequestBody Institution institution, HttpServletRequest request){
		institution = institutionDao.update(institution);
		return new ResponseEntity<>(URI.create(request.getRequestURL()+"/"+institution.getId()), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value="institution/{id}", method=RequestMethod.DELETE)
	ResponseEntity<Institution> delete(@PathVariable Long id, HttpServletRequest request){
		institutionDao.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
