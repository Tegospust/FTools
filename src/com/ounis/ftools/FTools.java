/*
 *  Bla, bla, blaaam!!!

 */
package com.ounis.ftools;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author AndroidAppsPlatform
 */
public class FTools {
    /**
     * zwracane z funkcji getFileNumberOfLines() w przypadku niepowodzenia
     */
    public static final int CANT_COUNT_LINES_OF_FILE = -1;
    private static final String FILE_EXT_WIN_REGEX = "\\\\(?=[^\\\\]+$)";
    private static final String FILE_EXT_UNIX_REGEX = "\\/(?=[^\\/]+$)";
    
    
    public static URL getFileURL(String fileName)
    {
        File file = new File(fileName);
        
        try {
            return file.toURI().toURL();
        }
        catch (MalformedURLException e){
            
            return null;
        }
    }
    
public static String getAppPath() 
    {
        final String FNAME = ".asa";
        String s = "";
        File file = new File(FNAME);
        
        try
        {
            s = file.getAbsolutePath().split(FNAME)[0];
        }
        catch (Exception e) {
            e.printStackTrace();
            s = "";
        }
        finally{
        return s;
        }
    }
    
    @Deprecated
    public static boolean fileExists(String filename) 
    {
        boolean result = true;
           
        BufferedReader file = null;
        try
        {
             file = new BufferedReader(
             new InputStreamReader(new FileInputStream(filename)));
             file.close();
        }
//        catch (FileNotFoundException e)
//        {
//           result = false;
//        }
        catch (IOException e)
        {
            result = false;
        }
        return result;
    }
             
    /**
     * Sprawdza czy podany plik istnieje
     * @param filename nazwa do sprawdzenia (filename == null -> zwraca false)
     * @return true/false
     */
    public static boolean fileExistsEx(String filename) {
        
        //
        // https://stackabuse.com/java-check-if-a-file-or-directory-exists/
        //
        //Files.exists(filename, LinkOption.NOFOLLOW_LINKS);
        //
        // "bezinwazyjne" sprawdzanie istnienia pliku
        Path path = Paths.get(filename);
        return Files.exists(path);
        
//        File file = new File(filename);
//        return Files.exists(file.toPath());
        
        
        // pierwszy sposób ale z jakiegoś powodu blokuje katalog nadrzędny
//        if (filename != null) {
//            return (new File(filename)).exists();
//        }
//        else
//            return false;
    }
    
    /**
     * Zwraca pełną nazwę pliku
     * @param filename nazwa do sprawdzenia (filename == null -> zwraca null)
     
     * @return null/String
     * @throws IOException tak jest rzuca mięchem 
     */
    public static String getCanonicalPath(String filename) throws IOException{
        if (filename != null)
            return (new File(filename).getCanonicalPath());
        else
            return null;
    }

    /**
     * Zwraca względną nazwę pliku.
     * @param filename nazwa do sprawdzenia 
     * @return null/String
     */
    public static String getName(String filename) {
        if (filename != null)
            return (new File(filename).getName());
        else
            return null;
    }
    
    
    /**
     * Zwraca nazwę katalogu nadrzędnego dla pliku
     * @param filename nazwa do sprawdzenia
     * @return null/String
     */
    public static String getParent(String filename) {
        return filename!=null?new File(filename).getParent():filename;
    }
    
    /**
     * Zwraca <code>true</code> jeżeli można odczytać zawartość pliku <b>filename</b>
     * @param filename nazwa do sprawdzenia
     * @return true/false
     */
    public static boolean canRead(String filename) {
        return filename!=null?new File(filename).canRead():false;
    }
    
    /**
     * Sprawdza czy plik można zapisać
     * @param filename nazwa do sprawdzenia
     * @return true/false
     */
    public static boolean canWrite(String filename) {
        return filename!=null?new File(filename).canWrite():false;
    }

    /**
     * Zwraca czas modyfikacji pliku (long)
     * @param filename nazwa do sprawdzenia
     * @return 0 - nie można ustalić/long
     */
    public static long lastModified(String filename) {
        return filename!=null?new File(filename).lastModified():0;
    }

    /**
     * Zwraca czas modyfikacji pliku (Date)
     * @param filename nazwa do sprawdzenia
     * @return Date
     */
    public static Date lastModifiedDT(String filename) {
        long dt = lastModified(filename);
        return new Date(dt);
    }
    
