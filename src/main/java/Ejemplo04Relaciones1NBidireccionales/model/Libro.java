package Ejemplo04Relaciones1NBidireccionales.model;


import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Libros")
public class Libro implements Serializable {

	@Id
	@Column(name = "liCodigo")
	private int codigo;

	@Column(name = "liTitulo")
	private String titulo;

	@ManyToOne(fetch = FetchType.LAZY) // porque el codigo del autor puede estar muchas veces en la tabla de libros un
	// autor hace muchos libros
	@JoinColumn(name = "liCodAutor")
	private Autor aut;

	public Libro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Libro(int codigo, String titulo, Autor aut) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.aut = aut;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Autor getAut() {
		return aut;
	}

	public void setAut(Autor aut) {
		this.aut = aut;
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
		Libro other = (Libro) obj;
		return codigo == other.codigo;
	}

	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", titulo=" + titulo + ", aut=" + aut + "]";
	}
}
