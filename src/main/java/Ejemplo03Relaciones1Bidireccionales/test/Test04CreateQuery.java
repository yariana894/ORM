package Ejemplo03Relaciones1Bidireccionales.test;


import Ejemplo03Relaciones1Bidireccionales.model.Empleado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Test04CreateQuery {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {

		emf = Persistence.createEntityManagerFactory("HibernateEjemplo1");
		em = emf.createEntityManager();

		// createQuery(consulta (string)).getResultList() - devuelve varios objetos
		// .getResultFirst() - devuelve el primer objeto

		// si queremos listar todo los objetos de la clase ponemos from y ya no
		// necesitamos el select
		String hql = "FROM Empleado";

		// crear la consulta y ejecutarla
		List<Empleado> empleados = em.createQuery(hql).getResultList();

		for (Empleado emple : empleados) {
			System.out.println(emple);
		}

		System.out.println("\n\n");

		// getSingleResult(), lista el primer elemento de la consulta
		// listar el empleado de codigo 235

		hql = "FROM Empleado e where e.codigo = 235";
		Empleado empleado = (Empleado) em.createQuery(hql).getSingleResult();

		System.out.println(empleado);

		System.out.println("Fin");

		em.close();
		emf.close();
	}
}
