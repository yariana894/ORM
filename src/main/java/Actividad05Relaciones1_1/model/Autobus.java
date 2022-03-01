package Actividad05Relaciones1_1.model;

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
@Table(name = "Autobuses")

public class Autobus implements Serializable {

	@Id
	@Column(name = "auMatricula", length = 11, nullable = false)
	private String matricula;

	@Column(name = "auMarca", length = 20, nullable = false)
	private String marca;

	@Column(name = "auModelo", length = 20, nullable = false)
	private String modelo;

	@Column(name = "auNumPlazas", nullable = false)
	private int numPlazas;

	public Autobus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Autobus(String matricula, String marca, String modelo, int numPlazas) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.numPlazas = numPlazas;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autobus other = (Autobus) obj;
		return Objects.equals(matricula, other.matricula);
	}

	@Override
	public String toString() {
		return "Autobus [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", numPlazas="
				+ numPlazas + "]";
	}

}
