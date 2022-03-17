package ActividadRelaciones1_N.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Equipos")
public class Equipo implements Serializable {

    @Id
    @Column(name = "eqNombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "eqPresidente", length = 50, nullable = false)
    private String presidente;

    @Column(name = "eqEstadio", length = 50, nullable = false)
    private String estadio;

    @Column(name = "eqAnhoFundacion", nullable = false)
    private int anhoFundacion;

    @Column(name = "eqNumSocios", nullable = false)
    private int numSocios;

    @OneToMany(mappedBy = "eq")
    private List<Contrato> contratos;

    public Equipo() {
        super();
    }

    public Equipo(String nombre, String presidente, String estadio, int anhoFundacion, int numSocios, List<Contrato> contratos) {
        this.nombre = nombre;
        this.presidente = presidente;
        this.estadio = estadio;
        this.anhoFundacion = anhoFundacion;
        this.numSocios = numSocios;
        this.contratos = contratos;
    }

    public Equipo(String nombre, String presidente, String estadio, int anhoFundacion, int numSocios) {
        this.nombre = nombre;
        this.presidente = presidente;
        this.estadio = estadio;
        this.anhoFundacion = anhoFundacion;
        this.numSocios = numSocios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipo equipo = (Equipo) o;
        return nombre == equipo.nombre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public int getAnhoFundacion() {
        return anhoFundacion;
    }

    public void setAnhoFundacion(int anhoFundacion) {
        this.anhoFundacion = anhoFundacion;
    }

    public int getNumSocios() {
        return numSocios;
    }

    public void setNumSocios(int numSocios) {
        this.numSocios = numSocios;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre=" + nombre +
                ", presidente=" + presidente +
                ", estadio=" + estadio +
                ", anhoFundacion=" + anhoFundacion +
                ", numSocios=" + numSocios +
                '}';
    }
}
