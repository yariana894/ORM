package Ejemplo03Relaciones1Bidireccionales.test;

import Ejemplo03Relaciones1Bidireccionales.model.Direccion;
import Ejemplo03Relaciones1Bidireccionales.model.Empleado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test01Find {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();
		
		
		//select * from direcciones join empleados on emIdDireccion = diId
		Empleado empleado = em.find(Empleado.class, 240);
		System.out.println(empleado);

		System.out.println("\n\n");

		// recuperar un objeto direci�n
		Direccion direccion = em.find(Direccion.class, 4);
		System.out.println(direccion + "\t" + direccion.getEmpleado().getApellidos() + "\t\t"
				+ direccion.getEmpleado().getNombre());

		em.close();
		emf.close();

		System.out.println("FIn");
	}
}
