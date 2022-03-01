package ActividadAlquilerVeh.modeloVO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "VehiculosClientes")
public class VehiculoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vcId")
    private Integer idVehiculoCliente;

    @Column(name = "vcDias", nullable = false)
    private Integer dias;

    @Column(name = "vcSeguro", length = 20, nullable = false)
    private String seguro;

    @Column(name = "vcPrecio", nullable = false)
    private Integer precio;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vcDNI")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vcMatricula")
    private Vehiculo vehiculo;

    public VehiculoCliente() {
        super();
    }

    public VehiculoCliente(Integer idVehiculoCliente, Integer dias, String seguro, Integer precio) {
        this.idVehiculoCliente = idVehiculoCliente;
        this.dias = dias;
        this.seguro = seguro;
        this.precio = precio;
    }

    public VehiculoCliente(Integer dias, String seguro, Integer precio, Cliente cliente, Vehiculo vehiculo) {
        this.dias = dias;
        this.seguro = seguro;
        this.precio = precio;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
    }

    public VehiculoCliente(Integer idVehiculoCliente, Integer dias, String seguro, Integer precio, Cliente cliente, Vehiculo vehiculo) {
        this.idVehiculoCliente = idVehiculoCliente;
        this.dias = dias;
        this.seguro = seguro;
        this.precio = precio;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
    }

    public Integer getIdVehiculoCliente() {
        return idVehiculoCliente;
    }

    public void setIdVehiculoCliente(Integer idVehiculoCliente) {
        this.idVehiculoCliente = idVehiculoCliente;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getSeguro() {
        return seguro;
    }

    public void setSeguro(String seguro) {
        this.seguro = seguro;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehiculoCliente that = (VehiculoCliente) o;
        return Objects.equals(idVehiculoCliente, that.idVehiculoCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVehiculoCliente);
    }


}