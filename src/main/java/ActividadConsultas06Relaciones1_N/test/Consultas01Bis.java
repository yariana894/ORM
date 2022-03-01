package ActividadConsultas06Relaciones1_N.test;

import ActividadConsultas06Relaciones1_N.modeloDAO.ContratoDao;
import ActividadConsultas06Relaciones1_N.modeloDAO.InmuebleDao;
import ActividadConsultas06Relaciones1_N.modeloDAO.InquilinoDao;
import ActividadConsultas06Relaciones1_N.modeloDAO.PropietarioDao;
import ActividadConsultas06Relaciones1_N.modeloVO.*;
import Ejemplo04Relaciones1NBidireccionales.introducirDatos.IntroducirDatos;

import java.util.Iterator;
import java.util.List;

public class Consultas01Bis {

    public static void main(String[] args) {
        //listado inmuebles
        //consultas01();

        //listado del inquilino de codigo 12345678G
        //consulta02();

        //seleccionar columnas
        //listar el nombre, apellidos y teléfono de los propietarios
        //consulta03();

        //campos calculadors
        //listar código del contrato, precio, precio*0.21, precio*1.21
        //consulta04();

        //Consulta que crea un objeto
        //listar dni y nombre de los inquilinos, creando un objeto
        //consulta05();

        //COnsultas de parámetros
        //Buscar los datos de un propietario cuyo DNI introducimos por teclado
        // SELECT * FROM propietarios where prDNI= ?
        //consulta06();

        //buscar los datos de los inquilinos con un salario entre dos valores
        //que introducimos por teclado
        //consulta07();

        //Listar dni, nombre, salario de los inquilinos creando una lista
        //consulta08();


        //Listado de los inmuebles libres de una determinada zona de la que sabemos el nombre
        //consulta09();

        //listado de los contratos mostrando el nombre del inquilino, nombre del propietario y el
        //nombre de la zona y dirección del inmueble
        //consulta10();

        //listado de todos los inmuebles de Vigo
        //consulta11();

        //listar los precios mayor y menor de los contratos, total contratos y el precio medio de los contratos
        //consulta12();

        //Listar cuantos inmuebles hay en cada zona
        //consulta13();

        //Listar los inmuebles que tiene cada propietario
        //consulta14();

        //listar los propietarios con sus inmuebles ocn join se muentrasn los propietarios con inmuebles
        //consulta15();

        //listar los propietarios que no tengan inmuebles
        //consulta16();


        //listar los inmuebles de una determinada zona que introducimos como parámetro con join
        //consulta17();

        //listar los contratos con un precio superiror a la media de los contrtos con subconsultas
        //consulta18();


    }

    private static void consulta18() {
        List<Contrato> contratos = ContratoDao.listadoContratosPrecioMayorMedia();
        for (Contrato c : contratos) {
            System.out.println(c);
        }
    }

