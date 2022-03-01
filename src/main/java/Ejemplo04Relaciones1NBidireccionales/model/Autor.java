package Ejemplo04Relaciones1NBidireccionales.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Autores")
public class Autor implements Serializable {

	@Id
	@Column(name = "auCodigo")
	private int codigo;

	@Column(name = "auNombre", length = 45, nullable = false)
	private String nombre;

	@Column(name = "auNacionalidad", length = 25, nullable = false)
	private String nacionalidad;

	@OneToMany(mappedBy = "aut", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	// el mappedby es obligatorio, objeto de la otra clase con la que est�
	// relacionado
	// es el nombre que le puse al atributo de autor en Libro.java
	private List<Libro> libros;

	public Autor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Autor(int codigo, String nombre, String nacionalidad, List<Libro> libros) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.libros = libros;
	}
	
	public Autor(int codigo, String nombre, String nacionalidad) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return codigo == other.codigo;
	}

	@Override
	public String toString() {
		return "Autor [codigo=" + codigo + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}

}
