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
import demo.healthcare.entity.Examination;

@RestController
public class ExaminationResource {
	@Autowired
	private ExaminationDao examinationDao;
	
	@RequestMapping(value="examination/{id}", method=RequestMethod.GET, produces="application/json")
	public ResponseEntity<Examination> get(@PathVariable Long id){
		Examination examination = examinationDao.get(id);
		if(examination != null){
			examination.setBmi(examination.getWeight()/(examination.getHeight()*examination.getHeight()));
			return new ResponseEntity<>(examination,HttpStatus.FOUND);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="examination", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<URI> save(@RequestBody Examination examination, HttpServletRequest request){
		examination = examinationDao.save(examination);
		return new ResponseEntity<>(URI.create(request.getRequestURL()+""+examination.getId()),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="examination", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<URI> update(@RequestBody Examination examination, HttpServletRequest request){
		examination = examinationDao.update(examination);
		return new ResponseEntity<>(URI.create(request.getRequestURL()+""+examination.getId()),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="examination/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Examination> delete(@PathVariable Long id){
		examinationDao.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
