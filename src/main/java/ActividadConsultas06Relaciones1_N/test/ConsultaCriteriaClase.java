package ActividadConsultas06Relaciones1_N.test;

import ActividadConsultas06Relaciones1_N.conexion.Conexion;
import ActividadConsultas06Relaciones1_N.modeloVO.Inmueble;
import ActividadConsultas06Relaciones1_N.modeloVO.Inquilino;
import ActividadConsultas06Relaciones1_N.modeloVO.Propietario;
import Ejemplo04Relaciones1NBidireccionales.introducirDatos.IntroducirDatos;

import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

public class ConsultaCriteriaClase {
    static Conexion conexion = new Conexion();

    public static void main(String[] args) {
        //Listar los inquilinos
        //consulta01();

        //listar nombres de los  inquilinos
        //consulta02();

        //listar el nombre y apelldios de los propietarios
        //consulta03();

        //listar el nombre y apelldios de los propietarios.utilizando muiltiselect
        //consulta04();

        //consultar el inquilino cuyo dni es '12345678G'
        //consulta05();

        //consultar un propietarios cuyo dni introducimos como parametro dni 12345678M
        //consulta06();

        //consultar los propietarios cuyo telefono no sea nulo
        //consulta07();

        //listar los inquilinos cuyo nombre empieza por R
        //consulta08();

        //listar los inmuebles de vigo que estén desocupados
        //consulta09();

        conexion.desconectar();
    }

    private static void consulta09() {
        //from inmuebles i where i.estado = false and i.direccion like '%Vigo%
        CriteriaBuilder criteriaBuilder = conexion.getConexion().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Inmueble.class);

        Root<Inmueble> root = criteriaQuery.from(Inmueble.class);

        //crear las restricciones o condicioens
        //es un array
        Predicate[] restrincciones = new Predicate[]{
                criteriaBuilder.like(root.get("direccion"), "%Vigo"),
                criteriaBuilder.isFalse(root.get("estado"))
        };

        //agregar las restrincciones a la consulta
        criteriaQuery.where(criteriaBuilder.and(restrincciones));

        Query query = conexion.getConexion().createQuery(criteriaQuery);

        List<Inmueble> inmuebles = query.getResultList();

