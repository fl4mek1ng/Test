package actividades.actividadbackend.Controller;

/* importación de archivos de las entidades, como el modelo y el servicio CRUD */
import actividades.actividadbackend.service.socatego_service;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import actividades.actividadbackend.entity.Socatego;
import actividades.actividadbackend.entity.Soproduc;

/* librerias de utilidades */
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

/* paquetes de libreria para las etiquetas de los servicios */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/socatego")
public class socatego_controller {
	
	@Autowired
	private socatego_service service;
	
	@ApiOperation(value="recibe todos las categorias", 
			notes="Muestra todos los elementos de la tabla socatego,"
			+"en caso de no poder mostrar retorna un internal failure.")
	@GetMapping /* control del servicio de listado de todos los elementos de la base de datos */
	@ApiResponses(value = { @ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST,message="Faltan Elementos por llenar"),
			@ApiResponse(code=HttpServletResponse.SC_INTERNAL_SERVER_ERROR,message="Algo salió mal")})
	public List<Socatego> listartodos(){
		return service.ListarTodoscategorias();
	}
	
	@ApiOperation(value="recibe una categoria de la tabla socatego", 
			notes="Muestra una categoria en función del id de busqueda ingresado en la petición,"
			+"en caso de ingresar un id menor o igual a 0 retorna un bad request,"
			+"y en caso de que se generé un error interno retorna dicho error para mostrarse en pantalla.")
	@GetMapping(path="/{id}") /* control de get por el id del campo */
	@ApiResponses(value = { @ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST,message="Faltan Elementos por llenar"),
			@ApiResponse(code=HttpServletResponse.SC_INTERNAL_SERVER_ERROR,message="Algo salió mal")})
	public ResponseEntity <Optional<Socatego>>buscar(@PathVariable ("id") int id){
		return service.buscarcategorias(id);
		
	}
	
	@ApiOperation(value="Ingresa un nuevo elemento de categoria a la tabla Socatego", 
			notes="recibe un objeto de tipo socatego, que tiene relación con la entidad para validar"
				+ "	 los campos ingresados y posteriormente agregar,"
				+ "	la nueva categoria")
	@PostMapping /* control de inserción de un nuevo campo utilizando el cuerpo de la entidad*/
	@ApiResponses(value = { @ApiResponse(code=HttpServletResponse.SC_CREATED,message="Elemento creado"),
			@ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST,message="Faltan Elementos por llenar"),
			@ApiResponse(code=HttpServletResponse.SC_INTERNAL_SERVER_ERROR,message="Algo salió mal"),})
	public ResponseEntity <Socatego> insertar(@RequestBody Socatego entity) {
		return service.insertarcategorias(entity);
			
	}
	
	@ApiOperation(value="Actualiza los valores de una categoria a la tabla Socatego",
			notes="recibe los datos de una entidad socatego, con la finalidad de evaluar los datos,"
			+"los datos que se ingresan y posteriormente se actualizan los campos nuevos")
	@PutMapping(path="/{id}") /* Control de la actualización de un campo ya existente confirmado por el id */
	@ApiResponses(value = { @ApiResponse(code=HttpServletResponse.SC_CREATED,message="Elemento Actualizado"),
			@ApiResponse(code=HttpServletResponse.SC_BAD_REQUEST,message="Faltan Elementos por llenar"),
			@ApiResponse(code=HttpServletResponse.SC_INTERNAL_SERVER_ERROR,message="Algo salió mal"),})
	public ResponseEntity<Socatego> actualizar(@PathVariable ("id") int id, @RequestBody Socatego entity) {
		return service.actualizarcategorias(id, entity);
		
	}
	
	@ApiOperation(value="Elimina un producto", 
			notes="Se busca un producto por el id, y posteriormente se elimina el registro de dicho producto.")
	@DeleteMapping(path="/{id}")  /* Control de la eliminación de un campo confirmado por el id */
	@ApiResponses(value = { @ApiResponse(code=HttpServletResponse.SC_OK,message="Elemento eliminado"),
			@ApiResponse(code=HttpServletResponse.SC_NOT_FOUND,message="No existe el elemento que se desesa eliminar"),
			@ApiResponse(code=HttpServletResponse.SC_INTERNAL_SERVER_ERROR,message="Algo salió mal"),})
	public ResponseEntity <Socatego> eliminar(@PathVariable ("id") int id) {
		try {
			service.eliminarcategorias(id);
			return new ResponseEntity<Socatego>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
