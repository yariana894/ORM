package Ejemplo01ORM.test;


import Ejemplo01ORM.model.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;

public class Test05Merge {

	private static EntityManager em;
	private static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();
		
		// Abrimos una transacci�n
		em.getTransaction().begin();
		//Creamos el objeto Persona
		Persona persona = new Persona();
		
		persona.setIdPersona(1);
		persona.setApellido("G�mez");
		persona.setEstado(Persona.Estado.Divorciado);
		persona.setFecNacimiento(Calendar.getInstance());
		persona.setNombre("Javier");
		persona.setSalario(2300.0);
		
		em.merge(persona);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
		System.out.println("Fin");
	}
}
