package Ejemplo01ORM.test;


import Ejemplo01ORM.model.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test08Contains {
	private static EntityManager em;
	private static EntityManagerFactory emf; 

	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

		//Query para recuperar la entidad a modificar
		Persona persona = em.find(Persona.class, 2);
		
		boolean existe = em.contains(persona);
		if(existe) 
			System.out.println(persona +"\n Está en el Contenxto de Persistencia");
		else
			System.out.println(persona +"\n No Está en el Contenxto de Persistencia");
		
		em.detach(persona);

		existe = em.contains(persona);
		System.out.println("Después de ejecutar el método detach()");
		if(existe) 
			System.out.println(persona +"\n\n Está en el Contenxto de Persistencia");
		else
			System.out.println(persona +"\n\n No Está en el Contenxto de Persistencia");

		em.close();
		emf.close();

		System.out.println("Fin");
	}

}
