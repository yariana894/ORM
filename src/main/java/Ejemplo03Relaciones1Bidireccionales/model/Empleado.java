package Ejemplo03Relaciones1Bidireccionales.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Empleados")
public class Empleado implements Serializable {

	@Id
	@Column(name = "emCodigo")
	private Integer codigo;

	@Column(name = "emNombre", nullable = false, length = 40)
	private String nombre;

	@Column(name = "emApellidos", nullable = false, length = 40)
	private String apellidos;

	@Column(name = "emSueldo", nullable = false)
	private Integer sueldo;

	@OneToOne  (cascade = CascadeType.ALL, fetch = FetchType.LAZY) /* porque es una relaci�n de uno a uno */
	@JoinColumn(name = "emIdDireccion")
	private Direccion dire;

	public Empleado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Empleado(Integer codigo, String nombre, String apellidos, Integer sueldo, Direccion direccion) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.sueldo = sueldo;
		this.dire = direccion;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getSueldo() {
		return sueldo;
	}

	public void setSueldo(Integer sueldo) {
		this.sueldo = sueldo;
	}

	public Direccion getDireccion() {
		return dire;
	}

	public void setDireccion(Direccion direccion) {
		this.dire = direccion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Empleado [codigo=" + codigo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", sueldo=" + sueldo
				+ ", dire=" + dire + "]";
	}
	
	

}
