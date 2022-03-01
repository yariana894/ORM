package ActividadConsultas06Relaciones1_N.modeloDAO;

import ActividadConsultas06Relaciones1_N.conexion.Conexion;
import ActividadConsultas06Relaciones1_N.modeloVO.Inmueble;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class InmuebleDao {
    public static Inmueble buscarInmueble(String codInmueble) {

        Conexion conexion = new Conexion();

        Inmueble inmueble = conexion.getConexion().find(Inmueble.class, codInmueble);

        conexion.desconectar();
        return inmueble;
    }

    public static void incrementarPrecioInmueblesZona(String zona, double incremento) {
        Conexion conexion = new Conexion();

        String hql = "update Contrato c set c.precio=c.precio+ :incremento where  c.inmueble.zona.nombre = :zona";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("zona", zona);
        query.setParameter("incremento", incremento);

        try {
            conexion.getConexion().getTransaction().begin();
            query.executeUpdate();
            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            conexion.getConexion().getTransaction().rollback();
        }
        conexion.desconectar();
    }

    public static Iterator iterator(String hql) {
        Conexion con = new Conexion();
        Query query = con.getConexion().createQuery(hql);
        Iterator iterator = query.getResultList().iterator();

        con.desconectar();
        return iterator;
    }

    public static List<Inmueble> listadoInmuebles() {
        Conexion con = new Conexion();
        String hql = "FROM Inmueble i";

        //equivale al PreparedStatement de jdbc
        Query query = con.getConexion().createQuery(hql);
        List<Inmueble> inmuebles = query.getResultList();

        con.desconectar();
        return inmuebles;
    }

    public static List<Inmueble> listadoInmueblesLibresZona(String zona) {
        Conexion conexion = new Conexion();
        //select * from join zonas on zocodigo = incodzona where zonombre = ? and
        //inestado = 0

        String hql = "FROM Inmueble i WHERE i.zona.nombre = :zona AND i.estado= false";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("zona", zona);

        List<Inmueble> inmuebles = query.getResultList();

        conexion.desconectar();

        return inmuebles;
    }

    public static List<Inmueble> listadoInmueblesVigo() {

        Conexion conexion = new Conexion();

        //select * from inmuebles where indireccion like '%Vigo'
        String hql = "from Inmueble i where i.direccion like '%Vigo'";
        Query query = conexion.getConexion().createQuery(hql);
        List<Inmueble> inmuebles = query.getResultList();

        conexion.desconectar();

        return inmuebles;
    }

    public static Iterator contarInmueblesZonas() {
        /* select zocodigo, zonombre, count(incodigo)
         * from zonas join inmuebles on zocodigio = incodigzona
         * group by zocodigo, zonombre*/
        String hql = "select i.zona.codZona, i.zona.nombre, count(i.codInmueble) " +
                " from Inmueble i group by i.zona.codZona, i.zona.nombre";

        return iterator(hql);
    }

    public static Iterator listadoInmueblesZonas(String zona) {
        Conexion conexion = new Conexion();
        //select * from zonas join inmuebles on zocodigo = incodzona where zonombre = ?
        String hql = "FROM Inmueble i join i.zona z where z.nombre = :zona";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("zona", zona);

        Iterator iterator = query.getResultList().iterator();
        return iterator;
    }

    public static Iterator listadoInmueblesFecVencEsteMesYLibreZona(String zona) {
        Conexion conexion = new Conexion();
        String hql = "FROM Inmueble i join Contrato c " +
                " on i.codInmueble = c.inmueble " +
                "WHERE i.estado= false AND i.zona.nombre = :zona or (month(c.fechaVencimiento) = month(CURRENT_DATE()))  ";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("zona", zona);

        Iterator iterator = query.getResultList().iterator();
        return iterator;
    }

    public static Iterator listarInmueblesLibresOFecVenci() {
        Conexion conexion = new Conexion();
        String hql = "select c.inmueble.codInmueble, c.inmueble.direccion, c.fechaVencimiento, " +
                " c.inmueble.propietario.nombre, c.inmueble.propietario.telefono" +
                " from Contrato c " +
                "  WHERE month(c.fechaVencimiento) = month(current_date())";

        Query query = conexion.getConexion().createQuery(hql);

        Iterator iterator = query.getResultList().iterator();

        conexion.desconectar();

        return iterator;
    }

    public static Iterator listDifferentNumInmuebles() {
        Conexion conexion = new Conexion();

        String hql = "SELECT c.inquilino.dni, c.inquilino.nombre, COUNT(DISTINCT c.inmueble.codInmueble) FROM Contrato c" +
                " GROUP BY c.inquilino.dni";
        Query query = conexion.getConexion().createQuery(hql);

        Iterator iterator = query.getResultList().iterator();

        conexion.desconectar();

        return iterator;
    }
}

