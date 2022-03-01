package ActividadConsultas06Relaciones1_N.test;

import ActividadConsultas06Relaciones1_N.conexion.Conexion;
import ActividadConsultas06Relaciones1_N.convertirFechasDate.ConvertirFechas;
import ActividadConsultas06Relaciones1_N.modeloDAO.ContratoDao;
import ActividadConsultas06Relaciones1_N.modeloDAO.InmuebleDao;
import ActividadConsultas06Relaciones1_N.modeloDAO.InquilinoDao;
import ActividadConsultas06Relaciones1_N.modeloVO.Contrato;
import ActividadConsultas06Relaciones1_N.modeloVO.Inmueble;
import ActividadConsultas06Relaciones1_N.modeloVO.Inquilino;
import ActividadConsultas06Relaciones1_N.modeloVO.Propietario;
import Ejemplo04Relaciones1NBidireccionales.introducirDatos.IntroducirDatos;

import java.util.List;

public class Repaso {
    static Conexion conexion = new Conexion();

    public static void main(String[] args) {
        //insertar
        consulta01();
        //actualizar
        consulta02();

    }

    private static void consulta02() {
        Contrato contrato;


    }

    private static void consulta01() {

        Contrato contrato = new Contrato();
        Inquilino inquilino;

        String numcontrato = IntroducirDatos.introducirDatos("Num contrato: ");
        contrato = ContratoDao.buscarContrato(numcontrato);
        if (contrato == null) {
            contrato = new Contrato();
            contrato.setCodContrato(numcontrato);

            String dni = IntroducirDatos.introducirDatos("Dni: ");
            inquilino = InquilinoDao.buscarInquilinoDni(dni);
            if (inquilino != null) {
                inquilino = new Inquilino();
                inquilino.setDni(dni);

                contrato.setInquilino(inquilino);
                System.out.println("El inquilino existe");

                String codInmueble = IntroducirDatos.introducirDatos("Codigo inmueble: ");
                Inmueble inmueble = InmuebleDao.buscarInmueble(codInmueble);

                if (inmueble != null) {
                    inmueble = new Inmueble();
                    inmueble.setCodInmueble(codInmueble);

                    contrato.setInmueble(inmueble);
                    System.out.println("El inmueble existe");

                    contrato.setFechaContrato(ConvertirFechas.convertirStringDate(IntroducirDatos.introducirDatos("Fecha contrato: ")));
                    contrato.setFechaVencimiento(ConvertirFechas.sumarAnhos(ConvertirFechas.convertirStringDate(IntroducirDatos.introducirDatos("Fecha vencimiento: ")), 4));
                    contrato.setPrecio(Double.parseDouble(IntroducirDatos.introducirDatos("Precio: ")));

                } else
                    System.out.println(codInmueble + "\tInexistente");
            } else
                System.out.println("Inquilino inexistente");
        } else
            System.out.println("Contrato existente");
        ContratoDao.nuevoContrato(contrato);

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
