package Ejemplo01ORM.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test01 {

	//Ejemplo crea la tabla Personas en la BBDD ya que en el fichero persitence tenemos la opci�n create
	//EntityManager es una interfaz que contiene los m�todos para insertar, seleccionar, etc
	
	private static EntityManager em;
	private static EntityManagerFactory emf; 

	public static void main(String[] args) {

		//EntityManagerFactory es una interfaz que contiene los m�todos para insertar, 
		// seleccionar, etc
		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

	
		//cierra el EntityManager
		em.close();
		emf.close();
		
		System.out.println("Fin del programa");

	}

}
