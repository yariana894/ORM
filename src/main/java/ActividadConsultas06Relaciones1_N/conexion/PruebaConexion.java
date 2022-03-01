package ActividadConsultas06Relaciones1_N.conexion;

public class PruebaConexion {

	public static void main(String[] args) {
		Conexion conexion = new Conexion();
		
		if(conexion.getConexion() != null) {
			System.out.println("Conexion ok");
		}else
			System.out.println("Error");
		conexion.desconectar();
	}
}
