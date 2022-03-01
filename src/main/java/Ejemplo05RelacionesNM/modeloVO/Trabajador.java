package Ejemplo05RelacionesNM.modeloVO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="Trabajadores")
public class Trabajador {
	@Column(name = "trCodigo")
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int codigo;

	@Column(name = "trNombre", nullable = false, length = 50)
	private String nombre;

	@Column(name = "trSalario", nullable = false)
	private int salario;
	
	// atributo que contendr� todos los proyectos de un empleado
	@ManyToMany(targetEntity=Proyecto.class)
	@JoinTable(name = "trabajadoresproyectos",
	joinColumns = @JoinColumn(name="tpCodTrabajador"),
	inverseJoinColumns = @JoinColumn(name = "tpCodProyecto"))
	private List<Proyecto> proyectos = new ArrayList<>();

	public Trabajador() {

	}

	public Trabajador(int codigo, String nombre, int salario) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.salario = salario;
	}


	public Trabajador(String nombre, int salario) {
		super();
		this.nombre = nombre;
		this.salario = salario;
	}

	public Trabajador(String nombre, int salario, List<Proyecto> proyectos) {
		super();
		this.nombre = nombre;
		this.salario = salario;
		this.proyectos = proyectos;
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

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
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
		Trabajador other = (Trabajador) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trabajador [codigo=" + codigo + ", nombre=" + nombre + ", salario=" + salario + "]";
	}

}
