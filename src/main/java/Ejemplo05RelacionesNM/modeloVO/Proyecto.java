package Ejemplo05RelacionesNM.modeloVO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "Proyectos")
public class Proyecto {
	@Id
	@Column(name = "prCodigo")
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int codigo;

	@Column (name = "prNombre", nullable = false, length = 20)
	private String nombre;

	//como un proyecto lo realizan varios empleados
	@ManyToMany(mappedBy="proyectos")
	// no le tenemos que indicar en que columna 
	private List <Trabajador> trabajadores = new ArrayList<>();

	public Proyecto() {

	}
	
	

	public Proyecto(int codigo, String nombre) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
	}



	public Proyecto(int codigo, String nombre, List<Trabajador> trabajadores) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.trabajadores = trabajadores;
	}


	public Proyecto(String nombre) {
		super();
		this.nombre = nombre;
	}

	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public List<Trabajador> getTrabajadores() {
		return trabajadores;
	}

	public void setTrabajadores(List<Trabajador> trabajadores) {
		this.trabajadores = trabajadores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
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
		Proyecto other = (Proyecto) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Proyecto [codigo=" + codigo + ", nombre=" + nombre + "]";
	}
}
