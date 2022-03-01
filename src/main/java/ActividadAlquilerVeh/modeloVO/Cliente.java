package ActividadAlquilerVeh.modeloVO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Clientes")
public class Cliente {

    @Id
    @Column(name = "clDni", length = 9, nullable = false)
    private String dni;

    @Column(name = "clNombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "clDireccion", length = 50, nullable = false)
    private String direccion;

    @Column(name = "clCiudad", length = 20, nullable = false)
    private String ciudad;

    @Column(name = "clCodigoPostal", length = 5, nullable = false)
    private Integer codigoPostal;

    @Column(name = "clProvincia", length = 20, nullable = false)
    private String provincia;

    @Column(name = "clTelefono", length = 9, nullable = false)
    private Integer telefono;

    @Column(name = "clNumTarjeta", length = 16, nullable = false)
    private String numTarjeta;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<VehiculoCliente> vehiculoClientes = new ArrayList<>();


    public Cliente() {
        super();
    }

    public Cliente(String dni, String nombre, String direccion, String ciudad, Integer codigoPostal, String provincia, Integer telefono, String numTarjeta) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.telefono = telefono;
        this.numTarjeta = numTarjeta;
    }

    public Cliente(String dni, String nombre, String direccion, String ciudad, Integer codigoPostal, String provincia, Integer telefono, String numTarjeta, List<VehiculoCliente> vehiculoClientes) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.telefono = telefono;
        this.numTarjeta = numTarjeta;
        this.vehiculoClientes = vehiculoClientes;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }

    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
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
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", codigoPostal=" + codigoPostal +
                ", provincia='" + provincia + '\'' +
                ", telefono=" + telefono +
                ", numTarjeta='" + numTarjeta + '\'' +
                ", vehiculoClientes=" + vehiculoClientes +
                '}';
    }
}

