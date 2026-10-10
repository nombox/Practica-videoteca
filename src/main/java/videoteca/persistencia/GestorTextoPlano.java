package videoteca.persistencia;

//librerias de sebastian
import java.io.BufferedWriter;
import java.nio.file.Files;
//----------------

import videoteca.modelo.Categoria;

import java.io.IOException;

import java.nio.file.Path;
import java.util.List;
import videoteca.modelo.Pelicula;

/**
 * Guardar y leer la videoteca en texto plano.
 *
 * TAREA 1 (guardarCategoriasTexto) y TAREA 4 (obtenerCategoriasTexto).
 * Apuntes: apartado 4 (BufferedReader / BufferedWriter).
 *
 * FORMATO (una linea por registro):
 *     CATEGORIA;id;nombre
 *     PELICULA;id;titulo;director;anio;duracionMinutos
 *
 * Cada linea PELICULA pertenece a la ULTIMA CATEGORIA leida antes.
 *
 * Ejemplo:
 *     CATEGORIA;1;Ciencia ficcion
 *     PELICULA;101;Blade Runner;Ridley Scott;1982;117
 *     CATEGORIA;3;Terror
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

        try(BufferedWriter bw = Files.newBufferedWriter(fichero)){
            for(Categoria item: categorias){
               //for para las categorias
                bw.write(String.format("CATEGORIA;%d;%s%n",item.getId(),item.getNombre()));
                for(Pelicula peli: item.getPeliculas()){
                    //Segundo for para imprimir sus peliculas
                    bw.write(String.format("PELICULA;%d;%s;%s;%d;%d%n",peli.getId(),peli.getTitulo(),peli.getDirector(),peli.getAnio(),peli.getDuracionMinutos()));
                }
        }
        }
    }

    /**
     * TAREA 4. Lee el fichero y devuelve la lista de categorias, cada una con sus peliculas.
     *
     * PISTAS: Integer.parseInt lanza NumberFormatException si no es un numero.
     *
     * TODO: implementar.
     */
    public static List<Categoria> obtenerCategoriasTexto(Path fichero) throws IOException {
        throw new UnsupportedOperationException("TODO: obtenerCategoriasTexto (tarea 4)");
    }
}
