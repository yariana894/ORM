package ActividadAlquilerVeh.modeloVO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Oficinas")
public class Oficina {

    @Id
    @Column(name = "ofCodigo", nullable = false)
    private Integer idOficina;

    @Column(name = "ofDireccion", length = 50, nullable = false)
    private String direccion;

    @Column(name = "ofCiudad", length = 20, nullable = false)
    private String ciudad;

    @Column(name = "ofCodigoPostal", length = 5, nullable = false)
    private Integer codigoPostal;

    @Column(name = "ofProvincia", length = 20, nullable = false)
    private String provincia;

    @Column(name = "ofTelefono", length = 9, nullable = false)
    private Integer telefono;

    @OneToMany(mappedBy = "oficina", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vehiculo> vehiculos = new ArrayList<>();

    public Oficina() {
        super();
    }

    public Oficina(Integer idOficina, String direccion, String ciudad, Integer codigoPostal, String provincia, Integer telefono) {
        this.idOficina = idOficina;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.telefono = telefono;
    }

    public Oficina(Integer idOficina, String direccion, String ciudad, Integer codigoPostal, String provincia, Integer telefono, List<Vehiculo> vehiculos) {
        this.idOficina = idOficina;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.provincia = provincia;
        this.telefono = telefono;
        this.vehiculos = vehiculos;
    }

    public Integer getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(Integer idOficina) {
        this.idOficina = idOficina;
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

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }


    @Override
    public String toString() {
        return "Oficina{" +
                "idOficina=" + idOficina +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", codigoPostal=" + codigoPostal +
                ", provincia='" + provincia + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}


