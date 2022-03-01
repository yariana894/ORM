package ActividadConsultas06Relaciones1_N.modeloDAO;

import ActividadConsultas06Relaciones1_N.conexion.Conexion;
import ActividadConsultas06Relaciones1_N.modeloVO.Propietario;

import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class PropietarioDao {
    public static Propietario buscarPropietario(String buscar) {
        Conexion conexion = new Conexion();

        //sin utilizar parámetros
        String hql = "FROM Propietario p WHERE p.dni = '" + buscar + "'";

        //con parámetros
        String hql1 = "FROM Propietario p WHERE p.dni = :dni";

        Query query = conexion.getConexion().createQuery(hql1);

        query.setParameter("dni", buscar);

        Propietario propietario = (Propietario) query.getSingleResult();

        conexion.desconectar();

        return propietario;
    }

    public static Iterator iterator(String hql) {
        Conexion con = new Conexion();
        Query query = con.getConexion().createQuery(hql);
        Iterator iterator = query.getResultList().iterator();

        con.desconectar();
        return iterator;
    }

    public static Iterator listarPropietarios() {
        String hql = "SELECT  p.dni, p.nombre, p.apellidos FROM Propietario p ";
        return iterator(hql);
    }

    public static Iterator listadoPropietariosInmueblesJoin01() {
        //select * from propietarios join inmuebles on prdni = indniPropietario
        String hql = " FROM Propietario pro join pro.inmuebles ";
        return iterator(hql);
    }

    public static Iterator listadoPropietariosSinInmuebles() {
        String hql = "from Inmueble  i right join  i.propietario where i.codInmueble is null";
        return iterator(hql);
    }

    public static List<Propietario> listadoPropietariosInmuebles() {
        Conexion conexion = new Conexion();

        String hql = "FROM Propietario p";

        Query query = conexion.getConexion().createQuery(hql);
        List<Propietario> propietarios = query.getResultList();

        conexion.desconectar();

        return propietarios;
    }
}
