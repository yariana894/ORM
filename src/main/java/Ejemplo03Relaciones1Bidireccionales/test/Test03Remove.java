package Ejemplo03Relaciones1Bidireccionales.test;

import Ejemplo03Relaciones1Bidireccionales.model.Empleado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class Test03Remove {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

		// eliminar el empleado codigo 235 con direcci�n 4
		Empleado empleado = em.find(Empleado.class, 235);

		try {
			em.getTransaction().begin();
			em.remove(empleado);
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
