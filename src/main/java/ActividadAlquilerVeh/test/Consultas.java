package ActividadAlquilerVeh.test;

import ActividadAlquilerVeh.conexion.Conexion;
import ActividadAlquilerVeh.modeloDAO.ClienteDao;
import ActividadAlquilerVeh.modeloDAO.VehiculoClienteDao;
import ActividadAlquilerVeh.modeloDAO.VehiculoDao;
import ActividadAlquilerVeh.modeloVO.Cliente;
import ActividadAlquilerVeh.modeloVO.Oficina;
import ActividadAlquilerVeh.modeloVO.Vehiculo;
import ActividadAlquilerVeh.modeloVO.VehiculoCliente;
import Ejemplo04Relaciones1NBidireccionales.introducirDatos.IntroducirDatos;

import javax.persistence.PersistenceException;
import java.util.Iterator;
import java.util.List;

public class Consultas {
    public static void main(String[] args) {


        int opcion;
        do {
            System.out.println("");
            System.out.println("1. Generar un listado con todos los vehículos de los grupos E, F y G.");
            System.out.println("2. Obtener para cada vehículo el total de días alquilados y el importe total cobrado. Ordenados " +
                    " por el número de matrícula.");
            System.out.println("3. Listar los vehículos que no hayan sido alquilados.");
            System.out.println("4. Obtener un listado con todos los alquileres de un determinado coche cuya matrícula" +
                    " introducimos por teclado, por ejemplo 'HHM-4572'. Los datos que se mostrarán son: el" +
                    " nombre del cliente, la matricula y la marca del coche, los días que duró el alquiler, el tipo de" +
                    " seguro y el precio.");
            System.out.println("5. Insertar un alquiler, en el caso de que el cliente o el vehículo no existan hay que darlos de" +
                    " alta previamente. Los datos del alquiler se introducirán por teclado");
            System.out.println("6. Obtener un listado de los alquileres mostrando los siguientes datos: Matrídula, DNI, Nombre" +
                    " y teléfono del cliente, días y precio. Ordenado por el nombre del cliente y por matrícula.");
            System.out.println("7. Listar los vehículos con un número de puertas y cuyo maletero tenga una capacidad superior" +
                    " a unos valores que introducimos por teclado. Por ejemplo 5 y 500");
            System.out.println("8. Listar todos los clientes cuyo apellido comience por una letra que introducimos por teclado." +
                    " Por ejemplo la A.");
            System.out.println("9. Listar el coche o coches cuyo precio de alquiler haya sido el más caro.");
            System.out.println("10. Listar todos los vehículos cuya matricula contenga una combinación de letras determinada" +
                    " que introducimos por teclado. Por ejemplo HHM.");
            System.out.println("11. Actualizar el precio de los alquileres delos vehículos de una determinada marca.");
            System.out.println("12. Realizar una consulta para eliminar un determinado coche cuya matricula introducimos como" +
                    " parémetro.");
            System.out.println("13. Salir");

            opcion = Integer.parseInt(IntroducirDatos.introducirDatos("Elegir opcion: "));

            System.out.println("");

            switch (opcion) {
                case 1:
                    consulta01();
                    /*listo*/
                    break;
                case 2:
                    consulta02();
                    /*listo*/
                    break;
                case 3:
                    consulta03();
                    /*listo*/
                    break;
                case 4:
                    consulta04();
                    /*listo*/
                    break;
                case 5:
                    consulta05();
                    /*====================NO FUNCIONA===============*/
                    break;
                case 6:
                    consulta06();
                    /*listo*/
                    break;
                case 7:
                    consulta07();
                    /*listo*/
                    break;
                case 8:
                    consulta08();
                    /*listo*/
                    break;
                case 9:
                    consulta09();
                    /*listo*/
                    break;
                case 10:
                    consulta10();
                    /*lsito*/
                    break;
                case 11:
                    consulta11();
                    /*====================NO FUNCIONA ERROR EN EL WHERE===============*/
                    break;
                case 12:
                    consulta12();
                    /*listo*/
                    break;
                case 13:
                    System.exit(0);
                default:
            }
        } while (opcion != 13);
    }

    private static void consulta12() {
        VehiculoDao.eliminarVehMatricula(IntroducirDatos.introducirDatos("Matricula: "));
    }

    private static void consulta11() {
        /*
         * update vehiculosclientes set vcprecio = vcprecio +50
         * where vcmatricula in (select vematricula from vehiculos where vemarca= 'alfa romeo')
         * */

        String marca = IntroducirDatos.introducirDatos("Introduce marca: ");

        int incremento = Integer.parseInt(IntroducirDatos.introducirDatos("Incremento: "));
        VehiculoClienteDao.actualizarAlquileres(marca, incremento);
    }

