package ActividadAlquilerVeh.modeloDAO;

import ActividadAlquilerVeh.conexion.Conexion;
import ActividadAlquilerVeh.modeloVO.Cliente;

import javax.persistence.Query;
import java.util.List;

public class ClienteDao {

    public static Cliente buscarCliente(String dni) {
        Conexion conexion = new Conexion();

        Cliente cliente = conexion.getConexion().find(Cliente.class, dni);
        System.out.println(cliente);

        conexion.desconectar();

        return cliente;
    }


    public static List<Cliente> listadoNombreClienteEmpieceLetraPorTeclado(String letra) {
        Conexion conexion = new Conexion();
        String hql = "from Cliente where substring(nombre,1, 1) like :letra";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("letra", letra);

        List<Cliente> clientes = query.getResultList();

        conexion.desconectar();
        return clientes;
    }


}
