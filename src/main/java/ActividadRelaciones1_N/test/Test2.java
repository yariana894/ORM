package ActividadRelaciones1_N.test;

import ActividadRelaciones1_N.convertirFechasDate.ConvertirFechas;
import ActividadRelaciones1_N.introducirDatos.IntroducirDatos;
import ActividadRelaciones1_N.model.Contrato;
import ActividadRelaciones1_N.model.Equipo;
import ActividadRelaciones1_N.model.Futbolista;
import ActividadRelaciones1_N.modeloDao.ContratoDao;
import ActividadRelaciones1_N.modeloDao.EquipoDao;
import ActividadRelaciones1_N.modeloDao.FutbolistaDao;

import java.util.List;

public class Test2 {

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("1-- Nuevo Equipo");
            System.out.println("2-- Nuevo Futbolista");
            System.out.println("3-- Nuevo Contrato");
            System.out.println("4-- Listado Contratos");
            System.out.println("5-- Salir");
            opcion = Integer.parseInt(IntroducirDatos.introducirDatos("Elegir opcion: "));

            switch (opcion) {
                case 1:
                    nuevoEquipo();
                    break;
                case 2:
                    nuevoFutbolista();
                    break;
                case 3:
                    nuevoContrato();
                    break;
                case 4:
                    listadoContratos();
                    break;
                case 5:
                    // listadoFutbolista();
                    break;
                case 6:
                    System.exit(0);
                default:
            }
        } while (opcion != 7);
    }


    private static void listadoContratos() {
        // select * from libros
        List<Contrato> contratos = ContratoDao.listadoContratos();

        for (Contrato contrato : contratos) {
            System.out.println(contrato);
        }
    }


    private static void nuevoContrato() {
        int numContrato = Integer.parseInt(IntroducirDatos.introducirDatos("Num contrato: "));

        Contrato contrato = ContratoDao.buscarContrato(numContrato);

        if (contrato == null) {
            contrato = new Contrato(numContrato,
                    ConvertirFechas.convertirStringDate(IntroducirDatos.introducirDatos("Fecha contrato: ")),
                    Integer.parseInt(IntroducirDatos.introducirDatos("Duración contrato: ")),
                    Integer.parseInt(IntroducirDatos.introducirDatos("Ficha anual contrato: ")),
                    Integer.parseInt(IntroducirDatos.introducirDatos("Clausula contrato: ")));

            String eqNombre = IntroducirDatos.introducirDatos("Nombre del equipo: ");
            Equipo equipo = EquipoDao.buscarEquipo(eqNombre);

            if (equipo == null) {
                System.out.println("Equipo no existe");
            } else {
                contrato.setEq(equipo);

                int numFicha = Integer.parseInt(IntroducirDatos.introducirDatos("Número de ficha: "));
                Futbolista futbolista = FutbolistaDao.buscarFutbolista(numFicha);

                if (futbolista == null) {
                    System.out.println("Futbolista no existe");
                } else {
                    contrato.setFut(futbolista);
                    ContratoDao.nuevoContrato(contrato);
                }
            }

        } else {
            System.out.println("Contrato ya existe");
        }
    }

    private static void nuevoEquipo() {
        String eqNombre = IntroducirDatos.introducirDatos("Nombre del equipo: ");
        Equipo equipo = EquipoDao.buscarEquipo(eqNombre);

        if (equipo == null) {
            equipo = new Equipo(eqNombre, IntroducirDatos.introducirDatos("Presidente: "),
                    IntroducirDatos.introducirDatos("Estadio: "),
                    Integer.parseInt(IntroducirDatos.introducirDatos("Anho fundación: ")),
                    Integer.parseInt(IntroducirDatos.introducirDatos("Número de socios: ")));

            EquipoDao.nuevoEquipo(equipo);
        } else {
            System.out.println("Equipo ya existe");
        }
    }

    private static void nuevoFutbolista() {
        int numFicha = Integer.parseInt(IntroducirDatos.introducirDatos("Número de ficha: "));
        Futbolista futbolista = FutbolistaDao.buscarFutbolista(numFicha);

        if (futbolista == null) {
            futbolista = new Futbolista(numFicha,
                    IntroducirDatos.introducirDatos("Nombre futbolista: "),
                    IntroducirDatos.introducirDatos("Apellidos futbolista: "),
                    ConvertirFechas.convertirStringDate(IntroducirDatos.introducirDatos("FechaNacmi: ")),
                    Double.parseDouble(IntroducirDatos.introducirDatos("Peso futbolista: ")),
                    Integer.parseInt(IntroducirDatos.introducirDatos("Altura futbolista: ")),
                    Integer.parseInt(IntroducirDatos.introducirDatos("Sueldo futbolista: ")));

            FutbolistaDao.nuevoFutbolista(futbolista);
        } else {
            System.out.println("Futbolista ya existe");
        }
    }
}
