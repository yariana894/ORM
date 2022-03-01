package Ejemplo05RelacionesNM.test;

import Ejemplo05RelacionesNM.conexion.Conexion;
import Ejemplo05RelacionesNM.modeloVO.Proyecto;
import Ejemplo05RelacionesNM.modeloVO.Trabajador;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class Test01 {
    static Conexion conexion = new Conexion();

    public static void main(String[] args) {
        insertarDatos();


        visualizarDatosTrabajadores();

        //visuliaza los proyectos y sus trabajadores
        visualizarDatosProyectos();


        conexion.desconectar();
    }

    private static void insertarDatos() {

        //creamos la transacci�n
        conexion.getConexion().getTransaction().begin();

        try {
            //creamos los Proyecto
            Proyecto proyecto1 = new Proyecto("Zeus");
            Proyecto proyecto2 = new Proyecto("Apolo");
            Proyecto proyecto3 = new Proyecto("Atenea");
            Proyecto proyecto4 = new Proyecto("Hera");
            Proyecto proyecto5 = new Proyecto("Ares");
            Proyecto proyecto6 = new Proyecto("Afrodita");

            //persistimos los proyectos
            conexion.getConexion().persist(proyecto1);
            conexion.getConexion().persist(proyecto2);
            conexion.getConexion().persist(proyecto3);
            conexion.getConexion().persist(proyecto4);
            conexion.getConexion().persist(proyecto5);
            conexion.getConexion().persist(proyecto6);

            //creamos las listas con los proyectos que le vamos a asignar a cada empleado

            List<Proyecto> listProyectos01 = new ArrayList<Proyecto>();
            listProyectos01.add(proyecto1);
            listProyectos01.add(proyecto4);
            listProyectos01.add(proyecto6);

            List<Proyecto> listProyectos02 = new ArrayList<Proyecto>();
            listProyectos02.add(proyecto2);
            listProyectos02.add(proyecto3);

            List<Proyecto> listProyectos03 = new ArrayList<Proyecto>();
            listProyectos03.add(proyecto5);
            listProyectos03.add(proyecto1);
            listProyectos03.add(proyecto2);
            listProyectos03.add(proyecto4);

            //creamos los trabajadores asignandole su lista de proyectos
            Trabajador trabajador1 = new Trabajador("Ana L�pez", 2500, listProyectos01);
            Trabajador trabajador2 = new Trabajador("Marta Garc�a", 3600, listProyectos02);
            Trabajador trabajador3 = new Trabajador("Isabel Rodr�guez", 2500, listProyectos03);
            Trabajador trabajador4 = new Trabajador("Mario �lvarez", 2500, listProyectos01);

            //guardamos los trabajadores
            conexion.getConexion().persist(trabajador1);
            conexion.getConexion().persist(trabajador2);
            conexion.getConexion().persist(trabajador3);
            conexion.getConexion().persist(trabajador4);

            //cerramos la transaccion
            conexion.getConexion().getTransaction().commit();
        } catch (Exception e) {
            conexion.getConexion().getTransaction().rollback();
            e.printStackTrace();
        }


    }

    //	visualiza los proyectos con sus trabajadores
    private static void visualizarDatosProyectos() {


        String consulta = "FROM Proyecto p";
        Query query = conexion.getConexion().createQuery(consulta);
        List<Proyecto> proyectos = query.getResultList();
        System.out.println("Listado de Empleados por Proyecto");
        System.out.println("================================");

        for (Proyecto proyecto : proyectos) {
            System.out.println(proyecto.getCodigo() + "\t" + proyecto.getNombre());

            //recuperamos los proyectos del empleado
            List<Trabajador> trabajadores = proyecto.getTrabajadores();
            for (Trabajador trabajador : trabajadores)
                System.out.println(trabajador.getCodigo() + "\t" + trabajador.getNombre()
                        + "\t" + trabajador.getSalario());


            System.out.println("______________________________________________");
        }


    }

    // recorre todos los trabajadores y sus proyectos
    private static void visualizarDatosTrabajadores() {

        String consulta = "FROM Trabajador t";
        Query query = conexion.getConexion().createQuery(consulta);
        List<Trabajador> trabajadores = query.getResultList();
        System.out.println("Listado de Trabajadores con sus proyectos");
        System.out.println("================================");

        for (Trabajador trabajador : trabajadores) {
            System.out.println(trabajador.getCodigo() + "\t" + trabajador.getNombre()
                    + "\t" + trabajador.getSalario());

            //recuperamos los proyectos del trabajador
            List<Proyecto> proyectos = trabajador.getProyectos();
            for (Proyecto proyecto : proyectos)
                System.out.println(proyecto.getCodigo() + "\t" + proyecto.getNombre());

            System.out.println("______________________________________________");
        }

    }

}
