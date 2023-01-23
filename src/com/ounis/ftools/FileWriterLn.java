

package com.ounis.ftools;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * Rozszerzenie klasy FileWriter o metodę writeln(), która dodaje separator linii.
 * Separator jest pobieralny poprzez 
 * <code>System.getProperty("line.separator")</code>
 * i zapisywny w prywaciarzu <code>lineSep</code>
 * 
 * 
 * @author AndroidAppsPlatform
 * 
 * 
 */
public class FileWriterLn extends FileWriter{
    
    
    private static final String ENV_LINE_SEPARATOR = "line.separator";
    
    private final String lineSep;
    
    {
        lineSep = System.getProperty(ENV_LINE_SEPARATOR);
    }

    /**
     * zwraca aktualny dla danego systemu oper. separator linii
     * wykorzystywany w metodzie writeln()
     * 
     * @return separator linii
     */
    public String getLineSep(){
        return lineSep;
    }


    /**
     * konstruktor klasy FileWriterEx
     * 
     * @param fileName nazwa pliku
     * @throws IOException w razie niepowodzenia potrafi opierdolić...
     */
    public FileWriterLn(String fileName) throws IOException {
        super(fileName);
    }
    

    /**
     * konstruktor klasy FileWriterEx
     * 
     * @param fileName nazwa pliku
     * @param append   patrz konstruktor klasy nadrzędnej
     * @throws IOException  - w razie niepowdzenia rzuca mięchem...
     */
    public FileWriterLn(String fileName, boolean append) throws IOException{
        super(fileName, append);
    }
    
    
    
    /**
     * zapisuje linię w pliku dodając separator końca linii
     * @param s dane do zapisu
     * @throws IOException  w razie niepowodzenia rzuca mięchem...
     */
    public void writeln(String s) throws IOException {
        write(s + lineSep);
    }
    
    
}
