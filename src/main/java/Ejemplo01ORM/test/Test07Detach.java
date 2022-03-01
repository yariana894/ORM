package Ejemplo01ORM.test;


import Ejemplo01ORM.model.Persona;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test07Detach {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
        em = emf.createEntityManager();

        //Query para recuperar la entidad a modificar
        Persona persona = em.find(Persona.class, 2);

        System.out.println(persona);

        // Abrimos una transacci�n
        em.getTransaction().begin();

        em.detach(persona);

        //modificamos los valores de la entidad Persona recuperada

        persona.setApellido("Arrieta");
        persona.setNombre("Rosario");
        persona.setSalario(3500.0);

        // si persistimos da error ya que est� detached
        //	em.persist(persona);

        em.getTransaction().commit();

        System.out.println("Despu�s");
        System.out.println(persona);

        em.close();
        emf.close();

        System.out.println("Fin");
    }
}
