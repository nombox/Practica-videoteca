package videoteca.persistencia;

import java.io.Closeable;
//Librerías de Sebastian
import java.io.FileInputStream;
import java.io.ObjectInputStream;
//--------------------------
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Busca caratulas en un fichero de acceso directo. SOLO LECTURA.
 *
 * TAREA 8. Apuntes: apartado 6 (RandomAccessFile), apartado 7 (serializacion), UD1 EP 13.
 *
 * Los ficheros imagenes.dat e indice.dat están en la carpeta config.
 * Deberán buscarse esos ficheros tanto en el propio directorio como en cualquier subdirectorio 
 * dentro de él de forma recursiva ( directorioConfig/.../imagenes.dat, directorioConfig/.../.../imagenes.dat
 * , etc).
 * 
 * Al leer: se lee el registro ENTERO y se quitan los ceros del final ("trim" para bytes).
 * Es correcto porque las caratulas son PNG, y un PNG termina siempre en 0x82 (nunca en 0).
 */
public class AlmacenImagenes implements Closeable {

    public static final int TAMANO_REGISTRO = 100 * 1024;

    private final Path ficheroImagenes;
    private final Path ficheroIndice;
    private RandomAccessFile almacen;
    private Map<Integer, Integer> indice; // idPelicula -> numero de registro

    public AlmacenImagenes(Path ficheroImagenes, Path ficheroIndice) {
        this.ficheroImagenes = ficheroImagenes;
        this.ficheroIndice = ficheroIndice;
    }

    /**
     * Abre el fichero de imagenes y carga el indice.
     *
     * PASOS:
     *   1. RandomAccessFile en modo "r" (solo lectura) sobre ficheroImagenes
     *      (pide un File: usa ficheroImagenes.toFile()).
     *   2. indice = cargarIndice();
     *
     * TODO: implementar.
     */
    public void abrir() throws IOException {
        try{
            this.almacen = new RandomAccessFile(ficheroImagenes.toFile(),"r");
            this.indice = cargarIndice();
        }catch(IOException ex){
            System.out.println("Error! " + ex.getMessage());//Revisar
        }
        
       
    }

    /**
     * Lee el indice de ficheroIndice DE OBJETO casteando a Map<Integer,Integer>).
     *
     * TODO: implementar.
     */
    @SuppressWarnings("unchecked")
    private Map<Integer, Integer> cargarIndice() throws IOException {
        Map<Integer, Integer> indice = null;
        try(FileInputStream fis = new FileInputStream(ficheroIndice.toFile());
            ObjectInputStream os = new ObjectInputStream(fis)){
            indice = (Map<Integer, Integer>) os.readObject();
        } catch (ClassNotFoundException ex) {
            return indice;
        }
        return indice;
    }

    /**
     * Devuelve los bytes de la imagen de esa pelicula, o null si no tiene ninguna.
     *
     * PASOS:
     *   1. Busca el registro en el indice (si no esta -> return null).
     *   2. seek(numRegistro * TAMANO_REGISTRO)  -> cuidado: usa (long) para que no desborde.
     *   3. Lee el registro ENTERO con readFully (un byte[TAMANO_REGISTRO]).
     *   4. Recorre el array desde el final mientras el byte sea 0.
     *   5. Devuelve solo la parte util (Arrays.copyOf).
     *
     * TODO: implementar.
     */
    public byte[] obtener(int idPelicula) throws IOException {
         byte[] retorno=null;
        if(indice.get(idPelicula)!=null){
           try{
              this.almacen.seek(idPelicula*TAMANO_REGISTRO);
           } catch(IOException ex){
            ex.printStackTrace();//Revisar
           }
        }
        
        return retorno;
    }

    @Override
    public void close() throws IOException {
        if (almacen != null) {
            almacen.close();
        }
    }
}
