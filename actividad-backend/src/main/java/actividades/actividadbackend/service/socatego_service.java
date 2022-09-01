package actividades.actividadbackend.service;


/* librerias de utilidades */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import actividades.actividadbackend.entity.Socatego;
import actividades.actividadbackend.repository.socatego_repository;

@Service
public class socatego_service {

	
	@Autowired /* Conexión al archivo soproduc_repository para la extracción de la información contenida en el */
	private socatego_repository repo;
	
	/* Servicio de listado de todos los elementos de la base de datos */
	public List<Socatego> ListarTodoscategorias(){
		return repo.findAll();
	}
	
	/* Servicio de listado de los elementos  de la base de datos */
	public ResponseEntity <Optional<Socatego>> buscarcategorias(int id){
		try {
			if(id<=0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			if(repo.findById(id).isEmpty()||repo.findById(id).equals(" ")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			if(repo.existsById(id)) {
				return new ResponseEntity<Optional<Socatego>>(repo.findById(id),HttpStatus.OK);
			}
			return new ResponseEntity<Optional<Socatego>>(repo.findById(id),HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity <Socatego> insertarcategorias(Socatego socatego) {
		try {
			if(socatego.getCat_idcate()<=0 || socatego.getCat_nombre().equals("")||socatego.getCat_nombre()==null||repo.existsById(socatego.getCat_idcate())) {
				return new ResponseEntity<Socatego>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Socatego>(repo.save(socatego),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	public ResponseEntity <Socatego> actualizarcategorias(int id, Socatego socatego) {
		try {
			if(socatego.getCat_idcate()<=0 || socatego.getCat_nombre().equals("")||socatego.getCat_nombre()==null) {
				return new ResponseEntity<Socatego>(HttpStatus.BAD_REQUEST);
			}
			Socatego socatego_modificado=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("id:"+id+"No existe"));
			socatego_modificado.setCat_idcate(socatego.getCat_idcate());
			socatego_modificado.setCat_nombre(socatego.getCat_nombre());
			return new ResponseEntity<Socatego>(repo.save(socatego_modificado),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public void eliminarcategorias(int id) {
		repo.deleteById(id);
	}
	
	
}
