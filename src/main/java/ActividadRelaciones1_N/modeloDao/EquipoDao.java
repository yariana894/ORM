package ActividadRelaciones1_N.modeloDao;

import ActividadRelaciones1_N.model.Equipo;
import Ejemplo05RelacionesNM.conexion.Conexion;

import javax.persistence.PersistenceException;

public class EquipoDao {

    public static void nuevoEquipo(Equipo equipo) {
        Conexion conexion = new Conexion();
        try {
            conexion.getConexion().getTransaction().begin();
            conexion.getConexion().persist(equipo);
            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            conexion.getConexion().getTransaction().rollback();
            pe.printStackTrace();
        }
        conexion.desconectar();
    }

    public static Equipo buscarEquipo(String eqNombre) {
        Conexion conexion = new Conexion();

        Equipo equipo = conexion.getConexion().find(Equipo.class, eqNombre);

        conexion.desconectar();

        return equipo;
    }

}
