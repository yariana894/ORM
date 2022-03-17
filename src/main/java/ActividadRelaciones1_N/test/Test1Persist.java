package ActividadRelaciones1_N.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test1Persist {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
        em = emf.createEntityManager();

        em.close();
        emf.close();

        System.out.println("Fin");
    }
}