        listadoInmuebles(inmuebles);


    }


    private static void consulta08() {
        //from inquilino i where i.nombre like 'R%'
        CriteriaBuilder criteriaBuilder = conexion.getConexion().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Inquilino.class);

        Root<Inquilino> root = criteriaQuery.from(Inquilino.class);

        String buscar = "R".toUpperCase() + "%";

        criteriaQuery.select(root).where(criteriaBuilder.like(criteriaBuilder.upper(root.get("nombre")), buscar));

        Query query = conexion.getConexion().createQuery(criteriaQuery);
        List<Inquilino> inquilinos = query.getResultList();
        listadoInquilinos(inquilinos);
    }

    private static void consulta07() {
        //from propietario p where p.telefno is not null
        CriteriaBuilder criteriaBuilder = conexion.getConexion().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Propietario.class);

        Root<Propietario> root = criteriaQuery.from(Propietario.class);

        //crear los parametros de busqueda
        //varias maneras de hacerlo
        criteriaQuery.select(root).where(criteriaBuilder.isNotNull(root.get("telefono")));
        //criteriaQuery.select(root).where(criteriaBuilder.isNull(root.get("telefono")));

        Query query = conexion.getConexion().createQuery(criteriaQuery);

        List<Propietario> propietarios = query.getResultList();
        listadoPropietarios(propietarios);
    }


    private static void consulta06() {
        //from propietarios p where p.dni=:buscar
        CriteriaBuilder criteriaBuilder = conexion.getConexion().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Propietario.class);

        Root<Propietario> root = criteriaQuery.from(Propietario.class);

        //crear los parametros de busqueda
        ParameterExpression<String> parametro = criteriaBuilder.parameter(String.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("dni"), parametro));

        TypedQuery<Propietario> query = conexion.getConexion().createQuery(criteriaQuery);
        query.setParameter(parametro, IntroducirDatos.introducirDatos("DNI a buscar: "));

        //ejecutar consulta
        Propietario propietario = query.getSingleResult();
        System.out.println(propietario);
    }

    private static void consulta05() {
        //from inquilino i where i.dni = '12345678G
        CriteriaBuilder criteriaBuilder = conexion.getConexion().getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Inquilino.class);

        Root<Inquilino> root = criteriaQuery.from(Inquilino.class);

        //crear los parametros de busqueda
        ParameterExpression<String> dni = criteriaBuilder.parameter(String.class);
        //el parametro que le paso y el que quiero comparar
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("dni"), dni));

        Query query = conexion.getConexion().createQuery(criteriaQuery);

        //pasarle los parámetros
        query.setParameter(dni, "12345678G");
        Inquilino inquilino = (Inquilino) query.getSingleResult();
        System.out.println(inquilino);
    }

    private static void consulta04() {
        //listar el nombre y apelldios de los propietarios.utilizando muiltiselect
        CriteriaBuilder criteriaBuilder = conexion.getConexion().getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();

        Root<Propietario> root = criteriaQuery.from(Propietario.class);
        criteriaQuery.multiselect(root.get("nombre"), root.get("apellidos"));

        List<Tuple> tuplas = conexion.getConexion().createQuery(criteriaQuery).getResultList();

        for (Tuple tupla : tuplas) {
            System.out.println(tupla.get(0) + "\t\t" + tupla.get(1));
        }
    }

    private static void consulta03() {
        //listar nombre y apellidos de los propietarios
        //select p.nombre, p.apellidos from propietario p
        CriteriaBuilder criteriaBuilder = conexion.getConexion().getCriteriaBuilder();
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();

        Root<Propietario> root = criteriaQuery.from(Propietario.class);
        criteriaQuery.select(criteriaBuilder.tuple(root.get("nombre"), root.get("apellidos")));

        List<Tuple> tuplas = conexion.getConexion().createQuery(criteriaQuery).getResultList();

        for (Tuple tupla : tuplas) {
            System.out.println(tupla.get(0) + "\t\t" + tupla.get(1));
        }
    }

    private static void consulta02() {
        //listar llos nombres de los inquilinos
        //select i.nombre from inquilino i

        CriteriaBuilder criteriaBuilder = conexion.getConexion().getCriteriaBuilder();
        //en createquery muestra lo que vas a recoger como aquí devuelve un i.nombre que es un string ponemos String.class
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(String.class);

        //root
        Root root = criteriaQuery.from(Inquilino.class);
        criteriaQuery.select(root.get("nombre"));

        List<String> nombres = conexion.getConexion().createQuery(criteriaQuery).getResultList();

        for (String nombre : nombres) {
            System.out.println(nombre);
        }
    }

    private static void consulta01() {
        //listar los inquilinos
        //utilizar cb
        CriteriaBuilder criteriaBuilder = conexion.getConexion().getCriteriaBuilder();
        //cre un objeto criteriaquery
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Inquilino.class);

        //from inquilino i
        //crear root
        Root<Inquilino> root = criteriaQuery.from(Inquilino.class);

        //ejecutar la consulta
        List<Inquilino> inquilinos = conexion.getConexion().createQuery(criteriaQuery).getResultList();

        listadoInquilinos(inquilinos);
    }

    private static void listadoInquilinos(List<Inquilino> inquilinos) {
        for (Inquilino inquilino : inquilinos)
            System.out.println(inquilino);
    }

    private static void listadoPropietarios(List<Propietario> propietarios) {
        for (Propietario propietario : propietarios)
            System.out.println(propietario);
    }

    private static void listadoInmuebles(List<Inmueble> inmuebles) {
        for (Inmueble inmueble : inmuebles)
            System.out.println(inmueble);
    }

}
