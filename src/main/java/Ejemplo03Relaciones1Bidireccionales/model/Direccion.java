package Ejemplo03Relaciones1Bidireccionales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Direcciones") /*
								 * esto hay que ponerlo cuando la tabla se llame de diferente manera la tabla en
								 * sql
								 */
public class Direccion implements Serializable {
	@Id
	@Column(name = "diId")
	private int id;

	@Column(name = "diDireccion", nullable = false, length = 40)
	private String direccion;

	@Column(name = "diCiudad", nullable = false, length = 40)
	private String ciudad;

	@Column(name = "diProvincia", nullable = false, length = 40)
	private String provincia;

	@OneToOne(mappedBy = "dire") /* tengo que darle el nombre que le di en la clase empleado */
	/* private Direccion "direccion" */
	private Empleado empleado;

	public Direccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Direccion(int id, String direccion, String ciudad, String provincia) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.provincia = provincia;
	}

	public Direccion(int id, String direccion, String ciudad, String provincia, Empleado empleado) {
		super();
		this.id = id;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.empleado = empleado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", direccion=" + direccion + ", ciudad=" + ciudad + ", provincia=" + provincia
				+ ", empleado=" + empleado + "]";
	}

}
