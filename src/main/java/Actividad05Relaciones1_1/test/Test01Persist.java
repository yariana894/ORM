package Actividad05Relaciones1_1.test;

import Actividad05Relaciones1_1.model.Autobus;
import Actividad05Relaciones1_1.model.Conductor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;


public class Test01Persist {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void main(String[] args) {

        emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
        em = emf.createEntityManager();

        // persistir un autobus
        Autobus autobus = new Autobus();
        autobus.setMatricula("1");
        autobus.setMarca("Seat");
        autobus.setModelo("12H");
        autobus.setNumPlazas(4);

        // creamos el conductor
        Conductor conductor = new Conductor();
        conductor.setDni("1");
        conductor.setNombre("Samuel");
        //conductor.setFecNacimiento(ConvertirFechas.convertirJavaDateASqlDate(12/05/2200));
        conductor.setNumCarnet("a");
        conductor.setAutobus(autobus);

        try {
            em.getTransaction().begin();
            em.persist(conductor);
            em.getTransaction().commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            em.getTransaction().rollback();
        }

        em.close();
        emf.close();

        System.out.println("Fin");
    }
}
