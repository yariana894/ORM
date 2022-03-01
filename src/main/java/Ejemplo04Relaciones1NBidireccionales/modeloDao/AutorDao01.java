package Ejemplo04Relaciones1NBidireccionales.modeloDao;

import Ejemplo04Relaciones1NBidireccionales.model.Autor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import java.util.List;

public class AutorDao01 {

	static EntityManagerFactory emf;
	static EntityManager em;

	public static void nuevoAutor(Autor autor) {
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo01");
		em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			em.persist(autor);
			em.getTransaction().commit();
		} catch (PersistenceException pe) {
			em.getTransaction().rollback();
			pe.printStackTrace();
		}
		em.close();
		emf.close();
	}

	public static Autor buscarAutor(int codigo) {
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo01");
		em = emf.createEntityManager();

		Autor autor = em.find(Autor.class, codigo);

		em.close();
		emf.close();

		return autor;
	}

	public static List<Autor> listadoAutores() {
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo01");
		em = emf.createEntityManager();
		
		String hql = "FROM Autor";

		List<Autor> autores = em.createQuery(hql).getResultList();

		return autores;
	}
}
