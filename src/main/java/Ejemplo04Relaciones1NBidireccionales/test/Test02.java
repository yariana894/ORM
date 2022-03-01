package Ejemplo04Relaciones1NBidireccionales.test;

import Ejemplo04Relaciones1NBidireccionales.introducirDatos.IntroducirDatos;
import Ejemplo04Relaciones1NBidireccionales.model.Autor;
import Ejemplo04Relaciones1NBidireccionales.model.Libro;
import Ejemplo04Relaciones1NBidireccionales.modeloDao.AutorDao01;
import Ejemplo04Relaciones1NBidireccionales.modeloDao.LibroDao01;

import java.util.List;

public class Test02 {

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("1-- Nuevo Autor");
            System.out.println("2-- Nuevo Libro");
            System.out.println("3-- Listado Libros");
            System.out.println("4-- Listado Autores");
            System.out.println("5-- Salir");
            opcion = Integer.parseInt(IntroducirDatos.introducirDatos("Elegir opcion: "));

            switch (opcion) {
                case 1:
                    nuevoAutor();
                    break;
                case 2:
                    nuevoLibro();
                    break;
                case 3:
                    listadoLibros();
                    break;
                case 4:
                    listadoAutores();
                    break;
                case 5:
                    System.exit(0);

                default:
            }
        } while (opcion != 5);

    }

    private static void listadoAutores() {
        // select * from autores join libros on licodautor = aucodigo
        List<Autor> autores = AutorDao01.listadoAutores();

        // listado LAZY
        for (Autor autor : autores) {
            System.out.println(autor + "\t" + autor.getLibros());
        }

        // listado EAGER
        for (Autor autor : autores) {
            System.out.println(autor + "\t" + autor.getLibros());
        }
        System.out.println("==========================");

        for (Autor autor : autores) {
            System.out.println(autor);
            for (Libro libro : autor.getLibros()) {
                System.out.println("\t" + libro);
            }
        }
    }

    private static void listadoLibros() {
        // select * from libros
        List<Libro> libros = LibroDao01.listadoLibros();

        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    private static void nuevoAutor() {
        Autor autor = new Autor(Integer.parseInt(IntroducirDatos.introducirDatos("C�digo autor:")),
                IntroducirDatos.introducirDatos("Nombre: "), IntroducirDatos.introducirDatos("Nacionalidad: "));
        AutorDao01.nuevoAutor(autor);
    }

    private static void nuevoLibro() {
        Libro libro = new Libro();
        libro.setCodigo(Integer.parseInt(IntroducirDatos.introducirDatos("C�digo libro: ")));
        libro.setTitulo(IntroducirDatos.introducirDatos("Titulo: "));

        Autor autor = AutorDao01.buscarAutor(Integer.parseInt(IntroducirDatos.introducirDatos("C�digo Autor: ")));
        if (autor != null) {
            libro.setAut(autor);
            LibroDao01.nuevoLibro(libro);
        } else {
            System.out.println("Autor Desconocido, tiene que darlo de alta");
        }
    }
}
