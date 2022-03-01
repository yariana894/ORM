package ActividadAlquilerVeh.modeloVO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Vehiculos")
public class Vehiculo {

    @Id
    @Column(name = "veMatricula", length = 10, nullable = false)
    private String matricula;

    @Column(name = "veMarca", length = 20, nullable = false)
    private String marca;

    @Column(name = "veGrupo", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Grupo grupo;

    @Column(name = "vePlazas", nullable = false)
    private Integer plazas;

    @Column(name = "vePuertas", nullable = false)
    private Integer puertas;

    @Column(name = "veMaletero", nullable = false)
    private Integer maletero;

    @Column(name = "veEdad", nullable = false)
    private Integer edad;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "veCodOficina")
    private Oficina oficina;

    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.EAGER)
    List<VehiculoCliente> vehiculoClientes = new ArrayList<>();

    public enum Grupo {
        A, B, C, D, E, F, G
    }

    public Vehiculo() {
        super();
    }

    public Vehiculo(String matricula, String marca, Grupo grupo, Integer plazas, Integer puertas, Integer maletero, Integer edad) {
        this.matricula = matricula;
        this.marca = marca;
        this.grupo = grupo;
        this.plazas = plazas;
        this.puertas = puertas;
        this.maletero = maletero;
        this.edad = edad;
    }

    public Vehiculo(String matricula, String marca, Grupo grupo, Integer plazas, Integer puertas, Integer maletero, Integer edad, Oficina oficina) {
        this.matricula = matricula;
        this.marca = marca;
        this.grupo = grupo;
        this.plazas = plazas;
        this.puertas = puertas;
        this.maletero = maletero;
        this.edad = edad;
        this.oficina = oficina;
    }

    public Vehiculo(String matricula, String marca, Grupo grupo, Integer plazas, Integer puertas, Integer maletero, Integer edad, Oficina oficina, List<VehiculoCliente> vehiculoClientes) {
        this.matricula = matricula;
        this.marca = marca;
        this.grupo = grupo;
        this.plazas = plazas;
        this.puertas = puertas;
        this.maletero = maletero;
        this.edad = edad;
        this.oficina = oficina;
        this.vehiculoClientes = vehiculoClientes;
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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public Integer getPlazas() {
        return plazas;
    }

    public void setPlazas(Integer plazas) {
        this.plazas = plazas;
    }

    public Integer getPuertas() {
        return puertas;
    }

    public void setPuertas(Integer puertas) {
        this.puertas = puertas;
    }

    public Integer getMaletero() {
        return maletero;
    }

    public void setMaletero(Integer maletero) {
        this.maletero = maletero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public List<VehiculoCliente> getVehiculoClientes() {
        return vehiculoClientes;
    }

    public void setVehiculoClientes(List<VehiculoCliente> vehiculoClientes) {
        this.vehiculoClientes = vehiculoClientes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", grupo=" + grupo +
                ", plazas=" + plazas +
                ", puertas=" + puertas +
                ", maletero=" + maletero +
                ", edad=" + edad +
                ", oficina=" + oficina +
                ", vehiculoClientes=" + vehiculoClientes +
                '}';
    }
}

