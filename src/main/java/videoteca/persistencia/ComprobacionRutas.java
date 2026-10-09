package videoteca.persistencia;

import java.nio.file.Files;
import videoteca.excepciones.ExcepcionDirectorioNoExiste;
import videoteca.excepciones.ExcepcionFicheroNoExiste;
import videoteca.excepciones.ExcepcionOperacionCancelada;

import java.nio.file.Path;

/**
 * Comprobaciones previas a importar y a exportar.
 *
 * TAREA 1 (comprobarExportacion) y TAREA 4 (comprobarImportacion).
 * Apuntes: apartado 2 (NIO.2) y apartado 14 (excepciones).
 *
 * Las 3 excepciones ya estan creadas (paquete videoteca.excepciones): solo hay
 * que lanzarlas con "throw new ...".
 */
public final class ComprobacionRutas {

    private ComprobacionRutas() {
    }

    /**
     * TAREA 4. Antes de IMPORTAR: el directorio Y el fichero tienen que existir.
     *
     * PASOS:
     *   1. Saca el directorio: fichero.toAbsolutePath().getParent()
     *      (con toAbsolutePath() getParent() nunca es null, aunque el Path sea solo "datos.txt").
     *   2. Si NO es un directorio (Files.isDirectory) -> throw new ExcepcionDirectorioNoExiste(directorio).
     *   3. Si NO es un fichero (Files.isRegularFile) -> throw new ExcepcionFicheroNoExiste(fichero).
     *
     * TODO: implementar.
     * @throws videoteca.excepciones.ExcepcionDirectorioNoExiste
     */
    public static void comprobarImportacion(Path fichero)
            throws ExcepcionDirectorioNoExiste, ExcepcionFicheroNoExiste {
        //Esta parte la empecé por error pero falta
        if(fichero.toAbsolutePath().getParent() == null){
            throw new ExcepcionDirectorioNoExiste(fichero);
        }
        throw new UnsupportedOperationException("TODO: comprobarImportacion (tarea 4)");
    }

    /**
     * TAREA 1. Antes de EXPORTAR.
     *
     * PASOS:
     *   1. El directorio tiene que existir -> si no, ExcepcionDirectorioNoExiste.
     *   2. Si el fichero NO existe: no pasa nada (se creara al escribir).
     *   3. Si el fichero YA existe y sobrescribir es false -> ExcepcionOperacionCancelada.
     *   4. Si el fichero YA existe y sobrescribir es true -> no pasa nada (se sobrescribira).
     *
     * PISTAS: Files.exists, Files.isDirectory.
     *
     * El objetivo teórico es asegurar que el entorno esté preparado para guardar un archivo nuevo, 
     * confirmando que la ruta destino sea válida 
     * y protegiendo los datos previos del usuario en caso de que ya exista un archivo con ese nombre.
     * 
     * TODO: implementar.
     */
    public static void comprobarExportacion(Path fichero, boolean sobrescribir)
            throws ExcepcionDirectorioNoExiste, ExcepcionOperacionCancelada {
        
        if(!Files.isDirectory(//si es no es un directorio real
                fichero.getParent())){
        throw new ExcepcionDirectorioNoExiste(fichero);
        }
        
        if(!Files.exists(fichero)){
        //TODO quedé aquí Si no existe 
        }
        
        throw new UnsupportedOperationException("TODO: comprobarExportacion (tarea 1)");
    }
}
