package Ejemplo01ORM.test;


import Ejemplo01ORM.model.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test04PersistFind {

	private static EntityManager em;
	private static EntityManagerFactory emf; 

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

		//Query para recuperar la entidad a modificar
		Persona persona = em.find(Persona.class, 1);

		// Abrimos una transacci�n
		em.getTransaction().begin();

	
		//modificamos los valores de la entidad Persona recuperada

		persona.setApellido("Arrieta");
		persona.setEstado(Persona.Estado.Casado);

		persona.setNombre("Rosario");
		persona.setSalario(3500.0);

		// persistimos 
		em.persist(persona);

		em.getTransaction().commit();

		System.out.println(persona);
		System.out.println("\n" +persona.getIdPersona()+"\t"+persona.getApellido());
	}

}
