package Ejemplo01ORM.test;


import Ejemplo01ORM.model.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test03Find {

	private static EntityManager em;
	private static EntityManagerFactory emf; 
	
	public static void main(String[] args) {
		
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();
		
		//Query de la entidad Persona por el ID o campo clave
		Persona persona = em.find(Persona.class, 1);
		
		System.out.println(persona);
		System.out.println("\n" +persona.getIdPersona()+"\t"+persona.getApellido());
	}
}
