package ActividadRelaciones1_N.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Futbolistas")
public class Futbolista implements Serializable {

    @Id
    @Column(name = "fuNumFicha", nullable = false)
    private int numFicha;

    @Column(name = "fuNombre", length = 15, nullable = false)
    private String nombre;

    @Column(name = "fuApellidos", length = 30, nullable = false)
    private String apellidos;

    @Column(name = "fuFecNacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecNacimiento;

    @Column(name = "fuPeso", nullable = false)
    private double peso;

    @Column(name = "fuAltura", nullable = false)
    private int altura;

    @Column(name = "fuSueldo", nullable = false)
    private int sueldo;

    @OneToMany(mappedBy = "fut")
    private List<Contrato> contratos;

    public Futbolista() {
        super();
    }

    public Futbolista(int numFicha, String nombre, String apellidos, Date fecNacimiento, double peso, int altura, int sueldo) {
        this.numFicha = numFicha;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecNacimiento = fecNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.sueldo = sueldo;
    }

    public Futbolista(int numFicha, String nombre, String apellidos, Date fecNacimiento, double peso, int altura, int sueldo, List<Contrato> contratos) {
        this.numFicha = numFicha;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecNacimiento = fecNacimiento;
        this.peso = peso;
        this.altura = altura;
        this.sueldo = sueldo;
        this.contratos = contratos;
    }

    public int getNumFicha() {
        return numFicha;
    }

    public void setNumFicha(int numFicha) {
        this.numFicha = numFicha;
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

    public Date getFecNacimiento() {
        return fecNacimiento;
    }

    public void setFecNacimiento(Date fecNacimiento) {
        this.fecNacimiento = fecNacimiento;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Futbolista that = (Futbolista) o;
        return numFicha == that.numFicha;
    }

    @Override
    public String toString() {
        return "Futbolista{" +
                "numFicha=" + numFicha +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", fecNacimiento=" + fecNacimiento +
                ", peso=" + peso +
                ", altura=" + altura +
                ", sueldo=" + sueldo +
                '}';
    }
}
