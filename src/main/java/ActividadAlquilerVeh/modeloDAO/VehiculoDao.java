package ActividadAlquilerVeh.modeloDAO;


import ActividadAlquilerVeh.conexion.Conexion;
import ActividadAlquilerVeh.modeloVO.Vehiculo;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class VehiculoDao {

    public static Vehiculo buscarVehiculo(String matricula) {
        Conexion conexion = new Conexion();

        Vehiculo vehiculo = conexion.getConexion().find(Vehiculo.class, matricula);

        System.out.println(vehiculo);
        conexion.desconectar();

        return vehiculo;
    }

    public static List<Vehiculo> listadoVehiculoGrupoEFG() {
        Conexion conexion = new Conexion();

        String hql = "FROM Vehiculo v where grupo = 'E' OR grupo ='G' or grupo = 'F' order by grupo";

        Query query = conexion.getConexion().createQuery(hql);

        List<Vehiculo> vehiculos = query.getResultList();

        conexion.desconectar();

        return vehiculos;
    }

    public static Iterator listadoVehiculosTotalDiasEImporte() {
        Conexion conexion = new Conexion();
        //select * from zonas join inmuebles on zocodigo = incodzona where zonombre = ?
        String hql = "select v.vehiculo.matricula, sum(dias), sum(precio) " +
                "FROM VehiculoCliente v group by v.vehiculo.matricula order by v.vehiculo.matricula";

        Query query = conexion.getConexion().createQuery(hql);
        Iterator iterator = query.getResultList().iterator();

        conexion.desconectar();
        return iterator;
    }

    public static Iterator listadoVehiculosNoAlquilados() {
        Conexion conexion = new Conexion();

        String hql = "FROM Vehiculo v left join VehiculoCliente vc on v.matricula = vc.vehiculo" +
                " where vc.idVehiculoCliente is null ";

        Query query = conexion.getConexion().createQuery(hql);
        Iterator iterator = query.getResultList().iterator();

        conexion.desconectar();

        return iterator;
    }

    public static Iterator listadoVehiculoPorTeclado(String matricula) {
        Conexion conexion = new Conexion();

        String hql = "SELECT v.cliente.nombre,v.vehiculo.matricula, v.vehiculo.marca, sum(dias), seguro, sum(precio)" +
                " from VehiculoCliente v where v.vehiculo.matricula = :matricula group by v.cliente.nombre";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("matricula", matricula);

        Iterator iterator = query.getResultList().iterator();
        return iterator;
    }

    public static List<Vehiculo> listadoVehiculosPuertasMaleteroSuperiorCantidadporTeclado(int puertas, int maletero) {
        Conexion conexion = new Conexion();

        String hql = "FROM Vehiculo where puertas >= :puertas and maletero > :maletero";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("puertas", puertas);
        query.setParameter("maletero", maletero);

        List<Vehiculo> vehiculos = query.getResultList();

        conexion.desconectar();

        return vehiculos;
    }

    public static void eliminarVehMatricula(String matricula) {
        Conexion conexion = new Conexion();

        String hql = "delete from Vehiculo i where matricula = :matricula";
        String hql1 = "delete from VehiculoCliente i where i.vehiculo.matricula = :matricula";

        try {
            conexion.getConexion().getTransaction().begin();

            /*EJECUTÉ EN SQL -> set foreign_key_checks=0*/

            Query query1 = conexion.getConexion().createQuery(hql1);
            query1.setParameter("matricula", matricula);
            query1.executeUpdate();

            Query query = conexion.getConexion().createQuery(hql);
            query.setParameter("matricula", matricula);
            query.executeUpdate();

            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            conexion.getConexion().getTransaction().rollback();
            pe.printStackTrace();
        }
        conexion.desconectar();
    }

    public static Iterator listadoVehMarca(String marca) {
        Conexion conexion = new Conexion();

        String hql = "select v.vehiculo.marca, precio FROM VehiculoCliente v " +
                "  where v.vehiculo.marca = :marca";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("marca", marca);

        Iterator iterator = query.getResultList().iterator();
        return iterator;
    }


    public static List<Vehiculo> listadoVehLetrasMatricula(String matricula) {
        Conexion conexion = new Conexion();

        String hql = "FROM Vehiculo where matricula like :matricula";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("matricula", matricula);

        List<Vehiculo> vehiculos = query.getResultList();

        conexion.desconectar();

        return vehiculos;
    }
}
