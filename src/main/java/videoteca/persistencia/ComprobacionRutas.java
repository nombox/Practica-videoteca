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
      
       Path directorio = fichero.toAbsolutePath().getParent();
       
       if(!Files.isDirectory(directorio)){
           throw new ExcepcionDirectorioNoExiste(directorio);
       }
       
       if(!Files.isRegularFile(fichero)){
           throw new ExcepcionFicheroNoExiste(fichero);
       }
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
     * NOTA de Sebastian:
     * El objetivo teórico es asegurar que el entorno esté preparado para guardar un archivo nuevo, 
     * confirmando que la ruta destino sea válida 
     * y protegiendo los datos previos del usuario en caso de que ya exista un archivo con ese nombre.
     * 
     * TODO: implementar.
     */
    public static void comprobarExportacion(Path fichero, boolean sobrescribir)
            throws ExcepcionDirectorioNoExiste, ExcepcionOperacionCancelada {
        
        //Para evitar cualquier null si el archivo llega sin carpeta base,
        //Tal y como dice la Tarea 4 también en la importación. Lo implementé aquí también.
        Path directorio = fichero.toAbsolutePath().getParent(); 
        
        if(!Files.isDirectory(//si es no es un directorio real
                directorio)){
            
            throw new ExcepcionDirectorioNoExiste(directorio);
        }
        //hago el absolute path para verificar que exista previamente
        if(Files.exists(fichero.toAbsolutePath())&& sobrescribir == false){
            throw new ExcepcionOperacionCancelada(fichero);
        }
        
    }
}