    private static void consulta17() {
        Iterator iterator = InmuebleDao.listadoInmueblesZonas(
                IntroducirDatos.introducirDatos("Nombre de la zona: "));

        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            Inmueble inmueble = (Inmueble) fila[0];
            Zona zona = (Zona) fila[1];
            System.out.println(zona + "\t" + inmueble);
        }
    }

    private static void consulta16() {
        //select * from propietarios left join inmuebles on prdni = indniPropietario
        // where incodInmueble is null;
        Iterator iterator = PropietarioDao.listadoPropietariosSinInmuebles();

        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            Propietario propietario = (Propietario) fila[1];
            Inmueble inmueble = (Inmueble) fila[0];
            System.out.println(propietario + "\t" + inmueble);
        }
    }

    private static void consulta15() {
        Iterator iterator = PropietarioDao.listadoPropietariosInmueblesJoin01();
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            Propietario propietario = (Propietario) fila[0];
            Inmueble inmueble = (Inmueble) fila[1];
            System.out.println(propietario + "\t" + inmueble);
        }
    }

    private static void consulta14() {
        List<Propietario> propietarios = PropietarioDao.listadoPropietariosInmuebles();

        //me imprime los propietarios que tienen inmuebles o no
        for (Propietario pro : propietarios) {
            System.out.println(pro);
            for (Inmueble in : pro.getInmuebles()) {
                System.out.println(in);
            }
            System.out.println("");
        }
    }

    private static void consulta13() {
        /* select zocodigo, zonombre, count(incodigo)
         * from zonas join inmuebles on zocodigio = incodigzona
         * group by zocodigo, zonombre*/

        Iterator iterator = InmuebleDao.contarInmueblesZonas();
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println("Código: " + fila[0] + "\t" + "Zona: " + fila[1] + "\t" + "Contador: " + fila[2]);
        }
    }

    private static void consulta12() {
        //select max(coprecio), min (coprecio), sum(coprecio), avg(coprecio), count(*) from contratos
        Iterator iterator = ContratoDao.listadoContratosPreciosMayorMenor();

        System.out.println("Máximo\tMínimo\tTotal\tMedia\tContador");
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            double maximo = (double) fila[0];
            double minimo = (double) fila[1];
            double suma = (double) fila[2];
            double media = (double) fila[3];
            long contador = (long) fila[4];

            System.out.println(maximo + "\t" + minimo + "\t" + suma + "\t" + media + "\t" + contador);
        }
    }

    private static void consulta11() {
        //select * from inmuebles where indireccion like '%Vigo'
        List<Inmueble> inmuebles = InmuebleDao.listadoInmueblesVigo();
        for (Inmueble inmu : inmuebles) {
            System.out.println(inmu);
        }
    }

    private static void consulta10() {
        Iterator iterator = ContratoDao.listadoContratosPropietarios();
        System.out.println("Contrato\tFecha Vencimiento\tInquilino\tDireccion\tZona\tPropietario");

        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1] + "\t" + fila[2] + "\t" + fila[3] + "\t" + fila[4] + "\t" + fila[5]);
        }
    }

    private static void consulta09() {
        List<Inmueble> inmuebles = InmuebleDao.listadoInmueblesLibresZona(IntroducirDatos.introducirDatos("Nombre zona: "));
        for (Inmueble inmu : inmuebles) {
            System.out.println(inmu);
        }
    }

    private static void consulta08() {
        List<Object> listainquilinos = InquilinoDao.listadoCreandoLista();

        for (int i = 0; i < listainquilinos.size(); i++) {
            Object inqui = listainquilinos.get(i);
            System.out.println(inqui);
        }
    }

    private static void consulta07() {
        List<Inquilino> inquilinos = InquilinoDao.buscarCiudadSalario(
                Double.parseDouble(IntroducirDatos.introducirDatos("Sueldo menor: ")),
                Double.parseDouble(IntroducirDatos.introducirDatos("Sueldo mayor: "))
        );

        for (Inquilino inquilino : inquilinos) {
            System.out.println(inquilino);
        }
    }

    private static void consulta06() {
        String dni = IntroducirDatos.introducirDatos("DNI propietario a buscar: ");

        Propietario propietario = PropietarioDao.buscarPropietario(dni);

        System.out.println(propietario);
    }

    private static void consulta05() {
        List<Inquilino> inquilinos = InquilinoDao.listadoInquilinosObjetos();

        System.out.println(inquilinos);
        System.out.println("=====================================");
        for (Inquilino inquilino : inquilinos) {
            System.out.println(inquilino.getDni());
        }
    }

    private static void consulta04() {
        //select cocodContrato, coprecio, coprecio*0.21, coprecio*1.21 from contratos
        Iterator iterator = ContratoDao.listarPrecios();

        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1] + "\t" + fila[2] + "\t" + fila[3]);
        }
    }

    private static void consulta03() {
        //SELECT prNombre, prApellidos, prTelefono from propietarios
        /*siempre que devolvamos columnas lo devolvemos en un iterator
         * siempre utilizamos el while*/
        Iterator iterator = PropietarioDao.listarPropietarios();

        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1] + "\t" + fila[2]);
        }
    }

    private static void consulta02() {
        Inquilino inquilino = InquilinoDao.buscarInquilino("12345678G");
        System.out.println(inquilino);

        //me visualiza la lista de contratos de ese inquilino
        for (Contrato c : inquilino.getContratos()) {
            System.out.println(c);
        }
    }

    private static void consultas01() {
        List<Inmueble> inmuebles = InmuebleDao.listadoInmuebles();

        for (Inmueble in : inmuebles) {
            System.out.println(in);
            System.out.println(in.getCodInmueble() + "\t" + in.getDireccion() + "\t" +
                    in.getZona().getNombre() + "\t" + in.getPropietario().getApellidos()
                    + "\t" + in.getPropietario().getNombre());
        }
    }



}
