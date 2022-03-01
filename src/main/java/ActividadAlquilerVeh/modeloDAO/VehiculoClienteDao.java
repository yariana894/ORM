package ActividadAlquilerVeh.modeloDAO;

import ActividadAlquilerVeh.conexion.Conexion;
import ActividadAlquilerVeh.modeloVO.VehiculoCliente;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class VehiculoClienteDao {

    public static VehiculoCliente buscarVehiculoCliente(int codigo) {
        Conexion conexion = new Conexion();

        VehiculoCliente vehiculo = conexion.getConexion().find(VehiculoCliente.class, codigo);

        conexion.desconectar();

        return vehiculo;
    }

    public static Iterator listadoVehiculosAlquiladosCliente() {
        Conexion conexion = new Conexion();
        String hql = "select v.vehiculo.matricula, v.cliente.dni, v.cliente.nombre," +
                " v.cliente.telefono, v.dias, v.precio" +
                " FROM VehiculoCliente v order by v.cliente.nombre, v.vehiculo.matricula";

        Query query = conexion.getConexion().createQuery(hql);
        Iterator iterator = query.getResultList().iterator();

        conexion.desconectar();

        return iterator;
    }

    public static List<VehiculoCliente> listadoVehiculosPrecioMasCaro() {
        Conexion conexion = new Conexion();
        String hql = "from VehiculoCliente where precio >=(select max(precio) from VehiculoCliente )";

        Query query = conexion.getConexion().createQuery(hql);

        List<VehiculoCliente> vehiculoClientes = query.getResultList();

        conexion.desconectar();
        return vehiculoClientes;
    }

    public static void actualizarAlquileres(String marca, int incremento) {
        Conexion conexion = new Conexion();

        String hql = "update VehiculoCliente v set v.precio = v.precio + :incremento " +
                " where v.vehiculo.marca = :marca";

        try {
            conexion.getConexion().getTransaction().begin();
            Query query = conexion.getConexion().createQuery(hql);
            query.setParameter("marca", marca);
            query.setParameter("incremento", incremento);
            query.executeUpdate();

            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            conexion.getConexion().getTransaction().rollback();
            pe.printStackTrace();
        }
        conexion.desconectar();
    }

    public static void nuevoAlquiler(VehiculoCliente vehiculoCliente) {
        Conexion conexion = new Conexion();

        try {
            conexion.getConexion().getTransaction().begin();

            conexion.getConexion().persist(vehiculoCliente);

            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            conexion.getConexion().getTransaction().rollback();
            pe.printStackTrace();
        }
        conexion.desconectar();
    }
}
