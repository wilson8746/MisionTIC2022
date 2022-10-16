package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Propietario")
public class Dueno implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPropietario; 
	
	@Column(name = "nombrePropietario", length = 60, nullable=false)
	private String nameDueno;

	public Dueno() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dueno(int idPropietario, String nameDueno) {
		super();
		this.idPropietario = idPropietario;
		this.nameDueno = nameDueno;
	}

	public int getIdPropietario() {
		return idPropietario;
	}

	public void setIdPropietario(int idPropietario) {
		this.idPropietario = idPropietario;
	}

	public String getNameDueno() {
		return nameDueno;
	}

	public void setNameDueno(String nameDueno) {
		this.nameDueno = nameDueno;
	}

		
}