    private static void consulta10() {
        /*select * from vehiculos where veMatricula like ?;*/

        String matricula = IntroducirDatos.introducirDatos("Letras Matrícula: ").toUpperCase() + "%";
        //System.out.println(matricula);

        List<Vehiculo> vehiculos = VehiculoDao.listadoVehLetrasMatricula(matricula);
        listadoVehiculos(vehiculos);
    }

    private static void consulta09() {
        /*select * from vehiculosclientes
        where vcPrecio >= (select max(vcPrecio) from vehiculosclientes)*/
        List<VehiculoCliente> vehiculoClientes = VehiculoClienteDao.listadoVehiculosPrecioMasCaro();
        listadoVehiculosCliente(vehiculoClientes);
    }

    private static void consulta08() {
        /*select substring(clNombre,1, 1) from clientes
        where substring(clNombre,1, 1) like ?*/
        List<Cliente> clientes = ClienteDao.listadoNombreClienteEmpieceLetraPorTeclado(
                IntroducirDatos.introducirDatos("Introduce la letra: ").toUpperCase());

        listadoClientes(clientes);
    }

    private static void consulta07() {
        /*select vePuertas, veMaletero from vehiculos
        where vePuertas >= ? and veMaletero > ?;*/
        List<Vehiculo> vehiculo = VehiculoDao.listadoVehiculosPuertasMaleteroSuperiorCantidadporTeclado(
                Integer.parseInt(IntroducirDatos.introducirDatos("Numero puertas: ")),
                Integer.parseInt(IntroducirDatos.introducirDatos("Cantidad maletero: ")));
        listadoVehiculos(vehiculo);
    }

