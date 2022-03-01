package Ejemplo01ORM.test;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Ejemplo01ORM.model.Persona;
public class Test09CreateQuery {
    private static EntityManager em;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
        em = emf.createEntityManager();

        //Listar las personas de la tabal
        List<Persona> personas = (List<Persona>) em.createQuery("FROM Persona").getResultList();
        System.out.println("Hay " + personas.size() + "personas");
        for (Persona per : personas)
            System.out.println(per);

        em.close();
        emf.close();

        System.out.println("Fin");
    }
}
