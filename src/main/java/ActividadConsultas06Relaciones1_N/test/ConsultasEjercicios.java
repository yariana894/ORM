package ActividadConsultas06Relaciones1_N.test;

import ActividadConsultas06Relaciones1_N.convertirFechasDate.ConvertirFechas;
import ActividadConsultas06Relaciones1_N.modeloDAO.ContratoDao;
import ActividadConsultas06Relaciones1_N.modeloDAO.InmuebleDao;
import ActividadConsultas06Relaciones1_N.modeloDAO.InquilinoDao;
import ActividadConsultas06Relaciones1_N.modeloVO.Contrato;
import ActividadConsultas06Relaciones1_N.modeloVO.Inmueble;
import ActividadConsultas06Relaciones1_N.modeloVO.Inquilino;
import ActividadConsultas06Relaciones1_N.modeloVO.Propietario;
import Ejemplo04Relaciones1NBidireccionales.introducirDatos.IntroducirDatos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ConsultasEjercicios {
    public static void main(String[] args) {
        /*1a) Actualizar la fecha de vencimiento de un contrato que introducimos como
        parámetro.*/
        /*listo*/
        //consulta01();

        /*2a) Listar todos los inmuebles libres o que su contrato venza en el presente mes, de
        una determinada zona que introducimos como parámetro.*/
        //consulta02();

        /*3a) Incrementar el precio de los inmuebles de una zona cuyo nombre introducimos
        como parámetro.*/
        /*==============error con el double===========*/
        //consulta03();

        /*4a) Listar los inmuebles libres o cuya fecha de vencimiento sea el presente mes,
        mostrando el código, la dirección, la fecha de vencimiento, el nombre de y el teléfono
        del propietario.*/
        //consulta04();

        /*5a) Insertar un nuevo contrato, la fecha del contrato es el día que se da de alta y la
        duración del contrato es de 4 años. Hay que utilizar funciones de fecha. El inmueble
        obligatoriamente debe estar dado de alta, lo mismo que el propietario, en el caso que
        el inquilino no esté en la BBDD hay que insertarlo.*/
        consulta05();
        
        /*6a) Necesitamos separar en tres columnas diferentes el nombre y los apellidos de los
        inquilinos.*/
        //consulta06();

        /*7a) Insertar en una nueva tabla ContratosVencen, mismas columnas que la tabla
        Contratos, los contratos que venzan en el presente año.*/
        //consulta07();

        /*8o) Eliminar los inquilinos que no tengan ningún contrato.*/
        //consulta08();

        /*9o) Mostrar el contrato más caro, indicando el nombre del inquilino.*/
        /*listo*/
        //consulta09();

        /*10o) Listar el total recaudado en alquileres por cada contrato. Hay que tener en cuenta
        que el precio es mensual.*/
        //consulta010();

        /*11o) Listar para cada inquilino el número de inmuebles diferentes que nos ha
        alquilado.*/
        /*listo*/
        //consulta11();
    }


    private static void consulta11() {
        /*select iqNombre, count(inCodInmueble)
        from inquilinos join contratos c on inquilinos.iqDNI = c.coDNIInquilino
        join inmuebles i on i.inCodInmueble = c.coCodInmueble
        group by iqNombre;*/
        Iterator iterator = InmuebleDao.listDifferentNumInmuebles();
        while (iterator.hasNext()) {
            Object[] row = (Object[]) iterator.next();
            System.out.println("dni: " + row[0] + " inquilino: " + row[1] + " num contracts: " + row[2]);
        }
    }

    private static void consulta010() {
        Iterator iterator = ContratoDao.listadoRecaudadoContratos();
        System.out.println("Contrato\t\tTotal Recaudado");
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t\t" + fila[1]);
        }
    }

    private static void consulta09() {
        /*select iqNombre, coPrecio from contratos
        join inquilinos i on i.iqDNI = contratos.coDNIInquilino
        where coPrecio >= (select max(coPrecio) from contratos)
        group by iqNombre*/
        Iterator iterator = ContratoDao.listarInquilinoPrecioMax();
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1]);
        }


        /*otra forma de hacerlo*/
        List<Contrato> contratos = ContratoDao.listadoContratosMasCaros();
        for (Contrato contrato : contratos) {
            System.out.println(contrato.getCodContrato() + "\t" + contrato.getInquilino().getNombre());
        }
    }

    private static void consulta08() {
        List<Inquilino> inquilinos = InquilinoDao.listadoInquilinosSinContrato();

        InquilinoDao.eliminarInquilinoSinContrato();
        System.out.println("Listado después de eliminar: ");
        InquilinoDao.listadoInquilinosSinContrato();
    }


    private static void consulta07() {
        List<Contrato> contratos = ContratoDao.crearContratosVencen();

        for (Contrato contrato : contratos) {
            System.out.println(contrato);
        }
    }

    private static void consulta06() {
        Iterator iterator = InquilinoDao.separarNombres();
        System.out.println("PrApelldios\tSg.Apellido\tNombre\tDOs Apellidos");
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1] + "\t" + fila[2] + "\t" + fila[3]);
        }
    }

    private static void consulta05() {
        Contrato contrato = new Contrato();
        Inquilino inquilino;

        String numContrato = IntroducirDatos.introducirDatos("Num contrato: ");

        if (ContratoDao.buscarContrato(numContrato) == null) {
            contrato.setCodContrato(numContrato);

            inquilino = InquilinoDao.buscarInquilino(IntroducirDatos.introducirDatos("Dni inquilino: "));

            if (inquilino != null) {
                contrato.setInquilino(inquilino);

                String codInmueble = IntroducirDatos.introducirDatos("Código inmueble: ");
                Inmueble inmueble = InmuebleDao.buscarInmueble(codInmueble);

                if (inmueble != null) {
                    contrato.setInmueble(inmueble);

                    String hoy = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
                    contrato.setFechaContrato(ConvertirFechas.convertirStringDate(hoy));

                    Date fecha = ConvertirFechas.sumarAnhos(ConvertirFechas.convertirStringDate(hoy), 4);
                    contrato.setFechaVencimiento(fecha);

                    contrato.setPrecio(Double.parseDouble(IntroducirDatos.introducirDatos("Precio: ")));

                } else
                    System.out.println(codInmueble + "\tInexistente");
            } else
                System.out.println("Inquilino inexistente");
        } else
            System.out.println("Contrato inexistente");
        ContratoDao.nuevoContrato(contrato);
    }

    private static void consulta04() {
        /*select coCodContrato, inDireccion, coFechaVencimiento, prNombre, prTelefono
         from contratos join inmuebles i on i.inCodInmueble = contratos.coCodInmueble
         join propietarios p on p.prDNI = i.inDNIPropietario
         where (inEstado = false or (month(coFechaVencimiento) = month(curdate())));*/

        Iterator iterator = InmuebleDao.listarInmueblesLibresOFecVenci();
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1] + "\t" + fila[2] + "\t" + fila[3] + "\t" + fila[4]);
        }
    }

    private static void consulta03() {
        /*update contratos join inmuebles i on i.inCodInmueble = contratos.coCodInmueble
        set coPrecio = coPrecio +(coPrecio*?/100)*/
        String zona = IntroducirDatos.introducirDatos("Zona: ");

        double incremento = Double.parseDouble(IntroducirDatos.introducirDatos("Incremento: "));

        InmuebleDao.incrementarPrecioInmueblesZona(zona, incremento);
    }

    private static void consulta02() {
        /*select * from inmuebles join contratos c on inmuebles.inCodInmueble = c.coCodInmueble
        join zonas z on z.zoCodZona = inmuebles.inCodZona
        where (MONTH(coFechaVencimiento) = month(curdate()) or inEstado = false ) and zoNombre = ?*/
        Iterator iterator = InmuebleDao.listadoInmueblesFecVencEsteMesYLibreZona(
                IntroducirDatos.introducirDatos("Introduce la zona: "));
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1]);
        }
    }

    private static void consulta01() {
        Contrato contrato = ContratoDao.buscarContrato(IntroducirDatos.introducirDatos("Num Contrato: "));

        if (contrato != null) {
            contrato.setFechaVencimiento(ConvertirFechas.convertirStringDate(
                    IntroducirDatos.introducirDatos("Introduce la fecha(dd/mm/yyyy): ")));
            ContratoDao.actualizarContrato(contrato);
            System.out.println("Fecha actualizada");
        } else {
            System.out.println("El número de contrato introduce no existe");
        }
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
