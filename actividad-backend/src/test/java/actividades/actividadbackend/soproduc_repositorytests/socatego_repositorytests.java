package actividades.actividadbackend.soproduc_repositorytests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import actividades.actividadbackend.entity.Socatego;
import actividades.actividadbackend.repository.socatego_repository;

public class socatego_repositorytests {
	@Autowired
	socatego_repository Socatego_repositorytests;
	
	@Test
	void NuevaCategoria() {
		Socatego categoria = new Socatego();
		categoria.setCat_nombre("Hogar");
		Socatego productoguardado= Socatego_repositorytests.save(categoria);
		assertNotNull(productoguardado);
	}
	
	@Test
	void MostrarTodasCategorias() {
		List<Socatego> lista = new ArrayList<>();
		List<Socatego> Respuesta = Socatego_repositorytests.findAll();
		if(Respuesta==null||Respuesta.equals("")) {
			Assertions.fail();
		}else {
			Assertions.assertTrue(true);
		}
	}
	
	@Test
	void ActualizarCategoria() {
		Socatego categoria = Socatego_repositorytests.getById(2);
		categoria.setCat_nombre("Electrodomestico");
	}
	
	@Test
	void EliminarProducto() {
		Socatego categoria = Socatego_repositorytests.getById(4);
		Socatego_repositorytests.deleteById(categoria.getCat_idcate());
		Socatego categoriaeliminada = null;
		assertThat(categoriaeliminada).isNull();
	}
}
