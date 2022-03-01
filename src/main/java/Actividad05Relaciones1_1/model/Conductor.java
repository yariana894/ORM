package Actividad05Relaciones1_1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Conductores")
public class Conductor implements Serializable {

    @Id
    @Column(name = "coDni", length = 9, nullable = false)
    private String dni;

    @Column(name = "coNombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "coFecNacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecNacimiento;

    @Column(name = "coNumCarnet", length = 9, nullable = false)
    private String numCarnet;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "coMatricula")
    private Autobus autobus;

    public Conductor() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Conductor(String dni, String nombre, Date fecNacimiento, String numCarnet, Autobus autobus) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.fecNacimiento = fecNacimiento;
        this.numCarnet = numCarnet;
        this.autobus = autobus;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public String getNumCarnet() {
        return numCarnet;
    }

    public void setNumCarnet(String numCarnet) {
        this.numCarnet = numCarnet;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(autobus, dni, fecNacimiento, nombre, numCarnet);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Conductor other = (Conductor) obj;
        return Objects.equals(autobus, other.autobus) && Objects.equals(dni, other.dni)
                && Objects.equals(fecNacimiento, other.fecNacimiento) && Objects.equals(nombre, other.nombre)
                && Objects.equals(numCarnet, other.numCarnet);
    }

    @Override
    public String toString() {
        return "Conductor [dni=" + dni + ", nombre=" + nombre + ", fecNacimiento=" + fecNacimiento + ", numCarnet="
                + numCarnet + ", autobus=" + autobus + "]";
    }

}
