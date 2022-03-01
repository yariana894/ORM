package Ejemplo03Relaciones1Bidireccionales.test;

import Ejemplo03Relaciones1Bidireccionales.model.Direccion;
import Ejemplo03Relaciones1Bidireccionales.model.Empleado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class Test02Persist {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

		// crear un nuevo empleado con su dirección
		Direccion direccion = new Direccion(12, "Principe, 80", "Vigo", "Pontevedra");
		Empleado empleado = new Empleado(38, "Yari", "Rodriguez", 5200, direccion);

		try {
			em.getTransaction().begin();
			// instanciamos la transacción
			em.persist(direccion);
			em.persist(empleado);
			em.getTransaction().commit();

		} catch (PersistenceException pe) {
			em.getTransaction().rollback();
			pe.printStackTrace();
		}

		System.out.println("Fin");

		em.close();
		emf.close();
	}
}
