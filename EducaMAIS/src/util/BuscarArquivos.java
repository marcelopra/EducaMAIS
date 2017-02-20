/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcelo
 */
public class BuscarArquivos implements Runnable {
    private File[] dirsBusca;
    private FileFilter filtro;
    private List<File> arqsEncontrados;
    
    public BuscarArquivos(File[] dirsBusca, FileFilter filtro) {
        this.dirsBusca = dirsBusca;
        this.filtro = filtro;
        this.arqsEncontrados = new ArrayList<File>();
    }
    
    public BuscarArquivos(List<File> dirsBusca, FileFilter filtro) {
        this((File[])dirsBusca.toArray(), filtro);
    }

    public BuscarArquivos(String dirBusca, FileFilter filtro) {
        this(new File[] {new File(dirBusca)}, filtro);
    }
    
    public void buscar() {
        try {
            Thread t = new Thread(this);
            t.start();
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscarArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run() {
        for(File f : dirsBusca) {
            buscar(f);
        }
    }
    
    public List<File> getArqsEncontrados() {
        return arqsEncontrados;
    }
    
    private void buscar(File dirBusca) {
        //System.out.println(dirBusca);
        File[] listFiles = dirBusca.listFiles();
        if(listFiles != null && listFiles.length > 0) {
            for(File f : listFiles) {
                if(f.isDirectory()) {
                    buscar(f);
                } else {
                    if(filtro.accept(f)) {
                        arqsEncontrados.add(f);
                    }
                }
            }
        }
    }
}
