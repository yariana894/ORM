package ActividadRelaciones1_N.modeloDao;

import ActividadRelaciones1_N.model.Contrato;
import Ejemplo05RelacionesNM.conexion.Conexion;

import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

public class ContratoDao {

    public static void nuevoContrato(Contrato contrato) {
        Conexion conexion = new Conexion();
        try {
            conexion.getConexion().getTransaction().begin();
            conexion.getConexion().persist(contrato);
            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            conexion.getConexion().getTransaction().rollback();
            pe.printStackTrace();
        }
        conexion.desconectar();
    }

    public static List<Contrato> listadoContratos() {
        Conexion conexion = new Conexion();
        List<Contrato> contratos = new ArrayList<>();
        String hql = "FROM Contrato c";

        contratos = conexion.getConexion().createQuery(hql).getResultList();
        conexion.desconectar();
        return contratos;
    }

    public static Contrato buscarContrato(int numContrato) {
        Conexion conexion = new Conexion();

        Contrato contrato = conexion.getConexion().find(Contrato.class, numContrato);

        conexion.desconectar();

        return contrato;
    }
}
