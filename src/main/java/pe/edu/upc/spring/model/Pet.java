package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Mascota")
public class Pet implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPet; 
	
	@Column(name = "nombreMascota", length = 60, nullable=false)
	private String namePet;

	@Temporal(TemporalType.DATE)
	@Column(name="fechaNacimiento")
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date birthDatePet;
	
	@ManyToOne
	@JoinColumn(name="idPropietario", nullable=false)
	private Dueno dueno;
	
	@ManyToOne
	@JoinColumn(name="idRace", nullable=false)
	private Race race;

	public Pet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pet(int idPet, String namePet, Date birthDatePet, Dueno dueno, Race race) {
		super();
		this.idPet = idPet;
		this.namePet = namePet;
		this.birthDatePet = birthDatePet;
		this.dueno = dueno;
		this.race = race;
	}

	public int getIdPet() {
		return idPet;
	}

	public void setIdPet(int idPet) {
		this.idPet = idPet;
	}

	public String getNamePet() {
		return namePet;
	}

	public void setNamePet(String namePet) {
		this.namePet = namePet;
	}

	public Date getBirthDatePet() {
		return birthDatePet;
	}

	public void setBirthDatePet(Date birthDatePet) {
		this.birthDatePet = birthDatePet;
	}

	public Dueno getDueno() {
		return dueno;
	}

	public void setDueno(Dueno dueno) {
		this.dueno = dueno;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}
	
	
}
