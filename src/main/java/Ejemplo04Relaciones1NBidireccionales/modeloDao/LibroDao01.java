package Ejemplo04Relaciones1NBidireccionales.modeloDao;

import Ejemplo04Relaciones1NBidireccionales.model.Libro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.List;

public class LibroDao01 {

	private static EntityManager em;
	private static EntityManagerFactory emf;

	public static void nuevoLibro(Libro libro) {
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(libro);
		} catch (PersistenceException pe) {
			em.getTransaction().rollback();
			pe.printStackTrace();
		}
		em.close();
		emf.close();
	}

	public static List<Libro> listadoLibros() {

		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

		String hql = "FROM Libro";

		List<Libro> libros = (List<Libro>) em.createQuery(hql).getResultList();

		em.close();
		emf.close();
		
		return libros;
	}

}
