package Ejemplo01ORM.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "Personas", schema = "UD03Hibernate", 
	indexes = {@Index (name = "nbIndice", columnList = "peApellido", unique = true)})

/*
 * @SequenceGenerator( name = "PersonaSeq", sequenceName = "id_Persona",
 * initialValue =1, allocationSize = 10
 * 
 * )
 */

/*
@TableGenerator(
		name = "PersonaTable",
		initialValue = 1,
		pkColumnName = "Entity",
		pkColumnValue = "ID",
		allocationSize = 10,
		table = "Entity_Generator"
		)
*/

public class Persona implements Serializable{
	
	
	
	@Id
	@Column(name = "peIdPersona")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PersonaSeq")
//	@GeneratedValue(strategy = GenerationType.TABLE, generator ="PersonaTable" )
	private Integer idPersona;
	
	@Column(name = "peNombre", length = 15, nullable = false )
	private String nombre;
	
	@Column(name = "peApellido", length = 45, nullable = false, unique = true )
	private String apellido;

	@Column(name = "peSalario", scale = 2)
	private Double salario;
	
	@Column(name = "peFecNacimiento", updatable = false)
	@Temporal(TemporalType.DATE)
	private Calendar fecNacimiento;
	
	@Column(name="peEstadoCivil", nullable = false, length = 12 )
	@Enumerated(value = EnumType.STRING)
	private Estado estadoCivil;
	
	@Column(name = "peFoto")
	@Basic(fetch = FetchType.LAZY)
	@Lob
	private byte[] foto;
	
//	@Transient 
//	private String pro;
	
	public enum Estado{
		Casado,
		Divorciado,
		Soltero,
		Viudo
	}

	
	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}


	


	public Integer getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public Double getSalario() {
		return salario;
	}


	public void setSalario(Double salario) {
		this.salario = salario;
	}


	public Calendar getFecNacimiento() {
		return fecNacimiento;
	}


	public void setFecNacimiento(Calendar fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}


	public Estado getEstado() {
		return estadoCivil;
	}


	public void setEstado(Estado estado) {
		this.estadoCivil = estado;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPersona == null) ? 0 : idPersona.hashCode());
		return result;
	}





	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		if (idPersona == null) {
			if (other.idPersona != null)
				return false;
		} else if (!idPersona.equals(other.idPersona))
			return false;
		return true;
	}





	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", salario="
				+ salario + ", fecNacimiento=" + fecNacimiento + ", estadoCivil=" + estadoCivil + "]";
	}


	
	
	
	

}