    private static void consulta06() {
        /*select vcMatricula, clDNI,clNombre,clTelefono,vcDias,vcPrecio
        from vehiculosclientes join clientes c on c.clDNI = vehiculosclientes.vcDni
        order by clNombre, vcMatricula*/
        Iterator iterator = VehiculoClienteDao.listadoVehiculosAlquiladosCliente();

        System.out.println("Matrícula" + "\t" + "DNI cliente" + "\t" + "Nombre cliente" + "\t" + "Teléfono" + "\t" + "Días" + "\t" + "Precio");
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1] + "\t" + fila[2] + "\t" + fila[3] + "\t" + fila[4] + "\t" + fila[5]);
        }
    }

    private static void consulta05() {
        Conexion conexion = new Conexion();

        VehiculoCliente vc = new VehiculoCliente();

        String dni = IntroducirDatos.introducirDatos("Introduce el dni: ");

        Cliente cliente = conexion.getConexion().find(Cliente.class, dni);
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setDni(dni);
            System.out.println("Introduce los datos del cliente nuevo: ");
            cliente.setNombre(IntroducirDatos.introducirDatos("Introduce un nombre: "));
            cliente.setDireccion(IntroducirDatos.introducirDatos("Introduce una dirección: "));
            cliente.setCodigoPostal(Integer.parseInt(IntroducirDatos.introducirDatos("Introduce code postal: ")));
            cliente.setCiudad(IntroducirDatos.introducirDatos("Introduce una ciudad: "));
            cliente.setProvincia(IntroducirDatos.introducirDatos("Introduce provincia: "));
            cliente.setTelefono(Integer.parseInt(IntroducirDatos.introducirDatos("Introduce tlf: ")));
            cliente.setNumTarjeta(IntroducirDatos.introducirDatos("Introduce el num de tarjeta: "));

            try {
                conexion.getConexion().getTransaction().begin();

                conexion.getConexion().persist(cliente);

                conexion.getConexion().getTransaction().commit();
            } catch (PersistenceException pe) {
                conexion.getConexion().getTransaction().rollback();
                pe.printStackTrace();
            }
            conexion.desconectar();
        }
        String matricula = IntroducirDatos.introducirDatos("Introduce la matrícula: ");
        Vehiculo vehiculo = conexion.getConexion().find(Vehiculo.class, matricula);

        if (vehiculo == null) {
            vehiculo = new Vehiculo();
            System.out.println("Introduce los datos del vehiculo nuevo: ");
            vehiculo.setMarca(IntroducirDatos.introducirDatos("Introduce marca: "));
            vehiculo.setGrupo(Vehiculo.Grupo.valueOf(IntroducirDatos.introducirDatos("Introduce grupo: ")));
            vehiculo.setPlazas(Integer.parseInt(IntroducirDatos.introducirDatos("Introduce plazas: ")));
            vehiculo.setPuertas(Integer.parseInt(IntroducirDatos.introducirDatos("Introduce puertas: ")));
            vehiculo.setMaletero(Integer.parseInt(IntroducirDatos.introducirDatos("Introduce capacidad maletero: ")));
            vehiculo.setEdad(Integer.parseInt(IntroducirDatos.introducirDatos("Introduce edad: ")));
            Oficina oficina = conexion.getConexion().find(Oficina.class, Integer.parseInt(IntroducirDatos.introducirDatos("Oficina: ")));
            try {
                conexion.getConexion().getTransaction().begin();

                conexion.getConexion().persist(vehiculo);

                conexion.getConexion().getTransaction().commit();
            } catch (PersistenceException pe) {
                conexion.getConexion().getTransaction().rollback();
                pe.printStackTrace();
            }
            conexion.desconectar();
        }
        vc.setCliente(cliente);
        vc.setVehiculo(vehiculo);

        vc.setDias(Integer.parseInt(IntroducirDatos.introducirDatos("Introduce los días: ")));
        vc.setPrecio(Integer.parseInt(IntroducirDatos.introducirDatos("Introduce el precio: ")));
        vc.setSeguro(IntroducirDatos.introducirDatos("Introduce el tipo de seguro: "));

        try {
            conexion.getConexion().getTransaction().begin();

            conexion.getConexion().persist(vc);

            conexion.getConexion().getTransaction().commit();
        } catch (PersistenceException pe) {
            conexion.getConexion().getTransaction().rollback();
            pe.printStackTrace();
        }
        conexion.desconectar();

    }

    private static void consulta04() {
        /*select clNombre, veMatricula, veMarca, sum(vcDias), vcSeguro, sum(vcPrecio)
        from vehiculosclientes join clientes c on c.clDNI = vehiculosclientes.vcDni
        join vehiculos v on v.veMatricula = vehiculosclientes.vcMatricula
        where vcMatricula = 'HHM-4572' group by clNombre*/
        Iterator iterator = VehiculoDao.listadoVehiculoPorTeclado(
                IntroducirDatos.introducirDatos("Introduce la matrícula: "));
        System.out.println("Nombre del cliente" + "\t" + "Matrícula" + "\t" + "Marca" + "\t" + "Dias" + "\t" + "Seguro coche" + "\t" + "Precio");
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1] + "\t" + fila[2] + "\t" + fila[3] + "\t" + fila[4] + "\t" + fila[5]);
        }
    }

    private static void consulta03() {
        /*select *
        from vehiculos left join vehiculosclientes v on vehiculos.veMatricula = v.vcMatricula
        where vcId is null*/
        Iterator iterator = VehiculoDao.listadoVehiculosNoAlquilados();
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            Vehiculo vehiculo = (Vehiculo) fila[0];
            VehiculoCliente vehiculoCliente = (VehiculoCliente) fila[1];
            System.out.println(vehiculo + "\t" + vehiculoCliente);
        }
    }

    private static void consulta02() {
        /*select veMatricula, sum(vcDias), sum(vcPrecio)
        from vehiculos join vehiculosclientes v on vehiculos.veMatricula = v.vcMatricula
        group by veMatricula order by veMatricula*/
        Iterator iterator = VehiculoDao.listadoVehiculosTotalDiasEImporte();
        while (iterator.hasNext()) {
            Object[] fila = (Object[]) iterator.next();
            System.out.println(fila[0] + "\t" + fila[1] + "\t" + fila[2]);
        }
    }

    private static void consulta01() {
        /*select * from vehiculos where veGrupo = 'E' OR veGrupo =  'F' OR veGrupo = 'G'*/
        List<Vehiculo> vehiculos = VehiculoDao.listadoVehiculoGrupoEFG();
        listadoVehiculos(vehiculos);
    }


    private static void listadoVehiculos(List<Vehiculo> vehiculos) {
        for (Vehiculo vehiculo : vehiculos)
            System.out.println(vehiculo);
    }

    private static void listadoClientes(List<Cliente> clientes) {
        for (Cliente cliente : clientes)
            System.out.println(cliente);
    }

    private static void listadoVehiculosCliente(List<VehiculoCliente> vehiculoClientes) {
        for (VehiculoCliente vehiculoCliente : vehiculoClientes)
            System.out.println(vehiculoCliente);
    }
}
