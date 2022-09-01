package actividades.actividadbackend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="soproduc")
public class Soproduc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pro_idprod;
	
	@Column(name="pro_nompro")
	private String pro_nompro;
	
	@Column(name="pro_prepro")
	private int pro_prepro;

	public int getPro_idprod() {
		return pro_idprod;
	}

	public void setPro_idprod(int pro_idprod) {
		this.pro_idprod = pro_idprod;
	}

	public String getPro_nompro() {
		return pro_nompro;
	}

	public void setPro_nompro(String pro_nompro) {
		this.pro_nompro = pro_nompro;
	}

	public int getPro_prepro() {
		return pro_prepro;
	}

	public void setPro_prepro(int pro_prepro) {
		this.pro_prepro = pro_prepro;
	}

}
