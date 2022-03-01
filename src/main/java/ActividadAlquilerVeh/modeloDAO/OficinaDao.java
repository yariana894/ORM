package ActividadAlquilerVeh.modeloDAO;

import ActividadAlquilerVeh.conexion.Conexion;
import ActividadAlquilerVeh.modeloVO.Oficina;

public class OficinaDao {

    public static Oficina buscarOficina(int cod) {
        Conexion conexion = new Conexion();

        Oficina oficina = conexion.getConexion().find(Oficina.class, cod);
        System.out.println(oficina);

        conexion.desconectar();

        return oficina;
    }
}
