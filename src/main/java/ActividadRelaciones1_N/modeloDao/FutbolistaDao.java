package ActividadRelaciones1_N.modeloDao;

import ActividadRelaciones1_N.model.Futbolista;
import Ejemplo05RelacionesNM.conexion.Conexion;

import javax.persistence.PersistenceException;

public class FutbolistaDao {

    public static void nuevoFutbolista(Futbolista futbolista) {
        Conexion conexion = new Conexion();
        try {
            conexion.getConexion().getTransaction().begin();
            conexion.getConexion().persist(futbolista);
            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            conexion.getConexion().getTransaction().rollback();
            pe.printStackTrace();
        }
        conexion.desconectar();
    }

    public static Futbolista buscarFutbolista(int numFicha) {
        Conexion conexion = new Conexion();

        Futbolista futbolista = conexion.getConexion().find(Futbolista.class, numFicha);

        conexion.desconectar();

        return futbolista;
    }
}
