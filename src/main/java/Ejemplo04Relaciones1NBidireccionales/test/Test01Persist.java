package Ejemplo04Relaciones1NBidireccionales.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test01Persist {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

		System.out.println("Fin");
		em.close();
		emf.close();
	}

}
