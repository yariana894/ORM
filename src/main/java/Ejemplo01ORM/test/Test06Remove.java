package Ejemplo01ORM.test;


import Ejemplo01ORM.model.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test06Remove {
	private static EntityManager em;
	private static EntityManagerFactory emf; 

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

		//Query para recuperar la entidad a eliminar
		Persona persona = em.find(Persona.class, 1);

		// Abrimos una transacci�n
		em.getTransaction().begin();
	
		// eliminamos la persona 
		em.remove(persona);

		em.getTransaction().commit();

		em.close();
		emf.close();

		System.out.println("Fin");
	}
	}
