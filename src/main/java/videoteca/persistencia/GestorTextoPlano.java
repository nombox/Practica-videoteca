package videoteca.persistencia;

//librerias de sebastian
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.util.ArrayList;
//----------------

import videoteca.modelo.Categoria;

import java.io.IOException;

import java.nio.file.Path;
import java.util.List;
import javax.imageio.IIOException;
import videoteca.modelo.Pelicula;

/**
 * Guardar y leer la videoteca en texto plano.
 *
 * TAREA 1 (guardarCategoriasTexto) y TAREA 4 (obtenerCategoriasTexto). Apuntes:
 * apartado 4 (BufferedReader / BufferedWriter).
 *
 * FORMATO (una linea por registro): CATEGORIA;id;nombre
 * PELICULA;id;titulo;director;anio;duracionMinutos
 *
 * Cada linea PELICULA pertenece a la ULTIMA CATEGORIA leida antes.
 *
 * Ejemplo: CATEGORIA;1;Ciencia ficcion PELICULA;101;Blade Runner;Ridley
 * Scott;1982;117 CATEGORIA;3;Terror
 */
public final class GestorTextoPlano {

    private GestorTextoPlano() {
    }

    /**
     * TAREA 1. Escribe las categorias (y sus peliculas) en el fichero.
     *
     * TODO: implementar.
     */
    public static void guardarCategoriasTexto(List<Categoria> categorias, Path fichero) throws IOException {

        try (BufferedWriter bw = Files.newBufferedWriter(fichero)) {
            for (Categoria item : categorias) {
                //for para las categorias
                bw.write(String.format("CATEGORIA;%d;%s%n", item.getId(), item.getNombre()));
                for (Pelicula peli : item.getPeliculas()) {
                    //Segundo for para imprimir sus peliculas
                    bw.write(String.format("PELICULA;%d;%s;%s;%d;%d%n", peli.getId(), peli.getTitulo(), peli.getDirector(), peli.getAnio(), peli.getDuracionMinutos()));
                }
            }
        }
    }

    /**
     * TAREA 4. Lee el fichero y devuelve la lista de categorias, cada una con
     * sus peliculas.
     *
     * PISTAS: Integer.parseInt lanza NumberFormatException si no es un numero.
     *
     * TODO: implementar.
     */
    public static List<Categoria> obtenerCategoriasTexto(Path fichero) throws IOException {
        String linea;
        List<Categoria> categorias = new ArrayList<>();
        //Mi variable auxiliar para recordar las categorías leídas
        Categoria categoriaActual = null;
        try (BufferedReader br = Files.newBufferedReader(fichero)) {

            while ((linea = br.readLine()) != null) {

                //Para las líneas en blanco
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] campos = linea.split(";");
                String categoriaOPelicula = campos[0];

                if (!categoriaOPelicula.equals("CATEGORIA") && !categoriaOPelicula.equals("PELICULA")) {
                    throw new IOException("Error! Linea desconocida: " + linea);
                }
                try {
                    if (categoriaOPelicula.equals("CATEGORIA")) {
                        int id = Integer.parseInt(campos[1]);
                        String nombre = campos[2];
                        Categoria categoriaNueva = new Categoria(id, nombre);
                        categorias.add(categoriaNueva);
                        categoriaActual = categoriaNueva;
                    } else if (categoriaOPelicula.equals("PELICULA")) {

                        if (categoriaActual == null) {
                            throw new IOException("Error! Es una Película sin categoría asignada");
                        }

                        int id = Integer.parseInt(campos[1]);
                        String titulo = campos[2];
                        String director = campos[3];
                        int anio = Integer.parseInt(campos[4]);
                        int duracionMinutos = Integer.parseInt(campos[5]);

                        Pelicula peliculaActual = new Pelicula(id, titulo, director, anio, duracionMinutos);
                        categoriaActual.getPeliculas().add(peliculaActual);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                    throw new IOException("Error de formato en los datos de la línea: " + linea, ex);
                }

            }
        }

        return categorias;
    }
}
