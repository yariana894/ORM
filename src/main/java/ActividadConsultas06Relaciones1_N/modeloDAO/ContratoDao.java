package ActividadConsultas06Relaciones1_N.modeloDAO;

import ActividadConsultas06Relaciones1_N.conexion.Conexion;
import ActividadConsultas06Relaciones1_N.modeloVO.Contrato;
import ActividadConsultas06Relaciones1_N.modeloVO.Inmueble;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class ContratoDao {
    public static Contrato buscarContrato(String numContrato) {
        Conexion conexion = new Conexion();

        Contrato contrato = conexion.getConexion().find(Contrato.class, numContrato);

        conexion.desconectar();
        return contrato;
    }

    public static Contrato actualizarContrato(Contrato contrato) {
        Conexion conexion = new Conexion();

        try {
            conexion.getConexion().getTransaction().begin();
            conexion.getConexion().merge(contrato);
            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            conexion.getConexion().getTransaction().rollback();
            pe.printStackTrace();
        }
        conexion.desconectar();
        return contrato;
    }

    public static void nuevoContrato(Contrato contrato) {
        Inmueble inmueble = InmuebleDao.buscarInmueble(contrato.getInmueble().getCodInmueble());
        Conexion conexion = new Conexion();

        try {
            conexion.getConexion().getTransaction().begin();
            conexion.getConexion().persist(contrato);

            inmueble.setEstado(true);
            conexion.getConexion().merge(inmueble);
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

    public static Iterator listarPrecios() {
        String hql = "SELECT  c.codContrato, c.precio, c.precio*0.21, c.precio*1.21 FROM Contrato c ";
        return iterator(hql);
    }

    public static Iterator listadoContratosPropietarios() {
        String hql = "SELECT c.codContrato, c.fechaVencimiento, c.inquilino.nombre," +
                "c.inmueble.direccion, c.inmueble.zona.nombre, c.inmueble.propietario.nombre" +
                " from Contrato c";
        //como me devuelve un arraylist tengo que ponerle el .iterator
        //Iterator iterator = query.getResultList().iterator();

        return iterator(hql);
    }

    public static Iterator listadoContratosPreciosMayorMenor() {
        String hql = "select max(c.precio), min (c.precio), sum(c.precio), avg(c.precio), count(*) from Contrato c";
        return iterator(hql);
    }

    public static List<Contrato> listadoContratosPrecioMayorMedia() {
        //select * from contratos where coPrecio > (select avg(coPrecio)from contratos)
        Conexion conexion = new Conexion();

        String hql = " from Contrato where precio > (select avg (precio) from Contrato )";

        Query query = conexion.getConexion().createQuery(hql);
        List<Contrato> contratos = query.getResultList();

        conexion.desconectar();

        return contratos;
    }

    public static Iterator listarInquilinoPrecioMax() {
        Conexion conexion = new Conexion();

        String hql = " select c.precio, c.inquilino.nombre FROM Contrato c join c.inquilino" +
                " where c.precio >= (select max(precio) from Contrato) group by c.inquilino.nombre";

        Query query = conexion.getConexion().createQuery(hql);

        Iterator iterator = query.getResultList().iterator();

        conexion.desconectar();
        return iterator;
    }

    public static List<Contrato> crearContratosVencen() {
        Conexion conexion = new Conexion();

        String hql = "FROM Contrato c where year(c.fechaVencimiento)=year(current_date())";

        Query query = conexion.getConexion().createQuery(hql);
        List<Contrato> contratos = query.getResultList();

        conexion.desconectar();

        return contratos;
    }

    /*otra forma de hacerlo en el ejercicio*/
    public static List<Contrato> listadoContratosMasCaros() {
        Conexion conexion = new Conexion();

        String hql = "FROM Contrato c where c.precio = (select max (c.precio) from Contrato c)";

        Query query = conexion.getConexion().createQuery(hql);
        List<Contrato> contratos = query.getResultList();

        conexion.desconectar();
        return contratos;
    }

    public static Iterator listadoRecaudadoContratos() {
        Conexion conexion = new Conexion();

        String hql = "select c.codContrato, " +
                "truncate(datediff(c.fechaVencimiento, c.fechaContrato)/30, 0) * c.precio " +
                "from Contrato c";

        Query query = conexion.getConexion().createQuery(hql);

        Iterator iterator = query.getResultList().iterator();

        conexion.desconectar();
        return iterator;
    }
}
