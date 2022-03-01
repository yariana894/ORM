package Ejemplo01ORM.test;



import Ejemplo01ORM.model.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;

public class Test02Persist {

	private static EntityManager em;
	private static EntityManagerFactory emf; 
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();
		
		// Abrimos una transacci�n
		em.getTransaction().begin();
		//Creamos el objeto Persona
		Persona persona = new Persona(); // estado NEW

		persona.setApellido("Guisasola");
		persona.setEstado(Persona.Estado.Casado);
		persona.setFecNacimiento(Calendar.getInstance());
		persona.setNombre("Isabel");
		persona.setSalario(1500.50);
		
		em.persist(persona);
		
		em.getTransaction().commit();
	
		
		em.close();
		emf.close();
		
		System.out.println("Fin");
		


	}

}