    /**
     * Zwraca wilkość pliku w bajtach
     * @param filename nazwa do sprawdzenia
     * @return 0 - nie można ustalić/długość
     */
    public static long length(String filename) {
        return filename!=null?new File(filename).length():0;
    }
    
    /**
     * Zwraca <code>true</code> jeżeli <b>filename</b> jest plikiem
     * @param filename nazwa do sprawdzenia
     * @return false nie plik, lub nie można ustalić/true - plik
     */
    public static boolean isFile(String filename) {
        return filename!=null?new File(filename).isFile():false;
    }
    
    /**
     * Zwraca <code>true</code> jeżeli @param filename jest katalogiem
     * @param filename nazwa do sprawdzenia
     * @return false - nie katalog lub nie można sprawdzić/ true - katalog
     */
    public static boolean isDirectory(String filename) {
        return filename!=null?new File(filename).isDirectory():false;
    }
    
    /**
     * 
     * Zwraca pełną ścieżkę do pliku bez jego nazwy
     * @param filename nazwa do sprawdzenia
     * @return null/ścieżka dostępu
     * @throws IOException rzuca mięchem jeżeli będą problemy z I/O
     */
    public static String getPath(String filename) throws IOException{
        filename = getCanonicalPath(filename);
        if (filename == null) return filename;

        
// może się przydać:
// lklucze z System.GetProperties
// patrz też projekt: <link>SystemPropertiesList</link>
//
//        java.runtime.name
//        sun.boot.library.path
//        java.vm.version
//        java.vm.vendor
//        java.vendor.url
//        path.separator
//        java.vm.name
//        file.encoding.pkg
//        user.country
//        user.script
//        sun.java.launcher
//        sun.os.patch.level
//        java.vm.specification.name
//        user.dir
//        java.runtime.version
//        java.awt.graphicsenv
//        java.endorsed.dirs
//        os.arch
//        java.io.tmpdir
//        line.separator
//        java.vm.specification.vendor
//        user.variant
//        os.name
//        sun.jnu.encoding
//        java.library.path
//        java.specification.name
//        java.class.version
//        sun.management.compiler
//        os.version
//        user.home
//        user.timezone
//        java.awt.printerjob
//        file.encoding
//        java.specification.version
//        java.class.path
//        user.name
//        java.vm.specification.version
//        sun.java.command
//        java.home
//        sun.arch.data.model
//        user.language
//        java.specification.vendor
//        awt.toolkit
//        java.vm.info
//        java.version
//        java.ext.dirs
//        sun.boot.class.path
//        java.vendor
//        file.separator
//        java.vendor.url.bug
//        sun.io.unicode.encoding
//        sun.cpu.endian
//        sun.desktop
//        sun.cpu.isalist
        
        String fs = System.getProperty("file.separator");
        String regEx = "";
        
        if (fs.equals("\\")) 
            regEx = FILE_EXT_WIN_REGEX;
        if (fs.equals("/"))
            regEx = FILE_EXT_UNIX_REGEX;
        
        
        if (filename != null) {
            String[] tokens = filename.split(regEx);
            if (tokens.length > 0)
                return tokens[0] + System.getProperty("file.separator");
            else 
                return null;
                          
        }
        else
            return null;
        //return filename!=null?new File(filename).getPath():null;
    }
 
    
    
   /**
     * Zlicza ilość linii w pliku.
     *
     * @param  filename nazwa do
     * 
     * @return ilosć linii z pliku lub
     * <code>FTools.CANT_COUNT_LINES_OF_FILE</code> w przypadku niepowodzenia
     * 
     * @author
     */    
    public static int getFileNumberOfLines(String filename)
    {
        
        
        int result = 0;
        BufferedReader file = null;
        String line;
        try
        {
            file = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename)));
            // line olewamy, chodzi tylko o zliczenie ilości linii
            while ((line = file.readLine()) != null)
                result++;
            
            file.close();
        }
        catch (IOException e)
        {
            // syf kiła i mogiła i jeszce dno i trzy metry mułu, a jeszcze głęniej....
            // to kurwa ręce opadają
            result = CANT_COUNT_LINES_OF_FILE; // no i z tym można dyskutować ale ta -1 to taki znak
                         // z odmętów, że coś jednak poszło nie tak i liczenie
                         // linii się posypało
        }
        finally
        {
            return result;
        }
    }
    
}
