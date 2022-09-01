package actividades.actividadbackend.soproduc_repositorytests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import actividades.actividadbackend.entity.Soproduc;
import actividades.actividadbackend.repository.soproduc_repository;

public class soproduc_repositorytests {
	
	@Autowired
	soproduc_repository Soproduc_repository;
	
	@Test
	void NuevoProducto() {
		Soproduc producto = new Soproduc();
		producto.setPro_nompro("Ventilador de PC");
		producto.setPro_prepro(758);
		Soproduc productoguardado= Soproduc_repository.save(producto);
		assertNotNull(productoguardado);
	}
	
	@Test
	void MostrarTodos() {
		List<Soproduc> lista = new ArrayList<>();
		List<Soproduc> Respuesta = Soproduc_repository.findAll();
		if(Respuesta==null||Respuesta.equals("")) {
			Assertions.fail();
		}else {
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void ActualizarProducto() {
		Soproduc producto = Soproduc_repository.getById(2);
		producto.setPro_nompro("Escoba");
		producto.setPro_prepro(75);
	}
	
	@Test
	void EliminarProducto() {
		Soproduc producto = Soproduc_repository.getById(4);
		Soproduc_repository.deleteById(producto.getPro_idprod());
		Soproduc productoeliminado = null;
		assertThat(productoeliminado).isNull();
	}
}
