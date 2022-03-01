package ActividadConsultas06Relaciones1_N.modeloDAO;

import ActividadConsultas06Relaciones1_N.conexion.Conexion;
import ActividadConsultas06Relaciones1_N.modeloVO.Inquilino;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class InquilinoDao {
    public static Inquilino buscarInquilinoDni(String dni) {
        Conexion conexion = new Conexion();

        Inquilino inquilino = conexion.getConexion().find(Inquilino.class, dni);

        conexion.desconectar();
        return inquilino;
    }

    public static List<Inquilino> buscarCiudadSalario(double suelMenor, double suelMayor) {
        Conexion conexion = new Conexion();

        String hql = "FROM Inquilino i WHERE i.salario BETWEEN :salMenor and :salMayor";

        Query query = conexion.getConexion().createQuery(hql);
        query.setParameter("salMenor", suelMenor);
        query.setParameter("salMayor", suelMayor);

        List<Inquilino> inquilinos = query.getResultList();

        conexion.desconectar();
        return inquilinos;
    }

    public static Inquilino buscarInquilino(String s) {
        Conexion con = new Conexion();
        String hql = "FROM Inquilino i where i.dni = '12345678G'";

        Query query = con.getConexion().createQuery(hql);

        //se puede hacer de dos maneras
        Inquilino inquilino = (Inquilino) query.getSingleResult();

        //esto solo devuelve una fila
        //Inquilino inquilino1 = (Inquilino) con.getConexion().createQuery(hql).getSingleResult();

        con.desconectar();
        return inquilino;
    }

    public static List<Inquilino> listadoInquilinosObjetos() {
        Conexion conexion = new Conexion();
        //me crea un objeto con las columnas que yo quiero
        //tengo que tener el constructor de dni y nombre sino me da error
        String hql = "SELECT new Inquilino(i.dni, i.nombre) FROM Inquilino i ORDER BY i.nombre";

        Query query = conexion.getConexion().createQuery(hql);
        List<Inquilino> inquilinos = query.getResultList();

        conexion.desconectar();

        return inquilinos;
    }

    public static List<Object> listadoCreandoLista() {
        Conexion conexion = new Conexion();

        String hql = "SELECT new list(i.dni, i.nombre, i.salario) FROM Inquilino i";
        Query query = conexion.getConexion().createQuery(hql);
        List<Object> lista = query.getResultList();
        conexion.desconectar();

        return lista;
    }

    /*se puede hacer de otra forma*/
    public static Iterator listarNumeroInmueDeInquilino() {
        Conexion conexion = new Conexion();

        String hql = "select c.inquilino.nombre, count(c.inmueble.codInmueble) as cuenta from Contrato c" +
                " join c.inquilino join c.inmueble" +
                " group by c.inquilino.nombre order by cuenta desc";
        Query query = conexion.getConexion().createQuery(hql);

        Iterator iterator = query.getResultList().iterator();

        conexion.desconectar();
        return iterator;
    }

    public static void eliminarInquilinoSinContrato() {
        Conexion conexion = new Conexion();

        String hql = "delete from Inquilino  i where i.dni not in(select c.inquilino.dni from ContratoR c)";

        try {
            conexion.getConexion().getTransaction().begin();
            Query query = conexion.getConexion().createQuery(hql);
            query.executeUpdate();

            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            conexion.getConexion().getTransaction().rollback();
        }
        conexion.desconectar();
    }

    public static Iterator separarNombres() {
        Conexion conexion = new Conexion();

        String hql = "select substring(i.nombre, i, LOCATE(' ', i.nombre))," +
                " substring(i.nombre, locate(' ', i.nombre),locate(',', i.nombre)- locate(' ', i.nombre))" +
                ", substring(i.nombre, locate(',', i.nombre)+1,length(i.nombre))," +
                " substring(i.nombre, 1, locate(i.nombre, ','))" +
                " from Inquilino i";

        Query query = conexion.getConexion().createQuery(hql);

        Iterator iterator = query.getResultList().iterator();

        conexion.desconectar();
        return iterator;
    }

    public static List<Inquilino> listadoInquilinosSinContrato() {

        Conexion conexion = new Conexion();

        String hql = "FROM Inquilino i where  i.dni not in (select c.inquilino.dni from ContratoR c)";

        Query query = conexion.getConexion().createQuery(hql);

        List<Inquilino> inquilinos = query.getResultList();

        for (Inquilino inquilino : inquilinos) {
            System.out.println(inquilino);
        }
        conexion.desconectar();
        return inquilinos;
    }
}
