package actividades.actividadbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="socatego")
public class Socatego {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cat_idcate;

	@Column(name="cat_nombre")
	private String cat_nombre;

	public int getCat_idcate() {
		return cat_idcate;
	}

	public void setCat_idcate(int cat_idcate) {
		this.cat_idcate = cat_idcate;
	}

	public String getCat_nombre() {
		return cat_nombre;
	}

	public void setCat_nombre(String cat_nombre) {
		this.cat_nombre = cat_nombre;
	}


}
