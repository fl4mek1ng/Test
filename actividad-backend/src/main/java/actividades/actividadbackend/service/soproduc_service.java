package actividades.actividadbackend.service;

/* librerias de utilidades */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import actividades.actividadbackend.entity.Socatego;
import actividades.actividadbackend.entity.Soproduc;
import actividades.actividadbackend.repository.soproduc_repository;

@Service
public class soproduc_service {

	
	@Autowired /* Conexión al archivo soproduc_repository para la extracción de la información contenida en el */
	private soproduc_repository repo;
	
	/* Servicio de listado de todos los elementos de la base de datos */
	public List<Soproduc> ListarTodosproductos(){
		return repo.findAll();
	}
	
	/* Servicio de listado de los elementos  de la base de datos */
	public ResponseEntity <Optional<Soproduc>>buscarproductos(int id){
		try {
			if(id<=0) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}if(repo.findById(id).isEmpty()||repo.findById(id).equals(" ")) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			if(repo.existsById(id)) {
				return new ResponseEntity<Optional<Soproduc>>(repo.findById(id),HttpStatus.OK);
			}
			return new ResponseEntity<Optional<Soproduc>>(repo.findById(id),HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity <Soproduc> insertarproductos(Soproduc soproduc) {
		try {
			if(soproduc.getPro_nompro().equals("")||soproduc.getPro_nompro()==null || soproduc.getPro_prepro()==0||repo.existsById(soproduc.getPro_idprod())){
				return new ResponseEntity<Soproduc>(HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<Soproduc>(repo.save(soproduc),HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity <Soproduc> actualizarproductos(int id, Soproduc soproduc) {
		if(soproduc.getPro_idprod()<0 || soproduc.getPro_nompro().equals("")||soproduc.getPro_nompro()==null) {
			return new ResponseEntity<Soproduc>(HttpStatus.BAD_REQUEST);
		}
		if(repo.findById(id).isEmpty()||repo.findById(id).equals(" ")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Soproduc soproduc_modificado=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("id:"+id+"No existe"));
		soproduc_modificado.setPro_nompro(soproduc.getPro_nompro());
		soproduc_modificado.setPro_nompro(soproduc.getPro_nompro());
		return new ResponseEntity<Soproduc>(repo.save(soproduc_modificado),HttpStatus.OK);
	}
	
	public void eliminarproductos(int id) {
		repo.deleteById(id);
	}
	
	
}
